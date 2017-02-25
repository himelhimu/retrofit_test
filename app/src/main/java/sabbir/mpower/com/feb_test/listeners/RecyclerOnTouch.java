package sabbir.mpower.com.feb_test.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sabbir on 2/25/17.
 */

public class RecyclerOnTouch implements RecyclerView.OnItemTouchListener {
    MyOnClickListener myOnClickListener;
    private GestureDetector mGestureDetector;

   public RecyclerOnTouch(Context context,final RecyclerView recyclerView,final MyOnClickListener myOnClickListener){
       this.myOnClickListener=myOnClickListener;
       mGestureDetector=new GestureDetector(context, new GestureDetector.OnGestureListener() {
           @Override
           public boolean onDown(MotionEvent e) {
               return false;
           }

           @Override
           public void onShowPress(MotionEvent e) {

           }

           @Override
           public boolean onSingleTapUp(MotionEvent e) {
               return false;
           }

           @Override
           public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
               return false;
           }

           @Override
           public void onLongPress(MotionEvent e) {
               View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
               if (child!=null && myOnClickListener!=null){
                   myOnClickListener.onLongClick(child,recyclerView.getChildAdapterPosition(child));
               }

           }

           @Override
           public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
               return false;
           }
       });
   }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child=rv.findChildViewUnder(e.getX(),e.getY());
        if (child!=null && myOnClickListener!=null){
            myOnClickListener.onClick(child,rv.getChildAdapterPosition(child));
        }

        return true;

    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
