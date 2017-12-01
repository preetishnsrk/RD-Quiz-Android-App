package ie.freetime.reddwarf.Fragments.LeaderBoardFragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ie.freetime.reddwarf.Custom.LifetimeLeaderboardAdapter;
import ie.freetime.reddwarf.Models.LeaderBoardModel;
import ie.freetime.reddwarf.Models.LifetimeLeaderboardModel;
import ie.freetime.reddwarf.R;


public class LifetimeLeaderboard extends Fragment {

    ImageView lifetimeLoadingGIF;

    String TAG = "LifetimeLeaderboard";
    DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();
    RecyclerView recyclerView;
    LifetimeLeaderboardAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<LifetimeLeaderboardModel> leaderBoardModelArrayList = new ArrayList<>();
    TextView noRecordsTV;

    public LifetimeLeaderboard() {
        // Required empty public constructor
    }


    public static LifetimeLeaderboard newInstance() {
        LifetimeLeaderboard fragment = new LifetimeLeaderboard();;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lifetime_leaderboard, container, false);

        noRecordsTV = (TextView) v.findViewById(R.id.noRecordsTV);

        if(!leaderBoardModelArrayList.isEmpty()){
            leaderBoardModelArrayList.clear();
        }

        lifetimeLoadingGIF = (ImageView) v.findViewById(R.id.lifetimeLoadingGIF);

        recyclerView = (RecyclerView) v.findViewById(R.id.lifetimeLeaderRecyclerList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        Resources resources = getResources();

        adapter = new LifetimeLeaderboardAdapter(getActivity(), leaderBoardModelArrayList, resources);
        recyclerView.setAdapter(adapter);



        Query getLifetime = myDatabase.child("leaderboards").child("lifetime");
        getLifetime.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot thisSnapshot : dataSnapshot.getChildren()){

                        final LifetimeLeaderboardModel thisEntry = new LifetimeLeaderboardModel();
                        final String userString = thisSnapshot.child("user").getValue().toString();
                        String scoreString = thisSnapshot.child("score").getValue().toString();

                        int scoreCount = scoreString.length();

                        if(scoreCount == 1){
                            scoreString  = "0"+scoreString;
                        }

                        Query getGameCount = myDatabase.child("users").child(thisSnapshot.getKey()).child("gameHistory");
                        final String finalScoreString = scoreString;
                        getGameCount.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String gamesString = String.valueOf(dataSnapshot.getChildrenCount());

                                int gamesCount = gamesString.length();
                                if(gamesCount == 1){
                                    gamesString = "0"+gamesString;
                                }

                                thisEntry.setUser(userString);
                                thisEntry.setScore(finalScoreString);
                                thisEntry.setGameCount(gamesString);

                                leaderBoardModelArrayList.add(thisEntry);

                                Collections.sort(leaderBoardModelArrayList, new Comparator<LifetimeLeaderboardModel>() {
                                    @Override
                                    public int compare(LifetimeLeaderboardModel o1, LifetimeLeaderboardModel o2) {
                                        long i1 = Long.parseLong(o1.getScore());
                                        long i2 = Long.parseLong(o2.getScore());

                                        if(i1 != i2) {
                                            return (int) (i2 - i1);
                                        }else {
                                            long s1 = Long.parseLong(o1.getGameCount());
                                            long s2 = Long.parseLong(o2.getGameCount());

                                            return (int) (s1-s2);
                                        }
                                    }
                                });

                                /*
                                Collections.sort(leaderBoardModelArrayList, new Comparator<LifetimeLeaderboardModel>() {
                                    @Override
                                    public int compare(LifetimeLeaderboardModel o1, LifetimeLeaderboardModel o2) {
                                        String firstScore = ((LifetimeLeaderboardModel) o1).getScore();
                                        String secondScore = ((LifetimeLeaderboardModel) o2).getScore();

                                        int comparator = secondScore.compareTo(firstScore);

                                        if(comparator != 0){
                                            return comparator;
                                        } else {
                                            String x1 = ((LifetimeLeaderboardModel)o1).getGameCount();
                                            String x2 = ((LifetimeLeaderboardModel)o2).getGameCount();
                                            return  x2.compareTo(x1);
                                        }
                                    }
                                });
                                */
                                adapter.notifyDataSetChanged();
                                lifetimeLoadingGIF.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }

                        });

                    }
                }  else {
                    lifetimeLoadingGIF.setVisibility(View.GONE);
                    noRecordsTV.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }


}
