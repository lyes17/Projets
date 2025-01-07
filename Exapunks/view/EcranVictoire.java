package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class EcranVictoire extends JPanel{
    JButton boutonEcranTitre = new JButton("Niveau suivant");
    
    public EcranVictoire(){
        setLayout(null);
        setBackground(Color.decode("#FFAEFD"));
        setBounds(150,150,700,200);
        boutonEcranTitre.setBounds(250,150,200,20);
        boutonEcranTitre.setBackground(Color.white);
        boutonEcranTitre.setBorder(null);
        boutonEcranTitre.setForeground(Color.decode("#02AFD1"));
        boutonEcranTitre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //InterfaceGraphique.fenetre.lancerJeu();
                Fenetre.gameArenaPanel.remove(GameArenaPanel.ecranVictoire);
                if (Fenetre.niveau == 3) {
                    System.exit(0);
                }
                InterfaceGraphique.fenetre.lancerJeu(Fenetre.niveau + 1);
            }
        });
        add(boutonEcranTitre);

        JLabel felicitations = new JLabel("Felicitations");
        felicitations.setBounds(150,0,700,100);
        felicitations.setFont(InterfaceGraphique.customFont.deriveFont(40f));
        
        add(felicitations);
        
    
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (Fenetre.niveau  == 3) {
            boutonEcranTitre.setText("Fermer le jeu");
        }
    }
    
}