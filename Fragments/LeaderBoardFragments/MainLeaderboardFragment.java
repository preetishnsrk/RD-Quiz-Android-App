package ie.freetime.reddwarf.Fragments.LeaderBoardFragments;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ie.freetime.reddwarf.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainLeaderboardFragment extends Fragment {

    public static PagerAdapter pagerAdapter;
    static ViewPager viewPager;

    public MainLeaderboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_leaderboard, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        pagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        return v;
    }


    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new LifetimeLeaderboard();

                case 1:
                    return new EasyLeaderboard();

                case 2:
                    return new MediumLeaderboard();

                case 3:
                    return new HardLeaderboard();

                case 4:
                    return new MarathonLeaderboardFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public int getItemPosition(Object object){
            return POSITION_NONE;
        }
    }
}
