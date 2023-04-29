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
        String tmp_processId;
        double tmp_servicetime,tmp_arrivetime;
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
            while(st.hasMoreTokens()) {
                processId = st.nextToken();
                arriveTime = Double.parseDouble(st.nextToken());
                serviceTime = Double.parseDouble(st.nextToken());
                st.nextToken();
                st.nextToken();
                if (arriveTime == 0) {
                    q.add(process[i]);
                    tmp_q.add(process[i]);
                }
            }
        }
        /*
        덱에서 하나씩 꺼내서 HRN우선순위 판별
         */
        String []tmp=new String[1];
        for(int i=0;i< process.length;i++){
            StringTokenizer str=new StringTokenizer(q.peekLast());
            double max_priority=0;
            while(str.hasMoreTokens()){
                tmp_processId=str.nextToken();
                tmp_servicetime=Integer.parseInt(str.nextToken());
                tmp_arrivetime=Integer.parseInt(str.nextToken());
                sum += tmp_servicetime; //실행시간 총합
                str.nextToken();str.nextToken();
            }

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
                    Iterator it= q.iterator();
                    while(it.hasNext()){
                        String s= (String) it.next();
                        StringTokenizer s1=new StringTokenizer(s);
                        while(s1.hasMoreTokens()){
                            String Id=s1.nextToken();
                            if(Id.equals(processId))
                                count++;
                        }
                    }
                    if(count==0) { //실제 우선순위를 구하는 작업을 하는 조건문
                            Math.max(max_priority,(serviceTime+(sum-arriveTime))/serviceTime);
                            tmp[0]=process[k];
                    }
                    st1.nextToken();st1.nextToken();
                }
            }
            q.addLast(tmp[0]);
            tmp_q.add(tmp[0]);
        }

        Iterator it=tmp_q.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    public static void main(String[] args) {
        HRN_Scheduling hrn_scheduling=new HRN_Scheduling();
        hrn_scheduling.run();
    }
}
