package es.tipolisto.breeds.ui.adapters;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewClickListener implements RecyclerView.OnItemTouchListener {
    GestureDetector gestureDetector;
    private OnItemViewClickListener onItemViewClickListener;
    public RecyclerViewClickListener(Context context, final RecyclerView recyclerView, OnItemViewClickListener listener){
        onItemViewClickListener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && onItemViewClickListener != null) {
                    onItemViewClickListener.onItemClick(child,recyclerView.getChildAdapterPosition(child));
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && onItemViewClickListener != null) {
                    onItemViewClickListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && onItemViewClickListener != null && gestureDetector.onTouchEvent(e)) {
            onItemViewClickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    public interface OnItemViewClickListener{
        void onItemClick(View view, int position);
        void onLongItemClick(View view,int position);
    }
}
