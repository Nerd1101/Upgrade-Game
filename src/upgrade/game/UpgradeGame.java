
package upgrade.game;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import static javafx.scene.text.TextAlignment.CENTER;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;


public class UpgradeGame extends Application {
    
    //BUTTONS
    Button start;
    Button pause;
    int level;
    Label labellevel;
    Label coinslabel;
    ImageView lbg;
    ImageView rbg;
    ImageView bad1;
    ImageView bad2;
    ImageView bad3;
    ImageView coin;
    ImageView icoins;
    ImageView life1;
    ImageView life2;
    ImageView life3;
    ImageView galaxaga;
    static ImageView shot;
    static ImageView shot2;
    ImageView explode;
    
    //PANES
    VBox left;
    static Pane right;
    HBox top;
    HBox livesbox;
    StackPane coinsbox;
    BorderPane borderPane;
    SplitPane splitpane;
    StackPane lstack;
    StackPane rstack;
    Scene scene;
    Stage window;
    
    //STUFF
    Player p;
    BadGuy bad;
    boolean playing;
    boolean paused;
    PathTransition pathTransition;
    Spawner spawn;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        p = new Player();
        playing = false;
        paused = false;
        
        //BUTTONS
        start = new Button("Start");
        pause = new Button("Pause");
        start.setPrefSize(100,100);
        pause.setPrefSize(100,100);
        pause.setVisible(false);
        pause.setDisable(true);
        
        //OTHERS
        level = 1;
        labellevel = new Label("LEVEL: "+level);
        coinslabel = new Label(""+p.getCoins());
        coinslabel.setStyle("-fx-font-weight: bold;-fx-font-size: 15px;");
        
        //IMAGES
        lbg = new ImageView(new Image(getClass().getResourceAsStream("grey.jpg")));
        galaxaga = new ImageView(new Image(getClass().getResourceAsStream("galaxaga.png")));
        rbg = new ImageView(new Image(getClass().getResourceAsStream("color.jpg")));
        coin = new ImageView(new Image(getClass().getResourceAsStream("coin.png")));
        icoins = new ImageView(new Image(getClass().getResourceAsStream("coins.png")));
        life1 = new ImageView(new Image(getClass().getResourceAsStream("playermini.png")));
        life2 = new ImageView(new Image(getClass().getResourceAsStream("playermini.png")));
        life3 = new ImageView(new Image(getClass().getResourceAsStream("playermini.png")));
        shot = new ImageView(new Image(getClass().getResourceAsStream("shot.png")));
        shot2 = new ImageView(new Image(getClass().getResourceAsStream("shot.png")));
        bad1 = new ImageView(new Image(getClass().getResourceAsStream("bad1.png")));
        bad2 = new ImageView(new Image(getClass().getResourceAsStream("bad2.png")));
        bad3 = new ImageView(new Image(getClass().getResourceAsStream("bad3.png")));
        explode = new ImageView(new Image(getClass().getResourceAsStream("explode.png")));
        lbg.setFitWidth(120);
        lbg.setFitHeight(500);
        rbg.setFitWidth(500);
        rbg.setFitHeight(500);
        
        bad = new BadGuy(bad1,1);
        
        
        //SCENE
        coinsbox = new StackPane();
        livesbox = new HBox();
        left = new VBox();
        right = new Pane();
        top = new HBox();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(livesbox,labellevel);
        livesbox.getChildren().addAll(life1,life2,life3);
        coinsbox.setMinWidth(52);
        coinsbox.setAlignment(Pos.CENTER);
        coinsbox.getChildren().addAll(icoins,coinslabel);
        galaxaga.setFitWidth(500);
        galaxaga.setFitHeight(50);
        top.setPrefHeight(50);
        top.getChildren().addAll(vbox,coinsbox,galaxaga);
        left.setPadding(new Insets(10,10,10,10));
        right.setPadding(new Insets(10,10,10,10));
        left.setSpacing(5);
        right.getChildren().addAll(p.image,bad.img,start);
        start.setTranslateX(180);
        start.setTranslateY(160);
        lstack = new StackPane(lbg);
        rstack = new StackPane(rbg);
        splitpane = new SplitPane(lstack,rstack);
        splitpane.setOrientation(Orientation.HORIZONTAL);
        borderPane = new BorderPane(splitpane);
        borderPane.setTop(top);
        scene = new Scene(borderPane, 600, 500);
        
        //ADD
        lstack.getChildren().add(left);
        rstack.getChildren().add(right);
        left.getChildren().addAll(pause);
        
