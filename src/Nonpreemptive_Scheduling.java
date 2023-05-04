import java.util.*;

public class Nonpreemptive_Scheduling {
    public void run(){
        FIle_Open fIle_open=new FIle_Open();
        String []process;
        process=fIle_open.open(); //파일에서 process배열에 저장
        int num=FIle_Open.num;

        Deque<String> q = new LinkedList<>();
        Queue<String> tmp_q = new LinkedList<>(); //동일한 우선순위일 경우 도착시간에 따른 정렬까지 마침
        int process_count=Integer.parseInt(process[0]);
        int time_quantum=Integer.parseInt(process[num-1]);

        int[] arrtime = new int[process_count];
        String processId = null;
        int arriveTime = 0, serviceTime; //프로세스ID, 도착시간, 작업시간, 반환시간
        int priority = 0;

        int[] tmp_servicetime = new int[process_count];
        int[] tmp_arrivetime = new int[process_count];
        int[] tmp_priority = new int[process_count];
        String[] tmp_processId = new String[process_count];
        String[] cpu_process = new String[process_count];

        for (int i = 1; i <= process_count; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                tmp_arrivetime[i-1] = Integer.parseInt(st.nextToken()); //도착시간
                tmp_servicetime[i-1] = Integer.parseInt(st.nextToken()); //실행시간
                tmp_priority[i-1] = Integer.parseInt(st.nextToken()); //우선순위
            }
        }
        Arrays.sort(tmp_arrivetime);

        for (int i = 1; i <= process_count; i++) {
            for (int j = 1; j <= process_count; j++) {
                StringTokenizer st = new StringTokenizer(process[j]);
                while (st.hasMoreTokens()) {
                    processId = st.nextToken();
                    arriveTime = Integer.parseInt(st.nextToken());
                    serviceTime = Integer.parseInt(st.nextToken());
                    priority = Integer.parseInt(st.nextToken());
                    if (tmp_arrivetime[i-1] == arriveTime) {
                        cpu_process[i-1] = process[j];
                    }
                }
            }
        }

        String[] tmp_arr = new String[cpu_process.length - 1];
        String[] arr = new String[cpu_process.length - 1];
        int[] tmp_priority1 = new int[cpu_process.length - 1];
        int[] tmp_servicetime1 = new int[cpu_process.length - 1];
        int[] tmp_arrivetime1 = new int[cpu_process.length - 1];

        for (int i = 0; i < cpu_process.length - 1; i++) {
            tmp_arr[i] = cpu_process[i + 1]; //2 3 4 5 프로세스 정렬
        }

        for (int i = 0; i < arr.length; i++) {
            StringTokenizer st = new StringTokenizer(tmp_arr[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                tmp_arrivetime1[i] = Integer.parseInt(st.nextToken()); //도착시간
                tmp_servicetime1[i] = Integer.parseInt(st.nextToken()); //실행시간
                tmp_priority1[i] = Integer.parseInt(st.nextToken()); //우선순위
            }
        }
        Arrays.sort(tmp_priority1); //1 2 2 4

        for(int i=0;i<arr.length;i++){
            int count=0;
            for(int j=0;j<arr.length;j++){
                StringTokenizer st=new StringTokenizer(tmp_arr[j]);
                int mcount=0;
                while(st.hasMoreTokens()){
                    processId=st.nextToken();
                    arriveTime=Integer.parseInt(st.nextToken());
                    serviceTime=Integer.parseInt(st.nextToken());
                    priority=Integer.parseInt(st.nextToken());
                }
                Iterator iterator=q.iterator(); //큐에 동일 프로세스가 있는 지 확인
                while(iterator.hasNext()){
                    StringTokenizer stringTokenizer=new StringTokenizer((String) iterator.next());
                    String proId=stringTokenizer.nextToken();
                    stringTokenizer.nextToken();stringTokenizer.nextToken();stringTokenizer.nextToken();
                    if(processId.equals(proId))
                        mcount++;
                }
                if(tmp_priority1[i]==priority&& mcount==0){
                    count++; //동일한 우선순위가 있는지 확인
                    tmp_q.add(tmp_arr[j]);
                }
            }
            if(count==1){
                while(!tmp_q.isEmpty()){
                    q.add(tmp_q.poll());
                }
            }
            else if(count>1){
                String str1=tmp_q.poll();
                String str2=tmp_q.poll();
                StringTokenizer stringTokenizer1=new StringTokenizer(str1);
                StringTokenizer stringTokenizer2=new StringTokenizer(str2);
                while(stringTokenizer1.hasMoreTokens()&&stringTokenizer2.hasMoreTokens()){
                    stringTokenizer1.nextToken();
                    int atime1=Integer.parseInt(stringTokenizer1.nextToken());
                    stringTokenizer1.nextToken();stringTokenizer1.nextToken();
                    stringTokenizer2.nextToken();
                    int atime2=Integer.parseInt(stringTokenizer2.nextToken());
                    stringTokenizer2.nextToken();stringTokenizer2.nextToken();
                    if(atime1>atime2)
                        q.add(str2);
                    else
                        q.add(str1);
                }
            }
        }

        q.addFirst(cpu_process[0]);
        Print_Process print_process=new Print_Process();
        print_process.print(q,process_count);
    }

    public static void main(String[] args) {
        Nonpreemptive_Scheduling priority_scheduling_비선점=new Nonpreemptive_Scheduling();
        priority_scheduling_비선점.run();
    }
}
