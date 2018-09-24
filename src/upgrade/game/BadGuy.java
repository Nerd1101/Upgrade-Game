
package upgrade.game;

import javafx.scene.image.ImageView;

public class BadGuy {
    
    private int health;
    ImageView im;
    ImageView img;
    private Double posx;
    private Double posy;
    ImageView shot1;
    ImageView shot2;
    
    public BadGuy(ImageView i,int h){
        this. health = h;
        this.img = i;
        shot1 = UpgradeGame.getShot1();
        shot2 = UpgradeGame.getShot2();
        this.img.setX(0);
        this.img.setY(-25);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public Double getPosx() {
        return img.getX();
    }

    public void setPosx(Double posx) {
        this.img.setX(posx);
    }

    public Double getPosy() {
        return img.getY();
    }

    public void setPosy(Double posy) {
        this.img.setY(posy);
    }
    
    public void gotShot(){
        
    }
    
    
}
