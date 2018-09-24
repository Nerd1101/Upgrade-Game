
package upgrade.game;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


public class Spawner {
    
    ImageView bad1;
    ImageView bad2;
    ImageView bad3;
    Pane right;
    PathTransition pathTransition;
    ParallelTransition sq;
    BadGuy bad;
    PathTransition qwer;
    
    private BadGuy[] baddies;
    
    public Spawner(int num){
        this.baddies = new BadGuy[num];
    }
    
    public void create() throws InterruptedException{
                
        bad1 = new ImageView(new Image(getClass().getResourceAsStream("bad1.png")));
        bad2 = new ImageView(new Image(getClass().getResourceAsStream("bad2.png")));
        bad3 = new ImageView(new Image(getClass().getResourceAsStream("bad3.png")));
        
        
        Path path = new Path();
        MoveTo moveto = new MoveTo(25,-25);
        LineTo line = new LineTo(25,25);
        LineTo line1 = new LineTo(460,25);
        LineTo line2 = new LineTo(460,75);
        LineTo line3 = new LineTo(25,75);
        LineTo line4 = new LineTo(25,125);
        LineTo line5 = new LineTo(460,125);
        LineTo line6 = new LineTo(460,175);
        LineTo line7 = new LineTo(25,175);
        LineTo line8 = new LineTo(25,225);
        LineTo line9 = new LineTo(460,225);
        LineTo line10 = new LineTo(460,275);
        LineTo line11 = new LineTo(25,275);
        LineTo line12 = new LineTo(25,325);
        LineTo line13 = new LineTo(460,325);
        LineTo line14 = new LineTo(460,375);
        LineTo line15 = new LineTo(25,375);
        LineTo line16 = new LineTo(25,425);
        LineTo line17 = new LineTo(460,425);
        LineTo line18 = new LineTo(460,500);
        path.getElements().add(moveto); 
        path.getElements().addAll(line,line1,line2,line3,line4,line5,line6,line7,line8,line9,line10,line11,line12,line13,line14,line15,line16,line17,line18);
        
        for(int i = 0; i < baddies.length; i++){
            BadGuy badd = new BadGuy(bad1,1);
            baddies[i] = badd;
            right = UpgradeGame.getPane();
            right.getChildren().add(badd.img);
            qwer = new PathTransition(Duration.seconds(20),path,badd.img);
            sq = new ParallelTransition(qwer);
            sq.play();
        }
        
    }
    
    public ImageView getBadGuy(int a){
        return baddies[a].img;
    }
    
    public void stop(){
        sq.pause();
    }
    
    public void go(){
       sq.play();
    }
    
}
