import javax.swing.JButton;
import javax.swing.JPanel;

public class PageCheminBar extends JPanel 
{
    public PageCheminBar(GestionnaireAffichage gestionAff)
    {
        JButton bouton1 = new JButton("Indiquez à quel bar vous voulez vous rendre");
        //bouton1.addActionListener(new BoutonChangementPage(gestionAff, "PageAccueil")); // Changer l'action
        this.add(bouton1);
    }  
}
