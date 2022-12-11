package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile { // entity class-->project tile-->obj_fireball, so fireball class can
                                               // access all stuf in the entity class
    GamePanel gp;

    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 10; // how fast it can flay
        maxLife = 80; // dissaper until 80 frame
        life = maxLife;
        attack = 2;
        useCost = 1; // spent 1 mana to use this
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/fireball_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/fireball_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/fireball_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/fireball_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/fireball_right_2", gp.tileSize, gp.tileSize);
    }

}
