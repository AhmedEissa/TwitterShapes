
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Random;


public class Background {
    private Random randomize = new Random();
    private Stage stage = null;
    private Group group = null;

    public void start(Stage primaryStage) {

        int sceneWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
        int sceneHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        stage = primaryStage;

        group = new Group();
        Scene scene = new Scene(group, sceneWidth, sceneHeight, Color.BLACK);
        stage.setOpacity(0.7);
        stage.setScene(scene);
        stage.setMinWidth(sceneWidth);
        stage.setMinHeight(sceneHeight);
        stage.setMaxWidth(sceneWidth);
        stage.setMaxHeight(sceneHeight);
        scene.getStylesheets().add(Background.class.getResource("shield.css").toExternalForm());
        scene.getStylesheets().add(Background.class.getResource("bubble.css").toExternalForm());
        scene.getStylesheets().add(Background.class.getResource("bulb.css").toExternalForm());
        scene.getStylesheets().add(Background.class.getResource("face.css").toExternalForm());
        scene.getStylesheets().add(Background.class.getResource("megaphone.css").toExternalForm());

        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();
    }

    void addBubble(String author, String text, String shape) {
        String shapeSet;
        Pane pane = new Pane();
        pane.setPadding(new Insets(10, 10, 10, 10));

        switch (shape) {
            case "bubble":
                pane = setSize(pane, "bubble");
                pane.getStyleClass().add("bubble");
                shapeSet = "bubble";
                break;
            case "empty":
                int number = (int) (Math.random() * 4);
                switch (number) {
                    case 0:
                        pane = setSize(pane, "shield");
                        pane.getStyleClass().add("shield");
                        shapeSet = "shield";
                        break;
                    case 1:
                        pane = setSize(pane, "bulb");
                        pane.getStyleClass().add("bulb");
                        shapeSet = "bulb";
                        break;
                    case 2:
                        pane = setSize(pane, "face");
                        pane.getStyleClass().add("face");
                        shapeSet = "face";
                        break;
                    case 3:
                        pane = setSize(pane, "megaphone");
                        pane.getStyleClass().add("megaphone");
                        shapeSet = "megaphone";
                        break;
                    default:
                        pane = setSize(pane, "shield");
                        pane.getStyleClass().add("shield");
                        shapeSet = "shield";
                        break;
                }
                break;
            default:
                pane = setSize(pane, "shield");
                pane.getStyleClass().add("shield");
                shapeSet = "shield";
                break;

        }


        pane.setRotate(randomize.nextInt(25 + 1 + 25) - 25);
        Label user = new Label();
        Label tweet = new Label();
        user.relocate(35,70);
        user.setText(author);
        user.setMinWidth(130);
        user.setWrapText(true);
        user.setTextFill(Color.BLACK);
        tweet.setMaxWidth(150);
        tweet.relocate(168,55);
        tweet.setWrapText(true);
        tweet.setTextFill(Color.WHITE);
        tweet.setText(text);

        if (!shape.equals("empty")) {
            user.setStyle("" +
                    "-fx-background-radius: 10px;" +
                    "-fx-background-color: white; " +
                    "-fx-padding: 10px;");
            tweet.setStyle("" +
                    "-fx-background-radius: 10px;" +
                    "-fx-background-color: black; " +
                    "-fx-padding: 10px;");
        }


        switch (shapeSet) {
            case "shield":
                pane.getChildren().add(user);
                pane.getChildren().add(tweet);
                break;
            case "bubble":
                pane.getChildren().add(user);
                pane.getChildren().add(tweet);
                break;
        }

        new Scene(pane, pane.getWidth(), pane.getHeight());



        group.getChildren().add(pane);
        WritableImage image = pane.snapshot(null, null);
        group.getChildren().remove(pane);
        ImageView imageView = new ImageView(image);

        double x = getRandomInt((int) (stage.getScene().getWidth())) / 1.4;

        imageView.setLayoutX(x);
        imageView.setLayoutY(stage.getScene().getHeight());
        group.getChildren().add(imageView);
        animation(imageView,group);
    }
    private Pane setSize(Pane pane, String shape) {
        switch (shape) {
            case "shield":
                pane.setMinHeight(350);
                pane.setMinWidth(300);
                break;
            case "bubble":
                pane.setMinHeight(300);
                pane.setMinWidth(350);
                break;
            case "bulb":
                pane.setMinHeight(350);
                pane.setMinWidth(300);
                break;
            case "megaphone":
                pane.setMinHeight(300);
                pane.setMinWidth(350);
                break;
            case "face":
                pane.setMinHeight(350);
                pane.setMinWidth(350);
                break;
        }
        return pane;
    }

    public int getRandomInt(int range) {
        int randomInt = randomize.nextInt() % range;
        if (randomInt < 0) {
            randomInt = -randomInt;
        }
        return randomInt;
    }

    private void animation(ImageView grid, Group group) {
        Thread thread = new Thread(() -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),
                    (ActionEvent t) -> {
                        if(grid.getLayoutY()==0){
                            group.getChildren().remove(grid);

                        }else{
                            grid.setLayoutY(grid.getLayoutY()-2);

                        }
                    }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });

        thread.start();


    }
}