public class Main {
    public static void main(String[] args) {
        FcfsScheduling fcfs=new FcfsScheduling();
        SjfScheduling sjf=new SjfScheduling();
        HrnScheduling hrn=new HrnScheduling();
        NonpreemptivePriorityScheduling np=new NonpreemptivePriorityScheduling();
        RrScheduling rr=new RrScheduling();
        SrtScheduling srt=new SrtScheduling();
        PreemptivePriorityScheduling pp=new PreemptivePriorityScheduling();

        fcfs.run();
        sjf.run();
        hrn.run();
        np.run();
        rr.run();
        srt.run();
        pp.run();
    }
}
