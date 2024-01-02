import javax.swing.JPanel;
import javax.swing.JButton;

public class PageAccueil extends JPanel
{
    public PageAccueil(GestionnaireAffichage gestionAff )
    {
        JButton bouton1 = new JButton("Propriétaire");
        bouton1.addActionListener(new BoutonChangementPage(gestionAff, "PageConnectionProprio"));
        this.add(bouton1);
        JButton bouton2 = new JButton("Locataire");
        bouton2.addActionListener(new BoutonChangementPage(gestionAff, "PageConnectionColoc"));
        this.add(bouton2);
    }   
}

    
