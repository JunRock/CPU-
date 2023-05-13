import java.util.*;

public class NonpreemptivePriority_Scheduling extends ProcessSort{
    public void run(){
        process=open();
        Deque<String> q = new LinkedList<>();
        Queue<String> tmp_q = new LinkedList<>(); //동일한 우선순위일 경우 도착시간에 따른 정렬까지 마침

        ArriveTimeSort();
        TimeSort();

        String[] tmp_arr = new String[ProcessCount -1];
        String[] arr = new String[ProcessCount -1];
        int[] tmp_priority1 = new int[ProcessCount -1];
        int[] tmp_servicetime1 = new int[ProcessCount -1];
        int[] tmp_arrivetime1 = new int[ProcessCount -1];

        for (int i = 0; i < ProcessCount -1; i++) {
            tmp_arr[i] = cpu_process[i+2]; //2 3 4 5 프로세스 정렬
        }

        for (int i = 0; i < ProcessCount -1; i++) {
            StringTokenizer processToken = new StringTokenizer(tmp_arr[i]);
                ProcessId = processToken.nextToken(); //프로세스 ID
                tmp_arrivetime1[i] = Integer.parseInt(processToken.nextToken()); //도착시간
                tmp_servicetime1[i] = Integer.parseInt(processToken.nextToken()); //실행시간
                tmp_priority1[i] = Integer.parseInt(processToken.nextToken()); //우선순위
                processToken.nextToken();
        }
        Arrays.sort(tmp_priority1); //1 2 2 4

        for(int i = 0; i< ProcessCount -1; i++){
            int count=0;
            for(int j = 0; j< ProcessCount -1; j++){
                StringTokenizer processToken=new StringTokenizer(tmp_arr[j]);
                int mcount=0;
                    ProcessId =processToken.nextToken();
                    ArriveTime =Integer.parseInt(processToken.nextToken());
                    ServiceTime =Integer.parseInt(processToken.nextToken());
                    priority=Integer.parseInt(processToken.nextToken());
                    processToken.nextToken();
                Iterator iterator=q.iterator(); //큐에 동일 프로세스가 있는 지 확인
                while(iterator.hasNext()){
                    StringTokenizer QToken=new StringTokenizer((String) iterator.next());
                    String proId=QToken.nextToken();
                    QToken.nextToken();QToken.nextToken();QToken.nextToken();QToken.nextToken();
                    if(ProcessId.equals(proId))
                        mcount++;
                }
                if(tmp_priority1[i]==priority&& mcount==0){
                    count++; //동일한 우선순위가 있는지 확인
                    tmp_q.add(tmp_arr[j]);
                }
            }
            if(count==1){
                while(!tmp_q.isEmpty()){
                    q.add(tmp_q.poll());
                }
            }
            else if(count>1){
                String str1=tmp_q.poll();
                String str2=tmp_q.poll();
                StringTokenizer str1Token=new StringTokenizer(str1);
                StringTokenizer str2Token=new StringTokenizer(str2);
                    str1Token.nextToken();
                    int atime1=Integer.parseInt(str1Token.nextToken());
                    str1Token.nextToken();str1Token.nextToken();str1Token.nextToken();
                    str2Token.nextToken();
                    int atime2=Integer.parseInt(str2Token.nextToken());
                    str2Token.nextToken();str2Token.nextToken();str2Token.nextToken();
                    if(atime1>atime2)
                        q.add(str2);
                    else
                        q.add(str1);
            }
        }
        q.addFirst(cpu_process[1]);
        System.out.println("비선점형 - 비선점 우선순위 스케줄링");
        Nonpreemptive_Print_Process print_process=new Nonpreemptive_Print_Process();
        print_process.print(q, ProcessCount);
    }
}
