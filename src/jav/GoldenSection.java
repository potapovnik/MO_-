package jav;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

class GoldenSection {

    final double PHI = (1 + Math.sqrt(5)) / 2;
    double EPS, N;
    double xl, xr;
    double[] result = new double[2];
    double u1;
    double u2;
    double grad1;
    double grad2;
    public GoldenSection(double eps, double left, double right, double N,double u1,double u2, double grad1,double grad2) {
        EPS = eps;
        xl = left;
        xr = right;
        this.N = N;
        this.grad1=grad1;
        this.grad2=grad2;
        this.u1=u1;
        this.u2=u2;
    }

    double f(double x) {
        return pow(u1-x*grad1 - 5, 2) + pow((u2-x*grad2 + 3), 2);
    }

    public double[] run() {
        double x1, x2;
        int n = 0;
        while (n < N) {
            n++;
            x1 = xr - (xr - xl) / PHI;
            x2 = xl + (xr - xl) / PHI;
            if (f(x1) >= f(x2))
                xl = x1;
            else
                xr = x2;
            if (abs(xl - xr) < EPS) {
                result[0] = f((xl + xr) / 2);
                result[1] = n;
                return result;
            }
        }

        result[0] = f((xl + xr) / 2);
        result[1] = n;
        return result;
    }

}
