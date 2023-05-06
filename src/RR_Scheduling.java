import java.util.*;

public class RR_Scheduling extends Process_Variable{
    public void run(){
        process=open();
        Queue<String> q=new LinkedList<>();
        int c=0;
        int total_servicetime=0;

        for(int i=1;i<=process_count;i++){
            StringTokenizer st=new StringTokenizer(process[i]);
                processId=st.nextToken();
                arriveTime=Integer.parseInt(st.nextToken());
                serviceTime=Integer.parseInt(st.nextToken());
                st.nextToken(); st.nextToken();
                tmp_time[i]= (int) arriveTime;
                servicetime_sum+=serviceTime; //총 실행시간 저장
        }
        String[] ganttchatt=new String[servicetime_sum];
        Arrays.sort(tmp_time);
        /*
        도착시간 정렬
         */
        for(int i=1;i<=process_count;i++){
            for(int j=1;j<=process_count;j++){
                StringTokenizer st=new StringTokenizer(process[j]);
                while(st.hasMoreTokens()){
                    processId=st.nextToken();
                    arriveTime=Integer.parseInt(st.nextToken());
                    serviceTime=Integer.parseInt(st.nextToken());
                    st.nextToken(); st.nextToken();

                    if(tmp_time[i]==arriveTime){
                        q.add(process[j]);
                        save_servicetime[i]= (int) serviceTime;
                        tmp_arrivetime[i]= (int) arriveTime;
                        tmp_processId[i]=processId;
                    }
                }
            }
        }

        while(total_servicetime!=servicetime_sum) {
                if (q.size() != 0) {
                    String str = q.poll();
                    StringTokenizer st = new StringTokenizer(str);
                        processId = st.nextToken();
                        arriveTime = Integer.parseInt(st.nextToken());
                        serviceTime = Integer.parseInt(st.nextToken());
                        st.nextToken();
                        responseTime=Integer.parseInt(st.nextToken());
                        for (int i = 1; i <= process_count; i++) {
                            if (tmp_time[i] == arriveTime) {
                                if (save_servicetime[i] >= time_quantum) {
                                    wait_time[i] += (total_servicetime - tmp_arrivetime[i]); //대기시간 저장
                                    save_servicetime[i] -= time_quantum;
                                    if(response_time[i]==0)
                                        response_time[i]= (int) ((total_servicetime+responseTime)-arriveTime);
                                    total_servicetime += time_quantum;
                                    tmp_arrivetime[i] = total_servicetime;
                                    for(int m=0;m<time_quantum;m++)
                                        ganttchatt[c++]=processId;
                                    if(save_servicetime[i]==0){
                                        return_time[i]= (int) (total_servicetime-arriveTime);
                                        break;
                                    }
                                    q.add(str);
                                }
                                else if (save_servicetime[i] != 0 && save_servicetime[i] < time_quantum) {
                                    wait_time[i] += (total_servicetime - tmp_arrivetime[i]);
                                    if(response_time[i]==0)
                                        response_time[i]= (int) ((total_servicetime+responseTime)-arriveTime);
                                    total_servicetime += save_servicetime[i];
                                    for(int m=0;m<save_servicetime[i];m++)
                                        ganttchatt[c++]=processId;
                                    q.add(str);
                                }
                            }
                        }
                }
        }
        System.out.println("선점형 - 라운드로빈(RR)스케줄링");
        Preemptive_Print_Process print_process=new Preemptive_Print_Process();
        print_process.print(process_count,wait_time,tmp_processId,return_time,ganttchatt,response_time);
    }
}
