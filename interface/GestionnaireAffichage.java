import javax.swing.JPanel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class GestionnaireAffichage {

    private CardLayout cardLayout;
    private JPanel panneauPrincipal;
    private String clePremierePage;
    private BufferedReader in;
    private PrintWriter out;

    public GestionnaireAffichage(CardLayout cardLayout,  JPanel panneauPrincipal , BufferedReader in, PrintWriter out )
    {
        this.cardLayout =cardLayout;
        this.panneauPrincipal = panneauPrincipal;
        this.clePremierePage = "PageAccueil";
        this.in = in;
        this.out = out;
    }

    public CardLayout getJeuxDeCarte(){return this.cardLayout;}
    public JPanel getPanneauPrincipal(){return this.panneauPrincipal;}
    public String getClePremierePage(){return this.clePremierePage;}
    public BufferedReader getIn(){return this.in;}
    public PrintWriter getOut(){return this.out;}

    public void setJeuxDeCarte(CardLayout cardLayout){this.cardLayout = cardLayout;}
    public void setPanneauPrincipal(JPanel panneauPrincipal){this.panneauPrincipal = panneauPrincipal;}
    public void setClePremierePage( String clePremierePage ){this.clePremierePage = clePremierePage;}
    public void setIn(BufferedReader in){this.in=in;}
    public void setOut(PrintWriter out){this.out=out;}

    public void deplacement(String Cle){
        this.setClePremierePage(Cle);
        this.getJeuxDeCarte().show(this.getPanneauPrincipal(), Cle);
    }
}
