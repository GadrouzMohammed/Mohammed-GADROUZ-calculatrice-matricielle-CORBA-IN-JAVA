package corba_matrice_impl;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import MatriceService.calculeMatriciel;
import MatriceService.calculeMatricielHelper;
import org.omg.PortableServer.*;

public class Serveur {

    public static void main(String[] args) {
        try {
            // Intialisation de l'ORB

            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

            // Gestion du POA
            // Recuperation du POA
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

            // Activer le POA manager
            rootPOA.the_POAManager().activate();
            // Creation du servant

            ImplCalculMat matrice = new ImplCalculMat();
            org.omg.CORBA.Object objref = rootPOA.servant_to_reference(matrice);

            calculeMatriciel matriceRef = calculeMatricielHelper.narrow(objref);
            // Utilisation du service de noms

            org.omg.CORBA.Object ann
                    = orb.resolve_initial_references("NameService");
            NamingContextExt annuaireDistant = NamingContextExtHelper.narrow(ann);
            String nom = "calculeMatrice";
            annuaireDistant.rebind(annuaireDistant.to_name(nom), matriceRef);
            System.out.println("Serveur pret.....");

            // Lancement de l'ORB et mise en attente de requete
            orb.run();

        } catch (Exception e) {
            System.err.println("erreur");
            e.getMessage();
        }

    }
}
