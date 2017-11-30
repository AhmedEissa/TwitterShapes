import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Created by Matthew on 14/01/2017.
 */
public class PaneControl {

    private Pane panel;
    private boolean increased = false;
    private Label user;
    private Label tweet;
    private String userName;
    private String tweetText;

    public PaneControl() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public void setUser(Label user) {
        this.user = user;
    }

    public void setTweet(Label tweet) {
        this.tweet = tweet;
    }

    public void setPanel(Pane panel) {
        this.panel = panel;
    }
}
