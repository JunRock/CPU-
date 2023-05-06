import java.util.*;
public class HRN_Scheduling extends Process_Variable{
    public void run(){
        process=open();
        Deque<String> q = new LinkedList<>();
        Deque<String>tmp_q=new LinkedList<>();
        /*
        도착시간이 0인 프로세스 탐색
         */
        for(int i=1;i<=process_count;i++){
            StringTokenizer st=new StringTokenizer(process[i]);
                processId = st.nextToken();
                arriveTime = Double.parseDouble(st.nextToken());
                serviceTime = Double.parseDouble(st.nextToken());
                st.nextToken();st.nextToken();
                if (arriveTime == 0) {
                    q.addLast(process[i]);
                    tmp_q.addLast(process[i]);
                }
        }
        /*
        덱에서 하나씩 꺼내서 HRN우선순위 판별
         */
        String tmp = null;
        for(int i=0;i< process_count;i++){
            String que=q.peekLast();
            StringTokenizer str=new StringTokenizer(que);
            double max_priority=0;
                str.nextToken();str.nextToken();
                serviceTime=Double.parseDouble(str.nextToken());
                sum +=serviceTime; //실행시간 총합
                str.nextToken();str.nextToken();

            for(int k=1;k<=process_count;k++){
                int count=0;
                StringTokenizer st1=new StringTokenizer(process[k]);
                    processId=st1.nextToken();
                    arriveTime=Double.parseDouble(st1.nextToken());
                    serviceTime=Double.parseDouble(st1.nextToken());
                    /*
                    이미 큐 안에 동일한 프로세스가 들어가 있는지 확인
                     */
                    Iterator it= q.iterator();
                    while(it.hasNext()){
                        String s= (String) it.next();
                        StringTokenizer s1=new StringTokenizer(s);
                        while(s1.hasMoreTokens()){
                            String Id=s1.nextToken();
                            if(Id.equals(processId))
                                count++;
                        }
                    }
                    if(count==0) { //실제 우선순위를 구하는 작업을 하는 조건문
                        double m=(serviceTime+(sum-arriveTime))/serviceTime;
                        if(max_priority<m){
                            max_priority=Math.max(max_priority,m);
                            tmp=process[k];
                        }
                    }
                    st1.nextToken();st1.nextToken();
            }
            q.addLast(tmp);
            tmp_q.add(tmp);
        }
        tmp_q.pollLast(); //마지막 요소 삭제
        System.out.println("비선점형 - HRN스케줄링");
        Nonpreemptive_Print_Process print_process=new Nonpreemptive_Print_Process();
        print_process.print(tmp_q,process_count);
    }
}
