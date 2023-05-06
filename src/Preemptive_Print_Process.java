public class Preemptive_Print_Process {
    public void print(int process_count,int wait_time[],String tmp_processId[],int return_time[],String gant[],int responsetime[]){
        int wait_sum=0;
        int return_sum=0;
        int response_sum=0;
        System.out.println("<간트차트>");
        for(int i=0;i< gant.length;i++){
            System.out.print("["+gant[i]+"]");
        }

        System.out.println();
        for(int i=1;i<=process_count;i++){
            System.out.println(tmp_processId[i]+"의 대기시간: "+wait_time[i]);
            wait_sum+=wait_time[i];
        }
        System.out.println("평균 대기 시간(AWT): "+(double)wait_sum/process_count);

        for(int i=1;i<=process_count;i++){
            System.out.println(tmp_processId[i]+"의 응답시간: "+responsetime[i]);
            response_sum+=responsetime[i];
        }
        System.out.println("평균 응답 시간(ART): "+(double)response_sum/process_count);

        for(int i=1;i<=process_count;i++){
            System.out.println(tmp_processId[i]+"의 반환시간: "+return_time[i]);
            return_sum+=return_time[i];
        }
        System.out.println("평균 반환 시간(ATT): "+(double)return_sum/process_count);
    }
}
