import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Process_Variable {
    static int num; //파일 라인수
    public String[] FileOpen(){
        File f = new File("c:\\Temp\\OS.txt");
        FileReader fin = null;
        BufferedReader br = null;
        String[] tmp_process = new String[100]; //파일 전체의 내용을 저장하는 배열
        num=0;
        try {
            br = new BufferedReader(new FileReader(f));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                tmp_process[num++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp_process;
    }

    Queue<String> q = new LinkedList<>(); //프로세스 정보를 저장하는 Queue
    Deque<String> deque =new LinkedList<>(); //임시로 프로세스 정보를 저장하는 Deque
    String process[]= FileOpen();
    double ArriveTime, ServiceTime, ResponseTime;
    String ProcessId;
    int ProcessCount =Integer.parseInt(process[0]); //전체 프로세스 개수
    int priority=0, ServiceTimeSum =0; //우선순위, 실행시간 총합
    String[] tmp_processId = new String[ProcessCount +1]; //프로세스ID 임시 저장 배열
    String[] SrtProcess =new String[ProcessCount +1]; // srt스케줄링 정렬된 프로세스 배열
    String[] NonpreemrtiveProcess = new String[ProcessCount +1]; //비선점 우선순위 스케줄링 정렬된 프로세스 배열
    String[] tmp_process=process;
    int[] tmp_servicetime = new int[ProcessCount +1]; //실행시간 임시 저장 배열
    int[] tmp_arrivetime = new int[ProcessCount +1]; //도착시간 임시 저장 배열
    int[] tmp_priority = new int[ProcessCount +1]; //우선순위 임시 저장 배열
    int[] tmp_time = new int[ProcessCount +1]; //시간 임시 저장 배열
    int[] wait_time=new int[ProcessCount +1]; //프로세스 대기시간 배열
    int[] return_time=new int[ProcessCount +1]; //프로세스 반환시간 배열
    int[] SaveServiceTime =new int[ProcessCount +1]; //각 프로세스 별 남은 실행시간 저장 배열
    int[] response_time=new int[ProcessCount +1]; //각 프로세스 응답시간값 저장 배열
    int[] restime=new int[ProcessCount +1]; //각 프로세스 응답시간
    int TimeQuantum =Integer.parseInt(process[num-1]); //타임슬라이스
    int[] check=new int[ProcessCount +1]; //검사한 프로세스인지 확인하는 배열
}
