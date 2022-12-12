package tile_interactive;

import main.GamePanel;

public class IT_Trunk extends InteractiveTile {

    GamePanel gp;

    public IT_Trunk(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        name = "Trunk";
        down1 = setup("/tiles_interactive/trunk", gp.tileSize, gp.tileSize);

        // set no cllision, no slid are
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }
}
