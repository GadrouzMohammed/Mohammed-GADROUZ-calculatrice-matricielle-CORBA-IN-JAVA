package corba_matrice_impl;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import MatriceService.calculeMatriciel;
import MatriceService.calculeMatricielHelper;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) {

        //initialisation de l'ORB
        ORB orb = ORB.init(args, null);
        //obtention de la r�ference du serveur et invocation de la m�thode affiche
        org.omg.CORBA.Object matriceRef;
        try {
            matriceRef = orb.resolve_initial_references("NameService");
            NamingContextExt annuaireDistant = NamingContextExtHelper.narrow(matriceRef);
            calculeMatriciel matrice = calculeMatricielHelper.narrow(annuaireDistant.resolve_str("calculeMatrice"));

            System.out.print("Saisir le nombre de lignes dans la matrice: ");
            Scanner sc = new Scanner(System.in);
            int m = sc.nextInt();
            System.out.print("Saisir le nombre de colonnes dans la matrice: ");
            int n = sc.nextInt();
            //déclarer la matrice
            System.out.println("Saisir les valeurs matrice 1");
            int[][] m1 = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(String.format("Entrez m1[%d][%d] : ", i, j));
                    m1[i][j] = sc.nextInt();
                }
            }
            System.out.println("Saisir les valeurs matrice 2");
            int[][] m2 = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(String.format("Entrez m2[%d][%d] : ", i, j));
                    m2[i][j] = sc.nextInt();
                }
            }
            int[][] m3 = new int[n][m];

            int[][] resultatAdd = matrice.addition(m1, m2);
            int[][] resultatMult = matrice.multiplication(m1, m2);
            System.out.println("\n ");
            System.out.println("la somme des deux matrices matrice1 et matrice 2 :");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.println("[" + i + "][" + j + "]=" + " m1:" + m1[i][j] + " m2:" + m2[i][j]
                            + " addition:" + resultatAdd[i][j]);
                }
            }
            System.out.println("\n ");
            System.out.println("la multiplication des deux matrice1 et matrice2 :");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.println("[" + i + "][" + j + "]=" + " m1:" + m1[i][j] + " m2:" + m2[i][j]
                            + " multiplication:" + resultatMult[i][j]);
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
