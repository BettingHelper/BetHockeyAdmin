package sample;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// определяем корневой элемент
@XmlRootElement(name = "League")
// определяем последовательность тегов в XML
@XmlType(propOrder = { "leagueName", "season", "fileName", "matchesPlayed", "matchesToAddingInStat",
        "goalsScored", "goalsScored1per", "goalsScored2per", "goalsScored3per", "goalsScoredOT",
        "homeGoals", "awayGoals",
        "homeGoals1per", "awayGoals1per", "homeGoals2per", "awayGoals2per", "homeGoals3per", "awayGoals3per",
        "homeShots", "awayShots", "homeShotsOnTarget", "awayShotsOnTarget",
        "homeShotsOnTarget1per", "awayShotsOnTarget1per", "homeShotsOnTarget2per", "awayShotsOnTarget2per", "homeShotsOnTarget3per", "awayShotsOnTarget3per",
        "homeBlockedShots", "awayBlockedShots",
        "homeMissedShots", "awayMissedShots",
        "homePowerPlays", "awayPowerPlays",
        "homeGoalsInPP", "awayGoalsInPP",
        "homeFaceoffsWon", "awayFaceoffsWon",
        "homeTimeInAttack", "awayTimeInAttack",
        "homeHits", "awayHits",
        "homePenMinutes", "awayPenMinutes",
        "home2MinPenalties", "away2MinPenalties",
        "g_homeWin_draw_awayWin", "g_homeWin_draw_awayWinOT", "g_homeWin_draw_awayWin_1per", "g_homeWin_draw_awayWin_2per", "g_homeWin_draw_awayWin_3per",
        "g_OZ15", "g_totals", "g_totals_1per", "g_totals_2per", "g_totals_3per", "g_goalsInAllPeriods",
        "tournamentTable", "overallStatsTable", "homeStatsTable", "awayStatsTable"
})

public class League {
    public String leagueName;
    public String season;
    public String fileName;
    public int matchesPlayed;
    public ArrayList<String> matchesToAddingInStat;
    public int goalsScored;
    public int goalsScoredOT;
    public int goalsScored1per;
    public int goalsScored2per;
    public int goalsScored3per;
    public int homeGoals;
    public int awayGoals;
    public int homeGoals1per;
    public int awayGoals1per;
    public int homeGoals2per;
    public int awayGoals2per;
    public int homeGoals3per;
    public int awayGoals3per;
    public int homeShots;
    public int awayShots;
    public int homeShotsOnTarget;
    public int awayShotsOnTarget;
    public int homeShotsOnTarget1per;
    public int awayShotsOnTarget1per;
    public int homeShotsOnTarget2per;
    public int awayShotsOnTarget2per;
    public int homeShotsOnTarget3per;
    public int awayShotsOnTarget3per;
    public int homeBlockedShots;
    public int awayBlockedShots;
    public int homeMissedShots;
    public int awayMissedShots;
    public int homePowerPlays;
    public int awayPowerPlays;
    public int homeGoalsInPP;
    public int awayGoalsInPP;
    public int homeFaceoffsWon;
    public int awayFaceoffsWon;
    public int homeTimeInAttack;
    public int awayTimeInAttack;
    public int homeHits;
    public int awayHits;
    public int homePenMinutes;
    public int awayPenMinutes;
    public int home2MinPenalties;
    public int away2MinPenalties;

    public String g_homeWin_draw_awayWin;
    public String g_homeWin_draw_awayWinOT;
    public String g_homeWin_draw_awayWin_1per;
    public String g_homeWin_draw_awayWin_2per;
    public String g_homeWin_draw_awayWin_3per;

    public int g_OZ15;
    public String g_totals;
    public String g_totals_1per;
    public String g_totals_2per;
    public String g_totals_3per;
    public int g_goalsInAllPeriods;

    public ArrayList<String> tournamentTable;
    public ArrayList<String> overallStatsTable;
    public ArrayList<String> homeStatsTable;
    public ArrayList<String> awayStatsTable;

    public League(){

    }

    public League(String leagueName, String season) {
        this.leagueName = leagueName;
        this.season = season;
        this.fileName = leagueName + "_" + season + ".xml";

        g_homeWin_draw_awayWin = "0*0*0";
        g_homeWin_draw_awayWin_1per = "0*0*0";
        g_homeWin_draw_awayWin_2per = "0*0*0";
        g_homeWin_draw_awayWin_3per = "0*0*0";
        g_homeWin_draw_awayWinOT = "0*0*0";

        g_totals = "0*0*0*0*0*0*0*0*0*0*0";
        g_totals_1per = "0*0*0*0*0*0*0";
        g_totals_2per = "0*0*0*0*0*0*0";
        g_totals_3per = "0*0*0*0*0*0*0";

    }

