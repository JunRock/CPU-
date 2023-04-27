package 비선점형스케줄링;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SJF_Scheduling {
    public void run(){
        File f=new File("c:\\Temp\\OS.txt");
        FileReader fin=null;
        BufferedReader br=null;
        Queue<String> q=new LinkedList<>();
        int [] arrtime=new int[5];
        String processId;
        int arriveTime,serviceTime,retime; //프로세스ID, 도착시간, 작업시간, 반환시간
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

        /*
        최단 작업 시간 프로세스순대로 정렬
         */
        int min=0;
        for(int i=0;i<process.length;i++){
            StringTokenizer st=new StringTokenizer(process[i]);
            while (st.hasMoreTokens()){
                processId=st.nextToken();
                arriveTime=Integer.parseInt(st.nextToken());
                serviceTime=Integer.parseInt(st.nextToken());
                st.nextToken(); st.nextToken();
                if(arriveTime<=min){
                    q.add(process[i]);
                    min+=1;
                }
               // else


            }
        }


    }

    public static void main(String[] args) {
        SJF_Scheduling sjf_scheduling=new SJF_Scheduling();
        sjf_scheduling.run();
    }
}
