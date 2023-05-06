import java.util.*;

public class NonpreemptivePriority_Scheduling extends Process_Variable{
    public void run(){
        process=open();

        Deque<String> q = new LinkedList<>();
        Queue<String> tmp_q = new LinkedList<>(); //동일한 우선순위일 경우 도착시간에 따른 정렬까지 마침
        String[] cpu_process = new String[process_count+1];

        for (int i = 1; i <= process_count; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
                processId = st.nextToken(); //프로세스 ID
                tmp_arrivetime[i] = Integer.parseInt(st.nextToken()); //도착시간
                tmp_servicetime[i] = Integer.parseInt(st.nextToken()); //실행시간
                tmp_priority[i] = Integer.parseInt(st.nextToken()); //우선순위
                st.nextToken();
        }
        Arrays.sort(tmp_arrivetime);

        for (int i = 1; i <= process_count; i++) {
            for (int j = 1; j <= process_count; j++) {
                StringTokenizer st = new StringTokenizer(process[j]);
                    processId = st.nextToken();
                    arriveTime = Integer.parseInt(st.nextToken());
                    serviceTime = Integer.parseInt(st.nextToken());
                    priority = Integer.parseInt(st.nextToken());
                    st.nextToken();
                    if (tmp_arrivetime[i] == arriveTime) {
                        cpu_process[i] = process[j];
                    }
            }
        }

        String[] tmp_arr = new String[process_count-1];
        String[] arr = new String[process_count-1];
        int[] tmp_priority1 = new int[process_count-1];
        int[] tmp_servicetime1 = new int[process_count-1];
        int[] tmp_arrivetime1 = new int[process_count-1];

        for (int i = 0; i < process_count-1; i++) {
            tmp_arr[i] = cpu_process[i+2]; //2 3 4 5 프로세스 정렬
        }

        for (int i = 0; i <process_count-1; i++) {
            StringTokenizer st = new StringTokenizer(tmp_arr[i]);
                processId = st.nextToken(); //프로세스 ID
                tmp_arrivetime1[i] = Integer.parseInt(st.nextToken()); //도착시간
                tmp_servicetime1[i] = Integer.parseInt(st.nextToken()); //실행시간
                tmp_priority1[i] = Integer.parseInt(st.nextToken()); //우선순위
                st.nextToken();
        }
        Arrays.sort(tmp_priority1); //1 2 2 4

        for(int i=0;i<process_count-1;i++){
            int count=0;
            for(int j=0;j<process_count-1;j++){
                StringTokenizer st=new StringTokenizer(tmp_arr[j]);
                int mcount=0;
                    processId=st.nextToken();
                    arriveTime=Integer.parseInt(st.nextToken());
                    serviceTime=Integer.parseInt(st.nextToken());
                    priority=Integer.parseInt(st.nextToken());
                    st.nextToken();
                Iterator iterator=q.iterator(); //큐에 동일 프로세스가 있는 지 확인
                while(iterator.hasNext()){
                    StringTokenizer stringTokenizer=new StringTokenizer((String) iterator.next());
                    String proId=stringTokenizer.nextToken();
                    stringTokenizer.nextToken();stringTokenizer.nextToken();stringTokenizer.nextToken();stringTokenizer.nextToken();
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
                    stringTokenizer1.nextToken();
                    int atime1=Integer.parseInt(stringTokenizer1.nextToken());
                    stringTokenizer1.nextToken();stringTokenizer1.nextToken();stringTokenizer1.nextToken();
                    stringTokenizer2.nextToken();
                    int atime2=Integer.parseInt(stringTokenizer2.nextToken());
                    stringTokenizer2.nextToken();stringTokenizer2.nextToken();stringTokenizer2.nextToken();
                    if(atime1>atime2)
                        q.add(str2);
                    else
                        q.add(str1);
            }
        }
        q.addFirst(cpu_process[1]);
        System.out.println("비선점형 - 비선점 우선순위 스케줄링");
        Nonpreemptive_Print_Process print_process=new Nonpreemptive_Print_Process();
        print_process.print(q,process_count);
    }

    public static void main(String[] args) {
        NonpreemptivePriority_Scheduling nonpreemptivePriority_scheduling=new NonpreemptivePriority_Scheduling();
        nonpreemptivePriority_scheduling.run();
    }
}
