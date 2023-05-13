import java.util.*;

public class Nonpreemptive_Print_Process extends Process_Variable{
    public void print(Deque<String> q, int process_count){
        int arrsum = 0,retime;; //프로세스 대기시간 총합
        int responsetime_sum=0,total_time=0,j=0;
        double re_time_sum = 0;
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
                System.out.println(ServiceTimeSum - ArriveTime);
                arrsum += (ServiceTimeSum - ArriveTime);
                ServiceTimeSum += ServiceTime;
                time[j] = (int) (ServiceTimeSum - ArriveTime);
                j++;
                strToken.nextToken();strToken.nextToken(); //우선순위
        }
        System.out.println("평균 대기 시간(AWT): " + (double)arrsum / process_count);

        it=q.iterator();
        while(it.hasNext()){
            String str= (String) it.next();
            StringTokenizer strToken=new StringTokenizer(str);
            ProcessId = strToken.nextToken();
            ArriveTime =Integer.parseInt(strToken.nextToken());
            ServiceTime =Integer.parseInt(strToken.nextToken());
            strToken.nextToken();
            retime=Integer.parseInt(strToken.nextToken());
            ResponseTime =(total_time+retime)- ArriveTime;
            System.out.println(ProcessId + "의 응답시간: " + ResponseTime); //프로세스 ID
            total_time+= ServiceTime;
            responsetime_sum+= ResponseTime;
        }

        System.out.println("평균응답시간(ART): "+(double)responsetime_sum/process_count);

        j = 0;
        it = q.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            StringTokenizer strToken = new StringTokenizer(str);
                System.out.println(strToken.nextToken() + "의 반환시간: " + time[j]); //프로세스 ID
                strToken.nextToken(); strToken.nextToken(); strToken.nextToken(); strToken.nextToken();
            re_time_sum += time[j++];
        }
        System.out.println("평균 반환 시간(ATT): "+re_time_sum /process_count);
    }
}
