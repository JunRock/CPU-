import java.util.*;
public class PreemptivePriority_Scheduling extends ProcessSort{
    public void run() {
        process= FileOpen();
        ArriveTimeSort(); //도착시간대로 정렬
        Vector<Integer>v=new Vector<>();
        Vector<Integer>sertime_v=new Vector<>();
        Vector<Integer>arrtime_v=new Vector<>();
        String[] cpu_process = new String[ProcessCount +1];
        int index = 0,c=1,gc=0;
        String[] GanttChatt=new String[ServiceTimeSum]; //간트차트 배열
/*
프로세스 정렬
 */
        for(int i = 1; i<= ProcessCount; i++){
            int count=0;
            for(int j = 1; j<= ProcessCount; j++){
                StringTokenizer processToken=new StringTokenizer(process[j]);
                    ProcessId = processToken.nextToken();
                    ArriveTime = Integer.parseInt(processToken.nextToken());
                    ServiceTime = Integer.parseInt(processToken.nextToken());
                    priority = Integer.parseInt(processToken.nextToken());
                   if(tmp_priority[i]==priority&&tmp_processId[j]!="0"){ //우선순위대로 정렬, 벡터에 동일한 프로세스가 있는지 검사
                       count++;
                       v.add(j);//인덱스를 담음
                       sertime_v.add((int) ServiceTime);
                       arrtime_v.add((int) ArriveTime);
                   }
            }
            if(count==1){ //동일한 우선순위 프로세스가 없을 때
                cpu_process[c] = process[v.get(0)]; //우선순위에 맞는 프로세스를 정렬된 cpu_process에 담음
                SaveServiceTime[c]=sertime_v.get(0); //해당 프로세스의 실행시간을 각각의 프로세스 실행시간을 저장하는 배열에 담음
                tmp_processId[v.get(0)]="0"; //한번 배열에 들어간 프로세스의 아이디를 "0"으로 바꿈
                v.clear();
                sertime_v.clear();
                arrtime_v.clear();
                c++; //cpu_process의 인덱스를 하나 증가시킴
            }
            else if(count>=1) { //동일한 우선순위를 가진 프로세스가 있는 경우
                while (!v.isEmpty()) {
                    int min = arrtime_v.get(0); //도착시간이 빠른 프로세스를 먼저 cpu_process배열에 담음
                    for (int m = 0; m < v.size(); m++) {
                        if (min >= arrtime_v.get(m)) {
                            min = arrtime_v.get(m);
                            index = m; //최소 도착시간을 가진 프로세스의 인덱스값을 index배열에 저장
                        }
                    }
                    cpu_process[c] = process[v.get(index)]; //우선순위에 맞는 프로세스를 정렬된 cpu_process에 담음
                    SaveServiceTime[c] = sertime_v.get(index); //해당 프로세스의 실행시간을 각각의 프로세스 실행시간을 저장하는 배열에 담음
                    tmp_processId[v.get(index)] = "0"; //한번 배열에 들어간 프로세스의 아이디를 "0"으로 바꿈
                    v.remove(index);
                    sertime_v.remove(index);
                    arrtime_v.remove(index);
                    c++; //cpu_process의 인덱스를 하나 증가시킴
                }
            }
        }

        for(int i = 1; i<= ProcessCount; i++){ //정렬된 프로세스들의 정보를 각각의 정보에 맞는 배열에 저장
            StringTokenizer CpuprocessToken=new StringTokenizer(cpu_process[i]);
            tmp_processId[i] = CpuprocessToken.nextToken(); //프로세스 ID
            tmp_arrivetime[i] = Integer.parseInt(CpuprocessToken.nextToken()); //도착시간
            tmp_servicetime[i] = Integer.parseInt(CpuprocessToken.nextToken()); //실행시간
            tmp_priority[i] = Integer.parseInt(CpuprocessToken.nextToken()); //우선순위
            restime[i]=Integer.parseInt(CpuprocessToken.nextToken()); //응답시간
        }

        int total_servicetime=0;
        while(total_servicetime!= ServiceTimeSum){ //1초 단위로 현재 실행시간이 전체 프로세스들의 실행시간이 되기 전까지 반복
            for(int i = 1; i<= ProcessCount; i++){
                StringTokenizer CpuprocessToken=new StringTokenizer(cpu_process[i]);
                ProcessId =CpuprocessToken.nextToken();
                ArriveTime = Integer.parseInt(CpuprocessToken.nextToken());
                ServiceTime = Integer.parseInt(CpuprocessToken.nextToken());
                priority = Integer.parseInt(CpuprocessToken.nextToken());
                if(total_servicetime>= ArriveTime && SaveServiceTime[i]!=0){ //프로세스의 실행시간이 남아있고, 현재작업시간안에 프로세스가 도착한 경우
                    wait_time[i]+=(total_servicetime-tmp_arrivetime[i]); //대기시간 계산
                    if(response_time[i]==0) //한번만 응답시간을 계산
                        response_time[i]= (int) ((total_servicetime+restime[i])- ArriveTime);
                    total_servicetime++; //현재까지의 작업시간을 1초 증가
                    SaveServiceTime[i]--; //한번 작업한 프로세스의 실행시간을 1초 감소
                    tmp_arrivetime[i]=total_servicetime; //해당 인덱스 프로세스 도착시간을 현재까지 실행한 작업시간으로 초기화
                    GanttChatt[gc++]= ProcessId; //간트차트에 작업을 수행한 프로세스아이디 저장
                    return_time[i]= (int) (total_servicetime- ArriveTime); //반환시간 계산
                    break;
                }
            }
        }
        System.out.println("선점형 - 선점형 우선순위 스케줄링");
        Preemptive_Print_Process print_process=new Preemptive_Print_Process();
        print_process.print(ProcessCount,wait_time,tmp_processId,return_time,GanttChatt,response_time);
    }
}
