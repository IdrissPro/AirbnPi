import javax.swing.JButton;
import javax.swing.JPanel;

public class PageLoyer extends JPanel
{
    public PageLoyer(GestionnaireAffichage gestionAff)
    {
        JButton bouton1 = new JButton("Payer le loyer");
        bouton1.addActionListener(new BoutonChangementPage(gestionAff, "PagePayerLoyer")); 
        this.add(bouton1);

        JButton bouton2 = new JButton("Historique Des Paiements");
        bouton2.addActionListener(new BoutonChangementPage(gestionAff, "PageHistoriqueLoyerColoc")); 
        this.add(bouton2);
    }   
}
