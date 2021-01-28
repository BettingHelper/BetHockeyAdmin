package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.util.ArrayList;

// определяем корневой элемент
@XmlRootElement(name = "Team")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"teamName", "matches", "wins", "winsOT", "loses", "losesOT", "goalsScored", "goalsConcedered", "goalsDifference",
        "goalsScoredOTandBullits", "goalsConcederedOTandBullits", "goalsOTandBullitsDifference", "points",
        "shotsOnTarget", "shotsOnTargetOpponent", "numberOfPP", "numberOfPPopponent", "goalsInPP", "goalsInPPopponent",
        "shorthandedGoals", "shorthandedGoalsOpponent", "numberOffFaceoffs", "faceoffsWon", "blockedShots", "blockedShotsOpponent",
        "hits", "hitsOpponent", "penaltyMinutes", "penaltyMinutesOpponent", "numberOf2MinPenalties", "numberOf2MinPenaltiesOpponent",
        "shotsOnTarget1stPeriod", "shotsOnTarget2ndPeriod", "shotsOnTarget3rdPeriod",
        "shotsOnTarget1stPeriodOpp", "shotsOnTarget2ndPeriodOpp", "shotsOnTarget3rdPeriodOpp",
        "penaltyMinutes1stPeriod", "penaltyMinutes2ndPeriod", "penaltyMinutes3rdPeriod",
        "penaltyMinutes1stPeriodOpp", "penaltyMinutes2ndPeriodOpp", "penaltyMinutes3rdPeriodOpp",
        "twoMinPenalties1stPeriod", "twoMinPenalties2ndPeriod", "twoMinPenalties3rdPeriod",
        "twoMinPenalties1stPeriodOpp", "twoMinPenalties2ndPeriodOpp", "twoMinPenalties3rdPeriodOpp",
        "timeInAttack", "timeInAttackOpp", "timeInAttack1stPeriod", "timeInAttack1stPeriodOpp", "timeInAttack2ndPeriod",
        "timeInAttack2ndPeriodOpp", "timeInAttack3rdPeriod", "timeInAttack3rdPeriodOpp", "missedShots", "missedShotsOpponent",
        "matchList"})

public class Team {

    public String teamName;
    //======================= Основные параметры команды
    public int matches;
    public int wins;
    public int winsOT;
    public int loses;
    public int losesOT;
    public int goalsScored;
    public int goalsConcedered;
    public int goalsDifference;
    public int goalsScoredOTandBullits;
    public int goalsConcederedOTandBullits;
    public int goalsOTandBullitsDifference;
    public int points;
    //======================= Статистические параметры команды за весь турнир и за матч (Заполняемые)
    public int shotsOnTarget;
    public int shotsOnTargetOpponent;
    public int numberOfPP;
    public int numberOfPPopponent;
    public int goalsInPP;
    public int goalsInPPopponent;
    public int shorthandedGoals;
    public int shorthandedGoalsOpponent;
    public int numberOffFaceoffs;
    public int faceoffsWon;
    public int blockedShots;
    public int blockedShotsOpponent;
    public int hits;
    public int hitsOpponent;
    public int penaltyMinutes;
    public int penaltyMinutesOpponent;
    public int numberOf2MinPenalties;
    public int numberOf2MinPenaltiesOpponent;
    public int shotsOnTarget1stPeriod;
    public int shotsOnTarget2ndPeriod;
    public int shotsOnTarget3rdPeriod;
    public int shotsOnTarget1stPeriodOpp;
    public int shotsOnTarget2ndPeriodOpp;
    public int shotsOnTarget3rdPeriodOpp;
    public int penaltyMinutes1stPeriod;
    public int penaltyMinutes2ndPeriod;
    public int penaltyMinutes3rdPeriod;
    public int penaltyMinutes1stPeriodOpp;
    public int penaltyMinutes2ndPeriodOpp;
    public int penaltyMinutes3rdPeriodOpp;
    public int twoMinPenalties1stPeriod;
    public int twoMinPenalties2ndPeriod;
    public int twoMinPenalties3rdPeriod;
    public int twoMinPenalties1stPeriodOpp;
    public int twoMinPenalties2ndPeriodOpp;
    public int twoMinPenalties3rdPeriodOpp;
    public int timeInAttack;
    public int timeInAttackOpp;
    public int timeInAttack1stPeriod;
    public int timeInAttack1stPeriodOpp;
    public int timeInAttack2ndPeriod;
    public int timeInAttack2ndPeriodOpp;
    public int timeInAttack3rdPeriod;
    public int timeInAttack3rdPeriodOpp;
    public int missedShots;
    public int missedShotsOpponent;

