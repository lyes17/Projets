package view;
import model.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;

import java.awt.event.*;

public class DeleteRobotButton extends JButton {
    RobotZone myRZ;
    public DeleteRobotButton() {
        super();
        setForeground(Color.decode("#7E000F"));
        setText("X");
        setBackground(Color.decode("#03A3AD"));
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RobotIG.listeRobot.remove(myRZ.myRobot);
                Robot.robots.remove(myRZ.myRobot.myRobot);
                myRZ.myRobot.getPlateforme().etatCases.set(myRZ.myRobot.getCell()-1, false);
                Fenetre.sidePanel.remove(myRZ);
                if(RobotIG.listeRobot.size() < 3){
                    Fenetre.sidePanel.add(SidePanel.newRobotButton);
                    for(FichierZone fz : FichierZone.listeFZ){
                        Fenetre.sidePanel.remove(fz);
                        Fenetre.sidePanel.add(fz);
                    }
                }
                Fenetre.sidePanel.revalidate();
                Fenetre.sidePanel.repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.decode("#7E000F")); // Changement de couleur du texte lorsque la souris survole le bouton
                setForeground(Color.white);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.decode("#03A3AD")); // Rétablissement de la couleur du texte lorsque la souris quitte le bouton
                setForeground(Color.decode("#7E000F"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
    
}