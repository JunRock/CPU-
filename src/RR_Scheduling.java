import java.util.*;

public class RR_Scheduling extends Process_Variable{
    public void run() {
        process = open();
        Queue<String> q = new LinkedList<>();
        int c = 0;
        int total_servicetime = 0;
        String[] tmp_process;
        tmp_process = process;


        for (int i = 1; i <= process_count; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
            processId = st.nextToken();
            arriveTime = Integer.parseInt(st.nextToken());
            serviceTime = Integer.parseInt(st.nextToken());
            st.nextToken();
            st.nextToken();
            tmp_time[i] = (int) arriveTime;
            if (arriveTime == 0)
                q.add(process[i]);
            servicetime_sum += serviceTime; //총 실행시간 저장
        }

        String[] ganttchatt = new String[servicetime_sum];
        Arrays.sort(tmp_time);

        for (int i = 1; i <= process_count; i++) {
            for (int j = 1; j <= process_count; j++) {
                StringTokenizer st = new StringTokenizer(tmp_process[j]);
                processId = st.nextToken();
                arriveTime = Integer.parseInt(st.nextToken());
                serviceTime = Integer.parseInt(st.nextToken());
                st.nextToken();
                st.nextToken();
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
            StringTokenizer st = new StringTokenizer(str);
            processId = st.nextToken();
            arriveTime = Integer.parseInt(st.nextToken());
            serviceTime = Integer.parseInt(st.nextToken());
            st.nextToken();
            responseTime = Integer.parseInt(st.nextToken());

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
                                    StringTokenizer st1 = new StringTokenizer(process[m]);
                                    String processId1 = st1.nextToken();
                                    int arriveTime1 = Integer.parseInt(st1.nextToken());
                                    int serviceTime1 = Integer.parseInt(st1.nextToken());
                                    st1.nextToken();
                                    int responseTime1 = Integer.parseInt(st1.nextToken());
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
                                    StringTokenizer st1 = new StringTokenizer(process[m]);
                                    String processId1 = st1.nextToken();
                                    int arriveTime1 = Integer.parseInt(st1.nextToken());
                                    int serviceTime1 = Integer.parseInt(st1.nextToken());
                                    st1.nextToken();
                                    int responseTime1 = Integer.parseInt(st1.nextToken());
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