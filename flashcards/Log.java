package flashcards;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Log {
    private ArrayList<String> logs = new ArrayList<>();

    public void saveLog(String s) {
        logs.add(s);
    }

    public void saveLogToFile() {
        Scanner sc = new Scanner(System.in);
        String s = "File name:";
        System.out.println(s);
        saveLog(s);
        String fileName = sc.nextLine();
        saveLog(fileName);
        s = "The log has been saved.";
        saveLog(s);
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (String log : logs) {
                writer.write(log);
                writer.write("\n");
            }
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
