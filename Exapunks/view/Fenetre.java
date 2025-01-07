package view;
import model.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Container;
import java.awt.BorderLayout;


import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Fenetre extends JFrame {
    public static GameArenaPanel gameArenaPanel = new GameArenaPanel();
    public static SidePanel sidePanel = new SidePanel();
    public static DownPanel downPanel = new DownPanel();
    public static EcranTitre ecranTitre = new EcranTitre();
    public static EcranJeu ecranJeu = new EcranJeu();
    public static Niveau niveauActuel;
    public static int niveau;

    Piece p1 = new Piece("200");
    Piece p2 = new Piece("400");
    Piece p3 = new Piece("600");
    /*Dimensions de la fenetre de jeu par défaut */
    public static int width = 1280;
    public static int height = 720;
    
    public Fenetre() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("OctoPunks");
        ImageIcon icon = new ImageIcon(Fenetre.class.getResource("img/octoicon.png"));
        Image image = icon.getImage();
        setIconImage(image);
        this.setPreferredSize(new Dimension(width,height));
        this.pack();

        setVisible(true);
        setLocationRelativeTo(null);
        


        /*// Création des elements de la fenetre en leger /////
        Container conteneur = getContentPane();
        conteneur.setLayout(new BorderLayout());
        conteneur.setBackground(Color.white);

        Container conteneurDroit = new Container();
        conteneurDroit.setLayout(new BorderLayout());
        conteneurDroit.add(gameArenaPanel, BorderLayout.CENTER);
        conteneurDroit.add(downPanel, BorderLayout.SOUTH);

        conteneur.add(conteneurDroit,BorderLayout.CENTER);
        conteneur.add(sidePanel,BorderLayout.WEST);

        new RobotIG(); 
        sidePanel.add(SidePanel.newRobotButton);

        setLocationRelativeTo(null);
        
        ////////////////////////////////////////////////////*/

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Fenetre.sidePanel.refresh();
                System.out.println("un pagnan");
            }
        });
    }
    public void afficherEcranTitre(){
        add(ecranTitre);
    }

    
    
    public void lancerJeu(int n){
        
        // Création des elements de la fenetre en leger /////
        Container conteneur = getContentPane();
        conteneur.setLayout(new BorderLayout());
        conteneur.setBackground(Color.white);

        Container conteneurDroit = new Container();
        conteneurDroit.setLayout(new BorderLayout());
        conteneurDroit.add(gameArenaPanel, BorderLayout.CENTER);
        conteneurDroit.add(downPanel, BorderLayout.SOUTH);

        conteneur.add(conteneurDroit,BorderLayout.CENTER);
        conteneur.add(sidePanel,BorderLayout.WEST);

        
        
        // Niveaux
        
        
        
        niveauActuel = new Niveau(n);
        
        
        niveau = n;

        /*ArrayList<String> nbF1 = new ArrayList<>();
        for(int i=0;i<5;i++){
            if (i<4) {
                nbF1.add(String.valueOf(i)+",  ");
            }
            else{
                nbF1.add(String.valueOf(i));
            }
            
        }
        p1.addFichier(new Fichier(50,p1,nbF1));
        p2.addFichier(new Fichier(70,p2,nbF1));
        p3.addFichier(new Fichier(536,p3,nbF1));

        
    
        ////////////////////////////////////////////////////
    }*/
    
    }
}