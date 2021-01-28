package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.text.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

// определяем корневой элемент
@XmlRootElement(name = "Match")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"homeTeam", "awayTeam", "homeScore", "awayScore", "homeScoreOT", "homeScoreBullits",
                        "awayScoreOT", "awayScoreBullits", "homeShotsOnTarget", "awayShotsOnTarget",
                        "homeNumberOfPP", "awayNumberOfPP", "homeGoalsInPP", "awayGoalsInPP", "homeShorthandedGoals",
                        "awayShorthandedGoals", "homeFaceoffsWon", "awayFaceoffsWon", "homeBlockedShots",
                        "awayBlockedShots", "homeHits", "awayHits", "homePenaltyMinutes", "awayPenaltyMinutes",
                        "home2MinPenalties", "away2MinPenalties",
                        "homeScore1stPeriod", "homeScore2ndPeriod", "homeScore3rdPeriod",
                        "awayScore1stPeriod", "awayScore2ndPeriod", "awayScore3rdPeriod",
                        "homeShotsOnTarget1stPeriod", "homeShotsOnTarget2ndPeriod", "homeShotsOnTarget3rdPeriod",
                        "awayShotsOnTarget1stPeriod", "awayShotsOnTarget2ndPeriod", "awayShotsOnTarget3rdPeriod",
                        "homePenaltyMinutes1stPeriod", "homePenaltyMinutes2ndPeriod", "homePenaltyMinutes3rdPeriod",
                        "awayPenaltyMinutes1stPeriod", "awayPenaltyMinutes2ndPeriod", "awayPenaltyMinutes3rdPeriod",
                        "home2MinPenalties1stPeriod", "home2MinPenalties2ndPeriod", "home2MinPenalties3rdPeriod",
                        "away2MinPenalties1stPeriod", "away2MinPenalties2ndPeriod", "away2MinPenalties3rdPeriod",
                        "homeTimeInAttack", "awayTimeInAttack", "homeTimeInAttack1stPeriod", "awayTimeInAttack1stPeriod",
                        "homeTimeInAttack2ndPeriod", "awayTimeInAttack2ndPeriod", "homeTimeInAttack3rdPeriod",
                        "awayTimeInAttack3rdPeriod", "homeMissedShots", "awayMissedShots", "date",
                        "url", "title", "league"
                })

public class Match {
    public String homeTeam;
    public String awayTeam;
    public int homeScore;
    public int awayScore;
    public int homeScoreOT;
    public int homeScoreBullits;
    public int awayScoreOT;
    public int awayScoreBullits;
    public int homeShotsOnTarget;
    public int awayShotsOnTarget;
    public int homeNumberOfPP;
    public int awayNumberOfPP;
    public int homeGoalsInPP;
    public int awayGoalsInPP;
    public int homeShorthandedGoals;
    public int awayShorthandedGoals;
    public int homeFaceoffsWon;
    public int awayFaceoffsWon;
    public int homeBlockedShots;
    public int awayBlockedShots;
    public int homeHits;
    public int awayHits;
    public int homePenaltyMinutes;
    public int awayPenaltyMinutes;
    public int home2MinPenalties;
    public int away2MinPenalties;
    public int homeScore1stPeriod;
    public int homeScore2ndPeriod;
    public int homeScore3rdPeriod;
    public int awayScore1stPeriod;
    public int awayScore2ndPeriod;
    public int awayScore3rdPeriod;
    public int homeShotsOnTarget1stPeriod;
    public int awayShotsOnTarget1stPeriod;
    public int homePenaltyMinutes1stPeriod;
    public int awayPenaltyMinutes1stPeriod;
    public int home2MinPenalties1stPeriod;
    public int away2MinPenalties1stPeriod;
    public int homeShotsOnTarget2ndPeriod;
    public int awayShotsOnTarget2ndPeriod;
    public int homePenaltyMinutes2ndPeriod;
    public int awayPenaltyMinutes2ndPeriod;
    public int home2MinPenalties2ndPeriod;
    public int away2MinPenalties2ndPeriod;
    public int homeShotsOnTarget3rdPeriod;
    public int awayShotsOnTarget3rdPeriod;
    public int homePenaltyMinutes3rdPeriod;
    public int awayPenaltyMinutes3rdPeriod;
    public int home2MinPenalties3rdPeriod;
    public int away2MinPenalties3rdPeriod;
    public int homeTimeInAttack;
    public int awayTimeInAttack;
    public int homeTimeInAttack1stPeriod;
    public int awayTimeInAttack1stPeriod;
    public int homeTimeInAttack2ndPeriod;
    public int awayTimeInAttack2ndPeriod;
    public int homeTimeInAttack3rdPeriod;
    public int awayTimeInAttack3rdPeriod;
    public int homeMissedShots;
    public int awayMissedShots;

    public String date;
    public String title;
    public String league;
    public String url;

    public Match() {
    }

    public Match(String homeTeam, String awayTeam, String[][] stats, String date, String url){
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = Integer.parseInt(stats[0][1]);
        this.awayScore = Integer.parseInt(stats[0][2]);
        this.homeScoreOT = Integer.parseInt(stats[1][1]);
        this.homeScoreBullits = Integer.parseInt(stats[2][1]);
        this.awayScoreOT = Integer.parseInt(stats[1][2]);
        this.awayScoreBullits = Integer.parseInt(stats[2][2]);
        this.homeShotsOnTarget = Integer.parseInt(stats[3][1]);
        this.awayShotsOnTarget = Integer.parseInt(stats[3][2]);
        this.homeNumberOfPP = Integer.parseInt(stats[4][1]);
        this.awayNumberOfPP = Integer.parseInt(stats[4][2]);
        this.homeGoalsInPP = Integer.parseInt(stats[5][1]);
        this.awayGoalsInPP = Integer.parseInt(stats[5][2]);
        this.homeShorthandedGoals = Integer.parseInt(stats[6][1]);
        this.awayShorthandedGoals = Integer.parseInt(stats[6][2]);
        this.homeFaceoffsWon = Integer.parseInt(stats[7][1]);
        this.awayFaceoffsWon = Integer.parseInt(stats[7][2]);
        this.homeBlockedShots = Integer.parseInt(stats[8][1]);
        this.awayBlockedShots = Integer.parseInt(stats[8][2]);
        this.homeHits = Integer.parseInt(stats[9][1]);
        this.awayHits = Integer.parseInt(stats[9][2]);
        this.home2MinPenalties = Integer.parseInt(stats[10][1]);
        this.away2MinPenalties = Integer.parseInt(stats[10][2]);
        this.homeScore1stPeriod = Integer.parseInt(stats[11][1]);
        this.homeScore2ndPeriod = Integer.parseInt(stats[12][1]);
        this.homeScore3rdPeriod = Integer.parseInt(stats[13][1]);
        this.awayScore1stPeriod = Integer.parseInt(stats[11][2]);
        this.awayScore2ndPeriod = Integer.parseInt(stats[12][2]);
        this.awayScore3rdPeriod = Integer.parseInt(stats[13][2]);
        this.homePenaltyMinutes = Integer.parseInt(stats[14][1]);
        this.awayPenaltyMinutes = Integer.parseInt(stats[14][2]);
        this.homeShotsOnTarget1stPeriod = Integer.parseInt(stats[15][1]);
        this.awayShotsOnTarget1stPeriod = Integer.parseInt(stats[15][2]);
        this.homeShotsOnTarget2ndPeriod = Integer.parseInt(stats[16][1]);
        this.awayShotsOnTarget2ndPeriod = Integer.parseInt(stats[16][2]);
        this.homeShotsOnTarget3rdPeriod = Integer.parseInt(stats[17][1]);
        this.awayShotsOnTarget3rdPeriod = Integer.parseInt(stats[17][2]);
        this.homePenaltyMinutes1stPeriod = Integer.parseInt(stats[18][1]);
        this.awayPenaltyMinutes1stPeriod = Integer.parseInt(stats[18][2]);
        this.homePenaltyMinutes2ndPeriod = Integer.parseInt(stats[19][1]);
        this.awayPenaltyMinutes2ndPeriod = Integer.parseInt(stats[19][2]);
        this.homePenaltyMinutes3rdPeriod = Integer.parseInt(stats[20][1]);
        this.awayPenaltyMinutes3rdPeriod = Integer.parseInt(stats[20][2]);
        this.home2MinPenalties1stPeriod = Integer.parseInt(stats[21][1]);
        this.away2MinPenalties1stPeriod = Integer.parseInt(stats[21][2]);
        this.home2MinPenalties2ndPeriod = Integer.parseInt(stats[22][1]);
        this.away2MinPenalties2ndPeriod = Integer.parseInt(stats[22][2]);
        this.home2MinPenalties3rdPeriod = Integer.parseInt(stats[23][1]);
        this.away2MinPenalties3rdPeriod = Integer.parseInt(stats[23][2]);
        this.homeTimeInAttack = Integer.parseInt(stats[24][1]);
        this.awayTimeInAttack = Integer.parseInt(stats[24][2]);
        this.homeTimeInAttack1stPeriod = Integer.parseInt(stats[25][1]);
        this.awayTimeInAttack1stPeriod = Integer.parseInt(stats[25][2]);
        this.homeTimeInAttack2ndPeriod = Integer.parseInt(stats[26][1]);
        this.awayTimeInAttack2ndPeriod = Integer.parseInt(stats[26][2]);
        this.homeTimeInAttack3rdPeriod = Integer.parseInt(stats[27][1]);
        this.awayTimeInAttack3rdPeriod = Integer.parseInt(stats[27][2]);
        this.homeMissedShots = Integer.parseInt(stats[28][1]);
        this.awayMissedShots = Integer.parseInt(stats[28][2]);
        this.date = date;
        this.title = Team.getShortName(homeTeam) + Team.getShortName(awayTeam) + date;
        this.league = Team.getLeague(homeTeam);
        this.url = url;
    }

