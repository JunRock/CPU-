import java.util.*;

public class PreemptivePriority_Scheduling {
    public void run() {
        FIle_Open fIle_open=new FIle_Open();
        String []process;
        process=fIle_open.open(); //파일에서 process배열에 저장

        int process_count=Integer.parseInt(process[0]);
        Vector<Integer>v=new Vector<>();
        Vector<Integer>sertime_v=new Vector<>();
        Vector<Integer>arrtime_v=new Vector<>();
        String processId = null;
        int arriveTime = 0, serviceTime; //프로세스ID, 도착시간, 작업시간, 반환시간
        int priority = 0;
        int servicetime_sum=0;
        int[] tmp_servicetime = new int[process_count+1];
        int[] tmp_arrivetime = new int[process_count+1];
        int[] tmp_priority = new int[process_count+1];
        int[] wait_time=new int[process_count+1];
        int[] return_time=new int[process_count+1];
        String[] tmp_processId = new String[process_count+1];
        String[] cpu_process = new String[process_count+1];
        int [] save_servicetime=new int[process_count+1];
        int index = 0;
        int c=1;

        for (int i = 1; i <= process_count; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
            while (st.hasMoreTokens()) {
                tmp_processId[i] = st.nextToken(); //프로세스 ID
                tmp_arrivetime[i] = Integer.parseInt(st.nextToken()); //도착시간
                tmp_servicetime[i] = Integer.parseInt(st.nextToken()); //실행시간
                tmp_priority[i] = Integer.parseInt(st.nextToken()); //우선순위
                servicetime_sum+=tmp_servicetime[i];
            }
        }
        Arrays.sort(tmp_priority);
/*
프로세스 정렬
 */
        for(int i=1;i<=process_count;i++){
            int count=0;
            for(int j=1;j<=process_count;j++){
                StringTokenizer st=new StringTokenizer(process[j]);
                    processId = st.nextToken();
                    arriveTime = Integer.parseInt(st.nextToken());
                    serviceTime = Integer.parseInt(st.nextToken());
                    priority = Integer.parseInt(st.nextToken());
                   if(tmp_priority[i]==priority&&tmp_processId[j]!="0"){
                       count++;
                       v.add(j);//인덱스를 담음
                       sertime_v.add(serviceTime);
                       arrtime_v.add(arriveTime);
                   }
            }
            if(count==1){
                cpu_process[c] = process[v.get(0)];
                save_servicetime[c]=sertime_v.get(0);
                tmp_processId[v.get(0)]="0";
                v.clear();
                sertime_v.clear();
                arrtime_v.clear();
                c++;
            }
            else if(count>=1) {
                while (!v.isEmpty()) {
                    int min = arrtime_v.get(0);
                    for (int m = 0; m < v.size(); m++) {
                        if (min >= arrtime_v.get(m)) {
                            min = arrtime_v.get(m);
                            index = m;
                        }
                    }
                    cpu_process[c] = process[v.get(index)];
                    save_servicetime[c] = sertime_v.get(index);
                    tmp_processId[v.get(index)] = "0";
                    v.remove(index);
                    sertime_v.remove(index);
                    arrtime_v.remove(index);
                    c++;
                }
            }
        }

        for(int i=1;i<=process_count;i++){
            StringTokenizer st=new StringTokenizer(cpu_process[i]);
            tmp_processId[i] = st.nextToken(); //프로세스 ID
            tmp_arrivetime[i] = Integer.parseInt(st.nextToken()); //도착시간
            tmp_servicetime[i] = Integer.parseInt(st.nextToken()); //실행시간
            tmp_priority[i] = Integer.parseInt(st.nextToken()); //우선순위
        }


        int total_servicetime=0;
        while(total_servicetime!=servicetime_sum){
            for(int i=1;i<=process_count;i++){
                StringTokenizer st=new StringTokenizer(cpu_process[i]);
                processId=st.nextToken();
                arriveTime = Integer.parseInt(st.nextToken());
                serviceTime = Integer.parseInt(st.nextToken());
                priority = Integer.parseInt(st.nextToken());
                if(total_servicetime>=arriveTime&&save_servicetime[i]!=0){
                    wait_time[i]+=(total_servicetime-tmp_arrivetime[i]);
                    total_servicetime++;
                    save_servicetime[i]--;
                    tmp_arrivetime[i]=total_servicetime;
                    return_time[i]=total_servicetime-arriveTime;
                    break;
                }
            }
        }
        Preemptive_Print_Process print_process=new Preemptive_Print_Process();
       // print_process.print(process_count,wait_time,tmp_processId,return_time);
    }

    public static void main(String[] args) {
        PreemptivePriority_Scheduling preemptive_scheduling = new PreemptivePriority_Scheduling();
        preemptive_scheduling.run();
    }
}
