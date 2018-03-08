import javafx.application.Platform;

import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by IntelliJ IDEA.
 * User: sthirumuru
 * Date: 5/26/14
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class UIUpdateTimerTask extends TimerTask {
    public void run() {
        Platform.runLater(() -> {
            try {
                uiUpdate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void uiUpdate() throws IOException {

    }
}
