package jav;

public class Penalty {
    double u1,u2,EPS,N;
    double[] result=new double[4];
    GradientFirst gradientFirst;
    public Penalty(double u1, double u2, double EPS, double N){
        this.u1=u1;
        this.u2=u2;
        this.EPS=EPS;
        this.N=N;
    }
    public double funcOfPenalty(double u1,double u2,double r){
        return r*(Math.abs(1/(u1-5)+1/(u2+3)));
    }
    public double[] run(){
        double rk=1;
        double n=0;
        double[] keepResult={100,100,100,100};
        while (n<N){
            gradientFirst=new GradientFirst(u1,u2,EPS,0.15,50);
            GradientFirst.r=funcOfPenalty(u1,u2,rk);
            keepResult=gradientFirst.run();
            if (rk<EPS){
                break;
            }
            u1=keepResult[0];
            u2=keepResult[1];
            n++;
            rk/=10;
        }
        result[0]=u1;
        result[1]=u2;
        result[2]=n;
        result[3]=keepResult[3];
        return result;
    }
}

