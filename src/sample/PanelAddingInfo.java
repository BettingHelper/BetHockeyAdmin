package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HostnameVerifier;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class PanelAddingInfo extends JPanel{
    public PanelAddingInfo() {
        this.setLayout(new GridLayout(1, 2, 0, 0));

        ////////////////////////////////////////////ОСНОВНАЯ СТАТИСТИКА (НАЧАЛО)
        JPanel addMainStatsPanel = new JPanel(new BorderLayout());
        addMainStatsPanel.setBorder(
                BorderFactory.createTitledBorder("Добавление основной статистики матча"));
        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        final JTextArea textArea = new JTextArea("Вставьте сюда ссылку на матч.\nПрограмма автоматически заполнит данные, если их состав будет полным.");
        textArea.setMargin(new Insets(0, 5, 0, 0));
        scrollPane.add(textArea);
        addMainStatsPanel.add(scrollPane);
        JButton buttonAddInfo = new JButton("Перезаписать таблицы лиг!");
        final Font fontForButton = new Font("Добавить!", 0, 25);
        buttonAddInfo.setFont(fontForButton);
        addMainStatsPanel.add(buttonAddInfo, BorderLayout.SOUTH);
        this.add(addMainStatsPanel);
        ////////////////////////////////////////////ОСНОВНАЯ СТАТИСТИКА  (КОНЕЦ)

        JPanel panelAddMatches = new JPanel(new GridLayout(3 , 1, 0, 0));

        JPanel addingNHLmatchesPanel = new JPanel(new BorderLayout());
        addingNHLmatchesPanel.setBorder(
                BorderFactory.createTitledBorder("Добавление матчей НХЛ"));

        JButton buttonAddNHLMatches = new JButton("Добавить");
        buttonAddNHLMatches.setFont(fontForButton);
        addingNHLmatchesPanel.add(buttonAddNHLMatches, BorderLayout.NORTH);

        ScrollPane scrollPaneNHL = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        final JTextArea resultOfAddingNHLgame = new JTextArea("");
        resultOfAddingNHLgame.setMargin(new Insets(0, 5, 0, 0));
        scrollPaneNHL.add(resultOfAddingNHLgame);
        addingNHLmatchesPanel.add(scrollPaneNHL);

        panelAddMatches.add(addingNHLmatchesPanel);

        JPanel addingKHLmatchesPanel = new JPanel(new BorderLayout());
        addingKHLmatchesPanel.setBorder(
                BorderFactory.createTitledBorder("Добавление матчей KХЛ"));

        JButton buttonAddMatchesKHL = new JButton("Добавить");
        buttonAddMatchesKHL.setFont(fontForButton);
        addingKHLmatchesPanel.add(buttonAddMatchesKHL, BorderLayout.NORTH);

        ScrollPane scrollPaneKHL = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        final JTextArea resultOfAddingKHLgame = new JTextArea("");
        resultOfAddingKHLgame.setMargin(new Insets(0, 5, 0, 0));
        scrollPaneKHL.add(resultOfAddingKHLgame);
        addingKHLmatchesPanel.add(scrollPaneKHL);

        panelAddMatches.add(addingKHLmatchesPanel);

        JPanel addingVHLmatchesPanel = new JPanel(new BorderLayout());
        addingVHLmatchesPanel.setBorder(
                BorderFactory.createTitledBorder("Добавление матчей ВХЛ"));

        JButton buttonAddMatchesVHL = new JButton("Добавить");
        buttonAddMatchesVHL.setFont(fontForButton);
        addingVHLmatchesPanel.add(buttonAddMatchesVHL, BorderLayout.NORTH);

        ScrollPane scrollPaneVHL = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        final JTextArea resultOfAddingVHLgame = new JTextArea("");
        resultOfAddingVHLgame.setMargin(new Insets(0, 5, 0, 0));
        scrollPaneVHL.add(resultOfAddingVHLgame);
        addingVHLmatchesPanel.add(scrollPaneVHL);

        panelAddMatches.add(addingVHLmatchesPanel);

        this. add(panelAddMatches);



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

        buttonAddInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pathToLeaguesInfo = Settings.getPathToDatabase().replace("database", "leaguesInfo");
                String[] list = new JFileChooser(pathToLeaguesInfo).getCurrentDirectory().list();
                String resultOfFilling;
                String seasonToRewrite1 = "20-21";
                //String seasonToRewrite2 = "19_20_Playoffs";

                for (int i=0; i<list.length; i++){
                    if (list[i].contains("_" + seasonToRewrite1) /*|| list[i].contains("_" + seasonToRewrite2 + ".")*/){
                        League league = League.getLeagueFromFileByName(pathToLeaguesInfo + list[i]);
                        resultOfFilling = league.fillDataFromAddingList();
                        textArea.setText( textArea.getText() + resultOfFilling);
                    }

                }
            }
        });

        // ДОБАВЛЕНИЕ РЕГУЛЯРКИ ДО ПЛЕЙ-ОФФ
        /*buttonAddNHLMatches.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultOfAddingNHLgame.setText("");
                int index = Integer.parseInt(Settings.getLastAddedNHLmatch()) + 1;
                int k = 0;
                boolean flagNoMatch = false;
                while (k<10 && !flagNoMatch){
                    String s = String.valueOf(index);
                    while (s.length() < 4)
                        s = "0" + s;
                    String reportUrl = "http://www.nhl.com/scores/htmlreports/20192020/GS02" + s + ".HTM";
                    Document doc1 = null;
                    Document doc2;
                    Match match;
                    boolean fileNotFoundFlag = true;
                    try {
                        doc1 = Jsoup.connect(reportUrl).get();
                        String reportUrl2 = reportUrl.replace("/GS", "/ES");
                        doc2 = Jsoup.connect(reportUrl2).get();
                        match = new Match(doc1, doc2, "NHL", reportUrl);
                        final String curSeason = Settings.getCurrentSeason("NHL");
                        String matchTitle = Team.getShortName(match.homeTeam) + Team.getShortName(match.awayTeam) + match.date;
                        Team t1 = Team.getTeamFromFileWithSeason(match.homeTeam, curSeason);
                        for (int i = 0; i < t1.matchList.size(); i++) {
                            if (t1.matchList.get(i).equals(matchTitle))
                                fileNotFoundFlag = false;
                        }
                        if (fileNotFoundFlag) {
                            match.pushMatchToFile();
                            Team homeTeam = Team.getTeamFromFileWithSeason(match.homeTeam, curSeason);
                            homeTeam.addMatch(match);
                            Team awayTeam = Team.getTeamFromFileWithSeason(match.awayTeam, curSeason);
                            awayTeam.addMatch(match);

                            String path1 = "database/" + curSeason + "/" + Team.getLeague(match.homeTeam) + "/Teams/" + match.homeTeam + ".xml";
                            String path2 = "database/" + curSeason + "/" + Team.getLeague(match.awayTeam) + "/Teams/" + match.awayTeam + ".xml";
                            String path3 = "database/" + curSeason + "/" + Team.getLeague(match.homeTeam) + "/Matches/" + matchTitle + ".xml";

                            //LogWriter.writeUpdates(path1);
                            //LogWriter.writeUpdates(path2);
                            //LogWriter.writeUpdates(path3);
                            resultOfAddingNHLgame.setText(resultOfAddingNHLgame.getText()
                                    + match.homeTeam + " vs " + match.awayTeam + " (" + match.date + ") успешно добавлен." + "\n") ;
                            index++;
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingNHLgame.setFont(fontForText);
                            LogWriter.writeLog("Added match statistics - " + match.homeTeam + " vs " + match.awayTeam + "   (" + matchTitle + ");");
                            Settings.setLastAddedNHLmatch(String.valueOf(index));
                        } else {
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingNHLgame.setFont(fontForText);
                            resultOfAddingNHLgame.setText(resultOfAddingNHLgame.getText()
                                    + "Этот матч уже был добавлен в базу ранее!" + "\n");
                            index++;
                            Settings.setLastAddedNHLmatch(String.valueOf(index));
                        }

                    } catch (IOException e1) {
                        if (doc1 == null){
                            flagNoMatch = true;
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingNHLgame.setFont(fontForText);
                            resultOfAddingNHLgame.setText(resultOfAddingNHLgame.getText()
                                    + "Необработанные матчи закончились!" + "\n");
                            resultOfAddingNHLgame.setForeground(new Color(13, 161, 13));
                        } else {
                            e1.printStackTrace();
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingNHLgame.setFont(fontForText);
                            resultOfAddingNHLgame.setText(resultOfAddingNHLgame.getText()
                                    + "Данные НЕ УДАЛОСЬ обработать!" + "\n");
                            resultOfAddingNHLgame.setForeground(Color.red);
                        }
                    }
                    k++;
                }
                index --;
                Settings.setLastAddedNHLmatch(String.valueOf(index));
            }
        });*/

        buttonAddNHLMatches.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String reportUrl = resultOfAddingNHLgame.getText();
                int index = Integer.parseInt(Settings.getLastAddedNHLmatch()) + 1;
                String stringIndex = String.valueOf(index);
                while (stringIndex.length()<4){
                    stringIndex = "0" + stringIndex;
                }

                String reportUrl = "http://www.nhl.com/scores/htmlreports/20202021/GS02"  + stringIndex + ".HTM";

                Document doc1 = null;
                Document doc2;
                Match match;
                boolean fileNotFoundFlag = true;
                try {
                    doc1 = Jsoup.connect(reportUrl).get();
                    String reportUrl2 = reportUrl.replace("/GS", "/ES");
                    doc2 = Jsoup.connect(reportUrl2).get();
                    match = new Match(doc1, doc2, "NHL", reportUrl);
                    final String curSeason = Settings.getCurrentSeason("NHL");
                    String matchTitle = Team.getShortName(match.homeTeam) + Team.getShortName(match.awayTeam) + match.date;
                    Team t1 = Team.getTeamFromFileWithSeason(match.homeTeam, curSeason);
                    for (int i = 0; i < t1.matchList.size(); i++) {
                        if (t1.matchList.get(i).equals(matchTitle))
                            fileNotFoundFlag = false;
                    }
                    if (fileNotFoundFlag) {
                        match.pushMatchToFile(true);
                        Team homeTeam = Team.getTeamFromFileWithSeason(match.homeTeam, curSeason);
                        homeTeam.addMatch(match);
                        Team awayTeam = Team.getTeamFromFileWithSeason(match.awayTeam, curSeason);
                        awayTeam.addMatch(match);

                        //String path1 = "database/" + curSeason + "/" + Team.getLeague(match.homeTeam) + "/Teams/" + match.homeTeam + ".xml";
                        //String path2 = "database/" + curSeason + "/" + Team.getLeague(match.awayTeam) + "/Teams/" + match.awayTeam + ".xml";
                        //String path3 = "database/" + curSeason + "/" + Team.getLeague(match.homeTeam) + "/Matches/" + matchTitle + ".xml";

                        //LogWriter.writeUpdates(path1);
                        //LogWriter.writeUpdates(path2);
                        //LogWriter.writeUpdates(path3);
                        resultOfAddingNHLgame.setText(resultOfAddingNHLgame.getText()
                                + match.homeTeam + " vs " + match.awayTeam + " (" + match.date + ") успешно добавлен." + "\n") ;
                        //index++;
                        Font fontForText = new Font("", 0, 15);
                        resultOfAddingNHLgame.setFont(fontForText);
                        LogWriter.writeLog("Added match statistics - " + match.homeTeam + " vs " + match.awayTeam + "   (" + matchTitle + ");");
                        Settings.setLastAddedNHLmatch(String.valueOf(index));
                    } else {
                        Font fontForText = new Font("", 0, 15);
                        resultOfAddingNHLgame.setFont(fontForText);
                        resultOfAddingNHLgame.setText(resultOfAddingNHLgame.getText()
                                + "Этот матч уже был добавлен в базу ранее!" + "\n");
                        //index++;
                        Settings.setLastAddedNHLmatch(String.valueOf(index));
                    }

                } catch (IOException e1) {
                    if (doc1 == null){
                        Font fontForText = new Font("", 0, 15);
                        resultOfAddingNHLgame.setFont(fontForText);
                        resultOfAddingNHLgame.setText(resultOfAddingNHLgame.getText()
                                + "Необработанные матчи закончились!" + "\n");
                        resultOfAddingNHLgame.setForeground(new Color(13, 161, 13));
                    } else {
                        e1.printStackTrace();
                        Font fontForText = new Font("", 0, 15);
                        resultOfAddingNHLgame.setFont(fontForText);
                        resultOfAddingNHLgame.setText(resultOfAddingNHLgame.getText()
                                + "Данные НЕ УДАЛОСЬ обработать!" + "\n");
                        resultOfAddingNHLgame.setForeground(Color.red);
                    }
                }

                //Settings.setLastAddedNHLmatch(String.valueOf(index));
            }
        });

        buttonAddMatchesKHL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar c = Calendar.getInstance();
                int month = c.get(Calendar.MONTH)+1;
                resultOfAddingKHLgame.setText("");
                Document doc;
                Document doc1;
                Document doc2;
                String currentMonth = Settings.getCurrentMonth();
                int currentDay = 0;
