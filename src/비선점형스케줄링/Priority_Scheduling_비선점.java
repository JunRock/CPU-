package 비선점형스케줄링;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Priority_Scheduling_비선점 {
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
        int resum = 0;
        double re_time_sum = 0;
        int[] time = new int[5]; //프로세스 별 반환시간 저장배열

        int Min = 0;
        for (int i = 0; i < process.length; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                arriveTime = Integer.parseInt(st.nextToken()); //도착시간
                serviceTime = Integer.parseInt(st.nextToken()); //실행시간
                priority=Integer.parseInt(st.nextToken()); //우선순위
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
                tmp_priority[i]=priority; //우선순위 저장
            }
        }

        Arrays.sort(tmp_priority);

        for(int i=0;i< process.length;i++){
            for(int k=0;k< process.length;k++){
                int count=0;
                StringTokenizer st1=new StringTokenizer(process[k]);
                while(st1.hasMoreTokens()){
                    processId=st1.nextToken();
                    arriveTime=Integer.parseInt(st1.nextToken());
                    serviceTime=Integer.parseInt(st1.nextToken());
                    priority=Integer.parseInt(st1.nextToken());
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
                        if (priority == tmp_priority[i]) {
                            q.add(process[k]);
                        }
                    }
                    st1.nextToken();
                }
            }
        }
        int m=0;
        String[] tmp_pri=new String[5];
        Iterator t=q.iterator();
        while(t.hasNext()){
            tmp_pri[m++]= (String) t.next();
        }
        /*
        동일한 우선순위가 있는 경우를 확인, 우선순위 정렬 완료
         */
        for (int i = 0; i < process.length; i++) {
            String str=q.remove();
            StringTokenizer st = new StringTokenizer(str);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                arriveTime = Integer.parseInt(st.nextToken()); //도착시간
                serviceTime = Integer.parseInt(st.nextToken()); //실행시간
                priority=Integer.parseInt(st.nextToken()); //우선순위
                st.nextToken(); //시간 할당량
                /*
                프로세스 도착시간 정렬
                 */
                if(arriveTime==0){ //도착시간이 0인 프로세스는 바로 큐에 삽입
                    tmp_q.add(str); //우선순위, 도착시간을 고려해서 정렬되어있는 큐
                }
                //tmp_servicetime[i]=serviceTime; //실행시간 저장
                tmp_arrivetime[i]=arriveTime; //도착시간 저장
                tmp_processId[i]=processId; //프로세스 ID저장
                //tmp_priority[i]=priority; //우선순위 저장
            }
        }

        for(int i=0;i<tmp_pri.length;i++){
            int count=0;
            String s1=tmp_pri[i];
            StringTokenizer st=new StringTokenizer(s1);
            while(st.hasMoreTokens()){
                processId=st.nextToken();
                arriveTime=Integer.parseInt(st.nextToken());
                serviceTime=Integer.parseInt(st.nextToken());
                priority=Integer.parseInt(st.nextToken());
                st.nextToken();
            }
            //for(int j=0;j<tmp_pri.length;j++){
                Iterator iterator=tmp_q.iterator();
                while(iterator.hasNext()){
                    StringTokenizer st_tmp=new StringTokenizer((String) iterator.next());
                    String Id=st_tmp.nextToken();
                    int tmp_arr=Integer.parseInt(st_tmp.nextToken());
                    int tmp_ser=Integer.parseInt(st_tmp.nextToken());
                    int tmp_p=Integer.parseInt(st_tmp.nextToken());
                    st_tmp.nextToken();
                    if(Id.equals(processId)){
                        count++;
                    }
                }
                if(count==0){
                    int tmp_c=0;
                    Queue<Integer> index=new LinkedList<>();
                    for(int j=0;j<tmp_pri.length;j++){
                        if(priority==tmp_priority[j]){
                            tmp_c++;
                            index.add(j);
                        }
                     }
                    if(tmp_c<2){
                        tmp_q.add(tmp_pri[i]);
                    }
                    else{
                        for(int in=0;in<index.size();in++){
                            int tmp_index=index.remove();
                            if(i==tmp_index)
                                continue;
                            else{
                                if(arriveTime>tmp_arrivetime[tmp_index]){
                                    tmp_q.add(tmp_pri[tmp_index]);
                                }
                            }
                        }
                    }
                 }
        }
        /*
        출력문
         */
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
        Priority_Scheduling_비선점 priority_scheduling_비선점=new Priority_Scheduling_비선점();
        priority_scheduling_비선점.run();
    }
}
