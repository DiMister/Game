import gameObjects.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RunGame implements KeyListener
{
    JFrame f1;
    JPanel main;
    GameGraphics graph;
    Container c1;
    
    Map map;
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    Player player;
    
    int tileSize = 40;
    
    public RunGame()
    {
        decodeFile("big");
        setupPanel();
        new Thread(player).start();
        for(Enemy e : enemies) e.playIdle();
        update();
    }
    
    private void update()
    {
        Thread runner = new Thread();
        while(true){
            /** Need this section of code to slow computer down.*/  
            try 
            { 
                runner.sleep(5); 
            }
            catch(InterruptedException e) {} 

            player.move();
            graph.repaint();
            graph.requestFocus();
        }
    }
    
    private void setupPanel()
    {
        f1 = new JFrame("Don't forget a game name! -Max");
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        f1.setSize(ss.width,ss.height);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setResizable(false);

        graph = new GameGraphics(map,enemies,player,tileSize,ss);
        graph.addKeyListener(this);

        c1 = f1.getContentPane();

        main = new JPanel();
        main.setLayout(new BorderLayout());          
        main.add(graph,BorderLayout.CENTER);

        
        c1.add(main);
        f1.show();
    }

    /** changes a list of strings from file into tiles, enemies, and objects */
    private void decodeFile(String fileName) {
        ArrayList<String> lines = FileMangement.readFile(fileName);
        
        //find line where tiles --> enemies
        int split = lines.size();
        for(int index = 0; index < lines.size(); index++) {
            if(lines.get(index).equals("")) {
                split = index;
                break;
            }
        }
        
        //find line where enemies --> objects
        int split2 = lines.size();
        for(int index = split+1; index < lines.size(); index++) {
            if(lines.get(index).equals("")) {
                split2 = index;
                break;
            }
        }
        
        String[] mapLines = new String[split];
        for(int index = 0; index < split; index++){
            mapLines[index] = lines.get(index);
        }
        map = new Map(mapLines);
        
        for(int index = split+1; index < split2; index++) {
            String line = lines.get(index);
            String[] temp = new String[3];
            for (int i = 0; i < temp.length; i++) 
                temp[i] = "";
            
            int j = 0;
            for(int i = 0; i < line.length(); i++) {
                char car = line.charAt(i);
                if(car != '-') temp[j]+=car;
                else j++;
            }
 
            enemies.add(findEnemy(temp[0].charAt(0),Integer.parseInt(temp[1]),Integer.parseInt(temp[2])));
        }
        
        if(split2 < lines.size()) {
            String line = lines.get(split2+1);
            String[] temp = new String[3];
            for (int i = 0; i < temp.length; i++) 
                temp[i] = "";
            
            int j = 0;
            for(int i = 0; i < line.length(); i++) {
                char car = line.charAt(i);
                if(car != '-') temp[j]+=car;
                else j++;
            }
            player = new Player(Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        }
    }
    
    
    
    private Enemy findEnemy(char car, int x, int y) {
        if(car == 's') return new Enemy(x,y);
        return null;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 65) {
            //a || left
            player.moveInput("Left");
        }
        if(e.getKeyCode() == 68) {
            //d || right
            player.moveInput("Right");
        }
        if(e.getKeyCode() == 83) {
            //s || down
            player.moveInput("Down");
        }
        if(e.getKeyCode() == 87) {
            //w || up
            player.moveInput("Up");
        }
        if(e.getKeyCode() == 32) {
            //space || attack
            player.attack();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 65 || e.getKeyCode() == 68 || e.getKeyCode() == 83 || e.getKeyCode() == 87) {
            //stop if any key released
            player.stop();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
}
