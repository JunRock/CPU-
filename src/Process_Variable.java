import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Process_Variable {
    static int num;
    public String[] open(){
        File f = new File("c:\\Temp\\OS.txt");
        FileReader fin = null;
        BufferedReader br = null;
        String[] process1 = new String[7];
        num=0;
        try {
            br = new BufferedReader(new FileReader(f));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                process1[num++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return process1;
    }
    String process[]=open();
    double arriveTime, serviceTime,responseTime;
    String processId;
    int process_count=Integer.parseInt(process[0]);
    double sum=0;
    int priority=0,servicetime_sum=0;
    String[] tmp_processId = new String[process_count+1];
    int[] tmp_servicetime = new int[process_count+1];
    int[] tmp_arrivetime = new int[process_count+1];
    int[] tmp_priority = new int[process_count+1];
    int[] tmp_time = new int[process_count+1];
    int[] wait_time=new int[process_count+1];
    int[] return_time=new int[process_count+1];
    int[] save_servicetime=new int[process_count+1];
    int[] response_time=new int[process_count+1];
    int[] restime=new int[process_count+1];
    int time_quantum=Integer.parseInt(process[num-1]);
}
