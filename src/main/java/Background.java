
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Random;


class Background {
    private Random randomize = new Random();
    private Stage stage = null;
    private Group group = null;
    private static double xOffset = 0;
    private static double yOffset = 0;

    public void start(Stage primaryStage) {

        int sceneWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
        //int sceneWidth = (int) Screen.getScreens().get(1).getVisualBounds().getWidth();//.getPrimary().getVisualBounds().getWidth();
        int sceneHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();
        //int sceneHeight = (int) Screen.getScreens().get(1).getVisualBounds().getHeight();
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
        scene.getStylesheets().add(Background.class.getResource("bubble.css").toExternalForm());


        scene.setOnMousePressed(event -> {
            xOffset = primaryStage.getX() - event.getScreenX();
            yOffset = primaryStage.getY() - event.getScreenY();
        });
        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + xOffset);
            primaryStage.setY(event.getScreenY() + yOffset);
        });

        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();
    }

    void addBubble(String author, String text, String shape,ImageView userImage) {
        Image image = null;
        Pane pane = new Pane();

        switch (shape) {
            case "bubble":
                pane = setSize(pane, "bubble");
                pane.getStyleClass().add("bubble");
                break;
            case "empty":
                int number = (int) (Math.random() * 6);
                switch (number) {
                    case 0:
                        image = new Image("flower.png");
                        pane = setSize(pane, "flower");
                        break;
                    case 1:
                        image = new Image("flower1.png");
                        pane = setSize(pane, "flower1");
                        break;
                    case 2:
                        image = new Image("heart.png");
                        pane = setSize(pane, "heart");
                        break;
                    case 3:
                        image = new Image("star.png");
                        pane = setSize(pane, "star");
                        break;
                }
                break;
            default:
                pane = setSize(pane, "shield");
                pane.getStyleClass().add("shield");
                break;
        }


        pane.setRotate(randomize.nextInt(25 + 1 + 25) - 25);
        double x = getRandomInt((int) (stage.getScene().getWidth())) / 1.4;
        ImageView imageView;
        if (!shape.equals("empty")) {
            pane = addLabels(pane, author, text,userImage);
            new Scene(pane, pane.getWidth(), pane.getHeight());
            group.getChildren().add(pane);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            WritableImage writeImage = pane.snapshot(params, null);
            group.getChildren().remove(pane);
            imageView = new ImageView(writeImage);
            imageView.setLayoutX(x);
            imageView.setLayoutY(-400);
        } else {
            imageView = new ImageView(image);
            imageView.setLayoutX(x);
            imageView.setLayoutY(-400);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
        }

        group.getChildren().add(imageView);
        animation(imageView, group);
    }

    private Pane addLabels(Pane pane, String author, String text,ImageView imageView) {
        Label user = new Label();
        Label tweet = new Label();

        user.relocate(55, 160);
        user.setText(author);
        user.setMinWidth(90);
        user.setWrapText(true);
        user.setTextFill(Color.BLACK);
        user.setStyle("" +
                "-fx-background-radius: 10px;" +
                "-fx-background-color: white; " +
                "-fx-padding: 10px;");

        tweet.setMaxWidth(150);
        tweet.relocate(160, 55);
        tweet.setWrapText(true);
        tweet.setTextFill(Color.WHITE);
        tweet.setText(text);
        tweet.setStyle("" +
                "-fx-background-radius: 10px;" +
                "-fx-background-color: black; " +
                "-fx-padding: 10px;");

        imageView.relocate(55,55);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);


        pane.getChildren().add(user);
        pane.getChildren().add(tweet);
        pane.getChildren().add(imageView);
        return pane;
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
            case "snow":
                pane.setMinHeight(350);
                pane.setMinWidth(350);
                break;
            case "rudolph":
                pane.setMinHeight(350);
                pane.setMinWidth(350);
                break;
            case "flower":
                pane.setMinHeight(350);
                pane.setMinWidth(350);
                break;
            case "flower1":
                pane.setMinHeight(300);
                pane.setMinWidth(350);
                break;
            case "heart":
                pane.setMinHeight(350);
                pane.setMinWidth(300);
                break;
            case "star":
                pane.setMinHeight(350);
                pane.setMinWidth(300);
                break;
        }
        return pane;
    }

    int getRandomInt(int range) {
        int randomInt = randomize.nextInt() % range;
        if (randomInt < 0) {
            randomInt = -randomInt;
        }
        return randomInt;
    }

    private void animation(ImageView image, Group group) {
        Thread thread = new Thread(() -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50),
                    (ActionEvent t) -> {
                        if (image.getLayoutY() == stage.getScene().getHeight() + 300) {
                            group.getChildren().remove(image);
                        } else {
                            image.setLayoutY(image.getLayoutY() + 2);
                        }
                    }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });
        thread.start();
    }
}