package 비선점형스케줄링;
import java.util.*;
public class FCFS_Scheduling {
    public void run(){
        Scanner sc=new Scanner(System.in);
        int [][]process=new int[5][1];
        int []ATT=new int[5]; //평균반환시간
        int []AWT=new int[5]; //평균반환시간

        int arrive,active;
        System.out.println("도착시간 및 실행시간 입력");
        for(int i=0;i<process.length;i++){
            for(int j=0;j<1;j++){
                process[i][j]=sc.nextInt();
            }
        }
    }

    public static void main(String[] args) {
        FCFS_Scheduling fcfs_scheduling=new FCFS_Scheduling();
        fcfs_scheduling.run();
    }
}
