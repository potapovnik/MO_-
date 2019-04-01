package jav;

import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class Newton {

    double u1,u2,EPS,a,N;
    double[] result=new double[4];
    public Newton(double u1, double u2, double EPS, double a, double N){
        this.u1=u1;
        this.u2=u2;
        this.EPS=EPS;
        this.a=a;
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

    public double fu11() { return (2);
    }

    public double fu22(double x2) { return (2 );
    }

    public double[] run() {
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
            double[][] arrrayForGes={{2.0,0.0},{0.0,2.0}};
            Matrix ges=new Matrix(arrrayForGes).Inverse();
            gradX=fu1(u1);
            gradY=fu2(u2);
            double[][] arrrayForGrad=new double[1][2];
            arrrayForGrad[0][0]=gradX;
            arrrayForGrad[0][1]=gradY;
            Matrix gradMatrix=new Matrix(arrrayForGrad);
            Matrix result=ges.MultiplyOnVector(gradMatrix);
            u1=u1-result.data[0][0];
            u2=u2-result.data[1][0];
            n++;
        }
        result[0]=u1;
        result[1]=u2;
        result[2]=n;
        result[3]=f(u1,u2);
        return result;


    }

}
