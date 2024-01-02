import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class PageBar extends JPanel
{
    public PageBar(GestionnaireAffichage gestionAff)
    {
        this.setLayout(new GridBagLayout());


        // IMAGE Largeur 1746 Longueur 850 round(950*850/1746)=462
        ImageIcon imageIcon = new ImageIcon("./PlanBar.png"); 
        // Redimensionner l'image pour s'adapter à la fenêtre
        Image image = imageIcon.getImage();
        Image imageRedimensionnee = image.getScaledInstance(950, 462, Image.SCALE_SMOOTH); 
        ImageIcon imageRedimensionneeIcon = new ImageIcon(imageRedimensionnee);
        JLabel imageLabel = new JLabel(imageRedimensionneeIcon);

        GridBagConstraints gbcImage = new GridBagConstraints();
        gbcImage.gridx = 0;
        gbcImage.gridy = 0;
        gbcImage.insets = new Insets(10, 10, 10, 10); // Marge autour de l'image
        this.add(imageLabel, gbcImage);

        // Bouton centré en dessous de l'image
        JButton bouton1 = new JButton("Mais où s'arracher ?");
        //bouton1.addActionListener(new BoutonChangementPage(gestionAff, "PageAccueil")); // Changer l'action
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 1;
        gbcButton.insets = new Insets(10, 10, 10, 10); // Marge autour du bouton
        this.add(bouton1, gbcButton);
    }
}   