    //======================= Список матчей
    public ArrayList<String> matchList;

    public Team() {
        this.matchList = new ArrayList<>();
    }

    public Team(String name) {

        this.teamName = name;
        this.matches= 0;
        this.wins = 0;
        this.winsOT = 0;
        this.loses = 0;
        this.losesOT = 0;
        this.goalsScored = 0;
        this.goalsConcedered = 0;
        this.goalsDifference = 0;
        this.goalsScoredOTandBullits = 0;
        this.goalsConcederedOTandBullits = 0;
        this.goalsOTandBullitsDifference = 0;
        this.points = 0;
        this.shotsOnTarget = 0;
        this.shotsOnTargetOpponent = 0;
        this.numberOfPP = 0;
        this.numberOfPPopponent = 0;
        this.goalsInPP = 0;
        this.goalsInPPopponent = 0;
        this.shorthandedGoals = 0;
        this.shorthandedGoalsOpponent = 0;
        this.numberOffFaceoffs = 0;
        this.faceoffsWon = 0;
        this.blockedShots = 0;
        this.blockedShotsOpponent = 0;
        this.hits = 0;
        this.hitsOpponent = 0;
        this.penaltyMinutes = 0;
        this.penaltyMinutesOpponent = 0;
        this.numberOf2MinPenalties = 0;
        this.numberOf2MinPenaltiesOpponent = 0;
        this.shotsOnTarget1stPeriod = 0;
        this.shotsOnTarget2ndPeriod = 0;
        this.shotsOnTarget3rdPeriod = 0;
        this.shotsOnTarget1stPeriodOpp = 0;
        this.shotsOnTarget2ndPeriodOpp = 0;
        this.shotsOnTarget3rdPeriodOpp = 0;
        this.penaltyMinutes1stPeriod = 0;
        this.penaltyMinutes2ndPeriod = 0;
        this.penaltyMinutes3rdPeriod = 0;
        this.penaltyMinutes1stPeriodOpp = 0;
        this.penaltyMinutes2ndPeriodOpp = 0;
        this.penaltyMinutes3rdPeriodOpp = 0;
        this.twoMinPenalties1stPeriod = 0;
        this.twoMinPenalties2ndPeriod = 0;
        this.twoMinPenalties3rdPeriod = 0;
        this.twoMinPenalties1stPeriodOpp = 0;
        this.twoMinPenalties2ndPeriodOpp = 0;
        this.twoMinPenalties3rdPeriodOpp = 0;
        this.timeInAttack = 0;
        this.timeInAttackOpp = 0;
        this.timeInAttack1stPeriod = 0;
        this.timeInAttack1stPeriodOpp = 0;
        this.timeInAttack2ndPeriod = 0;
        this.timeInAttack2ndPeriodOpp = 0;
        this.timeInAttack3rdPeriod = 0;
        this.timeInAttack3rdPeriodOpp = 0;
        this.missedShots = 0;
        this.missedShotsOpponent = 0;

        this.matchList = new ArrayList<>();

    }

