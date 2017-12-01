package ie.freetime.reddwarf.Models;

/**
 * Created by flyin on 18/09/2017.
 */

public class LeaderBoardModel {

    public LeaderBoardModel(){

    }

    String user, score, bonus, total, timeTaken, timeLeft, rightAnswers, wrongAnswers, passes;

    public int getTotalSeconds() {
        return totalSeconds;
    }

    public void setTotalSeconds(int totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    int totalSeconds;

    public LeaderBoardModel(String user, String score, String bonus, String total, String timeTaken,
                            String timeLeft, String rightAnswers, String wrongAnswers, String passes,
                            int totalSeconds) {
        this.user = user;
        this.score = score;
        this.bonus = bonus;
        this.total = total;
        this.timeTaken = timeTaken;
        this.timeLeft = timeLeft;
        this.rightAnswers = rightAnswers;
        this.wrongAnswers = wrongAnswers;
        this.passes = passes;
        this.totalSeconds = totalSeconds;
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

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(String rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public String getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(String wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public String getPasses() {
        return passes;
    }

    public void setPasses(String passes) {
        this.passes = passes;
    }


}
