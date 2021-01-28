package sample;

import javax.swing.*;
import java.util.ArrayList;

public class Parameters {

    public Parameters() {
    }

    public static String[] getParamsList(){
        return new String[]{"Выберите показатель", "Голы в основное время", "Голы с учетом ОТ и буллитов", "Голы в первом периоде",
                            "Голы во втором периоде", "Голы в третьем периоде", "Броски в створ", "Количество двухминутных удалений"};
    }

    public static String[] getIndex( String parameter){
        return new String[]{"Выберите тип ставки", "Общий тотал", "Инд.тотал команды", "Инд.тотал соперника", "Фора команды"};
    }

    public static String[] getValues(String parameter, String index){
        String[] result = new String[0];

        if ((index.equals("Выберите тип ставки"))||(parameter.equals("Выберите показатель"))){
            result = new String[]{"Выберите значение"};
        }
        if (parameter.contains("Голы")&&index.equals("Общий тотал")){
            result = new String[]{"Выберите значение", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5"};
        }
        if (parameter.contains("Голы")&&index.equals("Инд.тотал команды")){
            result = new String[]{"Выберите значение", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5"};
        }
        if (parameter.contains("Голы")&&index.equals("Инд.тотал соперника")){
            result = new String[]{"Выберите значение", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5"};
        }
        if (parameter.contains("Голы")&&index.equals("Фора команды")){
            result = new String[]{"Выберите значение", "-3.5", "-3", "-2.5", "-2", "-1.5", "-1", "-0.5", "0", "+0.5", "+1", "+1.5", "+2", "+2.5", "+3", "+3.5"};
        }

        if (parameter.contains("Броски")&&index.equals("Общий тотал")){
            result = new String[27];
            result[0] = "Выберите значение";
            for (int i = 45; i < 71; i++){
                result[i-44] = String.valueOf(i+0.5);
            }
        }
        if (parameter.contains("Броски")&&index.equals("Инд.тотал команды")){
            result = new String[33];
            result[0] = "Выберите значение";
            for (int i = 15; i < 47; i++){
                result[i-14] = String.valueOf(i+0.5);
            }
        }
        if (parameter.contains("Броски")&&index.equals("Инд.тотал соперника")){
            result = new String[33];
            result[0] = "Выберите значение";
            for (int i = 15; i < 47; i++){
                result[i-14] = String.valueOf(i+0.5);
            }
        }
        if (parameter.contains("Броски")&&index.equals("Фора команды")){
            result = new String[45];
            result[0] = "Выберите значение";
            double value = -10.5;
            for (int i = 1; i < 45; i+=2){
                result[i] = String.valueOf(value);
                result[i+1] = String.valueOf(value+0.5);
                if (value > 0){
                    result[i] = "+" + result[i];
                    result[i+1] = "+" + result[i+1];
                }
                value += 1;

            }
        }

        if (parameter.contains("Количество двухминутных удалений")&&index.equals("Общий тотал")){
            result = new String[27];
            result[0] = "Выберите значение";
            for (int i = 1; i < 27; i++){
                result[i] = String.valueOf(i-0.5);
            }
        }
        if (parameter.contains("Количество двухминутных удалений")&&index.equals("Инд.тотал команды")){
            result = new String[27];
            result[0] = "Выберите значение";
            for (int i = 1; i < 27; i++){
                result[i] = String.valueOf(i-0.5);
            }
        }
        if (parameter.contains("Количество двухминутных удалений")&&index.equals("Инд.тотал соперника")){
            result = new String[27];
            result[0] = "Выберите значение";
            for (int i = 1; i < 27; i++){
                result[i] = String.valueOf(i-0.5);
            }
        }
        if (parameter.contains("Количество двухминутных удалений")&&index.equals("Фора команды")){
            result = new String[45];
            result[0] = "Выберите значение";
            double value = -10.5;
            for (int i = 1; i < 45; i+=2){
                result[i] = String.valueOf(value);
                result[i+1] = String.valueOf(value+0.5);
                if (value > 0){
                    result[i] = "+" + result[i];
                    result[i+1] = "+" + result[i+1];
                }
                value += 1;

            }
        }


        return result;
    }

    public static JTable getTableByParams(String teamName, ArrayList<Match> matchList, String parameter, String index, String valueString){

        int typeOfTable = 1;

        if ((index == null)||(valueString == null)){
            index = "Выберите тип ставки";
            valueString = "Выберите значение";
        }
        int numberOfMatches = matchList.size();
        int numberPlus = 0;
        int numberEqual = 0;
        int numberMinus = 0;

        double morePercent = 0.0;
        double lessPercent = 0.0;
        double equalPercent = 0.0;

        if (!valueString.equals("Выберите значение")){


            if ((parameter.equals("Голы в основное время"))&&(index.equals("Общий тотал"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeScore + matchList.get(i).awayScore > value)
                        numberPlus++;
                    if (matchList.get(i).homeScore + matchList.get(i).awayScore < value)
                        numberMinus++;
                    if (matchList.get(i).homeScore + matchList.get(i).awayScore == value)
                        numberEqual++;
                }
            }

            if ((parameter.equals("Голы в основное время"))&&(index.equals("Инд.тотал команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore == value)
                            numberEqual++;
                    }
                }
            }

            if ((parameter.equals("Голы в основное время"))&&(index.equals("Инд.тотал соперника"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeScore > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore == value)
                            numberEqual++;
                    }
                }
            }

            if ((parameter.equals("Голы в основное время"))&&(index.equals("Фора команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore - matchList.get(i).awayScore + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeScore - matchList.get(i).awayScore + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeScore - matchList.get(i).awayScore + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore - matchList.get(i).homeScore + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayScore - matchList.get(i).homeScore + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayScore - matchList.get(i).homeScore + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
            }

            if ((parameter.equals("Голы с учетом ОТ и буллитов"))&&(index.equals("Общий тотал"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits + matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits > value)
                        numberPlus++;
                    if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits + matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits < value)
                        numberMinus++;
                    if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits + matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits == value)
                        numberEqual++;
                }
            }

            if ((parameter.equals("Голы с учетом ОТ и буллитов"))&&(index.equals("Инд.тотал команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits == value)
                            numberEqual++;
                    }
                }
            }

            if ((parameter.equals("Голы с учетом ОТ и буллитов"))&&(index.equals("Инд.тотал соперника"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits == value)
                            numberEqual++;
                    }
                }
            }

            if ((parameter.equals("Голы с учетом ОТ и буллитов"))&&(index.equals("Фора команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits - (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits) + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits - (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits) + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits - (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits) + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits - (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits) + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits - (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits) + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayScore + matchList.get(i).awayScoreOT + matchList.get(i).awayScoreBullits - (matchList.get(i).homeScore + matchList.get(i).homeScoreOT + matchList.get(i).homeScoreBullits) + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
            }

            if ((parameter.equals("Голы в первом периоде"))&&(index.equals("Общий тотал"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeScore1stPeriod + matchList.get(i).awayScore1stPeriod > value)
                        numberPlus++;
                    if (matchList.get(i).homeScore1stPeriod + matchList.get(i).awayScore1stPeriod < value)
                        numberMinus++;
                    if (matchList.get(i).homeScore1stPeriod + matchList.get(i).awayScore1stPeriod == value)
                        numberEqual++;
                }
            }

            if ((parameter.equals("Голы в первом периоде"))&&(index.equals("Инд.тотал команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore1stPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore1stPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore1stPeriod == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore1stPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore1stPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore1stPeriod == value)
                            numberEqual++;
                    }
                }
            }

            if ((parameter.equals("Голы в первом периоде"))&&(index.equals("Инд.тотал соперника"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeScore1stPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore1stPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore1stPeriod == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore1stPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore1stPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore1stPeriod == value)
                            numberEqual++;
                    }
                }
            }

            if ((parameter.equals("Голы в первом периоде"))&&(index.equals("Фора команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore1stPeriod - matchList.get(i).awayScore1stPeriod + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeScore1stPeriod - matchList.get(i).awayScore1stPeriod + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeScore1stPeriod - matchList.get(i).awayScore1stPeriod + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore1stPeriod - matchList.get(i).homeScore1stPeriod + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayScore1stPeriod - matchList.get(i).homeScore1stPeriod + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayScore1stPeriod - matchList.get(i).homeScore1stPeriod + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
            }

            if ((parameter.equals("Голы во втором периоде"))&&(index.equals("Общий тотал"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeScore2ndPeriod + matchList.get(i).awayScore2ndPeriod > value)
                        numberPlus++;
                    if (matchList.get(i).homeScore2ndPeriod + matchList.get(i).awayScore2ndPeriod < value)
                        numberMinus++;
                    if (matchList.get(i).homeScore2ndPeriod + matchList.get(i).awayScore2ndPeriod == value)
                        numberEqual++;
                }
            }

            if ((parameter.equals("Голы во втором периоде"))&&(index.equals("Инд.тотал команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore2ndPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore2ndPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore2ndPeriod == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore2ndPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore2ndPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore2ndPeriod == value)
                            numberEqual++;
                    }
                }
            }

            if ((parameter.equals("Голы во втором периоде"))&&(index.equals("Инд.тотал соперника"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeScore2ndPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore2ndPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore2ndPeriod == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore2ndPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore2ndPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore2ndPeriod == value)
                            numberEqual++;
                    }
                }
            }

            if ((parameter.equals("Голы во втором периоде"))&&(index.equals("Фора команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore2ndPeriod - matchList.get(i).awayScore2ndPeriod + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeScore2ndPeriod - matchList.get(i).awayScore2ndPeriod + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeScore2ndPeriod - matchList.get(i).awayScore2ndPeriod + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore2ndPeriod - matchList.get(i).homeScore2ndPeriod + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayScore2ndPeriod - matchList.get(i).homeScore2ndPeriod + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayScore2ndPeriod - matchList.get(i).homeScore2ndPeriod + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
            }

            if ((parameter.equals("Голы в третьем периоде"))&&(index.equals("Общий тотал"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeScore3rdPeriod + matchList.get(i).awayScore3rdPeriod > value)
                        numberPlus++;
                    if (matchList.get(i).homeScore3rdPeriod + matchList.get(i).awayScore3rdPeriod < value)
                        numberMinus++;
                    if (matchList.get(i).homeScore3rdPeriod + matchList.get(i).awayScore3rdPeriod == value)
                        numberEqual++;
                }
            }

            if ((parameter.equals("Голы в третьем периоде"))&&(index.equals("Инд.тотал команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore3rdPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore3rdPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore3rdPeriod == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore3rdPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore3rdPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore3rdPeriod == value)
                            numberEqual++;
                    }
                }
            }

            if ((parameter.equals("Голы в третьем периоде"))&&(index.equals("Инд.тотал соперника"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeScore3rdPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).homeScore3rdPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).homeScore3rdPeriod == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore3rdPeriod > value)
                            numberPlus++;
                        if (matchList.get(i).awayScore3rdPeriod < value)
                            numberMinus++;
                        if (matchList.get(i).awayScore3rdPeriod == value)
                            numberEqual++;
                    }
                }
            }

            if ((parameter.equals("Голы в третьем периоде"))&&(index.equals("Фора команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeScore3rdPeriod - matchList.get(i).awayScore3rdPeriod + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeScore3rdPeriod - matchList.get(i).awayScore3rdPeriod + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeScore3rdPeriod - matchList.get(i).awayScore3rdPeriod + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayScore3rdPeriod - matchList.get(i).homeScore3rdPeriod + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayScore3rdPeriod - matchList.get(i).homeScore3rdPeriod + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayScore3rdPeriod - matchList.get(i).homeScore3rdPeriod + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
            }

            if ((parameter.equals("Броски в створ"))&&(index.equals("Общий тотал"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeShotsOnTarget + matchList.get(i).awayShotsOnTarget > value)
                        numberPlus++;
                    if (matchList.get(i).homeShotsOnTarget + matchList.get(i).awayShotsOnTarget < value)
                        numberMinus++;
                    if (matchList.get(i).homeShotsOnTarget + matchList.get(i).awayShotsOnTarget == value)
                        numberEqual++;
                }
                typeOfTable = 1;
            }

            if ((parameter.equals("Броски в створ"))&&(index.equals("Инд.тотал команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget > value)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget < value)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget > value)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget < value)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
            }

            if ((parameter.equals("Броски в створ"))&&(index.equals("Инд.тотал соперника"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget > value)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget < value)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget > value)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget < value)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
            }

            if ((parameter.equals("Броски в створ"))&&(index.equals("Фора команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).homeShotsOnTarget - matchList.get(i).awayShotsOnTarget + value > 0)
                            numberPlus++;
                        if (matchList.get(i).homeShotsOnTarget - matchList.get(i).awayShotsOnTarget + value < 0)
                            numberMinus++;
                        if (matchList.get(i).homeShotsOnTarget - matchList.get(i).awayShotsOnTarget + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).awayShotsOnTarget - matchList.get(i).homeShotsOnTarget + value > 0)
                            numberPlus++;
                        if (matchList.get(i).awayShotsOnTarget - matchList.get(i).homeShotsOnTarget + value < 0)
                            numberMinus++;
                        if (matchList.get(i).awayShotsOnTarget - matchList.get(i).homeShotsOnTarget + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
            }

            if ((parameter.equals("Количество двухминутных удалений"))&&(index.equals("Общий тотал"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).home2MinPenalties + matchList.get(i).away2MinPenalties > value)
                        numberPlus++;
                    if (matchList.get(i).home2MinPenalties + matchList.get(i).away2MinPenalties < value)
                        numberMinus++;
                    if (matchList.get(i).home2MinPenalties + matchList.get(i).away2MinPenalties == value)
                        numberEqual++;
                }
                typeOfTable = 1;
            }

            if ((parameter.equals("Количество двухминутных удалений"))&&(index.equals("Инд.тотал команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).home2MinPenalties > value)
                            numberPlus++;
                        if (matchList.get(i).home2MinPenalties < value)
                            numberMinus++;
                        if (matchList.get(i).home2MinPenalties == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).away2MinPenalties > value)
                            numberPlus++;
                        if (matchList.get(i).away2MinPenalties < value)
                            numberMinus++;
                        if (matchList.get(i).away2MinPenalties == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
            }

            if ((parameter.equals("Количество двухминутных удалений"))&&(index.equals("Инд.тотал соперника"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).awayTeam.equals(teamName)){
                        if (matchList.get(i).home2MinPenalties > value)
                            numberPlus++;
                        if (matchList.get(i).home2MinPenalties < value)
                            numberMinus++;
                        if (matchList.get(i).home2MinPenalties == value)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).away2MinPenalties > value)
                            numberPlus++;
                        if (matchList.get(i).away2MinPenalties < value)
                            numberMinus++;
                        if (matchList.get(i).away2MinPenalties == value)
                            numberEqual++;
                    }
                }
                typeOfTable = 1;
            }

            if ((parameter.equals("Количество двухминутных удалений"))&&(index.equals("Фора команды"))){
                double value = Double.parseDouble(valueString);
                for (int i=0; i<numberOfMatches; i++){
                    if (matchList.get(i).homeTeam.equals(teamName)){
                        if (matchList.get(i).home2MinPenalties - matchList.get(i).away2MinPenalties + value > 0)
                            numberPlus++;
                        if (matchList.get(i).home2MinPenalties - matchList.get(i).away2MinPenalties + value < 0)
                            numberMinus++;
                        if (matchList.get(i).home2MinPenalties - matchList.get(i).away2MinPenalties + value == 0)
                            numberEqual++;
                    }
                    else {
                        if (matchList.get(i).away2MinPenalties - matchList.get(i).home2MinPenalties + value > 0)
                            numberPlus++;
                        if (matchList.get(i).away2MinPenalties - matchList.get(i).home2MinPenalties + value < 0)
                            numberMinus++;
                        if (matchList.get(i).away2MinPenalties - matchList.get(i).home2MinPenalties + value == 0)
                            numberEqual++;
                    }
                }
                typeOfTable = 2;
            }


            morePercent =  Team.roundResult(100 * (double) numberPlus/numberOfMatches, 2);
            lessPercent =  Team.roundResult(100 * (double) numberMinus/numberOfMatches, 2);
            equalPercent = Team.roundResult(100 * (double) numberEqual/numberOfMatches, 2);
        }

        String[] heads = new String[0];
        Object[][] data = new Object[0][];
        if (typeOfTable == 1){
            heads = new String[]{"Больше", "Меньше", "Ровно", "% больше", "% меньше", "% ровно"};
            data = new Object[][]{
                    {String.valueOf(numberPlus), String.valueOf(numberMinus), String.valueOf(numberEqual), String.valueOf(morePercent), String.valueOf(lessPercent), String.valueOf(equalPercent)}
            };
        }
        if (typeOfTable == 2){
            heads = new String[]{"Прошла", "Не прошла", "Возврат", "% прохода", "% непрохода", "% возврата"};
            data = new Object[][]{
                    {String.valueOf(numberPlus), String.valueOf(numberMinus), String.valueOf(numberEqual), String.valueOf(morePercent), String.valueOf(lessPercent), String.valueOf(equalPercent)}
            };
        }
        return new JTable(data, heads);
    }
}
