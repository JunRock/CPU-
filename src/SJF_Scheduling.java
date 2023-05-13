import java.util.*;
public class SJF_Scheduling extends Process_Variable{
    public void run(){
        process=open();
        Deque<String> q=new LinkedList<>();

        for (int i = 1; i <= ProcessCount; i++) {
            StringTokenizer processsToken = new StringTokenizer(process[i]);
            while (processsToken.hasMoreTokens()) {
                ProcessId = processsToken.nextToken(); //프로세스 ID
                ArriveTime = Integer.parseInt(processsToken.nextToken()); //도착시간
                ServiceTime = Integer.parseInt(processsToken.nextToken()); //실행시간
                processsToken.nextToken(); //우선순위
                ResponseTime =Integer.parseInt(processsToken.nextToken());//응답시간

                if(ArriveTime ==0){ //도착시간이 0인 프로세스는 바로 큐에 삽입
                    q.add(process[i]);
                }
                tmp_servicetime[i]= (int) ServiceTime; //실행시간 저장
                tmp_arrivetime[i]= (int) ArriveTime; //도착시간 저장
                tmp_processId[i]= ProcessId; //프로세스 ID저장
            }
        }

        Arrays.sort(tmp_servicetime);

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
                    이미 큐 안에 동일한 프로세스가 들어가 있는지 확인
                    */
                    Iterator it=q.iterator();
                    while(it.hasNext()){
                        String s= (String) it.next();
                        StringTokenizer QToken=new StringTokenizer(s);
                        while(QToken.hasMoreTokens()){
                            String Id=QToken.nextToken();
                            if(Id.equals(ProcessId))
                                count++;
                        }
                    }

                    if(count==0) {
                        if (ServiceTime == tmp_servicetime[i]) {
                            q.add(process[k]);
                        }
                    }
                }
            }
        }
        System.out.println("비선점형 - SJF스케줄링");
        Nonpreemptive_Print_Process print_process=new Nonpreemptive_Print_Process();
        print_process.print(q, ProcessCount);
    }
}
