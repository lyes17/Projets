package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;





public class BoutonTitre extends JButton{
    
    public BoutonTitre(String nom, int x){
        super(nom);
        setBounds(x,450,300,100);
        setBackground(null);
        setBorder(null);
    
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, EcranTitre.class.getResourceAsStream("img/fontest.ttf")).deriveFont(40f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setFont(customFont);
        //setFont(new Font("Arial", Font.BOLD, 40));
        setForeground(Color.white);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //setBackground(Color.decode("#02AFD1")); // Changement de couleur du texte lorsque la souris survole le bouton
                setForeground(Color.decode("#02AFD1"));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                //setBackground(Color.decode("#FFE7FF")); // Rétablissement de la couleur du texte lorsque la souris quitte le bouton
                setForeground(Color.white);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        switch (nom) {
            case "Niveau 1":
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        InterfaceGraphique.fenetre.lancerJeu(1);
                        InterfaceGraphique.fenetre.remove(Fenetre.ecranTitre);
                    }
                });
                break;
            case "Niveau 2":
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    InterfaceGraphique.fenetre.lancerJeu(2);
                    InterfaceGraphique.fenetre.remove(Fenetre.ecranTitre);
                    // Inserer attribut niveau
                }
            });
                break;
            case "Niveau 3":
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    InterfaceGraphique.fenetre.lancerJeu(3);
                    InterfaceGraphique.fenetre.remove(Fenetre.ecranTitre);
                }
            });
                break;
            default:
                break;
        }

    }

}