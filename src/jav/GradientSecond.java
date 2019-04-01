package jav;

import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class GradientSecond {
    double u1,u2,EPS,a,N;
    double[] result=new double[4];
    public GradientSecond(double u1, double u2, double EPS, double N){
        this.u1=u1;
        this.u2=u2;
        this.EPS=EPS;
        this.N=N;
    }
    public double f(double x1, double x2) {
        return (pow(x1 - 5, 2) + pow((x2 + 3), 2));
    }

    public double fu1(double x1) {
        return (2 * (x1 - 5));
    }

    public double fu2(double x2) {
        return (2 * (x2 + 3));
    }
   public double[] run(){
        GoldenSection goldenSection;
        double grad;
        double n=0;
        double gradX;
        double gradY;
        while (true&&n<N){
            gradX=fu1(u1);
            gradY=fu2(u2);
            grad=sqrt(pow(fu1(u1),2)+pow(fu2(u2),2));
            if (grad<EPS){
                break;
            }
            goldenSection=new GoldenSection(0.1,0.000001,1,50,u1,u2,gradX,gradY);
            a=goldenSection.run()[0];
            u1=u1-a*gradX;
            u2=u2-a*gradY;
            n++;
        }
        result[0]=u1;
        result[1]=u2;
        result[2]=n;
        result[3]=f(u1,u2);
        return result;
    }
}
