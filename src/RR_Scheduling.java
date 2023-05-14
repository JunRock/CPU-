import java.util.*;

public class RR_Scheduling extends ProcessSort{
    public void run() {
        process = FileOpen();
        int c = 0;
        int total_servicetime = 0;
        ArriveTimeSort(); //도착시간대로 정렬
        TimeSort(); //도착시간에 맞는 프로세스들의 각종 정보들 정렬
        String[] ganttchatt = new String[ServiceTimeSum];

        while (total_servicetime != ServiceTimeSum) { //현재까지의 실행시간이 전체 실행시간이 되기 전까지 반복
            String str = q.poll(); //큐에 들어있는 프로세스를 str에 저장
            StringTokenizer strToken = new StringTokenizer(str);
            ProcessId = strToken.nextToken();
            ArriveTime = Integer.parseInt(strToken.nextToken());
            ServiceTime = Integer.parseInt(strToken.nextToken());
            strToken.nextToken();
            ResponseTime = Integer.parseInt(strToken.nextToken());

            for (int i = 1; i <= ProcessCount; i++) {
                if (tmp_processId[i].equals(ProcessId)) { //정렬된 프로세스ID와 큐에서 꺼낸 프로세스ID가 동일한 경우
                    if (total_servicetime >= ArriveTime) { //현재 실행시간안에 도착한 프로세스인 경우
                        check[i] = 0; //한번 검사했다는 걸 표시

                        if (SaveServiceTime[i] >= TimeQuantum) { //해당 프로세스의 실행시간이 타임슬라이스보다 큰 경우
                            for (int j = 1; j <= TimeQuantum; j++) {
                                wait_time[i] += (total_servicetime - tmp_arrivetime[i]); //대기시간 저장
                                SaveServiceTime[i] -= 1; //해당 프로세스의 남은 실행시간 1초 감소
                                total_servicetime++; //전체 실행시간 1초 증가

                                for (int m = 1; m <= ProcessCount; m++) { //큐에 새로운 실행시간 안에 도착한 프로세스 삽입
                                    StringTokenizer processToken = new StringTokenizer(process[m]);
                                    String processId1 = processToken.nextToken();
                                    int arriveTime1 = Integer.parseInt(processToken.nextToken());
                                    int serviceTime1 = Integer.parseInt(processToken.nextToken());
                                    processToken.nextToken();
                                    int responseTime1 = Integer.parseInt(processToken.nextToken());
                                    /*
                                    현재 실행시간 안에 도착한 프로세스와 한번도 검사하지 않은 프로세스인 경우
                                     */
                                    if (total_servicetime >= arriveTime1 && check[m] != 0) {
                                        q.add(process[m]); //큐에 해당 프로세스 정보 삽입
                                        check[m] = 0; //한번 검사했다는 것을 표시
                                    }
                                }

                                if (response_time[i] == 0) //응답시간을 한번만 계산
                                    response_time[i] = (int) ((total_servicetime + ResponseTime) - ArriveTime);
                                tmp_arrivetime[i] = total_servicetime;
                                ganttchatt[c++] = ProcessId;
                                if (SaveServiceTime[i] == 0) { //프로세스가 주어진 작업을 다한 경우
                                    return_time[i] = (int) (total_servicetime - ArriveTime); //반환시간 계산
                                    break;
                                }
                            }
                            q.add(process[i]); //타임슬라이스만큼 작업하고 다시 큐에 삽입
                        }
                        else if (SaveServiceTime[i] != 0 && SaveServiceTime[i] < TimeQuantum) { //프로세스 남은 작업시간이 0이 아니고 타임슬라이스보다 적은경우
                            for (int j = 1; j <= TimeQuantum; j++) {
                                wait_time[i] += (total_servicetime - tmp_arrivetime[i]); //대기시간 저장
                                SaveServiceTime[i] -= 1; //남은 실행시간 1초 감소
                                total_servicetime++; //전체 실행시간 1초 증가

                                for (int m = 1; m <= ProcessCount; m++) { //큐에 새로운 실행시간 안에 도착한 프로세스 삽입
                                    StringTokenizer processToken = new StringTokenizer(process[m]);
                                    String processId1 = processToken.nextToken();
                                    int arriveTime1 = Integer.parseInt(processToken.nextToken());
                                    int serviceTime1 = Integer.parseInt(processToken.nextToken());
                                    processToken.nextToken();
                                    int responseTime1 = Integer.parseInt(processToken.nextToken());
                                    /*
                                    현재 실행시간 안에 도착한 프로세스와 한번도 검사하지 않은 프로세스인 경우
                                     */
                                    if (total_servicetime >= arriveTime1 && check[m] != 0) {
                                        q.add(process[m]); //큐에 해당 프로세스 정보 삽입
                                        check[m] = 0; //한번 검사했다는 것을 표시
                                    }
                                }

                                if (response_time[i] == 0)
                                    response_time[i] = (int) ((total_servicetime + ResponseTime) - ArriveTime);
                                tmp_arrivetime[i] = total_servicetime;
                                ganttchatt[c++] = ProcessId;
                                if (SaveServiceTime[i] == 0) {
                                    return_time[i] = (int) (total_servicetime - ArriveTime);
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
            Preemptive_Print_Process print_process = new Preemptive_Print_Process();
            print_process.print(ProcessCount, wait_time, tmp_processId, return_time, ganttchatt, response_time);
        }
    }