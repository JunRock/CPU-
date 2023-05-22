import java.util.Arrays;
import java.util.StringTokenizer;
public class ProcessSort extends ProcessVariable {
    public  void arriveTimeSort(){ //도착시간을 정렬해주는 메소드
        for (int i = 1; i <= processCount; i++) {
            StringTokenizer processToken = new StringTokenizer(process[i]);
            processId = processToken.nextToken(); //프로세스 ID
            arriveTime = Integer.parseInt(processToken.nextToken()); //도착시간
            serviceTime = Integer.parseInt(processToken.nextToken()); //실행시간
            tmpPriority[i] = Integer.parseInt(processToken.nextToken()); //우선순위
            processToken.nextToken(); //응답시간
            tmpTime[i] = (int) arriveTime; //도착시간 모음배열
            serviceTimeSum += serviceTime;
            if (arriveTime == 0){ //도착시간이 0인 프로세스를 검사
                q.add(process[i]); //도착시간이 0인 프로세스를 큐에 저장
                deque.addLast(process[i]); //도착시간이 0인 프로세스를 덱에 저장
            }
            tmpServiceTime[i]= (int) serviceTime; //실행시간 저장
            tmpArriveTime[i]= (int) arriveTime; //도착시간 저장
            tmpProcessId[i]= processId; //프로세스 ID저장
        }
        Arrays.sort(tmpTime); //도착시간 정렬
        Arrays.sort(tmpPriority); //우선순위 정렬
        Arrays.sort(tmpServiceTime); //실행시간 정렬
    }

    public void timeSort(){ //정렬된 도착시간배열에 맞춰 다른 프로세스 정보들도 정렬(srt, 비선점우선순위, rr사용)
        for(int i = 1; i<= processCount; i++){
            for(int j = 1; j<= processCount; j++){
                StringTokenizer processToken=new StringTokenizer(process[j]);
                processId =processToken.nextToken();
                arriveTime =Integer.parseInt(processToken.nextToken());
                serviceTime =Integer.parseInt(processToken.nextToken());
                processToken.nextToken();//우선순위
                responseTime =Integer.parseInt(processToken.nextToken());
                if(tmpTime[i]== arriveTime){ //정렬된 도착시간배열에 프로세스 정보를 정렬
                    srtProcess[i]=process[j]; //SRT에서 사용하는 정렬된 프로세스 배열
                    nonpreemrtiveProcess[i] = process[j]; //비선점우선순위에서 사용하는 정렬된 프로세스 배열
                    process[i] = tmpProcess[i]; //정렬된 프로세스 배열
                    saveServiceTime[i]= (int) serviceTime; //각각 프로세스의 남은 실행시간 저장
                    tmpArriveTime[i]= (int) arriveTime; //각 프로세스 별 정렬된 도착시간 저장
                    tmpProcessId[i]= processId; //프로세스 ID저장
                    tmpServiceTime[i] = (int) serviceTime; //각 프로세스별 정렬된 실행시간 저장
                    check[i] = 1; //RR스케줄링에서 한번 검사한 프로세스인지 확인하는 배열
                    restime[i]= (int) responseTime; //각 프로세스별 정렬된 반응시간 저장
                }
            }
        }
    }
}
