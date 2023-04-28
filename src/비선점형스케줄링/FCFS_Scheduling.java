package 비선점형스케줄링;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class FCFS_Scheduling {
    public void run() {
        File f = new File("c:\\Temp\\OS.txt");
        FileReader fin = null;
        BufferedReader br = null;
        String processId;
        Queue<String> q = new LinkedList<>();
        int[] arrtime = new int[5];
        int arriveTime, serviceTime, retime; //프로세스ID, 도착시간, 작업시간, 반환시간
        int num = 0;
        String[] process = new String[5];
        /*
        파일데이터 입력
         */
        try {
            br = new BufferedReader(new FileReader(f));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                process[num++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] tmp_time = new int[5];
        int sum = 0;
        int arrsum = 0; //프로세스 대기시간 총합
        double re_time_sum = 0;
        int[] time = new int[5]; //프로세스 별 반환시간 저장배열

        for (int i = 0; i < process.length; i++) {
            StringTokenizer st = new StringTokenizer(process[i]);
            while (st.hasMoreTokens()) {
                processId = st.nextToken(); //프로세스 ID
                arriveTime = Integer.parseInt(st.nextToken()); //도착시간
                serviceTime = Integer.parseInt(st.nextToken()); //실행시간
                st.nextToken(); //우선순위
                st.nextToken(); //시간 할당량
                /*
                프로세스 도착시간 정렬
                 */
                tmp_time[i] = arriveTime; //도착시간 모음배열
            }
        }

        Arrays.sort(tmp_time);
        int count = 0;
        int j = 0;
        for (int i = 0; i < process.length; i++) {
            for (int k = 0; k < process.length; k++) {
                StringTokenizer st1 = new StringTokenizer(process[k]);
                while (st1.hasMoreTokens()) {
                    processId = st1.nextToken();
                    arriveTime = Integer.parseInt(st1.nextToken());
                    if (tmp_time[i] == arriveTime)
                        q.add(process[k]);
                    st1.nextToken();
                    st1.nextToken();
                    st1.nextToken();
                }
            }
        }
        j = 0;
        Iterator it = q.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            StringTokenizer st = new StringTokenizer(str);
            while (st.hasMoreTokens()) {
                System.out.print(st.nextToken() + "의 대기시간: "); //프로세스 ID
                arriveTime = Integer.parseInt(st.nextToken()); //도착시간
                System.out.println(sum - arriveTime);
                arrsum += (sum - arriveTime);
                serviceTime = Integer.parseInt(st.nextToken()); //실행시간
                sum += serviceTime;
                time[j] = sum - arriveTime;
                j++;
                st.nextToken(); //우선순위
                st.nextToken(); //시간 할당량
            }
        }
        j = 0;
        System.out.println("평균 대기 시간(AWT): " + arrsum / process.length);
        it = q.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            StringTokenizer st = new StringTokenizer(str);
            while (st.hasMoreTokens()) {
                System.out.println(st.nextToken() + "의 반환시간: " + time[j]); //프로세스 ID
                st.nextToken();
                st.nextToken();
                st.nextToken(); //우선순위
                st.nextToken(); //시간 할당량
            }
            re_time_sum += time[j++];
        }
        System.out.println("평균 반환 시간(ATT): "+re_time_sum /process.length);
    }

    public static void main(String[] args) {
        FCFS_Scheduling fcfs_scheduling = new FCFS_Scheduling();
        fcfs_scheduling.run();
    }
}