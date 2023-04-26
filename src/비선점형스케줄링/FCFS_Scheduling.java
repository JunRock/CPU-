package 비선점형스케줄링;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class FCFS_Scheduling {
    public void run(){
        File f=new File("c:\\Temp\\OS.txt");
        FileReader fin=null;
        BufferedReader br=null;

        int [] arrtime=new int[5];
        int processId,arriveTime,serviceTime,retime; //프로세스ID, 도착시간, 작업시간, 반환시간
        int c;
        int num=0;
        String [] process=new String[5];
        /*
        파일데이터 입력
         */
        try{
           br=new BufferedReader(new FileReader(f));
           while(true) {
               String line = br.readLine();
               if (line == null)
                   break;
               process[num++]=line;
           }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        int sum=0;
        int arrsum=0; //프로세스 대기시간 총합
        int resum=0;
        double re_time_sum=0;
        int []time=new int[5]; //프로세스 별 반환시간 저장배열

        for(int i=0;i<process.length;i++){
            StringTokenizer st=new StringTokenizer(process[i]);
            while(st.hasMoreTokens()){
                System.out.print(st.nextToken()+"의 대기시간: "); //프로세스 ID
                arriveTime=Integer.parseInt(st.nextToken()); //도착시간
                System.out.println(sum-arriveTime);
                arrsum+=(sum-arriveTime);
                serviceTime=Integer.parseInt(st.nextToken()); //실행시간
                sum+=serviceTime;
                time[i]=sum-arriveTime;
                st.nextToken(); //우선순위
                st.nextToken(); //시간 할당량
            }
       }

        System.out.println("평균 대기 시간(AWT): "+ arrsum/process.length);
        for(int i=0;i<process.length;i++){
            StringTokenizer st=new StringTokenizer(process[i]);
            while(st.hasMoreTokens()){
                System.out.println(st.nextToken()+"의 반환시간: "+time[i]); //프로세스 ID
                st.nextToken();
                st.nextToken();
                st.nextToken(); //우선순위
                st.nextToken(); //시간 할당량
            }
            re_time_sum+=time[i];
        }

        System.out.println("평균 반환 시간(ATT): "+re_time_sum/process.length);
    }

    public static void main(String[] args) {
        FCFS_Scheduling fcfs_scheduling=new FCFS_Scheduling();
        fcfs_scheduling.run();
    }
}
