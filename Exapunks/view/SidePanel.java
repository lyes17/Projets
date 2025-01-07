package view;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SidePanel extends JPanel {
    
    // Dimensions de la barre latérale
    private static int ratioEcran = 20;
    public static int width = 256;
    public static int height = Fenetre.height;
    // Liste des zones de texte Robot
    public static ArrayList<RobotZone> listeRZ = new ArrayList<RobotZone>();
    public static ArrayList<FichierZone> listeFZ = new ArrayList<FichierZone>();
    public static NewRobotButton newRobotButton = new NewRobotButton("Ajouter un Octopunk");
    // Constructeur de la barre latérale
    public SidePanel() {
        super();
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        //this.setLayout(new FlowLayout());
        this.setBackground(Color.decode("#03A3AD"));
        this.setBounds(0,0,width,height);
        this.setPreferredSize(new Dimension(width,height));
    }

    /*  Get nombre de zones de texte robots
    *   <=> get nb de robots
    */
    public int getRZnb() {
        return listeRZ.size();
    }

    public void refresh(){
        width = (InterfaceGraphique.fenetre.getWidth()*ratioEcran)/100;
        height = InterfaceGraphique.fenetre.getHeight();
        setPreferredSize(new Dimension(width,height));
        for(RobotZone r : RobotZone.listeRZ){
            r.refresh();
        }
    }
}
