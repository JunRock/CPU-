import java.util.Arrays;
import java.util.StringTokenizer;

public class ProcessSort extends Process_Variable{
    public  void TimeSort(){
        for (int i = 1; i <= ProcessCount; i++) {
            StringTokenizer processToken = new StringTokenizer(process[i]);
            ProcessId = processToken.nextToken(); //프로세스 ID
            ArriveTime = Integer.parseInt(processToken.nextToken()); //도착시간
            ServiceTime = Integer.parseInt(processToken.nextToken()); //실행시간
            tmp_priority[i] = Integer.parseInt(processToken.nextToken()); //우선순위
            processToken.nextToken(); //우선순위
            tmp_time[i] = (int) ArriveTime; //도착시간 모음배열
            //tmp_arrivetime[i] = Integer.parseInt(processToken.nextToken()); //도착시간
            servicetime_sum+= ServiceTime;
            if (ArriveTime == 0){
                q.add(process[i]);
                tmp_q.addLast(process[i]);
            }
        }
        Arrays.sort(tmp_time); //도착시간 정렬
        Arrays.sort(tmp_priority); //우선순위 정렬
    }
}
