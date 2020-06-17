package idwall.desafio.string;

import idwall.desafio.utils.Constants;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

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
        return breakLinesGreedy(text);
    }

    private String breakLinesGreedy(String text) {
        String[] paragraphs = text.split(Constants.NEW_LINE);
        StringBuilder textWithBreaks = new StringBuilder();
        StringBuilder line;

        for(String paragraph : paragraphs ) {
            String[] words = paragraph.split(Constants.WHITESPACE);
            line = new StringBuilder();

            for (int i = 0; i < words.length; i++) {
                if (!wordFitsInLine(words[i], line)) {
                    addNewLine(textWithBreaks, line);
                    line = new StringBuilder();
                }

                line.append(words[i].trim()).append(Constants.WHITESPACE);

                if(isLastWordOfLastLine(words.length, i) && line.length() > 0) {
                    addNewLine(textWithBreaks, line);
                }
            }
        }

        return textWithBreaks.toString();
    }

    private boolean wordFitsInLine (String word, StringBuilder line) {
        return (line.length() + word.trim().length()) <= getLimit();
    }

    private boolean isLastWordOfLastLine(int numberOfWords, int i) {
        return i == numberOfWords - 1;
    }

    private void addNewLine(StringBuilder text, StringBuilder line) {
        String newLine = line.toString().trim();

        if(isJustify() && newLine.length() < getLimit()) {
            newLine = addSpacesToLine(newLine);
        }
        text.append(newLine);
        text.append(Constants.NEW_LINE);
    }

    private String addSpacesToLine(String newLine) {
        String[] words;
        StringBuilder lineWithSpaces = new StringBuilder();

        words = newLine.split(Constants.WHITESPACE);

        int diff = getLimit() - newLine.length();
        int numberOfGaps = words.length - 1;
        int totalSpaces = diff + numberOfGaps;
        int spacesBetweenWords = (numberOfGaps > 0) ? (totalSpaces / numberOfGaps) : 1; // Spaces needed between every word.
        int extraSpaces = (numberOfGaps > 0) ? (totalSpaces % numberOfGaps) : 0; // number of extra spaces needed.

        for(String word : words) {
            lineWithSpaces.append(word);

            // If is the last word in the line;
            if(word.equals(words[words.length - 1])) {
                break;
            }

            for (int i = 0; i < spacesBetweenWords; i++) {
                lineWithSpaces.append(Constants.WHITESPACE);
            }

            if(extraSpaces > 0) {
                lineWithSpaces.append(Constants.WHITESPACE);
                extraSpaces--;
            }
        }

        newLine = lineWithSpaces.toString().trim();

        return newLine;
    }
}
