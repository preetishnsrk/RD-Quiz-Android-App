package ie.freetime.reddwarf.Fragments;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ie.freetime.reddwarf.MainActivity;
import ie.freetime.reddwarf.R;

import static ie.freetime.reddwarf.MainActivity.backStackCount;
import static ie.freetime.reddwarf.MainActivity.currentFragment;
import static ie.freetime.reddwarf.MainActivity.thisDrawer;
import static ie.freetime.reddwarf.Models.Seeds.populateQuotesArray;
import static ie.freetime.reddwarf.Models.Seeds.quotesArray;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {


    String TAG = "MainFragment";
    TextView quoteTV;
    Button getStartedButton;
    DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();
    ImageView imageView2;
    String thisQuote;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        getStartedButton = (Button) view.findViewById(R.id.getStartedButton);
        getStartedButton.setOnClickListener(this);

        imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        Glide.with(this).load(R.drawable.holly).into(imageView2);

        quoteTV = (TextView) view.findViewById(R.id.quoteTV);

        populateQuotesArray();

        thisQuote = quotesArray.get(new Random().nextInt(quotesArray.size()));

        quoteTV.setText(thisQuote);

        return view;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getStartedButton:
                thisDrawer.openDrawer();
                break;
        }
    }

}