    public Match(Document doc, Document doc2, String league, String url){
        this.homeTeam = "";
        this.awayTeam = "";
        this.homeScore = 0;
        this.awayScore = 0;
        this.homeScoreOT = 0;
        this.homeScoreBullits = 0;
        this.awayScoreOT = 0;
        this.awayScoreBullits = 0;
        this.homeShotsOnTarget = 0;
        this.awayShotsOnTarget = 0;
        this.homeNumberOfPP = 0;
        this.awayNumberOfPP = 0;
        this.homeGoalsInPP = 0;
        this.awayGoalsInPP = 0;
        this.homeShorthandedGoals = 0;
        this.awayShorthandedGoals = 0;
        this.homeFaceoffsWon = 0;
        this.awayFaceoffsWon = 0;
        this.homeBlockedShots = 0;
        this.awayBlockedShots = 0;
        this.homeHits = 0;
        this.awayHits = 0;
        this.home2MinPenalties = 0;
        this.away2MinPenalties = 0;
        this.homeScore1stPeriod = 0;
        this.homeScore2ndPeriod = 0;
        this.homeScore3rdPeriod = 0;
        this.awayScore1stPeriod = 0;
        this.awayScore2ndPeriod = 0;
        this.awayScore3rdPeriod = 0;
        this.homePenaltyMinutes = 0;
        this.awayPenaltyMinutes = 0;
        this.homeShotsOnTarget1stPeriod = 0;
        this.awayShotsOnTarget1stPeriod = 0;
        this.homeShotsOnTarget2ndPeriod = 0;
        this.awayShotsOnTarget2ndPeriod = 0;
        this.homeShotsOnTarget3rdPeriod = 0;
        this.awayShotsOnTarget3rdPeriod = 0;
        this.homePenaltyMinutes1stPeriod = 0;
        this.awayPenaltyMinutes1stPeriod = 0;
        this.homePenaltyMinutes2ndPeriod = 0;
        this.awayPenaltyMinutes2ndPeriod = 0;
        this.homePenaltyMinutes3rdPeriod = 0;
        this.awayPenaltyMinutes3rdPeriod = 0;
        this.home2MinPenalties1stPeriod = 0;
        this.away2MinPenalties1stPeriod = 0;
        this.home2MinPenalties2ndPeriod = 0;
        this.away2MinPenalties2ndPeriod = 0;
        this.home2MinPenalties3rdPeriod = 0;
        this.away2MinPenalties3rdPeriod = 0;
        this.homeTimeInAttack = 0;
        this.awayTimeInAttack = 0;
        this.homeTimeInAttack1stPeriod = 0;
        this.awayTimeInAttack1stPeriod = 0;
        this.homeTimeInAttack2ndPeriod = 0;
        this.awayTimeInAttack2ndPeriod = 0;
        this.homeTimeInAttack3rdPeriod = 0;
        this.awayTimeInAttack3rdPeriod = 0;
        this.date = "";
        this.url = url;

        switch (league){
            case "KHL":{
                org.jsoup.nodes.Element element = doc.getElementsByClass("b-wide_block b-wide_tile m-wide_tile").get(0);

                Elements el = element.select("h3");
                this.homeTeam = Team.getNameFromFullName(el.get(0).ownText());
                this.awayTeam = Team.getNameFromFullName(el.get(2).ownText());
                String score = el.get(1).wholeText().replaceAll(" ", "").trim();
                int homeScore = 0;
                int awayScore = 0;

                if (score.contains("Б")) {
                    homeScore = Integer.parseInt((score.split("–")[0]));
                    awayScore = Integer.parseInt((score.split("–")[1]).split(" ")[0]);
                    if (homeScore > awayScore)
                        this.homeScoreBullits = 1;
                    else
                        this.awayScoreBullits = 1;
                }
                if (score.contains("OT")) {
                    homeScore = Integer.parseInt((score.split("–")[0]));
                    awayScore = Integer.parseInt((score.split("–")[1]).split(" ")[0]);
                    if (homeScore > awayScore)
                        this.homeScoreOT = 1;
                    else
                        this.awayScoreOT = 1;
                }
                if (!score.contains("Б") && !score.contains("OT")){
                    homeScore = Integer.parseInt((score.split("–")[0]));
                    awayScore = Integer.parseInt((score.split("–")[1]));
                }

                this.homeScore = homeScore - this.homeScoreOT - this.homeScoreBullits;
                this.awayScore = awayScore - this.awayScoreOT - this.awayScoreBullits;

                el = element.select("li");
                this.date = el.get(0).text().split(" ")[1];

                String byPeriods = element.getElementsByClass("b-period_score").text();
                this.homeScore1stPeriod = Integer.parseInt(byPeriods.split(" ")[0].split(":")[0]);
                this.awayScore1stPeriod = Integer.parseInt(byPeriods.split(" ")[0].split(":")[1]);
                this.homeScore2ndPeriod = Integer.parseInt(byPeriods.split(" ")[1].split(":")[0]);
                this.awayScore2ndPeriod = Integer.parseInt(byPeriods.split(" ")[1].split(":")[1]);
                this.homeScore3rdPeriod = Integer.parseInt(byPeriods.split(" ")[2].split(":")[0]);
                this.awayScore3rdPeriod = Integer.parseInt(byPeriods.split(" ")[2].split(":")[1]);

                int position = -1;
                boolean flag3per = false;
                String period = "1";
                this.homePenaltyMinutes = 0;
                this.awayPenaltyMinutes = 0;
                this.home2MinPenalties = 0;
                this.away2MinPenalties = 0;
                this.home2MinPenalties1stPeriod = 0;
                this.away2MinPenalties1stPeriod = 0;
                this.home2MinPenalties2ndPeriod = 0;
                this.away2MinPenalties2ndPeriod = 0;
                this.home2MinPenalties3rdPeriod = 0;
                this.away2MinPenalties3rdPeriod = 0;
                this.homePenaltyMinutes1stPeriod = 0;
                this.awayPenaltyMinutes1stPeriod = 0;
                this.homePenaltyMinutes2ndPeriod = 0;
                this.awayPenaltyMinutes2ndPeriod = 0;
                this.homePenaltyMinutes3rdPeriod = 0;
                this.awayPenaltyMinutes3rdPeriod = 0;

                try{
                    Elements trPenalties = doc.getElementsByClass("dataTable nowrap stripe compact hover row-border no-footer m-table_small").get(doc.getElementsByClass("dataTable nowrap stripe compact hover row-border no-footer m-table_small").size()-1).select("tbody").get(0).select("tr");
                    while ((position < trPenalties.size()) && (!flag3per)){
                        position ++;
                        if (trPenalties.get(position).wholeText().contains("Всего за первый период"))
                            period = "2";
                        if (trPenalties.get(position).wholeText().contains("Всего за второй период"))
                            period = "3";
                        if (!trPenalties.get(position).wholeText().contains("Всего за третий период") &&!trPenalties.get(position).wholeText().contains("Итого") &&!trPenalties.get(position).wholeText().contains(":6")){
                            if (trPenalties.get(position).toString().contains("odd") || trPenalties.get(position).toString().contains("even")){
                                String whosePenalty = "away";
                                int r = 1;
                                if (!trPenalties.get(position).select("td").get(0).text().equals("")){
                                    whosePenalty = "home";
                                    r = 0;
                                }
                                double time = Settings.getSeconds(trPenalties.get(position).select("td").get(r).text());
                                if (time < 1200)
                                    period = "1";
                                else
                                if (time < 2400)
                                    period = "2";
                                else
                                if (time < 3600)
                                    period = "3";

                                if (trPenalties.get(position).select("td").get(r+2).text().trim().equals("2")){
                                    switch (whosePenalty+period) {
                                        case "home1":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            home2MinPenalties ++;
                                            home2MinPenalties1stPeriod++;
                                            homePenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                        case "home2":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            home2MinPenalties ++;
                                            home2MinPenalties2ndPeriod++;
                                            homePenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                        case "home3":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            home2MinPenalties ++;
                                            home2MinPenalties3rdPeriod++;
                                            homePenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                        case "away1":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            away2MinPenalties ++;
                                            away2MinPenalties1stPeriod++;
                                            awayPenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                        case "away2":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            away2MinPenalties ++;
                                            away2MinPenalties2ndPeriod++;
                                            awayPenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                        case "away3":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            away2MinPenalties ++;
                                            away2MinPenalties3rdPeriod++;
                                            awayPenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                    }
                                } else{
                                    switch (whosePenalty+period) {
                                        case "home1":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            homePenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                        case "home2":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            homePenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                        case "home3":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            homePenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                        case "away1":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            awayPenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                        case "away2":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            awayPenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                        case "away3":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            awayPenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
                                            break;
                                    }
                                }

                            }

                        } else
                            flag3per = true;
                    }
                } catch (java.lang.ArrayIndexOutOfBoundsException ignored){
                }

                org.jsoup.nodes.Element tablePP1 = doc.getElementsByClass("dataTable stripe compact row-border hl no-footer rc m-table_small").get(0).select("tr").get(1);
                org.jsoup.nodes.Element tablePP2 = doc.getElementsByClass("dataTable stripe compact row-border hl no-footer rc m-table_small").get(0).select("tr").get(2);
                if (tablePP1.select("td").get(0).ownText().equals(Team.getFullName(homeTeam))){
                    this.homeNumberOfPP = Integer.parseInt(tablePP1.select("td").get(2).ownText());
                    this.awayNumberOfPP = Integer.parseInt(tablePP2.select("td").get(2).ownText());
                    this.homeGoalsInPP = Integer.parseInt(tablePP1.select("td").get(3).ownText());
                    this.awayGoalsInPP = Integer.parseInt(tablePP2.select("td").get(3).ownText());
                    this.homeShorthandedGoals = Integer.parseInt(tablePP1.select("td").get(9).ownText());
                    this.awayShorthandedGoals = Integer.parseInt(tablePP2.select("td").get(9).ownText());
                }
                else {
                    this.awayNumberOfPP = Integer.parseInt(tablePP1.select("td").get(2).ownText());
                    this.homeNumberOfPP = Integer.parseInt(tablePP2.select("td").get(2).ownText());
                    this.awayGoalsInPP = Integer.parseInt(tablePP1.select("td").get(3).ownText());
                    this.homeGoalsInPP = Integer.parseInt(tablePP2.select("td").get(3).ownText());
                    this.awayShorthandedGoals = Integer.parseInt(tablePP1.select("td").get(9).ownText());
                    this.homeShorthandedGoals = Integer.parseInt(tablePP2.select("td").get(9).ownText());
                }


                int totalShotsHT = 0;
                int totalShotsAT = 0;
                this.homeFaceoffsWon = 0;
                this.awayFaceoffsWon = 0;
                this.homeBlockedShots = 0;
                this.awayBlockedShots = 0;
                this.homeHits = 0;
                this.awayHits = 0;

                org.jsoup.nodes.Element period1 = null;
                org.jsoup.nodes.Element period2 = null;
                org.jsoup.nodes.Element period3 = null;

                boolean flagEnglishText = false;

                if (doc2.getElementsContainingText("Статистика 1-го периода:").size()>0){
                    period1 = doc2.getElementsContainingText("Статистика 1-го периода:").get(11);
                    period2 = doc2.getElementsContainingText("Статистика 2-го периода:").get(11);
                    period3 = doc2.getElementsContainingText("Статистика 3-го периода:").get(11);
                }
                if (period == null || period2 == null || period3 == null){
                    if (doc2.getElementsContainingText("Stats of 1st period").size()>0){
                        period1 = doc2.getElementsContainingText("Stats of 1st period").get(11);
                        period2 = doc2.getElementsContainingText("Stats of 2nd period").get(11);
                        period3 = doc2.getElementsContainingText("Stats of 3rd period").get(11);
                        flagEnglishText = true;
                    }
                }

                String period1stats = period1.ownText();

                if (doc2.getElementsContainingText("Статистика 1-го периода:").size()>0){
                    totalShotsHT += Integer.parseInt(period1stats.split(";")[0].split(":")[1].split("-")[0].trim());
                    totalShotsAT += Integer.parseInt(period1stats.split(";")[0].split(":")[1].split("-")[1].trim());
                }
                if (doc2.getElementsContainingText("Stats of 1st period").size()>0 && flagEnglishText){
                    totalShotsHT += Integer.parseInt(period1stats.split(";")[0].split("Shots:")[1].split("-")[0].trim());
                    totalShotsAT += Integer.parseInt(period1stats.split(";")[0].split("Shots:")[1].split("-")[1].trim());
                }
                this.homeShotsOnTarget1stPeriod = Integer.parseInt(period1stats.split(";")[1].split(":")[1].split("-")[0].trim());
                this.awayShotsOnTarget1stPeriod = Integer.parseInt(period1stats.split(";")[1].split(":")[1].split("-")[1].trim());
                homeFaceoffsWon += Integer.parseInt(period1stats.split(";")[3].split(":")[1].split("-")[0].trim());
                awayFaceoffsWon += Integer.parseInt(period1stats.split(";")[3].split(":")[1].split("-")[1].trim());
                homeBlockedShots += Integer.parseInt(period1stats.split(";")[4].split(":")[1].split("-")[0].trim());
                awayBlockedShots += Integer.parseInt(period1stats.split(";")[4].split(":")[1].split("-")[1].trim());
                homeHits += Integer.parseInt(period1stats.split(";")[5].split(":")[1].split("-")[0].trim());
                awayHits += Integer.parseInt(period1stats.split(";")[5].split(":")[1].split("-")[1].trim());
                this.homeTimeInAttack1stPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period1stats.split(";")[6].split(": ")[1].split("-")[0].trim())));
                this.awayTimeInAttack1stPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period1stats.split(";")[6].split(": ")[1].split("-")[1].trim())));

                String period2stats = period2.ownText();
                if (doc2.getElementsContainingText("Статистика 2-го периода:").size()>0){
                    totalShotsHT += Integer.parseInt(period2stats.split(";")[0].split(":")[1].split("-")[0].trim());
                    totalShotsAT += Integer.parseInt(period2stats.split(";")[0].split(":")[1].split("-")[1].trim());
                }
                if (doc2.getElementsContainingText("Stats of 2nd period").size()>0 && flagEnglishText){
                    totalShotsHT += Integer.parseInt(period2stats.split(";")[0].split("Shots:")[1].split("-")[0].trim());
                    totalShotsAT += Integer.parseInt(period2stats.split(";")[0].split("Shots:")[1].split("-")[1].trim());
                }
                this.homeShotsOnTarget2ndPeriod = Integer.parseInt(period2stats.split(";")[1].split(": ")[1].split("-")[0].trim());
                this.awayShotsOnTarget2ndPeriod = Integer.parseInt(period2stats.split(";")[1].split(": ")[1].split("-")[1].trim());
                homeFaceoffsWon += Integer.parseInt(period2stats.split(";")[3].split(":")[1].split("-")[0].trim());
                awayFaceoffsWon += Integer.parseInt(period2stats.split(";")[3].split(":")[1].split("-")[1].trim());
                homeBlockedShots += Integer.parseInt(period2stats.split(";")[4].split(":")[1].split("-")[0].trim());
                awayBlockedShots += Integer.parseInt(period2stats.split(";")[4].split(":")[1].split("-")[1].trim());
                homeHits += Integer.parseInt(period2stats.split(";")[5].split(":")[1].split("-")[0].trim());
                awayHits += Integer.parseInt(period2stats.split(";")[5].split(":")[1].split("-")[1].trim());
                this.homeTimeInAttack2ndPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period2stats.split(";")[6].split(": ")[1].split("-")[0].trim())));
                this.awayTimeInAttack2ndPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period2stats.split(";")[6].split(": ")[1].split("-")[1].trim())));

                String period3stats = period3.ownText();

                if (doc2.getElementsContainingText("Статистика 3-го периода:").size()>0){
                    totalShotsHT += Integer.parseInt(period3stats.split(";")[0].split(":")[1].split("-")[0].trim());
                    totalShotsAT += Integer.parseInt(period3stats.split(";")[0].split(":")[1].split("-")[1].trim());
                }
                if (doc2.getElementsContainingText("Stats of 3rd period").size()>0 && flagEnglishText){
                    totalShotsHT += Integer.parseInt(period3stats.split(";")[0].split("Shots:")[1].split("-")[0].trim());
                    totalShotsAT += Integer.parseInt(period3stats.split(";")[0].split("Shots:")[1].split("-")[1].trim());
                }
                this.homeShotsOnTarget3rdPeriod = Integer.parseInt(period3stats.split(";")[1].split(":")[1].split("-")[0].trim());
                this.awayShotsOnTarget3rdPeriod = Integer.parseInt(period3stats.split(";")[1].split(":")[1].split("-")[1].trim());
                homeFaceoffsWon += Integer.parseInt(period3stats.split(";")[3].split(":")[1].split("-")[0].trim());
                awayFaceoffsWon += Integer.parseInt(period3stats.split(";")[3].split(":")[1].split("-")[1].trim());
                homeBlockedShots += Integer.parseInt(period3stats.split(";")[4].split(":")[1].split("-")[0].trim());
                awayBlockedShots += Integer.parseInt(period3stats.split(";")[4].split(":")[1].split("-")[1].trim());
                homeHits += Integer.parseInt(period3stats.split(";")[5].split(":")[1].split("-")[0].trim());
                awayHits += Integer.parseInt(period3stats.split(";")[5].split(":")[1].split("-")[1].trim());
                this.homeTimeInAttack3rdPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period3stats.split(";")[6].split(": ")[1].split("-")[0].trim())));
                this.awayTimeInAttack3rdPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period3stats.split(";")[6].split(": ")[1].split("-")[1].trim())));

                this.homeShotsOnTarget = homeShotsOnTarget1stPeriod + homeShotsOnTarget2ndPeriod + homeShotsOnTarget3rdPeriod;
                this.awayShotsOnTarget = awayShotsOnTarget1stPeriod + awayShotsOnTarget2ndPeriod + awayShotsOnTarget3rdPeriod;
                this.homeTimeInAttack = homeTimeInAttack1stPeriod + homeTimeInAttack2ndPeriod + homeTimeInAttack3rdPeriod;
                this.awayTimeInAttack = awayTimeInAttack1stPeriod + awayTimeInAttack2ndPeriod + awayTimeInAttack3rdPeriod;
                this.homeMissedShots = totalShotsHT - homeShotsOnTarget;
                this.awayMissedShots = totalShotsAT - awayShotsOnTarget;

                break;

            }
            case "VHL":{
                try {
                    org.jsoup.nodes.Element element = doc.getElementsByClass("matches_protocol_main").get(0);
                    this.homeTeam = Team.getNameFromFullName(element.getElementsByClass("first_row").get(0).child(0).child(0).text());
                    this.awayTeam = Team.getNameFromFullName(element.getElementsByClass("first_row").get(0).child(2).child(0).text());
                    String score = element.getElementsByClass("first_row").get(0).child(1).child(0).text().trim();
                    String scoreByPeriods = element.getElementsByClass("first_row").get(0).child(1).getElementsByClass("detail_count").get(0).text().replaceAll("\\(", "").replaceAll("\\)", "").trim();
                    int homeScore = 0;
                    int awayScore = 0;

                    if (score.contains("Б")) {
                        homeScore = Integer.parseInt((score.split(":")[0]));
                        awayScore = Integer.parseInt((score.split(":")[1]).split(" ")[0]);
                        if (homeScore > awayScore)
                            this.homeScoreBullits = 1;
                        else
                            this.awayScoreBullits = 1;
                    }
                    if (score.contains("ОТ")) {
                        homeScore = Integer.parseInt((score.split(":")[0]));
                        awayScore = Integer.parseInt((score.split(":")[1]).split(" ")[0]);
                        scoreByPeriods = scoreByPeriods.split(";")[0].trim();
                        if (homeScore > awayScore)
                            this.homeScoreOT = 1;
                        else
                            this.awayScoreOT = 1;
                    }
                    if (!score.contains("Б") && !score.contains("ОТ")){
                        homeScore = Integer.parseInt((score.split(":")[0]));
                        awayScore = Integer.parseInt((score.split(":")[1]));
                        scoreByPeriods = scoreByPeriods.split(";")[0].trim();
                    }

                    this.homeScore = homeScore - this.homeScoreOT - this.homeScoreBullits;
                    this.awayScore = awayScore - this.awayScoreOT - this.awayScoreBullits;


                    this.homeScore1stPeriod = Integer.parseInt(scoreByPeriods.split(" ")[0].split(":")[0]);
                    this.homeScore2ndPeriod = Integer.parseInt(scoreByPeriods.split(" ")[1].split(":")[0]);
                    this.homeScore3rdPeriod = Integer.parseInt(scoreByPeriods.split(" ")[2].split(":")[0]);
                    this.awayScore1stPeriod = Integer.parseInt(scoreByPeriods.split(" ")[0].split(":")[1]);
                    this.awayScore2ndPeriod = Integer.parseInt(scoreByPeriods.split(" ")[1].split(":")[1]);
                    this.awayScore3rdPeriod = Integer.parseInt(scoreByPeriods.split(" ")[2].split(":")[1]);

                    String period = "1";
                    this.homePenaltyMinutes = 0;
                    this.awayPenaltyMinutes = 0;
                    this.home2MinPenalties = 0;
                    this.away2MinPenalties = 0;
                    this.home2MinPenalties1stPeriod = 0;
                    this.away2MinPenalties1stPeriod = 0;
                    this.home2MinPenalties2ndPeriod = 0;
                    this.away2MinPenalties2ndPeriod = 0;
                    this.home2MinPenalties3rdPeriod = 0;
                    this.away2MinPenalties3rdPeriod = 0;
                    this.homePenaltyMinutes1stPeriod = 0;
                    this.awayPenaltyMinutes1stPeriod = 0;
                    this.homePenaltyMinutes2ndPeriod = 0;
                    this.awayPenaltyMinutes2ndPeriod = 0;
                    this.homePenaltyMinutes3rdPeriod = 0;
                    this.awayPenaltyMinutes3rdPeriod = 0;

                    try{
                        Elements trPenalties = doc.getElementsByClass("tablesorter matches_penalty").get(0).select("tr");
                        for (int i=2; i<trPenalties.size(); i++){
                            org.jsoup.nodes.Element tdTime = trPenalties.get(i).select("td").get(0);
                            if (tdTime.toString().contains("time")){
                                String whosePenalty = "away";
                                int column = 5;
                                if (!trPenalties.get(i).select("td").get(0).text().equals("")){
                                    whosePenalty = "home";
                                    column = 0;
                                }
                                double time = Settings.getSeconds(trPenalties.get(i).select("td").get(column).text());
                                if (time < 1200)
                                    period = "1";
                                else
                                if (time < 2400)
                                    period = "2";
                                else
                                if (time < 3600)
                                    period = "3";

                                if (trPenalties.get(i).select("td").get(column+2).text().trim().equals("2")){
                                    switch (whosePenalty+period) {
                                        case "home1":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            home2MinPenalties ++;
                                            home2MinPenalties1stPeriod++;
                                            homePenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "home2":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            home2MinPenalties ++;
                                            home2MinPenalties2ndPeriod++;
                                            homePenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "home3":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            home2MinPenalties ++;
                                            home2MinPenalties3rdPeriod++;
                                            homePenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away1":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            away2MinPenalties ++;
                                            away2MinPenalties1stPeriod++;
                                            awayPenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away2":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            away2MinPenalties ++;
                                            away2MinPenalties2ndPeriod++;
                                            awayPenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away3":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            away2MinPenalties ++;
                                            away2MinPenalties3rdPeriod++;
                                            awayPenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                    }
                                } else{
                                    switch (whosePenalty+period) {
                                        case "home1":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            homePenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "home2":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            homePenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "home3":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            homePenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away1":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            awayPenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away2":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            awayPenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away3":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            awayPenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                    }
                                }
                            }

                            int ttt = 0;
                        }
                    } catch (java.lang.ArrayIndexOutOfBoundsException ignored){
                        System.out.println("Ошибка в парсинге штрафного времени");
                    }

                /*homePenaltyMinutes = 2;
                home2MinPenalties = 1;
                home2MinPenalties1stPeriod = 1;
                homePenaltyMinutes1stPeriod = 2;
                home2MinPenalties2ndPeriod = 0;
                homePenaltyMinutes2ndPeriod = 0;
                home2MinPenalties3rdPeriod = 0;
                homePenaltyMinutes3rdPeriod = 0;

                awayPenaltyMinutes = 6;
                away2MinPenalties = 3;
                away2MinPenalties1stPeriod = 2;
                awayPenaltyMinutes1stPeriod = 1;
                away2MinPenalties2ndPeriod = 0;
                awayPenaltyMinutes2ndPeriod = 0;
                away2MinPenalties3rdPeriod = 4;
                awayPenaltyMinutes3rdPeriod = 2;*/

                    int index = 1;
                    for (int i=0; i<doc.getElementsByClass("tablesorter matches_goals").size(); i++ ){
                        if (doc.getElementsByClass("tablesorter matches_goals").get(i).select("tr").get(0).toString().
                                contains("Количество полученных численных преимуществ")){
                            index = i;
                        }

                    }
                    org.jsoup.nodes.Element tablePP1 = doc.getElementsByClass("tablesorter matches_goals").get(index).select("tr").get(1);
                    org.jsoup.nodes.Element tablePP2 = doc.getElementsByClass("tablesorter matches_goals").get(index).select("tr").get(2);
                    if (tablePP1.select("td").get(0).text().equals(Team.getFullName(homeTeam))){
                        this.homeNumberOfPP = Integer.parseInt(tablePP1.select("td").get(2).ownText());
                        this.awayNumberOfPP = Integer.parseInt(tablePP2.select("td").get(2).ownText());
                        this.homeGoalsInPP = Integer.parseInt(tablePP1.select("td").get(3).ownText());
                        this.awayGoalsInPP = Integer.parseInt(tablePP2.select("td").get(3).ownText());
                        this.homeShorthandedGoals = Integer.parseInt(tablePP1.select("td").get(9).ownText());
                        this.awayShorthandedGoals = Integer.parseInt(tablePP2.select("td").get(9).ownText());
                    }
                    else {
                        this.awayNumberOfPP = Integer.parseInt(tablePP1.select("td").get(2).ownText());
                        this.homeNumberOfPP = Integer.parseInt(tablePP2.select("td").get(2).ownText());
                        this.awayGoalsInPP = Integer.parseInt(tablePP1.select("td").get(3).ownText());
                        this.homeGoalsInPP = Integer.parseInt(tablePP2.select("td").get(3).ownText());
                        this.awayShorthandedGoals = Integer.parseInt(tablePP1.select("td").get(9).ownText());
                        this.homeShorthandedGoals = Integer.parseInt(tablePP2.select("td").get(9).ownText());
                    }

                    this.homeMissedShots = 0;
                    this.awayMissedShots = 0;
                    this.homeFaceoffsWon = 0;
                    this.awayFaceoffsWon = 0;
                    this.homeBlockedShots = 0;
                    this.awayBlockedShots = 0;
                    this.homeHits = 0;
                    this.awayHits = 0;

                    int size = doc.getElementsByClass("tablesorter matches_bullits").size()-1;
                    if (size >= 0){
                        org.jsoup.nodes.Element shotsTable = doc.getElementsByClass("tablesorter matches_bullits").get(size);
                        Elements tableRows = shotsTable.select("tr");

                        this.homeShotsOnTarget1stPeriod = Integer.parseInt(tableRows.get(3).select("td").get(2).text().trim());
                        this.homeShotsOnTarget2ndPeriod = Integer.parseInt(tableRows.get(4).select("td").get(2).text().trim());
                        this.homeShotsOnTarget3rdPeriod = Integer.parseInt(tableRows.get(5).select("td").get(2).text().trim());
                        this.awayShotsOnTarget1stPeriod = Integer.parseInt(tableRows.get(3).select("td").get(6).text().trim());
                        this.awayShotsOnTarget2ndPeriod = Integer.parseInt(tableRows.get(4).select("td").get(6).text().trim());
                        this.awayShotsOnTarget3rdPeriod = Integer.parseInt(tableRows.get(5).select("td").get(6).text().trim());

                        this.homeShotsOnTarget = homeShotsOnTarget1stPeriod + homeShotsOnTarget2ndPeriod + homeShotsOnTarget3rdPeriod;
                        this.awayShotsOnTarget = awayShotsOnTarget1stPeriod + awayShotsOnTarget2ndPeriod + awayShotsOnTarget3rdPeriod;
                    }


                    org.jsoup.nodes.Element defendersHT = doc.getElementsByClass("matches_player_statistic").get(0).select("table").get(1);
                    org.jsoup.nodes.Element forwardsHT = doc.getElementsByClass("matches_player_statistic").get(0).select("table").get(2);
                    org.jsoup.nodes.Element defendersAT = doc.getElementsByClass("matches_player_statistic").get(0).select("table").get(4);
                    org.jsoup.nodes.Element forwardsAT = doc.getElementsByClass("matches_player_statistic").get(0).select("table").get(5);

                    for (int i = 1; i<defendersHT.select("tr").size(); i++){
                        this.homeFaceoffsWon += Integer.parseInt(defendersHT.select("tr").get(i).select("td").get(17).text().trim());
                    }
                    for (int i = 1; i<defendersAT.select("tr").size(); i++){
                        this.awayFaceoffsWon += Integer.parseInt(defendersAT.select("tr").get(i).select("td").get(17).text().trim());
                    }

                    for (int i = 1; i<forwardsHT.select("tr").size(); i++){
                        this.homeFaceoffsWon += Integer.parseInt(forwardsHT.select("tr").get(i).select("td").get(17).text().trim());
                    }
                    for (int i = 1; i<forwardsAT.select("tr").size(); i++){
                        int t = Integer.parseInt(forwardsAT.select("tr").get(i).select("td").get(17).text().trim());
                        this.awayFaceoffsWon += t;
                    }

                    String dateS = doc.getElementsByClass("games_title").get(0).selectFirst("p").text().split("\\. ")[1].split(",")[0];
                    this.date = Settings.getDateStringByTextDate(dateS);
                } catch (IndexOutOfBoundsException e){

                }

                break;
            }
            case "NHL":{
                String dateS = doc.getElementById("GameInfo").select("td").get(3).ownText();
                this.date = Settings.getDateForNHLgame(dateS.split(", ")[1] + "," + dateS.split(", ")[2]);


                this.homeTeam = Team.getNameFromFullName(doc.getElementById("VPenaltySummary").getAllElements().get(2).child(1).ownText());
                this.awayTeam = Team.getNameFromFullName(doc.getElementById("VPenaltySummary").getAllElements().get(2).child(0).ownText());
                String htShortName = Team.getShortName(homeTeam);
                String atShortName = Team.getShortName(awayTeam);
                this.homeScore = Integer.parseInt(doc.getElementById("Home").getAllElements().get(11).text());
                this.awayScore = Integer.parseInt(doc.getElementById("Visitor").getAllElements().get(11).text());

                this.homeScoreOT = 0;
                this.homeScoreBullits = 0;
                this.awayScoreOT = 0;
                this.awayScoreBullits = 0;
                this.homeShorthandedGoals = 0;
                this.awayShorthandedGoals = 0;
                int awayOTgoalsPP = 0;
                int homeOTgoalsPP = 0;

                Elements elements = doc.select("table").get(9).select("tr");
                for (int i=1; i<elements.size(); i++){
                    String text = elements.get(i).select("td").get(1).text();
                    if (text.contains("SO"))
                        if (homeScore > awayScore)
                            homeScoreBullits = 1;
                        else
                            awayScoreBullits = 1;
                    if (text.contains("OT"))
                        if (homeScore > awayScore){
                            homeScoreOT = 1;
                            if (elements.get(i).select("td").get(3).text().contains("PP"))
                                homeOTgoalsPP = 1;
                        }
                        else{
                            awayScoreOT = 1;
                            if (elements.get(i).select("td").get(3).text().contains("PP"))
                                awayOTgoalsPP = 1;
                        }

                    String shText = elements.get(i).select("td").get(3).text();
                    if (shText.contains("SH") && (elements.get(i).select("td").get(4).text().equals(htShortName)))
                        this.homeShorthandedGoals++;
                    if (shText.contains("SH") && (elements.get(i).select("td").get(4).text().equals(atShortName)))
                        this.awayShorthandedGoals++;
                }
                homeScore = homeScore - homeScoreOT - homeScoreBullits;
                awayScore = awayScore - awayScoreOT - awayScoreBullits;

                elements = doc.getElementById("PenaltySummary").select("table").get(0).select("td");
                int homeOTpenalties = 0;
                int awayOTpenalties = 0;
                int index = 1;

                boolean flag = false;
                while (!flag && index<elements.size()){
                    if (elements.get(index).ownText().contains("Penalty") && elements.get(index-1).ownText().contains("PIM"))
                        flag = true;
                    index++;
                }
                boolean awayEnded = false;
                while (!awayEnded && index<elements.size()){
                    String period = elements.get(index+1).text();
                    if (period.equals("1") || period.equals("2") || period.equals("3") || period.equals("OT")){
                        int penalty = Integer.parseInt(elements.get(index+8).text());
                        if (!period.equals("OT")){
                            this.awayPenaltyMinutes += penalty;
                            if (penalty == 2)
                                this.away2MinPenalties ++;
                            switch (period) {
                                case "1":
                                    if (penalty == 2)
                                        this.away2MinPenalties1stPeriod++;
                                    this.awayPenaltyMinutes1stPeriod+= penalty;
                                    break;
                                case "2":
                                    if (penalty == 2)
                                        this.away2MinPenalties2ndPeriod++;
                                    this.awayPenaltyMinutes2ndPeriod += penalty;
                                    break;
                                case "3":
                                    if (penalty == 2)
                                        this.away2MinPenalties3rdPeriod++;
                                    this.awayPenaltyMinutes3rdPeriod += penalty;
                                    break;
                            }
                        } else{
                            if (penalty == 2)
                                awayOTpenalties++;
                        }
                    } else {
                        awayEnded = true;
                    }
                    index += 10;
                }

                index -= 10;
                flag = false;
                while (!flag && index<elements.size()){
                    if (elements.get(index).ownText().contains("Penalty") && elements.get(index-1).ownText().contains("PIM"))
                        flag = true;
                    index++;
                }
                boolean homeEnded = false;
                while (!homeEnded && index<elements.size()){
                    String period = elements.get(index+1).text();
                    if (period.equals("1") || period.equals("2") || period.equals("3") || period.equals("OT")){
                        int penalty = Integer.parseInt(elements.get(index+8).text());
                        if (!period.equals("OT")){
                            this.homePenaltyMinutes += penalty;
                            if (penalty == 2)
                                this.home2MinPenalties ++;
                            switch (period) {
                                case "1":
                                    if (penalty == 2)
                                        this.home2MinPenalties1stPeriod++;
                                    this.homePenaltyMinutes1stPeriod += penalty;
                                    break;
                                case "2":
                                    if (penalty == 2)
                                        this.home2MinPenalties2ndPeriod++;
                                    this.homePenaltyMinutes2ndPeriod += penalty;
                                    break;
                                case "3":
                                    if (penalty == 2)
                                        this.home2MinPenalties3rdPeriod++;
                                    this.homePenaltyMinutes3rdPeriod += penalty;
                                    break;
                            }
                        } else {
                            if (penalty == 2)
                                homeOTpenalties++;
                        }
                    } else {
                        homeEnded = true;
                    }
                    index += 10;
                }

                index -= 10;
                flag = false;
                while (!flag && index<elements.size()){
                    if (elements.get(index).ownText().contains("Goals-Opp."))
                        flag = true;
                    index++;
                }
                index ++;
                this.awayGoalsInPP = Integer.parseInt(elements.get(index).ownText().split("-")[0]) - awayOTgoalsPP;
                this.awayNumberOfPP = Integer.parseInt(elements.get(index).ownText().split("-")[1].split("/")[0]) - homeOTpenalties;

                flag = false;
                while (!flag && index<elements.size()){
                    if (elements.get(index).ownText().contains("Goals-Opp."))
                        flag = true;
                    index++;
                }
                index ++;
                this.homeGoalsInPP = Integer.parseInt(elements.get(index).ownText().split("-")[0]) - homeOTgoalsPP;
                this.homeNumberOfPP = Integer.parseInt(elements.get(index).ownText().split("-")[1].split("/")[0]) - awayOTpenalties;

                elements = doc.getElementsContainingText("BY PERIOD").get(7).select("td");

                flag = false;
                index = 1;
                while (!flag && index<elements.size()){
                    if (elements.get(index).ownText().contains("PIM") && elements.get(index-1).ownText().contains("PN"))
                        flag = true;
                    index++;
                }
                this.awayScore1stPeriod = Integer.parseInt(elements.get(index+1).text());
                this.awayShotsOnTarget1stPeriod = Integer.parseInt(elements.get(index+2).text());
                index += 5;
                this.awayScore2ndPeriod = Integer.parseInt(elements.get(index+1).text());
                this.awayShotsOnTarget2ndPeriod = Integer.parseInt(elements.get(index+2).text());
                index += 5;
                this.awayScore3rdPeriod = Integer.parseInt(elements.get(index+1).text());
                this.awayShotsOnTarget3rdPeriod = Integer.parseInt(elements.get(index+2).text());
                index += 5;
                flag = false;
                while (!flag && index<elements.size()){
                    if (elements.get(index).ownText().contains("PIM") && elements.get(index-1).ownText().contains("PN"))
                        flag = true;
                    index++;
                }
                this.homeScore1stPeriod = Integer.parseInt(elements.get(index+1).text());
                this.homeShotsOnTarget1stPeriod = Integer.parseInt(elements.get(index+2).text());
                index += 5;
                this.homeScore2ndPeriod = Integer.parseInt(elements.get(index+1).text());
                this.homeShotsOnTarget2ndPeriod = Integer.parseInt(elements.get(index+2).text());
                index += 5;
                this.homeScore3rdPeriod = Integer.parseInt(elements.get(index+1).text());
                this.homeShotsOnTarget3rdPeriod = Integer.parseInt(elements.get(index+2).text());

                this.homeShotsOnTarget = this.homeShotsOnTarget1stPeriod + this.homeShotsOnTarget2ndPeriod + this.homeShotsOnTarget3rdPeriod;
                this.awayShotsOnTarget = this.awayShotsOnTarget1stPeriod + this.awayShotsOnTarget2ndPeriod + this.awayShotsOnTarget3rdPeriod;


                elements = doc2.select("td");
                int foundedTotals = 0;
                int homeTotals = 0;
                int awayTotals = 0;
                for (int i=0; i<elements.size(); i++){
                    if (elements.get(i).text().equals("TEAM TOTALS")){
                        foundedTotals ++;
                        if (foundedTotals == 1)
                            awayTotals = i;
                        if (foundedTotals == 2){
                            homeTotals = i;
                            break;
                        }
                    }
                }
                this.awayFaceoffsWon = Integer.parseInt(elements.get(awayTotals + 20).ownText());
                this.homeFaceoffsWon = Integer.parseInt(elements.get(homeTotals + 20).ownText());
                this.awayBlockedShots = Integer.parseInt(elements.get(awayTotals + 19).ownText());
                this.homeBlockedShots = Integer.parseInt(elements.get(homeTotals + 19).ownText());
                this.awayHits = Integer.parseInt(elements.get(awayTotals + 16).ownText());
                this.homeHits = Integer.parseInt(elements.get(homeTotals + 16).ownText());
                this.awayMissedShots = Integer.parseInt(elements.get(awayTotals + 15).ownText());
                this.homeMissedShots = Integer.parseInt(elements.get(homeTotals + 15).ownText());

                break;
            }
        }
        this.league = Team.getLeague(homeTeam);
        this.title = Team.getShortName(homeTeam) + Team.getShortName(awayTeam) + date;
    }

    public Match(String textS, String league, String url){
        this.homeTeam = "";
        this.awayTeam = "";
        this.homeScore = 0;
        this.awayScore = 0;
        this.homeScoreOT = 0;
        this.homeScoreBullits = 0;
        this.awayScoreOT = 0;
        this.awayScoreBullits = 0;
        this.homeShotsOnTarget = 0;
        this.awayShotsOnTarget = 0;
        this.homeNumberOfPP = 0;
        this.awayNumberOfPP = 0;
        this.homeGoalsInPP = 0;
        this.awayGoalsInPP = 0;
        this.homeShorthandedGoals = 0;
        this.awayShorthandedGoals = 0;
        this.homeFaceoffsWon = 0;
        this.awayFaceoffsWon = 0;
        this.homeBlockedShots = 0;
        this.awayBlockedShots = 0;
        this.homeHits = 0;
        this.awayHits = 0;
        this.home2MinPenalties = 0;
        this.away2MinPenalties = 0;
        this.homeScore1stPeriod = 0;
        this.homeScore2ndPeriod = 0;
        this.homeScore3rdPeriod = 0;
        this.awayScore1stPeriod = 0;
        this.awayScore2ndPeriod = 0;
        this.awayScore3rdPeriod = 0;
        this.homePenaltyMinutes = 0;
        this.awayPenaltyMinutes = 0;
        this.homeShotsOnTarget1stPeriod = 0;
        this.awayShotsOnTarget1stPeriod = 0;
        this.homeShotsOnTarget2ndPeriod = 0;
        this.awayShotsOnTarget2ndPeriod = 0;
        this.homeShotsOnTarget3rdPeriod = 0;
        this.awayShotsOnTarget3rdPeriod = 0;
        this.homePenaltyMinutes1stPeriod = 0;
        this.awayPenaltyMinutes1stPeriod = 0;
        this.homePenaltyMinutes2ndPeriod = 0;
        this.awayPenaltyMinutes2ndPeriod = 0;
        this.homePenaltyMinutes3rdPeriod = 0;
        this.awayPenaltyMinutes3rdPeriod = 0;
        this.home2MinPenalties1stPeriod = 0;
        this.away2MinPenalties1stPeriod = 0;
        this.home2MinPenalties2ndPeriod = 0;
        this.away2MinPenalties2ndPeriod = 0;
        this.home2MinPenalties3rdPeriod = 0;
        this.away2MinPenalties3rdPeriod = 0;
        this.homeTimeInAttack = 0;
        this.awayTimeInAttack = 0;
        this.homeTimeInAttack1stPeriod = 0;
        this.awayTimeInAttack1stPeriod = 0;
        this.homeTimeInAttack2ndPeriod = 0;
        this.awayTimeInAttack2ndPeriod = 0;
        this.homeTimeInAttack3rdPeriod = 0;
        this.awayTimeInAttack3rdPeriod = 0;
        this.date = "";
        this.url = url;

        Document text = Jsoup.parse(textS);

        switch (league){
            case "VHL":{
                try {
                    org.jsoup.nodes.Element element = text.getElementsByClass("matches_protocol_main").get(0);
                    this.homeTeam = Team.getNameFromFullName(element.getElementsByClass("first_row").get(0).child(0).child(0).text());
                    this.awayTeam = Team.getNameFromFullName(element.getElementsByClass("first_row").get(0).child(2).child(0).text());
                    String score = element.getElementsByClass("first_row").get(0).child(1).child(0).text().trim();
                    String scoreByPeriods = element.getElementsByClass("first_row").get(0).child(1).getElementsByClass("detail_count").get(0).text().replaceAll("\\(", "").replaceAll("\\)", "").trim();
                    int homeScore = 0;
                    int awayScore = 0;

                    if (score.contains("Б")) {
                        homeScore = Integer.parseInt((score.split(":")[0]));
                        awayScore = Integer.parseInt((score.split(":")[1]).split(" ")[0]);
                        if (homeScore > awayScore)
                            this.homeScoreBullits = 1;
                        else
                            this.awayScoreBullits = 1;
                    }
                    if (score.contains("ОТ")) {
                        homeScore = Integer.parseInt((score.split(":")[0]));
                        awayScore = Integer.parseInt((score.split(":")[1]).split(" ")[0]);
                        scoreByPeriods = scoreByPeriods.split(";")[0].trim();
                        if (homeScore > awayScore)
                            this.homeScoreOT = 1;
                        else
                            this.awayScoreOT = 1;
                    }
                    if (!score.contains("Б") && !score.contains("ОТ")){
                        homeScore = Integer.parseInt((score.split(":")[0]));
                        awayScore = Integer.parseInt((score.split(":")[1]));
                        scoreByPeriods = scoreByPeriods.split(";")[0].trim();
                    }

                    this.homeScore = homeScore - this.homeScoreOT - this.homeScoreBullits;
                    this.awayScore = awayScore - this.awayScoreOT - this.awayScoreBullits;


                    this.homeScore1stPeriod = Integer.parseInt(scoreByPeriods.split(" ")[0].split(":")[0]);
                    this.homeScore2ndPeriod = Integer.parseInt(scoreByPeriods.split(" ")[1].split(":")[0]);
                    this.homeScore3rdPeriod = Integer.parseInt(scoreByPeriods.split(" ")[2].split(":")[0]);
                    this.awayScore1stPeriod = Integer.parseInt(scoreByPeriods.split(" ")[0].split(":")[1]);
                    this.awayScore2ndPeriod = Integer.parseInt(scoreByPeriods.split(" ")[1].split(":")[1]);
                    this.awayScore3rdPeriod = Integer.parseInt(scoreByPeriods.split(" ")[2].split(":")[1]);

                    String period = "1";
                    this.homePenaltyMinutes = 0;
                    this.awayPenaltyMinutes = 0;
                    this.home2MinPenalties = 0;
                    this.away2MinPenalties = 0;
                    this.home2MinPenalties1stPeriod = 0;
                    this.away2MinPenalties1stPeriod = 0;
                    this.home2MinPenalties2ndPeriod = 0;
                    this.away2MinPenalties2ndPeriod = 0;
                    this.home2MinPenalties3rdPeriod = 0;
                    this.away2MinPenalties3rdPeriod = 0;
                    this.homePenaltyMinutes1stPeriod = 0;
                    this.awayPenaltyMinutes1stPeriod = 0;
                    this.homePenaltyMinutes2ndPeriod = 0;
                    this.awayPenaltyMinutes2ndPeriod = 0;
                    this.homePenaltyMinutes3rdPeriod = 0;
                    this.awayPenaltyMinutes3rdPeriod = 0;

                    try{
                        Elements trPenalties = text.getElementsByClass("tablesorter matches_penalty").get(0).select("tr");
                        for (int i=2; i<trPenalties.size(); i++){
                            org.jsoup.nodes.Element tdTime = trPenalties.get(i).select("td").get(0);
                            if (tdTime.toString().contains("time")){
                                String whosePenalty = "away";
                                int column = 5;
                                if (!trPenalties.get(i).select("td").get(0).text().equals("")){
                                    whosePenalty = "home";
                                    column = 0;
                                }
                                double time = Settings.getSeconds(trPenalties.get(i).select("td").get(column).text());
                                if (time < 1200)
                                    period = "1";
                                else
                                if (time < 2400)
                                    period = "2";
                                else
                                if (time < 3600)
                                    period = "3";

                                if (trPenalties.get(i).select("td").get(column+2).text().trim().equals("2")){
                                    switch (whosePenalty+period) {
                                        case "home1":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            home2MinPenalties ++;
                                            home2MinPenalties1stPeriod++;
                                            homePenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "home2":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            home2MinPenalties ++;
                                            home2MinPenalties2ndPeriod++;
                                            homePenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "home3":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            home2MinPenalties ++;
                                            home2MinPenalties3rdPeriod++;
                                            homePenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away1":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            away2MinPenalties ++;
                                            away2MinPenalties1stPeriod++;
                                            awayPenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away2":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            away2MinPenalties ++;
                                            away2MinPenalties2ndPeriod++;
                                            awayPenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away3":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            away2MinPenalties ++;
                                            away2MinPenalties3rdPeriod++;
                                            awayPenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                    }
                                } else{
                                    switch (whosePenalty+period) {
                                        case "home1":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            homePenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "home2":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            homePenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "home3":
                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            homePenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away1":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            awayPenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away2":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            awayPenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                        case "away3":
                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            awayPenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(i).select("td").get(column+2).text().trim());
                                            break;
                                    }
                                }
                            }

                            int tttt = 0;
                        }
                    } catch (java.lang.ArrayIndexOutOfBoundsException ignored){
                        System.out.println("Ошибка в парсинге штрафного времени");
                    }

                /*homePenaltyMinutes = 2;
                home2MinPenalties = 1;
                home2MinPenalties1stPeriod = 1;
                homePenaltyMinutes1stPeriod = 2;
                home2MinPenalties2ndPeriod = 0;
                homePenaltyMinutes2ndPeriod = 0;
                home2MinPenalties3rdPeriod = 0;
                homePenaltyMinutes3rdPeriod = 0;

                awayPenaltyMinutes = 6;
                away2MinPenalties = 3;
                away2MinPenalties1stPeriod = 2;
                awayPenaltyMinutes1stPeriod = 1;
                away2MinPenalties2ndPeriod = 0;
                awayPenaltyMinutes2ndPeriod = 0;
                away2MinPenalties3rdPeriod = 4;
                awayPenaltyMinutes3rdPeriod = 2;*/

                    int index = 1;
                    for (int i=0; i<text.getElementsByClass("tablesorter matches_goals").size(); i++ ){
                        if (text.getElementsByClass("tablesorter matches_goals").get(i).select("tr").get(0).toString().
                                contains("Количество полученных численных преимуществ")){
                            index = i;
                        }

                    }
                    org.jsoup.nodes.Element tablePP1 = text.getElementsByClass("tablesorter matches_goals").get(index).select("tr").get(1);
                    org.jsoup.nodes.Element tablePP2 = text.getElementsByClass("tablesorter matches_goals").get(index).select("tr").get(2);
                    if (tablePP1.select("td").get(0).text().equals(Team.getFullName(homeTeam))){
                        this.homeNumberOfPP = Integer.parseInt(tablePP1.select("td").get(2).ownText());
                        this.awayNumberOfPP = Integer.parseInt(tablePP2.select("td").get(2).ownText());
                        this.homeGoalsInPP = Integer.parseInt(tablePP1.select("td").get(3).ownText());
                        this.awayGoalsInPP = Integer.parseInt(tablePP2.select("td").get(3).ownText());
                        this.homeShorthandedGoals = Integer.parseInt(tablePP1.select("td").get(9).ownText());
                        this.awayShorthandedGoals = Integer.parseInt(tablePP2.select("td").get(9).ownText());
                    }
                    else {
                        this.awayNumberOfPP = Integer.parseInt(tablePP1.select("td").get(2).ownText());
                        this.homeNumberOfPP = Integer.parseInt(tablePP2.select("td").get(2).ownText());
                        this.awayGoalsInPP = Integer.parseInt(tablePP1.select("td").get(3).ownText());
                        this.homeGoalsInPP = Integer.parseInt(tablePP2.select("td").get(3).ownText());
                        this.awayShorthandedGoals = Integer.parseInt(tablePP1.select("td").get(9).ownText());
                        this.homeShorthandedGoals = Integer.parseInt(tablePP2.select("td").get(9).ownText());
                    }

                    this.homeMissedShots = 0;
                    this.awayMissedShots = 0;
                    this.homeFaceoffsWon = 0;
                    this.awayFaceoffsWon = 0;
                    this.homeBlockedShots = 0;
                    this.awayBlockedShots = 0;
                    this.homeHits = 0;
                    this.awayHits = 0;

                    int size = text.getElementsByClass("tablesorter matches_bullits").size()-1;
                    if (size >= 0){
                        org.jsoup.nodes.Element shotsTable = text.getElementsByClass("tablesorter matches_bullits").get(size);
                        Elements tableRows = shotsTable.select("tr");

                        this.homeShotsOnTarget1stPeriod = Integer.parseInt(tableRows.get(3).select("td").get(2).text().trim());
                        this.homeShotsOnTarget2ndPeriod = Integer.parseInt(tableRows.get(4).select("td").get(2).text().trim());
                        this.homeShotsOnTarget3rdPeriod = Integer.parseInt(tableRows.get(5).select("td").get(2).text().trim());
                        this.awayShotsOnTarget1stPeriod = Integer.parseInt(tableRows.get(3).select("td").get(6).text().trim());
                        this.awayShotsOnTarget2ndPeriod = Integer.parseInt(tableRows.get(4).select("td").get(6).text().trim());
                        this.awayShotsOnTarget3rdPeriod = Integer.parseInt(tableRows.get(5).select("td").get(6).text().trim());

                        this.homeShotsOnTarget = homeShotsOnTarget1stPeriod + homeShotsOnTarget2ndPeriod + homeShotsOnTarget3rdPeriod;
                        this.awayShotsOnTarget = awayShotsOnTarget1stPeriod + awayShotsOnTarget2ndPeriod + awayShotsOnTarget3rdPeriod;
                    }


                    org.jsoup.nodes.Element defendersHT = text.getElementsByClass("matches_player_statistic").get(0).select("table").get(1);
                    org.jsoup.nodes.Element forwardsHT = text.getElementsByClass("matches_player_statistic").get(0).select("table").get(2);
                    org.jsoup.nodes.Element defendersAT = text.getElementsByClass("matches_player_statistic").get(0).select("table").get(4);
                    org.jsoup.nodes.Element forwardsAT = text.getElementsByClass("matches_player_statistic").get(0).select("table").get(5);

                    for (int i = 1; i<defendersHT.select("tr").size(); i++){
                        this.homeFaceoffsWon += Integer.parseInt(defendersHT.select("tr").get(i).select("td").get(17).text().trim());
                    }
                    for (int i = 1; i<defendersAT.select("tr").size(); i++){
                        this.awayFaceoffsWon += Integer.parseInt(defendersAT.select("tr").get(i).select("td").get(17).text().trim());
                    }

                    for (int i = 1; i<forwardsHT.select("tr").size(); i++){
                        this.homeFaceoffsWon += Integer.parseInt(forwardsHT.select("tr").get(i).select("td").get(17).text().trim());
                    }
                    for (int i = 1; i<forwardsAT.select("tr").size(); i++){
                        int t = Integer.parseInt(forwardsAT.select("tr").get(i).select("td").get(17).text().trim());
                        this.awayFaceoffsWon += t;
                    }

                    String dateS = text.getElementsByClass("games_title").get(0).selectFirst("p").text().split("\\. ")[1].split(",")[0];
                    this.date = Settings.getDateStringByTextDate(dateS);
                } catch (IndexOutOfBoundsException e){

                }

                break;
            }
        }
        this.league = Team.getLeague(homeTeam);
        this.title = Team.getShortName(homeTeam) + Team.getShortName(awayTeam) + date;

    }


