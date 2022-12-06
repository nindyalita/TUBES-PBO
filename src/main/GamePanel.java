package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.Dimension;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // screen settings
    final int orginalTileSize = 16; // original size of character or object
    final int scale = 3; // we make object with 16x16 pixel but lok in the screen in 48x48

    public final int tileSize = orginalTileSize * scale; // 48x48
    public final int maxScreenCol = 16;// max col to see in the screen
    public final int maxScreenRow = 12;// max row to see in the screen
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixel
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixel

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS (frame per second)
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this); // instansisasi keyhandler
    // make differenet instansiasi so we can stop base music and play sound effect
    // at the same time
    Sound music = new Sound();
    Sound se = new Sound();

    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    // actually object in game is static but it like move because in 60 s the object
    // is actually move so it look like real, to make this we make clock on the game
    // with thread
    Thread gameThread;

    public Player player = new Player(this, keyH); // intansiasi player
    // [10] means we can display up to 10 object at the same time
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];// 20 means moster we can display at the same time
    ArrayList<Entity> entityList = new ArrayList<>();

    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1; // must not 1 or 2, we can choose any number
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel() {
        // size of jpanel or content
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); // add keylistener to gamepanel
        this.setFocusable(true); // make gamePanel focus to receive key input
    }

    public void setUpGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        // playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);// add jpanel to thread
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0,01666 second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            // System.out.println("running loop game");
            // long currentTime = System.nanoTime();
            // System.out.println("current time : " + currentTime);

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;

            }
            /*
             * NOTE!
             * 1, UPDATE : update ifnoramtion such as character position
             * update();
             * 
             * 2. DRAW : draw the screen tih the updated information
             * repaint();
             */
        }
    }

    public void update() {

        if (gameState == playState) {
            // player
            player.update();
            // npc
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            // moster
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive == true && monster[i].dying == false) {
                        monster[i].update();
                    }
                    if (monster[i].alive == false) {
                        monster[i] = null;
                    }
                }
            }
        }
        if (gameState == pauseState) {
            // we dont update player information while the game is paused
        }

    }

    public void paintComponent(Graphics g) { // standart method to draw object in java
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // debug
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // title screen
        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            // tile
            tileM.draw(g2);
            // add entity to arraylist
            entityList.add(player);
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            // sort
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity o1, Entity o2) {
                    int result = Integer.compare(o1.worldY, o2.worldX);
                    return result;
                }

            });

            // draw entity
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            // empty entity list
            entityList.clear();

            // ui
            ui.draw(g2);
        }

        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time :" + passed, 10, 400);
            System.out.println("Draw Time:" + passed);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
