public class Main {
    public static void main(String[] args) {
        FCFS_Scheduling fcfs=new FCFS_Scheduling();
        SJF_Scheduling sjf=new SJF_Scheduling();
        HRN_Scheduling hrn=new HRN_Scheduling();
        NonpreemptivePriority_Scheduling np=new NonpreemptivePriority_Scheduling();
        RR_Scheduling rr=new RR_Scheduling();
        SRT_Scheduling srt=new SRT_Scheduling();
        PreemptivePriority_Scheduling pp=new PreemptivePriority_Scheduling();

        //fcfs.run();
        //sjf.run();
        //hrn.run();
        //np.run();
        //rr.run();
        srt.run();
        //pp.run();
    }
}
