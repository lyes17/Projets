package view;
import java.awt.*;




public class InterfaceGraphique implements Runnable {
    public static Font customFont = null;
    /*try {
        customFont = Font.createFont(Font.TRUETYPE_FONT, Fenetre.class.getResourceAsStream("img/fontest.ttf")).deriveFont(20f);
        System.out.println(customFont);
    } catch (Exception e) {
        e.printStackTrace();
    };*/
    public static Fenetre fenetre;

    
        
    
    /*public static void main(String[] args) throws Exception {
        
        
        new Thread(new InterfaceGraphique()).start();
        
    }*/
    @Override
    public void run() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, InterfaceGraphique.class.getResourceAsStream("img/fontest.ttf")).deriveFont(10f);
            System.out.println(customFont);
        } catch (Exception e) {
            e.printStackTrace();
        };
        fenetre = new Fenetre();
        new Thread(new GameLoop()).start();
        fenetre.afficherEcranTitre();
    }
}
