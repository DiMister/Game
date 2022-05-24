package LevelEditor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.event.*;

//you do not want to see how many methods I override
public class CreateLevel implements ActionListener, MouseMotionListener, ChangeListener, MouseListener
{
    JFrame f1;
    JPanel main, sub, scrollPane;
    JButton done, importFile, snap;
    JComboBox<String> mapSelect, tilesSelect, enemySelect, objectSelect, swap;
    JSlider brushSelect;
    CreateGraphics graph;
    Dimension ss;
    JScrollPane scroll;
    Container c1;
    

    String[] tiles = {"Normal", "Wall", "Water", "Lava", "Spike"};
    String[] mapSize = {"Tiny", "Small", "Normal", "Huge", "Massive"};
    String[] enemiesList = {"Skeleton"};
    String[] objectList = {"Player"};
    String[] swapList = {"Tile Map","Enemies","Objects"};

    Tile[][] map = new Tile[18][18];
    Tile selectedTile = new NormalTile();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    Point playerSpawn;

    int brushSize = 1;
	boolean snapToGrid = false;
    
    public CreateLevel()
    {
        //big, large, huge, massive, girthy
        setPanel();
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

            graph.repaint();
        }
    }
    
    private void decodeFile(String fileName) {
        ArrayList<String> lines = FileMangement.readFile(fileName);
        
        int split = lines.size();
        for(int index = 0; index < lines.size(); index++) {
            if(lines.get(index).equals("")) split = index;
        }
        
        map = new Tile[split][lines.get(0).length()];
        
        //reads each char and finds the tile that corsonds to it
        for(int index = 0; index < map.length; index++){
          for(int i = 0; i < map[0].length; i++){
                map[index][i] = findTile(lines.get(index).charAt(i));
            }
        }
        
        for(int index = split+1; index < lines.size(); index++) {
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
    }
    
    private Tile findTile(char car) {
        if(car == 'n') return new NormalTile();
        if(car == 'w') return new WallTile();
        if(car == 'l') return new LavaTile();
        if(car == 'v') return new WaterTile();
        if(car == 's') return new SpikeTile(1);
        return null;
    }
    
    private Enemy findEnemy(char car, int x, int y) {
        if(car == 's') return new Enemy(x,y);
        return null;
    }

    private void drawTiles(Point p) {

        //set location based on the index value of grid and dispalcment
        p.setLocation((int)((p.getX()) / 30),(int)((p.getY()) / 30));
        //int rounding caused a lot of pain
        int row = (int)p.getX();
        int col = (int)p.getY();
        int radius = brushSize/2;
        
        if(col < map[0].length && row < map.length)
            for(int index = -radius; index < radius+1; index++){
                if(row+index < map.length && row+index >= 0){
                    for(int i = -radius; i < radius+1; i++){
                        if(col+i < map[0].length && col+i >= 0)
                            if(inside_circle(p,new Point(row+index,col+i),radius))
                                map[row+index][col+i] = selectedTile;
                    }
                }
            }
    }
    
    private void drawEnemy(Point p) {
        //p.setLocation((int)((p.getX()) / 30),(int)((p.getY()) / 30));
        if(p.y < map[0].length*30 && p.x < map.length*30)
            enemies.add(new Enemy(p.x-30,p.y-30));
    }
    
    private void drawObject(Point p) {
        //p.setLocation((int)((p.getX()) / 30),(int)((p.getY()) / 30));
        if(p.y < map[0].length*30 && p.x < map.length*30)
            playerSpawn = new Point(p.x-15,p.y-15);
        graph.updateReset(map,enemies,playerSpawn);
    }
    
    private boolean inside_circle(Point center, Point tile, float radius) {
        //from https://www.redblobgames.com/grids/circle-drawing/
        //really fancy math to find if tile distance from center of circle in radius
        float dx = center.x - tile.x,
              dy = center.y - tile.y;
        double distance = Math.sqrt(dx*dx + dy*dy);
        return distance <= radius+0.5;
    }

    private void setPanel()
    {

        f1 = new JFrame("Lebel Edit");
        ss = Toolkit.getDefaultToolkit().getScreenSize();
        f1.setSize(ss.width,ss.height-50);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setResizable(false);

        graph = new CreateGraphics(map,enemies,playerSpawn);
        graph.addMouseMotionListener(this);
        graph.addMouseListener(this);

        c1 = f1.getContentPane();

        tilesSelect = new JComboBox<>(tiles);
        tilesSelect.addActionListener(this);
        mapSelect = new JComboBox<>(mapSize);
        mapSelect.addActionListener(this);
        enemySelect = new JComboBox<>(enemiesList);
        enemySelect.addActionListener(this);
        objectSelect = new JComboBox<>(objectList);
        objectSelect.addActionListener(this);
        swap = new JComboBox<>(swapList);
        swap.addActionListener(this);

        //sliders suck they need their own changeLister cuz their *special*
        brushSelect = new JSlider(JSlider.HORIZONTAL,1,10,1);
        brushSelect.addChangeListener(this);

        done = new JButton("Done");
        done.addActionListener(this);
        importFile = new JButton("Import File");
        importFile.addActionListener(this);
		snap = new JButton("Snap To Grid");
		snap.addActionListener(this);

        
        scrollPane = new JPanel();
        scrollPane.setLayout(new BorderLayout());
        scrollPane.add(graph,BorderLayout.CENTER);
        scrollPane.setPreferredSize(new Dimension(540,540));
        scroll = new JScrollPane(scrollPane);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        sub = new JPanel(); 
        sub.add(tilesSelect);
        sub.add(brushSelect);
        sub.add(mapSelect);
        sub.add(swap);
        sub.add(importFile);
        sub.add(done);

        main = new JPanel();
        main.setLayout(new BorderLayout());          
        //main.setSize(500,500);
        main.add(scroll,BorderLayout.CENTER);
        main.add(sub,BorderLayout.SOUTH);
        

        c1.add(main);
        f1.show();
    }
    
    private void swapToEnemies() {
        sub.removeAll();
        
        sub.add(enemySelect);
		sub.add(snap);
        sub.add(swap);  
        sub.add(importFile);
        sub.add(done);
        f1.show();
    }
    
    private void swapToObjects() {
        sub.removeAll();
        
        sub.add(objectSelect);
		sub.add(snap);
        sub.add(swap);
        sub.add(importFile);
        sub.add(done);
        f1.show();
    }
    
    private void swapToTiles() {
        sub.removeAll();
        
        sub.add(tilesSelect);
        sub.add(brushSelect);
        sub.add(mapSelect);
        sub.add(swap);
        sub.add(importFile);
        sub.add(done);
        f1.show();
    }

    @Override
    public void actionPerformed (ActionEvent event)
    {
        if(event.getSource() == done) {
            //save level
            /**JOptionPane to get name of level*/
            String name = (String)JOptionPane.showInputDialog(f1,"Enter name of level","Save",JOptionPane.INFORMATION_MESSAGE);

            FileMangement.saveFile(map,enemies,name);
        }
        if(event.getSource() == importFile) {
            //import level
            /**JOptionPane to get name of file*/
            String name = (String)JOptionPane.showInputDialog(f1,"Enter name of file","Import",JOptionPane.INFORMATION_MESSAGE);

            decodeFile(name);
            graph.updateReset(map,enemies,playerSpawn);
        }
		if(event.getSource() == snap) {
			if(snapToGrid) snapToGrid = false;
			else snapToGrid = true;
		}
        if(event.getSource() == swap) {
            if(swap.getSelectedItem() == "Tile Map")
                swapToTiles();
            else if(swap.getSelectedItem() == "Enemies")
                swapToEnemies();
            else if(swap.getSelectedItem() == "Objects")
                swapToObjects();
        }
        if(event.getSource() == mapSelect) {
            //wipes map and makes new size
            if(mapSelect.getSelectedItem() == "Tiny")
                map = new Tile[10][10];
            else if(mapSelect.getSelectedItem() ==  "Small")
                map = new Tile[20][20];
            else if(mapSelect.getSelectedItem() ==  "Normal")
                map = new Tile[40][30];
            else if(mapSelect.getSelectedItem() ==  "Huge")
                map = new Tile[80][80];
            else if(mapSelect.getSelectedItem() ==  "Massive"){
                map = new Tile[160][160];
                                
            }
            
            
            playerSpawn = null;
            enemies = new ArrayList<Enemy>();
            graph.updateReset(map,enemies,playerSpawn);
            scrollPane.setPreferredSize(new Dimension(map.length*30,map.length*30));
            scrollPane.revalidate();
        }
        if(event.getSource() == tilesSelect) {
            if(tilesSelect.getSelectedItem() == "Normal")
                selectedTile = new NormalTile();
            if(tilesSelect.getSelectedItem() ==  "Wall")
                selectedTile = new WallTile();
            if(tilesSelect.getSelectedItem() ==  "Water")
                selectedTile = new WaterTile();
            if(tilesSelect.getSelectedItem() ==  "Lava")
                selectedTile = new LavaTile();
            if(tilesSelect.getSelectedItem() ==  "Spike")
                selectedTile = new SpikeTile(1);
            
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent evt) {}

    @Override
    public void mouseEntered(MouseEvent evt) {}
    
    @Override
    public void mouseExited(MouseEvent evt) {}
    
    @Override
    public void mousePressed(MouseEvent evt) {
		p.setLocation((int)((p.getX()) / 30)*30,(int)((p.getY()) / 30)*30);
        if(swap.getSelectedItem() == "Tile Map") drawTiles(evt.getPoint());
        else if(swap.getSelectedItem() == "Enemies")drawEnemy(evt.getPoint());
        else if(swap.getSelectedItem() == "Objects") drawObject(evt.getPoint());
    }
    
    @Override
    public void mouseReleased(MouseEvent evt) {}

    @Override
    public void mouseMoved(MouseEvent evt) 
    {}
    
    @Override
    public void mouseDragged(MouseEvent evt) {
        if(swap.getSelectedItem() == "Tile Map") drawTiles(evt.getPoint());
    }

    @Override
    public void stateChanged(ChangeEvent event){
        //do weird math to make number always odd
        brushSize = brushSelect.getValue() + brushSelect.getValue() -1;
    }

}