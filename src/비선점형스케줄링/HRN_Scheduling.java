package 비선점형스케줄링;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class HRN_Scheduling {
    public void run(){
        File f = new File("c:\\Temp\\OS.txt");
        FileReader fin = null;
        BufferedReader br = null;
        String processId;
        Deque<String> q = new LinkedList<>();
        Queue<String>tmp_q=new LinkedList<>();
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

        int sum = 0,tmp_sum=0;
        double arrsum = 0,tmp_arrsum=0; //프로세스 대기시간 총합
        int resum = 0;
        double re_time_sum = 0;
        int[] time = new int[5]; //프로세스 별 반환시간 저장배열
        int []priority=new int[5];
        /*
        도착시간이 0인 프로세스 탐색
         */
        for(int i=0;i<process.length;i++){
            StringTokenizer st=new StringTokenizer(process[i]);
            while(st.hasMoreTokens()){
                processId=st.nextToken();
                arriveTime=Double.parseDouble(st.nextToken());
                serviceTime=Double.parseDouble(st.nextToken());
                st.nextToken();st.nextToken();
                if(arriveTime==0)
                    q.add(process[i]);
                //tmp_q.add(process[i]);
            }
        }
        /*
        덱에서 하나씩 꺼내서 HRN우선순위 판별
         */
        for(int i=0;i<process.length;i++) {

        }
    }

    public static void main(String[] args) {
        HRN_Scheduling hrn_scheduling=new HRN_Scheduling();
        hrn_scheduling.run();
    }
}
