import java.util.*;
public class NonpreemptivePriorityScheduling extends ProcessSort{
    public void run(){
        process= fileOpen();
        Deque<String> d = new LinkedList<>();
        Queue<String> tmp_q = new LinkedList<>(); //동일한 우선순위일 경우 도착시간에 따른 정렬까지 마침
        arriveTimeSort(); //도착시간대로 정렬
        timeSort(); //정렬된 도착시간에 맞게 프로세스들의 각종 정보들 정렬

        String[] tmpArr = new String[processCount -1];
        String[] arr = new String[processCount -1];
        int[] tmpPriority1 = new int[processCount -1]; //P1프로세스를 제외한 나머지 프로세스들 우선순위 배열
        int[] tmpServiceTime1 = new int[processCount -1]; //P1프로세스를 제외한 나머지 프로세스들 실행시간 배열
        int[] tmpArriveTime1 = new int[processCount -1]; //P1프로세스를 제외한 나머지 프로세스들 도착시간 배열

        for (int i = 0; i < processCount -1; i++) {
            tmpArr[i] = nonpreemrtiveProcess[i+2]; //P2 P3 P4 P5 프로세스 정렬
        }

        for (int i = 0; i < processCount -1; i++) {
            StringTokenizer processToken = new StringTokenizer(tmpArr[i]);
                processId = processToken.nextToken(); //프로세스 ID
                tmpArriveTime1[i] = Integer.parseInt(processToken.nextToken()); //도착시간
                tmpServiceTime1[i] = Integer.parseInt(processToken.nextToken()); //실행시간
                tmpPriority1[i] = Integer.parseInt(processToken.nextToken()); //우선순위
                processToken.nextToken();
        }
        Arrays.sort(tmpPriority1); //1 2 2 4(P4 P2 P5 P3순으로 정렬)

        for(int i = 0; i< processCount -1; i++){
            int priorityCount=0;
            for(int j = 0; j< processCount -1; j++){
                StringTokenizer processToken=new StringTokenizer(tmpArr[j]);
                int sameProcessCount=0;
                processId =processToken.nextToken();
                arriveTime =Integer.parseInt(processToken.nextToken());
                serviceTime =Integer.parseInt(processToken.nextToken());
                priority=Integer.parseInt(processToken.nextToken());
                processToken.nextToken();
                Iterator iterator=d.iterator(); //덱에 동일 프로세스가 있는지 확인
                while(iterator.hasNext()){
                    StringTokenizer QToken=new StringTokenizer((String) iterator.next());
                    String proId=QToken.nextToken();
                    QToken.nextToken();QToken.nextToken();QToken.nextToken();QToken.nextToken();
                    if(processId.equals(proId)) //동일한 프로세스가 있다면 SameProcessCount증가
                        sameProcessCount++;
                }
                if(tmpPriority1[i]==priority&& sameProcessCount==0){ //덱에 동일한 프로세스가 없는 경우
                    priorityCount++; //동일한 우선순위가 있는지 확인
                    tmp_q.add(tmpArr[j]); //임시저장소인 큐에 저장
                }
            }
            if(priorityCount==1){ //PriorityCount가 1인 경우는 동일한 우선순위가 없는 것을 의미
                while(!tmp_q.isEmpty()){
                    d.add(tmp_q.poll()); //덱에 삽입
                }
            }
            else if(priorityCount>1){ //동일한 우선순위를 가진 프로세스가 있는 경우
                String str1=tmp_q.poll();
                String str2=tmp_q.poll();
                StringTokenizer str1Token=new StringTokenizer(str1);
                StringTokenizer str2Token=new StringTokenizer(str2);
                    str1Token.nextToken();
                    int arrtime1=Integer.parseInt(str1Token.nextToken());
                    str1Token.nextToken();str1Token.nextToken();str1Token.nextToken();
                    str2Token.nextToken();
                    int arrtime2=Integer.parseInt(str2Token.nextToken());
                    str2Token.nextToken();str2Token.nextToken();str2Token.nextToken();
                    if(arrtime1>arrtime2) //도착시간이 더 빠른 프로세스를 덱에 저장
                        d.add(str2);
                    else
                        d.add(str1);
            }
        }
        d.addFirst(nonpreemrtiveProcess[1]); //덱에 도착시간이 0인 프로세스를 삽입
        System.out.println("비선점형 - 비선점 우선순위 스케줄링");
        NonpreemptivePrintProcess print_process=new NonpreemptivePrintProcess();
        print_process.print(d, processCount);
    }
}
