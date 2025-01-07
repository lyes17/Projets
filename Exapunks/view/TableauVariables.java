package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

public class TableauVariables extends JPanel {
    private JPanel xPanel; 
    private JPanel tPanel; 
    private JPanel fPanel; 
    private JPanel mPanel; 

    public JLabel valeurX;
    public JLabel valeurT;
    public JLabel valeurF;
    public JLabel valeurM;

    public RobotZone myRobotZone;

    private int width = (int) (0.3125*Fenetre.sidePanel.getWidth());
    private int heightCell = 27;

    public class NomVariables extends JPanel{
        Color couleur = Color.decode("#D187DC");
        public NomVariables(String nom){
            super();
            setPreferredSize(new Dimension(width/3,heightCell));
            setBackground(couleur);
            JLabel lettre = new JLabel(nom);
            lettre.setForeground(Color.white);
            add(lettre);
            
        }
        public NomVariables(){
            super();
            setPreferredSize(new Dimension(width/3,2*heightCell));
            setBackground(couleur);
            JLabel lettre = new JLabel("M");
            lettre.setForeground(Color.white);
            add(lettre);
        }
    }
    public TableauVariables(){
        super();
        Color couleur = Color.decode("#FFE7FF");
        xPanel  = new JPanel();
        tPanel  = new JPanel();
        fPanel  = new JPanel();
        mPanel  = new JPanel();

        valeurX = new JLabel("  ");
        valeurT = new JLabel("  ");
        valeurF = new JLabel("  ");
        valeurM = new JLabel("  ");
        
        setPreferredSize(new Dimension(width,100));
        setBackground(Color.decode("#410D69"));
        setLayout(new FlowLayout(FlowLayout.LEADING, 0, 2));

        xPanel.setBackground(couleur);
        xPanel.setPreferredSize(new Dimension(width,heightCell));
        xPanel.setLayout(new BorderLayout());
        xPanel.add(new NomVariables("X"),BorderLayout.WEST);
        xPanel.add(valeurX);
        add(xPanel);

        tPanel.setBackground(couleur);
        tPanel.setPreferredSize(new Dimension(width,heightCell));
        add(tPanel);
        tPanel.setLayout(new BorderLayout());
        tPanel.add(new NomVariables("T"),BorderLayout.WEST);
        tPanel.add(valeurT);
        add(tPanel);
    
        fPanel.setBackground(couleur);
        fPanel.setPreferredSize(new Dimension(width,heightCell));
        add(fPanel);
        fPanel.setLayout(new BorderLayout());
        fPanel.add(new NomVariables("F"),BorderLayout.WEST);
        fPanel.add(valeurF);
        add(fPanel);
    

        mPanel.setBackground(couleur);
        mPanel.setPreferredSize(new Dimension(width,2*heightCell));
        add(mPanel);
        mPanel.setLayout(new BorderLayout());
        mPanel.add(new NomVariables(),BorderLayout.WEST);
        mPanel.add(valeurM);
        add(mPanel);

        Border border = new LineBorder(Color.decode("#03A3AD"), 2, true);
        mPanel.setBorder(border);
        fPanel.setBorder(border);
        xPanel.setBorder(border);
        tPanel.setBorder(border);
    
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        valeurX.setText("   " + String.valueOf(myRobotZone.myRobot.myRobot.getX()));
        valeurT.setText("   " + String.valueOf(myRobotZone.myRobot.myRobot.getT()));
        if (myRobotZone.myRobot.myRobot.getF() != null ) {
            valeurF.setText("   " + String.valueOf(myRobotZone.myRobot.myRobot.getF().getName()));
        }
        else{
            valeurF.setText("   Vide");
        }
        valeurM.setText("   " + String.valueOf(myRobotZone.myRobot.myRobot.getM()));
    }
}