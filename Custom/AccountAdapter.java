package ie.freetime.reddwarf.Custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ie.freetime.reddwarf.Models.GameModel;
import ie.freetime.reddwarf.R;

/**
 * Created by flyin on 23/09/2017.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder>{
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView gameTypeTV, gameScoreTV, gameTimestampTV;
        public LinearLayout gameRow;


        public ViewHolder(View v) {
            super(v);

            gameRow = (LinearLayout) v.findViewById(R.id.gameRow);
            gameTypeTV = (TextView) v.findViewById(R.id.gameTypeTV);
            gameScoreTV = (TextView) v.findViewById(R.id.gameScoreTV);
            gameTimestampTV = (TextView) v.findViewById(R.id.gameTimeStampTV);
        }

    }


    private List<GameModel> values;
    private Activity activity;
    private Resources resources;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AccountAdapter(Activity activityIn, List<GameModel> valuesIn, Resources resourcesIn) {
        activity = activityIn;
        values = valuesIn;
        resources = resourcesIn;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.game_history_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final GameModel gameModel = values.get(position);

        String status = gameModel.getCompletionStatus();
        if(!status.contains("Completed!")){
            status = "Failed!";
        }

        String timestamp = gameModel.getTimestamp();
        StringBuilder sb = new StringBuilder(timestamp);
        sb.setCharAt(timestamp.length() - 6, ':');
        timestamp = sb.toString();

        String bonus = gameModel.getBonus();
        int bonusInt;
        int totalScore = 0;

        if(bonus == null){
            bonus = "n/a";
            totalScore = gameModel.getScore();
        }else {
            bonus = bonus.replaceAll("\\bbonus\\b", "");
            bonus = bonus.replaceAll("\\bpoints\\b", "");
            bonus = bonus.trim();
            bonusInt = Integer.parseInt(bonus);
            totalScore = gameModel.getScore()+bonusInt;
            bonus = bonus+" points";
        }

        holder.gameTypeTV.setText(gameModel.getDifficulty()+" Quiz ("+status+")");
        holder.gameScoreTV.setText("Score: "+totalScore+"\t\tTime: "+gameModel.getTimeTaken());
        holder.gameTimestampTV.setText(timestamp);


        final int finalTotalScore = totalScore;
        final String finalBonus = bonus;
        final String finalStatus = status;
        final String finalTimestamp = timestamp;
        holder.gameRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                builder.setView(inflater.inflate(R.layout.game_history_dialog, null));



                final AlertDialog alertDialog = builder.create();
                alertDialog.show();


                TextView dialogTypeTV, dialogStatusTV, dialogScoreTV, dialogTimeTakenTV, dialogTimeLeftTV,
                        dialogBonusTV, dialogRightTV, dialogWrongTV, dialogPassTV, dialogDismissTV, timestampTV;
                dialogTypeTV = (TextView) alertDialog.findViewById(R.id.dialogTypeTV);
                dialogTypeTV.setText(gameModel.getDifficulty()+" Quiz");

                dialogStatusTV = (TextView) alertDialog.findViewById(R.id.dialogStatusTV);
                dialogStatusTV.setText(finalStatus);

                dialogScoreTV = (TextView) alertDialog.findViewById(R.id.dialogScoreTV);
                dialogScoreTV.setText(finalTotalScore +" points");

                dialogTimeTakenTV = (TextView) alertDialog.findViewById(R.id.dialogTimeTakenTV);
                dialogTimeTakenTV.setText(gameModel.getTimeTaken());

                dialogTimeLeftTV = (TextView) alertDialog.findViewById(R.id.dialogTimeLeftTV);
                if(gameModel.getDifficulty().contains("Marathon")){
                    dialogTimeLeftTV.setText("n/a");
                }else {
                    if(gameModel.getTimeLeft().contains("0m1s")){
                        dialogTimeLeftTV.setText("0m0s");
                    }else {
                        dialogTimeLeftTV.setText(gameModel.getTimeLeft());
                    }
                }

                dialogBonusTV = (TextView) alertDialog.findViewById(R.id.dialogBonusTV);
                dialogBonusTV.setText(finalBonus);

                dialogRightTV = (TextView) alertDialog.findViewById(R.id.dialogRightTV);
                if(gameModel.getDifficulty().contains("Marathon")){
                    dialogRightTV.setText(gameModel.getRightString());
                }else {
                    dialogRightTV.setText(gameModel.getRightString()+" ("+gameModel.getScore()+"pts)");
                }


                dialogWrongTV = (TextView) alertDialog.findViewById(R.id.dialogWrongTV);
                dialogWrongTV.setText(gameModel.getWrongString());

                dialogPassTV = (TextView) alertDialog.findViewById(R.id.dialogPassTV);
                dialogPassTV.setText(gameModel.getPassString());

                timestampTV = (TextView) alertDialog.findViewById(R.id.timestampTV);
                timestampTV.setText(finalTimestamp);

                dialogDismissTV = (TextView) alertDialog.findViewById(R.id.dialogDismissTV);
                dialogDismissTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


            }
        });
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
