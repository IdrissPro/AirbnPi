import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PageConnection extends JPanel implements ActionListener
{

    private JTextField textFieldIdentifiant ;
    private JPasswordField passwordField ;
    private GestionnaireAffichage gestionAff;
    private String statut;

    private static final String MENUPROPRIO     = "PageMenuProprio";
    private static final String MENUCOLOC       = "PageMenuColoc";
    private static final String MENULOYER       = "PageLoyer";
    private static final String HISTORIQUECOLOC = "PageHistoriqueLoyerColoc";
    private static final String PAYERLOYER      = "PagePayerLoyer";
    private static final String BAR             = "PageBar";
    private static final String CHEMINBAR       = "CheminBar";
    private static final String TACHE           = "PageTache";

    public PageConnection( GestionnaireAffichage gestionAff , String statut )
    {
        // ----------------
        this.gestionAff = gestionAff;
        this.statut = statut;
        // ----------------

        this.setLayout(new BorderLayout());

        JLabel panneauMessage = new JLabel(statut);
        this.add(panneauMessage, BorderLayout.NORTH);

        JPanel panneauConnection = new JPanel();
        panneauConnection.setLayout( new GridBagLayout() );

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel labelIdentifiant = new JLabel("Identifiant:");
        textFieldIdentifiant = new JTextField(15);

        JLabel labelMotDePasse = new JLabel("Mot de Passe:");
        passwordField = new JPasswordField(15);

        JButton boutonConnexion = new JButton("Se Connecter");
        boutonConnexion.addActionListener(this);

        // Configurer GridBagConstraints pour les composants
        gbc.insets = new Insets(5, 5, 5, 5); // Espacement entre les composants
        gbc.gridx = 0;  gbc.gridy = 0;
        panneauConnection.add(labelIdentifiant, gbc);

        gbc.gridx = 1;
        panneauConnection.add(textFieldIdentifiant, gbc);

        gbc.gridx = 0;  gbc.gridy = 1;
        panneauConnection.add(labelMotDePasse, gbc);

        gbc.gridx = 1;
        panneauConnection.add(passwordField, gbc);

        gbc.gridx = 0;  gbc.gridy = 2;
        gbc.gridwidth = 2; // Le bouton s'étend sur deux colonnes
        panneauConnection.add(boutonConnexion, gbc);

        this.add(panneauConnection, BorderLayout.CENTER); 
    }

    public void actionPerformed(ActionEvent e) 
    {
        String identifiant = textFieldIdentifiant.getText();
        String motDePasse = new String(passwordField.getPassword());
        boolean estProprio = false;

        String messageErreurConnection = "Erreur de connexion, vérifiez vos identifiants et que vous êtes sur la page correspondant à votre statut";

        if (gestionAff.getClePremierePage()=="PageConnectionProprio"){estProprio = true;}        
        // CLIENT / SERVEUR ICI
        try
        {
            if (estProprio) 
            {
            
                gestionAff.getOut().println("P/"+identifiant + "/" + motDePasse); // On envoie les identifiants au Serveur pour Pripriétaire
                String reponse = gestionAff.getIn().readLine();
                if ("covalide" == reponse )
                {
                    //-------------------------
                    // Charger ici toutes les pages du proprio
                    gestionAff.getPanneauPrincipal().add(new PageMenuProprio(gestionAff), MENUPROPRIO);
                    //-------------------------
                    gestionAff.deplacement("PageMenuProprio");
                }
                else {statut = messageErreurConnection;}
            
            }
            else
            {
                gestionAff.getOut().println("C/"+identifiant + "/" + motDePasse); // On envoie les identifiants au Serveur pour Coloc
                String reponse = gestionAff.getIn().readLine();
                if ("covalide" == reponse )
                {
                    //-------------------------
                    // Charger ici toutes les pages du coloc
                    gestionAff.getPanneauPrincipal().add(new PageMenuColoc(gestionAff), MENUCOLOC);

                    gestionAff.getPanneauPrincipal().add(new PageLoyer(gestionAff), MENULOYER);
                        gestionAff.getPanneauPrincipal().add(new PageHistoriqueLoyerColoc(gestionAff), HISTORIQUECOLOC);
                        gestionAff.getPanneauPrincipal().add(new PagePayerLoyer(gestionAff), PAYERLOYER);

                    gestionAff.getPanneauPrincipal().add(new PageBar(gestionAff), BAR);
                        gestionAff.getPanneauPrincipal().add(new PageCheminBar(gestionAff), CHEMINBAR);
                    gestionAff.getPanneauPrincipal().add(new PageTache(gestionAff), TACHE);
                    //-------------------------
                    gestionAff.deplacement("PageMenuColoc");
                }
                else{statut = messageErreurConnection;}
            }
        }catch (IOException a) {
                System.err.println("Impossible de créer les flux");
                a.printStackTrace();}        
    }
}

