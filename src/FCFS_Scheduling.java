import java.util.*;
public class FCFS_Scheduling extends ProcessSort{
    public void run() {
        process=open();
        Deque<String> q = new LinkedList<>();
        TimeSort();

        for (int i = 1; i <= ProcessCount; i++) {
            for (int k = 1; k <= ProcessCount; k++) {
                StringTokenizer st1 = new StringTokenizer(process[k]);
                    ProcessId = st1.nextToken();
                    ArriveTime = Integer.parseInt(st1.nextToken());
                    ServiceTime =Integer.parseInt(st1.nextToken());
                    st1.nextToken();st1.nextToken();
                    if (tmp_time[i] == ArriveTime){
                        q.add(process[k]);
                    }
            }
        }
        System.out.println("비선점형 - FCFS스케줄링");
        Nonpreemptive_Print_Process print_process=new Nonpreemptive_Print_Process();
        print_process.print(q, ProcessCount);
    }
}