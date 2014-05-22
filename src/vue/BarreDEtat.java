package vue;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridLayout;

public class BarreDEtat extends JPanel {
    private JLabel position, outil, couleur, epaisseur;
    public BarreDEtat() {
        setLayout(new GridLayout(1, 4));
        add( position = new JLabel());
        add(    outil = new JLabel());
        add(  couleur = new JLabel());
        add(epaisseur = new JLabel());
    }
    public void afficheCoordonnées(int x, int y) {
        position.setText("( " + x + ", " + y + " )");
    }
    public void afficheOutil(String nomOutil) {
        outil.setText("Outil : " + nomOutil);
    }
    public void afficheCouleur(String color) {
        couleur.setText("Couleur : " + color);
    }
    public void afficheEpaisseur(int epais) {
        epaisseur.setText("Epaisseur : " + epais);
    }
}
