import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Nihs on 2018/10/19.
 */
public class NERFindTag extends FindTag
{
    private final String[] remainTags = {"PERSON", "NORP", "FAC", "ORG", "GPE", "LOC",
            "PRODUCT", "DATE", "TIME", "PERCENT", "MONEY", "QUANTITY",
            "ORDINAL", "CARDINAL", "EVENT", "WORK_OF_ART", "LAW", "ALNGUAGE"};

    NERFindTag()
    {
        super();
    }


    /**
     * Only the NER Previous tag would call and input this method
     **/
    private boolean in18Tag(TagPosition tag)
    {
        for (String test : remainTags) {
            if (tag.getTagName().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public String getFixedPreTag(TagPosition tag)
    {
        if (in18Tag(tag))
            return "<" + tag.getTagName() + ">";
        else return "";
    }

    public String getFixedFollowString(ArrayList<String> tokens, TagPosition tag)
    {
        int tagEnd = tag.getTagIndexEnd();
        String outputString = "";
        String middleString = tokens.get(tagEnd);



        // for A="B>CD
        if (!tokens.get(tagEnd).contains("</") && in18Tag(tag)) {
            middleString = middleString.split(">")[1];
//            if(middleString.contains("＜") || middleString.contains("＞")) return outputString;
            // for those ＜ ＥＮＧ or ＜／
            if(middleString.contains("＜")
                    || middleString.contains("＞"))
                middleString = "";
            outputString = middleString;
        } else {
            // ABC</D> or A="B">C</D> come here

            //for A="B">C</D> need to remove A="B">
            if (tokens.get(tagEnd).split(">").length > 1) {
                middleString = middleString.split(">")[1];
            }

            // for those ＜ ＥＮＧ or ＜／
            if(middleString.contains("＜")
                    || middleString.contains("＞"))
                middleString = "";

            // for ABC</D>
            middleString = middleString.split("<")[0];
            for (int i = tagEnd; i > 0; i--) {
                if (tokens.get(i).contains("TYPE=")) {
                    if(Pattern.matches(".*[a-zA-Z]+.*", middleString)) outputString = " ";
                    outputString += middleString + "</" + tokens.get(i).split("\"")[1] + ">";
                    return outputString;
                }
            }
        }

        return outputString;
    }
}
