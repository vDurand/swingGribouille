package vue;

import java.awt.Frame;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class DlgEpaisseur extends JDialog {
    private static DlgEpaisseur uniqueInstance = null;
    private JSlider jgeValeur;
    private JButton btValider, btAnnuler;
    private boolean validé;
    private DlgEpaisseur() {
        super((Frame)null, "Epaisseur de pinceau", true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                actionBtAnnuler();
            }
        });
        setSize(300,150); setResizable(false);
        setLayout(new GridLayout(2,1)); //2 lignes de 1 colonne
        JPanel zoneDeSaisie = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        zoneDeSaisie.add(new JLabel("Valeur :"));
        jgeValeur = new JSlider(1,9,1);
        jgeValeur.setMinorTickSpacing(1);
        jgeValeur.setMajorTickSpacing(1);
        jgeValeur.setPaintTicks(true);
        jgeValeur.setPaintLabels(true);
        jgeValeur.setFont(new Font("Serif", Font.PLAIN, 12));
        zoneDeSaisie.add(jgeValeur);
        add(zoneDeSaisie);
        btValider = new JButton("Valider");
        btValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { actionBtValider(); }
        });
        btAnnuler = new JButton("Annuler");
        btAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { actionBtAnnuler(); }
        });
        JPanel bandeDeBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        bandeDeBoutons.add(btValider);
        bandeDeBoutons.add(btAnnuler);
        add(bandeDeBoutons);
    }
    private void actionBtValider() {
        validé = true;
        setVisible(false);
    }
    private void actionBtAnnuler() {
        validé = false;
        setVisible(false);
    }
    public static boolean réponseValidée() { //FenetreDeDessin uneVue) {
        if (uniqueInstance == null) uniqueInstance = new DlgEpaisseur();
        uniqueInstance.setLocationRelativeTo(null);
        uniqueInstance.jgeValeur.requestFocus();
        uniqueInstance.setVisible(true);
        return uniqueInstance.validé;
    }
    public static int epaisseurChoisie() {
        return uniqueInstance.jgeValeur.getValue();
    }
}
