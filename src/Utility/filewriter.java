package Utility;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class filewriter {

    public static void loginToFile(String userName, LocalDate date, LocalTime time, String attempt) throws IOException {
        String createdFile = "src/login_activity.txt", item;
        FileWriter fileName = new FileWriter(createdFile, true);
        PrintWriter outPut = new PrintWriter(fileName);
        item = userName + "'s log-in attempt " + attempt + " on " + date + " at " + time;
        outPut.println(item);
        outPut.close();
    }



}
