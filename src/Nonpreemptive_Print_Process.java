import java.util.*;

public class Nonpreemptive_Print_Process extends Process_Variable{
    public void print(Deque<String> q, int process_count){
        int TotalWaitTime = 0,retime; //프로세스 대기시간 총합, 응답시간
        int TotalResponseTime=0,TotalServiceTime=0,j=0; //반응시간, 실행시간 총합
        double TotalReturnTime = 0; //반환시간 총합
        int[] time = new int[process_count]; //프로세스 별 반환시간 저장배열
        System.out.println("<간트차트>");

        Iterator t=q.iterator();
        while(t.hasNext()){
            String str= (String) t.next();
            StringTokenizer strToken=new StringTokenizer(str);
            ProcessId =strToken.nextToken();
            strToken.nextToken();
            ServiceTime =Integer.parseInt(strToken.nextToken());
            strToken.nextToken();
            for(int i = 0; i< ServiceTime; i++)
                System.out.print("["+ ProcessId +"]");
        }
        System.out.println();

        Iterator it = q.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            StringTokenizer strToken = new StringTokenizer(str);
                ProcessId =strToken.nextToken();
                ArriveTime = Integer.parseInt(strToken.nextToken()); //도착시간
                ServiceTime = Integer.parseInt(strToken.nextToken()); //실행시간
                System.out.print(ProcessId + "의 대기시간: "); //프로세스 ID
                System.out.println(ServiceTimeSum - ArriveTime); //프로세스별 대기시간 출력
                TotalWaitTime += (ServiceTimeSum - ArriveTime); // 대기시간 총합
                ServiceTimeSum += ServiceTime;
                time[j] = (int) (ServiceTimeSum - ArriveTime); //반환시간 저장
                j++;
                strToken.nextToken();strToken.nextToken();
        }
        System.out.println("평균 대기 시간(AWT): " + (double)TotalWaitTime / process_count);

        it=q.iterator();
        while(it.hasNext()){
            String str= (String) it.next();
            StringTokenizer strToken=new StringTokenizer(str);
            ProcessId = strToken.nextToken();
            ArriveTime =Integer.parseInt(strToken.nextToken());
            ServiceTime =Integer.parseInt(strToken.nextToken());
            strToken.nextToken();
            retime=Integer.parseInt(strToken.nextToken());
            ResponseTime =(TotalServiceTime+retime) - ArriveTime; //응답시간 계산
            System.out.println(ProcessId + "의 응답시간: " + ResponseTime); //프로세스 ID
            TotalServiceTime+= ServiceTime;
            TotalResponseTime+= ResponseTime;
        }

        System.out.println("평균응답시간(ART): "+(double)TotalResponseTime/process_count);

        j = 0;
        it = q.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            StringTokenizer strToken = new StringTokenizer(str);
                System.out.println(strToken.nextToken() + "의 반환시간: " + time[j]); //프로세스 ID
                strToken.nextToken(); strToken.nextToken(); strToken.nextToken(); strToken.nextToken();
            TotalReturnTime += time[j++];
        }
        System.out.println("평균 반환 시간(ATT): "+TotalReturnTime /process_count);
    }
}
