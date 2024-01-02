import javax.swing.JButton;
import javax.swing.JPanel;

public class PageHistoriqueLoyerColoc extends JPanel
{
    public PageHistoriqueLoyerColoc(GestionnaireAffichage gestionAff)
    {
        JButton bouton1 = new JButton("Regarde l'historique des paiements");
        //bouton1.addActionListener(new BoutonChangementPage(gestionAff, "PageAccueil")); // Changer l'action
        this.add(bouton1);
    }   
}