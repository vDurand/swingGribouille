package contrôleurs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modèle.Dessin;
import vue.FenetreDeDessin;
import vue.ZoneDeDessin;

public class ControleurPrincipal
implements KeyListener {
	private FenetreDeDessin fenetre;
	private ZoneDeDessin panneau;
	private EcouteurCrayon crayon;
	private EcouteurEtoile etoile;
	private EcouteurAmpoule ampoule;
	private EcouteurSouris courant;
	public ControleurPrincipal(Dessin dessin, FenetreDeDessin fenetre, ZoneDeDessin panneau) {
		this.fenetre = fenetre;
		this.panneau = panneau;
		crayon = new EcouteurCrayon(dessin, fenetre);
		etoile = new EcouteurEtoile(dessin, fenetre);
		ampoule = new EcouteurAmpoule(dessin, fenetre);
		courant = null;
		changerPourOutilCrayon();
	}
	private void changerOutil(EcouteurSouris outil) {
		panneau.removeMouseListener(courant);
		panneau.removeMouseMotionListener(courant);
		panneau.addMouseListener(outil);
		panneau.addMouseMotionListener(outil);
		courant = outil;
	}
	public void changerPourOutilCrayon() {
	    changerOutil(crayon);
	    fenetre.afficheOutilCrayon();
	}
	public void changerPourOutilEtoile() {
	    changerOutil(etoile);
	    fenetre.afficheOutilEtoile();
	}
	public void changerPourOutilAmpoule() {
	    changerOutil(ampoule);
	    fenetre.afficheOutilAmpoule();
	}
	/* en tant que KeyListener */
	public void keyPressed(KeyEvent e) { }
	public void keyReleased(KeyEvent e) { }
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) {
    		case 'e': changerPourOutilEtoile(); break;
    		case 'c': changerPourOutilCrayon(); break;
		}
	}
}
