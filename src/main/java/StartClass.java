import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartClass extends Application {

    private Background main = null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        if (main == null) {
            main = new Background();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent o) {
                    System.exit(-1);
                }
            });
            main.start(primaryStage);
            TwitterCaller shape = new TwitterCaller(main);
            shape.scheduleBubbleTasks();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
