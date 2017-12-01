package ie.freetime.reddwarf.Custom;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;

import java.util.List;

import ie.freetime.reddwarf.Models.LeaderBoardModel;
import ie.freetime.reddwarf.R;

import static ie.freetime.reddwarf.R.string.fa_check;


/**
 * Created by flyin on 19/08/2017.
 */

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView userNameTV, totalScoreAndTimeTaken, timeRemainingAndBonusTV,
                        rightWrongAndPassTV, userLifeTimeScoreTV, openImage, closeImage, rank;
        public TableRow leaderboardRow, answerDetailsRow, userLifetimeRow, timeRemainingRow;
        public boolean open;


        public ViewHolder(View v) {
            super(v);

            userNameTV = (TextView) v.findViewById(R.id.userNameTV);
            totalScoreAndTimeTaken = (TextView) v.findViewById(R.id.totalScoreAndTimeTakenTV);
            timeRemainingAndBonusTV = (TextView) v.findViewById(R.id.timeRemainingAndBonusTV);
            rightWrongAndPassTV = (TextView) v.findViewById(R.id.rightWrongAndPassTV);
            userLifeTimeScoreTV = (TextView) v.findViewById(R.id.userLifeTimeScoreTV);
            openImage = (TextView) v.findViewById(R.id.openImage);
            closeImage = (TextView) v.findViewById(R.id.closeImage);
            rank = (TextView) v.findViewById(R.id.rank);
            leaderboardRow = (TableRow) v.findViewById(R.id.leaderboardRow);
            answerDetailsRow = (TableRow) v.findViewById(R.id.answerDetailsRow);
            userLifetimeRow = (TableRow) v.findViewById(R.id.userLifetimeRow);
            timeRemainingRow = (TableRow) v.findViewById(R.id.timeRemainingRow);
            open = false;

            leaderboardRow.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.leaderboardRow:
                    toggleDrawer();
                    break;
            }

        }

        public void toggleDrawer(){
            if(!open){
                open = true;
                answerDetailsRow.setVisibility(View.VISIBLE);
                userLifetimeRow.setVisibility(View.VISIBLE);
                openImage.setVisibility(View.GONE);
                closeImage.setVisibility(View.VISIBLE);
            }else {
                open = false;
                answerDetailsRow.setVisibility(View.GONE);
                userLifetimeRow.setVisibility(View.GONE);
                openImage.setVisibility(View.VISIBLE);
                closeImage.setVisibility(View.GONE);
            }
        }
    }


    private List<LeaderBoardModel> values;
    private Activity activity;
    private Resources resources;

    // Provide a suitable constructor (depends on the kind of dataset)
    public LeaderboardAdapter(Activity activityIn, List<LeaderBoardModel> valuesIn, Resources resourcesIn) {
        activity = activityIn;
        values = valuesIn;
        resources = resourcesIn;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LeaderboardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.leaderboard_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        LeaderBoardModel leaderBoardModel = values.get(position);
        int ranking = position+1;
        holder.rank.setText("" + ranking);
        holder.open = false;

        if(ranking == 1) {
            holder.userNameTV.setText("Ace "+leaderBoardModel.getUser());
        } else if(ranking == 2){
            holder.userNameTV.setText("Captain "+leaderBoardModel.getUser());
        }else if(ranking > 2 && ranking <10){
            holder.userNameTV.setText("Exec. Officer "+leaderBoardModel.getUser());
        }else if(ranking >= 10 && ranking <20){
            holder.userNameTV.setText("Officer "+leaderBoardModel.getUser());
        }else if(ranking >= 20 && ranking <30){
            holder.userNameTV.setText("1st Technician "+leaderBoardModel.getUser());
        }else if(ranking >= 30 && ranking <40){
            holder.userNameTV.setText("2nd Technician "+leaderBoardModel.getUser());
        }else if(ranking >= 40 && ranking <50){
            holder.userNameTV.setText("3rd Technician "+leaderBoardModel.getUser());
        }else if(ranking >= 50 && ranking <60){
            holder.userNameTV.setText("Scutter "+leaderBoardModel.getUser());
        }else{
            holder.userNameTV.setText("Canary "+leaderBoardModel.getUser());
        }

        holder.rightWrongAndPassTV.setText("Correct Answers:    "+leaderBoardModel.getRightAnswers()+" - earned "+leaderBoardModel.getScore()+"pts"
        +"\nIncorrect Answers:  "+leaderBoardModel.getWrongAnswers()
        +"\nQuestions Passed:   "+leaderBoardModel.getPasses());
        holder.totalScoreAndTimeTaken.setText(leaderBoardModel.getTotal()+"pts in "+leaderBoardModel.getTimeTaken());
        holder.timeRemainingAndBonusTV.setText(leaderBoardModel.getTimeLeft()+" remaining earned "+leaderBoardModel.getBonus()+"pts");

        Typeface iconFont = FontManager.getTypeface(holder.openImage.getContext(), FontManager.FONTAWESOME);
        holder.openImage.setTypeface(iconFont);

        Typeface iconFontClose = FontManager.getTypeface(holder.closeImage.getContext(), FontManager.FONTAWESOME);
        holder.closeImage.setTypeface(iconFontClose);

        if(!holder.open){
            holder.open = false;
            holder.answerDetailsRow.setVisibility(View.GONE);
            holder.userLifetimeRow.setVisibility(View.GONE);
            holder.openImage.setVisibility(View.VISIBLE);
            holder.closeImage.setVisibility(View.GONE);
        } else {
            holder.open = true;
            holder.answerDetailsRow.setVisibility(View.VISIBLE);
            holder.userLifetimeRow.setVisibility(View.VISIBLE);
            holder.openImage.setVisibility(View.GONE);
            holder.closeImage.setVisibility(View.VISIBLE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

    private Context getActivity(){
        return activity;
    }
}
