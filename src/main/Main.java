package main;

import javax.swing.JFrame;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // cannot resized window
        window.setTitle("Treasure Hunting Game");

        // * uncomment line ini untuk menampilkan top bar
        // window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // add gamepanel to frame

        window.pack(); // frame menyesuaikan dengan gamepanel/konten

        window.setLocationRelativeTo(null); // setwindow in center
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}