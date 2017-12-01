package ie.freetime.reddwarf.Fragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ie.freetime.reddwarf.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    ImageView aboutImageView, officialImageView, hollyImageView, gangImageView, posseImageView;
    TextView freetimeTV, officialTV, hollyTV, gangTV, posseTV;
    Button feedbackButton;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        freetimeTV = (TextView) v.findViewById(R.id.freetimeTV);
        Linkify.addLinks(freetimeTV,Linkify.ALL);
        freetimeTV.setLinkTextColor(Color.parseColor("#FFFFFF"));

        officialTV = (TextView) v.findViewById(R.id.officialTV);
        Linkify.addLinks(officialTV,Linkify.ALL);
        officialTV.setLinkTextColor(Color.parseColor("#FFFFFF"));

        posseTV = (TextView) v.findViewById(R.id.posseTV);
        Linkify.addLinks(posseTV,Linkify.ALL);
        posseTV.setLinkTextColor(Color.parseColor("#FFFFFF"));

        hollyTV = (TextView) v.findViewById(R.id.hollyTV);
        Linkify.addLinks(hollyTV,Linkify.ALL);
        hollyTV.setLinkTextColor(Color.parseColor("#FFFFFF"));

        gangTV = (TextView) v.findViewById(R.id.gangTV);
        Linkify.addLinks(gangTV,Linkify.ALL);
        gangTV.setLinkTextColor(Color.parseColor("#FFFFFF"));



        aboutImageView = (ImageView) v.findViewById(R.id.aboutImageView);
        Glide.with(this).load(R.drawable.freetime).into(aboutImageView);

        officialImageView = (ImageView) v.findViewById(R.id.officialImageView);
        Glide.with(this).load(R.drawable.official_logo).into(officialImageView);

        posseImageView = (ImageView) v.findViewById(R.id.posseImageView);
        Glide.with(this).load(R.drawable.posse).into(posseImageView);

        hollyImageView = (ImageView) v.findViewById(R.id.hollyImageView);
        Glide.with(this).load(R.drawable.real_holly).into(hollyImageView);

        gangImageView = (ImageView) v.findViewById(R.id.gangImageView);
        Glide.with(this).load(R.drawable.red_dwarf_2).into(gangImageView);


        feedbackButton = (Button) v.findViewById(R.id.feedbackButton);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                }
            }
        });

        return v;
    }



}