        //EVENTS
        Timeline timeline  = new Timeline(); 
        Timeline timeline2  = new Timeline(); 
        scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case LEFT:
                        if(p.getXpos()<-25){
                            p.setXpos(450.0);
                        }
                        KeyFrame frame = new KeyFrame(Duration.millis(2000),new KeyValue(p.image.xProperty(),-500));
                        timeline.getKeyFrames().addAll(frame);
                        timeline.playFromStart();
                    break;
                    case RIGHT: 
                        if(p.getXpos()>440.0){
                            p.setXpos(-30.0);
                        }
                        KeyFrame frame2 = new KeyFrame(Duration.millis(2000),new KeyValue(p.image.xProperty(),1000));
                        timeline2.getKeyFrames().addAll(frame2);
                        timeline2.playFromStart();
                    break;
                    case UP:
                        shootUp(bad);
                    break;
                }
            });
        
        scene.setOnKeyReleased(e ->{
            switch (e.getCode()){
                case LEFT:timeline.stop();break;
                case RIGHT:timeline2.stop();break;
            }
            
        });

        start.setOnAction(e ->{
            start.setDisable(true);
            start.setVisible(false);
            pause.setDisable(false);
            pause.setVisible(true);
            play();
        });
        
        pause.setOnAction(e ->{pause();});
        
        
        //COIN UPDATER
        Timer timer = new Timer();
        int count = 0;
        timer.schedule(new TimerTask() { // timer task to update the seconds
            public void run() {
                // use Platform.runLater(Runnable runnable) If you need to update a GUI component from a non-GUI thread.
                Platform.runLater(new Runnable() { 
                    public void run() {
                        coinslabel.setText(""+p.getCoins());
                        if (count >= 10){timer.cancel();}
        }});}}, 100, 100);
        
        //generalPop("Welcome to Galaxaga\nPlay, collect coins, buy upgrades!");
        
        
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        
        window.setTitle("Upgrade Galaga");
        window.setScene(scene);
        window.show();
        window.setResizable(false);
    }
    
    public void shootUp(BadGuy b){
        shot = new ImageView(new Image(getClass().getResourceAsStream("shot.png")));
        shot2 = new ImageView(new Image(getClass().getResourceAsStream("shot.png")));
        right.getChildren().addAll(shot,shot2);
        shot.setX(p.image.getX()+4);
        shot.setY(380);
        shot2.setX(p.image.getX()+50);
        shot2.setY(380);
        
        KeyValue key = new KeyValue(shot.yProperty(),-30);
        KeyValue key2 = new KeyValue(shot2.yProperty(),-30);
        KeyFrame frame = new KeyFrame(Duration.millis(1000),key,key2);
        Timeline timeline  = new Timeline(); 
        timeline.getKeyFrames().addAll(frame);
        timeline.play();
        
        //COLLISION
Thread t = new Thread(){
        @Override
        public void run(){
            while(true){               
                try {
                        Thread.sleep(200);
                        if (shot.getBoundsInParent().intersects(spawn.getBadGuy(0).getBoundsInParent())) {
                            spawn.getBadGuy(0).setImage(new Image(getClass().getResourceAsStream("explode.png")));
                            Timer timer = new Timer();
                            int count = 0;
                            timer.schedule(new TimerTask() { // timer task to update the seconds
                            public void run() {
                            // use Platform.runLater(Runnable runnable) If you need to update a GUI component from a non-GUI thread.
                            Platform.runLater(new Runnable() { 
                                public void run() {
                                    right.getChildren().remove(spawn.getBadGuy(0));
                                    if (count > 0){timer.cancel();}
                                }});}}, 300, 300);
                                    }
                        if(shot2.getBoundsInParent().intersects(spawn.getBadGuy(0).getBoundsInParent())){
                            spawn.getBadGuy(0).setImage(new Image(getClass().getResourceAsStream("explode.png")));
                            if(right.getChildren().contains(spawn.getBadGuy(0))){
                                right.getChildren().remove(spawn.getBadGuy(0));
                            }
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UpgradeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    t.start();
        
    }
    
    public static ImageView getShot1(){
        return shot;
    }
    
    public static ImageView getShot2(){
        return shot2;
    }
    
    
    
    public void play(){
        playing = true;
        spawn = new Spawner(1);
        try {
            spawn.create();
        } catch (InterruptedException ex) {
            Logger.getLogger(UpgradeGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pause(){
        paused = true;
        spawn.stop();
        ImageView pausepic = new ImageView(new Image(getClass().getResourceAsStream("pause.jpg")));
        ImageView cont = new ImageView(new Image(getClass().getResourceAsStream("continue.png")));
        cont.setX(100);
        cont.setY(350);
        pausepic.setFitHeight(500);
        pausepic.setFitWidth(500);
        playing = false;
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setAlwaysOnTop(true);
        Pane vbox = new Pane();
        vbox.setPrefSize(500,500);
        cont.setOnMouseClicked(f ->{
            stage.close();
            playing = true;
            spawn.go();
        });
        vbox.getChildren().addAll(pausepic,cont);
        Scene scene1 = new Scene(vbox);
        stage.setResizable(false);
        stage.setScene(scene1);
        stage.initOwner(window);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
    
    public void upgrade(){
        
    }
    
    public static Pane getPane(){
        return right;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void generalPop(String s){
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setAlwaysOnTop(true);
        VBox vbox = new VBox();
        vbox.setPrefSize(100,100);
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Text y = new Text(s);
        vbox.setAlignment(Pos.CENTER);
        y.setTextAlignment(CENTER);
        Button btn = new Button("OK");
        btn.setPrefSize(50,50);
        btn.setOnAction(e ->{
            stage.close();
        });
        vbox.getChildren().addAll(y,btn);
        Scene scene1 = new Scene(vbox);
        stage.setResizable(false);
        stage.setScene(scene1);
        stage.initOwner(window);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
}
