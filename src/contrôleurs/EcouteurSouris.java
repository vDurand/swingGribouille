package contrôleurs;

import modèle.Dessin;
import vue.FenetreDeDessin;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class EcouteurSouris
implements MouseListener, MouseMotionListener {
  protected Dessin modèle;
  protected FenetreDeDessin vue;
  public EcouteurSouris(Dessin dessin, FenetreDeDessin fenetre) {
    modèle = dessin;
    vue = fenetre;
  }
  /* en tant que MouseListener */
  public void mouseEntered(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  /* en tant que MouseMotionListener */
  public void mouseMoved(MouseEvent e) {
    vue.afficheCoordonnées(e.getX(), e.getY());
  }
}
