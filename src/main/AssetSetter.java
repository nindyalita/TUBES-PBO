package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import monster.MON_RedSlime;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // function to make default object to show in the map
    public void setObject() {

        // gp.obj[0] = new OBJ_Door(gp);
        // gp.obj[0].worldX = gp.tileSize * 21;
        // gp.obj[0].worldY = gp.tileSize * 22;

        // gp.obj[1] = new OBJ_Door(gp);
        // gp.obj[1].worldX = gp.tileSize * 23;
        // gp.obj[1].worldY = gp.tileSize * 25;

    }

    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }

    public void setMonster() {
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = gp.tileSize * 23;
        gp.monster[0].worldY = gp.tileSize * 36;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = gp.tileSize * 23;
        gp.monster[1].worldY = gp.tileSize * 37;

        gp.monster[2] = new MON_RedSlime(gp);
        gp.monster[2].worldX = gp.tileSize * 23;
        gp.monster[2].worldY = gp.tileSize * 38;

        gp.monster[3] = new MON_RedSlime(gp);
        gp.monster[3].worldX = gp.tileSize * 23;
        gp.monster[3].worldY = gp.tileSize * 39;
    }
}
