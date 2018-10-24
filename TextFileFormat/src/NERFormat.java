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

        if(allNumALph(tag, tokens, lineIndex) && !tag.getTagName().equals("EOF") ){
            fixedLine += tagFinder.getPreviousString(tag, tokens, lineIndex);
        } else {
        /* write the data which before the previous tag */
            for (int i = lineIndex; i < previousCount; i++) {
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
