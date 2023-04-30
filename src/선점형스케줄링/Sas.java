package 선점형스케줄링;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sas {
    public void run(){
        File f=new File("c:\\Temp\\OS.txt");
        FileReader fin=null;
        BufferedReader br=null;
        Queue<String> q=new LinkedList<>();
        Queue<String>tmp_q=new LinkedList<>(); //동일한 우선순위일 경우 도착시간에 따른 정렬까지 마침
        int [] arrtime=new int[5];
        String processId = null;
        int arriveTime = 0,serviceTime,retime; //프로세스ID, 도착시간, 작업시간, 반환시간
        int priority = 0;
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

        int[] tmp_servicetime = new int[5];
        int[] tmp_arrivetime=new int[5];
        int[] tmp_priority=new int[5];
        String[] tmp_processId=new String[5];

        int sum = 0;
        double arrsum = 0; //프로세스 대기시간 총합
        double re_time_sum = 0;
        int[] time = new int[5]; //프로세스 별 반환시간 저장배열
        String[] cpu_process=new String[5];

        for (int i = 0; i < process.length; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                tmp_arrivetime[i] = Integer.parseInt(st.nextToken()); //도착시간
                tmp_servicetime[i] = Integer.parseInt(st.nextToken()); //실행시간
                tmp_priority[i]=Integer.parseInt(st.nextToken()); //우선순위
                st.nextToken(); //시간 할당량
            }
        }
        Arrays.sort(tmp_arrivetime);

        for(int i=0;i<process.length;i++){
            for(int j=0;j< process.length;j++){
                StringTokenizer st=new StringTokenizer(process[j]);
                while(st.hasMoreTokens()){
                    processId=st.nextToken();
                    arriveTime=Integer.parseInt(st.nextToken());
                    serviceTime=Integer.parseInt(st.nextToken());
                    priority=Integer.parseInt(st.nextToken());
                    st.nextToken();
                    if(tmp_arrivetime[i]==arriveTime){
                        cpu_process[i]=process[j];
                    }
                }
            }
        }

        String[] tmp_arr=new String[cpu_process.length-1];
        String[] arr=new String[cpu_process.length-1];
        int[] tmp_priority1=new int[cpu_process.length-1];
        int[] tmp_servicetime1 = new int[cpu_process.length-1];
        int[] tmp_arrivetime1=new int[cpu_process.length-1];
        for(int i=0;i<cpu_process.length-1;i++){
            tmp_arr[i]=cpu_process[i+1]; //2 3 4 5 프로세스 정렬
        }

        for (int i = 0; i < arr.length; i++) {
            StringTokenizer st = new StringTokenizer(tmp_arr[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                tmp_arrivetime1[i] = Integer.parseInt(st.nextToken()); //도착시간
                tmp_servicetime1[i] = Integer.parseInt(st.nextToken()); //실행시간
                tmp_priority1[i]=Integer.parseInt(st.nextToken()); //우선순위
                st.nextToken(); //시간 할당량
            }
        }
        Arrays.sort(tmp_priority1); //1 2 2 4

        for(int i=0;i<arr.length;i++){
            StringTokenizer st3=new StringTokenizer(tmp_arr[i]);
            while(st3.hasMoreTokens()){
                String processId1=st3.nextToken();
                int arriveTime1=Integer.parseInt(st3.nextToken());
                int serviceTime1=Integer.parseInt(st3.nextToken());
                int priority1=Integer.parseInt(st3.nextToken());
                st3.nextToken();
            for(int j=0;j< arr.length;j++){
                StringTokenizer st=new StringTokenizer(tmp_arr[j]);
                while(st.hasMoreTokens()){
                    processId=st.nextToken();
                    arriveTime=Integer.parseInt(st.nextToken());
                    serviceTime=Integer.parseInt(st.nextToken());
                    priority=Integer.parseInt(st.nextToken());
                    st.nextToken();
                    if(tmp_priority1[i]==priority){
                        if()
                        arr[i]=tmp_arr[j];
                    }
                }
            }
        }
        for(int i=0;i<arr.length;i++)
            System.out.println(arr[i]);

        for(int i=0;i<arr.length;i++){
            StringTokenizer st1=new StringTokenizer(arr[i]);
            while(st1.hasMoreTokens()) {
                processId = st1.nextToken();
                arriveTime = Integer.parseInt(st1.nextToken());
                serviceTime = Integer.parseInt(st1.nextToken());
                priority = Integer.parseInt(st1.nextToken());
                st1.nextToken();
            }
            int count=0;
            String []tmp=new String[1];
            for(int j=i+1;j<arr.length;j++){
                StringTokenizer st2=new StringTokenizer(arr[j]);
                int tmp_arr1 = 0,tmp_ser,tmp_pri;
                while (st2.hasMoreTokens()){
                    String tmp_pId=st2.nextToken();
                    tmp_arr1=Integer.parseInt(st2.nextToken());
                    tmp_ser=Integer.parseInt(st2.nextToken());
                    tmp_pri=Integer.parseInt(st2.nextToken());
                    st2.nextToken();
                    if(priority==tmp_pri)
                        count++;
                }
                if(count==0){
                    cpu_process[i+1]=arr[i];
                }
                else {
                    if(arriveTime>tmp_arr1){
                        tmp[0]=arr[i];
                        arr[i]=arr[j];
                        arr[j]=arr[i];
                    }
                }
            }
        }

        for(int i=1;i<arr.length;i++){
            cpu_process[i]=arr[i];
        }

        for(int i=0;i<cpu_process.length;i++){
            tmp_q.add(cpu_process[i]);
        }

        int j = 0;
        Iterator it = tmp_q.iterator();
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
        it = tmp_q.iterator();
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
        Sas sas=new Sas();
        sas.run();
    }
}
