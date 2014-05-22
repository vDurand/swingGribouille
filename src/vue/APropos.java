package vue;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class APropos extends JDialog {
    private static APropos uniqueInstance = null;
    private APropos() {
        super((Frame)null, "A propos de Gribouille", false);
        JLabel etiq;
        Dimension dim = new Dimension(400,35);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setSize(400,200); setResizable(false);
        setLayout(new FlowLayout());
        etiq = new JLabel("Mini-application de dessin", JLabel.CENTER);
        etiq.setFont(new Font("Arial", Font.BOLD, 20));
        etiq.setPreferredSize(dim);
        add(etiq);
        etiq = new JLabel("Une application de Philippe Brutus", JLabel.CENTER);
        etiq.setFont(new Font("Times", Font.PLAIN, 18));
        etiq.setPreferredSize(dim); etiq.setSize(dim);
        add(etiq);
        etiq = new JLabel("Avec la participation exclusive de Pierre et Valentin", JLabel.CENTER);
        etiq.setFont(new Font("Times", Font.PLAIN, 16));
        etiq.setPreferredSize(dim); etiq.setSize(dim);
        add(etiq);
        etiq = new JLabel("Version du 22 Mai 2014", JLabel.CENTER);
        etiq.setFont(new Font("Courier New", Font.ITALIC, 16));
        etiq.setPreferredSize(dim); etiq.setSize(dim);
        add(etiq);
    }
    public static void affiche() {
        if (uniqueInstance == null) uniqueInstance = new APropos();
        uniqueInstance.setVisible(true);
    }
}