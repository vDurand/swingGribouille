package contrôleurs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import vue.FenetreDeDessin;
import modèle.Dessin;

public class EcouteurOpérationRisquée
implements ActionListener, //pour les options de menu déclenchant des opérations qui font perdre les données
           WindowListener  //pour la gestion de la case de fermeture de la fenêtre de l'application
{
    protected FenetreDeDessin fenetre;
    protected Dessin dessin;
    protected boolean abandon;
    protected String nomOpération;
    public EcouteurOpérationRisquée(FenetreDeDessin fenetre,
                                    Dessin dessin,
                                    String nomOpération) {
        this.fenetre = fenetre;
        this.dessin = dessin;
        this.nomOpération = nomOpération;
    }
// en tant que ActionListener
    public void actionPerformed(ActionEvent e) { //remarque : on ne sert pas de l'objet e
        abandon = false;
        if (dessin.modifié()) {
            if (fenetre.abandonAprèsEnregistrementEventuel(nomOpération))
                abandon = true;
        }
    }
// en tant que WindowListener
    public void windowActivated(WindowEvent e) { }
    public void windowDeactivated(WindowEvent e) { }
    public void windowClosing(WindowEvent e) { }
    public void windowClosed(WindowEvent e) { }
    public void windowIconified(WindowEvent e) { }
    public void windowDeiconified(WindowEvent e) { }
    public void windowOpened(WindowEvent e) { }
}
