package sample;

import org.jsoup.Jsoup;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main extends JFrame { //Наследуя от JFrame, мы получаем всю функциональность окна

    public Main(){
        super("Betting Helper"); //Заголовок окна

        int width = 1600;
        int height = 1000;
        int var = 0;
        if (var == 0){
            width = 1350;
            height = 830;
        }

        setBounds(10, 5, width, height); //отступы и размеры окна
        super.setResizable(false);
        String tabs[] = {"Добавление в базу", "Настройки", "Учет пользователей", "Матч-центр"};  /*Настройки и редактирование базы*/
        JTabbedPane jtp = new JTabbedPane();

        String sn = null;
        try {
            sn = Settings.getSerialKey();
        } catch (Exception e) {
            e.printStackTrace();
        }

        PanelSettings panelSettings = new PanelSettings(width, height);
        PanelAddingInfo panelAddingInfo = new PanelAddingInfo();
        PanelUsers panelUsers = new PanelUsers(width, height);
        //PanelAddKHL1920 panelAddKHL1920 = new PanelAddKHL1920(width, height);
        PanelMatchCenter panelMatchCenter = new PanelMatchCenter();

        jtp.addTab(tabs[0], panelAddingInfo);
        add(jtp);
        jtp.addTab(tabs[3], panelMatchCenter);
        add(jtp);
        jtp.addTab(tabs[1], panelSettings);
        add(jtp);
        jtp.addTab(tabs[2], panelUsers);
        add(jtp);


        jtp.setSelectedIndex(0);

        /*Team team = new Team();
        team.addMatch(match);*/


        /*org.jsoup.nodes.Document doc1 = null;
        org.jsoup.nodes.Document doc2;
        try {
            doc1 = Jsoup.connect("https://www.khl.ru/game/671/69800/protocol/").get();
            doc2 = Jsoup.connect("https://text.khl.ru/text/69800.html").get();
            Match match = new Match(doc1, doc2, "KHL");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*String[] mas = {
                "Anaheim",
                "Arizona",
                "Boston",
                "Buffalo",
                "Calgary",
                "Carolina",
                "Chicago",
                "Colorado",
                "Columbus",
                "Dallas",
                "Detroit",
                "Edmonton",
                "Florida",
                "LAKings",
                "Minnesota",
                "Montreal",
                "Nashville",
                "NewJersey",
                "NYIslanders",
                "NYRangers",
                "Ottawa",
                "Philadelphia",
                "Pittsburgh",
                "SanJose",
                "StLouis",
                "TampaBay",
                "Toronto",
                "Vancouver",
                "Vegas",
                "Washington",
                "Winnipeg"
        };
        for (int i = 0; i<mas.length; i++){
            //Team team = Team.getTeamFromFileWithSeason(mas[i], "20-21");
            //team.points = 3*team.wins + 2*team.winsOT + team.losesOT;
            Team team = new Team(mas[i]);
            team.pushTeamToFile();
        }*/

//        int ttt = 0;
//        League.resetLeagueInfo("NHL", "20-21");
//        System.out.println("********************************");
//        League.resetLeagueInfo("KHL", "20-21");
//        System.out.println("********************************");
//        League.resetLeagueInfo("VHL", "20-21");
//        System.out.println("********************************");
//        League.resetLeagueInfo("NHL", "17-18");
//        System.out.println("********************************");
//        League.resetLeagueInfo("KHL", "17-18");
//        System.out.println("********************************");
//        League.resetLeagueInfo("NHL", "18-19");
//        System.out.println("********************************");
//        League.resetLeagueInfo("KHL", "18-19");
//        System.out.println("********************************");
//        League.resetLeagueInfo("NHL", "18-19_Playoffs");
//        System.out.println("********************************");
//        League.resetLeagueInfo("KHL", "18-19_Playoffs");
//        System.out.println("********************************");
//        League.resetLeagueInfo("NHL", "19-20");
//        System.out.println("********************************");
//        League.resetLeagueInfo("KHL", "19-20");
//        System.out.println("********************************");
//        League.resetLeagueInfo("NHL", "19-20_Playoffs");
//        System.out.println("********************************");
//        League.resetLeagueInfo("KHL", "19-20_Playoffs");
//        System.out.println("********************************");

        /*org.jsoup.nodes.Document doc1;
        org.jsoup.nodes.Document doc2;
        String protocolUri = "https://www.khl.ru/game/1045/872631/protocol/";
        String textUri = "https://text.khl.ru/text/872631.html";

        try {
            doc1 = Jsoup.connect(protocolUri).get();
            doc2 = Jsoup.connect(textUri).get();
            Match match = new Match(doc1, doc2, "KHL", protocolUri);
        } catch (IOException e) {
            e.printStackTrace();
        }*/



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //это нужно для того, чтобы при закрытии окна закрывалась и программа, иначе она останется висеть в процессах
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            public void windowClosing(WindowEvent event) {

                Settings.deleteAllFilesFolder("tmp/");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
    }

    public static double roundResult(double d, int precise) {
        precise = (int) Math.pow(10,precise);
        d = d*precise;
        int i = (int) Math.round(d);
        return (double) i/precise;
    }

    void writeFile() throws FileNotFoundException {
        try {
            // отрываем поток для записи
            FileOutputStream fos = new FileOutputStream("");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            // пишем данные
            for (int i = 0; i < 10; i++) {
                String str = String.valueOf(i);
                bw.write(str);
                bw.newLine();
            }
            // закрываем поток
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // Конец процедуры записи списка в файл

    public static String[] readTxtFile(String fileName){
        String resultS = "Команда\n";
        try {
            File fileDir = new File(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                resultS += str + "\n";
            }
            //in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return resultS.split("\n");
    }

}
