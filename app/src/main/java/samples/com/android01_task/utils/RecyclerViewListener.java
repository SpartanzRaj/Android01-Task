package samples.com.android01_task.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerViewListener implements RecyclerView.OnItemTouchListener {

    OnRecyclerItemClickListener onRecyclerItemClickListener;
    GestureDetector gestureDetector;

    public RecyclerViewListener(OnRecyclerItemClickListener onRecyclerItemClickListener, Context context) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }
        });
    }



    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View itemView = rv.findChildViewUnder(e.getX(),e.getY());
        
        Log.i("QuestionActivity","recycler view touch event");
        if (itemView != null && onRecyclerItemClickListener!=null && gestureDetector.onTouchEvent(e))
        {
            onRecyclerItemClickListener.onClick(itemView,rv.getChildLayoutPosition(itemView));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {



    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnRecyclerItemClickListener
    {
        void onClick(View view,int position);
    }
}
