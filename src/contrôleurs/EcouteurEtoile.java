package contrôleurs;

/**
 * La classe EcouteurEtoile implante les méthodes correspondant
 * aux événements souris et déplacement de la souris
 * pour dessiner des segments ayant tous une extrémité en commun.
 */

import modèle.Dessin;
import modèle.Etoile;
import vue.FenetreDeDessin;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EcouteurEtoile
extends EcouteurSouris {
  private int xc, yc;
  private Etoile etoile;
  public EcouteurEtoile(Dessin dessin, FenetreDeDessin applet) {
    super(dessin, applet);
  }
  /* en tant que MouseListener */
  public void mousePressed(MouseEvent e) {
    xc = e.getX();
    yc = e.getY();
    etoile = new Etoile(vue.epaisseur(), xc, yc,vue.couleur());
    modèle.ajoute(etoile);
  }
  /* en tant que MouseMotionListener */
  public void mouseDragged(MouseEvent e) {
    int x, y;
    x = e.getX();
    y = e.getY();
    etoile.ajoute(x, y);
    vue.dessineTrait(xc, yc, x, y);
  }
}
