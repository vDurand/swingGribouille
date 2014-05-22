package modèle;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public abstract class Figure {
    protected List<Point> lesPoints;
    protected int epaisseur;
    public Color color;
    public Figure(int epaisseur,Color color) {
        this.epaisseur = epaisseur;
        this.color=color;
        lesPoints = new ArrayList<Point>();
    }
 
	public int epaisseur() {
        return epaisseur;
    }
    public void ajoute(int x, int y) {
        lesPoints.add(new Point(x, y));
    }
    public int nbPoints() {
        return lesPoints.size();
    }
    public Point element(int numero) {
        return lesPoints.get(numero);
    }
    public abstract void enregistreDans(DataOutputStream fichier) throws Exception;
    public abstract void chargeDepuis(DataInputStream fichier) throws Exception;
}
