package ie.freetime.reddwarf.Fragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;
import ie.freetime.reddwarf.Custom.AccountAdapter;
import ie.freetime.reddwarf.Models.GameModel;
import ie.freetime.reddwarf.Models.QuestionsModel;
import ie.freetime.reddwarf.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {

    DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    TextView historyHeader, usernameTV, lifetimeScoreTV, noDataTV;
    ImageView accountLoading;
    RecyclerView recyclerView;
    CircleImageView profilePic;
    ArrayList<GameModel> gameArrayList;
    AccountAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    public Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        profilePic = (CircleImageView)v.findViewById(R.id.profilePic);
        Glide.with(getActivity())
                .load(user.getPhotoUrl())
                .centerCrop()
                .into(profilePic);

        noDataTV = (TextView) v.findViewById(R.id.noDataTV);
        historyHeader = (TextView) v.findViewById(R.id.historyHeader);
        recyclerView = (RecyclerView) v.findViewById(R.id.historyRecyclerList);
        accountLoading = (ImageView) v.findViewById(R.id.accountLoading);
        usernameTV = (TextView) v.findViewById(R.id.usernameTV);
        lifetimeScoreTV = (TextView) v.findViewById(R.id.lifetimeScoreTV);
        usernameTV.setText("User: "+user.getDisplayName());
        gameArrayList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        Resources resources = getResources();

        adapter = new AccountAdapter(getActivity(), gameArrayList, resources);
        recyclerView.setAdapter(adapter);

        Query getScore = myDatabase.child("users").child(user.getUid()).child("lifetimeScore");
        getScore.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    lifetimeScoreTV.setText("Total Score: "+dataSnapshot.getValue().toString());
                }else {
                    lifetimeScoreTV.setText("Total Score: 0");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query getUserHistory = myDatabase.child("users").child(user.getUid()).child("gameHistory");
        getUserHistory.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot thisGame : dataSnapshot.getChildren()){
                        GameModel game = thisGame.getValue(GameModel.class);
                        gameArrayList.add(game);
                    }
                    Collections.reverse(gameArrayList);
                    adapter.notifyDataSetChanged();

                    accountLoading.setVisibility(View.GONE);
                    usernameTV.setVisibility(View.VISIBLE);
                    lifetimeScoreTV.setVisibility(View.VISIBLE);
                    historyHeader.setText("Your game history (" + gameArrayList.size() + " records found)");
                    historyHeader.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                }else {
                    accountLoading.setVisibility(View.GONE);
                    usernameTV.setVisibility(View.VISIBLE);
                    lifetimeScoreTV.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    noDataTV.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }

}
