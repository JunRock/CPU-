import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

public class RR_Scheduling {
    public void run(){
        File f = new File("c:\\Temp\\OS.txt");
        FileReader fin = null;
        BufferedReader br = null;
        String processId;
        Deque<String> q = new LinkedList<>();
        Deque<String>tmp_q=new LinkedList<>();
        double[] arrtime = new double[5];
        double arriveTime, serviceTime, retime; //프로세스ID, 도착시간, 작업시간, 반환시간

        int num = 0;
        String[] process = new String[5];
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
    }

    public static void main(String[] args) {
        RR_Scheduling rr_scheduling=new RR_Scheduling();
        rr_scheduling.run();
    }
}
