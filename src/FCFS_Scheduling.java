import java.util.*;
public class FCFS_Scheduling {
    public void run() {
        FIle_Open fIle_open=new FIle_Open();
        String []process;
        process=fIle_open.open();
        int num=FIle_Open.num;

        String processId;
        Deque<String> q = new LinkedList<>();
        int[] arrtime = new int[5];
        int arriveTime, serviceTime; //프로세스ID, 도착시간, 작업시간, 반환시간
        int process_count=Integer.parseInt(process[0]);
        int time_quantum=Integer.parseInt(process[num-1]);
        int[] tmp_time = new int[process_count+1];

        for (int i = 1; i <= process_count; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                arriveTime = Integer.parseInt(st.nextToken()); //도착시간
                serviceTime = Integer.parseInt(st.nextToken()); //실행시간
                st.nextToken(); //우선순위

                tmp_time[i] = arriveTime; //도착시간 모음배열
            }
        }

        Arrays.sort(tmp_time); //도착시간 정렬
        int count = 0;
        int j = 0;
        for (int i = 1; i <=process_count; i++) {
            for (int k = 1; k <= process_count; k++) {
                StringTokenizer st1 = new StringTokenizer(process[k]);
                while (st1.hasMoreTokens()) {
                    processId = st1.nextToken();
                    arriveTime = Integer.parseInt(st1.nextToken());
                    if (tmp_time[i] == arriveTime)
                        q.add(process[k]);
                    st1.nextToken();
                    st1.nextToken();
                }
            }
        }
        Print_Process print_process=new Print_Process();
        print_process.print(q,process_count);
    }

    public static void main(String[] args) {
        FCFS_Scheduling fcfs_scheduling = new FCFS_Scheduling();
        fcfs_scheduling.run();
    }
}