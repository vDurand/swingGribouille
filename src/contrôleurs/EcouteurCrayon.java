package contrôleurs;

/**
 * La classe EcouteurCrayon implante les méthodes correspondant
 * aux événements souris et déplacement de la souris
 * pour dessiner des segments les uns à la suite des autres.
 */

import modèle.Dessin;
import modèle.Trace;
import vue.FenetreDeDessin;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EcouteurCrayon extends EcouteurSouris {
  private int xd, yd;
  private Trace trace;
  public EcouteurCrayon(Dessin dessin, FenetreDeDessin fenetre) {
     super(dessin, fenetre);
  }
  /* en tant que MouseListener */
  public void mousePressed(MouseEvent e) {
    xd = e.getX();
    yd = e.getY();
    vue.afficheCoordonnées(xd, yd);
    trace = new Trace(vue.epaisseur(), xd, yd,vue.couleur());
    modèle.ajoute(trace);
  }
  /* en tant que MouseMotionListener */
  public void mouseDragged(MouseEvent e) {
    int x, y;
    x = e.getX(); y = e.getY();
    vue.afficheCoordonnées(x, y);
    trace.ajoute(x, y);
    vue.dessineTrait(xd, yd, x, y);

    xd = x; yd = y;
  }
}
