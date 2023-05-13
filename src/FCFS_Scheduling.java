import java.util.*;
public class FCFS_Scheduling extends ProcessSort{
    public void run() {
        process= FileOpen();
        Deque<String> q = new LinkedList<>();
        ArriveTimeSort(); //도착시간 순 정렬 함수

        for (int i = 1; i <= ProcessCount; i++) {
            for (int k = 1; k <= ProcessCount; k++) {
                StringTokenizer ProcessToken = new StringTokenizer(process[k]);
                    ProcessId = ProcessToken.nextToken();
                    ArriveTime = Integer.parseInt(ProcessToken.nextToken());
                    ServiceTime =Integer.parseInt(ProcessToken.nextToken());
                    ProcessToken.nextToken();ProcessToken.nextToken();
                    if (tmp_time[i] == ArriveTime){ //도착시간 순으로 q에 삽입
                        q.add(process[k]);
                    }
            }
        }
        System.out.println("비선점형 - FCFS스케줄링");
        Nonpreemptive_Print_Process print_process=new Nonpreemptive_Print_Process();
        print_process.print(q, ProcessCount);
    }
}