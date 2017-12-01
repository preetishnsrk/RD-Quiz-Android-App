package ie.freetime.reddwarf.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
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

import ie.freetime.reddwarf.MainActivity;
import ie.freetime.reddwarf.Models.GameModel;
import ie.freetime.reddwarf.Models.LeaderBoardModel;
import ie.freetime.reddwarf.Models.QuestionsModel;
import ie.freetime.reddwarf.R;

import static ie.freetime.reddwarf.MainActivity.backStackCount;
import static ie.freetime.reddwarf.MainActivity.currentFragment;
import static ie.freetime.reddwarf.Models.Seeds.easyQuestionsArray;
import static ie.freetime.reddwarf.Models.Seeds.mediumQuestionsArray;
import static ie.freetime.reddwarf.Models.Seeds.populateEasyArray;
import static ie.freetime.reddwarf.Models.Seeds.populateMediumArray;

/**
 * A simple {@link Fragment} subclass.
 */
public class EasyQuizFragment extends Fragment implements View.OnClickListener {

    DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    String TAG = "EasyQuizFragment";

    NestedScrollView quizLayout;

    public static LinearLayout easyIntroLayout,easyCountdownLayout;
    public static RelativeLayout easyMainLayout, easyResultsLayout;
    ProgressBar countdownProgressBar;
    public static CountDownTimer countDownTimer;

    public static Button exitButton;
    Button fishButton, restartButton, easyStartButton, answer1Button, answer2Button,
            answer3Button;

    TextView questionNumberTextView, scoreTextView, questionTextView, correctAnswersTV, incorrectAnswersTV,
            passedTV, timeLeftTV, scoreTV, finalTV, timeCounterTV, outcomeTV, completedInTV, timeLeftResultsTV,
            bonusPointsTV;

    ImageView countdown4, countdown3, countdown2, countdown1;

    int questionNumber = 0;
    int score = 0;

    int correctAnswers = 0;
    int wrongAnswers = 0;
    int timesPassed = 0;

    int time = 300;
    int modifiedTime = 0;

    int gameTimeTracker = 0;

    int minutesTracker = 0;
    int secondsTracker = 0;

    QuestionsModel thisQuestion;
    ArrayList<String> questionHistory = new ArrayList<>();

    ViewAnimator viewAnimator;
    Animation fadeIn, fadeOut;

    ArrayList<QuestionsModel> questionsArray = new ArrayList<>();

