package ie.freetime.reddwarf.Models;

/**
 * Created by flyin on 18/09/2017.
 */

public class GameModel {

    public GameModel() {
    }

    String timestamp;
    String difficulty;
    String timeTaken;
    String timeLeft;

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    String bonus;
    String rightString;
    String wrongString;
    String passString;
    String scoreString;

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    String completionStatus;
    int score, right, wrong, pass, totalSeconds;

    public GameModel(String timestamp, String difficulty, String timeTaken, String timeLeft, String bonus,
                     String rightString, String wrongString, String passString, String scoreString,
                     String completionStatus, int score, int right, int wrong, int pass, int totalSeconds) {
        this.timestamp = timestamp;
        this.difficulty = difficulty;
        this.timeTaken = timeTaken;
        this.timeLeft = timeLeft;
        this.bonus = bonus;
        this.rightString = rightString;
        this.wrongString = wrongString;
        this.passString = passString;
        this.scoreString = scoreString;
        this.completionStatus = completionStatus;
        this.score = score;
        this.right = right;
        this.wrong = wrong;
        this.pass = pass;
        this.totalSeconds = totalSeconds;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
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

    public String getRightString() {
        return rightString;
    }

    public void setRightString(String rightString) {
        this.rightString = rightString;
    }

    public String getWrongString() {
        return wrongString;
    }

    public void setWrongString(String wrongString) {
        this.wrongString = wrongString;
    }

    public String getPassString() {
        return passString;
    }

    public void setPassString(String passString) {
        this.passString = passString;
    }

    public String getScoreString() {
        return scoreString;
    }

    public void setScoreString(String scoreString) {
        this.scoreString = scoreString;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getTotalSeconds() {
        return totalSeconds;
    }

    public void setTotalSeconds(int totalSeconds) {
        this.totalSeconds = totalSeconds;
    }
}
