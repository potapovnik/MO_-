package jav;

import java.lang.reflect.Method;

import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class GradientFirst {
    double u1,u2,EPS,a,N;
    double[] result=new double[4];
    static double r=0;
    public GradientFirst(double u1, double u2, double EPS, double a, double N){
        this.u1=u1;
        this.u2=u2;
        this.EPS=EPS;
        this.a=a;
        this.N=N;
    }
    public double f(double x1, double x2) {
        return (pow(x1 - 5, 2) + pow((x2 + 3), 2))+r;
    }

    public double fu1(double x1) {
        return (2 * (x1 - 5));
    }

    public double fu2(double x2) {
        return (2 * (x2 + 3));
    }
    public double[] run(){
        double grad;
        double n=0;
        double Ffisrt=f(u1,u2);
        double FSecond=0;
        double gradX;
        double gradY;
        while (n<N){
            grad=sqrt(pow(fu1(u1),2)+pow(fu2(u2),2));

            if (grad<EPS){
                break;
            }
            gradX=fu1(u1);
            gradY=fu2(u2);

            double newu1=u1-a*gradX;
            double newu2=u2-a*gradY;

            FSecond=f(newu1,newu2);

            if (FSecond < Ffisrt){
                u1=newu1;
                u2=newu2;
                Ffisrt=FSecond;

                grad=sqrt(pow(fu1(newu1),2)+pow(fu2(newu2),2));
                if (grad<EPS){
                    break;
                }

            }
            else {
                a=a/2;
            }
            n++;
        }
        result[0]=u1;
        result[1]=u2;
        result[2]=n;
        result[3]=f(u1,u2);
        return result;
    }
}
