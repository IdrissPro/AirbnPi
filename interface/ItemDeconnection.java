import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemDeconnection implements ActionListener {

    private GestionnaireAffichage gestionAff ;

    private static String P1 = "PageConnectionProprio";
    private static String P2 = "PageConnectionColoc";

    public ItemDeconnection(GestionnaireAffichage gestionAff )
    {
        this.gestionAff = gestionAff;
    }

    public void actionPerformed(ActionEvent e) 
    {
        switch (gestionAff.getClePremierePage()) {

            case "PageMenuProprio":
            gestionAff.deplacement(P1);break;
            
                    case "PageMenuColoc":
                    gestionAff.deplacement(P2);break;

            case "PageLoyer":
            gestionAff.deplacement(P2);break;

                    case "PagePayerLoyer":
                    gestionAff.deplacement(P2);break;

                    case "PageHistoriqueLoyerColoc":
                    gestionAff.deplacement(P2);break;

            case "PageTache":
            gestionAff.deplacement(P2);break;

            case "PageBar":
            gestionAff.deplacement(P2);break;

            default:
            gestionAff.deplacement("PageAccueil");
        }
        
    }
}
