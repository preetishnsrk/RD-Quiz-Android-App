package ie.freetime.reddwarf.Fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import ie.freetime.reddwarf.R;

import static ie.freetime.reddwarf.MainActivity.backStackCount;
import static ie.freetime.reddwarf.MainActivity.currentFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UltimateQuizIntroFragment extends Fragment implements View.OnClickListener {

    Button easyButton, mediumButton, hardButton;

    public UltimateQuizIntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ultimate_quiz_intro, container, false);


        easyButton = (Button) v.findViewById(R.id.easyButton);
        mediumButton = (Button) v.findViewById(R.id.mediumButton);
        hardButton = (Button) v.findViewById(R.id.hardButton);

        easyButton.setOnClickListener(this);
        mediumButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (v.getId()){
            case R.id.easyButton:
                EasyQuizFragment easyQuizFragment = new EasyQuizFragment();
                fragmentTransaction.hide(UltimateQuizIntroFragment.this);
                fragmentTransaction.addToBackStack("EasyQuizFragment");
                backStackCount++;
                currentFragment = "EasyQuizFragment";
                fragmentTransaction.add(R.id.fragment_frame, easyQuizFragment);
                fragmentTransaction.commit();
                break;

            case R.id.mediumButton:
                MediumQuizFragment mediumQuizFragment = new MediumQuizFragment();
                fragmentTransaction.hide(UltimateQuizIntroFragment.this);
                fragmentTransaction.addToBackStack("MediumQuizFragment");
                fragmentTransaction.add(R.id.fragment_frame, mediumQuizFragment);
                backStackCount++;
                currentFragment = "MediumQuizFragment";
                fragmentTransaction.commit();
                break;

            case R.id.hardButton:
                HardQuizFragment hardQuizFragment = new HardQuizFragment();
                fragmentTransaction.hide(UltimateQuizIntroFragment.this);
                fragmentTransaction.addToBackStack("HardQuizFragment");
                fragmentTransaction.add(R.id.fragment_frame, hardQuizFragment);
                backStackCount++;
                currentFragment = "HardQuizFragment";
                fragmentTransaction.commit();
                break;

        }
    }
}
