package view;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.event.DocumentEvent;


public class RobotZone extends JPanel{ 

    public static ArrayList<RobotZone> listeRZ = new ArrayList<>();

    int id;
    RobotIG myRobot;
    TableauVariables tableauVariables;
    DeleteRobotButton delete = new DeleteRobotButton();
    JTextArea zoneDeTexte = new JTextArea("");
    JPanel conteneurHaut = new JPanel();

    // Constructeur de la zone texte de Robot
    public RobotZone(String nomRobot,int id) {
        tableauVariables =  new TableauVariables();
        tableauVariables.myRobotZone = this;
        listeRZ.add(this);
        this.id = id;
        SidePanel.listeRZ.add(this);
        
        Fenetre.sidePanel.add(this,BorderLayout.NORTH);
        this.setPreferredSize(new Dimension(SidePanel.width,170));
        this.setLayout(new BorderLayout());

        JTextField saisieNom = new JTextField(nomRobot,SidePanel.width/(64/5));
        saisieNom.setBackground(Color.decode("#03A3AD"));
        saisieNom.setHorizontalAlignment(SwingConstants.CENTER);
        Border border = new LineBorder(Color.black, 0, true);
        saisieNom.setForeground(Color.decode("#410D69"));
        saisieNom.setBorder(border);
        saisieNom.setFont(InterfaceGraphique.customFont.deriveFont(14f));
        
        saisieNom.addActionListener(e -> {
            String newText = saisieNom.getText();
            myRobot.setNom(newText);
        });

        delete.myRZ = this;
    
        zoneDeTexte.setFont(InterfaceGraphique.customFont.deriveFont(14f));
        zoneDeTexte.setForeground(Color.white);
        zoneDeTexte.setBackground(Color.decode("#410D69"));

        add(tableauVariables,BorderLayout.EAST);

        conteneurHaut.setLayout(new BorderLayout());
        conteneurHaut.add(saisieNom, BorderLayout.CENTER);
        conteneurHaut.add(delete, BorderLayout.EAST);

        add(conteneurHaut, BorderLayout.NORTH);
        add(zoneDeTexte, BorderLayout.CENTER);
        

        
        zoneDeTexte.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateText();
            }

            @Override 
            public void changedUpdate(DocumentEvent e) {
                updateText();
            }

            private void updateText() {
                String texte = zoneDeTexte.getText();
                myRobot.texteActuel = texte.toUpperCase();
                myRobot.myRobot.setTexte(texte.toUpperCase());
            }
        });
        
  }

    public int getId(){
        return id;
    }

    public void refresh() {
        setPreferredSize(new Dimension(Fenetre.sidePanel.getWidth(),170));
        tableauVariables.setPreferredSize(new Dimension((int) (0.3125*Fenetre.sidePanel.getWidth()),100));

    }


}
