package sample;

import java.util.ArrayList;

public class Selector {
    public ArrayList<Match> listOfMatches;
    public ArrayList<ArrayList<String>> pList;
    public ArrayList<String> listForConfrontation;
    public String[] params = {"Победы", "Победы в ОТ/Бул.", "Поражения", "Поражения в ОТ/Бул.", "Очки", "Голы в основное время",
            "Голы с учетом ОТ(Буллитов)", "Броски в створ","Кол-во большинства", "Голы в большинстве", "Голы в меньшинстве",
            "Вбрасывания", "Блокированные броски", "Силовые приемы", "Минуты штрафа", "Кол-во двухминутных удалений", "Форма",
            "Голы в первом периоде", "Голы во втором периоде", "Голы в третьем периоде", "Время в атаке", "Броски мимо"};

    public Selector(){
        pList = new ArrayList<>();
        listOfMatches = new ArrayList<>();
        listForConfrontation = new ArrayList<>();
    }

    public void getListOfMatches(String leagueName, String teamName, String allOrHomeOrAway, String season, String lastOrFullSeason){
        if ((!leagueName.contains("Лига"))&&(!teamName.contains("Команда"))){

            String path = Settings.getPathToDatabase() + season + "/" + leagueName + "/Matches/";
            Team team = Team.getTeamFromFileWithSeason(teamName, season);

            if (lastOrFullSeason.contains("Последние")){
                int count = Integer.parseInt(lastOrFullSeason.split(" ")[1]);
                int index = team.matchList.size()-1;
                while (count>0&&index>=0){
                    if (allOrHomeOrAway.contains("Все")){
                        listOfMatches.add(0, Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        count--;
                    }
                    if (allOrHomeOrAway.contains("Дома") && team.matchList.get(index).substring(0,3).equals(Team.getShortName(teamName))){
                        listOfMatches.add(0, Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        count--;
                    }
                    if (allOrHomeOrAway.contains("На выезде") && team.matchList.get(index).substring(3,6).equals(Team.getShortName(teamName))){
                        listOfMatches.add(0, Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        count--;
                    }
                    index --;
                }
            } else {
                if (allOrHomeOrAway.contains("Все")){
                    for (int index=0; index<team.matchList.size(); index++){
                        listOfMatches.add(Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                    }
                }
                if (allOrHomeOrAway.contains("Дома")){
                    for (int index=0; index<team.matchList.size(); index++){
                        if (team.matchList.get(index).substring(0,3).equals(Team.getShortName(teamName))){
                            listOfMatches.add(Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        }
                    }
                }
                if (allOrHomeOrAway.contains("На выезде")){
                    for (int index=0; index<team.matchList.size(); index++){
                        if (team.matchList.get(index).substring(3,6).equals(Team.getShortName(teamName))){
                            listOfMatches.add(Match.getMatchFromFileByName(path + team.matchList.get(index)+ ".xml"));
                        }
                    }
                }
            }
        }
    }

    public void getPList(ArrayList<Match> listOfMatches, String teamName, String season){
        if (listOfMatches.size()>0){
            double[][] paramsD = new double[params.length][2];
            String forma = "";
            for (int i=0; i<listOfMatches.size(); i++){
                Match m = listOfMatches.get(i);
                String league = Team.getLeague(teamName);
                if (teamName.equals(m.homeTeam)){
                    if (m.homeScore == m.awayScore){
                        if (m.homeScoreOT>m.awayScoreOT||m.homeScoreBullits>m.awayScoreBullits){
                            paramsD[1][0] += 1;
                            paramsD[3][1] += 1;
                            paramsD[4][0] += 2;
                            forma = forma + "w";
                        } else {
                            paramsD[1][1] += 1;
                            paramsD[3][0] += 1;
                            paramsD[4][0] += 1;
                            forma = forma + "l";
                        }
                    }
                    else if (m.homeScore > m.awayScore){
                        paramsD[0][0] += 1;
                        paramsD[2][1] += 1;
                        if (league.equals("KHL")&&season.contains("17-18")){
                            paramsD[4][0] += 3;
                        } else
                            paramsD[4][0] += 2;
                        forma = forma + "W";
                    }
                    else {
                        paramsD[0][1] += 1;
                        paramsD[2][0] += 1;
                        forma = forma + "L";
                    }

                    paramsD[5][0] += m.homeScore;
                    paramsD[5][1] += m.awayScore;
                    paramsD[6][0] += m.homeScore + m.homeScoreOT + m.homeScoreBullits;
                    paramsD[6][1] += m.awayScore + m.awayScoreOT + m.awayScoreBullits;
                    paramsD[7][0] += m.homeShotsOnTarget;
                    paramsD[7][1] += m.awayShotsOnTarget;
                    paramsD[8][0] += m.homeNumberOfPP;
                    paramsD[8][1] += m.awayNumberOfPP;
                    paramsD[9][0] += m.homeGoalsInPP;
                    paramsD[9][1] += m.awayGoalsInPP;
                    paramsD[10][0] += m.homeShorthandedGoals;
                    paramsD[10][1] += m.awayShorthandedGoals;
                    paramsD[11][0] += m.homeFaceoffsWon;
                    paramsD[11][1] += m.awayFaceoffsWon;
                    paramsD[12][0] += m.homeBlockedShots;
                    paramsD[12][1] += m.awayBlockedShots;
                    paramsD[13][0] += m.homeHits;
                    paramsD[13][1] += m.awayHits;
                    paramsD[14][0] += m.homePenaltyMinutes;
                    paramsD[14][1] += m.awayPenaltyMinutes;
                    paramsD[15][0] += m.home2MinPenalties;
                    paramsD[15][1] += m.away2MinPenalties;
                    paramsD[17][0] += m.homeScore1stPeriod;
                    paramsD[17][1] += m.awayScore1stPeriod;
                    paramsD[18][0] += m.homeScore2ndPeriod;
                    paramsD[18][1] += m.awayScore2ndPeriod;
                    paramsD[19][0] += m.homeScore3rdPeriod;
                    paramsD[19][1] += m.awayScore3rdPeriod;
                    paramsD[20][0] += m.homeTimeInAttack;
                    paramsD[20][1] += m.awayTimeInAttack;
                    paramsD[21][0] += m.homeMissedShots;
                    paramsD[21][1] += m.awayMissedShots;


                } else if (teamName.equals(m.awayTeam)){
                    if (m.homeScore == m.awayScore){
                        if (m.awayScoreOT>m.homeScoreOT||m.awayScoreBullits>m.homeScoreBullits){
                            paramsD[1][0] += 1;
                            paramsD[3][1] += 1;
                            paramsD[4][0] += 2;
                            forma = forma + "w";
                        } else {
                            paramsD[1][1] += 1;
                            paramsD[3][0] += 1;
                            paramsD[4][0] += 1;
                            forma = forma + "l";
                        }
                    }
                    else if (m.awayScore > m.homeScore){
                        paramsD[0][0] += 1;
                        paramsD[2][1] += 1;
                        if (league.equals("KHL")&&season.contains("17-18")){
                            paramsD[4][0] += 3;
                        } else
                            paramsD[4][0] += 2;
                        forma = forma + "W";
                    }
                    else {
                        paramsD[0][1] += 1;
                        paramsD[2][0] += 1;
                        forma = forma + "L";
                    }

                    paramsD[5][0] += m.awayScore;
                    paramsD[5][1] += m.homeScore;
                    paramsD[6][0] += m.awayScore + m.awayScoreOT + m.awayScoreBullits;
                    paramsD[6][1] += m.homeScore + m.homeScoreOT + m.homeScoreBullits;
                    paramsD[7][0] += m.awayShotsOnTarget;
                    paramsD[7][1] += m.homeShotsOnTarget;
                    paramsD[8][0] += m.awayNumberOfPP;
                    paramsD[8][1] += m.homeNumberOfPP;
                    paramsD[9][0] += m.awayGoalsInPP;
                    paramsD[9][1] += m.homeGoalsInPP;
                    paramsD[10][0] += m.awayShorthandedGoals;
                    paramsD[10][1] += m.homeShorthandedGoals;
                    paramsD[11][0] += m.awayFaceoffsWon;
                    paramsD[11][1] += m.homeFaceoffsWon;
                    paramsD[12][0] += m.awayBlockedShots;
                    paramsD[12][1] += m.homeBlockedShots;
                    paramsD[13][0] += m.awayHits;
                    paramsD[13][1] += m.homeHits;
                    paramsD[14][0] += m.awayPenaltyMinutes;
                    paramsD[14][1] += m.homePenaltyMinutes;
                    paramsD[15][0] += m.away2MinPenalties;
                    paramsD[15][1] += m.home2MinPenalties;
                    paramsD[17][0] += m.awayScore1stPeriod;
                    paramsD[17][1] += m.homeScore1stPeriod;
                    paramsD[18][0] += m.awayScore2ndPeriod;
                    paramsD[18][1] += m.homeScore2ndPeriod;
                    paramsD[19][0] += m.awayScore3rdPeriod;
                    paramsD[19][1] += m.homeScore3rdPeriod;
                    paramsD[20][0] += m.awayTimeInAttack;
                    paramsD[20][1] += m.homeTimeInAttack;
                    paramsD[21][0] += m.awayMissedShots;
                    paramsD[21][1] += m.homeMissedShots;

                }
            }
            for (int i=0; i<paramsD.length; i++){
                ArrayList<String> parameter = new ArrayList<>();
                if (i==7||i==11||i==12||i==13||i==21) {
                    paramsD[i][0] = Team.roundResult(paramsD[i][0] / listOfMatches.size(), 2);
                    paramsD[i][1] = Team.roundResult(paramsD[i][1] / listOfMatches.size(), 2);
                }

                parameter.add(params[i]);
                parameter.add(String.valueOf(Team.roundResult(paramsD[i][0], 2)));
                parameter.add(String.valueOf(Team.roundResult(paramsD[i][1], 2)));
                pList.add(parameter);
            }
            ArrayList<String> f = new ArrayList<>();
            if (forma.length()>20){
                forma = forma.substring(forma.length()-20,forma.length());
            }
            f.add("Форма");
            f.add(forma);
            f.add(forma);
            pList.set(16,f);
        }
    }

    public void getConfrontationList(String leagueName, String homeTeamName, String awayTeamName){
        if ((!leagueName.contains("Выберите"))&&(!homeTeamName.contains("Выберите"))&&(!awayTeamName.contains("Выберите"))){
            ArrayList<String> listOfSeasons = Settings.getListOfSeasons();
            for (int i=listOfSeasons.size()-1; i>=0; i--){
                Team ht = Team.getTeamFromFileWithSeason(homeTeamName, listOfSeasons.get(i));
                if (ht != null){
                    String matchTitle1 = Team.getShortName(homeTeamName) + Team.getShortName(awayTeamName);
                    String matchTitle2 = Team.getShortName(awayTeamName) + Team.getShortName(homeTeamName);
                    String path = Settings.getPathToDatabase() + listOfSeasons.get(i) + "/" + leagueName + "/Matches/";
                    for (int k=0; k<ht.matchList.size(); k++){
                        if (ht.matchList.get(k).contains(matchTitle1)){
                            listOfMatches.add(Match.getMatchFromFileByName(path + ht.matchList.get(k) + ".xml"));
                            listForConfrontation.add(listOfSeasons.get(i));
                        }
                        if (ht.matchList.get(k).contains(matchTitle2)){
                            listOfMatches.add(Match.getMatchFromFileByName(path + ht.matchList.get(k) + ".xml"));
                            listForConfrontation.add(listOfSeasons.get(i));
                        }
                    }
                }
            }
        }
    }

    public static ArrayList<Selector> getOpponentsList(String teamName, ArrayList<Match> list, String season, String allOrHomeOrAway, String lastOrFullSeason){
        Selector selector;
        ArrayList<Selector> listResult = new ArrayList<>();
        int count = list.size();

        for (int i=0; i<count; i++){
            selector = new Selector();
            String opponent = "";
            String opAllOrHomeOrAway = "";
            switch (allOrHomeOrAway) {
                case "Все матчи":
                    if (teamName.equals(list.get(i).homeTeam))
                        opponent = list.get(i).awayTeam;
                    else
                        opponent = list.get(i).homeTeam;
                    opAllOrHomeOrAway = "Все матчи";
                    break;
                case "Дома":
                    opponent = list.get(i).awayTeam;
                    opAllOrHomeOrAway = "На выезде";
                    break;
                case "На выезде":
                    opponent = list.get(i).homeTeam;
                    opAllOrHomeOrAway = "Дома";
                    break;
            }
            selector.getListOfMatches(Team.getLeague(opponent), opponent, opAllOrHomeOrAway, season, lastOrFullSeason);
            selector.getPList(selector.listOfMatches, opponent, season);
            listResult.add(selector);
        }

        return listResult;
    }

    public static ArrayList<ArrayList<Double>> getAdvancedStatsList(String teamName, ArrayList<Match> listOfMatches){
        ArrayList<ArrayList<Double>> result = new ArrayList<>();
        double corsiPercentMovingAverage = 0;
        double fenwickPercentMovingAverage = 0;
        double PDOMovingAverage = 0;

        for (int i=0; i<listOfMatches.size(); i++){
            ArrayList<Double> record = new ArrayList<>();
            if (teamName.equals(listOfMatches.get(i).homeTeam)){
                double corsi = (double) listOfMatches.get(i).homeShotsOnTarget + (double) listOfMatches.get(i).awayBlockedShots + (double) listOfMatches.get(i).homeMissedShots;
                double corsiOpp = (double) listOfMatches.get(i).awayShotsOnTarget + (double) listOfMatches.get(i).homeBlockedShots + (double) listOfMatches.get(i).awayMissedShots;
                double corsiPercent = Team.roundResult(100 * corsi / (corsi + corsiOpp), 2);
                corsiPercentMovingAverage = Team.roundResult((corsiPercentMovingAverage*i + corsiPercent) / (i+1), 2);

                record.add(corsi);
                record.add(corsiOpp);
                record.add(corsiPercent);
                record.add(corsiPercentMovingAverage);

                double fenwick = (double) listOfMatches.get(i).homeShotsOnTarget + (double) listOfMatches.get(i).homeMissedShots;
                double fenwickOpp = (double) listOfMatches.get(i).awayShotsOnTarget + (double) listOfMatches.get(i).awayMissedShots;
                double fenwickPercent = Team.roundResult(100 * fenwick / (fenwick + fenwickOpp), 2);
                fenwickPercentMovingAverage = Team.roundResult((fenwickPercentMovingAverage*i + fenwickPercent) / (i+1), 2);

                record.add(fenwick);
                record.add(fenwickOpp);
                record.add(fenwickPercent);
                record.add(fenwickPercentMovingAverage);

                double PDO = 100 + Team.roundResult(100 * ( listOfMatches.get(i).homeScore/(double)listOfMatches.get(i).homeShotsOnTarget - listOfMatches.get(i).awayScore/ (double)listOfMatches.get(i).awayShotsOnTarget), 2);
                PDOMovingAverage = Team.roundResult((PDOMovingAverage*i + PDO) / (i+1), 2);

                record.add(PDO);
                record.add(PDOMovingAverage);

                result.add(record);
            } else{
                double corsi = (double) listOfMatches.get(i).awayShotsOnTarget + (double) listOfMatches.get(i).homeBlockedShots + (double) listOfMatches.get(i).awayMissedShots;
                double corsiOpp = (double) listOfMatches.get(i).homeShotsOnTarget + (double) listOfMatches.get(i).awayBlockedShots + (double) listOfMatches.get(i).homeMissedShots;
                double corsiPercent = Team.roundResult(100 * corsi / (corsi + corsiOpp), 2);
                corsiPercentMovingAverage = Team.roundResult((corsiPercentMovingAverage*i + corsiPercent) / (i+1), 2);

                record.add(corsi);
                record.add(corsiOpp);
                record.add(corsiPercent);
                record.add(corsiPercentMovingAverage);

                double fenwick = (double) listOfMatches.get(i).awayShotsOnTarget + (double) listOfMatches.get(i).awayMissedShots;
                double fenwickOpp = (double) listOfMatches.get(i).homeShotsOnTarget + (double) listOfMatches.get(i).homeMissedShots;
                double fenwickPercent = Team.roundResult(100 * fenwick / (fenwick + fenwickOpp), 2);
                fenwickPercentMovingAverage = Team.roundResult((fenwickPercentMovingAverage*i + fenwickPercent) / (i+1), 2);

                record.add(fenwick);
                record.add(fenwickOpp);
                record.add(fenwickPercent);
                record.add(fenwickPercentMovingAverage);

                double PDO = 100 + Team.roundResult(100 * ( listOfMatches.get(i).awayScore/(double)listOfMatches.get(i).awayShotsOnTarget - listOfMatches.get(i).homeScore/ (double)listOfMatches.get(i).homeShotsOnTarget), 2);
                PDOMovingAverage = Team.roundResult((PDOMovingAverage*i + PDO) / (i+1), 2);

                record.add(PDO);
                record.add(PDOMovingAverage);

                result.add(record);
            }
        }
        return result;
    }
}