//                String reportUrl ="https://www.khl.ru/calendar/671/" + currentMonth +"/";
//                String reportUrl ="https://www.khl.ru/calendar/674/" + currentMonth +"/";
//                String reportUrl ="https://www.khl.ru/calendar/851/" + currentMonth +"/";
                String reportUrl ="https://www.khl.ru/calendar/1045/" + currentMonth +"/";
                try {
                    System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
                    doc =  Jsoup.connect(reportUrl).get();
                    Element element = doc.getElementById("tab-calendar-last");
                    Elements days = element.getElementsByClass("b-final_cup_date");
                    int indexOfDayToAdding = -1;
                    boolean flagBegin = false;
                    int i = 0;
                    while (i<days.size() && !flagBegin){
                        currentDay = Settings.getDayCode(days.get(i).select("b").get(0).ownText().split(",")[0]);
                        if (currentDay > Integer.parseInt(Settings.getLastAddedDayKHL())){
                            flagBegin = true;
                            indexOfDayToAdding = i;
                        }
                        i++;
                    }
                    if (indexOfDayToAdding > -1){
                        Element currentDayEl = element.getElementsByClass("m-future").get(indexOfDayToAdding);
                        Elements matches = currentDayEl.getElementsByClass("b-wide_tile_item");
                        for (int k=0; k<matches.size(); k++){
                            String protocolUri = "f";
                            String textUri = "f";
                            Elements li = matches.get(k).select("a");
                            for (int j=1; j<li.size(); j++){
                                if (li.get(j).text().contains("Протокол")){
                                    protocolUri = li.get(j).attr("href");
                                    textUri = li.get(j+1).attr("href");
                                }

                            }
                            protocolUri = "https://www.khl.ru/" + protocolUri;
                            doc1 = Jsoup.connect(protocolUri).get();
                            doc2 = Jsoup.connect(textUri).get();

                            try {
                                Match match = new Match(doc1, doc2, "KHL", protocolUri);
                                final String curSeason = Settings.getCurrentSeason("KHL");
                                boolean fileNotFoundFlag = true;
                                String matchTitle = Team.getShortName(match.homeTeam) + Team.getShortName(match.awayTeam) + match.date;
                                Team t1 = Team.getTeamFromFileWithSeason(match.homeTeam, curSeason);
                                for (int j = 0; j < t1.matchList.size(); j++) {
                                    if (t1.matchList.get(j).equals(matchTitle))
                                        fileNotFoundFlag = false;
                                }
                                if (fileNotFoundFlag) {
                                    match.pushMatchToFile(true);
                                    Team homeTeam = Team.getTeamFromFileWithSeason(match.homeTeam, curSeason);
                                    homeTeam.addMatch(match);
                                    Team awayTeam = Team.getTeamFromFileWithSeason(match.awayTeam, curSeason);
                                    awayTeam.addMatch(match);

                                    //String path1 = "database/" + curSeason + "/" + Team.getLeague(match.homeTeam) + "/Teams/" + match.homeTeam + ".xml";
                                    //String path2 = "database/" + curSeason + "/" + Team.getLeague(match.awayTeam) + "/Teams/" + match.awayTeam + ".xml";
                                    //String path3 = "database/" + curSeason + "/" + Team.getLeague(match.homeTeam) + "/Matches/" + matchTitle + ".xml";

                                    //LogWriter.writeUpdates(path1);
                                    //LogWriter.writeUpdates(path2);
                                    //LogWriter.writeUpdates(path3);
                                    resultOfAddingKHLgame.setText(resultOfAddingKHLgame.getText()
                                            + match.homeTeam + " vs " + match.awayTeam + " (" + match.date + ") успешно добавлен." + "\n") ;
                                    Font fontForText = new Font("", 0, 15);
                                    resultOfAddingKHLgame.setFont(fontForText);
                                    LogWriter.writeLog("Added match statistics - " + match.homeTeam + " vs " + match.awayTeam + "   (" + matchTitle + ");");
                                    Settings.setLastAddedDayKHL(String.valueOf(currentDay));
                                } else {
                                    Font fontForText = new Font("", 0, 15);
                                    resultOfAddingKHLgame.setFont(fontForText);
                                    resultOfAddingKHLgame.setText(resultOfAddingKHLgame.getText()
                                            + "Этот матч уже был добавлен в базу ранее!" + "\n");
                                }
                            } catch (IndexOutOfBoundsException e1){
                                System.out.println("Данные одной игры обработать не удалось.");
                            }

                        }
                    } else{
                        if (month > Integer.parseInt(currentMonth)){
                            resultOfAddingKHLgame.setText("Все матчи месяца добавлены");
                            int cm = Integer.parseInt(currentMonth) + 1;
                            if (cm>12)
                                cm -= 12;
                            Settings.setCurrentMonth(String.valueOf(cm));
                            int dayCode = Settings.getNextDayCode(Integer.parseInt(Settings.getLastAddedDayKHL()));
                            Settings.setLastAddedDayKHL(String.valueOf(dayCode));
                        } else{
                            Font fontForText = new Font("", 0, 35);
                            resultOfAddingKHLgame.setFont(fontForText);
                            resultOfAddingKHLgame.setText(resultOfAddingKHLgame.getText()
                                    + "Необработанные матчи закончились!" + "\n");
                            resultOfAddingKHLgame.setForeground(new Color(13, 161, 13));
                        }
                    }


                } catch (IOException e1) {
                    e1.printStackTrace();
                    Font fontForText = new Font("", 0, 15);
                    resultOfAddingKHLgame.setFont(fontForText);
                    resultOfAddingKHLgame.setText(resultOfAddingKHLgame.getText()
                            + "Данные НЕ УДАЛОСЬ обработать!" + "\n");
                    resultOfAddingKHLgame.setForeground(Color.red);
                }
            }
        });

        buttonAddMatchesVHL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultOfAddingVHLgame.setText("");
                int index = Integer.parseInt(Settings.getLastAddedVHLmatch()) + 1;
                int k = 0;
                boolean flagNoMatch = false;
                while (k<1 && !flagNoMatch){
                    String reportUrl = "http://www.vhlru.ru/report/1051/?idgame=" + String.valueOf(index);
                    Document doc1 = null;
                    Match match;
                    boolean fileNotFoundFlag = true;
                    try {
                        String fromBuffer = "";
                        try {
                            fromBuffer = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                        } catch (UnsupportedFlavorException | IOException e1) {
                            e1.printStackTrace();
                            textArea.setText(e1.toString());
                        }
                        match = new Match(fromBuffer, "VHL", reportUrl);
                        if ((match.homeTeam + match.awayTeam).equals("")){
                            index++;
                            Settings.setLastAddedVHLmatch(String.valueOf(index));
                            throw new IOException();
                        }
                        final String curSeason = Settings.getCurrentSeason("VHL");
                        String matchTitle = Team.getShortName(match.homeTeam) + Team.getShortName(match.awayTeam) + match.date;
                        Team t1 = Team.getTeamFromFileWithSeason(match.homeTeam, curSeason);
                        for (int i = 0; i < t1.matchList.size(); i++) {
                            if (t1.matchList.get(i).equals(matchTitle))
                                fileNotFoundFlag = false;
                        }
                        if (fileNotFoundFlag) {
                            match.pushMatchToFile(true);
                            Team homeTeam = Team.getTeamFromFileWithSeason(match.homeTeam, curSeason);
                            homeTeam.addMatch(match);
                            Team awayTeam = Team.getTeamFromFileWithSeason(match.awayTeam, curSeason);
                            awayTeam.addMatch(match);

                            //String path1 = "database/" + curSeason + "/" + Team.getLeague(match.homeTeam) + "/Teams/" + match.homeTeam + ".xml";
                            //String path2 = "database/" + curSeason + "/" + Team.getLeague(match.awayTeam) + "/Teams/" + match.awayTeam + ".xml";
                            //String path3 = "database/" + curSeason + "/" + Team.getLeague(match.homeTeam) + "/Matches/" + matchTitle + ".xml";

                            //LogWriter.writeUpdates(path1);
                            //LogWriter.writeUpdates(path2);
                            //LogWriter.writeUpdates(path3);
                            resultOfAddingVHLgame.setText(resultOfAddingVHLgame.getText()
                                    + match.homeTeam + " vs " + match.awayTeam + " (" + match.date + ") успешно добавлен." + "\n") ;
                            index++;
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingVHLgame.setFont(fontForText);
                            LogWriter.writeLog("Added match statistics - " + match.homeTeam + " vs " + match.awayTeam + "   (" + matchTitle + ");");
                            Settings.setLastAddedVHLmatch(String.valueOf(index));
                            resultOfAddingVHLgame.setForeground(Color.black);
                        } else {
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingVHLgame.setFont(fontForText);
                            resultOfAddingVHLgame.setText(resultOfAddingVHLgame.getText()
                                    + "Этот матч уже был добавлен в базу ранее!" + "\n");
                            index++;
                            Settings.setLastAddedVHLmatch(String.valueOf(index));
                        }

                    } catch (IOException e1) {
                        if (doc1 == null){
                            flagNoMatch = true;
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingVHLgame.setFont(fontForText);
                            resultOfAddingVHLgame.setText(resultOfAddingVHLgame.getText()
                                    + "Необработанные матчи закончились!" + "\n");
                            resultOfAddingVHLgame.setForeground(new Color(13, 161, 13));
                        } else {
                            e1.printStackTrace();
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingVHLgame.setFont(fontForText);
                            resultOfAddingVHLgame.setText(resultOfAddingVHLgame.getText()
                                    + "Данные НЕ УДАЛОСЬ обработать!" + "\n");
                            resultOfAddingVHLgame.setForeground(Color.red);
                        }
                    }
                    k++;
                }
                index --;
                Settings.setLastAddedVHLmatch(String.valueOf(index));



            }
        });

        /*buttonAddMatchesVHL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultOfAddingVHLgame.setText("");
                int index = Integer.parseInt(Settings.getLastAddedVHLmatch()) + 1;
                int k = 0;
                boolean flagNoMatch = false;
                while (k<1 && !flagNoMatch){
                    String reportUrl = "http://www.vhlru.ru/report/1051/?idgame=" + String.valueOf(index);
                    Document doc1 = null;
                    Document doc2 = null;
                    Match match;
                    boolean fileNotFoundFlag = true;
                    try {
//                        System.setProperty("http.protocols", "TLSv1,TLSv1.1,TLSv1.2");
//                        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
                        System.setProperty("javax.net.ssl.trustStore", "C:/.keystore");
                        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
                        System.setProperty("javax.net.ssl.trustStore", "secure.ts");
                        System.setProperty("javax.net.ssl.trustStorePassword", "S3cuR3pas$!");
                        doc1 = Jsoup.connect(reportUrl).get();
                        match = new Match(doc1, doc2, "VHL", reportUrl);
                        if ((match.homeTeam + match.awayTeam).equals("")){
                            index++;
                            Settings.setLastAddedVHLmatch(String.valueOf(index));
                            throw new IOException();
                        }
                        final String curSeason = Settings.getCurrentSeason("VHL");
                        String matchTitle = Team.getShortName(match.homeTeam) + Team.getShortName(match.awayTeam) + match.date;
                        Team t1 = Team.getTeamFromFileWithSeason(match.homeTeam, curSeason);
                        for (int i = 0; i < t1.matchList.size(); i++) {
                            if (t1.matchList.get(i).equals(matchTitle))
                                fileNotFoundFlag = false;
                        }
                        if (fileNotFoundFlag) {
                            match.pushMatchToFile(true);
                            Team homeTeam = Team.getTeamFromFileWithSeason(match.homeTeam, curSeason);
                            homeTeam.addMatch(match);
                            Team awayTeam = Team.getTeamFromFileWithSeason(match.awayTeam, curSeason);
                            awayTeam.addMatch(match);

                            //String path1 = "database/" + curSeason + "/" + Team.getLeague(match.homeTeam) + "/Teams/" + match.homeTeam + ".xml";
                            //String path2 = "database/" + curSeason + "/" + Team.getLeague(match.awayTeam) + "/Teams/" + match.awayTeam + ".xml";
                            //String path3 = "database/" + curSeason + "/" + Team.getLeague(match.homeTeam) + "/Matches/" + matchTitle + ".xml";

                            //LogWriter.writeUpdates(path1);
                            //LogWriter.writeUpdates(path2);
                            //LogWriter.writeUpdates(path3);
                            resultOfAddingVHLgame.setText(resultOfAddingVHLgame.getText()
                                    + match.homeTeam + " vs " + match.awayTeam + " (" + match.date + ") успешно добавлен." + "\n") ;
                            index++;
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingVHLgame.setFont(fontForText);
                            LogWriter.writeLog("Added match statistics - " + match.homeTeam + " vs " + match.awayTeam + "   (" + matchTitle + ");");
                            Settings.setLastAddedVHLmatch(String.valueOf(index));
                            resultOfAddingVHLgame.setForeground(Color.black);
                        } else {
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingVHLgame.setFont(fontForText);
                            resultOfAddingVHLgame.setText(resultOfAddingVHLgame.getText()
                                    + "Этот матч уже был добавлен в базу ранее!" + "\n");
                            index++;
                            Settings.setLastAddedVHLmatch(String.valueOf(index));
                        }

                    } catch (IOException e1) {
                        if (doc1 == null){
                            flagNoMatch = true;
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingVHLgame.setFont(fontForText);
                            resultOfAddingVHLgame.setText(resultOfAddingVHLgame.getText()
                                    + "Необработанные матчи закончились!" + "\n");
                            resultOfAddingVHLgame.setForeground(new Color(13, 161, 13));
                        } else {
                            e1.printStackTrace();
                            Font fontForText = new Font("", 0, 15);
                            resultOfAddingVHLgame.setFont(fontForText);
                            resultOfAddingVHLgame.setText(resultOfAddingVHLgame.getText()
                                    + "Данные НЕ УДАЛОСЬ обработать!" + "\n");
                            resultOfAddingVHLgame.setForeground(Color.red);
                        }
                    }
                    k++;
                }
                index --;
                Settings.setLastAddedVHLmatch(String.valueOf(index));



            }
        });*/


    }
}