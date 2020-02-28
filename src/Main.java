public class Main {
    public static void main(String[] args) {
        try {
            double matrA[][] = {
                    {0.5757, -0.0758, 0.0152, 0.0303, 0.1061},
                    {0.0788, 0.9014, 0.0000, -0.0606, 0.0606},
                    {0.0455, 0.0000, 0.7242, -0.2121, 0.1212},
                    {-0.0909, 0.1909, 0.0000, 0.7121, -0.0303},
                    {0.3788, 0.0000, 0.1364, 0.0152, 0.8484}
            };
            double vectorB[] = {
                    3.5148,
                    3.8542,
                    -4.9056,
                    2.3240,
                    0.1818
            };
            ZeidelMethod zm = new ZeidelMethod(matrA, vectorB);
            zm.zeidelMethod();
            zm.print();
            zm.prinaAll();
            zm.createDiscrepancy(matrA, vectorB);
            zm.printDiscrepancy();
            zm.printRateB();

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
