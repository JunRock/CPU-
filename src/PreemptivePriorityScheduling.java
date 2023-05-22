import java.util.*;
public class PreemptivePriorityScheduling extends ProcessSort{
    public void run() {
        process= fileOpen();
        arriveTimeSort(); //도착시간대로 정렬
        Vector<Integer>v=new Vector<>();
        Vector<Integer>sertimeVector=new Vector<>();
        Vector<Integer>arrtimeVector=new Vector<>();
        String[] cpuProcess = new String[processCount +1];
        int index = 0,c=1,gc=0;
        String[] ganttChatt=new String[serviceTimeSum]; //간트차트 배열
/*
프로세스 정렬
 */
        for(int i = 1; i<= processCount; i++){
            int count=0;
            for(int j = 1; j<= processCount; j++){
                StringTokenizer processToken=new StringTokenizer(process[j]);
                    processId = processToken.nextToken();
                    arriveTime = Integer.parseInt(processToken.nextToken());
                    serviceTime = Integer.parseInt(processToken.nextToken());
                    priority = Integer.parseInt(processToken.nextToken());
                   if(tmpPriority[i]==priority&& tmpProcessId[j]!="0"){ //우선순위대로 정렬, 벡터에 동일한 프로세스가 있는지 검사
                       count++;
                       v.add(j);//인덱스를 담음
                       sertimeVector.add((int) serviceTime);
                       arrtimeVector.add((int) arriveTime);
                   }
            }
            if(count==1){ //동일한 우선순위 프로세스가 없을 때
                cpuProcess[c] = process[v.get(0)]; //우선순위에 맞는 프로세스를 정렬된 cpu_process에 담음
                saveServiceTime[c]=sertimeVector.get(0); //해당 프로세스의 실행시간을 각각의 프로세스 실행시간을 저장하는 배열에 담음
                tmpProcessId[v.get(0)]="0"; //한번 배열에 들어간 프로세스의 아이디를 "0"으로 바꿈
                v.clear();
                sertimeVector.clear();
                arrtimeVector.clear();
                c++; //cpu_process의 인덱스를 하나 증가시킴
            }
            else if(count>=1) { //동일한 우선순위를 가진 프로세스가 있는 경우
                while (!v.isEmpty()) {
                    int min = arrtimeVector.get(0); //도착시간이 빠른 프로세스를 먼저 cpu_process배열에 담음
                    for (int m = 0; m < v.size(); m++) {
                        if (min >= arrtimeVector.get(m)) {
                            min = arrtimeVector.get(m);
                            index = m; //최소 도착시간을 가진 프로세스의 인덱스값을 index배열에 저장
                        }
                    }
                    cpuProcess[c] = process[v.get(index)]; //우선순위에 맞는 프로세스를 정렬된 cpu_process에 담음
                    saveServiceTime[c] = sertimeVector.get(index); //해당 프로세스의 실행시간을 각각의 프로세스 실행시간을 저장하는 배열에 담음
                    tmpProcessId[v.get(index)] = "0"; //한번 배열에 들어간 프로세스의 아이디를 "0"으로 바꿈
                    v.remove(index);
                    sertimeVector.remove(index);
                    arrtimeVector.remove(index);
                    c++; //cpu_process의 인덱스를 하나 증가시킴
                }
            }
        }

        for(int i = 1; i<= processCount; i++){ //정렬된 프로세스들의 정보를 각각의 정보에 맞는 배열에 저장
            StringTokenizer CpuprocessToken=new StringTokenizer(cpuProcess[i]);
            tmpProcessId[i] = CpuprocessToken.nextToken(); //프로세스 ID
            tmpArriveTime[i] = Integer.parseInt(CpuprocessToken.nextToken()); //도착시간
            tmpServiceTime[i] = Integer.parseInt(CpuprocessToken.nextToken()); //실행시간
            tmpPriority[i] = Integer.parseInt(CpuprocessToken.nextToken()); //우선순위
            restime[i]=Integer.parseInt(CpuprocessToken.nextToken()); //응답시간
        }

        int totalServiceTime=0;
        while(totalServiceTime!= serviceTimeSum){ //1초 단위로 현재 실행시간이 전체 프로세스들의 실행시간이 되기 전까지 반복
            for(int i = 1; i<= processCount; i++){
                StringTokenizer CpuprocessToken=new StringTokenizer(cpuProcess[i]);
                processId =CpuprocessToken.nextToken();
                arriveTime = Integer.parseInt(CpuprocessToken.nextToken());
                serviceTime = Integer.parseInt(CpuprocessToken.nextToken());
                priority = Integer.parseInt(CpuprocessToken.nextToken());
                if(totalServiceTime>= arriveTime && saveServiceTime[i]!=0){ //프로세스의 실행시간이 남아있고, 현재작업시간안에 프로세스가 도착한 경우
                    waitTime[i]+=(totalServiceTime- tmpArriveTime[i]); //대기시간 계산
                    if(tmpResponseTime[i]==0) //한번만 응답시간을 계산
                        tmpResponseTime[i]= (int) ((totalServiceTime+restime[i])- arriveTime);
                    totalServiceTime++; //현재까지의 작업시간을 1초 증가
                    saveServiceTime[i]--; //한번 작업한 프로세스의 실행시간을 1초 감소
                    tmpArriveTime[i]=totalServiceTime; //해당 인덱스 프로세스 도착시간을 현재까지 실행한 작업시간으로 초기화
                    ganttChatt[gc++]= processId; //간트차트에 작업을 수행한 프로세스아이디 저장
                    returnTime[i]= (int) (totalServiceTime- arriveTime); //반환시간 계산
                    break;
                }
            }
        }
        System.out.println("선점형 - 선점형 우선순위 스케줄링");
        PreemptivePrintProcess print_process=new PreemptivePrintProcess();
        print_process.print(processCount, waitTime, tmpProcessId, returnTime,ganttChatt, tmpResponseTime);
    }
}
