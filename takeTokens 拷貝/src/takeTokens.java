import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class takeTokens {
    public static void main(String[] args) {
        PrintWriter fileOutput = null;
        Random rand = new Random();

        try {
            fileOutput = new PrintWriter(new FileOutputStream("fileOut.dat"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int s = 0, i = 0, h = 0, d = 1, m = 1, y = 2017, status, end, fall;


        for (m = 1; m < 3; m++) {
            if (m == 1) end = 32;
            else end = 16;
            for (d = 1; d < end; d++) {
                for (h = 0; h < 24; h++) {
                    for (i = 0; i < 60; i++) {
                        if (s == 0) {
                            s = 30;
                        } else {
                            s = 0;
                        }
                        fall = rand.nextInt(20000) + 1;
                        status = rand.nextInt(2) + 1;
                        if (fall == 1) status = 3;
                        fileOutput.printf("\"Camera_1\",\"%4d-%02d-%02d %02d:%02d:%02d\",\"%d\"\n", y, m, d, h, i, s, status);
                    }
                }
            }
        }
        fileOutput.close();
    }
}
