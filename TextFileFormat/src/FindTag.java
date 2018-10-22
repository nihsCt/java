import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Nihs on 2018/10/18.
 * <p>
 * If want NER/WS FindTag to extends FindTag,
 * should make string be set in constructor
 * but, would that be wasting resource?
 * Or, should not set string to be instant?
 * <p>
 * Not sure of the different between List<> and ArrayList<>
 */
public class FindTag
{
    static class TagPosition
    {
        private String tagName = null;
        private int tagStart = -1;
        private int tagEnd = -1;
        private String data = "";

        TagPosition()
        {
            this.tagStart = -1;
            this.tagEnd = -1;
            this.tagName = "";
        }

        TagPosition(String tagName, int start, int end)
        {
            this.tagStart = start;
            this.tagEnd = end;
            this.tagName = tagName;
        }

        public void setTagInformation(String name, int start, int end)
        {
            this.tagName = name;
            this.tagStart = start;
            this.tagEnd = end;
        }

        public void setData(String data)
        {
            this.data = data;
        }

        public String getData()
        {
            return this.data;
        }

        public int getTagIndexFrom()
        {
            return this.tagStart;
        }

        public int getTagIndexEnd()
        {
            return this.tagEnd;
        }

        public String getTagName()
        {
            return this.tagName;
        }

        public TagPosition getTagInformation()
        {
            return this;
        }

    }

    private String[] removeList = {"<DOC", "</DOC>", "EMPTY"};


    FindTag()
    {
    }

    public ArrayList<String> getTokens(String line)
    {
        ArrayList<String> tokenList = new ArrayList<>();
        StringTokenizer tokens = new StringTokenizer(line, " ");
        while (tokens.hasMoreTokens()) {
            tokenList.add(tokens.nextToken());
        }

        return tokenList;
    }


    /**
     * Get tokens and check if tokens contains previous tag sign "<".
     * If truly does, call findTagEnd() to find the sing ">" of previous tag.
     * Call the findFollowTag method is the responsibly of the son Class.
     **/

    public TagPosition findPreTag(ArrayList<String> tokens, int index)
    {
        int startIndex = -1;
        TagPosition currentTag = null;
        TagPosition specialTag = null;
        int length = tokens.size();
        for (int i = index; i < length; i++) {
            if (tokens.get(i).equals("＜")) {
                if(specialTag != null) return specialTag;
                specialTag = spacialTag(tokens, i);
                i = specialTag.getTagIndexEnd();
                startIndex = specialTag.getTagIndexFrom();
            }

            if (tokens.get(i).contains("<")) {
                if (startIndex == -1) startIndex = i;
                currentTag = getTagEnd(tokens, i);
                if (currentTag != null) {
                    return new TagPosition(currentTag.getTagName(), startIndex, currentTag.getTagIndexEnd());
                }
            }

        }
        if(currentTag == null && specialTag != null){
            return specialTag;
        }

        // the null object return for the case that all tokens don't with any tag existed.
        return null;
    }

    /**
     * Return null for not exist > to be tag end.
     * Return _ for data with useless tag index
     * OR, return the data index in tag end.
     * <p>
     * input : the origin tokens and index of Tag start.
     * output: whole tag information, data contains in tokens.get(outputTag.getTagIndexEnd())
     **/

    // Since the previous tag end ">" all way contains Data. The previous tag end is the index to find label data
    private TagPosition getTagEnd(ArrayList<String> tokens, int index)
    {
        int tokenLength = tokens.size();
        TagPosition currentTag = new TagPosition();
        String tagEntity = "_";

        for (int i = index; i < tokenLength; i++) {

            // use contains(">") might not be a really good choice?
            if (tokens.get(i).contains("TYPE=")) {
                String[] temp = tokens.get(i).split("\"");
                tagEntity = temp[1];

                if (tokens.get(i).contains(">")) {
                    currentTag.setTagInformation(tagEntity, index, i);
                    return currentTag;
                }
            } else if (tokens.get(i).contains(">")) {

                currentTag.setTagInformation(tagEntity, index, i);
                return currentTag;

            }
        }
        return null;
    }


    public TagPosition spacialTag(ArrayList<String> tokens, int index)
    {
        int tokenLength = tokens.size();

        for (int i = index; i < tokenLength; i++) {
            if (tokens.get(i).equals("＞")) {
                return new TagPosition(tokens.get(i - 1), index, i);
            }
        }
        //System.out.println("Error in FindTag.spacialTag()" + " Index: " + index);
        return new TagPosition("ERROR", index, index);
    }

    /**
     * giving the whole line and check if contains this skip line object contains.
     * <p>
     * may have better ways.
     **/
    public boolean skipLine(String line)
    {
        for (String aRemoveList : removeList) {
            if (line.contains(aRemoveList)) return true;
        }
        return false;
    }

}
