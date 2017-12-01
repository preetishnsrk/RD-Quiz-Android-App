package ie.freetime.reddwarf.Fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ie.freetime.reddwarf.R;

import static ie.freetime.reddwarf.MainActivity.backStackCount;
import static ie.freetime.reddwarf.MainActivity.currentFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankedFragment extends Fragment implements View.OnClickListener {

    Button ultimateQuizButton, marathonQuizButton;
    ImageView imageView3, imageView7, imageView70;


    public RankedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ranked, container, false);

        ultimateQuizButton = (Button) v.findViewById(R.id.ultimateQuizButton);
        marathonQuizButton = (Button) v.findViewById(R.id.marathonQuizButton);

        imageView3 = (ImageView) v.findViewById(R.id.imageView3);
        Glide.with(this).load(R.drawable.red_dwarf_2).into(imageView3);

        ultimateQuizButton.setOnClickListener(this);
        marathonQuizButton.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.ultimateQuizButton:
                UltimateQuizIntroFragment ultimateQuizIntroFragment = new UltimateQuizIntroFragment();
                fragmentTransaction.remove(RankedFragment.this);
                fragmentTransaction.addToBackStack("UltimateQuizIntroFragment");
                fragmentTransaction.add(R.id.fragment_frame, ultimateQuizIntroFragment);
                backStackCount++;
                currentFragment = "UltimateQuizIntroFragment";
                fragmentTransaction.commit();
                break;

            case R.id.marathonQuizButton:
                MarathonQuizFragment marathonQuizFragment = new MarathonQuizFragment();
                fragmentTransaction.remove(RankedFragment.this);
                fragmentTransaction.addToBackStack("MarathonQuizFragment");
                fragmentTransaction.add(R.id.fragment_frame, marathonQuizFragment);
                backStackCount++;
                currentFragment = "MarathonQuizFragment";
                fragmentTransaction.commit();
                break;
        }
    }
}
