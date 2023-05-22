import java.io.*;
import java.util.*;
public class ProcessVariable {
    static int num; //파일 라인수
    public String[] fileOpen(){
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
    String process[]= fileOpen();
    double arriveTime, serviceTime, responseTime;
    String processId;
    int processCount =Integer.parseInt(process[0]); //전체 프로세스 개수
    int priority=0, serviceTimeSum =0; //우선순위, 실행시간 총합
    String[] tmpProcessId = new String[processCount +1]; //프로세스ID 임시 저장 배열
    String[] srtProcess =new String[processCount +1]; // srt스케줄링 정렬된 프로세스 배열
    String[] nonpreemrtiveProcess = new String[processCount +1]; //비선점 우선순위 스케줄링 정렬된 프로세스 배열
    String[] tmpProcess =process;
    int[] tmpServiceTime = new int[processCount +1]; //실행시간 임시 저장 배열
    int[] tmpArriveTime = new int[processCount +1]; //도착시간 임시 저장 배열
    int[] tmpPriority = new int[processCount +1]; //우선순위 임시 저장 배열
    int[] tmpTime = new int[processCount +1]; //시간 임시 저장 배열
    int[] waitTime =new int[processCount +1]; //프로세스 대기시간 배열
    int[] returnTime =new int[processCount +1]; //프로세스 반환시간 배열
    int[] tmpResponseTime =new int[processCount +1]; //각 프로세스 응답시간값 저장 배열
    int[] saveServiceTime =new int[processCount +1]; //각 프로세스 별 남은 실행시간 저장 배열
    int[] restime=new int[processCount +1]; //각 프로세스 응답시간
    int timeQuantum =Integer.parseInt(process[num-1]); //타임슬라이스
    int[] check=new int[processCount +1]; //검사한 프로세스인지 확인하는 배열
}
