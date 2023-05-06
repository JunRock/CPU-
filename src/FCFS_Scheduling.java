import java.util.*;
public class FCFS_Scheduling extends Process_Variable{
    public void run() {
        process=open();
        Deque<String> q = new LinkedList<>();

        for (int i = 1; i <= process_count; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
                processId = st.nextToken(); //프로세스 ID
                arriveTime = Integer.parseInt(st.nextToken()); //도착시간
                serviceTime = Integer.parseInt(st.nextToken()); //실행시간
                st.nextToken();st.nextToken(); //우선순위
                tmp_time[i] = (int) arriveTime; //도착시간 모음배열
        }

        Arrays.sort(tmp_time); //도착시간 정렬

        for (int i = 1; i <=process_count; i++) {
            for (int k = 1; k <= process_count; k++) {
                StringTokenizer st1 = new StringTokenizer(process[k]);
                    processId = st1.nextToken();
                    arriveTime = Integer.parseInt(st1.nextToken());
                    serviceTime=Integer.parseInt(st1.nextToken());
                    st1.nextToken();st1.nextToken();
                    if (tmp_time[i] == arriveTime){
                        q.add(process[k]);
                    }
            }
        }
        System.out.println("비선점형 - FCFS스케줄링");
        Nonpreemptive_Print_Process print_process=new Nonpreemptive_Print_Process();
        print_process.print(q,process_count);
    }
}