package main;

import javax.swing.JFrame;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // cannot resized window
        window.setTitle("Treasure Hunting Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // add gamepanel to frame

        gamePanel.config.loadConfig();
        if (gamePanel.fullScreenOn == true) {
            window.setUndecorated(true);
        }

        window.pack(); // frame menyesuaikan dengan gamepanel/konten

        window.setLocationRelativeTo(null); // setwindow in center
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}