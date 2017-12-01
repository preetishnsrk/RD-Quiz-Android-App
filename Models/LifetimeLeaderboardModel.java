package ie.freetime.reddwarf.Models;

import ie.freetime.reddwarf.Fragments.LeaderBoardFragments.LifetimeLeaderboard;

/**
 * Created by flyin on 19/09/2017.
 */

public class LifetimeLeaderboardModel {
    String user;
    String score;

    public String getGameCount() {
        return gameCount;
    }

    public void setGameCount(String gameCount) {
        this.gameCount = gameCount;
    }

    String gameCount;

    public LifetimeLeaderboardModel(){
    }

    public LifetimeLeaderboardModel(String user, String score, String gameCount) {
        this.user = user;
        this.score = score;
        this.gameCount = gameCount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


}
