package contrôleurs;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import vue.FenetreDeDessin;
import modèle.Dessin;

public class EcouteurDeFin
extends EcouteurOpérationRisquée
{
    public EcouteurDeFin(FenetreDeDessin fenetre, Dessin dessin) {
        super(fenetre, dessin, "Fermerture de l'application Gribouille");
    }
    protected void quitter() {
        super.actionPerformed(null); //on ne se sert pas de l'objet évènement dans la classe de base
        if (!abandon) {
            System.exit(0);
        }
    }
    public void actionPerformed(ActionEvent e) {
        quitter();
    }
    public void windowClosing(WindowEvent e) {
        quitter();
    }
}
