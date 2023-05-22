import java.util.*;
public class NonpreemptivePrintProcess extends ProcessVariable {
    public void print(Deque<String> q, int process_count){
        int totalWaitTime = 0,retime; //프로세스 대기시간 총합, 응답시간
        int totalResponseTime=0,totalServiceTime=0,j=0; //반응시간, 실행시간 총합
        double totalReturnTime = 0; //반환시간 총합
        int[] time = new int[process_count]; //프로세스 별 반환시간 저장배열
        System.out.println("<간트차트>");
        int count=0;
        Iterator t=q.iterator();
        while(t.hasNext()){
            String str= (String) t.next();
            StringTokenizer strToken=new StringTokenizer(str);
            processId =strToken.nextToken();
            strToken.nextToken();
            serviceTime =Integer.parseInt(strToken.nextToken());
            strToken.nextToken();
            for(int i = 0; i< serviceTime; i++){
                count++;
                if(count==30)
                    System.out.println();
                System.out.print("["+ processId +"]");
            }

        }
        System.out.println();

        Iterator it = q.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            StringTokenizer strToken = new StringTokenizer(str);
                processId =strToken.nextToken();
                arriveTime = Integer.parseInt(strToken.nextToken()); //도착시간
                serviceTime = Integer.parseInt(strToken.nextToken()); //실행시간
                System.out.print(processId + "의 대기시간: "); //프로세스 ID
                System.out.println(serviceTimeSum - arriveTime); //프로세스별 대기시간 출력
                totalWaitTime += (serviceTimeSum - arriveTime); // 대기시간 총합
                serviceTimeSum += serviceTime;
                time[j] = (int) (serviceTimeSum - arriveTime); //반환시간 저장
                j++;
                strToken.nextToken();strToken.nextToken();
        }
        System.out.println("평균 대기 시간(AWT): " + (double)totalWaitTime / process_count);

        it=q.iterator();
        while(it.hasNext()){
            String str= (String) it.next();
            StringTokenizer strToken=new StringTokenizer(str);
            processId = strToken.nextToken();
            arriveTime =Integer.parseInt(strToken.nextToken());
            serviceTime =Integer.parseInt(strToken.nextToken());
            strToken.nextToken();
            retime=Integer.parseInt(strToken.nextToken());
            responseTime =(totalServiceTime+retime) - arriveTime; //응답시간 계산
            System.out.println(processId + "의 응답시간: " + responseTime); //프로세스 ID
            totalServiceTime+= serviceTime;
            totalResponseTime+= responseTime;
        }

        System.out.println("평균응답시간(ART): "+(double)totalResponseTime/process_count);

        j = 0;
        it = q.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            StringTokenizer strToken = new StringTokenizer(str);
                System.out.println(strToken.nextToken() + "의 반환시간: " + time[j]); //프로세스 ID
                strToken.nextToken(); strToken.nextToken(); strToken.nextToken(); strToken.nextToken();
            totalReturnTime += time[j++];
        }
        System.out.println("평균 반환 시간(ATT): "+totalReturnTime /process_count);
    }
}
