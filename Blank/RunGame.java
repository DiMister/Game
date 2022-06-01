import gameObjects.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.IOException;

public class RunGame implements KeyListener
{
    JFrame f1;
    JPanel main;
    GameGraphics graph;
    Container c1;

    Map map;
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    Player player;

    public RunGame()
    {
        System.out.print('\u000C');
        decodeJSON("test");
        setupPanel();
        new Thread(player).start();
        for(Enemy e : enemies) {
            new Thread(e).start();
            e.startRandomMovement();
        }
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

            for(Enemy e : enemies) {
                e.move();
                if(player.isColliding(e))
                    System.out.println("hit");
            }
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

        graph = new GameGraphics(map,enemies,player,ss);
        graph.addKeyListener(this);

        c1 = f1.getContentPane();

        main = new JPanel();
        main.setLayout(new BorderLayout());          
        main.add(graph,BorderLayout.CENTER);

        c1.add(main);
        f1.show();
    }

    private void decodeJSON(String fileName) {
        FileReader reader;
        try{
            reader = new FileReader(fileName+".json");
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject)obj;
            
            JSONArray jMap = (JSONArray)jsonObject.get("Map");
            String[] sMap = new String[jMap.size()];
            for(int index = 0; index < jMap.size(); index++) {
                sMap[index] = (String)jMap.get(index);
            }
            map = new Map(sMap);
            
            JSONArray sEnemies = (JSONArray)jsonObject.get("Enemies");
            Iterator iterator = sEnemies.iterator();
            while(iterator.hasNext()) {
                JSONObject enemy = (JSONObject)iterator.next();
                char type = ((String)(enemy.get("type"))).charAt(0);
                long x = (long)enemy.get("x");
                long y = (long)enemy.get("y");
                enemies.add(findEnemy(type,(int)x,(int)y));
            }
            
            JSONObject sPlayer = (JSONObject)jsonObject.get("Player");
            long x = (long)sPlayer.get("x");
            long y = (long)sPlayer.get("y");
            player = new Player((int)x,(int)y);
            
        } catch (FileNotFoundException e) { e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}
        catch (org.json.simple.parser.ParseException e){e.printStackTrace();}
    }

    private Enemy findEnemy(char car, int x, int y) {
        if(car == 's') return new Enemy(x,y);
        return null;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 65) {
            //a || left
            player.moveInput(3);
        }
        if(e.getKeyCode() == 68) {
            //d || right
            player.moveInput(1);
        }
        if(e.getKeyCode() == 83) {
            //s || down
            player.moveInput(2);
        }
        if(e.getKeyCode() == 87) {
            //w || up
            player.moveInput(0);
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
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'e') {
            //e || attack
            player.attack();
        }
    }
}
