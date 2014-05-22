package vue; 

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Cursor;

import contrôleurs.ControleurPrincipal;
import contrôleurs.EcouteurEffacer;
import contrôleurs.EcouteurOuvrir;
import contrôleurs.EcouteurDeFin;
import modèle.Dessin;

public class FenetreDeDessin
extends JFrame {
    private ZoneDeDessin zoneDeDessin;
    private Dessin dessin;
    private BarreDEtat barreDEtat;
    private ControleurPrincipal controleur;
    private JMenuItem itemEnreg;
    private JRadioButtonMenuItem itemCrayon, itemEtoile, itemAmpoule;
    private int epaisseur;
    public Color color;
    private void constructionBarreDeMenus() {
        JMenuBar barreDeMenus = new JMenuBar();
        JMenu menu;
        JMenuItem item;
        barreDeMenus.add(menu = new JMenu("Gribouille"));
        item = new JMenuItem("A propos");
        item.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    APropos.affiche();
                }
            }
        );
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Quitter");
        item.addActionListener(new EcouteurDeFin(this, dessin));
        menu.add(item);

        barreDeMenus.add(menu = new JMenu("Dessin"));
        item = new JMenuItem("Ouvrir...");
        item.addActionListener(new EcouteurOuvrir(this, dessin));
        menu.add(item);
        itemEnreg = new JMenuItem("Enregistrer");
        itemEnreg.setEnabled(false);
        itemEnreg.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    enregistrer();
                }
            }
        );
        menu.add(itemEnreg);
        item = new JMenuItem("Enregistrer sous...");
        item.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    enregistrerSous();
                }
            }
        );
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Effacer");
        item.addActionListener(new EcouteurEffacer(this, dessin));
        menu.add(item);

        barreDeMenus.add(menu = new JMenu("Outil"));
        ButtonGroup groupe = new ButtonGroup();
        item = itemCrayon = new JRadioButtonMenuItem("Crayon", true);
        item.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controleur.changerPourOutilCrayon();
                    setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        );
        menu.add(item);
        groupe.add(item);
        item = itemEtoile = new JRadioButtonMenuItem("Etoile");
        item.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controleur.changerPourOutilEtoile();
                    setCursor (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }
        );
        menu.add(item);
        groupe.add(item);
        item = itemAmpoule = new JRadioButtonMenuItem("Ampoule");
        item.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controleur.changerPourOutilAmpoule();
                    setCursor (Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                }
            }
        );
        menu.add(item);
        groupe.add(item);

        barreDeMenus.add(menu = new JMenu("Pinceau"));
        item = new JMenuItem("Epaisseur...");
        item.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (DlgEpaisseur.réponseValidée()) {
                        choisirEpaisseur(DlgEpaisseur.epaisseurChoisie());
                        
                    };
                }
            }
        );
        menu.add(item);
        item = new JMenuItem("Couleur");
        item.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                        color = JColorChooser.showDialog(null,"couleur",Color.BLACK);
                        zoneDeDessin.setColor(color);
                }
            }
        );

        menu.add(item);

        setJMenuBar(barreDeMenus);
    }
    public FenetreDeDessin() {
        dessin = new Dessin();
        barreDEtat = new BarreDEtat();
        zoneDeDessin = new ZoneDeDessin(dessin);
        color=Color.BLACK;
        afficheCouleur("Noir");
 
        setTitle("Gribouille");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        constructionBarreDeMenus();

        setLayout(new BorderLayout());
        add(zoneDeDessin, BorderLayout.CENTER);
        add(barreDEtat, BorderLayout.SOUTH);

        addWindowListener(new EcouteurDeFin(this, dessin));
        controleur = new ControleurPrincipal(dessin, this, zoneDeDessin);
        addKeyListener(controleur);

        choisirEpaisseur(1);
        setVisible(true);
    }
    public void dessineTrait(int x1, int y1, int x2, int y2) {
        zoneDeDessin.dessineTrait(x1, y1, x2, y2);
    }
    public void dessineAmpoule(int x1, int y1) {
        zoneDeDessin.dessineAmpoule(x1, y1);
    }
    public void afficheCoordonnées(int x, int y) {
        barreDEtat.afficheCoordonnées(x, y);
    }
    private void afficheOutil(JRadioButtonMenuItem item) {
        item.setSelected(true);
        barreDEtat.afficheOutil(item.getText());
    }
    public void afficheOutilCrayon() {
        afficheOutil(itemCrayon);
    }
    public void afficheOutilEtoile() {
        afficheOutil(itemEtoile);
    }
    public void afficheOutilAmpoule() {
        afficheOutil(itemAmpoule);
    }
    public void afficheCouleur(String color) {
        barreDEtat.afficheCouleur(color);
    }
    public void activeOptionEnregistrer(boolean actif) {
        itemEnreg.setEnabled(actif);
    }
    public void choisirEpaisseur(int epaisseur) {
        zoneDeDessin.choisirEpaisseur(this.epaisseur = epaisseur);
        barreDEtat.afficheEpaisseur(epaisseur);
    }
    
    public int epaisseur() {
        return epaisseur;
    }
    public Color couleur() {
        return color;
    }
    public boolean abandonAprèsEnregistrementEventuel(String titre) {
        switch(JOptionPane.showConfirmDialog(this, "L'opération fait perdre les modifications non enregistrées !\nVoulez-voulez-vous enregistrer avant ?", titre, JOptionPane.YES_NO_CANCEL_OPTION)) {
            case JOptionPane.YES_OPTION    : return !enregistrerSous();
            case JOptionPane.NO_OPTION     : break;
            default                        : return true;
        }
        return false;
    }
    public boolean enregistrerSous() {
        JFileChooser dlgFichier = new JFileChooser();
        switch (dlgFichier.showSaveDialog(this)) {
            case JFileChooser.CANCEL_OPTION  :
                return false;
            case JFileChooser.APPROVE_OPTION :
                if (dessin.enregistreSous(dlgFichier.getSelectedFile())) {
                    activeOptionEnregistrer(true);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this,
                                                  "Un problème est survenu pendant l'enregistrement du dessin.\nLe dessin n'a pas été enregistré.",
                                                  "Dessin non enregistré",
                                                  JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            case JFileChooser.ERROR_OPTION   :
                JOptionPane.showMessageDialog(this,
                                              "Un problème est survenu lors du choix de fichier pour l'enregistrement du dessin.\nLe dessin n'a pas été enregistré.",
                                              "Dessin non enregistré",
                                              JOptionPane.ERROR_MESSAGE);
                return false;
            default :
                return false;
        }
    }
    public void enregistrer() {
        if (!dessin.enregistre()) {
            JOptionPane.showMessageDialog(this,
                                          "Un problème est survenu pendant l'enregistrement du dessin.\nLe dessin n'a pas été enregistré.",
                                          "Dessin non enregistré",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
    public void ouvrir() {
        JFileChooser dlgFichier = new JFileChooser();
        switch (dlgFichier.showOpenDialog(this)) {
            case JFileChooser.CANCEL_OPTION  :
                break;
            case JFileChooser.APPROVE_OPTION :
                if (dessin.charge(dlgFichier.getSelectedFile())) {
                    repaint();
                    activeOptionEnregistrer(true);
                } else
                    JOptionPane.showMessageDialog(this,
                                                  "Un problème est survenu pendant la lecture du dessin.\nLe dessin n'a pas été ouvert.",
                                                  "Dessin non ouvert",
                                                  JOptionPane.ERROR_MESSAGE);
                break;
            case JFileChooser.ERROR_OPTION   :
                JOptionPane.showMessageDialog(this,
                                              "Un problème est survenu lors du choix de fichier pour l'ouverture d'un dessin.\nLe dessin n'a pas été ouvert.",
                                              "Dessin non ouvert",
                                              JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}
