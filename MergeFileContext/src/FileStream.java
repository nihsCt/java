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
public class FileStream {
    private FileReader fileReader = null;
    private FileWriter fileWriter = null;
    private File mergefile = null;


    FileStream(File file) {
        this.mergefile = file;
    }


    /* Input a File object, return List of Strings */
    private ArrayList<String> readFile(File file) {

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

    /* Write the List of Strings into the merge file  */
    private File writeFile(ArrayList<String> contextLines) {
        try {
            fileWriter = new FileWriter(getEndMergeName(".NER"));
            for (String contextLine : contextLines) {
                fileWriter.write(contextLine + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mergefile;
    }

    public void writeAllNerIntoFIle(File endFolder) {
        File[] listOfFiles = endFolder.listFiles();
        ArrayList<String> fileContextLines = new ArrayList<>();

        try {
            // initialize the file writer, all file contexts merge in this file
            String outputName = getEndMergeName(".NER");
            fileWriter = new FileWriter(outputName);

            /* All files that need to be merge in one is in the listOfFiles */
            for (File theFile : listOfFiles) {
                // Only need to merge the *.NER files
                String endfoldrName = mergefile.getName();
                String currentFileName = theFile.getName().split("\\.")[0];
                if (!currentFileName.equals(endfoldrName) && theFile.getName().contains(".NER")) {

                    // First, read the current file
//                    System.out.println("Read:  File " + theFile.getName());
                    fileContextLines = readFile(theFile);

                    // Second, write it contexts lines by lines
                    for (String aLine: fileContextLines){
                        fileWriter.write(aLine + "\n");
                    }
                }
            }

            // After write all files, close the fileWriter to finish writing
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getEndMergeName(String extension) {
        String outputName = mergefile.getPath() + "/";
        outputName = outputName + mergefile.getName() + extension;

        return outputName;
    }
}
