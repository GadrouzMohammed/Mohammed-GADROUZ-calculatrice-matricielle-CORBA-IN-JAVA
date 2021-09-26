package corba_matrice_impl;

import MatriceService.calculeMatricielPOA;

public class ImplCalculMat extends calculeMatricielPOA {
    @Override
    public int[][] addition(int[][] m1, int[][] m2) {
        int[][] m3 = new int[m1.length][m2.length];

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                m3[i][j] = m1[i][j] + m2[i][j];
                System.out.println("resultat Add : " + m3[i][j]);
            }
        }

        return m3;
    }
    @Override
    public int[][] multiplication(int[][] m1, int[][] m2) {
        int[][] m3 = new int[m1.length][m2.length];
        // TODO Auto-generated method stub

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                for(int n= 0 ; n<m1.length; n++){
                m3[i][j] += m1[i][n] * m2[n][j];
                }
            }
        }
        return m3;
    }
}
