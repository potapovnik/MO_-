package jav;

public class Matrix {
    private final int M;             // количество столбцов
    private final int N;             // количество строк
    public double[][] data;

    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                this.data[i][j] = data[i][j];
    }

    private Matrix(Matrix A) {
        this(A.data);
    }

    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    public static Matrix identity(int N) { //создание единичной матрицы
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }


    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Неправильный размер матрицы");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }

    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Неправильный размер матрицы");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    public Matrix multiply(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Неправильный размер матрицы");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }

    public boolean equal(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Неправильный размер матрицы");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    public double determinant(double A[][], int N) {
        double det = 0;
        if (N == 1) {
            det = A[0][0];
        } else if (N == 2) {
            det = A[0][0] * A[1][1] - A[1][0] * A[0][1];
        } else {
            det = 0;
            for (int i = 0; i < N; i++) {
                double[][] keep = new double[N - 1][N - 1];
                for (int k = 1; k < N; k++) {
                    int iterator = 0;
                    for (int j = 0; j < N; j++) {
                        if (j != i) {
                            keep[k - 1][iterator] = A[k][j];
                            iterator++;
                        }
                    }
                }
                det += Math.pow(-1.0, 1.0 + i + 1.0) * A[0][i] * determinant(keep, N - 1);
            }
        }
        return det;
    }

    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(this.data[i][j] + " ");
            System.out.println();
        }
    }

    public Matrix Inverse() {
        if (N != M | Determinant() == 0)
            return null;
        Matrix trMatrix = Transpose();
        double det = Determinant();
        Matrix result = new Matrix(N, M);
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                result.data[i][j] = trMatrix.AlgDop(i, j);
            }
        }
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                result.data[i][j] /= det;
            }
        }
        return result;
    }


    public Matrix Transpose() {
        Matrix result = new Matrix(N, M);
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                result.data[j][i] = data[i][j];
            }
        }
        return result;
    }

    public double Determinant() {
        if (N != M)
            return 0;
        return detCounting(data);
    }


    private double detCounting(double[][] ar) {
        if (ar.length == 1)
            return ar[0][0];
        if (ar.length == 2)
            return ar[0][0] * ar[1][1] - ar[0][1] * ar[1][0];
        double det = 0;
        int k = 1;
        for (int i = 0; i < ar.length; ++i, k *= -1) {
            det += k * ar[0][i] * detCounting(MatrixForMinor(ar, 0, i));
        }
        return det;
    }


    public static double[][] MatrixForMinor(double[][] matrix, int row, int col) {
        double[][] res = new double[matrix.length - 1][matrix.length - 1];
        int di = 0, dj = 0;
        for (int i = 0; i < res.length; ++i) {
            dj = 0;
            for (int j = 0; j < res.length; ++j) {
                if (i == row && di == 0)
                    di = 1;
                if (j == col && dj == 0)
                    dj = 1;
                res[i][j] = matrix[i + di][j + dj];
            }
        }

        return res;
    }


    public double Minor(int row, int col) {
        if (N != M) {
            return 0;
        }
        return detCounting(MatrixForMinor(data, row, col));
    }

    public double AlgDop(int row, int col) {
        if ((row + col) % 2 == 1)
            return -1 * Minor(row, col);
        else
            return Minor(row, col);
    }

    public Matrix multiOnNumber(double number) {
        Matrix matrix = new Matrix(this.M, this.N);
        for (int i = 0; i < matrix.M; i++)
            for (int j = 0; j < matrix.N; j++) {
                matrix.data[i][j] = this.data[i][j] * number;
            }
        return matrix;
    }

    public Matrix Grade(int count) {
        Matrix result = new Matrix(M, N);
        count--;
        if (count > 0) {
            result = this.multiply(this);
            count--;
            while (count > 0) {
                result=result.multiply(result);
                count--;
            }
        }
        return result;
    }
    public Matrix MultiplyOnVector(Matrix a){
        Matrix res=new Matrix(M,1);
        for (int i=0;i<M;i++){
            for (int j=0;j<N;j++){
                res.data[i][0]+=this.data[i][j]*a.data[0][j];
            }
        }
        return res;
    }


}
