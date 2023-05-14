import java.util.*;
public class SRT_Scheduling extends ProcessSort{
    public void run(){
        process= FileOpen();
        int c=0,gc=0,total_servicetime=0,tmp_index=1,max=0;
        ArriveTimeSort(); //도착시간대로 정렬
        TimeSort(); //정렬된 도착시간에 맞게 프로세스들의 각종 정보들 정렬
        String[] ganttchatt=new String[ServiceTimeSum];

        StringTokenizer processrealToken=new StringTokenizer(SrtProcess[1]); //도착시간이 0인 프로세스 정보
            ProcessId = processrealToken.nextToken();
            ArriveTime = Integer.parseInt(processrealToken.nextToken());
            ServiceTime = Integer.parseInt(processrealToken.nextToken());
            processrealToken.nextToken();processrealToken.nextToken();
            if (SaveServiceTime[1] >= TimeQuantum) { //도착시간이 0인 프로세스의 실행시간이 타임슬라이스보다 큰 경우
                wait_time[1] += (total_servicetime - tmp_arrivetime[1]); //대기시간 저장
                SaveServiceTime[1] -= TimeQuantum;
                response_time[1]= (int) ((restime[1]+total_servicetime)- ArriveTime); //응답시간 계산
                total_servicetime += TimeQuantum;
                tmp_arrivetime[1] = total_servicetime;
                for(int m = 0; m< TimeQuantum; m++)
                    ganttchatt[gc++]= ProcessId;
                if (SaveServiceTime[1] == 0) { //프로세스가 주어진 작업을 다한 경우
                    return_time[1] = (int) (total_servicetime - ArriveTime); //반환시간 계산
                    process[tmp_index++]= SrtProcess[1]; //process베열에 작업을 다한 프로세스 정보 저장
                }
            }

            else if (SaveServiceTime[1] != 0 && SaveServiceTime[1] < TimeQuantum) { //도착시간이 0인 프로세스의 실행시간이 타임슬라이스보다 작은경우
                wait_time[1] += (total_servicetime - tmp_arrivetime[1]); //대기시간 저장
                response_time[1]= (int) ((restime[1]+total_servicetime)- ArriveTime); //반응시간 계산
                total_servicetime += SaveServiceTime[1];
                for(int m = 0; m< SaveServiceTime[1]; m++)
                    ganttchatt[gc++]= ProcessId;
            }

        while(total_servicetime!= ServiceTimeSum) { //현재까지의 실행시간이 전체 실행시간이 되기 전까지 반복
            int index=0;
            if (c!= ProcessCount) {
                int min=10000; //최소 실행시간을 매우 큰 수로 임의지정
                for(int i = 1; i<= ProcessCount; i++){
                    /*
                    최단 작업시간이면서 수행할 작업이 남아있고 현재까지의 실행시간안에 도착한 프로세스와 남은 작업시간이 같은 경우 먼저 도착한 프로세스 먼저 수행하는 조건
                     */
                         if(min>= SaveServiceTime[i]&& SaveServiceTime[i]!=0&&tmp_arrivetime[i]<=total_servicetime&&min!= SaveServiceTime[i]){
                                 min = SaveServiceTime[i];
                                 index = i; //조건에 맞는 프로세스의 인덱스를 index에 저장
                             }
                         }

                if (SaveServiceTime[index] >= TimeQuantum) { //프로세스의 실행시간이 타임슬라이스보다 큰 경우
                    wait_time[index] += (total_servicetime - tmp_arrivetime[index]); //대기시간 저장
                    SaveServiceTime[index] -= TimeQuantum;
                    if(response_time[index]==0) //응답시간을 한번만 계산
                        response_time[index]=(restime[index]+total_servicetime)-tmp_arrivetime[index];
                    total_servicetime += TimeQuantum;
                    tmp_arrivetime[index] = total_servicetime;
                    for(int m = 0; m< TimeQuantum; m++)
                        ganttchatt[gc++]=tmp_processId[index];
                    if (SaveServiceTime[index] == 0) { //주어진 작업을 프로세스가 다 수행한 경우
                        StringTokenizer processToken=new StringTokenizer(SrtProcess[index]);
                        processToken.nextToken();
                        ArriveTime =Integer.parseInt(processToken.nextToken());
                        processToken.nextToken();processToken.nextToken();
                        return_time[index] = (int) (total_servicetime - ArriveTime); //반환시간 계산
                        c++;
                        continue;
                    }
                }
                else if (SaveServiceTime[index] != 0 && SaveServiceTime[index] < TimeQuantum) { //프로세스 남은 작업시간이 0이 아니고 타임슬라이스보다 적은경우
                    wait_time[index] += (total_servicetime - tmp_arrivetime[index]); //대기시간 저장
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