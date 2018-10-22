/**
 * Created by Nihs on 2018/10/18.
 */

import java.io.File;

public class FindEndFolder
{
    private String path = null;

    FindEndFolder(String path)
    {
        this.path = path;
    }

    private FileStream endFolderWriter = null;

    public void processData()
    {
        File folder = new File(path);
        searchEndFile(folder);

    }

    private void searchEndFile(File folder)
    {
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles == null){
            System.out.println("Folder contains no files");
            return;
        }

        for (File theFile : listOfFiles) {
            if (isEndFile(folder)) {
                endFolderWriter = new FileStream(folder);
                endFolderWriter.writeAllNerIntoFIle(folder);


            } else if (theFile.isDirectory()) {
                System.out.println("Directory " + theFile.getName());

                /* still is a folder, so keep looking for end folder */
                searchEndFile(theFile);

            }
        }
    }

    private boolean isEndFile(File folder){
        File[] listOfFiles = folder.listFiles();

        for (File theFile : listOfFiles) {
            if(theFile.isDirectory()) return false;
        }
        return true;
    }



}
