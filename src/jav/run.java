package jav;

public class run {
    public static void main(String[] args) {
        GradientFirst gradientFirst =new GradientFirst(3,-1,0.0001,0.15,600);
        double[] gradFirst= gradientFirst.run();
        System.out.println(gradFirst[0]+" "+gradFirst[1]+" "+gradFirst[2]+" "+gradFirst[3]+" ");
        Newton newton =new Newton(3,-1,0.0001,0.15,600);
        double[] newtonAr= newton.run();
        System.out.println(newtonAr[0]+" "+newtonAr[1]+" "+newtonAr[2]+" "+newtonAr[3]+" ");
        Penalty penalty =new Penalty(3,-1,0.0001,600);
        double[] penaltyAr= penalty.run();
        System.out.println(penaltyAr[0]+" "+penaltyAr[1]+" "+penaltyAr[2]+" "+penaltyAr[3]+" ");
        GradientSecond gradientSecond =new GradientSecond(3,-1,0.0001,600_000);
        double[] gradientAr= gradientSecond.run();
        System.out.println(gradientAr[0]+" "+gradientAr[1]+" "+gradientAr[2]+" "+gradientAr[3]+" ");
    }
}