    public void addMatch(Match match){
        String matchTitle = Team.getShortName(match.homeTeam) + Team.getShortName(match.awayTeam) + match.date;
        String league = Team.getLeague(match.homeTeam);
        this.matchList.add(matchTitle);
        String type = "home";
        if (this.teamName.equals(match.awayTeam))
            type = "away";

        this.matches ++;


        if (type.equals("home")){
            if (match.homeScore == match.awayScore){
                if ((match.homeScoreOT > match.awayScoreOT)||(match.homeScoreBullits > match.awayScoreBullits))
                    this.winsOT++;
                else
                    this.losesOT++;
            } else
            if (match.homeScore > match.awayScore)
                this.wins ++;
            else this.loses ++;

            this.goalsScored += match.homeScore;
            this.goalsConcedered += match.awayScore;
            this.goalsDifference += match.homeScore - match.awayScore;
            this.goalsScoredOTandBullits += match.homeScoreOT + match.homeScoreBullits;
            this.goalsConcederedOTandBullits += match.awayScoreOT + match.awayScoreBullits;
            this.goalsOTandBullitsDifference = this.goalsScoredOTandBullits - this.goalsConcederedOTandBullits;
            if (league.equals("NHL"))
                this.points = 2*this.wins + 2*this.winsOT + this.losesOT;
            else
                this.points = 3*this.wins + 2*this.winsOT + this.losesOT;
            this.shotsOnTarget += match.homeShotsOnTarget;
            this.shotsOnTargetOpponent += match.awayShotsOnTarget;
            this.numberOfPP += match.homeNumberOfPP;
            this.numberOfPPopponent += match.awayNumberOfPP;
            this.goalsInPP += match.homeGoalsInPP;
            this.goalsInPPopponent += match.awayGoalsInPP;
            this.shorthandedGoals += match.homeShorthandedGoals;
            this.shorthandedGoalsOpponent += match.awayShorthandedGoals;
            this.faceoffsWon += match.homeFaceoffsWon;
            this.numberOffFaceoffs += match.homeFaceoffsWon + match.awayFaceoffsWon;
            this.blockedShots += match.homeBlockedShots;
            this.blockedShotsOpponent += match.awayBlockedShots;
            this.hits += match.homeHits;
            this.hitsOpponent += match.awayHits;
            this.penaltyMinutes += match.homePenaltyMinutes;
            this.penaltyMinutesOpponent += match.awayPenaltyMinutes;
            this.numberOf2MinPenalties += match.home2MinPenalties;
            this.numberOf2MinPenaltiesOpponent += match.away2MinPenalties;

            this.shotsOnTarget1stPeriod += match.homeShotsOnTarget1stPeriod;
            this.shotsOnTarget2ndPeriod += match.homeShotsOnTarget2ndPeriod;
            this.shotsOnTarget3rdPeriod += match.homeShotsOnTarget3rdPeriod;
            this.shotsOnTarget1stPeriodOpp += match.awayShotsOnTarget1stPeriod;
            this.shotsOnTarget2ndPeriodOpp += match.awayShotsOnTarget2ndPeriod;
            this.shotsOnTarget3rdPeriodOpp += match.awayShotsOnTarget3rdPeriod;
            this.penaltyMinutes1stPeriod += match.homePenaltyMinutes1stPeriod;
            this.penaltyMinutes2ndPeriod += match.homePenaltyMinutes2ndPeriod;
            this.penaltyMinutes3rdPeriod += match.homePenaltyMinutes3rdPeriod;
            this.penaltyMinutes1stPeriodOpp += match.awayPenaltyMinutes1stPeriod;
            this.penaltyMinutes2ndPeriodOpp += match.awayPenaltyMinutes2ndPeriod;
            this.penaltyMinutes3rdPeriodOpp += match.awayPenaltyMinutes3rdPeriod;
            this.twoMinPenalties1stPeriod += match.home2MinPenalties1stPeriod;
            this.twoMinPenalties2ndPeriod += match.home2MinPenalties2ndPeriod;
            this.twoMinPenalties3rdPeriod += match.home2MinPenalties3rdPeriod;
            this.twoMinPenalties1stPeriodOpp += match.away2MinPenalties1stPeriod;
            this.twoMinPenalties2ndPeriodOpp += match.away2MinPenalties2ndPeriod;
            this.twoMinPenalties3rdPeriodOpp += match.away2MinPenalties3rdPeriod;
            this.timeInAttack += match.homeTimeInAttack;
            this.timeInAttackOpp += match.awayTimeInAttack;
            this.timeInAttack1stPeriod  += match.homeTimeInAttack1stPeriod;
            this.timeInAttack1stPeriodOpp  += match.awayTimeInAttack1stPeriod;
            this.timeInAttack2ndPeriod  += match.homeTimeInAttack2ndPeriod;
            this.timeInAttack2ndPeriodOpp  += match.awayTimeInAttack2ndPeriod;
            this.timeInAttack3rdPeriod  += match.homeTimeInAttack3rdPeriod;
            this.timeInAttack3rdPeriodOpp  += match.awayTimeInAttack3rdPeriod;

            this.missedShots += match.homeMissedShots;
            this.missedShotsOpponent += match.awayMissedShots;

        } else {
            if (match.homeScore == match.awayScore){
                if ((match.homeScoreOT > match.awayScoreOT)||(match.homeScoreBullits > match.awayScoreBullits))
                    this.losesOT++;
                else
                    this.winsOT++;
            } else
            if (match.homeScore > match.awayScore)
                this.loses ++;
            else this.wins ++;

            this.goalsScored += match.awayScore;
            this.goalsConcedered += match.homeScore;
            this.goalsDifference += match.awayScore - match.homeScore;
            this.goalsScoredOTandBullits += match.awayScoreOT + match.awayScoreBullits;
            this.goalsConcederedOTandBullits += match.homeScoreOT + match.homeScoreBullits;
            this.goalsOTandBullitsDifference = this.goalsScoredOTandBullits - this.goalsConcederedOTandBullits;
            if (league.equals("NHL"))
                this.points = 2*this.wins + 2*this.winsOT + this.losesOT;
            else
                this.points = 3*this.wins + 2*this.winsOT + this.losesOT;
            this.shotsOnTarget += match.awayShotsOnTarget;
            this.shotsOnTargetOpponent += match.homeShotsOnTarget;
            this.numberOfPP += match.awayNumberOfPP;
            this.numberOfPPopponent += match.homeNumberOfPP;
            this.goalsInPP += match.awayGoalsInPP;
            this.goalsInPPopponent += match.homeGoalsInPP;
            this.shorthandedGoals += match.awayShorthandedGoals;
            this.shorthandedGoalsOpponent += match.homeShorthandedGoals;
            this.faceoffsWon += match.awayFaceoffsWon;
            this.numberOffFaceoffs += match.homeFaceoffsWon + match.awayFaceoffsWon;
            this.blockedShots += match.awayBlockedShots;
            this.blockedShotsOpponent += match.homeBlockedShots;
            this.hits += match.awayHits;
            this.hitsOpponent += match.homeHits;
            this.penaltyMinutes += match.awayPenaltyMinutes;
            this.penaltyMinutesOpponent += match.homePenaltyMinutes;
            this.numberOf2MinPenalties += match.away2MinPenalties;
            this.numberOf2MinPenaltiesOpponent += match.home2MinPenalties;

            this.shotsOnTarget1stPeriod += match.awayShotsOnTarget1stPeriod;
            this.shotsOnTarget2ndPeriod += match.awayShotsOnTarget2ndPeriod;
            this.shotsOnTarget3rdPeriod += match.awayShotsOnTarget3rdPeriod;
            this.shotsOnTarget1stPeriodOpp += match.homeShotsOnTarget1stPeriod;
            this.shotsOnTarget2ndPeriodOpp += match.homeShotsOnTarget2ndPeriod;
            this.shotsOnTarget3rdPeriodOpp += match.homeShotsOnTarget3rdPeriod;
            this.penaltyMinutes1stPeriod += match.awayPenaltyMinutes1stPeriod;
            this.penaltyMinutes2ndPeriod += match.awayPenaltyMinutes2ndPeriod;
            this.penaltyMinutes3rdPeriod += match.awayPenaltyMinutes3rdPeriod;
            this.penaltyMinutes1stPeriodOpp += match.homePenaltyMinutes1stPeriod;
            this.penaltyMinutes2ndPeriodOpp += match.homePenaltyMinutes2ndPeriod;
            this.penaltyMinutes3rdPeriodOpp += match.homePenaltyMinutes3rdPeriod;
            this.twoMinPenalties1stPeriod += match.away2MinPenalties1stPeriod;
            this.twoMinPenalties2ndPeriod += match.away2MinPenalties2ndPeriod;
            this.twoMinPenalties3rdPeriod += match.away2MinPenalties3rdPeriod;
            this.twoMinPenalties1stPeriodOpp += match.home2MinPenalties1stPeriod;
            this.twoMinPenalties2ndPeriodOpp += match.home2MinPenalties2ndPeriod;
            this.twoMinPenalties3rdPeriodOpp += match.home2MinPenalties3rdPeriod;
            this.timeInAttack += match.awayTimeInAttack;
            this.timeInAttackOpp += match.homeTimeInAttack;
            this.timeInAttack1stPeriod  += match.awayTimeInAttack1stPeriod;
            this.timeInAttack1stPeriodOpp  += match.homeTimeInAttack1stPeriod;
            this.timeInAttack2ndPeriod  += match.awayTimeInAttack2ndPeriod;
            this.timeInAttack2ndPeriodOpp  += match.homeTimeInAttack2ndPeriod;
            this.timeInAttack3rdPeriod  += match.awayTimeInAttack3rdPeriod;
            this.timeInAttack3rdPeriodOpp  += match.homeTimeInAttack3rdPeriod;
            this.missedShots += match.awayMissedShots;
            this.missedShotsOpponent += match.homeMissedShots;
        }

        this.pushTeamToFile();
    }


