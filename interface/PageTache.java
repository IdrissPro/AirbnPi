import javax.swing.JButton;
import javax.swing.JPanel;

public class PageTache extends JPanel
{
    public PageTache(GestionnaireAffichage gestionAff)
    {
        JButton bouton1 = new JButton("Passe le balais ou danse le ballet");
        //bouton1.addActionListener(new BoutonChangementPage(gestionAff, "PageAccueil")); // Changer l'action
        this.add(bouton1);
    }   
}