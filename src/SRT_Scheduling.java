import java.util.*;
public class SRT_Scheduling extends ProcessSort{
    public void run(){
        process= FileOpen();
        int c=0,gc=0,total_servicetime=0,tmp_index=1,max=0;
        ArriveTimeSort();
        TimeSort();
        String[] ganttchatt=new String[ServiceTimeSum];

        StringTokenizer processrealToken=new StringTokenizer(SrtProcess[1]);
            ProcessId = processrealToken.nextToken();
            ArriveTime = Integer.parseInt(processrealToken.nextToken());
            ServiceTime = Integer.parseInt(processrealToken.nextToken());
            processrealToken.nextToken();processrealToken.nextToken();
            if (SaveServiceTime[1] >= TimeQuantum) {
                wait_time[1] += (total_servicetime - tmp_arrivetime[1]); //대기시간 저장
                SaveServiceTime[1] -= TimeQuantum;
                response_time[1]= (int) ((restime[1]+total_servicetime)- ArriveTime);
                total_servicetime += TimeQuantum;
                tmp_arrivetime[1] = total_servicetime;
                for(int m = 0; m< TimeQuantum; m++)
                    ganttchatt[gc++]= ProcessId;
                if (SaveServiceTime[1] == 0) {
                    return_time[1] = (int) (total_servicetime - ArriveTime);
                    process[tmp_index++]= SrtProcess[1];
                }
            }

            else if (SaveServiceTime[1] != 0 && SaveServiceTime[1] < TimeQuantum) {
                wait_time[1] += (total_servicetime - tmp_arrivetime[1]);
                response_time[1]= (int) ((restime[1]+total_servicetime)- ArriveTime);
                total_servicetime += SaveServiceTime[1];
                for(int m = 0; m< SaveServiceTime[1]; m++)
                    ganttchatt[gc++]= ProcessId;
            }

        while(total_servicetime!= ServiceTimeSum) {
            int index=0;
            if (c!= ProcessCount) {
                int min=10000;
                for(int i = 1; i<= ProcessCount; i++){
                         if(min>= SaveServiceTime[i]&& SaveServiceTime[i]!=0&&tmp_arrivetime[i]<=total_servicetime&&min!= SaveServiceTime[i]){
                                 min = SaveServiceTime[i];
                                 index = i;
                             }
                         }

                if (SaveServiceTime[index] >= TimeQuantum) {
                    wait_time[index] += (total_servicetime - tmp_arrivetime[index]); //대기시간 저장
                    SaveServiceTime[index] -= TimeQuantum;
                    if(response_time[index]==0)
                        response_time[index]=(restime[index]+total_servicetime)-tmp_arrivetime[index];
                    total_servicetime += TimeQuantum;
                    tmp_arrivetime[index] = total_servicetime;
                    for(int m = 0; m< TimeQuantum; m++)
                        ganttchatt[gc++]=tmp_processId[index];
                    if (SaveServiceTime[index] == 0) {
                        StringTokenizer processToken=new StringTokenizer(SrtProcess[index]);
                        processToken.nextToken();
                        ArriveTime =Integer.parseInt(processToken.nextToken());
                        processToken.nextToken();processToken.nextToken();
                        return_time[index] = (int) (total_servicetime - ArriveTime);
                        c++;
                        continue;
                    }
                }
                else if (SaveServiceTime[index] != 0 && SaveServiceTime[index] < TimeQuantum) {
                    wait_time[index] += (total_servicetime - tmp_arrivetime[index]);
                    if(response_time[index]==0)
                        response_time[index]=(restime[index]+total_servicetime)-tmp_arrivetime[index];
                    total_servicetime += SaveServiceTime[index];
                    for(int m = 0; m< SaveServiceTime[index]; m++)
                        ganttchatt[gc++]=tmp_processId[index];
                }
                }
            }
        System.out.println("선점형 - SRT스케줄링");
        Preemptive_Print_Process print_process=new Preemptive_Print_Process();
        print_process.print(ProcessCount,wait_time,tmp_processId,return_time,ganttchatt,response_time);
    }
}