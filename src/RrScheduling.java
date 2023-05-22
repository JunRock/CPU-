import java.util.*;
public class RrScheduling extends ProcessSort{
    public void run() {
        process = fileOpen();
        int c = 0;
        int totalServiceTime = 0;
        arriveTimeSort(); //도착시간대로 정렬
        timeSort(); //도착시간에 맞는 프로세스들의 각종 정보들 정렬
        String[] ganttChatt = new String[serviceTimeSum];

        while (totalServiceTime != serviceTimeSum) { //현재까지의 실행시간이 전체 실행시간이 되기 전까지 반복
            String str = q.poll(); //큐에 들어있는 프로세스를 str에 저장
            StringTokenizer strToken = new StringTokenizer(str);
            processId = strToken.nextToken();
            arriveTime = Integer.parseInt(strToken.nextToken());
            serviceTime = Integer.parseInt(strToken.nextToken());
            strToken.nextToken();
            responseTime = Integer.parseInt(strToken.nextToken());

            for (int i = 1; i <= processCount; i++) {
                if (tmpProcessId[i].equals(processId)) { //정렬된 프로세스ID와 큐에서 꺼낸 프로세스ID가 동일한 경우
                    if (totalServiceTime >= arriveTime) { //현재 실행시간안에 도착한 프로세스인 경우
                        check[i] = 0; //한번 검사했다는 걸 표시

                        if (saveServiceTime[i] >= timeQuantum) { //해당 프로세스의 실행시간이 타임슬라이스보다 큰 경우
                            for (int j = 1; j <= timeQuantum; j++) {
                                waitTime[i] += (totalServiceTime - tmpArriveTime[i]); //대기시간 저장
                                saveServiceTime[i] -= 1; //해당 프로세스의 남은 실행시간 1초 감소
                                totalServiceTime++; //전체 실행시간 1초 증가

                                for (int m = 1; m <= processCount; m++) { //큐에 새로운 실행시간 안에 도착한 프로세스 삽입
                                    StringTokenizer processToken = new StringTokenizer(process[m]);
                                    String processId1 = processToken.nextToken();
                                    int arriveTime1 = Integer.parseInt(processToken.nextToken());
                                    int serviceTime1 = Integer.parseInt(processToken.nextToken());
                                    processToken.nextToken();
                                    int responseTime1 = Integer.parseInt(processToken.nextToken());
                                    /*
                                    현재 실행시간 안에 도착한 프로세스와 한번도 검사하지 않은 프로세스인 경우
                                     */
                                    if (totalServiceTime >= arriveTime1 && check[m] != 0) {
                                        q.add(process[m]); //큐에 해당 프로세스 정보 삽입
                                        check[m] = 0; //한번 검사했다는 것을 표시
                                    }
                                }

                                if (tmpResponseTime[i] == 0) //응답시간을 한번만 계산
                                    tmpResponseTime[i] = (int) ((totalServiceTime + responseTime) - arriveTime);
                                tmpArriveTime[i] = totalServiceTime;
                                ganttChatt[c++] = processId;
                                if (saveServiceTime[i] == 0) { //프로세스가 주어진 작업을 다한 경우
                                    returnTime[i] = (int) (totalServiceTime - arriveTime); //반환시간 계산
                                    break;
                                }
                            }
                            q.add(process[i]); //타임슬라이스만큼 작업하고 다시 큐에 삽입
                        }
                        else if (saveServiceTime[i] != 0 && saveServiceTime[i] < timeQuantum) { //프로세스 남은 작업시간이 0이 아니고 타임슬라이스보다 적은경우
                            for (int j = 1; j <= timeQuantum; j++) {
                                waitTime[i] += (totalServiceTime - tmpArriveTime[i]); //대기시간 저장
                                saveServiceTime[i] -= 1; //남은 실행시간 1초 감소
                                totalServiceTime++; //전체 실행시간 1초 증가

                                for (int m = 1; m <= processCount; m++) { //큐에 새로운 실행시간 안에 도착한 프로세스 삽입
                                    StringTokenizer processToken = new StringTokenizer(process[m]);
                                    String processId1 = processToken.nextToken();
                                    int arriveTime1 = Integer.parseInt(processToken.nextToken());
                                    int serviceTime1 = Integer.parseInt(processToken.nextToken());
                                    processToken.nextToken();
                                    int responseTime1 = Integer.parseInt(processToken.nextToken());
                                    /*
                                    현재 실행시간 안에 도착한 프로세스와 한번도 검사하지 않은 프로세스인 경우
                                     */
                                    if (totalServiceTime >= arriveTime1 && check[m] != 0) {
                                        q.add(process[m]); //큐에 해당 프로세스 정보 삽입
                                        check[m] = 0; //한번 검사했다는 것을 표시
                                    }
                                }

                                if (tmpResponseTime[i] == 0)
                                    tmpResponseTime[i] = (int) ((totalServiceTime + responseTime) - arriveTime);
                                tmpArriveTime[i] = totalServiceTime;
                                ganttChatt[c++] = processId;
                                if (saveServiceTime[i] == 0) {
                                    returnTime[i] = (int) (totalServiceTime - arriveTime);
                                    break;
                                }
                            }
                            q.add(process[i]);
                        }
                    }
                }
            }
            }
            System.out.println("선점형 - 라운드로빈(RR)스케줄링");
            PreemptivePrintProcess print_process = new PreemptivePrintProcess();
            print_process.print(processCount, waitTime, tmpProcessId, returnTime, ganttChatt, tmpResponseTime);
        }
    }