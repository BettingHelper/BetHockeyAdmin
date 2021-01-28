package sample;

import org.apache.commons.net.ftp.FTP;
import org.jfree.ui.tabbedui.VerticalLayout;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class PanelSettings extends JPanel{
    int WIDTH;
    int DEFWIDTH = 1600;
    double procWIDTH;
    int HEIGHT;
    int DEFHEIGHT = 1000;
    double procHEIGHT;
    Settings settings;
    JPanel container;
    final String path = Settings.getPathToDatabase();

    public PanelSettings(int width, int height){
        int defHeight = 1000;
        WIDTH = width;
        HEIGHT = height;
        procWIDTH = WIDTH / (double) DEFWIDTH;
        procHEIGHT = HEIGHT / (double) DEFHEIGHT;

        this.setLayout(null);

        settings = Settings.getSettingsFromFile();

        final JPanel redactorPanel = new JPanel();
        redactorPanel.setBorder(
                BorderFactory.createTitledBorder("Редактирование матча"));
        redactorPanel.setLocation(245, 5);
        redactorPanel.setSize(width - 240 - 15, 670);
        redactorPanel.setLayout(null);

        Font fontForButton = new Font("", 0, 25);

        JPanel databasePathPanel = new JPanel();
        databasePathPanel.setBorder(
                BorderFactory.createTitledBorder("Путь к базе данных"));
        databasePathPanel.setSize((int) (0.5 * width), (int) (0.92 * height));
        databasePathPanel.setLocation(0, 0);
        databasePathPanel.setLayout(null);

        final JFileChooser directoryChooser = new JFileChooser(Settings.getPathToDatabase());
        directoryChooser.setSize(600, 300);
        directoryChooser.setLocation(5, 5);
        databasePathPanel.add(directoryChooser);

        JButton buttonSetPath = new JButton("Сохранить");
        buttonSetPath.setSize(new Dimension((int) (0.4875 * width), 45 - (defHeight - height) / 40));
        buttonSetPath.setLocation(5, 310);
        buttonSetPath.setFont(fontForButton);

        databasePathPanel.add(buttonSetPath);


        final JButton buttonZipDatabase = new JButton("Архивировать базу");
        buttonZipDatabase.setSize(new Dimension((int) (0.4875 * width), 45 - (defHeight - height) / 40));
        buttonZipDatabase.setLocation(5, 400);
        buttonZipDatabase.setFont(fontForButton);

        databasePathPanel.add(buttonZipDatabase);

        this.add(databasePathPanel);

        JPanel reloadMatch = new JPanel(new VerticalLayout());
        reloadMatch.setBorder(BorderFactory.createTitledBorder("Перезагрузить матч"));
        reloadMatch.setSize((int) (0.5 * width) - 10, 500);
        reloadMatch.setLocation((int) (0.5 * width), 0);

        final JTextArea textArea = new JTextArea("Введите ссылку на протокол матча");
        reloadMatch.add(textArea);

        JButton buttonReload = new JButton("Перезагрузить");
        reloadMatch.add(buttonReload);


        this.add(reloadMatch);


        buttonSetPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = directoryChooser.getCurrentDirectory().toString();
                if (path.contains("database")){
                    path = path + "/";
                } else{
                    String[] list = directoryChooser.getCurrentDirectory().list();
                    for (int i = 0; i<list.length; i++){
                        if (list[i].equals("database")){
                            path = path + "/database/";
                        }
                    }
                }
                Settings.setPathToDatabase(path);
            }
        });

        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textArea.setText("");
                Font font10 = new Font("", 0, 12);
                textArea.setFont(font10);
                textArea.setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        buttonZipDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.compress("database");
                try {
                    FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/databaseCopies/databaseHBH.zip", "database.zip");
                    Settings.setLastUpdate();

                    FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/databaseCopies/lastUpdateHBH.txt", "tmp/lastUpdateHBH.txt");
                    buttonZipDatabase.setText("База скопирована успешно!");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttonReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String protocolUri = textArea.getText();  //https://www.khl.ru/game/671/70877/protocol/
                String textUri = "https://text.khl.ru/text/" + protocolUri.split("671/")[1].split("/protocol")[0] + ".html";
                Document doc1;
                Document doc2;
                try {
                    doc1 = Jsoup.connect(protocolUri).get();
                    doc2 = Jsoup.connect(textUri).get();
                    Match match = new Match(doc1, doc2, "KHL", textUri);
                    Team homeTeam = Team.getTeamFromFileWithSeason(match.homeTeam, "18-19");
                    Team awayTeam = Team.getTeamFromFileWithSeason(match.awayTeam, "18-19");
                    Match oldMatch = Match.getMatchFromFileByName("database/18-19/KHL/Matches/" + Team.getShortName(match.homeTeam)
                            +  Team.getShortName(match.awayTeam) + match.date + ".xml");

                    homeTeam.missedShots = homeTeam.missedShots - oldMatch.homeMissedShots + match.homeMissedShots;
                    homeTeam.missedShotsOpponent = homeTeam.missedShotsOpponent - oldMatch.awayMissedShots + match.awayMissedShots;
                    awayTeam.missedShots = awayTeam.missedShots - oldMatch.awayMissedShots + match.awayMissedShots;
                    awayTeam.missedShotsOpponent = awayTeam.missedShotsOpponent - oldMatch.homeMissedShots + match.homeMissedShots;
                    homeTeam.pushTeamToFile();
                    String path1 = "database/" + Settings.getCurrentSeason(Team.getLeague(homeTeam.teamName)) + "/" + Team.getLeague(match.homeTeam) + "/Teams/" + match.homeTeam + ".xml";
                    awayTeam.pushTeamToFile();
                    String path2 = "database/" + Settings.getCurrentSeason(Team.getLeague(homeTeam.teamName)) + "/" + Team.getLeague(match.awayTeam) + "/Teams/" + match.awayTeam + ".xml";
                    match.pushMatchToFile(true);
                    String path3 = "database/" + Settings.getCurrentSeason(Team.getLeague(homeTeam.teamName)) + "/" + Team.getLeague(match.homeTeam)
                            + "/Matches/" + Team.getShortName(match.homeTeam) + Team.getShortName(match.awayTeam) + match.date + ".xml";


                    LogWriter.writeUpdates(path1);
                    LogWriter.writeUpdates(path2);
                    LogWriter.writeUpdates(path3);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

}