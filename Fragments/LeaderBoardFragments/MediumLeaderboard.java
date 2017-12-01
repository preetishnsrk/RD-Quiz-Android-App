package ie.freetime.reddwarf.Fragments.LeaderBoardFragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import ie.freetime.reddwarf.Custom.LeaderboardAdapter;
import ie.freetime.reddwarf.Models.LeaderBoardModel;
import ie.freetime.reddwarf.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MediumLeaderboard extends Fragment {

    ImageView mediumLoadingGIF;

    String TAG = "MediumLeaderboardFragment";
    DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();
    RecyclerView recyclerView;
    LeaderboardAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<LeaderBoardModel> leaderBoardModelArrayList = new ArrayList<>();
    TextView noRecordsTV;

    public MediumLeaderboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_medium_leaderboard, container, false);

        noRecordsTV = (TextView) v.findViewById(R.id.noRecordsTV);

        if(!leaderBoardModelArrayList.isEmpty()){
            leaderBoardModelArrayList.clear();
        }

        mediumLoadingGIF = (ImageView) v.findViewById(R.id.mediumLoadingGIF);

        recyclerView = (RecyclerView) v.findViewById(R.id.mediumLeaderRecyclerList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        Resources resources = getResources();

        adapter = new LeaderboardAdapter(getActivity(), leaderBoardModelArrayList, resources);
        recyclerView.setAdapter(adapter);


        Query getLeaderboard = myDatabase.child("leaderboards").child("medium");
        getLeaderboard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot temp : dataSnapshot.getChildren()){
                        LeaderBoardModel thisEntry = temp.getValue(LeaderBoardModel.class);
                        leaderBoardModelArrayList.add(thisEntry);
                    }

                    Collections.sort(leaderBoardModelArrayList, new Comparator<LeaderBoardModel>() {
                        @Override
                        public int compare(LeaderBoardModel o1, LeaderBoardModel o2) {
                            long i1 = Long.parseLong(o1.getTotal());
                            long i2 = Long.parseLong(o2.getTotal());

                            if(i1 != i2) {
                                return (int) (i2 - i1);
                            }else {
                                long s1 = Long.parseLong(String.valueOf(o1.getTotalSeconds()));
                                long s2 = Long.parseLong(String.valueOf(o2.getTotalSeconds()));

                                return (int) (s1-s2);
                            }
                        }
                    });

                    /*
                    Collections.sort(leaderBoardModelArrayList, new Comparator<LeaderBoardModel>() {
                        @Override
                        public int compare(LeaderBoardModel o1, LeaderBoardModel o2) {
                            String firstScore = ((LeaderBoardModel) o1).getTotal();
                            String secondScore = ((LeaderBoardModel) o2).getTotal();

                            int comparator = secondScore.compareTo(firstScore);

                            if(comparator != 0){
                                return comparator;
                            } else {
                                Integer x1 = ((LeaderBoardModel)o1).getTotalSeconds();
                                Integer x2 = ((LeaderBoardModel)o2).getTotalSeconds();
                                return  x2.compareTo(x1);
                            }
                        }
                    });
*/
                    adapter.notifyDataSetChanged();
                    mediumLoadingGIF.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    mediumLoadingGIF.setVisibility(View.GONE);
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
