import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemRetour implements ActionListener 
{
    private GestionnaireAffichage gestionAff ;

    public ItemRetour(GestionnaireAffichage gestionAff )
    {
        this.gestionAff = gestionAff;
    }

    // Si temps libre, Définir un dictionnaire modifiable à chaque création de page
    //                 Définir un panneau latéral pour voir toutes les pages
    public void actionPerformed(ActionEvent e) 
    {
        switch (gestionAff.getClePremierePage()) {
            
            case "PageConnectionColoc":
            gestionAff.deplacement("PageAccueil");break;
            // ->
                    case "PageMenuColoc":
                    gestionAff.deplacement("PageConnectionColoc");break;
                    // ->
                            case "PageLoyer":
                            gestionAff.deplacement("PageMenuColoc");break;
                            // ->
                                    case "PagePayerLoyer":
                                    gestionAff.deplacement("PageLoyer");break;

                                    case "PageHistoriqueLoyerColoc":
                                    gestionAff.deplacement("PageLoyer");break;

                            case "PageTache":
                            gestionAff.deplacement("PageMenuColoc");break;

                            case "PageBar":
                            gestionAff.deplacement("PageMenuColoc");break;

            case "PageConnectionProprio":
            gestionAff.deplacement("PageAccueil");break;
            // ->
                    case "PageMenuProprio":
                    gestionAff.deplacement("PageConnectionProprio");break;
            
            default:
            System.out.println("Page Précédente non définie");
        }
    }

}
