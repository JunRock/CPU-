public class Preemptive_Print_Process {
    public void print(int process_count,int wait_time[],String tmp_processId[],int return_time[]){
        int wait_sum=0;
        int return_sum=0;
        for(int i=1;i<=process_count;i++){
            System.out.println(tmp_processId[i]+"의 대기시간: "+wait_time[i]);
            wait_sum+=wait_time[i];
        }
        System.out.println("평균 대기 시간: "+(double)wait_sum/process_count);

        for(int i=1;i<=process_count;i++){
            System.out.println(tmp_processId[i]+"의 반환시간: "+return_time[i]);
            return_sum+=return_time[i];
        }
        System.out.println("평균 반환 시간: "+(double)return_sum/process_count);
    }
}
