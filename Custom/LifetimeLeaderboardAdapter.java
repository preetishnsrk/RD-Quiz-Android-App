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

import java.util.List;

import ie.freetime.reddwarf.Models.LifetimeLeaderboardModel;
import ie.freetime.reddwarf.R;

/**
 * Created by flyin on 20/09/2017.
 */

public class LifetimeLeaderboardAdapter extends RecyclerView.Adapter<LifetimeLeaderboardAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView rank, name, lifetimeScore, gamesPlayed;

        public ViewHolder(View v) {
            super(v);

            rank = (TextView) v.findViewById(R.id.llrank);
            name = (TextView) v.findViewById(R.id.lluserNameTV);
            lifetimeScore = (TextView) v.findViewById(R.id.lifetimeScoreTV);
            gamesPlayed = (TextView) v.findViewById(R.id.llgamesPlayedTV);

        }
    }
    
    private List<LifetimeLeaderboardModel> values;
    private Activity activity;
    private Resources resources;
    
    public LifetimeLeaderboardAdapter(Activity activityIn, List<LifetimeLeaderboardModel> valuesIn,
                                      Resources resourcesIn){
        activity = activityIn;
        values = valuesIn;
        resources = resourcesIn;
    }
    

    @Override
    public LifetimeLeaderboardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.lifetime_leaderboard_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LifetimeLeaderboardAdapter.ViewHolder holder, int position) {
        
        LifetimeLeaderboardModel thisItem = values.get(position);
         
        int ranking = position+1;
        holder.rank.setText(""+ranking);

        if(ranking == 1) {
            holder.name.setText("Ace "+thisItem.getUser());
        } else if(ranking == 2){
            holder.name.setText("Captain "+thisItem.getUser());
        }else if(ranking > 2 && ranking <10){
            holder.name.setText("Exec. Officer "+thisItem.getUser());
        }else if(ranking >= 10 && ranking <20){
            holder.name.setText("Officer "+thisItem.getUser());
        }else if(ranking >= 20 && ranking <30){
            holder.name.setText("1st Technician "+thisItem.getUser());
        }else if(ranking >= 30 && ranking <40){
            holder.name.setText("2nd Technician "+thisItem.getUser());
        }else if(ranking >= 40 && ranking <50){
            holder.name.setText("3rd Technician "+thisItem.getUser());
        }else if(ranking >= 50 && ranking <60){
            holder.name.setText("Scutter "+thisItem.getUser());
        }else{
            holder.name.setText("Canary "+thisItem.getUser());
        }

        holder.lifetimeScore.setText("Lifetime Score: "+thisItem.getScore()+" points");
        holder.gamesPlayed.setText("Rounds played: "+thisItem.getGameCount());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

    private Activity getActivity(){
        return activity;
    }
}
