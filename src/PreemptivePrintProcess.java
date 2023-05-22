public class PreemptivePrintProcess {
    public void print(int process_count,int waitTime[],String tmpProcessId[],int returnTime[],String gant[],int responseTime[]){
        int waitSum=0;
        int returnSum=0;
        int responseSum=0;
        int count=0;
        System.out.println("<간트차트>");
        for(int i=0;i< gant.length;i++){
            count++;
            if(count== gant.length/2)
                System.out.println();
            System.out.print("["+gant[i]+"]");
        }

        System.out.println();
        for(int i=1;i<=process_count;i++){
            System.out.println(tmpProcessId[i]+"의 대기시간: "+waitTime[i]);
            waitSum+=waitTime[i];
        }
        System.out.println("평균 대기 시간(AWT): "+(double)waitSum/process_count);

        for(int i=1;i<=process_count;i++){
            System.out.println(tmpProcessId[i]+"의 응답시간: "+responseTime[i]);
            responseSum+=responseTime[i];
        }
        System.out.println("평균 응답 시간(ART): "+(double)responseSum/process_count);

        for(int i=1;i<=process_count;i++){
            System.out.println(tmpProcessId[i]+"의 반환시간: "+returnTime[i]);
            returnSum+=returnTime[i];
        }
        System.out.println("평균 반환 시간(ATT): "+(double)returnSum/process_count);
    }
}
