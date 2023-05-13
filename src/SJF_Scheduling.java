import java.util.*;
public class SJF_Scheduling extends ProcessSort{
    public void run(){
        process= FileOpen();
        ArriveTimeSort();

        for(int i = 1; i<= ProcessCount; i++){
            for(int k = 1; k<= ProcessCount; k++){
                int count=0;
                StringTokenizer processToken=new StringTokenizer(process[k]);
                while(processToken.hasMoreTokens()){
                    ProcessId =processToken.nextToken();
                    ArriveTime =Integer.parseInt(processToken.nextToken());
                    ServiceTime =Integer.parseInt(processToken.nextToken());
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
                            if(Id.equals(ProcessId)) //덱에 동일한 프로세스가 있는지 확인
                                count++;
                        }
                    }

                    if(count==0) { //동일한 프로세스가 없는 경우
                        if (ServiceTime == tmp_servicetime[i]) { //정렬된 tmp_servicetime에 의해 가장 짧은 실행시간 프로세스를 덱에 삽입
                            deque.add(process[k]);
                        }
                    }
                }
            }
        }
        System.out.println("비선점형 - SJF스케줄링");
        Nonpreemptive_Print_Process print_process=new Nonpreemptive_Print_Process();
        print_process.print(deque, ProcessCount);
    }
}
