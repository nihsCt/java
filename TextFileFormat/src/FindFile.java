/**
 * Created by Nihs on 2018/10/18.
 */

import java.io.File;
import java.util.ArrayList;

public class FindFile
{
    private String path = null;

    FindFile(String path)
    {
        this.path = path;
    }

    private FileStream fileOperator = new FileStream();
    private Full2Half transform = new Full2Half();

    public void processData()
    {
        File folder = new File(path);
        searchFile(folder);

    }

    private void searchFile(File folder)
    {
        File[] listOfFiles = folder.listFiles();


        for (File theFile : listOfFiles) {
            if (theFile.getName().equals(".DS_Store")) {
            } else if (theFile.isFile() && theFile.getName().contains(".name")) {
                System.out.println("File " + theFile.getName());

                /* here for file processing */
                /* First, read file into String Array List **/
                ArrayList<String> contextLines = fileOperator.readFile(theFile);

                /* Second, unicode fixed **/
                contextLines = transform.transferLines(contextLines);

                /* Third, may Try to create NER format data **/
                NERFormat nerFormatter = new NERFormat(contextLines);
                ArrayList<String> NERLines = nerFormatter.getFormattedLines();
                /* And, write the file to the file directory */
                fileOperator.writeFile(NERLines, theFile, ".NER");

//                /* Fourth, Create WS Format data **/
//                WSFormat wsFormatter = new WSFormat(contextLines);
//                ArrayList<String> WSLines = wsFormatter.getFormattedLines();
//                /* And, write the file to the file directory */
//                fileOperator.writeFile(WSLines, theFile, ".WS");

            } else if (theFile.isDirectory()) {
                System.out.println("Directory " + theFile.getName());

                /* still is a folder, so keep looking for file */
                searchFile(theFile);

            }
        }
    }

}
