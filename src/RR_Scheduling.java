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

        for (int i = 1; i <= process_count; i++) {
            for (int j = 1; j <= process_count; j++) {
                StringTokenizer tmpProcessToken = new StringTokenizer(tmp_process[j]);
                processId = tmpProcessToken.nextToken();
                arriveTime = Integer.parseInt(tmpProcessToken.nextToken());
                serviceTime = Integer.parseInt(tmpProcessToken.nextToken());
                tmpProcessToken.nextToken();
                tmpProcessToken.nextToken();
                if (tmp_time[i] == arriveTime) {
                    tmp_processId[i] = processId;
                    tmp_arrivetime[i] = (int) arriveTime;
                    tmp_servicetime[i] = (int) serviceTime;
                    save_servicetime[i] = (int) serviceTime;
                    check[i] = 1;
                    process[i] = tmp_process[i];
                }
            }
        }

        while (total_servicetime != servicetime_sum) {
            String str = q.poll();
            StringTokenizer strToken = new StringTokenizer(str);
            processId = strToken.nextToken();
            arriveTime = Integer.parseInt(strToken.nextToken());
            serviceTime = Integer.parseInt(strToken.nextToken());
            strToken.nextToken();
            responseTime = Integer.parseInt(strToken.nextToken());

            for (int i = 1; i <= process_count; i++) {
                if (tmp_processId[i].equals(processId)) {
                    if (total_servicetime >= arriveTime) {
                        check[i] = 0;

                        if (save_servicetime[i] >= time_quantum) {
                            for (int j = 1; j <= time_quantum; j++) {
                                wait_time[i] += (total_servicetime - tmp_arrivetime[i]); //대기시간 저장
                                save_servicetime[i] -= 1;
                                total_servicetime++;

                                for (int m = 1; m <= process_count; m++) { //큐에 새로운 실행시간 안에 도착한 프로세스 삽입
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
                                    response_time[i] = (int) ((total_servicetime + responseTime) - arriveTime);
                                tmp_arrivetime[i] = total_servicetime;
                                ganttchatt[c++] = processId;
                                if (save_servicetime[i] == 0) {
                                    return_time[i] = (int) (total_servicetime - arriveTime);
                                    break;
                                }
                            }
                            q.add(process[i]);
                        } else if (save_servicetime[i] != 0 && save_servicetime[i] < time_quantum) {
                            for (int j = 1; j <= time_quantum; j++) {
                                wait_time[i] += (total_servicetime - tmp_arrivetime[i]); //대기시간 저장
                                save_servicetime[i] -= 1;
                                total_servicetime++;

                                for (int m = 1; m <= process_count; m++) { //큐에 새로운 실행시간 안에 도착한 프로세스 삽입
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
                                    response_time[i] = (int) ((total_servicetime + responseTime) - arriveTime);
                                tmp_arrivetime[i] = total_servicetime;
                                ganttchatt[c++] = processId;
                                if (save_servicetime[i] == 0) {
                                    return_time[i] = (int) (total_servicetime - arriveTime);
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
            print_process.print(process_count, wait_time, tmp_processId, return_time, ganttchatt, response_time);
        }
    }