import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Nihs on 2017/3/28.
 */
public class Account {
    private String account = "N/A";
    private String password = "N/A";
    private String fileName = "accounts.txt";

    Account(String acc, String pw){
        account = acc;
        password = pw;
    }

    public String getAccount(){
        return account;
    }

    public void setElement(String a, String p){
        account = a;
        password = p;
    }

    private boolean isExist(){
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (fileInput.hasNext()) {
            String challenge = fileInput.nextLine();
            StringTokenizer tokens = new StringTokenizer(challenge, ",;");
            //System.out.println(id + type + Points + Name + Description + Solved);
            if(tokens.nextToken().equals(account)) return true;
        }
        return false;
    }

    public boolean createAccount(){
        PrintWriter fileOutput = null;

        try {
            fileOutput = new PrintWriter(new FileOutputStream(fileName, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!isExist()){
            fileOutput.println(account + "," + password + ";");
            System.out.println("Account create success.");
            fileOutput.close();
            return true;
        } else {
            System.out.println("Account " + account + " has existed.");
            fileOutput.close();
            return false;
        }
    }

    public boolean login(){
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (fileInput.hasNext()) {
            String challenge = fileInput.nextLine();
            StringTokenizer tokens = new StringTokenizer(challenge, ",;");
            //System.out.println(id + type + Points + Name + Description + Solved);
            if(tokens.nextToken().equals(account)) {
                if(tokens.nextToken().equals(password)){
                    System.out.println("Account and password are correct.");
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }
}
