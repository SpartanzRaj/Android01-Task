package samples.com.android01_task.utils.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import samples.com.android01_task.R;
import samples.com.android01_task.pojos.Questions;

public class QuestionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> questionData;
    private Context activityContext;
    int DATA=0,ADVERTISEMENT=1;
    public QuestionsAdapter(List<Object> questionData, Context context) {
        this.questionData = questionData;
        activityContext=context;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView showQuestion;
        public ViewHolder(View itemView) {
            super(itemView);
            showQuestion= (TextView)itemView.findViewById(R.id.ques_view);

        }
    }

    public class AdsViewHolder extends RecyclerView.ViewHolder
    {
        ImageView bannerAds;
        public AdsViewHolder(View itemView) {
            super(itemView);
            bannerAds=(ImageView)itemView.findViewById(R.id.banner_add);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (questionData.get(position) instanceof Questions)
        {
            return DATA;
        }
        else if (questionData.get(position) instanceof Integer)
        {
            return ADVERTISEMENT;
        }

        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;

        if(viewType==DATA)
        {
            View rootView = LayoutInflater.from(activityContext).inflate(R.layout.question_row,parent,false);
            viewHolder = new ViewHolder(rootView);


        }
        else if (viewType==ADVERTISEMENT)
        {
            View rootView = LayoutInflater.from(activityContext).inflate(R.layout.recylerview_advertisment,parent,false);
            viewHolder = new AdsViewHolder(rootView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType()==DATA)
        {
            ViewHolder questionHolder =(ViewHolder)holder;
            Questions qData =(Questions) questionData.get(position);
            questionHolder.showQuestion.setText(qData.getQuestion());
        }
        else
        {
            AdsViewHolder adsViewHolder =(AdsViewHolder)holder;
        }

    }

    @Override
    public int getItemCount() {
        return questionData.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }
}
