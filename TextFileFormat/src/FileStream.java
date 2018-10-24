import java.io.*;
import java.util.ArrayList;

/**
 * Created by Nihs on 2018/10/18.
 * <p>
 * For the constructors and methods,
 * all create two kinds of w/ and w/o parameter.
 * <p>
 * Not sure which kind is the better design.
 */
public class FileStream
{
    private FileReader fileReader = null;
    private FileWriter fileWriter = null;
    private File file = null;

    FileStream(File file)
    {
        this.file = file;
    }

    FileStream()
    {
    }

    /* Input a File object, return List of Strings */
    public ArrayList<String> readFile(File file)
    {

        ArrayList<String> contextLines = new ArrayList<String>();

        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            //StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                contextLines.add(line);
                //stringBuffer.append(line);
                //stringBuffer.append("\n");
            }
            //System.out.println(stringBuffer.toString());


            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contextLines;
    }

    /* If given the File Object when constructed can use this method directly */
    public ArrayList<String> readFile()
    {

        ArrayList<String> contextLines = new ArrayList<String>();

        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            //StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                contextLines.add(line);
                //stringBuffer.append(line);
                //stringBuffer.append("\n");
            }
            //System.out.println(stringBuffer.toString());


            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contextLines;
    }

    /* Write the List of Strings into the given File Object and output the File Object (maybe don't need to)  */
    public File writeFile(ArrayList<String> contextLines, File file, String extension)
    {
        try {

            String path = file.getPath();
            String[] outputName = path.split("\\.");
            if(outputName.length == 2)
                path = outputName[0] + extension;
            else {
                path = outputName[0];
                for(int i = 1; i < outputName.length - 1; i++){
                    path += "." + outputName[i];
                }
                path += extension;
            }

            fileWriter = new FileWriter(path);
            for (String contextLine : contextLines) {
                fileWriter.write(contextLine + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /* If given the File Object when constructed can use this method directly */
    public File writeFile(ArrayList<String> contextLines, String extension)
    {
        try {
            String outputName = file.getPath();
            outputName = outputName.split("\\.")[0] + extension;
            fileWriter = new FileWriter(outputName);
            for (String contextLine : contextLines) {
                fileWriter.write(contextLine);
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