    public static Team getTeamFromFileWithSeason(String teamName, String season){
        String path = Settings.getPathToDatabase() + season + "/" + Team.getLeague(teamName) + "/Teams/" + teamName+".xml";
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Team.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Team) un.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Team getTeamFromFile(String teamName){
        String league = Team.getLeague(teamName);
        String curSeason = Settings.getCurrentSeason(league);
        String fileName = Settings.getPathToDatabase() + curSeason + "/" + Team.getLeague(teamName)+ "/Teams/" + teamName+".xml";
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Team.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Team) un.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void pushTeamToFile(){
        String curSeason = Settings.getCurrentSeason(Team.getLeague(this.teamName));
        String fileName = Settings.getPathToDatabase() + curSeason + "/" + Team.getLeague(this.teamName) + "/Teams/" + this.teamName + ".xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Team.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
            ////////////////////////////////////////////////////РАСКОММЕНТИТЬ ПОСЛЕ ОТЛАДКИ
            try {
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/hockey/database/" + curSeason + "/" + Team.getLeague(this.teamName) + "/Teams/" + this.teamName + ".xml" , fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static double roundResult(double d, int precise) {
        precise = (int) Math.pow(10,precise);
        d = d*precise;
        int i = (int) Math.round(d);
        return (double) i/precise;
    }

    public static String getShortName(String teamName){
        String result = "";
        try {
            File fileDir = new File(Settings.getPathToDatabase() + "Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(teamName)){
                    result = str.split("=")[5];
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getNameFromFullName(String fullName){
        String result = "";
        try {
            File fileDir = new File(Settings.getPathToDatabase() + "Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[1].equals(fullName)){
                    result = str.split("=")[0];
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getFullName(String name){
        String result = "";
        try {
            File fileDir = new File(Settings.getPathToDatabase() + "Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(name)){
                    result = str.split("=")[1];
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getNameForMatchCenter(String teamName, String league){
        String result = "";
        try {
            File fileDir = new File("database/Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[8].equals(teamName) && str.split("=")[2].equals(league)){
                    return str.split("=")[0];
                }
            }
            in.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String getLeague(String teamName){
        String result = "";
        try {
            File fileDir = new File(Settings.getPathToDatabase() + "Teams.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[0].equals(teamName)){
                    result = str.split("=")[2];
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }
}