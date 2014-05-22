package modèle;
import java.awt.Color;
import java.awt.Point;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
/**
 * @author Valentin Durand & Pierre Friboulet - TP1.1 - 1A - DUT Informatique - IUT Ifs
 * @Class Ampoule
 * @ May 15, 2014 9:37:23 AM
 */
public class Ampoule extends Figure {

    public Ampoule() {
        super(1,Color.BLACK);
    }
    public Ampoule(int epaisseur,Color color) {
        super(epaisseur,color);
   
    }
    public void enregistreDans(DataOutputStream fichier) throws Exception {
        fichier.writeByte(3);
        fichier.writeInt(epaisseur);
        fichier.writeInt(lesPoints.size());
        for(Point pt:lesPoints) {
            fichier.writeInt((int)(pt.getX()));
            fichier.writeInt((int)(pt.getY()));
        }
        fichier.writeInt(color.getRGB());
    }
    public void chargeDepuis(DataInputStream fichier) throws Exception {
        /*epaisseur = fichier.readInt();
        int x = fichier.readInt();
        int y = fichier.readInt();
        int nbPts = fichier.readInt();
        for(int i=0; i<nbPts; i++) {
            x = fichier.readInt();
            y = fichier.readInt();
            ajoute(x, y);
        }
        color=new Color(fichier.readInt());*/
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
