import java.util.ArrayList;

/**
 * Created by Nihs on 2018/10/19.
 */
public class WSFormat
{
    private ArrayList<String> contextLines = null;
    private FindTag tagFinder = new FindTag();
    private ArrayList<String> returnLines = null;


    WSFormat(ArrayList<String> contextLines)
    {
        this.contextLines = contextLines;
        returnLines = new ArrayList<>();
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
                // the index of tag end contains data and follow Tag


                /* write the fixed follow tag */
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

    private String writeTillTag(FindTag.TagPosition tag, ArrayList<String> tokens, String fixedLine, int lineIndex)
    {
        // for ABD</T> of data
        int previousCount = tag.getTagIndexFrom();
//        if(previousCount < tokens.size() && tokens.get(previousCount).contains("</")){
//            fixedLine += tokens.get(lineIndex).split("<")[0];
//        }


        /* write the data which before the previous tag */
        for (int i = lineIndex; i < previousCount; i++) {

            fixedLine += tokens.get(i) + " ";
        }

        if (tokens.size() > tag.getTagIndexEnd())
            fixedLine += getDataInTagToken(tokens, tag) + " ";
        return fixedLine;

    }


    public String getDataInTagToken(ArrayList<String> tokens, FindTag.TagPosition tag)
    {

        int tagEnd = tag.getTagIndexEnd();
        String outputString = "";
        String containDataToken = tokens.get(tagEnd);


        if (containDataToken.contains("</")) {
            // ABC</D> or A="B">C</D> come here

            //for A="B">C</D> need to remove A="B">
            outputString = containDataToken;
            if (tokens.get(tagEnd).split(">").length > 1) {
                outputString = containDataToken.split(">")[1];
            }
            outputString = outputString.split("</")[0];
        } else if(containDataToken.contains(">")) {
            // for A="B>CD

            outputString = containDataToken.split(">")[1];
        }

        return outputString;
    }


    public ArrayList<String> getFormattedLines()
    {
        execFormatting();
        return returnLines;
    }

}
