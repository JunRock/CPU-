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
        Queue<String> q = new LinkedList<>();
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

        int[] tmp_servicetime = new int[5];
        int[] tmp_arrivetime=new int[5];
        String[] tmp_processId=new String[5];
        int sum = 0;
        double arrsum = 0; //프로세스 대기시간 총합
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
                if(arriveTime==0)
                    q.add(process[i]);
            }
        }

        for(int i=0;i<process.length;i++){
            String str=q.peek();
            StringTokenizer st1=new StringTokenizer(str);
            int wait_time;
            while(st1.hasMoreTokens()){
                processId=st1.nextToken();
                arriveTime=Double.parseDouble(st1.nextToken());
                serviceTime=Double.parseDouble(st1.nextToken());

            }
        }
    }

    public static void main(String[] args) {
        HRN_Scheduling hrn_scheduling=new HRN_Scheduling();
        hrn_scheduling.run();
    }
}
