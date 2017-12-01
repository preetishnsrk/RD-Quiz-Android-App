package ie.freetime.reddwarf.Fragments;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import ie.freetime.reddwarf.Models.QuestionsModel;
import ie.freetime.reddwarf.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddQuestionFragment extends Fragment implements View.OnClickListener {

    Spinner difficultySpinner;
    EditText questionET, rightET, wrongET1, wrongET2, wrongET3, wrongET4;
    Button submitButton, closeButton, proceedButton;
    TextView reviewQuestionTV, reviewAnswerTV, reviewWrong1TV, reviewWrong2TV, reviewWrong3TV,
            reviewDifficultyTV, reviewWrong4TV;
    String difficulty, question,option1,option2,option3,option4,option5,answer;

    DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    public AddQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_question, container, false);

        difficultySpinner = (Spinner) v.findViewById(R.id.difficultySpinner);

        questionET = (EditText) v.findViewById(R.id.questionET);
        rightET = (EditText) v.findViewById(R.id.rightET);
        wrongET1 = (EditText) v.findViewById(R.id.wrongET1);
        wrongET2 = (EditText) v.findViewById(R.id.wrongET2);
        wrongET3 = (EditText) v.findViewById(R.id.wrongET3);
        wrongET4 = (EditText) v.findViewById(R.id.wrongET4);

        submitButton = (Button) v.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        difficultySpinner = (Spinner)v.findViewById(R.id.difficultySpinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(v.getContext(), R.array.difficulty_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitButton:

                difficulty = difficultySpinner.getSelectedItem().toString();
                question = questionET.getText().toString();
                answer = rightET.getText().toString();
                option1 = wrongET1.getText().toString();
                option2 = wrongET2.getText().toString();
                option3 = wrongET3.getText().toString();
                option4 = wrongET4.getText().toString();
                option5 = rightET.getText().toString();

                if(question.isEmpty() || answer.isEmpty() || option1.isEmpty() || option2.isEmpty()
                        || option3.isEmpty() || option4.isEmpty() || option5.isEmpty()){
                    Toast.makeText(getActivity(), "At least one of the fields is still empty, please check them again.",Toast.LENGTH_LONG).show();
                } else if(difficulty.contains("Select difficulty…")){
                    Toast.makeText(getActivity(), "You forgot to set the difficulty, use the drop-down list over the Question field.",Toast.LENGTH_LONG).show();
                } else if(question.length()<10){
                    Toast.makeText(getActivity(), "The question is less than 10 characters long, are you sure you finished it?",Toast.LENGTH_LONG).show();
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    builder.setView(inflater.inflate(R.layout.submit_dialog, null));

                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    reviewQuestionTV = (TextView) alertDialog.findViewById(R.id.reviewQuestionTV);
                    reviewAnswerTV = (TextView) alertDialog.findViewById(R.id.reviewAnswerTV);
                    reviewWrong1TV = (TextView) alertDialog.findViewById(R.id.reviewWrong1TV);
                    reviewWrong2TV = (TextView) alertDialog.findViewById(R.id.reviewWrong2TV);
                    reviewWrong3TV = (TextView) alertDialog.findViewById(R.id.reviewWrong3TV);
                    reviewWrong4TV = (TextView) alertDialog.findViewById(R.id.reviewWrong4TV);
                    reviewDifficultyTV = (TextView) alertDialog.findViewById(R.id.reviewDifficultyTV);
                    closeButton = (Button) alertDialog.findViewById(R.id.closeButton);
                    proceedButton = (Button) alertDialog.findViewById(R.id.proceedButton);

                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                    proceedButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Query getQuestionNumber = myDatabase.child("userQuestions").child("questionTracker").child(difficulty);
                            getQuestionNumber.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    long questionNumber;
                                    if(dataSnapshot.exists()){
                                        questionNumber = dataSnapshot.getChildrenCount();
                                    } else {
                                        questionNumber = 0;
                                    }
                                    questionNumber++;
                                    QuestionsModel questionsModel = new QuestionsModel(String.valueOf(questionNumber),
                                            question,option1,option2,option3,option4,option5,answer);

                                    myDatabase.child("userQuestions").child("questionTracker").child(difficulty).child(String.valueOf(questionNumber)).setValue(currentUser.getUid());
                                    myDatabase.child("userQuestions").child(difficulty).child(String.valueOf(questionNumber)+" - "+currentUser.getUid()).setValue(questionsModel);

                                    Toast.makeText(getActivity(),"Question has been submitted, thanks!",Toast.LENGTH_LONG).show();
                                    alertDialog.dismiss();

                                    FragmentManager fragmentManager = getFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                    AddQuestionFragment addQuestionFragment = new AddQuestionFragment();
                                    fragmentTransaction.remove(AddQuestionFragment.this);
                                    fragmentTransaction.add(R.id.fragment_frame, addQuestionFragment);
                                    fragmentTransaction.commit();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    });

                    reviewQuestionTV.setText("Question: "+question);
                    reviewAnswerTV.setText("Answer: "+answer);
                    reviewWrong1TV.setText("Wrong 1: "+option1);
                    reviewWrong2TV.setText("Wrong 2: "+option2);
                    reviewWrong3TV.setText("Wrong 3: "+option3);
                    reviewWrong4TV.setText("Wrong 4: "+option4);
                    reviewDifficultyTV.setText("Difficulty: "+difficulty);

                }

                break;
        }
    }
}
