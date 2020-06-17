package idwall.desafio.filehandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public static void writeTextToFile(String text, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error reading file " + e.getMessage());
        }
    }
}
