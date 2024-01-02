import javax.swing.JButton;
import javax.swing.JPanel;

public class PageMenuColoc extends JPanel
{
    public PageMenuColoc(GestionnaireAffichage gestionAff)
    {
        JButton bouton1 = new JButton("Loyer");
        bouton1.addActionListener(new BoutonChangementPage(gestionAff, "PageLoyer")); 
        this.add(bouton1);

        JButton bouton2 = new JButton("Taches Ménagères");
        bouton2.addActionListener(new BoutonChangementPage(gestionAff, "PageTache")); 
        this.add(bouton2);

        JButton bouton3 = new JButton("Bars de Rouen");
        bouton3.addActionListener(new BoutonChangementPage(gestionAff, "PageBar")); 
        this.add(bouton3);
    }   
}
