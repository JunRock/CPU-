import java.util.*;
public class PreemptivePriority_Scheduling extends ProcessSort{
    public void run() {
        process=open();
        ArriveTimeSort();
        Vector<Integer>v=new Vector<>();
        Vector<Integer>sertime_v=new Vector<>();
        Vector<Integer>arrtime_v=new Vector<>();
        String[] cpu_process = new String[ProcessCount +1];
        int index = 0,c=1,gc=0;
        String[] ganttchatt=new String[servicetime_sum];
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
                   if(tmp_priority[i]==priority&&tmp_processId[j]!="0"){
                       count++;
                       v.add(j);//인덱스를 담음
                       sertime_v.add((int) ServiceTime);
                       arrtime_v.add((int) ArriveTime);
                   }
            }
            if(count==1){
                cpu_process[c] = process[v.get(0)];
                SaveServiceTime[c]=sertime_v.get(0);
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
                    SaveServiceTime[c] = sertime_v.get(index);
                    tmp_processId[v.get(index)] = "0";
                    v.remove(index);
                    sertime_v.remove(index);
                    arrtime_v.remove(index);
                    c++;
                }
            }
        }

        for(int i = 1; i<= ProcessCount; i++){
            StringTokenizer CpuprocessToken=new StringTokenizer(cpu_process[i]);
            tmp_processId[i] = CpuprocessToken.nextToken(); //프로세스 ID
            tmp_arrivetime[i] = Integer.parseInt(CpuprocessToken.nextToken()); //도착시간
            tmp_servicetime[i] = Integer.parseInt(CpuprocessToken.nextToken()); //실행시간
            tmp_priority[i] = Integer.parseInt(CpuprocessToken.nextToken()); //우선순위
            restime[i]=Integer.parseInt(CpuprocessToken.nextToken());
        }

        int total_servicetime=0;
        while(total_servicetime!=servicetime_sum){
            for(int i = 1; i<= ProcessCount; i++){
                StringTokenizer CpuprocessToken=new StringTokenizer(cpu_process[i]);
                ProcessId =CpuprocessToken.nextToken();
                ArriveTime = Integer.parseInt(CpuprocessToken.nextToken());
                ServiceTime = Integer.parseInt(CpuprocessToken.nextToken());
                priority = Integer.parseInt(CpuprocessToken.nextToken());
                if(total_servicetime>= ArriveTime && SaveServiceTime[i]!=0){
                    wait_time[i]+=(total_servicetime-tmp_arrivetime[i]);
                    if(response_time[i]==0)
                        response_time[i]= (int) ((total_servicetime+restime[i])- ArriveTime);
                    total_servicetime++;
                    SaveServiceTime[i]--;
                    tmp_arrivetime[i]=total_servicetime;
                    ganttchatt[gc++]= ProcessId;
                    return_time[i]= (int) (total_servicetime- ArriveTime);
                    break;
                }
            }
        }
        System.out.println("선점형 - 선점형 우선순위 스케줄링");
        Preemptive_Print_Process print_process=new Preemptive_Print_Process();
        print_process.print(ProcessCount,wait_time,tmp_processId,return_time,ganttchatt,response_time);
    }
}
