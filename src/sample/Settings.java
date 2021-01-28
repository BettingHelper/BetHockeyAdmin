package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

// определяем корневой элемент
@XmlRootElement(name = "Settings")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"showList"})

public class Settings {
    public ArrayList<Boolean> showList;

    public Settings(){
    }

    public Settings(ArrayList<Boolean> arrayList) {
        this.showList = arrayList;
    }

    public static Settings getSettingsFromFile(){
        String fileName = "settings/graphicSettings.xml";
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Settings) un.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void pushSettingsToFile(){
        String fileName = "settings/graphicSettings.xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Settings.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(this, new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static String getIp(){
        return "31.31.198.216";
    }

    public static String getLogin(){
        return "u0536356";
    }

    public static String getPassword(){
        return "Ds7!meBI";
    }

    public static String getCurrentSeason(String league){
        /*String result = "";
        try {
            File fileDir = new File("database/seasons.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            result = in.readLine();
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }*/
        return "20-21";
        /*if (league.equals("KHL"))
            return "20-21";
        if (league.equals("NHL"))
            return "19-20_Playoffs";
        return "";*/

    }

    public static String getPathToDatabase(){
        String result = "";
        try {
            File fileDir = new File("settings/pathToDatabase.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result = result + str;
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static void setPathToDatabase(String path){
        File file = new File("settings/pathToDatabase.txt");

        try(FileWriter writer = new FileWriter(file, false))
        {
            writer.write(path);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static int getDayCode(String s){
        int result = 0;
        String resultS = s.split(" ")[2];
        switch (s.split(" ")[1]){
            case "января":{
                resultS += "01"; break;
            }
            case "февраля":{
                resultS += "02"; break;
            }
            case "марта":{
                resultS += "03"; break;
            }
            case "апреля":{
                resultS += "04"; break;
            }
            case "мая":{
                resultS += "05"; break;
            }
            case "июня":{
                resultS += "06"; break;
            }
            case "июля":{
                resultS += "07"; break;
            }
            case "августа":{
                resultS += "08"; break;
            }
            case "сентября":{
                resultS += "09"; break;
            }
            case "октября":{
                resultS += "10"; break;
            }
            case "ноября":{
                resultS += "11"; break;
            }
            case "декабря":{
                resultS += "12"; break;
            }
        }
        String day = s.split(" ")[0];
        if (day.length() < 2)
            day = "0" + day;

        resultS += day;
        return Integer.parseInt(resultS);
    }

    public static int getNextDayCode(int todayCode){
        int res = todayCode;
        int currentDay = Integer.parseInt(String.valueOf(todayCode).substring(6,8));
        int currentMonth = Integer.parseInt(String.valueOf(todayCode).substring(4,6));
        int currentYear = Integer.parseInt(String.valueOf(todayCode).substring(0,4));

        if (currentDay+1 <= getLastDayInMonth(currentMonth, currentYear))
            res++;
        else {
            currentDay = 0;
            if (currentMonth < 12)
                currentMonth++;
            else{
                currentMonth = 1;
                currentYear++;
            }
            res = currentYear*10000 + currentMonth*100 + currentDay;
        }
        return  res;
    }

    public static int getLastDayInMonth(int month, int year){
        switch (month){
            case 1: {
                return 31;
            }
            case 2: {
                if (year % 4 == 0)
                    return 29;
                else
                    return 28;
            }
            case 3: {
                return 31;
            }
            case 4: {
                return 30;
            }
            case 5: {
                return 31;
            }
            case 6: {
                return 30;
            }
            case 7: {
                return 31;
            }
            case 8: {
                return 31;
            }
            case 9: {
                return 30;
            }
            case 10: {
                return 31;
            }
            case 11: {
                return 30;
            }
            case 12: {
                return 31;
            }

        }
        return 0;
    }

    public static void compress(String dirName){
        ZipOutputStream zos;
        Path sourceDir = Paths.get(dirName);

        try {
            String zipFileName = dirName.concat(".zip");
            zos = new ZipOutputStream(new FileOutputStream(zipFileName));

            Files.walkFileTree(sourceDir, new ZipDir(zos, sourceDir));

            zos.close();
        } catch (IOException ex) {
            System.err.println("I/O Error: " + ex);
        }
    }

    public static void decompressFolder(String fileToDecompress, String destinationFolder){
        File output = new File(destinationFolder);
        ZipFile zipFile = null;
        try {

            zipFile = new ZipFile(new File(fileToDecompress), Charset.forName("Cp437"));

            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.isDirectory()) {

                    // Create directory
                    File dir = new File(output, entry.getName());
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                } else {

                    // Get zipEntry name and create an output stream.
                    File file = new File(output, entry.getName());
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    InputStream inputStream = zipFile.getInputStream(entry);

                    // Read Zip file entry
                    int i;
                    byte[] data = new byte[1024];
                    while ((i = inputStream.read(data)) != -1) {
                        fileOutputStream.write(data, 0, i);
                    }

                    fileOutputStream.close();
                    inputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zipFile != null) {
                    zipFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getLastAddedNHLmatch(){
        String result = "";
        try {
            File fileDir = new File("settings/lastAddedNHLmatch.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result = result + str;
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static void setLastAddedNHLmatch(String path){
        File file = new File("settings/lastAddedNHLmatch.txt");

        try(FileWriter writer = new FileWriter(file, false))
        {
            writer.write(path);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String getLastAddedDayKHL(){
        String result = "";
        try {
            File fileDir = new File("settings/lastAddedDayKHL.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result = result + str;
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static String getLastAddedVHLmatch(){
        String result = "";
        try {
            File fileDir = new File("settings/lastAddedVHLmatch.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result = result + str;
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static void setLastAddedDayKHL(String dayCode){
        File file = new File("settings/lastAddedDayKHL.txt");

        try(FileWriter writer = new FileWriter(file, false))
        {
            writer.write(dayCode);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void setLastAddedVHLmatch(String path){
        File file = new File("settings/lastAddedVHLmatch.txt");

        try(FileWriter writer = new FileWriter(file, false))
        {
            writer.write(path);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String getCurrentMonth(){
        String result = "";
        try {
            File fileDir = new File("settings/currentMonth.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result = result + str;
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        if (result.length() < 2)
            result = "0" + result;
        return result;
    }

    public static void setCurrentMonth(String path){
        File file = new File("settings/currentMonth.txt");

        try(FileWriter writer = new FileWriter(file, false))
        {
            writer.write(path);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String getDateString(String s){
        String result = s.substring(0,3);
        switch (s.substring(4,7)){
            case "Jan":{
                result += "01."; break;
            }
            case "Feb":{
                result += "02."; break;
            }
            case "Mar":{
                result += "03."; break;
            }
            case "Apr":{
                result += "04."; break;
            }
            case "May":{
                result += "05."; break;
            }
            case "Jun":{
                result += "06."; break;
            }
            case "Jul":{
                result += "07."; break;
            }
            case "Aug":{
                result += "08."; break;
            }
            case "Sep":{
                result += "09."; break;
            }
            case "Oct":{
                result += "10."; break;
            }
            case "Nov":{
                result += "11."; break;
            }
            case "Dec":{
                result += "12."; break;
            }
        }
        result += s.substring(8,12);
        return result;
    }

    public static String getDateStringByTextDate(String s){
        String result = s.split(" ")[0];
        switch (s.split(" ")[1]){
            case "Январь":{
                result += ".01."; break;
            }
            case "Февраль":{
                result += ".02."; break;
            }
            case "Март":{
                result += ".03."; break;
            }
            case "Апрель":{
                result += ".04."; break;
            }
            case "Май":{
                result += ".05."; break;
            }
            case "Июнь":{
                result += ".06."; break;
            }
            case "Июль":{
                result += ".07."; break;
            }
            case "Август":{
                result += ".08."; break;
            }
            case "Сентябрь":{
                result += ".09."; break;
            }
            case "Октябрь":{
                result += ".10."; break;
            }
            case "Ноябрь":{
                result += ".11."; break;
            }
            case "Декабрь":{
                result += ".12."; break;
            }
        }
        result += s.split(" ")[2];
        return result;
    }

    public static String getDateForNHLgame(String s){
        String result = s.split(" ")[1].split(",")[0];
        if (result.length()==1)
            result = "0" + result;
        switch (s.split(" ")[0]){
            case "January":{
                result += ".01."; break;
            }
            case "February":{
                result += ".02."; break;
            }
            case "March":{
                result += ".03."; break;
            }
            case "April":{
                result += ".04."; break;
            }
            case "May":{
                result += ".05."; break;
            }
            case "June":{
                result += ".06."; break;
            }
            case "Jule":{
                result += ".07."; break;
            }
            case "August":{
                result += ".08."; break;
            }
            case "September":{
                result += ".09."; break;
            }
            case "October":{
                result += ".10."; break;
            }
            case "November":{
                result += ".11."; break;
            }
            case "December":{
                result += ".12."; break;
            }
        }
        result += s.split(" ")[1].split(",")[1];
        return result;
    }

    public static void setLastUpdate(){
        File file = new File("tmp/lastUpdateHBH.txt");

        try(FileWriter writer = new FileWriter(file, false)){
            String result = "";
            File fileDir = new File(Settings.getPathToDatabase() + "updates/_listOfUpdates.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result = str;
            }
            in.close();
            writer.write(result);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String getLeagueName(String country, String leagueName){
        String result = "";
        try {
            File fileDir = new File("settings/allLeagues.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (str.split("=")[3].equals(country + "*" + leagueName)){
                    in.close();
                    return str.split("=")[0];
                }
            }

        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }


    public static ArrayList<String> getListOfSeasons(){
        ArrayList<String> result = new ArrayList<>();
        try {
            //String path = getPathToDatabase();
            File fileDir = new File(Settings.getPathToDatabase() + "seasons.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                result.add(str);
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static String getSerialKey() throws Exception{
        String line;
        String serial = "";
        Process process = Runtime.getRuntime().exec("cmd /c wmic diskdrive get serialnumber");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(process.getInputStream()) );
        while ((line = in.readLine()) != null) {
            if ((!line.contains("erial"))&&(!line.equals("")))
                serial = serial + line;
        }
        in.close();
        return serial;
    }

    public static ArrayList<ArrayList<String>> getUsersList(){
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        try {
            FTPLoader.downloadFile(getIp(), getLogin(), getPassword(), "/data/usersHBH.txt", "tmp/usersHBH.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            File fileDir = new File("tmp/usersHBH.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF-8"));
            String str;
            while (((str = in.readLine()) != null)) {
                if (!str.equals("")){
                    ArrayList<String> record = new ArrayList<>();
                    for (int i=0; i<str.split("=").length;i++)
                        record.add(str.split("=")[i]);
                    result.add(record);
                }
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static void deleteAllFilesFolder(String path) {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();

    }

    public static int getSeconds(String time){
        return 60*Integer.parseInt(time.split(":")[0]) + Integer.parseInt(time.split(":")[1]);
    }

}


/*Date date = new Date();
boolean flag = false;

if (date.toString().split(" ")[1].equals("Jun"))
        flag = true;*/