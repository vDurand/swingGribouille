package modèle;

import java.awt.Color;
import java.awt.Point;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Trace extends Figure {
    public Trace() {
        super(1, Color.BLACK);
    }
    public Trace(int epaisseur, int xDebut, int yDebut,Color color) {
        super(epaisseur,color);
        ajoute(xDebut, yDebut);
    }
    public void enregistreDans(DataOutputStream fichier) throws Exception {
        fichier.writeByte(1);
        fichier.writeInt(epaisseur);
        fichier.writeInt(lesPoints.size());
        for(Point pt:lesPoints) {
            fichier.writeInt((int)(pt.getX()));
            fichier.writeInt((int)(pt.getY()));
        }
        fichier.writeInt(color.getRGB());
    }
    public void chargeDepuis(DataInputStream fichier) throws Exception {
        epaisseur = fichier.readInt();
        int nbPts = fichier.readInt();
        for(int i=0; i<nbPts; i++) {
            int x = fichier.readInt();
            int y = fichier.readInt();
            ajoute(x, y);
        }
        color=new Color(fichier.readInt());
    }
}
