package view;
import model.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.IOException;
import java.lang.Thread;
import java.awt.event.*;

public class DownPanel extends JPanel {
    JLabel consigne = new JLabel();
    public class Commande extends JButton{
        public BufferedImage icon;
        String iconPath;
        int posX = 100;
        int posY = 50;
        public Commande(int x){
             if(x==1){
                iconPath = "img/stop.png";
                setBounds(posX,posY, 30, 30);
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(RobotIG r : RobotIG.listeRobot){
                            if (r.getRobotModel().isMoving()) {
                                Thread thread = new Thread(() -> {
                                });
                                thread.start();
                            }
                            Execution.restart();
                        }
                    }
                });
            }
            if(x==2){
                iconPath = "img/cplay.png";
                setBounds(posX+40,posY, 30, 30);
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Execution.execute();
                    }
                });
            }
            if(x==3){
                iconPath = "img/cff.png";
                setBounds(posX+80,posY, 30, 30);
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(int i = 0 ; i < 15  ; i++){
                            Execution.execute();
                        }
                        
                    }
                });
            }
            try {
                icon = ImageIO.read(getClass().getResource(iconPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            setBackground(Color.decode("#FFAEFD"));
            setBorder(new LineBorder(Color.white, 2, true));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(Color.decode("#02AFD1")); // Changement de couleur du texte lorsque la souris survole le bouton
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
        
                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(Color.decode("#FFAEFD")); // Rétablissement de la couleur du texte lorsque la souris quitte le bouton
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(icon,5,5,20,20,this);
        }
    }
    // Constructeur de la barre du bas
    public DownPanel() {
        super();
        this.setLayout(null);
        this.setBackground(new Color(66,33,99));
        this.setPreferredSize(new Dimension(150,150));
        consigne.setBounds(100,-125,1000,300);
        consigne.setForeground(Color.white);
        add(consigne);

        
        Commande stop = new Commande(1);
        Commande step = new Commande(2);
        Commande stepF = new Commande(3);

        add(stop);
        add(step);
        add(stepF);

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        consigne.setText("Niveau "+Fenetre.niveau + "  : " + Fenetre.niveauActuel.conditionVictoire);
        
    }
}