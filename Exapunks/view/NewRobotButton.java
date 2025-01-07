package view;
import model.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.event.*;

public class NewRobotButton extends JButton {

    public NewRobotButton(String nom) {
        super(nom);
        setPreferredSize(new Dimension(220,30));
        setBackground(Color.decode("#FFE7FF"));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setFont(InterfaceGraphique.customFont);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fenetre.sidePanel.remove(SidePanel.newRobotButton);
                Robot newRobot = new Robot("a",Piece.listePiece.get(0));
                Fenetre.sidePanel.add(newRobot.getRobotGraphique().getRobotZone());
                if(RobotIG.listeRobot.size() < 3){
                    Fenetre.sidePanel.add(SidePanel.newRobotButton);
                }
                for(FichierZone fz : FichierZone.listeFZ){
                    Fenetre.sidePanel.remove(fz);
                    Fenetre.sidePanel.add(fz);
                }
                Fenetre.sidePanel.revalidate();
                Fenetre.sidePanel.repaint();
            }
        });
        // Couleur de hover //
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.decode("#D187DC")); // Changement de couleur du texte lorsque la souris survole le bouton
                setForeground(Color.white);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.decode("#FFE7FF")); // Rétablissement de la couleur du texte lorsque la souris quitte le bouton
                setForeground(Color.black);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        // Fin hover //
    }
    
}