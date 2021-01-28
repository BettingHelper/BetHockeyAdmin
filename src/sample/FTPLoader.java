package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;

public class FTPLoader {

    public static void uploadFile(String hostAddress, String log, String password, String pathOnServer, String pathToFile) throws FileNotFoundException {
        FTPClient fClient = new FTPClient();
        FileInputStream fInput = new FileInputStream(pathToFile);
        try {
            fClient.connect(hostAddress);
            fClient.enterLocalPassiveMode();
            fClient.login(log, password);
            fClient.storeFile(pathOnServer, fInput);
            fClient.logout();
            fClient.disconnect();
            fInput.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static String downloadFile(String hostAddress, String log, String password, String pathOnServer, String pathToSave) throws FileNotFoundException {
        String result = "";
        FTPClient fClient = new FTPClient();
        FileOutputStream fOutput = new FileOutputStream(pathToSave);
        try {
            fClient.connect(hostAddress);
            fClient.enterLocalPassiveMode();
            fClient.login(log, password);
            fClient.retrieveFile(pathOnServer, fOutput);
            fClient.logout();
            fClient.disconnect();
            fOutput.close();
            result = "Success";
        } catch (IOException ex) {
            System.err.println(ex);
            result = ex.toString().split("Exception: ")[1];
        }
        return result;
    }

}