    public EasyQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_easy_quiz, container, false);

        viewAnimator = (ViewAnimator) v.findViewById(R.id.viewAnimator);
        fadeIn = AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_out);
        viewAnimator.setInAnimation(fadeIn);
        viewAnimator.setOutAnimation(fadeOut);

        easyCountdownLayout = (LinearLayout) v.findViewById(R.id.easyCountdownLayout);
        easyIntroLayout = (LinearLayout) v.findViewById(R.id.easyWelcomeLayout);
        easyMainLayout = (RelativeLayout) v.findViewById(R.id.easyMainLayout);
        easyResultsLayout = (RelativeLayout) v.findViewById(R.id.easyResultsLayout);

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

        fishButton = (Button) v.findViewById(R.id.fishButton);
        exitButton = (Button) v.findViewById(R.id.exitButton);
        restartButton = (Button) v.findViewById(R.id.restartButton);
        easyStartButton = (Button) v.findViewById(R.id.easyStartButton);

        answer1Button.setOnClickListener(this);
        answer2Button.setOnClickListener(this);
        answer3Button.setOnClickListener(this);

        fishButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
        restartButton.setOnClickListener(this);
        easyStartButton.setOnClickListener(this);


        return v;
    }


    private void reverseTimer(int time, final TextView textview){

        if(countDownTimer!=null){
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(time*1000+1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                if(millisUntilFinished<21000){
                    textview.setTextColor(Color.RED);
                } else {
                    textview.setTextColor(Color.WHITE);
                }

                gameTimeTracker++;
                //Log.v(TAG,"gameTimeTracker: "+gameTimeTracker);

                modifiedTime = (int) millisUntilFinished/1000;

                int seconds = (int) (millisUntilFinished/1000);
                int minutes = seconds/60;
                minutesTracker = minutes;
                int displaySeconds = seconds - (minutes*60);
                secondsTracker = displaySeconds;

                textview.setText("Time: "+String.format("%02d",minutes)+
                ":"+String.format("%02d",displaySeconds));
            }

            @Override
            public void onFinish() {
                textview.setText("Time's up!");
                showResults();
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(v.getId()){
            case R.id.easyStartButton:
                easyIntroLayout.setVisibility(View.GONE);
                easyCountdownLayout.setVisibility(View.VISIBLE);

                populateEasyArray();
                populateMediumArray();

                questionsArray.addAll(easyQuestionsArray);
                questionsArray.addAll(mediumQuestionsArray);
                Log.v(TAG,"ArraySize: "+questionsArray.size());

                CountDownTimer gameStartTimer = new CountDownTimer(3500,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        viewAnimator.showNext();
                    }

                    @Override
                    public void onFinish() {
                        questionNumber = 0;
                        score = 0;
                        gameTimeTracker = 0;
                        minutesTracker = 0;
                        secondsTracker = 0;
                        modifiedTime = 0;
                        correctAnswers = 0;
                        wrongAnswers = 0;
                        timesPassed = 0;
                        time = 300;
                        reverseTimer(time, timeCounterTV);


                        questionHistory.clear();

                        easyCountdownLayout.setVisibility(View.GONE);
                        easyMainLayout.setVisibility(View.VISIBLE);



                        reverseTimer(time,timeCounterTV);
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
                        countDownTimer.cancel();
                        UltimateQuizIntroFragment ultimateFragment = new UltimateQuizIntroFragment();
                        fragmentTransaction.detach(EasyQuizFragment.this);
                        fragmentTransaction.add(R.id.fragment_frame, ultimateFragment);
                        backStackCount--;
                        currentFragment = "UltimateQuizFragment";
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

                builder1.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        countDownTimer.cancel();
                        questionHistory.clear();
                        questionNumber = 0;
                        score = 0;
                        gameTimeTracker = 0;
                        minutesTracker = 0;
                        secondsTracker = 0;
                        modifiedTime = 0;
                        correctAnswers = 0;
                        wrongAnswers = 0;
                        timesPassed = 0;
                        reverseTimer(time, timeCounterTV);
                        playTheGame();
                    }
                });

                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
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
                modifiedTime = modifiedTime - 3;
                reverseTimer(modifiedTime, timeCounterTV);
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
        }
    }

    private void playTheGame(){

        quizLayout.scrollTo(0,0);

        //if user hasn't yet had 50 questions present another to them, else show the results screen
        if(questionNumber<50) {
            //turning buttons on(turned off in answerSelected()) and setting colors back
            answer1Button.setClickable(true);
            answer1Button.setBackgroundResource(R.drawable.custom_button);
            answer2Button.setClickable(true);
            answer2Button.setBackgroundResource(R.drawable.custom_button);
            answer3Button.setClickable(true);
            answer3Button.setBackgroundResource(R.drawable.custom_button);

            //tracking question number
            questionNumber++;

            //Displaying question number and current score to user
            questionNumberTextView.setText("Question: " + questionNumber + "/50");
            scoreTextView.setText("Score: " + score);

            //Grabbing a random question from the array and making sure it hasn't already been
            //asked by checking questionHistory array
            do {
                thisQuestion = questionsArray.get(new Random().nextInt(questionsArray.size()));
                Log.v(TAG, "Random: " +thisQuestion.getId());
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

            for (int i = 0; i < 2; i++) {
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
        } else {
            //TODO: results screen
            countDownTimer.cancel();
            showResults();
        }
    }

    private void showResults(){

        String timeLeft = "";

        if(timeCounterTV.getText().equals("Time's up!")){
            outcomeTV.setText("Ran out of time...smeghead...");
            timeLeftResultsTV.setText("0m0s");
            finalTV.setText("Your score was not posted to the leaderboard.");
            timeLeft = "0m0s";
        }else{
            outcomeTV.setText("Completed!");
            timeLeftResultsTV.setText(minutesTracker+"m"+secondsTracker+"s");
            timeLeft = minutesTracker+"m"+secondsTracker+"s";
            finalTV.setText("Your score was added to the leaderboard.");
        }

        int rightPercent = correctAnswers*100/50;
        int wrongPercent = wrongAnswers*100/50;
        int passPercent = timesPassed*100/50;

        int secondsLeft = (minutesTracker*60)+secondsTracker;
        final int bonus = secondsLeft/15;

        bonusPointsTV.setText(bonus+" points awarded\n(1 per 15s of time remaining)");

        int totalScore = score+bonus;
        scoreTV.setText(totalScore+" points awarded");
        correctAnswersTV.setText(correctAnswers+ "/50 ("+rightPercent+"%)");
        incorrectAnswersTV.setText(wrongAnswers+"/50 ("+wrongPercent+"%)");
        passedTV.setText(timesPassed+"/50 ("+passPercent+"%)");

        int completedInSeconds = gameTimeTracker;
        Log.v(TAG,"completedInSeconds: "+completedInSeconds);
        int completedInMinutes = completedInSeconds/60;
        String completedInString = completedInMinutes+"m"+(completedInSeconds - (completedInMinutes*60))+"s";

        completedInTV.setText(completedInString);

        easyMainLayout.setVisibility(View.GONE);
        easyResultsLayout.setVisibility(View.VISIBLE);

        String timestamp = getDate()+"hrs";

        GameModel gameModel = new GameModel(timestamp,"Easy", completedInString, timeLeft, bonus+" bonus points", correctAnswers+ "/50 ("+rightPercent+"%)",
                wrongAnswers+"/50 ("+wrongPercent+"%)", timesPassed+"/50 ("+passPercent+"%)", score+" points", outcomeTV.getText().toString(), score, correctAnswers, wrongAnswers,
                timesPassed, gameTimeTracker);

        Query lifetimeScore = myDatabase.child("users").child(currentUser.getUid()).child("lifetimeScore");
        lifetimeScore.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    long thisScore = dataSnapshot.getValue(long.class);
                    long thisGameScore = (long) score;
                    long thisGameBonus = (long) bonus;
                    long newScore = thisScore+thisGameScore+thisGameBonus;
                    myDatabase.child("users").child(currentUser.getUid()).child("lifetimeScore").setValue(newScore);
                    myDatabase.child("leaderboards").child("lifetime").child(currentUser.getUid()).child("score").setValue(newScore);
                    myDatabase.child("leaderboards").child("lifetime").child(currentUser.getUid()).child("user").setValue(currentUser.getDisplayName());
                } else {
                    long thisGameScore = (long) score;
                    long thisGameBonus = (long) bonus;
                    long newScore = thisGameScore+thisGameBonus;
                    myDatabase.child("users").child(currentUser.getUid()).child("lifetimeScore").setValue(newScore);
                    myDatabase.child("leaderboards").child("lifetime").child(currentUser.getUid()).child("score").setValue(newScore);
                    myDatabase.child("leaderboards").child("lifetime").child(currentUser.getUid()).child("user").setValue(currentUser.getDisplayName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myDatabase.child("users").child(currentUser.getUid()).child("gameHistory").child(timestamp).setValue(gameModel);

        if(gameModel.getCompletionStatus().equals("Completed!")) {
            String minutesLeftString = String.valueOf(minutesTracker);
            String secondsLeftString = String.valueOf(secondsTracker);
            String minutesUsedString = String.valueOf(completedInMinutes);
            String secondsUsedString = String.valueOf(completedInSeconds);

            int minsLeft = minutesLeftString.length();
            int secsLeft = secondsLeftString.length();
            int minsUsed = minutesUsedString.length();
            int secsUsed = secondsUsedString.length();

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

            LeaderBoardModel thisEntry = new LeaderBoardModel(currentUser.getDisplayName(), "" + score, "" + bonus, (bonus + score) + "",
                    completedInString, timeLeft, correctAnswers + "", wrongAnswers + "", timesPassed + "", secondsTracker);

            myDatabase.child("leaderboards").child("easy").child(thisEntry.getTotal() + " " + thisEntry.getBonus() + ": " + currentUser.getUid() + " " + gameModel.getTimestamp()).setValue(thisEntry);

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
        }

        //checking if answer selected is right or wrong, and changing button appearance, score etc.
        if(answer.equals(thisQuestion.getAnswer())){
            selectedButton.setBackgroundResource(R.drawable.custom_right_button);
            score += 5;
            scoreTextView.setText("Score: "+score);
            correctAnswers++;

            modifiedTime = modifiedTime + 10;
            reverseTimer(modifiedTime, timeCounterTV);
        } else {
            if(answer1Button.getText().equals(thisQuestion.getAnswer())){
                answer1Button.setBackgroundResource(R.drawable.custom_right_button);
            } else if(answer2Button.getText().equals(thisQuestion.getAnswer())){
                answer2Button.setBackgroundResource(R.drawable.custom_right_button);
            } else if(answer3Button.getText().equals(thisQuestion.getAnswer())){
                answer3Button.setBackgroundResource(R.drawable.custom_right_button);
            }

            selectedButton.setBackgroundResource(R.drawable.custom_wrong_button);
            wrongAnswers++;

            modifiedTime = modifiedTime - 5;
            reverseTimer(modifiedTime, timeCounterTV);
        }

        //pausing the game for a second, before launching new question
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playTheGame();
            }
        }, 1500);
    }

}
