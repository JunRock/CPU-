import java.util.*;

public class Nonpreemptive_Print_Process {
    public void print(Deque<String> q, int process_count){
        String processId;
        int[] arrtime = new int[process_count];
        int arriveTime, serviceTime, retime; //프로세스ID, 도착시간, 작업시간, 반환시간
        int[] tmp_time = new int[process_count+1];
        int sum = 0;
        int arrsum = 0; //프로세스 대기시간 총합
        double re_time_sum = 0;
        int[] time = new int[process_count]; //프로세스 별 반환시간 저장배열
        int j=0;
        j = 0;

        System.out.println("<간트차트>");

        Iterator t=q.iterator();
        while(t.hasNext()){
            String str= (String) t.next();
            StringTokenizer st=new StringTokenizer(str);
            processId=st.nextToken();
            st.nextToken();
            serviceTime=Integer.parseInt(st.nextToken());
            st.nextToken();
            for(int i=0;i<serviceTime;i++)
                System.out.print("["+processId+"]");
        }
        System.out.println();
        Iterator it = q.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            StringTokenizer st = new StringTokenizer(str);
            while (st.hasMoreTokens()) {
                processId=st.nextToken();
                arriveTime = Integer.parseInt(st.nextToken()); //도착시간
                serviceTime = Integer.parseInt(st.nextToken()); //실행시간
                System.out.print(processId+ "의 대기시간: "); //프로세스 ID
                System.out.println(sum - arriveTime);
                arrsum += (sum - arriveTime);
                sum += serviceTime;
                time[j] = sum - arriveTime;
                j++;
                st.nextToken(); //우선순위
            }
        }
        j = 0;
        System.out.println("평균 대기 시간(AWT): " + (double)arrsum / process_count);
        it = q.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            StringTokenizer st = new StringTokenizer(str);
            while (st.hasMoreTokens()) {
                System.out.println(st.nextToken() + "의 반환시간: " + time[j]); //프로세스 ID
                st.nextToken();
                st.nextToken();
                st.nextToken(); //우선순위
            }
            re_time_sum += time[j++];
        }
        System.out.println("평균 반환 시간(ATT): "+re_time_sum /process_count);
    }
}