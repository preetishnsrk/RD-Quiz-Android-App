package ie.freetime.reddwarf;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;

import ie.freetime.reddwarf.Fragments.AboutFragment;
import ie.freetime.reddwarf.Fragments.Account;
import ie.freetime.reddwarf.Fragments.AddQuestionFragment;
import ie.freetime.reddwarf.Fragments.EasyQuizFragment;
import ie.freetime.reddwarf.Fragments.HardQuizFragment;
import ie.freetime.reddwarf.Fragments.LeaderBoardFragments.MainLeaderboardFragment;
import ie.freetime.reddwarf.Fragments.MainFragment;
import ie.freetime.reddwarf.Fragments.MarathonQuizFragment;
import ie.freetime.reddwarf.Fragments.MediumQuizFragment;
import ie.freetime.reddwarf.Fragments.RankedFragment;
import ie.freetime.reddwarf.Fragments.UltimateQuizIntroFragment;

import static ie.freetime.reddwarf.Fragments.EasyQuizFragment.easyCountdownLayout;
import static ie.freetime.reddwarf.Fragments.HardQuizFragment.hardCountdownLayout;
import static ie.freetime.reddwarf.Fragments.MarathonQuizFragment.marathonCountdownLayout;
import static ie.freetime.reddwarf.Fragments.MediumQuizFragment.mediumCountdownLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button logoutButton;

    String photoUri = "";
    Toolbar toolbar;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    FragmentManager fragmentManager = getFragmentManager();
    public static String currentFragment = "";
    public static int backStackCount;
    String TAG = "MainActivity";

    public static Drawer thisDrawer = null;
    AccountHeader thisHeader = null;

    DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();

    FrameLayout fragment_frame;
    private AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, "hidden");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAuth = FirebaseAuth.getInstance();

        navDrawer(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        Login.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        fragment_frame = (FrameLayout) findViewById(R.id.fragment_frame);

        fragmentManager.beginTransaction().replace(R.id.fragment_frame, new MainFragment()).addToBackStack("MainFragment").commit();
        fragmentManager.executePendingTransactions();
        currentFragment = getCurrentFragment();
        Log.v(TAG, "currentFragment: " + currentFragment);
    }

    public String getCurrentFragment() {
        String backStack = "";
        backStackCount = fragmentManager.getBackStackEntryCount();
        Log.v(TAG, "backStackCount: " + backStackCount);
        if (backStackCount > 0) {
            FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(backStackCount - 1);
            backStack = backStackEntry.getName();
        }
        return backStack;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){



        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onBackPressed() {
        if(thisDrawer.isDrawerOpen()){
            thisDrawer.closeDrawer();
        } else if(backStackCount == 1){
            finishAffinity();
        } else if(currentFragment.equals("EasyQuizFragment") || currentFragment.equals("MediumQuizFragment")
                || currentFragment.equals("HardQuizFragment") || currentFragment.equals("MarathonQuizFragment")){

            if(currentFragment.equals("EasyQuizFragment")) {
                if (easyCountdownLayout.getVisibility() == View.VISIBLE) {

                }else if(EasyQuizFragment.countDownTimer != null){
                    EasyQuizFragment.countDownTimer.cancel();
                    super.onBackPressed();
                }else{
                    super.onBackPressed();
                }
            } else if(currentFragment.equals("MediumQuizFragment")){
                if(mediumCountdownLayout.getVisibility()==View.VISIBLE){

                }else if(MediumQuizFragment.countDownTimer != null){
                    MediumQuizFragment.countDownTimer.cancel();
                    super.onBackPressed();
                }else{
                    super.onBackPressed();
                }
            } else if(currentFragment.equals("HardQuizFragment")){
                if(hardCountdownLayout.getVisibility()==View.VISIBLE){

                }else if(HardQuizFragment.countDownTimer != null){
                    HardQuizFragment.countDownTimer.cancel();
                    super.onBackPressed();
                }else{
                    super.onBackPressed();
                }
            }else{
                if(marathonCountdownLayout.getVisibility()==View.VISIBLE){

                }else if(MarathonQuizFragment.countUpTimer != null){
                    MarathonQuizFragment.countUpTimer.stop();
                    super.onBackPressed();
                }else{
                    super.onBackPressed();
                }
            }
        }else {
            super.onBackPressed();
            currentFragment = getCurrentFragment();
        }
    }

    public void navDrawer(Bundle savedInstanceState) {

        if (currentUser.getPhotoUrl() != null) { //TODO: Put in internet connectivity checks
            photoUri = currentUser.getPhotoUrl().toString();
        } else {
            photoUri = "android.resource://ie.wit.freeroom/drawable/question";
        }
        new DrawerBuilder().withActivity(this).build();

        ExpandableDrawerItem item1;
        PrimaryDrawerItem  item2, item3, item4, item5, item6, itemFooter, about, itemLogout;



        IProfile profile = new ProfileDrawerItem().withName(currentUser.getDisplayName())
                .withEmail(currentUser.getEmail()).withIcon(photoUri).withIdentifier(1);

        item1 = new ExpandableDrawerItem().withIcon(FontAwesome.Icon.faw_question).withName("Quizzes").withSubItems(
                new SecondaryDrawerItem().withIdentifier(11).withName("Quiz Home").withIcon(FontAwesome.Icon.faw_home).withIconTintingEnabled(true),
                new SecondaryDrawerItem().withIdentifier(12).withName("Timed").withIcon(FontAwesome.Icon.faw_clock_o).withIconTintingEnabled(true),
                new SecondaryDrawerItem().withIdentifier(13).withName("Marathon").withIcon(FontAwesome.Icon.faw_rocket).withIconTintingEnabled(true)
        );
        item2 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.ranked_quizzes).withIcon(FontAwesome.Icon.faw_question).withIconTintingEnabled(true);
        item3 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.leaderboards).withIcon(FontAwesome.Icon.faw_trophy).withIconTintingEnabled(true);
        //item4 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.fun).withIcon(FontAwesome.Icon.faw_smile_o);
        item5 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.account_history).withIcon(FontAwesome.Icon.faw_user).withIconTintingEnabled(true);
        item6 = new PrimaryDrawerItem().withIdentifier(7).withName("").withIcon(FontAwesome.Icon.faw_share_alt).withIconTintingEnabled(true);
        about = new PrimaryDrawerItem().withIdentifier(8).withName("About").withIcon(FontAwesome.Icon.faw_info).withIconTintingEnabled(true);
        itemFooter = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.contact).withIcon(FontAwesome.Icon.faw_plus).withIconTintingEnabled(true);
        itemLogout = new PrimaryDrawerItem().withIdentifier(6).withName(R.string.logout).withIcon(FontAwesome.Icon.faw_sign_out).withIconTintingEnabled(true);


        //MaterialDrawer API doesn't have an image loader in library, so Glide API is needed....
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
                Glide.with(imageView.getContext())
                        .load(uri)
                        .placeholder(placeholder)
                        .centerCrop()
                        .fitCenter()
                        .into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Glide.clear(imageView);
            }

            //Context changed to Activity as parameter
            public Drawable placeholder(Activity ctx, String tag) {
                if (DrawerImageLoader.Tags.PROFILE.name().equals(tag)) {
                    return DrawerUIUtils.getPlaceHolder(ctx);
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name().equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(com.mikepenz.materialdrawer.R.color.primary).sizeDp(56);
                } else if ("customUrlItem".equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(R.color.md_red_500).sizeDp(56);
                }
                return super.placeholder(ctx, tag);
            }
        });
        //End of required image loader code

        thisHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.new_drawer_header2)
                .addProfiles(profile)
                .withSelectionListEnabledForSingleProfile(false)
                .build();

        thisDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(thisHeader)
                .addDrawerItems( item1, item3, item5, itemFooter)
                .addStickyDrawerItems(about,itemLogout)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        /*
                        if (position == 1) {
                            //fragmentManager.beginTransaction().replace(R.id.fragment_frame, new HomeFragment()).addToBackStack("Home").commit();
                            //fragmentManager.executePendingTransactions();
                            //currentFragment = getCurrentFragment();
                            //Log.v("MainActivity", "currentFragment: "+currentFragment);
                        }
                        */
                        if (drawerItem.equals(11)) {
                            fragmentManager.beginTransaction().replace(R.id.fragment_frame, new RankedFragment()).addToBackStack("RankedQuizzes").commit();
                            fragmentManager.executePendingTransactions();
                            currentFragment = getCurrentFragment();
                            Log.v("MainActivity", "currentFragment: " + currentFragment);
                        }

                        if (drawerItem.equals(12)) {
                            fragmentManager.beginTransaction().replace(R.id.fragment_frame, new UltimateQuizIntroFragment()).addToBackStack("UltimateQuizIntroFragment").commit();
                            fragmentManager.executePendingTransactions();
                            currentFragment = getCurrentFragment();
                            Log.v("MainActivity", "currentFragment: " + currentFragment);
                        }

                        if (drawerItem.equals(13)) {
                            fragmentManager.beginTransaction().replace(R.id.fragment_frame, new MarathonQuizFragment()).addToBackStack("MarathonQuizFragment").commit();
                            fragmentManager.executePendingTransactions();
                            currentFragment = getCurrentFragment();
                            Log.v("MainActivity", "currentFragment: " + currentFragment);
                        }

                        if (drawerItem.equals(2)) {
                            fragmentManager.beginTransaction().replace(R.id.fragment_frame, new MainLeaderboardFragment()).addToBackStack("LeaderboardFragment").commit();
                            fragmentManager.executePendingTransactions();
                            currentFragment = getCurrentFragment();
                            Log.v("MainActivity", "currentFragment: " + currentFragment);
                        }

                        if (drawerItem.equals(4)) {
                            fragmentManager.beginTransaction().replace(R.id.fragment_frame, new Account()).addToBackStack("AccountFragment").commit();
                            fragmentManager.executePendingTransactions();
                            currentFragment = getCurrentFragment();
                            Log.v("MainActivity", "currentFragment: " + currentFragment);
                        }
                        if (drawerItem.equals(7)) {
                            //onInviteClicked();
                        }
                        if (drawerItem.equals(5)) {
                            fragmentManager.beginTransaction().replace(R.id.fragment_frame, new AddQuestionFragment()).addToBackStack("AddQuestionFragment").commit();
                            fragmentManager.executePendingTransactions();
                            currentFragment = getCurrentFragment();
                            Log.v("MainActivity", "currentFragment: " + currentFragment);
                        }
                        if (drawerItem.equals(6)) {

                            Login.logout();

                            Intent goToLogin = new Intent(getApplicationContext(), Login.class);
                            startActivity(goToLogin);
                        }

                        if(drawerItem.equals(8)){
                            fragmentManager.beginTransaction().replace(R.id.fragment_frame, new AboutFragment()).addToBackStack("AboutFragment").commit();
                            fragmentManager.executePendingTransactions();
                            currentFragment = getCurrentFragment();
                            Log.v("MainActivity", "currentFragment: " + currentFragment);
                        }

                        return false;
                    }
                })
                .withSelectedItem(-1)
                .build();

    }


}
