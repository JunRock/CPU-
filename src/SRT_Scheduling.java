import java.util.*;
import java.util.stream.Stream;

public class SRT_Scheduling {

    public void run(){
        FIle_Open fIle_open=new FIle_Open();
        String []process;
        process=fIle_open.open();
        int num=FIle_Open.num;
        int max=0;
        Queue<String> q=new LinkedList<>();
        int process_count=Integer.parseInt(process[0]);
        int time_quantum=Integer.parseInt(process[num-1]);
        int[] tmp_time = new int[process_count+1];
        int[] return_time=new int[process_count+1];
        String processId;
        String[] process_real=new String[process_count+1];
        String [] save_pid=new String[process_count+1];
        int arriveTime, serviceTime; //프로세스ID, 도착시간, 작업시간, 반환시간
        int [] save_servicetime=new int[process_count+1]; //변경되는 실행시간 저장 배열
        int servicetime_sum=0;
        int[] wait_time=new int[process_count+1];
        int[] tmp_servicetime=new int[process_count+1];
        int[] tmp_arrivetime=new int[process_count+1];

        for(int i=1;i<=process_count;i++){
            StringTokenizer st=new StringTokenizer(process[i]);
            while(st.hasMoreTokens()){
                processId=st.nextToken();
                arriveTime=Integer.parseInt(st.nextToken());
                serviceTime=Integer.parseInt(st.nextToken());
                st.nextToken();//우선순위
                tmp_time[i]=arriveTime;
                servicetime_sum+=serviceTime; //총 실행시간 저장
                max=Math.max(max,serviceTime);
            }
        }
        Arrays.sort(tmp_time);
        /*
        도착시간 정렬
         */
        for(int i=1;i<=process_count;i++){
            for(int j=1;j<=process_count;j++){
                StringTokenizer st=new StringTokenizer(process[j]);
                while(st.hasMoreTokens()){
                    processId=st.nextToken();
                    arriveTime=Integer.parseInt(st.nextToken());
                    serviceTime=Integer.parseInt(st.nextToken());
                    st.nextToken();//우선순위

                    if(tmp_time[i]==arriveTime){
                        process_real[i]=process[j];
                        save_servicetime[i]=serviceTime;
                        tmp_arrivetime[i]=arriveTime;
                        save_pid[i]=processId;
                    }
                }
            }
        }

        Deque<String> tmp_q=new LinkedList<>();
        int c=0;
        int total_servicetime=0;

        int tmp_index=1;
        StringTokenizer st1=new StringTokenizer(process_real[1]);
        while(st1.hasMoreTokens()){
            processId = st1.nextToken();
            arriveTime = Integer.parseInt(st1.nextToken());
            serviceTime = Integer.parseInt(st1.nextToken());
            st1.nextToken();
            if (save_servicetime[1] >= time_quantum) {
                wait_time[1] += (total_servicetime - tmp_arrivetime[1]); //대기시간 저장
                save_servicetime[1] -= time_quantum;
                total_servicetime += time_quantum;
                tmp_arrivetime[1] = total_servicetime;
                if (save_servicetime[1] == 0) {
                    return_time[1] = total_servicetime - arriveTime;
                    process[tmp_index++]=process_real[1];
                    break;
                }
            }
            else if (save_servicetime[1] != 0 && save_servicetime[1] < time_quantum) {
                wait_time[1] += (total_servicetime - tmp_arrivetime[1]);
                total_servicetime += save_servicetime[1];
            }
        }

        while(total_servicetime!=servicetime_sum) {
            int index=0;
            if (c!=process_count) {
                int min=max;
                for(int i=1;i<=process_count;i++){
                         if(min>=save_servicetime[i]&&save_servicetime[i]!=0&&tmp_arrivetime[i]<=total_servicetime){
                             min=save_servicetime[i];
                              index=i;
                         }
                     }
                if (save_servicetime[index] >= time_quantum) {
                    wait_time[index] += (total_servicetime - tmp_arrivetime[index]); //대기시간 저장
                    save_servicetime[index] -= time_quantum;
                    total_servicetime += time_quantum;
                    tmp_arrivetime[index] = total_servicetime;
                    if (save_servicetime[index] == 0) {
                        StringTokenizer st=new StringTokenizer(process_real[index]);
                        st.nextToken();
                        arriveTime=Integer.parseInt(st.nextToken());
                        st.nextToken();st.nextToken();
                        return_time[index] = total_servicetime - arriveTime;
                        c++;
                        continue;
                    }
                }
                else if (save_servicetime[index] != 0 && save_servicetime[index] < time_quantum) {
                    wait_time[index] += (total_servicetime - tmp_arrivetime[index]);
                    total_servicetime += save_servicetime[index];
                }
                }
            }


        int wait_sum=0;
        int return_sum=0;
        for(int i=1;i<=process_count;i++){
            System.out.println(save_pid[i]+"의 대기시간: "+wait_time[i]);
            wait_sum+=wait_time[i];
        }
        System.out.println("평균 대기 시간: "+(double)wait_sum/process_count);

        for(int i=1;i<=process_count;i++){
            System.out.println(save_pid[i]+"의 반환시간: "+return_time[i]);
            return_sum+=return_time[i];
        }
        System.out.println("평균 반환 시간: "+(double)return_sum/process_count);
    }

    public static void main(String[] args) {
        SRT_Scheduling srt_scheduling=new SRT_Scheduling();
        srt_scheduling.run();
    }
}