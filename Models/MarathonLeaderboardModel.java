package ie.freetime.reddwarf.Models;

/**
 * Created by flyin on 20/09/2017.
 */

public class MarathonLeaderboardModel {

    String user, timestamp, timeTaken, score, right, wrong, passes, type;

    public MarathonLeaderboardModel() {
    }

    public MarathonLeaderboardModel(String user, String timestamp, String timeTaken, String score, String right, String wrong, String passes, String type) {
        this.user = user;
        this.timestamp = timestamp;
        this.timeTaken = timeTaken;
        this.score = score;
        this.right = right;
        this.wrong = wrong;
        this.passes = passes;
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getWrong() {
        return wrong;
    }

    public void setWrong(String wrong) {
        this.wrong = wrong;
    }

    public String getPasses() {
        return passes;
    }

    public void setPasses(String passes) {
        this.passes = passes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
