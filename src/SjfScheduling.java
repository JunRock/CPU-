import java.util.*;
public class SjfScheduling extends ProcessSort{
    public void run(){
        process= fileOpen();
        arriveTimeSort(); //도착시간대로 정렬

        for(int i = 1; i<= processCount; i++){
            for(int k = 1; k<= processCount; k++){
                int count=0;
                StringTokenizer processToken=new StringTokenizer(process[k]);
                while(processToken.hasMoreTokens()){
                    processId =processToken.nextToken();
                    arriveTime =Integer.parseInt(processToken.nextToken());
                    serviceTime =Integer.parseInt(processToken.nextToken());
                    processToken.nextToken();processToken.nextToken();
                    /*
                    이미 덱 안에 동일한 프로세스가 들어가 있는지 확인
                    */
                    Iterator it=deque.iterator();
                    while(it.hasNext()){
                        String s= (String) it.next();
                        StringTokenizer QToken=new StringTokenizer(s);
                        while(QToken.hasMoreTokens()){
                            String Id=QToken.nextToken();
                            if(Id.equals(processId)) //덱에 동일한 프로세스가 있는지 확인
                                count++;
                        }
                    }

                    if(count==0) { //동일한 프로세스가 없는 경우
                        if (serviceTime == tmpServiceTime[i]) { //정렬된 tmp_servicetime에 의해 가장 짧은 실행시간 프로세스를 덱에 삽입
                            deque.add(process[k]);
                        }
                    }
                }
            }
        }
        System.out.println("비선점형 - SJF스케줄링");
        NonpreemptivePrintProcess print_process=new NonpreemptivePrintProcess();
        print_process.print(deque, processCount);
    }
}
