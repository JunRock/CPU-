import java.util.*;

public class RR_Scheduling extends ProcessSort{
    public void run() {
        process = open();
        int c = 0;
        int total_servicetime = 0;
        String[] tmp_process;
        tmp_process = process;
        TimeSort();
        String[] ganttchatt = new String[servicetime_sum];

        for (int i = 1; i <= ProcessCount; i++) {
            for (int j = 1; j <= ProcessCount; j++) {
                StringTokenizer tmpProcessToken = new StringTokenizer(tmp_process[j]);
                ProcessId = tmpProcessToken.nextToken();
                ArriveTime = Integer.parseInt(tmpProcessToken.nextToken());
                ServiceTime = Integer.parseInt(tmpProcessToken.nextToken());
                tmpProcessToken.nextToken();
                tmpProcessToken.nextToken();
                if (tmp_time[i] == ArriveTime) {
                    tmp_processId[i] = ProcessId;
                    tmp_arrivetime[i] = (int) ArriveTime;
                    tmp_servicetime[i] = (int) ServiceTime;
                    SaveServiceTime[i] = (int) ServiceTime;
                    check[i] = 1;
                    process[i] = tmp_process[i];
                }
            }
        }

        while (total_servicetime != servicetime_sum) {
            String str = q.poll();
            StringTokenizer strToken = new StringTokenizer(str);
            ProcessId = strToken.nextToken();
            ArriveTime = Integer.parseInt(strToken.nextToken());
            ServiceTime = Integer.parseInt(strToken.nextToken());
            strToken.nextToken();
            ResponseTime = Integer.parseInt(strToken.nextToken());

            for (int i = 1; i <= ProcessCount; i++) {
                if (tmp_processId[i].equals(ProcessId)) {
                    if (total_servicetime >= ArriveTime) {
                        check[i] = 0;

                        if (SaveServiceTime[i] >= TimeQuantum) {
                            for (int j = 1; j <= TimeQuantum; j++) {
                                wait_time[i] += (total_servicetime - tmp_arrivetime[i]); //대기시간 저장
                                SaveServiceTime[i] -= 1;
                                total_servicetime++;

                                for (int m = 1; m <= ProcessCount; m++) { //큐에 새로운 실행시간 안에 도착한 프로세스 삽입
                                    StringTokenizer processToken = new StringTokenizer(process[m]);
                                    String processId1 = processToken.nextToken();
                                    int arriveTime1 = Integer.parseInt(processToken.nextToken());
                                    int serviceTime1 = Integer.parseInt(processToken.nextToken());
                                    processToken.nextToken();
                                    int responseTime1 = Integer.parseInt(processToken.nextToken());
                                    if (total_servicetime >= arriveTime1 && check[m] != 0) {
                                        q.add(process[m]);
                                        check[m] = 0;
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
                        } else if (SaveServiceTime[i] != 0 && SaveServiceTime[i] < TimeQuantum) {
                            for (int j = 1; j <= TimeQuantum; j++) {
                                wait_time[i] += (total_servicetime - tmp_arrivetime[i]); //대기시간 저장
                                SaveServiceTime[i] -= 1;
                                total_servicetime++;

                                for (int m = 1; m <= ProcessCount; m++) { //큐에 새로운 실행시간 안에 도착한 프로세스 삽입
                                    StringTokenizer processToken = new StringTokenizer(process[m]);
                                    String processId1 = processToken.nextToken();
                                    int arriveTime1 = Integer.parseInt(processToken.nextToken());
                                    int serviceTime1 = Integer.parseInt(processToken.nextToken());
                                    processToken.nextToken();
                                    int responseTime1 = Integer.parseInt(processToken.nextToken());
                                    if (total_servicetime >= arriveTime1 && check[m] != 0) {
                                        q.add(process[m]);
                                        check[m] = 0;
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