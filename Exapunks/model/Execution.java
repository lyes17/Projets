package model;
import io.*;
import view.*;


public class Execution
{
    public static int index =0;
    

    public Execution()
    {
        index = 0;
    }

    public static void execute()
    {
        Exasynchrone exasynchrone = new Exasynchrone(2135515618);
        
        
        for(Robot r : exasynchrone.modeAsynchroneDiscret())
        {
            TextSplitter ts;
            try{
                ts = new TextSplitter(r.getTexte());
            }catch(NullPointerException e){
                System.err.println("pas de commandes dans");
                continue;
            }
             
            String commandeText;
            // On prends la ligne à l'index actuel
            try{
                commandeText = ts.getLignes().get(index);
            }catch(IndexOutOfBoundsException e){
                System.err.println("plus de commandes de robot :" + r.getName() );
                return;
            }
            commandeText = ts.getLignes().get(index);
            Commande commande = new Commande(commandeText);
            String arg = commande.getFirst();
            
            if (r.getRobotGraphique().getRobotModel().isMoving()) {
                r.getRobotGraphique().getRobotModel().tpTo(r.getRobotGraphique().getRobotModel().destinationX, r.getRobotGraphique().getRobotModel().destinationY);
            }
            if(VerifieCommande.commandeValide(arg)){
                System.out.println("commande valide");
                switch(arg)
                {
                    case "GRAB" :
                        if(commande.hasSecond() == false)
                        {
                            throw new IllegalArgumentException("il manque un arg\n");
                        }
                        r.grab(Integer.parseInt(commande.getSecond()));
                        break;
                    case "LINK" : 
                        if(commande.hasSecond() == false)
                        {
                            throw new IllegalArgumentException("il manque un arg\n");
                        }
                        r.link(Integer.parseInt(commande.getSecond()));
                        break;
                    case "COPY" :
                        if(commande.hasSecond() == false || commande.hasThird() == false)
                        {
                            throw new IllegalArgumentException("il manque un arg\n");
                        }
                       
                        r.copy(commande.getSecond(),commande.getThird());
                        if (commande.getSecond().equals("F") || commande.getThird().equals("F")) {
                            r.getF().incrCurseur();
                        }
                        break;
                    case "DROP" :
                        r.drop();
                        break; 
                    case "WIPE" :
                        System.out.println("paganndn ???");
                        r.wipe();
                        break;
                    case "ADDI" :
                        if(commande.hasSecond() == false ||commande.hasFourth() == false|| commande.hasThird() == false)
                        {
                            throw new IllegalArgumentException("il manque un arg\n");
                        }
                        r.addi(commande.getSecond(),commande.getThird(),commande.getFourth());
                        if (commande.getSecond().equals("F") || commande.getThird().equals("F") || commande.getFourth().equals("F")) {
                            r.getF().incrCurseur();
                        }
                        break;  
                    case "MULI" : 
                        if(commande.hasSecond() == false ||commande.hasFourth() == false|| commande.hasThird() == false)
                        {
                            throw new IllegalArgumentException("il manque un arg\n");
                        }
                        r.muli(commande.getSecond(),commande.getThird(),commande.getFourth());
                        if (commande.getSecond().equals("F") || commande.getThird().equals("F") || commande.getFourth().equals("F")) {
                            r.getF().incrCurseur();
                        }
                        break;  
                    case "SUBI" : 
                        if(commande.hasSecond() == false ||commande.hasFourth() == false|| commande.hasThird() == false)
                        {
                            throw new IllegalArgumentException("il manque un arg\n");
                        }
                        r.subi(commande.getSecond(),commande.getThird(),commande.getFourth());
                        break;  
                    case "HALT" :
                        r.halt();
                        break;            
                }
            }
            
        }
        index++;
    }
    public static void restart(){
        index = 0;
        for(Robot r: Robot.robots){
            r.drop();
            r.setX(0);
            r.setT(0);
            r.setM(0);
        }
        for(Fichier f : Fichier.listeFichier){
            f.zeroCurseur();
            f.restartDonnees();
        }
        GameArenaPanel.restartModels();
    }
    
}

