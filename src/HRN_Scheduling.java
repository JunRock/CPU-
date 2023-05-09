import java.util.*;
public class HRN_Scheduling extends Process_Variable{
    public void run(){
        process=open();

        for(int i=1;i<=process_count;i++){
            StringTokenizer processToken=new StringTokenizer(process[i]);
                processId = processToken.nextToken();
                arriveTime = Double.parseDouble(processToken.nextToken());
                serviceTime = Double.parseDouble(processToken.nextToken());
                processToken.nextToken();processToken.nextToken();
                if (arriveTime == 0) {
                    deque.addLast(process[i]);
                    tmp_q.addLast(process[i]);
                }
        }
        /*
        덱에서 하나씩 꺼내서 HRN우선순위 판별
         */
        String tmp = null;
        for(int i=0;i< process_count;i++){
            String que=deque.peekLast();
            StringTokenizer QToken=new StringTokenizer(que);
            double max_priority=0;
                QToken.nextToken();QToken.nextToken();
                serviceTime=Double.parseDouble(QToken.nextToken());
                sum +=serviceTime; //실행시간 총합
                QToken.nextToken();QToken.nextToken();

            for(int k=1;k<=process_count;k++){
                int count=0;
                StringTokenizer processToken=new StringTokenizer(process[k]);
                    processId=processToken.nextToken();
                    arriveTime=Double.parseDouble(processToken.nextToken());
                    serviceTime=Double.parseDouble(processToken.nextToken());
                    /*
                    이미 큐 안에 동일한 프로세스가 들어가 있는지 확인
                     */
                    Iterator it= deque.iterator();
                    while(it.hasNext()){
                        String s= (String) it.next();
                        StringTokenizer QueueToken=new StringTokenizer(s);
                        while(QueueToken.hasMoreTokens()){
                            String Id=QueueToken.nextToken();
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
            }
            deque.addLast(tmp);
            tmp_q.add(tmp);
        }
        tmp_q.pollLast(); //마지막 요소 삭제
        System.out.println("비선점형 - HRN스케줄링");
        Nonpreemptive_Print_Process print_process=new Nonpreemptive_Print_Process();
        print_process.print(tmp_q,process_count);
    }
}
