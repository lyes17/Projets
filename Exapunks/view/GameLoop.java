package view;
import java.util.ArrayList;


public class GameLoop implements Runnable {
   
   private boolean running;
   private final double updateRate = 1.0d/60.0d; 
   
   private long nextStatTime;
   private int fps, ups;
    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() +1000;

        while(running){
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            while(accumulator> updateRate){
                update();
                render();
                accumulator -= updateRate;
                
            }
            printStats();
            
        }
    }
    public void printStats() {
        if(System.currentTimeMillis() > nextStatTime){
            ArrayList<String> listeNoms=  new ArrayList<>();
            ArrayList<String> listeRobot=  new ArrayList<>();
            for(RobotIG r : RobotIG.listeRobot){
                listeNoms.add(r.getNom());
                if (r.myRobot.getName() == null) {
                    System.out.println("nuuuuuuulll");
                }else{
                listeRobot.add(r.myRobot.getName());}
            }
            System.out.println(String.format("FPS: %d, UPS: %d, RZ: %s,Robot: %s, Piece: %s", fps,ups,listeNoms,listeRobot,Plateforme.listePlateforme.get(1).etatCases));
            
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;        
            
        }
    }
    public void update(){
        ups++;
    }
    public void render(){
        fps++;
        Fenetre.gameArenaPanel.repaint();
        InterfaceGraphique.fenetre.repaint();
        Fenetre.sidePanel.revalidate();
        Fenetre.sidePanel.repaint();
        Fenetre.ecranTitre.revalidate();
        Fenetre.ecranTitre.repaint();
        GameArenaPanel.ecranVictoire.repaint();
    }
}
