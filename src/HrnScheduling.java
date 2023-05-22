import java.util.*;
public class HrnScheduling extends ProcessSort{
    public void run(){
        process= fileOpen();
        arriveTimeSort(); //도착시간대로 정렬
        /*
        덱에서 하나씩 꺼내서 HRN우선순위 판별
         */
        String tmp = null;
        for(int i = 0; i< processCount; i++){
            String que= deque.peekLast(); //덱의 가장 나중에 들어온 프로세스 정보
            StringTokenizer DequeToken=new StringTokenizer(que);
            double maxPriority=0;
                DequeToken.nextToken();DequeToken.nextToken();
                serviceTime =Double.parseDouble(DequeToken.nextToken());
                serviceTimeSum += serviceTime; //실행시간 총합
                DequeToken.nextToken();DequeToken.nextToken();

            for(int k = 1; k<= processCount; k++){
                int count=0;
                StringTokenizer processToken=new StringTokenizer(process[k]);
                    processId =processToken.nextToken();
                    arriveTime =Double.parseDouble(processToken.nextToken());
                    serviceTime =Double.parseDouble(processToken.nextToken());
                    /*
                    이미 덱 안에 동일한 프로세스가 들어가 있는지 확인
                     */
                    Iterator it= deque.iterator();
                    while(it.hasNext()){
                        String s= (String) it.next();
                        StringTokenizer QueueToken=new StringTokenizer(s);
                        while(QueueToken.hasMoreTokens()){
                            String Id=QueueToken.nextToken();
                            if(Id.equals(processId)) //덱 안에 동일한 프로세스가 있다면 count증가
                                count++;
                        }
                    }
                    if(count==0) { //덱에 동일한 프로세스가 없는 경우, 실제 우선순위를 구하는 작업을 하는 조건문
                        double m=(serviceTime +(serviceTimeSum - arriveTime))/ serviceTime; //(대기시간+CPU사용률)/CPU사용률
                        if(maxPriority<m){
                            maxPriority=Math.max(maxPriority,m);
                            tmp=process[k]; //우선순위가 가장 빠른 프로세스를 tmp에 저장
                        }
                    }
            }
            deque.add(tmp); //우선순위가 가장 높은 프로세스를 덱에 삽입
        }
        deque.pollLast(); //마지막 요소 삭제
        System.out.println("비선점형 - HRN스케줄링");
        NonpreemptivePrintProcess print_process=new NonpreemptivePrintProcess();
        print_process.print(deque, processCount);
    }
}
