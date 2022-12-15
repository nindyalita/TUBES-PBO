package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
// import java.nio.file.Path;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        // Path savePath = Path.of("config.txt");
        try {
            // BufferedWriter bw = new BufferedWriter(new FileWriter(savePath.toFile()));
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            // Full screen setting
            if (gp.fullScreenOn == true) {

            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void loadConfig() {

    }
}