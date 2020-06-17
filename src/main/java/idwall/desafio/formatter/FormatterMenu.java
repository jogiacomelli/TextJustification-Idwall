package idwall.desafio.formatter;

import idwall.desafio.filehandler.ReadFile;
import idwall.desafio.filehandler.WriteFile;
import idwall.desafio.formatter.string.IdwallFormatter;
import idwall.desafio.formatter.string.StringFormatter;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.util.Scanner;

public class FormatterMenu {
    private static final Scanner in = new Scanner(System.in);

    private static int limit;
    private static boolean justify;
    private static String text;

    public static void init(int limit, boolean justify, String text) {
        setLimit(limit);
        setJustify(justify);
        setText(text);
        printInputData();

        while(true) {
            int input;
            // Inputs can be changed
            System.out.println(" - Do you need to changed something?");
            System.out.println("    1 - No, i'm fine. Just do the thing, please.");
            System.out.println(" - Yes, please.");
            System.out.println("    2 - I want to change the LIMIT of characters by line.");
            System.out.println("    3 - I have changed my mind about JUSTIFYING my text.");
            System.out.println("    4 - The TEXT is not exactly what I wanted.");
            System.out.println("5 - I just want to GET OUT of here, please.");

            try {
                input = in.nextInt();
            } catch (Exception e) {
                System.out.println("Sorry, I'm very confused. I think you may be speaking a language that I don't understand =/" +
                        "I just understand 1, 2, 3, 4, or 5.");
                return;
            }

            switch (input) {
                case 1:
                    executeAndWriteToFile();
                    break;
                case 2:
                    setLimit(readNewLimit());
                    break;
                case 3:
                    setJustify(readNewJustify());
                    break;
                case 4:
                    setText(readNewText());
                    break;
                case 5:
                    exit();
                default:
                    System.out.println("Sorry, i didn't understand what you said and I don't know what to do =( \n" +
                            "Tell me again, please.");
            }
        }
    }

    private static void executeAndWriteToFile() {
        System.out.println("Executing the algorithm with the following data:\n");
        printInputData();
        // Run IdwallFormatter
        final StringFormatter sf = new IdwallFormatter(limit, justify);
        String outputText = sf.format(text);

        WriteFile.writeTextToFile(outputText,  "output.txt");

        // Print output text
        System.out.println("Output: ");
        System.out.println(outputText);
    }

    private static String readNewText() {
        System.out.println("You can tell me the text itself, or give me the path to a text file. You choose :)");
        Scanner scanner = new Scanner(System.in);
        String pathOrText = scanner.nextLine();

        String theText;

        if(isValidPath(pathOrText)) {
            theText = ReadFile.getFileContent(pathOrText);
        } else {
            //is a text
            theText = pathOrText;
        }

        return theText;
    }

    private static void printInputData() {
        // Print input data
        System.out.println("Inputs: ");
        System.out.println("Text: " + text);
        System.out.println("Limit: " + limit);
        System.out.println("Should justify: " + justify);
        System.out.println("=========================");
    }

    private static Boolean readNewJustify() {
        System.out.println("Say true if you want your text beautifully justified, or false if you don't:");
        return in.nextBoolean();
    }

    private static Integer readNewLimit() {
        System.out.println("Tell me the number: ");
        return in.nextInt();
    }

    public static boolean isValidPath(String path) {
        boolean exists;
        try {
            File file = new File(path);
            exists = file.exists();
        } catch (InvalidPathException | NullPointerException ex) {
            exists = false;
        }

        return exists;
    }

    public static void setLimit(int limit) {
        FormatterMenu.limit = limit;
    }

    public static void setJustify(boolean justify) {
        FormatterMenu.justify = justify;
    }

    public static void setText(String text) {
        FormatterMenu.text = text;
    }

    public static void exit() {
        System.exit(0);
    }
}
