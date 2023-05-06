import java.util.*;
public class SJF_Scheduling extends Process_Variable{
    public void run(){
        process=open();
        Deque<String> q=new LinkedList<>();

        for (int i = 1; i <= process_count; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                arriveTime = Integer.parseInt(st.nextToken()); //도착시간
                serviceTime = Integer.parseInt(st.nextToken()); //실행시간
                st.nextToken(); //우선순위
                responseTime=Integer.parseInt(st.nextToken());//응답시간
                /*
                프로세스 도착시간 정렬
                 */
                if(arriveTime==0){ //도착시간이 0인 프로세스는 바로 큐에 삽입
                    q.add(process[i]);
                }
                tmp_servicetime[i]= (int) serviceTime; //실행시간 저장
                tmp_arrivetime[i]= (int) arriveTime; //도착시간 저장
                tmp_processId[i]=processId; //프로세스 ID저장
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
                    st1.nextToken();st1.nextToken();
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
                        if (serviceTime == tmp_servicetime[i]) {
                            q.add(process[k]);
                        }
                    }
                }
            }
        }
        System.out.println("비선점형 - SJF스케줄링");
        Nonpreemptive_Print_Process print_process=new Nonpreemptive_Print_Process();
        print_process.print(q,process_count);
    }
}
