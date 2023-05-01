package 비선점형스케줄링;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Priority_Scheduling_비선점 {
    public void run(){
        File f = new File("c:\\Temp\\OS.txt");
        FileReader fin = null;
        BufferedReader br = null;
        Deque<String> q = new LinkedList<>();
        Queue<String> tmp_q = new LinkedList<>(); //동일한 우선순위일 경우 도착시간에 따른 정렬까지 마침
        int[] arrtime = new int[5];
        String processId = null;
        int arriveTime = 0, serviceTime, retime; //프로세스ID, 도착시간, 작업시간, 반환시간
        int priority = 0;
        int num = 0;
        String[] process = new String[5];

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
        int[] tmp_arrivetime = new int[5];
        int[] tmp_priority = new int[5];
        String[] tmp_processId = new String[5];

        int sum = 0;
        double arrsum = 0; //프로세스 대기시간 총합
        double re_time_sum = 0;
        int[] time = new int[5]; //프로세스 별 반환시간 저장배열
        String[] cpu_process = new String[5];

        for (int i = 0; i < process.length; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                tmp_arrivetime[i] = Integer.parseInt(st.nextToken()); //도착시간
                tmp_servicetime[i] = Integer.parseInt(st.nextToken()); //실행시간
                tmp_priority[i] = Integer.parseInt(st.nextToken()); //우선순위
                st.nextToken(); //시간 할당량
            }
        }
        Arrays.sort(tmp_arrivetime);

        for (int i = 0; i < process.length; i++) {
            for (int j = 0; j < process.length; j++) {
                StringTokenizer st = new StringTokenizer(process[j]);
                while (st.hasMoreTokens()) {
                    processId = st.nextToken();
                    arriveTime = Integer.parseInt(st.nextToken());
                    serviceTime = Integer.parseInt(st.nextToken());
                    priority = Integer.parseInt(st.nextToken());
                    st.nextToken();
                    if (tmp_arrivetime[i] == arriveTime) {
                        cpu_process[i] = process[j];
                    }
                }
            }
        }

        String[] tmp_arr = new String[cpu_process.length - 1];
        String[] arr = new String[cpu_process.length - 1];
        int[] tmp_priority1 = new int[cpu_process.length - 1];
        int[] tmp_servicetime1 = new int[cpu_process.length - 1];
        int[] tmp_arrivetime1 = new int[cpu_process.length - 1];
        for (int i = 0; i < cpu_process.length - 1; i++) {
            tmp_arr[i] = cpu_process[i + 1]; //2 3 4 5 프로세스 정렬
        }

        for (int i = 0; i < arr.length; i++) {
            StringTokenizer st = new StringTokenizer(tmp_arr[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                tmp_arrivetime1[i] = Integer.parseInt(st.nextToken()); //도착시간
                tmp_servicetime1[i] = Integer.parseInt(st.nextToken()); //실행시간
                tmp_priority1[i] = Integer.parseInt(st.nextToken()); //우선순위
                st.nextToken(); //시간 할당량
            }
        }
        Arrays.sort(tmp_priority1); //1 2 2 4

        for(int i=0;i<arr.length;i++){
            int count=0;
            for(int j=0;j<arr.length;j++){
                StringTokenizer st=new StringTokenizer(tmp_arr[j]);
                int mcount=0;
                while(st.hasMoreTokens()){
                    processId=st.nextToken();
                    arriveTime=Integer.parseInt(st.nextToken());
                    serviceTime=Integer.parseInt(st.nextToken());
                    priority=Integer.parseInt(st.nextToken());
                    st.nextToken();
                }
                Iterator iterator=q.iterator(); //큐에 동일 프로세스가 있는 지 확인
                while(iterator.hasNext()){
                    StringTokenizer stringTokenizer=new StringTokenizer((String) iterator.next());
                    String proId=stringTokenizer.nextToken();
                    stringTokenizer.nextToken();stringTokenizer.nextToken();stringTokenizer.nextToken();stringTokenizer.nextToken();
                    if(processId.equals(proId))
                        mcount++;
                }
                if(tmp_priority1[i]==priority&& mcount==0){
                    count++; //동일한 우선순위가 있는지 확인
                    tmp_q.add(tmp_arr[j]);
                }
            }
            if(count==1){
                while(!tmp_q.isEmpty()){
                    q.add(tmp_q.poll());
                }
            }
            else if(count>1){
                String str1=tmp_q.poll();
                String str2=tmp_q.poll();
                StringTokenizer stringTokenizer1=new StringTokenizer(str1);
                StringTokenizer stringTokenizer2=new StringTokenizer(str2);
                while(stringTokenizer1.hasMoreTokens()&&stringTokenizer2.hasMoreTokens()){
                    stringTokenizer1.nextToken();
                    int atime1=Integer.parseInt(stringTokenizer1.nextToken());
                    stringTokenizer1.nextToken();stringTokenizer1.nextToken();stringTokenizer1.nextToken();
                    stringTokenizer2.nextToken();
                    int atime2=Integer.parseInt(stringTokenizer2.nextToken());
                    stringTokenizer2.nextToken();stringTokenizer2.nextToken();stringTokenizer2.nextToken();
                    if(atime1>atime2)
                        q.add(str2);
                    else
                        q.add(str1);
                }
            }
        }

        q.addFirst(cpu_process[0]);
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
        j = 0;
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
        System.out.println("평균 반환 시간(ATT): " + re_time_sum / process.length);
    }

    public static void main(String[] args) {
        Priority_Scheduling_비선점 priority_scheduling_비선점=new Priority_Scheduling_비선점();
        priority_scheduling_비선점.run();
    }
}
