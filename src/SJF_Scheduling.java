import java.util.*;
public class SJF_Scheduling {
    public void run(){
        FIle_Open fIle_open=new FIle_Open();
        String []process;
        process=fIle_open.open();
        int num=FIle_Open.num;

        Deque<String> q=new LinkedList<>();
        String processId;
        int arriveTime,serviceTime; //프로세스ID, 도착시간, 작업시간, 반환시간
        int process_count=Integer.parseInt(process[0]);
        int time_quantum=Integer.parseInt(process[num-1]);
        /*
        최단 작업 시간 프로세스순대로 정렬
         */
        int[] tmp_servicetime = new int[process_count];
        int[] tmp_arrivetime=new int[process_count];
        String[] tmp_processId=new String[process_count];
        int total_servicetime=0;

        for (int i = 1; i <= process_count; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                arriveTime = Integer.parseInt(st.nextToken()); //도착시간
                serviceTime = Integer.parseInt(st.nextToken()); //실행시간
                st.nextToken(); //우선순위
                /*
                프로세스 도착시간 정렬
                 */
                if(arriveTime==0){ //도착시간이 0인 프로세스는 바로 큐에 삽입
                    q.add(process[i]);
                }
                tmp_servicetime[i-1]=serviceTime; //실행시간 저장
                tmp_arrivetime[i-1]=arriveTime; //도착시간 저장
                tmp_processId[i-1]=processId; //프로세스 ID저장
            }
        }

        Arrays.sort(tmp_servicetime);

        for(int i=1;i<= process_count;i++){
            for(int k=1;k<= process_count;k++){
                int count=0;
                StringTokenizer st1=new StringTokenizer(process[k]);
                while(st1.hasMoreTokens()){
                    processId=st1.nextToken();
                    arriveTime=Integer.parseInt(st1.nextToken());
                    serviceTime=Integer.parseInt(st1.nextToken());
                    /*
                    이미 큐 안에 동일한 프로세스가 들어가 있는지 확인
                    */
                    Iterator it=q.iterator();
                    while(it.hasNext()){
                        String s= (String) it.next();
                        StringTokenizer s1=new StringTokenizer(s);
                        while(s1.hasMoreTokens()){
                            String Id=s1.nextToken();
                            if(Id.equals(processId))
                                count++;
                        }
                    }

                    if(count==0) {
                        //total_servicetime+=serviceTime; &&arriveTime<=total_servicetime
                        if (serviceTime == tmp_servicetime[i-1]) {
                            q.add(process[k]);
                        }
                    }
                    st1.nextToken();
                }
            }
        }
        Print_Process print_process=new Print_Process();
        print_process.print(q,process_count);
    }

    public static void main(String[] args) {
        SJF_Scheduling sjf_scheduling=new SJF_Scheduling();
        sjf_scheduling.run();
    }
}
