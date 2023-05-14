import java.util.Arrays;
import java.util.StringTokenizer;

public class ProcessSort extends Process_Variable{
    public  void ArriveTimeSort(){ //도착시간을 정렬해주는 메소드
        for (int i = 1; i <= ProcessCount; i++) {
            StringTokenizer processToken = new StringTokenizer(process[i]);
            ProcessId = processToken.nextToken(); //프로세스 ID
            ArriveTime = Integer.parseInt(processToken.nextToken()); //도착시간
            ServiceTime = Integer.parseInt(processToken.nextToken()); //실행시간
            tmp_priority[i] = Integer.parseInt(processToken.nextToken()); //우선순위
            processToken.nextToken(); //응답시간
            tmp_time[i] = (int) ArriveTime; //도착시간 모음배열
            ServiceTimeSum += ServiceTime;
            if (ArriveTime == 0){ //도착시간이 0인 프로세스를 검사
                q.add(process[i]); //도착시간이 0인 프로세스를 큐에 저장
                deque.addLast(process[i]); //도착시간이 0인 프로세스를 덱에 저장
            }
            tmp_servicetime[i]= (int) ServiceTime; //실행시간 저장
            tmp_arrivetime[i]= (int) ArriveTime; //도착시간 저장
            tmp_processId[i]= ProcessId; //프로세스 ID저장
        }
        Arrays.sort(tmp_time); //도착시간 정렬
        Arrays.sort(tmp_priority); //우선순위 정렬
        Arrays.sort(tmp_servicetime); //실행시간 정렬
    }

    public void TimeSort(){ //정렬된 도착시간배열에 맞춰 다른 프로세스 정보들도 정렬(srt, 비선점우선순위, rr사용)
        for(int i = 1; i<= ProcessCount; i++){
            for(int j = 1; j<= ProcessCount; j++){
                StringTokenizer processToken=new StringTokenizer(process[j]);
                ProcessId =processToken.nextToken();
                ArriveTime =Integer.parseInt(processToken.nextToken());
                ServiceTime =Integer.parseInt(processToken.nextToken());
                processToken.nextToken();//우선순위
                ResponseTime =Integer.parseInt(processToken.nextToken());
                if(tmp_time[i]== ArriveTime){ //정렬된 도착시간배열에 프로세스 정보를 정렬
                    SrtProcess[i]=process[j]; //SRT에서 사용하는 정렬된 프로세스 배열
                    NonpreemrtiveProcess[i] = process[j]; //비선점우선순위에서 사용하는 정렬된 프로세스 배열
                    process[i] = tmp_process[i]; //정렬된 프로세스 배열
                    SaveServiceTime[i]= (int) ServiceTime; //각각 프로세스의 남은 실행시간 저장
                    tmp_arrivetime[i]= (int) ArriveTime; //각 프로세스 별 정렬된 도착시간 저장
                    tmp_processId[i]= ProcessId; //프로세스 ID저장
                    tmp_servicetime[i] = (int) ServiceTime; //각 프로세스별 정렬된 실행시간 저장
                    check[i] = 1; //RR스케줄링에서 한번 검사한 프로세스인지 확인하는 배열
                    restime[i]= (int) ResponseTime; //각 프로세스별 정렬된 반응시간 저장
                }
            }
        }
    }
}
