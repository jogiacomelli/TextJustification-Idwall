package idwall.desafio.string;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public abstract class StringFormatter {

    private Integer limit;
    private boolean justify;


    public StringFormatter() {
        setLimit(40);
    }

    /**
     * It receives a text and should return it formatted
     *
     * @param text
     * @return
     */
    public abstract String format(String text);

    public void setLimit(Integer limit){
        this.limit = limit;
    }

    public Integer getLimit() {
     return this.limit;
    }

    public boolean isJustify() {
        return justify;
    }

    public void setJustify(boolean justify) {
        this.justify = justify;
    }
}
