import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MainInterface{

    private static final String ACCUEIL = "PageAccueil";
    private static final String CONNECTIONPRO = "PageConnectionProprio";
    private static final String CONNECTIONCOLO = "PageConnectionColoc";


    public static void main(String[] args) {
        // On définit la fenetre
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Gestionnaire de Colocation");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setMinimumSize(new Dimension (1000, 700));

        // On définit les différentes pages affichables        
        CardLayout cardLayout = new CardLayout();
        GestionnaireAffichage gestionAff = new GestionnaireAffichage(cardLayout, new JPanel(cardLayout), ACCUEIL);

//-------------------------
        PageAccueil accueil = new PageAccueil(gestionAff);
        gestionAff.getPanneauPrincipal().add(accueil, ACCUEIL);
//-------------------------
        PageConnection connectionProprio = new PageConnection(gestionAff, "Bienvenue Jeune Entrepreneur !");
        gestionAff.getPanneauPrincipal().add(connectionProprio, CONNECTIONPRO);
//-------------------------
        PageConnection connectionColoc = new PageConnection(gestionAff, "Bienvenue Jeune Prolétaire !");
        gestionAff.getPanneauPrincipal().add(connectionColoc, CONNECTIONCOLO);
//-------------------------

        // Menu
        JMenuBar barDeMenu = new JMenuBar();
        JMenu fichierMenu = new JMenu("Fichier");

        JMenuItem item = new JMenuItem("Retour", 'r');
        item.addActionListener( new ItemRetour(gestionAff));
        
        fichierMenu.add(item);

        item = new JMenuItem("Se déconnecter", 'q');
        item.addActionListener( new ItemDeconnection(gestionAff));

        fichierMenu.add(item);

        barDeMenu.add(fichierMenu);
        fenetre.setJMenuBar(barDeMenu);
        // Fin Menu

        fenetre.add(gestionAff.getPanneauPrincipal());
        fenetre.setVisible(true);
    }
}
