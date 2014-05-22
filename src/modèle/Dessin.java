package modèle;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Dessin {
    private List<Figure> contenu;
    private boolean modifié;
    private File fichierChoisi;
    public Dessin() {
        fichierChoisi = null;
        contenu = new ArrayList<Figure>();
        modifié = false;
    }
    public void ajoute(Figure figure) {
        contenu.add(figure);
        modifié = true;
    }
    public int nbFigures() {
        return contenu.size();
    }
    public Figure element(int numero) {
        return contenu.get(numero);
    }
    public void vider() {
        contenu.clear();
        modifié = false;
    }
    public boolean modifié() {
        return modifié;
    }
    public boolean enregistre() {
        return enregistreSous(fichierChoisi);
    }
    public boolean enregistreSous(File f) {
    // renvoie vrai en cas de succès, faux en cas d'échec
        fichierChoisi = f;
        try {
            DataOutputStream fichier;
            fichier = new DataOutputStream(new FileOutputStream(f));
            enregistreDans(fichier);
            fichier.close();
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    public boolean charge(File f) {
    // renvoie vrai en cas de succès, faux en cas d'échec
        try {
            DataInputStream fichier;
            fichier = new DataInputStream(new FileInputStream(f));
            vider();
            chargeDepuis(fichier);
            fichier.close();
            fichierChoisi = f;
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    private void enregistreDans(DataOutputStream fichier) throws Exception {
        fichier.writeInt(contenu.size());
        for(Figure fig:contenu) {
            fig.enregistreDans(fichier);
        }
        modifié = false;
    }
    private void chargeDepuis(DataInputStream fichier) throws Exception {
        int nbFig = fichier.readInt();
        for(int i=0; i<nbFig; i++) {
            byte type = fichier.readByte();
            Figure fig;
            switch(type) {
                case  1: fig = new Trace(); break;
                case  2: fig = new Etoile(); break;
                case  3: fig = new Ampoule(); break;
                default: fig = null;
            }
            fig.chargeDepuis(fichier);
            contenu.add(fig);
        }
        modifié = false;
    }
}
