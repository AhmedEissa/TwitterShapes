
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import twitter4j.*;

import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

class TwitterCaller extends Timer {

    private ArrayList<Status> tweets;
    private Random randomize = new Random();
    private Background frame = null;

    TwitterCaller(Background frame) {
        this.frame=frame;
    }

    void scheduleBubbleTasks() {
        schedule(twitterSearch(), Calendar.getInstance().getTime(), 40000);

        schedule(getAddBubblesTask(), Calendar.getInstance().getTime(), 3000);


    }

    private TimerTask getAddBubblesTask() {
        return new UIUpdateTimerTask() {
            @Override
            public void uiUpdate() throws IOException {
                int randomNumber = frame.getRandomInt(100);
              if (randomNumber > 0 && randomNumber<=49) {
                    Status tweet;
                    int size = tweets.size();
                    synchronized (this) {
                        tweet = tweets.get(randomize.nextInt(size));
                        tweet.getUser().getProfileImageURL();

                    }

                  ImageView imageView = ImageViewBuilder.create()
                          .image(new Image(tweet.getUser().getBiggerProfileImageURL()))
                          .build();

                    frame.addBubble("@" + tweet.getUser().getScreenName(), tweet.getText(), "bubble",imageView);

                } else if (randomNumber > 50) {
                    frame.addBubble("", "", "empty",null);
                }
            }
        };
    }

    private TimerTask twitterSearch() {
        return new TimerTask() {
            @Override
            public void run() {
                Twitter twitter = new TwitterFactory().getInstance();
                try {
                    //synchronized (this) {
                    Query query = new Query("#trump");
                    //Query query1 = new Query("#MiddlesexUniversity");
//                        Query query2 = new Query("#YourMiddlesexYourVoice");
//                        Query query3 = new Query("#MDXNSS");
//                        Query query4 = new Query("#MiddlesexNSS");

                    QueryResult result = twitter.search(query);

                    //QueryResult result1 = twitter.search(query1);
//                        QueryResult result2 = twitter.search(query2);
//                        QueryResult result3 = twitter.search(query3);
//                        QueryResult result4 = twitter.search(query4);

                    tweets = new ArrayList<>();

                    tweets.addAll(result.getTweets());

                    //tweets.addAll(result1.getTweets());
//                        tweets.addAll(result2.getTweets());
//                        tweets.addAll(result3.getTweets());
//                        tweets.addAll(result4.getTweets());

                    //}

                } catch (TwitterException te) {
                    te.printStackTrace();
                }
            }
        };
    }






}
