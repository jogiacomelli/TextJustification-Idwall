package idwall.desafio.string;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    boolean justify;

    public IdwallFormatter() {
        super();
    }

    public IdwallFormatter(Boolean justify) {
        super();
        setJustify(justify);
    }
    public IdwallFormatter(Integer limit) {
        super();
        setLimit(limit);
    }

    public IdwallFormatter(Integer limit, Boolean justify) {
        super();
        setLimit(limit);
        setJustify(justify);
    }

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text) {
        return breakLinesByBruteForce(text);
    }

    private String breakLinesByBruteForce(String text) {
        String[] paragraphs = text.split("\n");
        StringBuilder textWithBreaks = new StringBuilder();
        StringBuilder line;

        for(String paragraph : paragraphs ) {
            String[] words = paragraph.split(" " );
            line = new StringBuilder();

            for (int i = 0; i < words.length; i++) {
                if (wordFitsInLine(words[i], line.toString())) {
                    line.append(words[i].trim()).append(" ");
                } else {
                    addNewLine(textWithBreaks, line);

                    line = new StringBuilder();
                    if(!isLastLine(words, i))
                      line.append(words[i].trim()).append(" ");
                }

                if(isLastLine(words, i) && line.length() > 0) {
                    addNewLine(textWithBreaks, line);
                }
            }
        }

        return textWithBreaks.toString();
    }

    private boolean wordFitsInLine (String word, String line) {
        return (line.length() + word.trim().length()) <= getLimit();
    }

    private boolean isLastLine(String[] words, int i) {
        return i == words.length - 1;
    }

    private void addNewLine(StringBuilder text, StringBuilder line) {
        String newLine = line.toString().trim();
        if(isJustify() && newLine.length() < getLimit()) {
            newLine = addSpacesToLine(newLine);
        }
        text.append(newLine);
        text.append("\n");
    }

    private String addSpacesToLine(String newLine) {
        List<String> words;
        int diff = getLimit() - newLine.length();

        StringBuilder buildLineWithSpaces;

        if(newLine.length() < getLimit() / 2) {
            return newLine;
        }

        while(diff > 0) {
           words = Arrays.asList(newLine.split(" "));

           buildLineWithSpaces = new StringBuilder();
           for (int i = 0; i < words.size(); i++) {
               String word = words.get(i);

               buildLineWithSpaces.append(word).append(" ");

               if (diff > 0 && i < words.size() - 1) {
                   buildLineWithSpaces.append(" ");
                   diff--;
               }
           }
           newLine = buildLineWithSpaces.toString().trim();
       }

        return newLine;
    }

    public boolean isJustify() {
        return justify;
    }

    public void setJustify(boolean justify) {
        this.justify = justify;
    }
}
