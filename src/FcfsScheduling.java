import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class FcfsScheduling extends ProcessSort {
    public void run() {
        process = fileOpen();
        Deque<String> q = new LinkedList<>();
        arriveTimeSort(); //도착시간대로 정렬

        for (int i = 1; i <= processCount; i++) {
            for (int k = 1; k <= processCount; k++) {
                StringTokenizer ProcessToken = new StringTokenizer(process[k]);
                processId = ProcessToken.nextToken();
                arriveTime = Integer.parseInt(ProcessToken.nextToken());
                serviceTime = Integer.parseInt(ProcessToken.nextToken());
                ProcessToken.nextToken();
                ProcessToken.nextToken();
                if (tmpTime[i] == arriveTime) { //도착시간 순으로 q에 삽입
                    q.add(process[k]);
                }
            }
        }
        System.out.println("비선점형 - FCFS스케줄링");
        NonpreemptivePrintProcess print_process = new NonpreemptivePrintProcess();
        print_process.print(q, processCount);
    }
}