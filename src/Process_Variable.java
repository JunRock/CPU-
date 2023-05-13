import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Process_Variable {
    static int num;
    public String[] open(){
        File f = new File("c:\\Temp\\OS.txt");
        FileReader fin = null;
        BufferedReader br = null;
        String[] tmp_process = new String[100];
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

    Queue<String> q = new LinkedList<>();
    Deque<String> tmp_q=new LinkedList<>();
    Deque<String> deque = new LinkedList<>();
    String process[]=open();
    double ArriveTime, ServiceTime, ResponseTime;
    String ProcessId;
    int ProcessCount =Integer.parseInt(process[0]);
    double sum=0;
    int priority=0,servicetime_sum=0,max=0;
    String[] tmp_processId = new String[ProcessCount +1];
    String[] process_real=new String[ProcessCount +1];
    String[] cpu_process = new String[ProcessCount +1];
    String[] tmp_process=process;
    int[] tmp_servicetime = new int[ProcessCount +1];
    int[] tmp_arrivetime = new int[ProcessCount +1];
    int[] tmp_priority = new int[ProcessCount +1];
    int[] tmp_time = new int[ProcessCount +1];
    int[] wait_time=new int[ProcessCount +1];
    int[] return_time=new int[ProcessCount +1];
    int[] SaveServiceTime =new int[ProcessCount +1];
    int[] response_time=new int[ProcessCount +1];
    int[] restime=new int[ProcessCount +1];
    int TimeQuantum =Integer.parseInt(process[num-1]);
    int[] check=new int[ProcessCount +1];
}
