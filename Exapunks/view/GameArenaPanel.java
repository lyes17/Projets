package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class GameArenaPanel extends JPanel {
    // Dimensions de la zone de jeu
    public static int width = Fenetre.width - SidePanel.width;
    public static int height = Fenetre.height;
    private Image backgroundImage;

    public static Plateforme plateforme = new Plateforme(50, 400,200);
    public static Plateforme plateforme2 = new Plateforme( 50 + 2*Plateforme.XC, 400 - 2*Plateforme.YC,400);
    public static Plateforme plateforme3 = new Plateforme( 50 + 4*Plateforme.XC, 400 - 4*Plateforme.YC,600);

    public static EcranVictoire ecranVictoire = new EcranVictoire();

    // Constructeur de la zone de jeu
    public GameArenaPanel(){
        super();
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("img/background.png")); // Remplacer "background.png" par le chemin de votre image

        } catch (IOException e){

        }

        this.setBackground(Color.red);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(150,150));
        //this.setBounds(SidePanel.width, 0,width , height);
        this.setDoubleBuffered(true);
    }

    public static void restartModels(){
        for(RobotIG r : RobotIG.listeRobot){
            r.getRobotModel().tpTo(r.plateformeDefaut,r.cellDefaut);
        }
        for(FichierIG f : FichierIG.listeFichier){
            f.getFichierModel().tpTo(f.plateformeDefaut,f.cellDefaut);
        }
    }

    public void afficherVictoire(){
        add(ecranVictoire);
        System.out.println("gagné ?");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        for(int i = Plateforme.listePlateforme.size();i>0 ;i--){
            Plateforme.listePlateforme.get(i-1).drawMe(g);
            
        }
        for(RobotIG r : RobotIG.listeRobot){
            r.getRobotModel().drawMe(g);
            r.getRobotModel().update();
            
        }
        for(FichierIG f : FichierIG.listeFichier){
            f.getFichierModel().drawMe(g);
        }
        for(RobotIG r : RobotIG.listeRobot){
            r.getRobotModel().drawMyName(g);
            r.getRobotModel().drawMyFichierName(g);
            
        }
        for(FichierIG f : FichierIG.listeFichier){
            f.getFichierModel().drawMyName(g);
        }
        
        
    }

}