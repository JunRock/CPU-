import java.util.Arrays;
import java.util.StringTokenizer;

public class ProcessSort extends Process_Variable{
    public  void ArriveTimeSort(){
        for (int i = 1; i <= ProcessCount; i++) {
            StringTokenizer processToken = new StringTokenizer(process[i]);
            ProcessId = processToken.nextToken(); //프로세스 ID
            ArriveTime = Integer.parseInt(processToken.nextToken()); //도착시간
            ServiceTime = Integer.parseInt(processToken.nextToken()); //실행시간
            tmp_priority[i] = Integer.parseInt(processToken.nextToken()); //우선순위
            processToken.nextToken(); //우선순위
            tmp_time[i] = (int) ArriveTime; //도착시간 모음배열
            servicetime_sum+= ServiceTime;
            if (ArriveTime == 0){
                q.add(process[i]);
                deque.addLast(process[i]);
                tmp_q.addLast(process[i]);
            }
            tmp_servicetime[i]= (int) ServiceTime; //실행시간 저장
            tmp_arrivetime[i]= (int) ArriveTime; //도착시간 저장
            tmp_processId[i]= ProcessId; //프로세스 ID저장
        }
        Arrays.sort(tmp_time); //도착시간 정렬
        Arrays.sort(tmp_priority); //우선순위 정렬
        Arrays.sort(tmp_servicetime); //실행시간 정렬
    }

    public void TimeSort(){ //srt, 비선점우선순위, rr사용
        for(int i = 1; i<= ProcessCount; i++){
            for(int j = 1; j<= ProcessCount; j++){
                StringTokenizer processToken=new StringTokenizer(process[j]);
                ProcessId =processToken.nextToken();
                ArriveTime =Integer.parseInt(processToken.nextToken());
                ServiceTime =Integer.parseInt(processToken.nextToken());
                processToken.nextToken();//우선순위
                ResponseTime =Integer.parseInt(processToken.nextToken());
                if(tmp_time[i]== ArriveTime){
                    process_real[i]=process[j];
                    cpu_process[i] = process[j];
                    process[i] = tmp_process[i];
                    SaveServiceTime[i]= (int) ServiceTime;
                    tmp_arrivetime[i]= (int) ArriveTime;
                    tmp_processId[i]= ProcessId;
                    tmp_servicetime[i] = (int) ServiceTime;
                    check[i] = 1;
                    restime[i]= (int) ResponseTime;
                }
            }
        }
    }
}