//    public Match(String sDoc, String sDoc2, String league){
//        this.homeTeam = "";
//        this.awayTeam = "";
//        this.homeScore = 0;
//        this.awayScore = 0;
//        this.homeScoreOT = 0;
//        this.homeScoreBullits = 0;
//        this.awayScoreOT = 0;
//        this.awayScoreBullits = 0;
//        this.homeShotsOnTarget = 0;
//        this.awayShotsOnTarget = 0;
//        this.homeNumberOfPP = 0;
//        this.awayNumberOfPP = 0;
//        this.homeGoalsInPP = 0;
//        this.awayGoalsInPP = 0;
//        this.homeShorthandedGoals = 0;
//        this.awayShorthandedGoals = 0;
//        this.homeFaceoffsWon = 0;
//        this.awayFaceoffsWon = 0;
//        this.homeBlockedShots = 0;
//        this.awayBlockedShots = 0;
//        this.homeHits = 0;
//        this.awayHits = 0;
//        this.home2MinPenalties = 0;
//        this.away2MinPenalties = 0;
//        this.homeScore1stPeriod = 0;
//        this.homeScore2ndPeriod = 0;
//        this.homeScore3rdPeriod = 0;
//        this.awayScore1stPeriod = 0;
//        this.awayScore2ndPeriod = 0;
//        this.awayScore3rdPeriod = 0;
//        this.homePenaltyMinutes = 0;
//        this.awayPenaltyMinutes = 0;
//        this.homeShotsOnTarget1stPeriod = 0;
//        this.awayShotsOnTarget1stPeriod = 0;
//        this.homeShotsOnTarget2ndPeriod = 0;
//        this.awayShotsOnTarget2ndPeriod = 0;
//        this.homeShotsOnTarget3rdPeriod = 0;
//        this.awayShotsOnTarget3rdPeriod = 0;
//        this.homePenaltyMinutes1stPeriod = 0;
//        this.awayPenaltyMinutes1stPeriod = 0;
//        this.homePenaltyMinutes2ndPeriod = 0;
//        this.awayPenaltyMinutes2ndPeriod = 0;
//        this.homePenaltyMinutes3rdPeriod = 0;
//        this.awayPenaltyMinutes3rdPeriod = 0;
//        this.home2MinPenalties1stPeriod = 0;
//        this.away2MinPenalties1stPeriod = 0;
//        this.home2MinPenalties2ndPeriod = 0;
//        this.away2MinPenalties2ndPeriod = 0;
//        this.home2MinPenalties3rdPeriod = 0;
//        this.away2MinPenalties3rdPeriod = 0;
//        this.homeTimeInAttack = 0;
//        this.awayTimeInAttack = 0;
//        this.homeTimeInAttack1stPeriod = 0;
//        this.awayTimeInAttack1stPeriod = 0;
//        this.homeTimeInAttack2ndPeriod = 0;
//        this.awayTimeInAttack2ndPeriod = 0;
//        this.homeTimeInAttack3rdPeriod = 0;
//        this.awayTimeInAttack3rdPeriod = 0;
//        this.date = "";
//
//        switch (league){
//            case "KHL":{
//                Document doc = new Document(sDoc);
//                Document doc2 = new Document(sDoc2);
//
//                try {
//                    doc = (Document) DocumentBuilderFactory
//                            .newInstance()
//                            .newDocumentBuilder()
//                            .parse(new ByteArrayInputStream(sDoc.getBytes()));
//                } catch (SAXException | IOException | ParserConfigurationException e) {
//                    e.printStackTrace();
//                }
//
//                org.jsoup.nodes.Element element = doc.getElementsByClass("b-wide_block b-wide_tile m-wide_tile").get(0);
//
//                Elements el = element.select("h3");
//                this.homeTeam = Team.getNameFromFullName(el.get(0).ownText());
//                this.awayTeam = Team.getNameFromFullName(el.get(2).ownText());
//                String score = el.get(1).wholeText().replaceAll(" ", "").trim();
//                int homeScore = 0;
//                int awayScore = 0;
//
//                if (score.contains("Б")) {
//                    homeScore = Integer.parseInt((score.split("–")[0]));
//                    awayScore = Integer.parseInt((score.split("–")[1]).split(" ")[0]);
//                    if (homeScore > awayScore)
//                        this.homeScoreBullits = 1;
//                    else
//                        this.awayScoreBullits = 1;
//                }
//                if (score.contains("OT")) {
//                    homeScore = Integer.parseInt((score.split("–")[0]));
//                    awayScore = Integer.parseInt((score.split("–")[1]).split(" ")[0]);
//                    if (homeScore > awayScore)
//                        this.homeScoreOT = 1;
//                    else
//                        this.awayScoreOT = 1;
//                }
//                if (!score.contains("Б") && !score.contains("OT")){
//                    homeScore = Integer.parseInt((score.split("–")[0]));
//                    awayScore = Integer.parseInt((score.split("–")[1]));
//                }
//
//                this.homeScore = homeScore - this.homeScoreOT - this.homeScoreBullits;
//                this.awayScore = awayScore - this.awayScoreOT - this.awayScoreBullits;
//
//                el = element.select("li");
//                this.date = el.get(0).text().split(" ")[1];
//
//                String byPeriods = element.getElementsByClass("b-period_score").text();
//                this.homeScore1stPeriod = Integer.parseInt(byPeriods.split(" ")[0].split(":")[0]);
//                this.awayScore1stPeriod = Integer.parseInt(byPeriods.split(" ")[0].split(":")[1]);
//                this.homeScore2ndPeriod = Integer.parseInt(byPeriods.split(" ")[1].split(":")[0]);
//                this.awayScore2ndPeriod = Integer.parseInt(byPeriods.split(" ")[1].split(":")[1]);
//                this.homeScore3rdPeriod = Integer.parseInt(byPeriods.split(" ")[2].split(":")[0]);
//                this.awayScore3rdPeriod = Integer.parseInt(byPeriods.split(" ")[2].split(":")[1]);
//
//                int position = -1;
//                boolean flag3per = false;
//                String period = "1";
//                this.homePenaltyMinutes = 0;
//                this.awayPenaltyMinutes = 0;
//                this.home2MinPenalties = 0;
//                this.away2MinPenalties = 0;
//                this.home2MinPenalties1stPeriod = 0;
//                this.away2MinPenalties1stPeriod = 0;
//                this.home2MinPenalties2ndPeriod = 0;
//                this.away2MinPenalties2ndPeriod = 0;
//                this.home2MinPenalties3rdPeriod = 0;
//                this.away2MinPenalties3rdPeriod = 0;
//                this.homePenaltyMinutes1stPeriod = 0;
//                this.awayPenaltyMinutes1stPeriod = 0;
//                this.homePenaltyMinutes2ndPeriod = 0;
//                this.awayPenaltyMinutes2ndPeriod = 0;
//                this.homePenaltyMinutes3rdPeriod = 0;
//                this.awayPenaltyMinutes3rdPeriod = 0;
//
//                try{
//                    Elements trPenalties = doc.getElementsByClass("dataTable nowrap stripe compact hover row-border no-footer m-table_small").get(doc.getElementsByClass("dataTable nowrap stripe compact hover row-border no-footer m-table_small").size()-1).select("tbody").get(0).select("tr");
//                    while ((position < trPenalties.size()) && (!flag3per)){
//                        position ++;
//                        if (trPenalties.get(position).wholeText().contains("Всего за первый период"))
//                            period = "2";
//                        if (trPenalties.get(position).wholeText().contains("Всего за второй период"))
//                            period = "3";
//                        if (!trPenalties.get(position).wholeText().contains("Всего за третий период") &&!trPenalties.get(position).wholeText().contains("Итого") &&!trPenalties.get(position).wholeText().contains(":6")){
//                            if (trPenalties.get(position).toString().contains("odd") || trPenalties.get(position).toString().contains("even")){
//                                String whosePenalty = "away";
//                                int r = 1;
//                                if (!trPenalties.get(position).select("td").get(0).text().equals("")){
//                                    whosePenalty = "home";
//                                    r = 0;
//                                }
//                                double time = Settings.getSeconds(trPenalties.get(position).select("td").get(r).text());
//                                if (time < 1200)
//                                    period = "1";
//                                else
//                                if (time < 2400)
//                                    period = "2";
//                                else
//                                if (time < 3600)
//                                    period = "3";
//
//                                if (trPenalties.get(position).select("td").get(r+2).text().trim().equals("2")){
//                                    switch (whosePenalty+period) {
//                                        case "home1":
//                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            home2MinPenalties ++;
//                                            home2MinPenalties1stPeriod++;
//                                            homePenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                        case "home2":
//                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            home2MinPenalties ++;
//                                            home2MinPenalties2ndPeriod++;
//                                            homePenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                        case "home3":
//                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            home2MinPenalties ++;
//                                            home2MinPenalties3rdPeriod++;
//                                            homePenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                        case "away1":
//                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            away2MinPenalties ++;
//                                            away2MinPenalties1stPeriod++;
//                                            awayPenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                        case "away2":
//                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            away2MinPenalties ++;
//                                            away2MinPenalties2ndPeriod++;
//                                            awayPenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                        case "away3":
//                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            away2MinPenalties ++;
//                                            away2MinPenalties3rdPeriod++;
//                                            awayPenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                    }
//                                } else{
//                                    switch (whosePenalty+period) {
//                                        case "home1":
//                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            homePenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                        case "home2":
//                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            homePenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                        case "home3":
//                                            homePenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            homePenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                        case "away1":
//                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            awayPenaltyMinutes1stPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                        case "away2":
//                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            awayPenaltyMinutes2ndPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                        case "away3":
//                                            awayPenaltyMinutes += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            awayPenaltyMinutes3rdPeriod += Integer.parseInt(trPenalties.get(position).select("td").get(r+2).text().trim());
//                                            break;
//                                    }
//                                }
//
//                            }
//
//                        } else
//                            flag3per = true;
//                    }
//                } catch (java.lang.ArrayIndexOutOfBoundsException ignored){
//                }
//
//                org.jsoup.nodes.Element tablePP1 = doc.getElementsByClass("dataTable stripe compact row-border hl no-footer rc m-table_small").get(0).select("tr").get(1);
//                org.jsoup.nodes.Element tablePP2 = doc.getElementsByClass("dataTable stripe compact row-border hl no-footer rc m-table_small").get(0).select("tr").get(2);
//                if (tablePP1.select("td").get(0).ownText().equals(Team.getFullName(homeTeam))){
//                    this.homeNumberOfPP = Integer.parseInt(tablePP1.select("td").get(2).ownText());
//                    this.awayNumberOfPP = Integer.parseInt(tablePP2.select("td").get(2).ownText());
//                    this.homeGoalsInPP = Integer.parseInt(tablePP1.select("td").get(3).ownText());
//                    this.awayGoalsInPP = Integer.parseInt(tablePP2.select("td").get(3).ownText());
//                    this.homeShorthandedGoals = Integer.parseInt(tablePP1.select("td").get(5).ownText());
//                    this.awayShorthandedGoals = Integer.parseInt(tablePP2.select("td").get(5).ownText());
//                }
//                else {
//                    this.awayNumberOfPP = Integer.parseInt(tablePP1.select("td").get(2).ownText());
//                    this.homeNumberOfPP = Integer.parseInt(tablePP2.select("td").get(2).ownText());
//                    this.awayGoalsInPP = Integer.parseInt(tablePP1.select("td").get(3).ownText());
//                    this.homeGoalsInPP = Integer.parseInt(tablePP2.select("td").get(3).ownText());
//                    this.awayShorthandedGoals = Integer.parseInt(tablePP1.select("td").get(5).ownText());
//                    this.homeShorthandedGoals = Integer.parseInt(tablePP2.select("td").get(5).ownText());
//                }
//
//
//                int totalShotsHT = 0;
//                int totalShotsAT = 0;
//                this.homeFaceoffsWon = 0;
//                this.awayFaceoffsWon = 0;
//                this.homeBlockedShots = 0;
//                this.awayBlockedShots = 0;
//                this.homeHits = 0;
//                this.awayHits = 0;
//
//                org.jsoup.nodes.Element period1 = null;
//                org.jsoup.nodes.Element period2 = null;
//                org.jsoup.nodes.Element period3 = null;
//
//                if (doc2.getElementsContainingText("Статистика 2-го периода:").size()>0){
//                    period1 = doc2.getElementsContainingText("Статистика 1-го периода:").get(11);
//                    period2 = doc2.getElementsContainingText("Статистика 2-го периода:").get(11);
//                    period3 = doc2.getElementsContainingText("Статистика 3-го периода:").get(11);
//                }
//                if (doc2.getElementsContainingText("Stats of 1st period").size()>0){
//                    period1 = doc2.getElementsContainingText("Stats of 1st period").get(11);
//                    period2 = doc2.getElementsContainingText("Stats of 2nd period").get(11);
//                    period3 = doc2.getElementsContainingText("Stats of 3rd period").get(11);
//                }
//                String period1stats = period1.ownText();
//                if (doc2.getElementsContainingText("Статистика 1-го периода:").size()>0){
//                    totalShotsHT += Integer.parseInt(period1stats.split(" ; ")[0].split(": ")[1].split("-")[0]);
//                    totalShotsAT += Integer.parseInt(period1stats.split(" ; ")[0].split(": ")[1].split("-")[1]);
//                }
//                if (doc2.getElementsContainingText("Stats of 1st period").size()>0){
//                    totalShotsHT += Integer.parseInt(period1stats.split(" ; ")[0].split("Shots: ")[1].split("-")[0]);
//                    totalShotsAT += Integer.parseInt(period1stats.split(" ; ")[0].split("Shots: ")[1].split("-")[1]);
//                }
//                this.homeShotsOnTarget1stPeriod = Integer.parseInt(period1stats.split(" ; ")[1].split(": ")[1].split("-")[0]);
//                this.awayShotsOnTarget1stPeriod = Integer.parseInt(period1stats.split(" ; ")[1].split(": ")[1].split("-")[1]);
//                homeFaceoffsWon += Integer.parseInt(period1stats.split(" ; ")[3].split(": ")[1].split("-")[0]);
//                awayFaceoffsWon += Integer.parseInt(period1stats.split(" ; ")[3].split(": ")[1].split("-")[1]);
//                homeBlockedShots += Integer.parseInt(period1stats.split(" ; ")[4].split(": ")[1].split("-")[0]);
//                awayBlockedShots += Integer.parseInt(period1stats.split(" ; ")[4].split(": ")[1].split("-")[1]);
//                homeHits += Integer.parseInt(period1stats.split(" ; ")[5].split(": ")[1].split("-")[0]);
//                awayHits += Integer.parseInt(period1stats.split(" ; ")[5].split(": ")[1].split("-")[1]);
//                this.homeTimeInAttack1stPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period1stats.split(" ; ")[6].split(": ")[1].split("-")[0])));
//                this.awayTimeInAttack1stPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period1stats.split(" ; ")[6].split(": ")[1].split("-")[1])));
//
//                String period2stats = period2.ownText();
//                if (doc2.getElementsContainingText("Статистика 2-го периода:").size()>0){
//                    totalShotsHT += Integer.parseInt(period2stats.split(" ; ")[0].split(": ")[1].split("-")[0]);
//                    totalShotsAT += Integer.parseInt(period2stats.split(" ; ")[0].split(": ")[1].split("-")[1]);
//                }
//                if (doc2.getElementsContainingText("Stats of 2nd period").size()>0){
//                    totalShotsHT += Integer.parseInt(period2stats.split(" ; ")[0].split("Shots: ")[1].split("-")[0]);
//                    totalShotsAT += Integer.parseInt(period2stats.split(" ; ")[0].split("Shots: ")[1].split("-")[1]);
//                }
//                this.homeShotsOnTarget2ndPeriod = Integer.parseInt(period2stats.split(" ; ")[1].split(": ")[1].split("-")[0]);
//                this.awayShotsOnTarget2ndPeriod = Integer.parseInt(period2stats.split(" ; ")[1].split(": ")[1].split("-")[1]);
//                homeFaceoffsWon += Integer.parseInt(period2stats.split(" ; ")[3].split(": ")[1].split("-")[0]);
//                awayFaceoffsWon += Integer.parseInt(period2stats.split(" ; ")[3].split(": ")[1].split("-")[1]);
//                homeBlockedShots += Integer.parseInt(period2stats.split(" ; ")[4].split(": ")[1].split("-")[0]);
//                awayBlockedShots += Integer.parseInt(period2stats.split(" ; ")[4].split(": ")[1].split("-")[1]);
//                homeHits += Integer.parseInt(period2stats.split(" ; ")[5].split(": ")[1].split("-")[0]);
//                awayHits += Integer.parseInt(period2stats.split(" ; ")[5].split(": ")[1].split("-")[1]);
//                this.homeTimeInAttack2ndPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period2stats.split(" ; ")[6].split(": ")[1].split("-")[0])));
//                this.awayTimeInAttack2ndPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period2stats.split(" ; ")[6].split(": ")[1].split("-")[1])));
//
//                String period3stats = period3.ownText();
//                if (doc2.getElementsContainingText("Статистика 3-го периода:").size()>0){
//                    totalShotsHT += Integer.parseInt(period3stats.split(" ; ")[0].split(": ")[1].split("-")[0]);
//                    totalShotsAT += Integer.parseInt(period3stats.split(" ; ")[0].split(": ")[1].split("-")[1]);
//                }
//                if (doc2.getElementsContainingText("Stats of 3rd period").size()>0){
//                    totalShotsHT += Integer.parseInt(period3stats.split(" ; ")[0].split("Shots: ")[1].split("-")[0]);
//                    totalShotsAT += Integer.parseInt(period3stats.split(" ; ")[0].split("Shots: ")[1].split("-")[1]);
//                }
//                this.homeShotsOnTarget3rdPeriod = Integer.parseInt(period3stats.split(" ; ")[1].split(": ")[1].split("-")[0]);
//                this.awayShotsOnTarget3rdPeriod = Integer.parseInt(period3stats.split(" ; ")[1].split(": ")[1].split("-")[1]);
//                homeFaceoffsWon += Integer.parseInt(period3stats.split(" ; ")[3].split(": ")[1].split("-")[0]);
//                awayFaceoffsWon += Integer.parseInt(period3stats.split(" ; ")[3].split(": ")[1].split("-")[1]);
//                homeBlockedShots += Integer.parseInt(period3stats.split(" ; ")[4].split(": ")[1].split("-")[0]);
//                awayBlockedShots += Integer.parseInt(period3stats.split(" ; ")[4].split(": ")[1].split("-")[1]);
//                homeHits += Integer.parseInt(period3stats.split(" ; ")[5].split(": ")[1].split("-")[0]);
//                awayHits += Integer.parseInt(period3stats.split(" ; ")[5].split(": ")[1].split("-")[1]);
//                this.homeTimeInAttack3rdPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period3stats.split(" ; ")[6].split(": ")[1].split("-")[0])));
//                this.awayTimeInAttack3rdPeriod = Integer.parseInt(String.valueOf(Settings.getSeconds(period3stats.split(" ; ")[6].split(": ")[1].split("-")[1])));
//
//                this.homeShotsOnTarget = homeShotsOnTarget1stPeriod + homeShotsOnTarget2ndPeriod + homeShotsOnTarget3rdPeriod;
//                this.awayShotsOnTarget = awayShotsOnTarget1stPeriod + awayShotsOnTarget2ndPeriod + awayShotsOnTarget3rdPeriod;
//                this.homeTimeInAttack = homeTimeInAttack1stPeriod + homeTimeInAttack2ndPeriod + homeTimeInAttack3rdPeriod;
//                this.awayTimeInAttack = awayTimeInAttack1stPeriod + awayTimeInAttack2ndPeriod + awayTimeInAttack3rdPeriod;
//                this.homeMissedShots = totalShotsHT - homeShotsOnTarget - awayBlockedShots;
//                this.awayMissedShots = totalShotsAT - awayShotsOnTarget - homeBlockedShots;
//
//                break;
//
//            }
//        }
//    }


    public static Match getMatchFromFile(String homeTeam, String awayTeam){
        String path = Settings.getPathToDatabase() + Team.getLeague(homeTeam) + "/Matches/";
        String fileName = path + Team.getShortName(homeTeam) + Team.getShortName(awayTeam) + ".xml";
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Match.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Match) un.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public void pushMatchToFile(){
        String curSeason = Settings.getCurrentSeason(Team.getLeague(this.homeTeam));
        String fileName = Settings.getPathToDatabase() + curSeason + "/" + Team.getLeague(this.homeTeam) + "/Matches/" + Team.getShortName(this.homeTeam) + Team.getShortName(this.awayTeam) + this.date + ".xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Match.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
            ////////////////////////////////////////////////////РАСКОММЕНТИТЬ ПОСЛЕ ОТЛАДКИ
            try {
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/hockey/database/" + curSeason + "/" + Team.getLeague(this.homeTeam) + "/Matches/" + Team.getShortName(this.homeTeam) + Team.getShortName(this.awayTeam) + this.date + ".xml" , fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }*/

    public void pushMatchToFile(boolean addToLeagueList){
        String curSeason = Settings.getCurrentSeason(Team.getLeague(this.homeTeam));
        String fileName = Settings.getPathToDatabase() + curSeason + "/" + this.league + "/Matches/" + title + ".xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Match.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
            if (addToLeagueList)
                League.addMatchToList(league, curSeason, title);

            try {
                FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/hockey/database/" + curSeason + "/" + this.league + "/Matches/" + title + ".xml" , fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Match getMatchFromFileByName(String matchName){
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Match.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Match) un.unmarshal(new File(matchName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
