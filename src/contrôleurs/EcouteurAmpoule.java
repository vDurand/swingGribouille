package contrôleurs;

import modèle.Dessin;
import modèle.Etoile;
import modèle.Ampoule;
import vue.FenetreDeDessin;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.Math;

/**
 * @author Valentin Durand & Pierre Friboulet - TP1.1 - 1A - DUT Informatique - IUT Ifs
 * @Class Ampoule
 * @ May 15, 2014 9:40:23 AM
 */

public class EcouteurAmpoule
	extends EcouteurSouris {
	
	  private int xg, yg;
	  private Ampoule ampoule;
	  public EcouteurAmpoule(Dessin dessin, FenetreDeDessin applet) {
	    super(dessin, applet);
	  }
	  /* en tant que MouseListener */
	  public void mousePressed(MouseEvent e) {
	    xg = e.getX();
	    yg = e.getY();
	    ampoule = new Ampoule(vue.epaisseur(),vue.couleur());
	    modèle.ajoute(ampoule);
	    ampoule.ajoute(xg, yg);
	    
	  }
	  /* en tant que MouseMotionListener */
	  public void mouseDragged(MouseEvent e) {
	    int x, y;
	    x = e.getX();
	    y = e.getY();
	    ampoule.ajoute(x, y);
	    if(Math.sqrt((xg-x)*(xg-x)+(yg-y)*(yg-y))>20){
	    	vue.dessineAmpoule(xg, yg);
		    xg=x;
		    yg=y;
	    }
	  }
}
