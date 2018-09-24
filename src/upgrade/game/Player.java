
package upgrade.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class Player {
    
    int coins;
    private int lives;
    ImageView image;
    private Double ypos;
    private Double xpos;
    private Double vel;
    
    public Player(){
        this.image = new ImageView(new Image(getClass().getResourceAsStream("player.png")));
        this.coins = 0;
        this.lives = 3;
        setYpos(390.00);
        setXpos(200.00);
        this.vel = 0.0;
    } 

    public Double getYpos() {
        return image.getY();
    }

    public void setYpos(Double ypos) {
        this.image.setY(ypos);
    }

    public Double getXpos() {
        return image.getX();
    }

    public void setXpos(Double xpos) {
        this.image.setX(xpos);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Double getVel() {
        return vel;
    }

    public void setVel(Double vel) {
        this.vel = vel;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
    
    
    
    
    
    
}
