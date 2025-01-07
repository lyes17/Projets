package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.*;



public class EcranTitre extends JPanel{
    public BufferedImage title;
    public static JButton boutonPlay = new BoutonTitre("Jouer",1280/2-150);
    public EcranTitre(){
        setLayout(null);
        setBackground(Color.decode("#FFAEFD"));
        try {
                // Charger l'image du sprite du personnage depuis un fichier
                title = ImageIO.read(getClass().getResource("img/title.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        
        add(boutonPlay);
        boutonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //InterfaceGraphique.fenetre.lancerJeu();
                Fenetre.ecranTitre.remove(EcranTitre.boutonPlay);
                BoutonTitre niveau1 = new BoutonTitre("Niveau 1",1280/2-150 - 400);
                BoutonTitre niveau2 = new BoutonTitre("Niveau 2", 1280/2-150);
                BoutonTitre niveau3 = new BoutonTitre("Niveau 3", 1280/2-150 +400);
                Fenetre.ecranTitre.add(niveau1);
                Fenetre.ecranTitre.add(niveau2);
                Fenetre.ecranTitre.add(niveau3);
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        double ratio = 1.5;
        int width =(int)(1024/ratio);
        int height =(int) (304/ratio);
        int x = 1280/2 - width/2;
        int y =150;
        g.drawImage(title, x, y,width,height, this);
    }
    
}