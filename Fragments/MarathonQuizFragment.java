package ie.freetime.reddwarf.Fragments;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;

import ie.freetime.reddwarf.Models.GameModel;
import ie.freetime.reddwarf.Models.MarathonLeaderboardModel;
import ie.freetime.reddwarf.Models.QuestionsModel;
import ie.freetime.reddwarf.R;

import static ie.freetime.reddwarf.MainActivity.backStackCount;
import static ie.freetime.reddwarf.Models.Seeds.easyQuestionsArray;
import static ie.freetime.reddwarf.Models.Seeds.hardQuestionsArray;
import static ie.freetime.reddwarf.Models.Seeds.mediumQuestionsArray;
import static ie.freetime.reddwarf.Models.Seeds.populateEasyArray;
import static ie.freetime.reddwarf.Models.Seeds.populateHardArray;
import static ie.freetime.reddwarf.Models.Seeds.populateMediumArray;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarathonQuizFragment extends Fragment implements View.OnClickListener {


    DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    String TAG = "MarathonQuizFragment";

    ArrayList<QuestionsModel> marathonQuestionsArray;

    public static LinearLayout marathonIntroLayout, marathonCountdownLayout;
    public static RelativeLayout marathonMainLayout, marathonResultsLayout;
    ProgressBar countdownProgressBar;
    public static CountUpTimer countUpTimer;

    NestedScrollView quizLayout;

    public static Button exitButton;
    Button fishButton, restartButton, marathonStartButton, answer1Button, answer2Button,
            answer3Button, answer4Button, answer5Button;

    TextView questionNumberTextView, scoreTextView, questionTextView, correctAnswersTV, incorrectAnswersTV,
            passedTV, timeLeftTV, scoreTV, finalTV, timeCounterTV, outcomeTV, completedInTV, timeLeftResultsTV,
            bonusPointsTV;

    int questionNumber = 0;
    int score = 0;

    int correctAnswers = 0;
    int wrongAnswers = 0;
    int timesPassed = 0;

    int time = 180;
    int modifiedTime = 0;

    int gameTimeTracker = 0;

    int minutesTracker = 0;
    int secondsTracker = 0;

    QuestionsModel thisQuestion;
    ArrayList<String> questionHistory = new ArrayList<>();

    ViewAnimator viewAnimator;
    Animation fadeIn, fadeOut;

    ImageView countdown4, countdown3, countdown2, countdown1;

    public MarathonQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_marathon_quiz, container, false);

        marathonIntroLayout = (LinearLayout) v.findViewById(R.id.marathonWelcomeLayout);
        marathonMainLayout = (RelativeLayout) v.findViewById(R.id.marathonMainLayout);
        marathonResultsLayout = (RelativeLayout) v.findViewById(R.id.marathonResultsLayout);

        viewAnimator = (ViewAnimator) v.findViewById(R.id.viewAnimator);
        fadeIn = AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_out);
        viewAnimator.setInAnimation(fadeIn);
        viewAnimator.setOutAnimation(fadeOut);
        marathonCountdownLayout = (LinearLayout) v.findViewById(R.id.marathonCountdownLayout);

        quizLayout = (NestedScrollView)v.findViewById(R.id.quizLayout);

        questionNumberTextView = (TextView) v.findViewById(R.id.questionNumberTV);
        questionTextView = (TextView) v.findViewById(R.id.questionTV);
        scoreTextView = (TextView) v.findViewById(R.id.scoreTV);

        countdown4 = (ImageView) v.findViewById(R.id.countdown4);
        Glide.with(this).load(Color.BLACK).into(countdown4);
        countdown3 = (ImageView) v.findViewById(R.id.countdown3);
        Glide.with(this).load(R.drawable.countdown3).into(countdown3);
        countdown2 = (ImageView) v.findViewById(R.id.countdown2);
        Glide.with(this).load(R.drawable.countdown2).into(countdown2);
        countdown1 = (ImageView) v.findViewById(R.id.countdown1);
        Glide.with(this).load(R.drawable.countdown1).into(countdown1);

        correctAnswersTV = (TextView) v.findViewById(R.id.correctAnswersTV);
        incorrectAnswersTV = (TextView) v.findViewById(R.id.incorrectAnswersTV);
        passedTV = (TextView) v.findViewById(R.id.passedTV);
        timeLeftTV = (TextView) v.findViewById(R.id.timeLeftResultsTV);
        scoreTV = (TextView) v.findViewById(R.id.resultsScoreTV);
        timeCounterTV = (TextView) v.findViewById(R.id.timeCounterTV);
        finalTV = (TextView) v.findViewById(R.id.finalTV);
        outcomeTV = (TextView) v.findViewById(R.id.outcomeTV);
        completedInTV = (TextView) v.findViewById(R.id.completedInTV);
        timeLeftResultsTV = (TextView) v.findViewById(R.id.timeLeftResultsTV);
        bonusPointsTV = (TextView) v.findViewById(R.id.bonusPointsTV);

        answer1Button = (Button) v.findViewById(R.id.answer1Button);
        answer2Button = (Button) v.findViewById(R.id.answer2Button);
        answer3Button = (Button) v.findViewById(R.id.answer3Button);
        answer4Button = (Button) v.findViewById(R.id.answer4Button);
        answer5Button = (Button) v.findViewById(R.id.answer5Button);

        fishButton = (Button) v.findViewById(R.id.fishButton);
        exitButton = (Button) v.findViewById(R.id.exitButton);
        restartButton = (Button) v.findViewById(R.id.restartButton);
        marathonStartButton = (Button) v.findViewById(R.id.marathonStartButton);

        answer1Button.setOnClickListener(this);
        answer2Button.setOnClickListener(this);
        answer3Button.setOnClickListener(this);
        answer4Button.setOnClickListener(this);
        answer5Button.setOnClickListener(this);

        fishButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
        restartButton.setOnClickListener(this);
        marathonStartButton.setOnClickListener(this);

        countUpTimer = new CountUpTimer(1000) {
            @Override
            public void onTick(long elapsedTime) {
                int seconds = (int) (elapsedTime/1000);
                int minutes = seconds/60;
                minutesTracker = minutes;
                int displaySeconds = seconds - (minutes*60);
                secondsTracker = displaySeconds;

                timeCounterTV.setText("Time: "+String.format("%02d",minutes)+
                        ":"+String.format("%02d",displaySeconds));
               // timeCounterTV.setText("Time elapsed: "+elapsedTime/1000);
                gameTimeTracker++;
            }
        };

        return v;
    }


    public abstract class CountUpTimer{
        private final long interval;
        private long base;

        public CountUpTimer(long interval) {
            this.interval = interval;
        }

        public void start() {
            base = SystemClock.elapsedRealtime();
            handler.sendMessage(handler.obtainMessage(MSG));
        }

        public void stop() {
            handler.removeMessages(MSG);
        }

        public void reset() {
            synchronized (this) {
                base = SystemClock.elapsedRealtime();
            }
        }

        abstract public void onTick(long elapsedTime);

        private static final int MSG = 1;

        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                synchronized (CountUpTimer.this) {
                    long elapsedTime = SystemClock.elapsedRealtime() - base;
                    onTick(elapsedTime);
                    sendMessageDelayed(obtainMessage(MSG), interval);
                }
            }
        };
    }



    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(v.getId()){
            case R.id.marathonStartButton:
                marathonIntroLayout.setVisibility(View.GONE);
                marathonCountdownLayout.setVisibility(View.VISIBLE);

                populateEasyArray();
                populateMediumArray();
                populateHardArray();

                marathonQuestionsArray = new ArrayList<>();

                marathonQuestionsArray.addAll(easyQuestionsArray);
                marathonQuestionsArray.addAll(mediumQuestionsArray);
                marathonQuestionsArray.addAll(hardQuestionsArray);

                CountDownTimer gameStartTimer = new CountDownTimer(3500,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        viewAnimator.showNext();
                    }

                    @Override
                    public void onFinish() {

                        questionNumber = 0;
                        correctAnswers = 0;
                        wrongAnswers = 0;
                        timesPassed = 0;
                        score = 0;
                        gameTimeTracker = 0;
                        countUpTimer = new CountUpTimer(1000) {
                            @Override
                            public void onTick(long elapsedTime) {
                                int seconds = (int) (elapsedTime / 1000);
                                int minutes = seconds / 60;
                                minutesTracker = minutes;
                                int displaySeconds = seconds - (minutes * 60);
                                secondsTracker = displaySeconds;

                                timeCounterTV.setText("Time: " + String.format("%02d", minutes) +
                                        ":" + String.format("%02d", displaySeconds));
                                // timeCounterTV.setText("Time elapsed: "+elapsedTime/1000);
                                gameTimeTracker++;
                            }
                        };
                        countUpTimer.start();


                        questionHistory.clear();

                        marathonCountdownLayout.setVisibility(View.GONE);
                        marathonMainLayout.setVisibility(View.VISIBLE);
                        countUpTimer.start();
                        playTheGame();
                    }
                };
                gameStartTimer.start();



                break;

            case R.id.exitButton:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle(Html.fromHtml("<font color='#FFFFFF'>Exit Game</font>"));
                builder.setMessage(Html.fromHtml("<font color='#FFFFFF'>Exit game and lose progress?</font>"));
                builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        countUpTimer.stop();
                        MarathonQuizFragment marathonQuizFragment = new MarathonQuizFragment();
                        fragmentTransaction.detach(MarathonQuizFragment.this);
                        fragmentTransaction.add(R.id.fragment_frame, marathonQuizFragment);
                        backStackCount++;
                        fragmentTransaction.commit();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.red_border);

                Button positive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                positive.setTextColor(ContextCompat.getColor(getActivity(), R.color.md_red_900));
                Button negative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                negative.setTextColor(ContextCompat.getColor(getActivity(), R.color.md_red_900));
                break;

            case R.id.restartButton:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setIcon(R.mipmap.ic_launcher);
                builder1.setTitle(Html.fromHtml("<font color='#FFFFFF'>Restart Game</font>"));
                builder1.setMessage(Html.fromHtml("<font color='#FFFFFF'>Restart game and lose progress?</font>"));

                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder1.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        questionNumber = 0;
                        score = 0;
                        gameTimeTracker = 0;
                        correctAnswers =0;
                        wrongAnswers = 0;
                        timesPassed = 0;
                        countUpTimer.reset();
                        playTheGame();
                    }
                });

                AlertDialog alertDialog1 = builder1.create();
                alertDialog1.show();
                alertDialog1.getWindow().setBackgroundDrawableResource(R.drawable.red_border);

                Button positive1 = alertDialog1.getButton(DialogInterface.BUTTON_POSITIVE);
                positive1.setTextColor(ContextCompat.getColor(getActivity(), R.color.md_red_900));
                Button negative1 = alertDialog1.getButton(DialogInterface.BUTTON_NEGATIVE);
                negative1.setTextColor(ContextCompat.getColor(getActivity(), R.color.md_red_900));
                break;

            case R.id.fishButton:
                timesPassed++;
                score = score-10;
                scoreTextView.setText("Score: "+score);
                playTheGame();
                break;

            case R.id.answer1Button:
                answerSelected(getView(),"1",answer1Button.getText().toString());
                break;

            case R.id.answer2Button:
                answerSelected(getView(), "2",answer2Button.getText().toString());
                break;

            case R.id.answer3Button:
                answerSelected(getView(), "3",answer3Button.getText().toString());
                break;

            case R.id.answer4Button:
                answerSelected(getView(), "4",answer4Button.getText().toString());
                break;

            case R.id.answer5Button:
                answerSelected(getView(), "5",answer5Button.getText().toString());
                break;

        }
    }

    private void playTheGame(){

        quizLayout.scrollTo(0,0);

        //if user hasn't yet had 50 questions present another to them, else show the results screen
        if(questionNumber<150) {
            //turning buttons on(turned off in answerSelected()) and setting colors back
            answer1Button.setClickable(true);
            answer1Button.setBackgroundResource(R.drawable.custom_button);
            answer2Button.setClickable(true);
            answer2Button.setBackgroundResource(R.drawable.custom_button);
            answer3Button.setClickable(true);
            answer3Button.setBackgroundResource(R.drawable.custom_button);
            answer4Button.setClickable(true);
            answer4Button.setBackgroundResource(R.drawable.custom_button);
            answer5Button.setClickable(true);
            answer5Button.setBackgroundResource(R.drawable.custom_button);

            //tracking question number
            questionNumber++;

            //Displaying question number and current score to user
            questionNumberTextView.setText("Question: " + questionNumber + "/150");
            scoreTextView.setText("Score: " + score);

            //Grabbing a random question from the array and making sure it hasn't already been
            //asked by checking questionHistory array
            do {
                thisQuestion = marathonQuestionsArray.get(new Random().nextInt(marathonQuestionsArray.size()));
            } while (questionHistory.contains(thisQuestion.getId()));

            //when it's an unused question the ID is added to questionHistoryArray
            questionHistory.add(thisQuestion.getId());

            //displaying question to user
            questionTextView.setText(thisQuestion.getQuestion());

            //adding all available options to array
            ArrayList<String> options = new ArrayList<>();
            options.add(thisQuestion.getOption1());
            options.add(thisQuestion.getOption2());
            options.add(thisQuestion.getOption3());
            options.add(thisQuestion.getOption4());
            options.add(thisQuestion.getOption5());

            //creating and adding correct answer to an array containing the 3 options that
            //will be presented to user
            ArrayList<String> usersOptions = new ArrayList<>();
            usersOptions.add(thisQuestion.getAnswer());

            String randomOption;

            for (int i = 0; i < 4; i++) {
                do {
                    randomOption = options.get(new Random().nextInt(options.size()));
                } while (usersOptions.contains(randomOption));

                usersOptions.add(randomOption);
            }

            //shuffling the userOptions array so that the correct answer won't always
            //be in the top button
            Collections.shuffle(usersOptions);

            answer1Button.setText(usersOptions.get(0));
            answer2Button.setText(usersOptions.get(1));
            answer3Button.setText(usersOptions.get(2));
            answer4Button.setText(usersOptions.get(3));
            answer5Button.setText(usersOptions.get(4));
        } else {
            //TODO: results screen
            countUpTimer.stop();
            showResults();
        }
    }

    private void showResults(){

        if(scoreTextView.getText().toString().contains("-")){
            outcomeTV.setText("A negative score? That's a fail, smegger...");
            finalTV.setText("Your score was not posted to the leaderboard.");
        }else{
            outcomeTV.setText("Completed!");
            finalTV.setText("Your score was added to the leaderboard.");
        }

        int rightPercent = correctAnswers*100/150;
        int wrongPercent = wrongAnswers*100/150;
        int passPercent = timesPassed*100/150;

        scoreTV.setText(score+" points awarded");
        correctAnswersTV.setText(correctAnswers+ "/150 ("+rightPercent+"%)");
        incorrectAnswersTV.setText(wrongAnswers+"/150 ("+wrongPercent+"%)");
        passedTV.setText(timesPassed+"/150 ("+passPercent+"%)");



        int completedInSeconds = gameTimeTracker;
        int completedInMinutes = completedInSeconds/60;
        String completedInString = completedInMinutes+"m"+(completedInSeconds - (completedInMinutes*60))+"s";

        completedInTV.setText(completedInString);

        marathonMainLayout.setVisibility(View.GONE);
        marathonResultsLayout.setVisibility(View.VISIBLE);

        int secondsLeft = (minutesTracker*60)+secondsTracker;


        String timestamp = getDate()+"hrs";

        GameModel gameModel = new GameModel(timestamp,"Marathon", completedInString, minutesTracker+"m"+secondsTracker+"s",null, correctAnswers+ "/150 ("+rightPercent+"%)",
                wrongAnswers+"/150 ("+wrongPercent+"%)", timesPassed+"/150 ("+passPercent+"%)", score+" points", outcomeTV.getText().toString(), score, correctAnswers, wrongAnswers,
                timesPassed, gameTimeTracker);

        if(!scoreTextView.getText().toString().contains("-")) {
            Query lifetimeScore = myDatabase.child("users").child(currentUser.getUid()).child("lifetimeScore");
            lifetimeScore.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        long thisScore = dataSnapshot.getValue(long.class);
                        long thisGameScore = (long) score;
                        long newScore = thisScore + thisGameScore;
                        myDatabase.child("users").child(currentUser.getUid()).child("lifetimeScore").setValue(newScore);
                        myDatabase.child("leaderboards").child("lifetime").child(currentUser.getUid()).child("score").setValue(newScore);
                        myDatabase.child("leaderboards").child("lifetime").child(currentUser.getUid()).child("user").setValue(currentUser.getDisplayName());
                    } else {
                        long thisGameScore = (long) score;
                        long newScore = thisGameScore;
                        myDatabase.child("users").child(currentUser.getUid()).child("lifetimeScore").setValue(newScore);
                        myDatabase.child("leaderboards").child("lifetime").child(currentUser.getUid()).child("score").setValue(newScore);
                        myDatabase.child("leaderboards").child("lifetime").child(currentUser.getUid()).child("user").setValue(currentUser.getDisplayName());
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


        myDatabase.child("users").child(currentUser.getUid()).child("gameHistory").child(timestamp).setValue(gameModel);

        if(gameModel.getCompletionStatus().equals("Completed!")) {

            String minutesLeftString = String.valueOf(minutesTracker);
            String secondsLeftString = String.valueOf(secondsTracker);
            String minutesUsedString = String.valueOf(completedInMinutes);
            String secondsUsedString = String.valueOf(completedInSeconds);

            int minsLeft = minutesLeftString.length();
            int secsLeft = secondsLeftString.length();
            int minsUsed = minutesUsedString.length();
            int secsUsed = secondsLeftString.length();

            if(minsLeft == 1){
                minutesLeftString = "0"+minutesLeftString+"m";
            } else {
                minutesLeftString = minutesLeftString+"m";
            }

            if(secsLeft == 1){
                secondsLeftString = "0"+secondsLeftString+"s";
            } else {
                secondsLeftString = secondsLeftString+"s";
            }

            if(minsUsed == 1){
                minutesUsedString = "0"+minutesUsedString+"m";
            } else {
                minutesUsedString = minutesUsedString+"m";
            }

            if(secsUsed == 1){
                secondsUsedString = "0"+secondsUsedString+"s";
            } else {
                secondsUsedString = secondsUsedString+"s";
            }

            MarathonLeaderboardModel thisEntry = new MarathonLeaderboardModel(currentUser.getDisplayName(), timestamp, ""+gameTimeTracker, ""+score, ""+correctAnswers,
                    ""+wrongAnswers, ""+timesPassed, "Marathon");
            myDatabase.child("leaderboards").child("marathon").child(thisEntry.getScore() + " " + thisEntry.getTimeTaken() + ": " + currentUser.getUid() + " " + gameModel.getTimestamp()).setValue(thisEntry);

        }
    }

    private String getDate(){
        DateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy");
        String date=dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH-mm");
        String time = dfTime.format(Calendar.getInstance().getTime());
        return date + " " + time;
    }

    private void answerSelected(View v, String button, String answer){

        //turning buttons off so user can't select another answer
        answer1Button.setClickable(false);
        answer2Button.setClickable(false);
        answer3Button.setClickable(false);
        answer4Button.setClickable(false);
        answer5Button.setClickable(false);

        //getting the button user selected, could probably change this to just
        //passing in the ID int value
        Button selectedButton = new Button(v.getContext());
        switch (button){
            case "1":
                selectedButton = (Button) v.findViewById(R.id.answer1Button);
                break;

            case "2":
                selectedButton = (Button) v.findViewById(R.id.answer2Button);
                break;

            case "3":
                selectedButton = (Button) v.findViewById(R.id.answer3Button);
                break;

            case "4":
                selectedButton = (Button) v.findViewById(R.id.answer4Button);
                break;

            case "5":
                selectedButton = (Button) v.findViewById(R.id.answer5Button);
                break;
        }

        //checking if answer selected is right or wrong, and changing button appearance, score etc.
        if(answer.equals(thisQuestion.getAnswer())){
            selectedButton.setBackgroundResource(R.drawable.custom_right_button);
            score = score+20;
            scoreTextView.setText("Score: "+score);
            correctAnswers++;
        } else {
            if(answer1Button.getText().equals(thisQuestion.getAnswer())){
                answer1Button.setBackgroundResource(R.drawable.custom_right_button);
            } else if(answer2Button.getText().equals(thisQuestion.getAnswer())){
                answer2Button.setBackgroundResource(R.drawable.custom_right_button);
            } else if(answer3Button.getText().equals(thisQuestion.getAnswer())){
                answer3Button.setBackgroundResource(R.drawable.custom_right_button);
            } else if(answer4Button.getText().equals(thisQuestion.getAnswer())){
                answer4Button.setBackgroundResource(R.drawable.custom_right_button);
            }else if(answer5Button.getText().equals(thisQuestion.getAnswer())){
                answer5Button.setBackgroundResource(R.drawable.custom_right_button);
            }
            selectedButton.setBackgroundResource(R.drawable.custom_wrong_button);
            wrongAnswers++;
            score = score-20;
            scoreTextView.setText("Score: "+score);
        }

        //pausing the game for a second, before launching new question
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playTheGame();
            }
        }, 1500);
    }

    @Override
    public void onStop() {
        super.onStop();
        onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }



}