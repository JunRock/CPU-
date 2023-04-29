package 선점형스케줄링;

public class Aa {
    public static void main(String[] args) {
        double []tmp=new double[5];
        double []tmp1=new double[5];
        tmp[0]=2.0;
        tmp[1]=1.0;
        tmp[2]=4.0;
        tmp[3]=6.0;
        tmp[4]=3.0;
        int k=0;
        double m=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                m=Math.max(m,tmp[j]);
            }
            tmp1[i]=m;
        }

        for(int i=0;i<5;i++)
            System.out.println(tmp1[i]);
    }


}
