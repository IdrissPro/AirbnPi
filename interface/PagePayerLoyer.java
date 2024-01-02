import javax.swing.JButton;
import javax.swing.JPanel;

public class PagePayerLoyer extends JPanel
{
    public PagePayerLoyer(GestionnaireAffichage gestionAff)
    {
        JButton bouton1 = new JButton("Il faut payer le loyer");
        //bouton1.addActionListener(new BoutonChangementPage(gestionAff, "PageAccueil")); // Changer l'action
        this.add(bouton1);
    }   
}
