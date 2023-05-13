import java.util.*;
public class FCFS_Scheduling extends ProcessSort{
    public void run() {
        process=open();
        Deque<String> q = new LinkedList<>();
        TimeSort();

        for (int i = 1; i <=process_count; i++) {
            for (int k = 1; k <= process_count; k++) {
                StringTokenizer st1 = new StringTokenizer(process[k]);
                    processId = st1.nextToken();
                    arriveTime = Integer.parseInt(st1.nextToken());
                    serviceTime=Integer.parseInt(st1.nextToken());
                    st1.nextToken();st1.nextToken();
                    if (tmp_arrivetime[i] == arriveTime){
                        q.add(process[k]);
                    }
            }
        }
        System.out.println("비선점형 - FCFS스케줄링");
        Nonpreemptive_Print_Process print_process=new Nonpreemptive_Print_Process();
        print_process.print(q,process_count);
    }
}