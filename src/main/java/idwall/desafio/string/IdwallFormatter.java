package idwall.desafio.string;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    boolean justify;

    public IdwallFormatter() {
        super();
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
                    textWithBreaks.append(line.toString().trim());
                    textWithBreaks.append("\n");

                    line = new StringBuilder();
                    if(!isLastLine(words, i))
                      line.append(words[i].trim()).append(" ");
                }

                if(isLastLine(words, i) && line.length() > 0) {
                    textWithBreaks.append(line.toString().trim());
                    textWithBreaks.append("\n");
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
            addSpacesToLine(newLine);
        }
        text.append(line.toString().trim());
        text.append("\n");
    }

    private void addSpacesToLine(String newLine) {


    }

    public boolean isJustify() {
        return justify;
    }

    public void setJustify(boolean justify) {
        this.justify = justify;
    }
}
