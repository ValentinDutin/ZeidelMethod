public class ZeidelMethod {

    private double matrB[][];
    private double vectorG[];
    private double prevX[];
    private double curX[];
    private double epsilon = 0.00001;
    private double discrepancy[];
    private int realCount;
    private int n;
    private double symmetricMatrixRate;
    private double matrA[][];


    public ZeidelMethod(double matrA[][], double vectorB[]){
        n = matrA.length;
        realCount = 0;
        vectorG = new double[n];
        prevX = new double[n];
        curX = new double[n];
        matrB = new double[n][n];
        this.matrA = new double[n][n];
        for(int i = 0; i < n; i++){
            vectorG[i] = vectorB[i];
            curX[i] = vectorB[i];
            for(int j = 0; j < n; j++){
                this.matrA[i][j] = matrA[i][j];
            }
        }
    }


    private void createMatrB() {
        /*double symmetricMatr[][] = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                symmetricMatr[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    symmetricMatr[i][j] += matrA[k][i] * matrA[k][j];
                }
            }
        }*/
        double max = 0;
        double sum;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                sum += Math.abs(/*symmetricMatr*/matrA[i][j]);
            }
            if(sum > max) {
                max = sum;
            }
        }
        symmetricMatrixRate = max;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j){
                    matrB[i][i] = 1 - /*symmetricMatr*/matrA[i][i] / symmetricMatrixRate;
                }
                else{
                    matrB[i][j] = - /*symmetricMatr*/matrA[i][j] / symmetricMatrixRate;
                }
            }
        }
    }

    public void createVectorG(){
        /*double newVector[] = new double[n];
        for(int i = 0; i < n; i++) {
            newVector[i]=0;
            for(int j = 0; j < n; j++) {
                newVector[i] += matrA[j][i]*vectorG[j];
            }
        }*/
        for(int i = 0; i < n; i++){
            prevX[i] = vectorG[i]/matrA[i][i];
            vectorG[i] = /*newVector*/vectorG[i] / symmetricMatrixRate;

        }
    }

    private double difference(){
        double diff = Math.abs(prevX[0] - curX[0]);
        for(int i = 0; i < n; i++){
            if(Math.abs(prevX[i] - curX[i]) > diff){
                diff = Math.abs(prevX[i] - curX[i]);
            }
        }
        return diff;
    }
    private void newPrevX(){
        for(int i = 0; i < n; i++){
            prevX[i] = curX[i];
        }
    }

    public void zeidelMethod(){
        createMatrB();
        createVectorG();
        double sum;
        while (difference() >= epsilon){
            realCount++;
            newPrevX();
            for(int i = 0; i < n; i++){
                sum = 0;
                for(int j = 0; j < n; j++){
                        if(j < i){
                            sum += matrB[i][j]*curX[j];
                        }
                        else{
                            sum += matrB[i][j]*prevX[j];
                        }
                }
                sum += vectorG[i];
                curX[i] = sum;
            }
        }
    }
    public void createDiscrepancy(double matrA[][], double vectorB[]){
        discrepancy = new double[n];
        double[] res = new double[n];
        for(int i = 0; i < n; i++){
            res[i] = 0;
            for(int j = 0; j < n; j++){
                res[i] += matrA[i][j]*curX[j];
            }
            discrepancy[i] = vectorB[i] - res[i];
        }
    }

    public void print(){
        System.out.println("Vector X");
        for(double item: curX){
            System.out.format("%25s", item + "    ");
        }
        System.out.println("real count = " + realCount);
    }
    public void printDiscrepancy(){
        System.out.println("Discrepancy");
        for(double item: discrepancy){
            System.out.format("%25s", item + "    ");
        }
        System.out.println();
    }

    public void printRateB(){
        double rate = 0;
        double sum;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                sum += Math.abs(matrB[i][j]);
            }
            if(sum > rate) {
                rate = sum;
            }
        }
        System.out.println("Rate of matrix B = " + rate);
    }
    public void prinaAll(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.format("%25s", matrB[i][j] + "    ");
            }
            System.out.format("%25s", vectorG[i] + "\n");
        }

    }

}
