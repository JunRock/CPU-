import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//num이랑 process배열을 인자로 리턴해야됌
public class FIle_Open {
    static int num;
    static String process[];
    public String[] open(){
        File f = new File("c:\\Temp\\OS.txt");
        FileReader fin = null;
        BufferedReader br = null;
        String[] process = new String[7];
        /*
        파일데이터 입력
         */
        try {
            br = new BufferedReader(new FileReader(f));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                process[num++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return process;
    }

    public int anInt(){
        return num;
    }
}
