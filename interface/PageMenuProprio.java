import javax.swing.JButton;
import javax.swing.JPanel;


public class PageMenuProprio extends JPanel
{
    public PageMenuProprio(GestionnaireAffichage gestionAff )
    {
        JButton bouton1 = new JButton("menu du jeune Entrepreneur");
        //bouton1.addActionListener(new BoutonChangementPage(gestionAff, "PageAccueil")); // Changer l'action
        this.add(bouton1);
    }   
}
