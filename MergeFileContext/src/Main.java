/**
 * Created by Nihs on 2018/10/21.
 */
public class Main {
    public static void main(String[] args){
        String path = "chinese/annotations";
        if(args.length > 0)
            path = args[0];

        FindEndFolder findData = new FindEndFolder(path);

        findData.processData();
    }
}