    public static void setStatsTables(String leagueName, String season){
        String nameOfFile = Settings.getPathToDatabase().replace("database/", "leaguesInfo/") + leagueName + "_" + season + ".xml";
        String path = Settings.getPathToDatabase() + season + "/" + leagueName;

        League league = null;

        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(League.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            league =  (League) un.unmarshal(new File(nameOfFile));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        ArrayList<String> tt = new ArrayList<>();
        String[] teamsList = new File(path + "/Teams").list();
        for (String s:teamsList){
            tt.add(s + "*0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0" +
                    "*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0");
        }
        league.overallStatsTable = (ArrayList<String>) tt.clone();
        league.homeStatsTable = (ArrayList<String>) tt.clone();
        league.awayStatsTable = (ArrayList<String>) tt.clone();

        tt.clear();

        String[] matchesList = new File(path + "/Matches").list();
        for (int i=0; i<matchesList.length; i++){
            Match match = Match.getMatchFromFileByName(path + "/Matches/" + matchesList[i]);
            System.out.println(i+1 + ") Матч: " + matchesList[i]);
            league.rewriteStatsTables(match);
        }
    }

    public static void setTournamentTable(String leagueName, String season){
        String nameOfFile = "leaguesInfo/" + leagueName + "_" + season + ".xml";
        String path = "database/" + season + "/" + leagueName;

        League league = null;

        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(League.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            league =  (League) un.unmarshal(new File(nameOfFile));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        ArrayList<String> tt = new ArrayList<>();
        String[] teamsList = new File(path + "/Teams").list();
        for (String s:teamsList){
            tt.add(s + "*0*0*0*0*0");
        }
        league.tournamentTable = tt;

        String[] matchesList = new File(path + "/Matches").list();
        for (int i=0; i<matchesList.length; i++){
            Match match = Match.getMatchFromFileByName(path + "/Matches/" + matchesList[i]);
            System.out.println(i+1 + ") Матч: " + matchesList[i]);
            league.rewriteTournamentTable(match);
        }
    }

    public void rewriteTournamentTable(Match match){
        int htIndex = 0;
        int atIndex = 0;

        for (int i=0; i<tournamentTable.size(); i++){
            if (tournamentTable.get(i).startsWith(match.homeTeam))
                htIndex = i;
            if (tournamentTable.get(i).startsWith(match.awayTeam))
                atIndex = i;
        }

        int htPoints;
        int atPoints;

        if (match.league.equals("NHL")){
            if (match.homeScore > match.awayScore){
                htPoints = 2;
                atPoints = 0;
            } else {
                if (match.homeScore < match.awayScore){
                    htPoints = 0;
                    atPoints = 2;
                } else {
                    htPoints = 1;
                    atPoints = 1;
                    if (match.homeScoreOT + match.homeScoreBullits > 0)
                        htPoints++;
                    if (match.awayScoreOT + match.awayScoreBullits  > 0)
                        atPoints++;
                }
            }
        }
        else{
            if (match.homeScore > match.awayScore){
                htPoints = 3;
                atPoints = 0;
            } else {
                if (match.homeScore < match.awayScore){
                    htPoints = 0;
                    atPoints = 3;
                } else {
                    htPoints = 1;
                    atPoints = 1;
                    if (match.homeScoreOT + match.homeScoreBullits > 0)
                        htPoints++;
                    if (match.awayScoreOT + match.awayScoreBullits > 0)
                        atPoints++;
                }
            }
        }

        String newRecordForHomeTeam = match.homeTeam + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(htIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(htIndex).split("\\*")[2]) + match.homeScore + match.homeScoreOT + match.homeScoreBullits) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(htIndex).split("\\*")[3]) + match.awayScore + match.awayScoreOT + match.awayScoreBullits) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(htIndex).split("\\*")[4]) + match.homeScore + match.homeScoreOT +  match.homeScoreBullits - (match.awayScore + match.awayScoreOT + match.awayScoreBullits)) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(htIndex).split("\\*")[5]) + htPoints);

        String newRecordForAwayTeam = match.awayTeam + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(atIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(atIndex).split("\\*")[2]) + match.awayScore + match.awayScoreOT + match.homeScoreBullits) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(atIndex).split("\\*")[3]) + match.homeScore + match.homeScoreOT + match.awayScoreBullits) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(atIndex).split("\\*")[4]) + match.awayScore + match.awayScoreOT + match.homeScoreBullits - (match.homeScore + match.awayScoreOT + match.awayScoreBullits)) + "*"
                + String.valueOf(Integer.parseInt(tournamentTable.get(atIndex).split("\\*")[5]) + atPoints);

        tournamentTable.set(htIndex, newRecordForHomeTeam);
        tournamentTable.set(atIndex, newRecordForAwayTeam);

        Collections.sort(tournamentTable, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (Integer.parseInt(o1.split("\\*")[5]) > Integer.parseInt(o2.split("\\*")[5]))
                    return -1;
                else {
                    if (Integer.parseInt(o1.split("\\*")[5]) == Integer.parseInt(o2.split("\\*")[5]))
                        return 0;
                    else
                        return 1;
                }
            }
        });
        pushLeagueToFile();
    }

    public static void resetLeagueInfo(String leagueName, String season){
        String path = Settings.getPathToDatabase() + season + "/" + leagueName;


        League league = new League(leagueName, season);

        ArrayList<String> tt = new ArrayList<>();
        ArrayList<String> tt2 = new ArrayList<>();
        String[] teamsList = new File(path + "/Teams").list();
        for (String s:teamsList){
            tt.add(s.replaceAll(".xml", "") + "*0*0*0*0*0");
            tt2.add(s.replaceAll(".xml", "") + "*0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0" +
                    "*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0*0_0_0_0");
        }
        league.tournamentTable = tt;
        league.matchesToAddingInStat = new ArrayList<>();
        league.overallStatsTable = (ArrayList<String>) tt2.clone();
        league.homeStatsTable = (ArrayList<String>) tt2.clone();
        league.awayStatsTable = (ArrayList<String>) tt2.clone();

        String[] matchesList = new File(path + "/Matches").list();
        for (int i=0; i<matchesList.length; i++){
            System.out.println(i+1 + ") Матч: " + matchesList[i]);
            league.matchesToAddingInStat.add(matchesList[i].replace(".xml",""));
        }
        league.fillDataFromAddingList();

    }

    public String fillDataFromAddingList(){
        String result = leagueName + ":\n";
        boolean flagError = false;
        if (matchesToAddingInStat == null ){
            result += "Необработанных матчей нет. \n";
            return result;
        }

        while (matchesToAddingInStat.size() > 0 && !flagError) {
            Match match = Match.getMatchFromFileByName(Settings.getPathToDatabase() + season + "/" + leagueName + "/Matches/" + matchesToAddingInStat.get(0) + ".xml");
            if (match != null){
                if (match.league == null || match.title == null){
                    match.league = Team.getLeague(match.homeTeam);
                    match.title = Team.getShortName(match.homeTeam) + Team.getShortName(match.awayTeam) + match.date;
                    match.pushMatchToFile(true);
                }


                matchesPlayed ++;
                goalsScored += match.homeScore + match.awayScore;
                goalsScoredOT += match.homeScoreOT + match.homeScoreBullits + match.awayScoreOT + match.awayScoreBullits;
                goalsScored1per += match.homeScore1stPeriod + match.awayScore1stPeriod;
                goalsScored2per += match.homeScore2ndPeriod + match.awayScore2ndPeriod;
                goalsScored3per += match.homeScore3rdPeriod + match.awayScore3rdPeriod;
                homeGoals += match.homeScore;
                awayGoals += match.awayScore;
                homeGoals1per += match.homeScore1stPeriod;
                homeGoals2per += match.homeScore2ndPeriod;
                homeGoals3per += match.homeScore3rdPeriod;
                awayGoals1per += match.awayScore1stPeriod;
                awayGoals2per += match.awayScore2ndPeriod;
                awayGoals3per += match.awayScore3rdPeriod;

                homeShots += match.homeShotsOnTarget + match.homeMissedShots;
                awayShots += match.awayShotsOnTarget + match.awayMissedShots;
                homeShotsOnTarget += match.homeShotsOnTarget;
                homeShotsOnTarget1per += match.homeShotsOnTarget1stPeriod;
                homeShotsOnTarget2per += match.homeShotsOnTarget2ndPeriod;
                homeShotsOnTarget3per += match.homeShotsOnTarget3rdPeriod;
                awayShotsOnTarget += match.awayShotsOnTarget;
                awayShotsOnTarget1per += match.awayShotsOnTarget1stPeriod;
                awayShotsOnTarget2per += match.awayShotsOnTarget2ndPeriod;
                awayShotsOnTarget3per += match.awayShotsOnTarget3rdPeriod;
                homeMissedShots += match.homeMissedShots;
                awayMissedShots += match.awayMissedShots;
                homeBlockedShots += match.homeBlockedShots;
                awayBlockedShots += match.awayBlockedShots;
                homePowerPlays += match.homeNumberOfPP;
                awayPowerPlays += match.awayNumberOfPP;
                homeGoalsInPP += match.homeGoalsInPP;
                awayGoalsInPP += match.awayGoalsInPP;
                homeFaceoffsWon += match.homeFaceoffsWon;
                awayFaceoffsWon += match.awayFaceoffsWon;
                homeTimeInAttack += match.homeTimeInAttack;
                awayTimeInAttack += match.awayTimeInAttack;
                homeHits += match.homeHits;
                awayHits += match.awayHits;
                homePenMinutes += match.homePenaltyMinutes;
                awayPenMinutes += match.awayPenaltyMinutes;
                home2MinPenalties += match.home2MinPenalties;
                away2MinPenalties += match.away2MinPenalties;

                int homeWin = Integer.parseInt(g_homeWin_draw_awayWin.split("\\*")[0]);
                int draw = Integer.parseInt(g_homeWin_draw_awayWin.split("\\*")[1]);
                int awayWin = Integer.parseInt(g_homeWin_draw_awayWin.split("\\*")[2]);

                if (match.homeScore == match.awayScore){
                    draw++;
                } else {
                    if (match.homeScore > match.awayScore)
                        homeWin ++;
                    else
                        awayWin ++;
                }

                g_homeWin_draw_awayWin = String.valueOf(homeWin) + "*" + String.valueOf(draw) + "*" + String.valueOf(awayWin);


                int homeWinOT = Integer.parseInt(g_homeWin_draw_awayWinOT.split("\\*")[0]);
                int awayWinOT = Integer.parseInt(g_homeWin_draw_awayWinOT.split("\\*")[2]);

                if (match.homeScore + match.homeScoreOT + match.homeScoreBullits > match.awayScore + match.awayScoreOT + match.awayScoreBullits)
                    homeWinOT ++;
                else
                    awayWinOT ++;

                g_homeWin_draw_awayWinOT = String.valueOf(homeWinOT) + "*0*" + String.valueOf(awayWinOT);

                int homeWin1per = Integer.parseInt(g_homeWin_draw_awayWin_1per.split("\\*")[0]);
                int draw1per = Integer.parseInt(g_homeWin_draw_awayWin_1per.split("\\*")[1]);
                int awayWin1per = Integer.parseInt(g_homeWin_draw_awayWin_1per.split("\\*")[2]);

                if (match.homeScore1stPeriod == match.awayScore1stPeriod){
                    draw1per++;
                } else {
                    if (match.homeScore1stPeriod > match.awayScore1stPeriod)
                        homeWin1per ++;
                    else
                        awayWin1per ++;
                }

                g_homeWin_draw_awayWin_1per = String.valueOf(homeWin1per) + "*" + String.valueOf(draw1per) + "*" + String.valueOf(awayWin1per);

                int homeWin2per = Integer.parseInt(g_homeWin_draw_awayWin_2per.split("\\*")[0]);
                int draw2per = Integer.parseInt(g_homeWin_draw_awayWin_2per.split("\\*")[1]);
                int awayWin2per = Integer.parseInt(g_homeWin_draw_awayWin_2per.split("\\*")[2]);

                if (match.homeScore2ndPeriod == match.awayScore2ndPeriod){
                    draw2per++;
                } else {
                    if (match.homeScore2ndPeriod > match.awayScore2ndPeriod)
                        homeWin2per ++;
                    else
                        awayWin2per ++;
                }

                g_homeWin_draw_awayWin_2per = String.valueOf(homeWin2per) + "*" + String.valueOf(draw2per) + "*" + String.valueOf(awayWin2per);

                int homeWin3per = Integer.parseInt(g_homeWin_draw_awayWin_3per.split("\\*")[0]);
                int draw3per = Integer.parseInt(g_homeWin_draw_awayWin_3per.split("\\*")[1]);
                int awayWin3per = Integer.parseInt(g_homeWin_draw_awayWin_3per.split("\\*")[2]);

                if (match.homeScore3rdPeriod == match.awayScore3rdPeriod){
                    draw3per++;
                } else {
                    if (match.homeScore3rdPeriod > match.awayScore3rdPeriod)
                        homeWin3per ++;
                    else
                        awayWin3per ++;
                }

                g_homeWin_draw_awayWin_3per = String.valueOf(homeWin3per) + "*" + String.valueOf(draw3per) + "*" + String.valueOf(awayWin3per);

                if (match.homeScore > 1.5 && match.awayScore > 1.5)
                    g_OZ15 ++;


                String[] totals = g_totals.split("\\*");
                if (match.homeScore + match.awayScore < 10)
                    totals[match.homeScore + match.awayScore] = String.valueOf(Integer.parseInt(totals[match.homeScore + match.awayScore]) + 1);
                else
                    totals[10] = String.valueOf(Integer.parseInt(totals[10]) + 1);

                g_totals = String.join("*", totals);

                totals = g_totals_1per.split("\\*");
                if (match.homeScore1stPeriod + match.awayScore1stPeriod < 6)
                    totals[match.homeScore1stPeriod + match.awayScore1stPeriod] = String.valueOf(Integer.parseInt(totals[match.homeScore1stPeriod + match.awayScore1stPeriod]) + 1);
                else
                    totals[6] = String.valueOf(Integer.parseInt(totals[6]) + 1);

                g_totals_1per = String.join("*", totals);

                totals = g_totals_2per.split("\\*");
                if (match.homeScore2ndPeriod + match.awayScore2ndPeriod < 6)
                    totals[match.homeScore2ndPeriod + match.awayScore2ndPeriod] = String.valueOf(Integer.parseInt(totals[match.homeScore2ndPeriod + match.awayScore2ndPeriod]) + 1);
                else
                    totals[6] = String.valueOf(Integer.parseInt(totals[6]) + 1);

                g_totals_2per = String.join("*", totals);

                totals = g_totals_3per.split("\\*");
                if (match.homeScore3rdPeriod + match.awayScore3rdPeriod < 6)
                    totals[match.homeScore3rdPeriod + match.awayScore3rdPeriod] = String.valueOf(Integer.parseInt(totals[match.homeScore3rdPeriod + match.awayScore3rdPeriod]) + 1);
                else
                    totals[6] = String.valueOf(Integer.parseInt(totals[6]) + 1);

                g_totals_3per = String.join("*", totals);

                if ( (match.homeScore1stPeriod+match.awayScore1stPeriod)*(match.homeScore2ndPeriod+match.awayScore2ndPeriod)*(match.homeScore3rdPeriod+match.awayScore3rdPeriod) > 0 )
                    g_goalsInAllPeriods ++;

                matchesToAddingInStat.remove(0);
                rewriteTournamentTable(match);
                rewriteStatsTables(match);
                System.out.println(matchesPlayed + ") Матч " + match.title + " добавлен успешно!");
                result += "Матч " + match.title + " добавлен успешно!\n";
            } else {
                flagError = true;
            }
        }
        pushLeagueToFile();
        return result;
    }


    public void pushLeagueToFile(){
        String path = Settings.getPathToDatabase().replace("database", "leaguesInfo");
        String fileName = path + "/" + this.fileName;
        try {
            JAXBContext context = JAXBContext.newInstance(League.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
            try {
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/hockey/leaguesInfo/" + this.fileName , fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void rewriteStatsTables(Match match){
        int htIndex = 0;
        int atIndex = 0;

        for (int i=0; i<overallStatsTable.size(); i++){
            if (overallStatsTable.get(i).startsWith(match.homeTeam))
                htIndex = i;
            if (overallStatsTable.get(i).startsWith(match.awayTeam))
                atIndex = i;
        }

        //        "Голы", "Голы с учетом ОТиБ", "Голы 1 пер", "Голы 2 пер", "Голы 3 пер",
        //        "Броски в створ", "Броски в створ 1 пер", "Броски в створ 2 пер", "Броски в створ 3 пер",
        //        "Реализация большинства, %", "Вбрасывания", "Время в атаке", "Блок.броски", "Сил приемы",
        //        "Минуты штрафа", "2мин удаления", "Бр. мимо"

        // свои _ чужие _ разница _ тотал
        double realHT_Overall = Team.roundResult(100 * (Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[19].split("_")[0]) +  match.homeGoalsInPP) / (double) (Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[20].split("_")[0]) +  match.homeNumberOfPP) , 2);
        double realHT_Overall_Opp = Team.roundResult(100 * (Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[19].split("_")[1]) +  match.awayGoalsInPP) / (double) (Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[20].split("_")[1]) +  match.awayNumberOfPP) , 2);
        double realAT_Overall = Team.roundResult(100 * (Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[19].split("_")[0]) +  match.awayGoalsInPP) / (double) (Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[20].split("_")[0]) +  match.awayNumberOfPP) , 2);
        double realAT_Overall_Opp = Team.roundResult(100 * (Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[19].split("_")[1]) +  match.homeGoalsInPP) / (double) (Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[20].split("_")[1]) +  match.homeNumberOfPP) , 2);

        double realHT = Team.roundResult(100 * (Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[19].split("_")[0]) +  match.homeGoalsInPP) / (double) (Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[20].split("_")[0]) +  match.homeNumberOfPP) , 2);
        double realHT_Opp = Team.roundResult(100 * (Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[19].split("_")[1]) +  match.awayGoalsInPP) / (double) (Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[20].split("_")[1]) +  match.awayNumberOfPP) , 2);
        double realAT = Team.roundResult(100 * (Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[19].split("_")[0]) +  match.homeGoalsInPP) / (double) (Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[20].split("_")[0]) +  match.homeNumberOfPP) , 2);
        double realAT_Opp = Team.roundResult(100 * (Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[19].split("_")[1]) +  match.awayGoalsInPP) / (double) (Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[20].split("_")[1]) +  match.awayNumberOfPP) , 2);

        String newRecordForHomeTeam = match.homeTeam + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[2].split("_")[0]) +  match.homeScore) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[2].split("_")[1]) +  match.awayScore) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[2].split("_")[2]) + (match.homeScore - match.awayScore)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[2].split("_")[3]) + (match.homeScore + match.awayScore)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[3].split("_")[0]) +  match.homeScore + match.homeScoreOT + match.homeScoreBullits) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[3].split("_")[1]) +  match.awayScore + match.awayScoreOT + match.awayScoreBullits) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[3].split("_")[2]) + (match.homeScore + match.homeScoreOT + match.homeScoreBullits - (match.awayScore + match.awayScoreOT + match.awayScoreBullits))) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[3].split("_")[3]) + (match.homeScore + match.awayScore + match.awayScoreOT + match.awayScoreOT + match.homeScoreBullits + match.awayScoreBullits)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[4].split("_")[0]) +  match.homeScore1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[4].split("_")[1]) +  match.awayScore1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[4].split("_")[2]) + (match.homeScore1stPeriod - match.awayScore1stPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[4].split("_")[3]) + (match.homeScore1stPeriod + match.awayScore1stPeriod)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[5].split("_")[0]) +  match.homeScore2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[5].split("_")[1]) +  match.awayScore2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[5].split("_")[2]) + (match.homeScore2ndPeriod - match.awayScore2ndPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[5].split("_")[3]) + (match.homeScore2ndPeriod + match.awayScore2ndPeriod)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[6].split("_")[0]) +  match.homeScore3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[6].split("_")[1]) +  match.awayScore3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[6].split("_")[2]) + (match.homeScore3rdPeriod - match.awayScore3rdPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[6].split("_")[3]) + (match.homeScore3rdPeriod + match.awayScore3rdPeriod)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[7].split("_")[0]) +  match.homeShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[7].split("_")[1]) +  match.awayShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[7].split("_")[2]) + (match.homeShotsOnTarget - match.awayShotsOnTarget)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[7].split("_")[3]) + (match.homeShotsOnTarget + match.awayShotsOnTarget)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[8].split("_")[0]) +  match.homeShotsOnTarget1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[8].split("_")[1]) +  match.awayShotsOnTarget1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[8].split("_")[2]) + (match.homeShotsOnTarget1stPeriod - match.awayShotsOnTarget1stPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[8].split("_")[3]) + (match.homeShotsOnTarget1stPeriod + match.awayShotsOnTarget1stPeriod)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[9].split("_")[0]) +  match.homeShotsOnTarget2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[9].split("_")[1]) +  match.awayShotsOnTarget2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[9].split("_")[2]) + (match.homeShotsOnTarget2ndPeriod - match.awayShotsOnTarget2ndPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[9].split("_")[3]) + (match.homeShotsOnTarget2ndPeriod + match.awayShotsOnTarget2ndPeriod)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[10].split("_")[0]) +  match.homeShotsOnTarget3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[10].split("_")[1]) +  match.awayShotsOnTarget3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[10].split("_")[2]) + (match.homeShotsOnTarget3rdPeriod - match.awayShotsOnTarget3rdPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[10].split("_")[3]) + (match.homeShotsOnTarget3rdPeriod + match.awayShotsOnTarget3rdPeriod)) + "*"
                + String.valueOf(realHT_Overall) + "_"
                + String.valueOf(realHT_Overall_Opp) + "_"
                + String.valueOf(0) + "_"
                + String.valueOf(0) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[12].split("_")[0]) +  match.homeFaceoffsWon) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[12].split("_")[1]) +  match.awayFaceoffsWon) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[12].split("_")[2]) + (match.homeFaceoffsWon - match.awayFaceoffsWon)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[12].split("_")[3]) + (match.homeFaceoffsWon + match.awayFaceoffsWon)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[13].split("_")[0]) +  match.homeTimeInAttack) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[13].split("_")[1]) +  match.awayTimeInAttack) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[13].split("_")[2]) + (match.homeTimeInAttack - match.awayTimeInAttack)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[13].split("_")[3]) + (match.homeTimeInAttack + match.awayTimeInAttack)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[14].split("_")[0]) +  match.homeBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[14].split("_")[1]) +  match.awayBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[14].split("_")[2]) + (match.homeBlockedShots - match.awayBlockedShots)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[14].split("_")[3]) + (match.homeBlockedShots + match.awayBlockedShots)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[15].split("_")[0]) +  match.homeHits) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[15].split("_")[1]) +  match.awayHits) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[15].split("_")[2]) + (match.homeHits - match.awayHits)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[15].split("_")[3]) + (match.homeHits + match.awayHits)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[16].split("_")[0]) +  match.homePenaltyMinutes) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[16].split("_")[1]) +  match.awayPenaltyMinutes) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[16].split("_")[2]) + (match.homePenaltyMinutes - match.awayPenaltyMinutes)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[16].split("_")[3]) + (match.homePenaltyMinutes + match.awayPenaltyMinutes)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[17].split("_")[0]) +  match.home2MinPenalties) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[17].split("_")[1]) +  match.away2MinPenalties) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[17].split("_")[2]) + (match.home2MinPenalties - match.away2MinPenalties)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[17].split("_")[3]) + (match.home2MinPenalties + match.away2MinPenalties)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[18].split("_")[0]) +  match.homeMissedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[18].split("_")[1]) +  match.awayMissedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[18].split("_")[2]) + (match.homeMissedShots - match.awayMissedShots)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[18].split("_")[3]) + (match.homeMissedShots + match.awayMissedShots)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[19].split("_")[0]) +  match.homeGoalsInPP) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[19].split("_")[1]) +  match.awayGoalsInPP) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[19].split("_")[2]) + (match.homeGoalsInPP - match.awayGoalsInPP)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[19].split("_")[3]) + (match.homeGoalsInPP + match.awayGoalsInPP)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[20].split("_")[0]) +  match.homeNumberOfPP) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[20].split("_")[1]) +  match.awayNumberOfPP) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[20].split("_")[2]) + (match.homeNumberOfPP - match.awayNumberOfPP)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(htIndex).split("\\*")[20].split("_")[3]) + (match.homeNumberOfPP + match.awayNumberOfPP));

        String newRecordForAwayTeam = match.awayTeam + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[2].split("_")[0]) +  match.awayScore) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[2].split("_")[1]) +  match.homeScore) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[2].split("_")[2]) + (match.awayScore - match.homeScore)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[2].split("_")[3]) + (match.awayScore + match.homeScore)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[3].split("_")[0]) +  match.awayScore + match.awayScoreOT + match.awayScoreBullits) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[3].split("_")[1]) +  match.homeScore + match.homeScoreOT + match.homeScoreBullits) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[3].split("_")[2]) + (match.awayScore + match.awayScoreOT + match.awayScoreBullits - (match.homeScore + match.homeScoreOT + match.homeScoreBullits))) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[3].split("_")[3]) + (match.awayScore + match.homeScore + match.homeScoreOT + match.homeScoreOT + match.awayScoreBullits + match.homeScoreBullits)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[4].split("_")[0]) +  match.awayScore1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[4].split("_")[1]) +  match.homeScore1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[4].split("_")[2]) + (match.awayScore1stPeriod - match.homeScore1stPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[4].split("_")[3]) + (match.awayScore1stPeriod + match.homeScore1stPeriod)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[5].split("_")[0]) +  match.awayScore2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[5].split("_")[1]) +  match.homeScore2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[5].split("_")[2]) + (match.awayScore2ndPeriod - match.homeScore2ndPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[5].split("_")[3]) + (match.awayScore2ndPeriod + match.homeScore2ndPeriod)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[6].split("_")[0]) +  match.awayScore3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[6].split("_")[1]) +  match.homeScore3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[6].split("_")[2]) + (match.awayScore3rdPeriod - match.homeScore3rdPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[6].split("_")[3]) + (match.awayScore3rdPeriod + match.homeScore3rdPeriod)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[7].split("_")[0]) +  match.awayShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[7].split("_")[1]) +  match.homeShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[7].split("_")[2]) + (match.awayShotsOnTarget - match.homeShotsOnTarget)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[7].split("_")[3]) + (match.awayShotsOnTarget + match.homeShotsOnTarget)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[8].split("_")[0]) +  match.awayShotsOnTarget1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[8].split("_")[1]) +  match.homeShotsOnTarget1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[8].split("_")[2]) + (match.awayShotsOnTarget1stPeriod - match.homeShotsOnTarget1stPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[8].split("_")[3]) + (match.awayShotsOnTarget1stPeriod + match.homeShotsOnTarget1stPeriod)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[9].split("_")[0]) +  match.awayShotsOnTarget2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[9].split("_")[1]) +  match.homeShotsOnTarget2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[9].split("_")[2]) + (match.awayShotsOnTarget2ndPeriod - match.homeShotsOnTarget2ndPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[9].split("_")[3]) + (match.awayShotsOnTarget2ndPeriod + match.homeShotsOnTarget2ndPeriod)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[10].split("_")[0]) +  match.awayShotsOnTarget3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[10].split("_")[1]) +  match.homeShotsOnTarget3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[10].split("_")[2]) + (match.awayShotsOnTarget3rdPeriod - match.homeShotsOnTarget3rdPeriod)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[10].split("_")[3]) + (match.awayShotsOnTarget3rdPeriod + match.homeShotsOnTarget3rdPeriod)) + "*"
                + String.valueOf(realAT_Overall) + "_"
                + String.valueOf(realAT_Overall_Opp) + "_"
                + String.valueOf(0) + "_"
                + String.valueOf(0) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[12].split("_")[0]) +  match.awayFaceoffsWon) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[12].split("_")[1]) +  match.homeFaceoffsWon) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[12].split("_")[2]) + (match.awayFaceoffsWon - match.homeFaceoffsWon)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[12].split("_")[3]) + (match.awayFaceoffsWon + match.homeFaceoffsWon)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[13].split("_")[0]) +  match.awayTimeInAttack) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[13].split("_")[1]) +  match.homeTimeInAttack) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[13].split("_")[2]) + (match.awayTimeInAttack - match.homeTimeInAttack)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[13].split("_")[3]) + (match.awayTimeInAttack + match.homeTimeInAttack)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[14].split("_")[0]) +  match.awayBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[14].split("_")[1]) +  match.homeBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[14].split("_")[2]) + (match.awayBlockedShots - match.homeBlockedShots)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[14].split("_")[3]) + (match.awayBlockedShots + match.homeBlockedShots)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[15].split("_")[0]) +  match.awayHits) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[15].split("_")[1]) +  match.homeHits) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[15].split("_")[2]) + (match.awayHits - match.homeHits)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[15].split("_")[3]) + (match.awayHits + match.homeHits)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[16].split("_")[0]) +  match.awayPenaltyMinutes) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[16].split("_")[1]) +  match.homePenaltyMinutes) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[16].split("_")[2]) + (match.awayPenaltyMinutes - match.homePenaltyMinutes)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[16].split("_")[3]) + (match.awayPenaltyMinutes + match.homePenaltyMinutes)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[17].split("_")[0]) +  match.away2MinPenalties) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[17].split("_")[1]) +  match.home2MinPenalties) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[17].split("_")[2]) + (match.away2MinPenalties - match.home2MinPenalties)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[17].split("_")[3]) + (match.away2MinPenalties + match.home2MinPenalties)) + "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[18].split("_")[0]) +  match.awayMissedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[18].split("_")[1]) +  match.homeMissedShots) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[18].split("_")[2]) + (match.awayMissedShots - match.homeMissedShots)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[18].split("_")[3]) + (match.awayMissedShots + match.homeMissedShots))+ "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[19].split("_")[0]) +  match.awayGoalsInPP) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[19].split("_")[1]) +  match.homeGoalsInPP) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[19].split("_")[2]) + (match.awayGoalsInPP - match.homeGoalsInPP)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[19].split("_")[3]) + (match.awayGoalsInPP + match.homeGoalsInPP))+ "*"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[20].split("_")[0]) +  match.awayNumberOfPP) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[20].split("_")[1]) +  match.homeNumberOfPP) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[20].split("_")[2]) + (match.awayNumberOfPP - match.homeNumberOfPP)) + "_"
                + String.valueOf(Integer.parseInt(overallStatsTable.get(atIndex).split("\\*")[20].split("_")[3]) + (match.awayNumberOfPP + match.homeNumberOfPP));

        overallStatsTable.set(htIndex, newRecordForHomeTeam);
        overallStatsTable.set(atIndex, newRecordForAwayTeam);

        String newRecordForHomeTeamInHomeTable = match.homeTeam + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[2].split("_")[0]) +  match.homeScore) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[2].split("_")[1]) +  match.awayScore) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[2].split("_")[2]) + (match.homeScore - match.awayScore)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[2].split("_")[3]) + (match.homeScore + match.awayScore)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[3].split("_")[0]) +  match.homeScore + match.homeScoreOT + match.homeScoreBullits) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[3].split("_")[1]) +  match.awayScore + match.awayScoreOT + match.awayScoreBullits) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[3].split("_")[2]) + (match.homeScore + match.homeScoreOT + match.homeScoreBullits - (match.awayScore + match.awayScoreOT + match.awayScoreBullits))) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[3].split("_")[3]) + (match.homeScore + match.awayScore + match.awayScoreOT + match.awayScoreOT + match.homeScoreBullits + match.awayScoreBullits)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[4].split("_")[0]) +  match.homeScore1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[4].split("_")[1]) +  match.awayScore1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[4].split("_")[2]) + (match.homeScore1stPeriod - match.awayScore1stPeriod)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[4].split("_")[3]) + (match.homeScore1stPeriod + match.awayScore1stPeriod)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[5].split("_")[0]) +  match.homeScore2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[5].split("_")[1]) +  match.awayScore2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[5].split("_")[2]) + (match.homeScore2ndPeriod - match.awayScore2ndPeriod)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[5].split("_")[3]) + (match.homeScore2ndPeriod + match.awayScore2ndPeriod)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[6].split("_")[0]) +  match.homeScore3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[6].split("_")[1]) +  match.awayScore3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[6].split("_")[2]) + (match.homeScore3rdPeriod - match.awayScore3rdPeriod)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[6].split("_")[3]) + (match.homeScore3rdPeriod + match.awayScore3rdPeriod)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[7].split("_")[0]) +  match.homeShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[7].split("_")[1]) +  match.awayShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[7].split("_")[2]) + (match.homeShotsOnTarget - match.awayShotsOnTarget)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[7].split("_")[3]) + (match.homeShotsOnTarget + match.awayShotsOnTarget)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[8].split("_")[0]) +  match.homeShotsOnTarget1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[8].split("_")[1]) +  match.awayShotsOnTarget1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[8].split("_")[2]) + (match.homeShotsOnTarget1stPeriod - match.awayShotsOnTarget1stPeriod)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[8].split("_")[3]) + (match.homeShotsOnTarget1stPeriod + match.awayShotsOnTarget1stPeriod)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[9].split("_")[0]) +  match.homeShotsOnTarget2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[9].split("_")[1]) +  match.awayShotsOnTarget2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[9].split("_")[2]) + (match.homeShotsOnTarget2ndPeriod - match.awayShotsOnTarget2ndPeriod)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[9].split("_")[3]) + (match.homeShotsOnTarget2ndPeriod + match.awayShotsOnTarget2ndPeriod)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[10].split("_")[0]) +  match.homeShotsOnTarget3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[10].split("_")[1]) +  match.awayShotsOnTarget3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[10].split("_")[2]) + (match.homeShotsOnTarget3rdPeriod - match.awayShotsOnTarget3rdPeriod)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[10].split("_")[3]) + (match.homeShotsOnTarget3rdPeriod + match.awayShotsOnTarget3rdPeriod)) + "*"
                + String.valueOf(realHT) + "_"
                + String.valueOf(realHT_Opp) + "_"
                + String.valueOf(0) + "_"
                + String.valueOf(0) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[12].split("_")[0]) +  match.homeFaceoffsWon) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[12].split("_")[1]) +  match.awayFaceoffsWon) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[12].split("_")[2]) + (match.homeFaceoffsWon - match.awayFaceoffsWon)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[12].split("_")[3]) + (match.homeFaceoffsWon + match.awayFaceoffsWon)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[13].split("_")[0]) +  match.homeTimeInAttack) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[13].split("_")[1]) +  match.awayTimeInAttack) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[13].split("_")[2]) + (match.homeTimeInAttack - match.awayTimeInAttack)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[13].split("_")[3]) + (match.homeTimeInAttack + match.awayTimeInAttack)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[14].split("_")[0]) +  match.homeBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[14].split("_")[1]) +  match.awayBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[14].split("_")[2]) + (match.homeBlockedShots - match.awayBlockedShots)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[14].split("_")[3]) + (match.homeBlockedShots + match.awayBlockedShots)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[15].split("_")[0]) +  match.homeHits) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[15].split("_")[1]) +  match.awayHits) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[15].split("_")[2]) + (match.homeHits - match.awayHits)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[15].split("_")[3]) + (match.homeHits + match.awayHits)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[16].split("_")[0]) +  match.homePenaltyMinutes) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[16].split("_")[1]) +  match.awayPenaltyMinutes) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[16].split("_")[2]) + (match.homePenaltyMinutes - match.awayPenaltyMinutes)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[16].split("_")[3]) + (match.homePenaltyMinutes + match.awayPenaltyMinutes)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[17].split("_")[0]) +  match.home2MinPenalties) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[17].split("_")[1]) +  match.away2MinPenalties) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[17].split("_")[2]) + (match.home2MinPenalties - match.away2MinPenalties)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[17].split("_")[3]) + (match.home2MinPenalties + match.away2MinPenalties)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[18].split("_")[0]) +  match.homeMissedShots) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[18].split("_")[1]) +  match.awayMissedShots) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[18].split("_")[2]) + (match.homeMissedShots - match.awayMissedShots)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[18].split("_")[3]) + (match.homeMissedShots + match.awayMissedShots)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[19].split("_")[0]) +  match.homeGoalsInPP) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[19].split("_")[1]) +  match.awayGoalsInPP) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[19].split("_")[2]) + (match.homeGoalsInPP - match.awayGoalsInPP)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[19].split("_")[3]) + (match.homeGoalsInPP + match.awayGoalsInPP)) + "*"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[20].split("_")[0]) +  match.homeNumberOfPP) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[20].split("_")[1]) +  match.awayNumberOfPP) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[20].split("_")[2]) + (match.homeNumberOfPP - match.awayNumberOfPP)) + "_"
                + String.valueOf(Integer.parseInt(homeStatsTable.get(htIndex).split("\\*")[20].split("_")[3]) + (match.homeNumberOfPP + match.awayNumberOfPP));

        String newRecordForAwayTeamInAwayTable = match.awayTeam + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[1]) + 1) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[2].split("_")[0]) +  match.awayScore) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[2].split("_")[1]) +  match.homeScore) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[2].split("_")[2]) + (match.awayScore - match.homeScore)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[2].split("_")[3]) + (match.awayScore + match.homeScore)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[3].split("_")[0]) +  match.awayScore + match.awayScoreOT + match.awayScoreBullits) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[3].split("_")[1]) +  match.homeScore + match.homeScoreOT + match.homeScoreBullits) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[3].split("_")[2]) + (match.awayScore + match.awayScoreOT + match.awayScoreBullits - (match.homeScore + match.homeScoreOT + match.homeScoreBullits))) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[3].split("_")[3]) + (match.awayScore + match.homeScore + match.homeScoreOT + match.homeScoreOT + match.homeScoreBullits + match.awayScoreBullits)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[4].split("_")[0]) +  match.awayScore1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[4].split("_")[1]) +  match.homeScore1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[4].split("_")[2]) + (match.awayScore1stPeriod - match.homeScore1stPeriod)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[4].split("_")[3]) + (match.awayScore1stPeriod + match.homeScore1stPeriod)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[5].split("_")[0]) +  match.awayScore2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[5].split("_")[1]) +  match.homeScore2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[5].split("_")[2]) + (match.awayScore2ndPeriod - match.homeScore2ndPeriod)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[5].split("_")[3]) + (match.awayScore2ndPeriod + match.homeScore2ndPeriod)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[6].split("_")[0]) +  match.awayScore3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[6].split("_")[1]) +  match.homeScore3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[6].split("_")[2]) + (match.awayScore3rdPeriod - match.homeScore3rdPeriod)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[6].split("_")[3]) + (match.awayScore3rdPeriod + match.homeScore3rdPeriod)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[7].split("_")[0]) +  match.awayShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[7].split("_")[1]) +  match.homeShotsOnTarget) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[7].split("_")[2]) + (match.awayShotsOnTarget - match.homeShotsOnTarget)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[7].split("_")[3]) + (match.awayShotsOnTarget + match.homeShotsOnTarget)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[8].split("_")[0]) +  match.awayShotsOnTarget1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[8].split("_")[1]) +  match.homeShotsOnTarget1stPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[8].split("_")[2]) + (match.awayShotsOnTarget1stPeriod - match.homeShotsOnTarget1stPeriod)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[8].split("_")[3]) + (match.awayShotsOnTarget1stPeriod + match.homeShotsOnTarget1stPeriod)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[9].split("_")[0]) +  match.awayShotsOnTarget2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[9].split("_")[1]) +  match.homeShotsOnTarget2ndPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[9].split("_")[2]) + (match.awayShotsOnTarget2ndPeriod - match.homeShotsOnTarget2ndPeriod)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[9].split("_")[3]) + (match.awayShotsOnTarget2ndPeriod + match.homeShotsOnTarget2ndPeriod)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[10].split("_")[0]) +  match.awayShotsOnTarget3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[10].split("_")[1]) +  match.homeShotsOnTarget3rdPeriod) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[10].split("_")[2]) + (match.awayShotsOnTarget3rdPeriod - match.homeShotsOnTarget3rdPeriod)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[10].split("_")[3]) + (match.awayShotsOnTarget3rdPeriod + match.homeShotsOnTarget3rdPeriod)) + "*"
                + String.valueOf(realAT) + "_"
                + String.valueOf(realAT_Opp) + "_"
                + String.valueOf(0) + "_"
                + String.valueOf(0) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[12].split("_")[0]) +  match.awayFaceoffsWon) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[12].split("_")[1]) +  match.homeFaceoffsWon) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[12].split("_")[2]) + (match.awayFaceoffsWon - match.homeFaceoffsWon)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[12].split("_")[3]) + (match.awayFaceoffsWon + match.homeFaceoffsWon)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[13].split("_")[0]) +  match.awayTimeInAttack) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[13].split("_")[1]) +  match.homeTimeInAttack) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[13].split("_")[2]) + (match.awayTimeInAttack - match.homeTimeInAttack)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[13].split("_")[3]) + (match.awayTimeInAttack + match.homeTimeInAttack)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[14].split("_")[0]) +  match.awayBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[14].split("_")[1]) +  match.homeBlockedShots) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[14].split("_")[2]) + (match.awayBlockedShots - match.homeBlockedShots)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[14].split("_")[3]) + (match.awayBlockedShots + match.homeBlockedShots)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[15].split("_")[0]) +  match.awayHits) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[15].split("_")[1]) +  match.homeHits) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[15].split("_")[2]) + (match.awayHits - match.homeHits)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[15].split("_")[3]) + (match.awayHits + match.homeHits)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[16].split("_")[0]) +  match.awayPenaltyMinutes) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[16].split("_")[1]) +  match.homePenaltyMinutes) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[16].split("_")[2]) + (match.awayPenaltyMinutes - match.homePenaltyMinutes)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[16].split("_")[3]) + (match.awayPenaltyMinutes + match.homePenaltyMinutes)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[17].split("_")[0]) +  match.away2MinPenalties) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[17].split("_")[1]) +  match.home2MinPenalties) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[17].split("_")[2]) + (match.away2MinPenalties - match.home2MinPenalties)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[17].split("_")[3]) + (match.away2MinPenalties + match.home2MinPenalties)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[18].split("_")[0]) +  match.awayMissedShots) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[18].split("_")[1]) +  match.homeMissedShots) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[18].split("_")[2]) + (match.awayMissedShots - match.homeMissedShots)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[18].split("_")[3]) + (match.awayMissedShots + match.homeMissedShots)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[19].split("_")[0]) +  match.awayGoalsInPP) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[19].split("_")[1]) +  match.homeGoalsInPP) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[19].split("_")[2]) + (match.awayGoalsInPP - match.homeGoalsInPP)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[19].split("_")[3]) + (match.awayGoalsInPP + match.homeGoalsInPP)) + "*"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[20].split("_")[0]) +  match.awayNumberOfPP) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[20].split("_")[1]) +  match.homeNumberOfPP) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[20].split("_")[2]) + (match.awayNumberOfPP - match.homeNumberOfPP)) + "_"
                + String.valueOf(Integer.parseInt(awayStatsTable.get(atIndex).split("\\*")[20].split("_")[3]) + (match.awayNumberOfPP + match.homeNumberOfPP));

        homeStatsTable.set(htIndex, newRecordForHomeTeamInHomeTable);
        awayStatsTable.set(atIndex, newRecordForAwayTeamInAwayTable);

        pushLeagueToFile();
    }

    public static League getLeagueFromFile(String leagueName, String season){
        String path = Settings.getPathToDatabase().replace("database", "leaguesInfo");
        String fileName = path + "/" + leagueName + "_" + season + ".xml";
        return getLeagueFromFileByName(fileName);
    }

    public static League getLeagueFromFileByName(String fileName){
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(League.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (League) un.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addMatchToList(String leagueName, String season, String matchTitle){
        League league = League.getLeagueFromFile(leagueName, season);
        if (league.matchesToAddingInStat == null)
            league.matchesToAddingInStat = new ArrayList<>();
        league.matchesToAddingInStat.add(matchTitle);
        league.pushLeagueToFile();
    }


}
