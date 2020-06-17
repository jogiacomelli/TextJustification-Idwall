package idwall.desafio.filehandler;

import idwall.desafio.utils.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
    public static String getFileContent(String pathOrText) {
        StringBuilder text = new StringBuilder();
        String line;
        FileReader reader;
        try {
            reader = new FileReader(pathOrText);
            Scanner s = new Scanner(reader);

            while(s.hasNextLine()) {
                line = s.nextLine();

                if(line.isEmpty())
                    line = "\n\n";

                text.append(line).append(Constants.WHITESPACE);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file " + e.getMessage());
        }

        return text.toString();
    }
}
