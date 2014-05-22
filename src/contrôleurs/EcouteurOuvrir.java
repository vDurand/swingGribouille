package contrôleurs;

import java.awt.event.ActionEvent;
import vue.FenetreDeDessin;
import modèle.Dessin;

public class EcouteurOuvrir extends EcouteurOpérationRisquée {
    public EcouteurOuvrir(FenetreDeDessin fenetre, Dessin dessin) {
        super(fenetre, dessin, "Ouverture d'un dessin");
    }
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (abandon) return;
        fenetre.ouvrir();
    }
}
