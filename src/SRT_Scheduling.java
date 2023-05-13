import java.util.*;
public class SRT_Scheduling extends ProcessSort{
    public void run(){
        process=open();
        Queue<String> q=new LinkedList<>();
        String[] process_real=new String[process_count+1];
        int c=0,gc=0,total_servicetime=0,tmp_index=1,max=0;

        TimeSort();
        String[] ganttchatt=new String[servicetime_sum];
        /*
        도착시간 정렬
         */
        for(int i=1;i<=process_count;i++){
            for(int j=1;j<=process_count;j++){
                StringTokenizer processToken=new StringTokenizer(process[j]);
                    processId=processToken.nextToken();
                    arriveTime=Integer.parseInt(processToken.nextToken());
                    serviceTime=Integer.parseInt(processToken.nextToken());
                    processToken.nextToken();//우선순위
                    responseTime=Integer.parseInt(processToken.nextToken());
                    if(tmp_time[i]==arriveTime){
                        process_real[i]=process[j];
                        save_servicetime[i]= (int) serviceTime;
                        tmp_arrivetime[i]= (int) arriveTime;
                        tmp_processId[i]=processId;
                        restime[i]= (int) responseTime;
                    }
            }
        }

        StringTokenizer processrealToken=new StringTokenizer(process_real[1]);
            processId = processrealToken.nextToken();
            arriveTime = Integer.parseInt(processrealToken.nextToken());
            serviceTime = Integer.parseInt(processrealToken.nextToken());
            processrealToken.nextToken();processrealToken.nextToken();
            if (save_servicetime[1] >= time_quantum) {
                wait_time[1] += (total_servicetime - tmp_arrivetime[1]); //대기시간 저장
                save_servicetime[1] -= time_quantum;
                response_time[1]= (int) ((restime[1]+total_servicetime)-arriveTime);
                total_servicetime += time_quantum;
                tmp_arrivetime[1] = total_servicetime;
                for(int m=0;m<time_quantum;m++)
                    ganttchatt[gc++]=processId;
                if (save_servicetime[1] == 0) {
                    return_time[1] = (int) (total_servicetime - arriveTime);
                    process[tmp_index++]=process_real[1];
                }
            }

            else if (save_servicetime[1] != 0 && save_servicetime[1] < time_quantum) {
                wait_time[1] += (total_servicetime - tmp_arrivetime[1]);
                response_time[1]= (int) ((restime[1]+total_servicetime)-arriveTime);
                total_servicetime += save_servicetime[1];
                for(int m=0;m<save_servicetime[1];m++)
                    ganttchatt[gc++]=processId;
            }

        while(total_servicetime!=servicetime_sum) {
            int index=0;
            if (c!=process_count) {
                int min=10000;
                int dsd=1;
                for(int i=1;i<=process_count;i++){
                         if(min>=save_servicetime[i]&&save_servicetime[i]!=0&&tmp_arrivetime[i]<=total_servicetime&&min!=save_servicetime[i]){
                                 min = save_servicetime[i];
                                 index = i;
                             }
                         }

                if (save_servicetime[index] >= time_quantum) {
                    wait_time[index] += (total_servicetime - tmp_arrivetime[index]); //대기시간 저장
                    save_servicetime[index] -= time_quantum;
                    if(response_time[index]==0)
                        response_time[index]=(restime[index]+total_servicetime)-tmp_arrivetime[index];
                    total_servicetime += time_quantum;
                    tmp_arrivetime[index] = total_servicetime;
                    for(int m=0;m<time_quantum;m++)
                        ganttchatt[gc++]=tmp_processId[index];
                    if (save_servicetime[index] == 0) {
                        StringTokenizer processToken=new StringTokenizer(process_real[index]);
                        processToken.nextToken();
                        arriveTime=Integer.parseInt(processToken.nextToken());
                        processToken.nextToken();processToken.nextToken();
                        return_time[index] = (int) (total_servicetime - arriveTime);
                        c++;
                        continue;
                    }
                }
                else if (save_servicetime[index] != 0 && save_servicetime[index] < time_quantum) {
                    wait_time[index] += (total_servicetime - tmp_arrivetime[index]);
                    if(response_time[index]==0)
                        response_time[index]=(restime[index]+total_servicetime)-tmp_arrivetime[index];
                    total_servicetime += save_servicetime[index];
                    for(int m=0;m<save_servicetime[index];m++)
                        ganttchatt[gc++]=tmp_processId[index];
                }
                }
            }
        System.out.println("선점형 - SRT스케줄링");
        Preemptive_Print_Process print_process=new Preemptive_Print_Process();
        print_process.print(process_count,wait_time,tmp_processId,return_time,ganttchatt,response_time);
    }
}