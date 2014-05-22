package modèle;

import java.awt.Color;
import java.awt.Point;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Etoile extends Figure {
    private Point centre;
    public Etoile() {
        super(1,Color.BLACK);
    }
    public Etoile(int epaisseur, int x, int y,Color color ){
        super(epaisseur,color);
        centre = new Point(x, y);
    }
    public Point centre() {
        return centre;
    }
    public void enregistreDans(DataOutputStream fichier) throws Exception {
        fichier.writeByte(2);
        fichier.writeInt(epaisseur);
        fichier.writeInt((int)(centre.getX()));
        fichier.writeInt((int)(centre.getY()));
        fichier.writeInt(lesPoints.size());
        for(Point pt:lesPoints) {
            fichier.writeInt((int)(pt.getX()));
            fichier.writeInt((int)(pt.getY()));
        }
        fichier.writeInt(color.getRGB());
    }
    public void chargeDepuis(DataInputStream fichier) throws Exception {
        epaisseur = fichier.readInt();
        int x = fichier.readInt();
        int y = fichier.readInt();
        centre = new Point(x, y);
        int nbPts = fichier.readInt();
        for(int i=0; i<nbPts; i++) {
            x = fichier.readInt();
            y = fichier.readInt();
            ajoute(x, y);
        }
        color=new Color(fichier.readInt());
    }
}
