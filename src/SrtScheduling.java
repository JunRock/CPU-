import java.util.*;
public class SrtScheduling extends ProcessSort{
    public void run(){
        process= fileOpen();
        int c=0,gc=0,totalServiceTime=0;
        arriveTimeSort(); //도착시간대로 정렬
        timeSort(); //정렬된 도착시간에 맞게 프로세스들의 각종 정보들 정렬
        String[] ganttChatt=new String[serviceTimeSum];

        StringTokenizer processRealToken=new StringTokenizer(srtProcess[1]); //도착시간이 0인 프로세스 정보
            processId = processRealToken.nextToken();
            arriveTime = Integer.parseInt(processRealToken.nextToken());
            serviceTime = Integer.parseInt(processRealToken.nextToken());
            processRealToken.nextToken();processRealToken.nextToken();
            if (saveServiceTime[1] >= timeQuantum) { //도착시간이 0인 프로세스의 실행시간이 타임슬라이스보다 큰 경우
                waitTime[1] += (totalServiceTime - tmpArriveTime[1]); //대기시간 저장
                saveServiceTime[1] -= timeQuantum;
                tmpResponseTime[1]= (int) ((restime[1]+totalServiceTime)- arriveTime); //응답시간 계산
                totalServiceTime += timeQuantum;
                tmpArriveTime[1] = totalServiceTime;
                for(int m = 0; m< timeQuantum; m++)
                    ganttChatt[gc++]= processId;
                if (saveServiceTime[1] == 0) { //프로세스가 주어진 작업을 다한 경우
                    returnTime[1] = (int) (totalServiceTime - arriveTime); //반환시간 계산
                }
            }

            else if (saveServiceTime[1] != 0 && saveServiceTime[1] < timeQuantum) { //도착시간이 0인 프로세스의 실행시간이 타임슬라이스보다 작은경우
                waitTime[1] += (totalServiceTime - tmpArriveTime[1]); //대기시간 저장
                tmpResponseTime[1]= (int) ((restime[1]+totalServiceTime)- arriveTime); //반응시간 계산
                totalServiceTime += saveServiceTime[1];
                for(int m = 0; m< saveServiceTime[1]; m++)
                    ganttChatt[gc++]= processId;
            }

        while(totalServiceTime!= serviceTimeSum) { //현재까지의 실행시간이 전체 실행시간이 되기 전까지 반복
            int index=0;
            if (c!= processCount) {
                int min=10000; //최소 실행시간을 매우 큰 수로 임의지정
                for(int i = 1; i<= processCount; i++){
                    /*
                    최단 작업시간이면서 수행할 작업이 남아있고 현재까지의 실행시간안에 도착한 프로세스와 남은 작업시간이 같은 경우 먼저 도착한 프로세스 먼저 수행하는 조건
                     */
                         if(min>= saveServiceTime[i]&& saveServiceTime[i]!=0&& tmpArriveTime[i]<=totalServiceTime&&min!= saveServiceTime[i]){
                                 min = saveServiceTime[i];
                                 index = i; //조건에 맞는 프로세스의 인덱스를 index에 저장
                             }
                         }

                if (saveServiceTime[index] >= timeQuantum) { //프로세스의 실행시간이 타임슬라이스보다 큰 경우
                    waitTime[index] += (totalServiceTime - tmpArriveTime[index]); //대기시간 저장
                    saveServiceTime[index] -= timeQuantum;
                    if(tmpResponseTime[index]==0) //응답시간을 한번만 계산
                        tmpResponseTime[index]=(restime[index]+totalServiceTime)- tmpArriveTime[index];
                    totalServiceTime += timeQuantum;
                    tmpArriveTime[index] = totalServiceTime;
                    for(int m = 0; m< timeQuantum; m++)
                        ganttChatt[gc++]= tmpProcessId[index];
                    if (saveServiceTime[index] == 0) { //주어진 작업을 프로세스가 다 수행한 경우
                        StringTokenizer processToken=new StringTokenizer(srtProcess[index]);
                        processToken.nextToken();
                        arriveTime =Integer.parseInt(processToken.nextToken());
                        processToken.nextToken();processToken.nextToken();
                        returnTime[index] = (int) (totalServiceTime - arriveTime); //반환시간 계산
                        c++;
                        continue;
                    }
                }
                else if (saveServiceTime[index] != 0 && saveServiceTime[index] < timeQuantum) { //프로세스 남은 작업시간이 0이 아니고 타임슬라이스보다 적은경우
                    waitTime[index] += (totalServiceTime - tmpArriveTime[index]); //대기시간 저장
                    if(tmpResponseTime[index]==0)
                        tmpResponseTime[index]=(restime[index]+totalServiceTime)- tmpArriveTime[index];
                    totalServiceTime += saveServiceTime[index];
                    for(int m = 0; m< saveServiceTime[index]; m++)
                        ganttChatt[gc++]= tmpProcessId[index];
                }
                }
            }
        System.out.println("선점형 - SRT스케줄링");
        PreemptivePrintProcess print_process=new PreemptivePrintProcess();
        print_process.print(processCount, waitTime, tmpProcessId, returnTime,ganttChatt, tmpResponseTime);
    }
}