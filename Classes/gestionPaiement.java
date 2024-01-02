
import java.net.Socket;

public interface gestionPaiement {
    //Locataire et propriétaire utilise cette méthode de différentes façon
    void voirHistoriqueLoyer(Socket socket);
}
