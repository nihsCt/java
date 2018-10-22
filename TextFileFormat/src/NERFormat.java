import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Nihs on 2018/10/18.
 */
public class NERFormat
{
    private ArrayList<String> contextLines = null;
    private NERFindTag tagFinder = new NERFindTag();
    ArrayList<String> returnLines = new ArrayList<>();


    NERFormat(ArrayList<String> contextLines)
    {
        this.contextLines = contextLines;
    }

    private void execFormatting()
    {
        for (String contextLine : contextLines) {
            if (!tagFinder.skipLine(contextLine)) {
                returnLines.add(formatEachLine(contextLine));
            }
        }
    }

    private String formatEachLine(String line)
    {
        int lineIndex = 0;
        ArrayList<String> tokens = null;

        /* First, need to get the String array list of each tokens */
        tokens = tagFinder.getTokens(line);
        String fixedLine = "";
        FindTag.TagPosition previousTag;

        /*
        second, call findPreTag() method to get the previous tag name, start index and end index
        Since that it might be several tag in a line, from the beginning, we search for the previous tag
        Stop the loop while the end of the line
        */

        do {
            previousTag = tagFinder.findPreTag(tokens, lineIndex);
            if (previousTag != null) {
                fixedLine = writeTillTag(previousTag, tokens, fixedLine, lineIndex);

                /* write the fixed previous tag */
                fixedLine += tagFinder.getFixedPreTag(previousTag);
                // the index of tag end contains data and follow Tag


                /* write the fixed follow tag */
                fixedLine += tagFinder.getFixedFollowString(tokens, previousTag);
                lineIndex = previousTag.getTagIndexEnd() + 1;
            }

        } while (previousTag != null);

        // set a EOF tag to make writeTillTag() write all left tokens
        FindTag.TagPosition EOF = new FindTag.TagPosition("EOF", tokens.size(), tokens.size());
        fixedLine = writeTillTag(EOF, tokens, fixedLine, lineIndex);


//        // if previous tag is a null object, means that no tag contains in this line
//
//        for (int i = lineIndex; i < tokens.size(); i++) {
//            fixedLine += tokens.get(i);
//        }
        return fixedLine;
    }
    /* Only need <ORG>APEC</ORG> from <ENAMEX TYPE="ORG">＜ Ｅｎｇｌｉｓｈ ＞ ＡＰＥＣ ＜ ／ Ｅｎｇｌｉｓｈ ＞</ENAMEX> */
    private int getPreviousStringStartIndex(int tagStartIndex, ArrayList<String> tokens, int lineIndex){
        int startIndex = -1;
        for (int i = lineIndex + 1; i < tagStartIndex; i++){
            if (tokens.get(i).contains("＞")){
                // context start from the next token
                startIndex = i + 1;
                break;
            }
        }

        return startIndex;
    }

    private int getPreviousStringEndIndex(int tagStartIndex, ArrayList<String> tokens, int middelDataStartIndex){
        int endIndex = -1;
        for (int i = middelDataStartIndex; i < tagStartIndex; i++){
            if (tokens.get(i).contains("＜")){
                endIndex = i;
            }
        }
        return endIndex;
    }

    private String getPreviousString(FindTag.TagPosition tag, ArrayList<String> tokens, int lineIndex)
    {
        int tagStartIndex = tag.getTagIndexFrom();
        int previousStringStartIndex = getPreviousStringStartIndex(tagStartIndex, tokens, lineIndex);
        int previousStringEndIndex;

        if(previousStringStartIndex > -1) {
            previousStringEndIndex = getPreviousStringEndIndex(tagStartIndex, tokens, previousStringStartIndex);
        } else {
            previousStringStartIndex = lineIndex;
            previousStringEndIndex = tagStartIndex;
        }

        // start to write previousStringStartIndex
        String outputString = tokens.get(previousStringStartIndex);
        for (int i = previousStringStartIndex + 1; i < previousStringEndIndex; i++) {
            outputString += " " + tokens.get(i);
        }
        return outputString;
    }

    private boolean allNumALph(FindTag.TagPosition tag, ArrayList<String> tokens, int lineIndex)
    {
        int previousCount = tag.getTagIndexFrom();

        if(lineIndex == previousCount || lineIndex == 0) return false;

        for (int i = lineIndex; i < previousCount; i++) {
            String test = tokens.get(i).substring(0, 1);
            if (test.matches("[\\u4E00-\\u9FA5]+")
                    || test.equals("，")
                    || test.equals("。")
                    || test.equals("（")
                    || test.equals("）")
                    || test.equals("｛")
                    || test.equals("｝")) {
                return false;
            }
        }
        return true;
    }

    private String writeTillTag(FindTag.TagPosition tag, ArrayList<String> tokens, String fixedLine, int lineIndex)
    {
        // for ABD</T> of data
        int previousCount = tag.getTagIndexFrom();
//        if(previousCount < tokens.size() && tokens.get(previousCount).contains("</")){
//            fixedLine += tokens.get(lineIndex).split("<")[0];
//        }

        if(allNumALph(tag, tokens, lineIndex) && !tag.getTagName().equals("EOF") ){
            fixedLine += getPreviousString(tag, tokens, lineIndex);
        } else {
        /* write the data which before the previous tag */
            for (int i = lineIndex; i < previousCount; i++) {
//                // if current toke is ＜, it's a special tag. Need to skip
//                if(tokens.get(i).equals("＜")){
//                    for (int j = 0; j < previousCount; j++){
//                        if (tokens.get(i).equals("＞")){
//                            i = j + 1;
//                        }
//                    }
//                }

                fixedLine += tokens.get(i);
            }
        }
        return fixedLine;
    }


    public ArrayList<String> getFormattedLines()
    {
        execFormatting();
        return returnLines;
    }


}
