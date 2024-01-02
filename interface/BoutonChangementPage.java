import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BoutonChangementPage implements ActionListener 
{
    private GestionnaireAffichage gestionAff;
    private String CLE;

    public BoutonChangementPage(GestionnaireAffichage gestionAff, String CLE) 
    {
        this.gestionAff = gestionAff;
        this.CLE = CLE;
    }

    public void actionPerformed(ActionEvent e) 
    {
        gestionAff.deplacement(CLE);
    }
}
