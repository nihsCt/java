import java.util.ArrayList;

/**
 * Created by Nihs on 2018/10/18.
 *
 *
 * For the constructors and methods,
 * all create several kinds of w/ and w/o parameter.
 *
 * Not sure which kind is the better design.
 */


public class Full2Half
{
    private final char SBC_SPACE = 12288; // 全形空格 12288
    private final char DBC_SPACE = 32; //半形空格 32
    // ASCII character 33-126 <-> unicode 65281-65374
    private final char UNICODE_NUMBER_START = 65296;
    private final char UNICODE_NUMBER_END = 65305;
    private final char UNICODE_CAPS_ALPHABET_START = 65313;
    private final char UNICODE_CAPS_ALPHABET_END = 65338;
    private final char UNICODE_ALPHABET_START = 65345;
    private final char UNICODE_ALPHABET_END = 65370;
    private final char DBC_SBC_STEP = 65248; // 全形半形轉換間隔

    private String string = "";

    Full2Half() {

    }
    Full2Half(String string)
    {
        this.string = string;
    }


    public ArrayList<String> transferLines(ArrayList<String> inputLines){
        ArrayList<String> outputLines = new ArrayList<>();
        for (String lines: inputLines)
        {
            outputLines.add(sbc2dbcCase(lines));
        }
        return outputLines;
    }

    public String transferString(){
        return sbc2dbcCase(string);
    }

    public String transferString(String str){
        return sbc2dbcCase(str);
    }

    private boolean needTransfer(char src){
        if ((src >= UNICODE_ALPHABET_START && src <= UNICODE_ALPHABET_END)
                || (src >= UNICODE_CAPS_ALPHABET_START && src <= UNICODE_CAPS_ALPHABET_END)
                || (src >= UNICODE_NUMBER_START && src <= UNICODE_NUMBER_END)) {
            return true;
        }
        return false;
    }

    private char sbc2dbc(char src){
        if (src == SBC_SPACE) {
            return DBC_SPACE;
        }
        if (needTransfer(src)) {
            return (char) (src - DBC_SBC_STEP);
        }
        return src;
    }
    /**
     * Convert from SBC case to DBC case
     *
     * @param src
     * @return DBC case
     */
    private String sbc2dbcCase(String src) {
        if (src == null) {
            return null;
        }
        char[] c = src.toCharArray();
        for (int i = 0; i < c.length; i++) {
            c[i] = sbc2dbc(c[i]);
        }
        return new String(c);
    }
    /**
     * Convert from DBC case to SBC case.
     *
     * @param src
     * @return SBC case string
     */
}
