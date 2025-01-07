package model;
import view.*;
import java.util.ArrayList;




public class Niveau {
    public String conditionVictoire;

    public ArrayList<Fichier> fichiers;

    public Niveau(int n) {
        restartNiveaux();
        
        switch (n) {
            case 1:
                conditionVictoire = "Deplacez le fichier 70 dans la dernière plateforme.";
                ArrayList<String> nbF1 = new ArrayList<>();
                for(int i=1;i<6;i++){
                    if (i<5) {
                        nbF1.add(String.valueOf(i));
                    }
                    else{
                        nbF1.add(String.valueOf(i));
                    }
                    
                }
                Piece p1 = Piece.listePiece.get(0);
                Piece p2 = Piece.listePiece.get(1);
                
                new Robot("robert",p1); 
                Fenetre.sidePanel.add(SidePanel.newRobotButton);
                p2.addFichier(new Fichier(70,p2,nbF1));
                
                break;
        
            case 2:
                conditionVictoire = "Pour chaque élément dans la bouteille, multipliez le par deux et ensuite poser la bouteille dans la derniere plateforme.";
                ArrayList<String> nbF2 = new ArrayList<>();
                for(int i=1;i<6;i++){
                    if (i<4) {
                        nbF2.add(String.valueOf(i));
                    }
                    else{
                        nbF2.add(String.valueOf(i));
                    }
                    
                }
                Piece p4 = Piece.listePiece.get(0);
                Piece p5 = Piece.listePiece.get(1);
                //p1.addFichier(new Fichier(50,p1,nbF1));
                new Robot("robert",p4); 
                Fenetre.sidePanel.add(SidePanel.newRobotButton);
                p5.addFichier(new Fichier(70,p5,nbF2));
                //p3.addFichier(new Fichier(536,p3,nbF1));
                break;
            case 3:
                conditionVictoire = "Ecrivez dans la bouteille contenant n,m : n,...,m";
                ArrayList<String> nbF3 = new ArrayList<>();
                nbF3.add("1");
                nbF3.add("4");
                Piece p7 = Piece.listePiece.get(0);
                Piece p8 = Piece.listePiece.get(1);
                //p1.addFichier(new Fichier(50,p1,nbF1));
                
                new Robot("robert",p7); 
                Fenetre.sidePanel.add(SidePanel.newRobotButton);
                p8.addFichier(new Fichier(70,p8,nbF3));
                //p3.addFichier(new Fichier(536,p3,nbF1));
                break;
                
            default:
                break;
        }
    }

    public boolean testNiveau(int n) {
        switch (n) {
            case 1:
                for(Fichier f : Piece.listePiece.get(2).getFichiers()){
                    if (Fichier.listeFichier.get(0) == f){
                        return true;
                    }
                }
                    
                break;
            case 2:
                for(Fichier f : Piece.listePiece.get(2).getFichiers()){
                    if (Integer.parseInt(f.contenu.get(0)) == (((Integer.parseInt(f.contenuDefaut.get(0)) + Integer.parseInt(f.contenuDefaut.get(1)))
                     * Integer.parseInt(f.contenuDefaut.get(2))) - Integer.parseInt(f.contenuDefaut.get(3)))){
                        return true;
                    }
                }
                    
                break;

            case 3:
                ArrayList<String> nbF3 = new ArrayList<>();
                nbF3.add("1");
                nbF3.add("2");
                nbF3.add("3");
                nbF3.add("4");
                for(Fichier f : Piece.listePiece.get(2).getFichiers()){
                    if (f.contenu.equals(nbF3)){
                        return true;
                    }
                }
                    
                break;
        
            default:
                break;
        }
        return false;
    }
    public static void restartNiveaux(){
        for(int i =0;i<3;i++){
            Piece.listePiece.get(i).getFichiers().clear();

        }
        Robot.getRobots().clear();
        RobotIG.listeRobot.clear();
        FichierIG.listeFichier.clear();
        //RobotZone.listeRZ.clear();
        for(RobotZone rz : RobotZone.listeRZ){
            Fenetre.sidePanel.remove(rz);
        }
        for(FichierZone fz : FichierZone.listeFZ){
            Fenetre.sidePanel.remove(fz);
        }
        RobotIG.nbRobotTotal  = 0;
        //Piece.listePiece.clear();
        
        Execution.restart();
    }

}