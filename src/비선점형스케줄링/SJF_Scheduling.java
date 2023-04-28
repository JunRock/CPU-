package 비선점형스케줄링;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

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
        int[] tmp_servicetime = new int[5];
        int[] tmp_arrivetime=new int[5];
        String[] tmp_processId=new String[5];
        int sum = 0;
        double arrsum = 0; //프로세스 대기시간 총합
        int resum = 0;
        double re_time_sum = 0;
        int[] time = new int[5]; //프로세스 별 반환시간 저장배열

        int min = 0;
        for (int i = 0; i < process.length; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                arriveTime = Integer.parseInt(st.nextToken()); //도착시간
                serviceTime = Integer.parseInt(st.nextToken()); //실행시간
                st.nextToken(); //우선순위
                st.nextToken(); //시간 할당량
                /*
                프로세스 도착시간 정렬
                 */
                if(arriveTime==0){ //도착시간이 0인 프로세스는 바로 큐에 삽입
                    q.add(process[i]);
                }

                tmp_servicetime[i]=serviceTime; //실행시간 저장
                tmp_arrivetime[i]=arriveTime; //도착시간 저장
                tmp_processId[i]=processId; //프로세스 ID저장
            }
        }

        Arrays.sort(tmp_servicetime);

        for(int i=0;i< process.length;i++){
            for(int k=0;k< process.length;k++){
                int count=0;
                StringTokenizer st1=new StringTokenizer(process[k]);
                while(st1.hasMoreTokens()){
                    processId=st1.nextToken();
                    arriveTime=Integer.parseInt(st1.nextToken());
                    serviceTime=Integer.parseInt(st1.nextToken());
                    /*
                    이미 큐 안에 동일한 프로세스가 들어가 있는지 확인
                     */
                    Iterator it=q.iterator();
                    while(it.hasNext()){
                        String s= (String) it.next();
                        StringTokenizer s1=new StringTokenizer(s);
                        while(s1.hasMoreTokens()){
                            String Id=s1.nextToken();
                            if(Id.equals(processId))
                                count++;
                        }
                    }

                    if(count==0) {
                        if (serviceTime == tmp_servicetime[i]) {
                            q.add(process[k]);
                        }
                    }
                    st1.nextToken();st1.nextToken();
                }
            }
        }

        int j = 0;
        Iterator it = q.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            StringTokenizer st = new StringTokenizer(str);
            while (st.hasMoreTokens()) {
                System.out.print(st.nextToken() + "의 대기시간: "); //프로세스 ID
                arriveTime = Integer.parseInt(st.nextToken()); //도착시간
                System.out.println(sum - arriveTime);
                arrsum += (sum - arriveTime);
                serviceTime = Integer.parseInt(st.nextToken()); //실행시간
                sum += serviceTime;
                time[j] = sum - arriveTime;
                j++;
                st.nextToken(); //우선순위
                st.nextToken(); //시간 할당량
            }
        }

        System.out.println("평균 대기 시간(AWT): " + arrsum / process.length);
        j=0;
        it = q.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            StringTokenizer st = new StringTokenizer(str);
            while (st.hasMoreTokens()) {
                System.out.println(st.nextToken() + "의 반환시간: " + time[j]); //프로세스 ID
                st.nextToken();
                st.nextToken();
                st.nextToken(); //우선순위
                st.nextToken(); //시간 할당량
            }
            re_time_sum += time[j++];
        }
        System.out.println("평균 반환 시간(ATT): "+re_time_sum /process.length);
    }

    public static void main(String[] args) {
        SJF_Scheduling sjf_scheduling=new SJF_Scheduling();
        sjf_scheduling.run();
    }
}
