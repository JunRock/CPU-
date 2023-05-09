import java.util.Arrays;
import java.util.StringTokenizer;

public class ProcessSort extends Process_Variable{
    public  void TimeSort(){
        for (int i = 1; i <= process_count; i++) {
            StringTokenizer processToken = new StringTokenizer(process[i]);
            processId = processToken.nextToken(); //프로세스 ID
            arriveTime = Integer.parseInt(processToken.nextToken()); //도착시간
            serviceTime = Integer.parseInt(processToken.nextToken()); //실행시간
            processToken.nextToken();processToken.nextToken(); //우선순위
            tmp_time[i] = (int) arriveTime; //도착시간 모음배열
            servicetime_sum+=serviceTime;
            if (arriveTime == 0){
                q.add(process[i]);
                tmp_q.addLast(process[i]);
            }
        }
        Arrays.sort(tmp_time); //도착시간 정렬
        //eturn tmp_time;
    }
}
