import java.util.*;

public class RR_Scheduling {
    public void run(){
        FIle_Open fIle_open=new FIle_Open();
        String []process;
        process=fIle_open.open();
        int num=FIle_Open.num;

        Queue<String> q=new LinkedList<>();
        int process_count=Integer.parseInt(process[0]);
        int time_quantum=Integer.parseInt(process[num-1]);
        int[] tmp_time = new int[process_count+1];
        int[] return_time=new int[process_count+1];
        String processId;
        String [] save_pid=new String[process_count+1];
        int arriveTime, serviceTime; //프로세스ID, 도착시간, 작업시간, 반환시간
        int [] save_servicetime=new int[process_count+1]; //변경되는 실행시간 저장 배열
        int servicetime_sum=0;
        int[] wait_time=new int[process_count+1];
        int[] tmp_servicetime=new int[process_count+1];
        int[] tmp_arrivetime=new int[process_count+1];

        for(int i=1;i<=process_count;i++){
            StringTokenizer st=new StringTokenizer(process[i]);
            while(st.hasMoreTokens()){
                processId=st.nextToken();
                arriveTime=Integer.parseInt(st.nextToken());
                serviceTime=Integer.parseInt(st.nextToken());
                st.nextToken();//우선순위
                tmp_time[i]=arriveTime;
                servicetime_sum+=serviceTime; //총 실행시간 저장
            }
        }
        String[] ganttchatt=new String[servicetime_sum];
        Arrays.sort(tmp_time);
        /*
        도착시간 정렬
         */
        int same_Id=0;
        for(int i=1;i<=process_count;i++){
            for(int j=1;j<=process_count;j++){
                StringTokenizer st=new StringTokenizer(process[j]);
                while(st.hasMoreTokens()){
                    processId=st.nextToken();
                    arriveTime=Integer.parseInt(st.nextToken());
                    serviceTime=Integer.parseInt(st.nextToken());
                    st.nextToken();//우선순위

                    if(tmp_time[i]==arriveTime){
                        q.add(process[j]);
                        save_servicetime[i]=serviceTime;
                        tmp_arrivetime[i]=arriveTime;
                        save_pid[i]=processId;
                    }
                }
            }
        }

        Deque<String> tmp_q=new LinkedList<>();
        int c=0;
        int total_servicetime=0;

        while(total_servicetime!=servicetime_sum) {
                if (q.size() != 0) {
                    String str = q.poll();
                    StringTokenizer st = new StringTokenizer(str);
                    while (st.hasMoreTokens()) {
                        processId = st.nextToken();
                        arriveTime = Integer.parseInt(st.nextToken());
                        serviceTime = Integer.parseInt(st.nextToken());
                        st.nextToken();
                        for (int i = 1; i <= process_count; i++) {
                            if (tmp_time[i] == arriveTime) {
                                if (save_servicetime[i] >= time_quantum) {
                                    wait_time[i] += (total_servicetime - tmp_arrivetime[i]); //대기시간 저장
                                    save_servicetime[i] -= time_quantum;
                                    total_servicetime += time_quantum;
                                    tmp_arrivetime[i] = total_servicetime;
                                    for(int m=0;m<time_quantum;m++)
                                        ganttchatt[c++]=processId;
                                    if(save_servicetime[i]==0){
                                        return_time[i]=total_servicetime-arriveTime;
                                        break;
                                    }
                                    q.add(str);
                                }
                                else if (save_servicetime[i] != 0 && save_servicetime[i] < time_quantum) {
                                    wait_time[i] += (total_servicetime - tmp_arrivetime[i]);
                                    total_servicetime += save_servicetime[i];
                                    for(int m=0;m<save_servicetime[i];m++)
                                        ganttchatt[c++]=processId;
                                    q.add(str);
                                }
                            }
                        }
                    }
                }
        }

        Preemptive_Print_Process print_process=new Preemptive_Print_Process();
        print_process.print(process_count,wait_time,save_pid,return_time,ganttchatt);
    }

    public static void main(String[] args) {
        RR_Scheduling rr_scheduling=new RR_Scheduling();
        rr_scheduling.run();
    }
}
