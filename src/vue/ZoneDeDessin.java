package vue;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.awt.BasicStroke;

import contrôleurs.EcouteurSouris;
import modèle.*;

public class ZoneDeDessin extends JPanel {
    private Dessin dessin;
    private BasicStroke pinceau;
    private Color color;
    public ZoneDeDessin(Dessin dessin) {
        this.dessin = dessin;
    }
    public void choisirEpaisseur(int epaisseur) {
        pinceau = new BasicStroke(epaisseur, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }

    public void dessineTrait(int x1, int y1, int x2, int y2) {
        Graphics g = getGraphics();
        g.setColor(color);
        ((Graphics2D)g).setStroke(pinceau);
        g.drawLine(x1, y1, x2, y2);
    }
    public void dessineAmpoule(int xc, int yc) {
    	Graphics g = getGraphics();
    	g.setColor(color);
    	((Graphics2D)g).setStroke(pinceau);
        g.drawLine(xc, yc+10, xc, yc-10);
        g.drawLine(xc+10, yc, xc-10, yc);
        g.drawLine(xc+5, yc+5, xc-5, yc-5);
        g.drawLine(xc-5, yc+5, xc+5, yc-5);
        
    }
    public void dessineFigure(Figure figure, Graphics g) { }
    
    public void dessineFigure(Trace trace, Graphics g) {
        choisirEpaisseur(trace.epaisseur());
        ((Graphics2D)g).setStroke(pinceau);
        Point pt = trace.element(0);
        int xd, yd, x, y;
        xd = (int)(pt.getX()); yd = (int)(pt.getY());
        for(int numPt = 1; numPt < trace.nbPoints(); numPt++) {
            pt = trace.element(numPt);
            x = (int)(pt.getX()); y = (int)(pt.getY());
            g.drawLine(xd, yd, x, y);
            xd = x; yd = y;
        }
    }
    public void dessineFigure(Etoile etoile, Graphics g) {
        choisirEpaisseur(etoile.epaisseur());
        ((Graphics2D)g).setStroke(pinceau);
        Point pt = etoile.centre();
        int xc, yc, x, y;
        xc = (int)(pt.getX()); yc = (int)(pt.getY());
        for(int numPt = 0; numPt < etoile.nbPoints(); numPt++) {
            pt = etoile.element(numPt);
            x = (int)(pt.getX()); y = (int)(pt.getY());
            g.drawLine(xc, yc, x, y);
        }
    }
    public void dessineFigure(Ampoule ampoule, Graphics g) {
        choisirEpaisseur(ampoule.epaisseur());
        ((Graphics2D)g).setStroke(pinceau);
        Point pt = ampoule.element(0);
        int xd, yd, x, y;
        xd = (int)(pt.getX()); yd = (int)(pt.getY());
        for(int numPt = 1; numPt < ampoule.nbPoints(); numPt++) {
            pt = ampoule.element(numPt);
            x = (int)(pt.getX()); y = (int)(pt.getY());
    	    if(Math.sqrt((xd-x)*(xd-x)+(yd-y)*(yd-y))>20){
    	        
    	    	g.drawLine(xd, yd+10, xd, yd-10);
    	        g.drawLine(xd+10, yd, xd-10, yd);
    	        g.drawLine(xd+5, yd+5, xd-5, yd-5);
    	        g.drawLine(xd-5, yd+5, xd+5, yd-5);
    	        
    		    xd=x;
    		    yd=y;
    	    }
        }
    }
    public void paint(Graphics g) {
        Figure f;
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(color);
        for(int numFig = 0; numFig < dessin.nbFigures(); numFig++) {
            f = dessin.element(numFig);
            g.setColor(f.color);
            if (f instanceof Trace) dessineFigure((Trace)f, g);
            if (f instanceof Etoile) dessineFigure((Etoile)f, g);
            if (f instanceof Ampoule) dessineFigure((Ampoule)f, g);
        }
    }
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
