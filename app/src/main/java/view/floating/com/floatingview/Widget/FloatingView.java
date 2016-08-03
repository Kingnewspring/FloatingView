package view.floating.com.floatingview.Widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import view.floating.com.floatingview.Ui.H5;
import view.floating.com.floatingview.R;
import view.floating.com.floatingview.Util.CountDownTimer;

/**
 * 项目名称：FloatingView
 * 类描述：
 * 创建人：Wangxc
 * 创建时间：2016/8/1 16:52
 * 修改人：Administrator
 * 修改时间：2016/8/1 16:52
 * 修改备注：
 */
public class FloatingView {
    private static PopupWindow popup;
    private ViewGroup viewGroup;
    private  int lastX,lastY,dx,dy,first_eventx,second_eventx,first_eventy,second_eventy;
    private static ImageView gifimg;
    private static boolean flag = true;
    /** 屏幕的坐标 */
    public static int mScreenX ,mScreenY;
    /** 计时器,这里的CountDownTimer相对于安卓自带的进行了修改，可以cancel */
    private TimeCount timeCount;
    public static int ScreenWidth,ScreenHeight;
    /** 图标距离屏幕顶部的百分比 */
    private  int icon_top;
    /** 图标距离屏幕右边的百分比 */
    private  int icon_right;
    /** 图标点击相应跳转的url */
    private  String click_action;
    /** 图标显示的时间 秒*/
    private  int icon_staty;

    private Context mContext;

    public FloatingView(){
    }

    public  FloatingView(Context context){
        initView(context);
    }

    public void setIcon_top(int icon_top){
        this.icon_top = icon_top;
    }
    public void setIcon_right(int icon_right){
        this.icon_right = icon_right;
    }
    public void setIcon_staty(int icon_staty){
        this.icon_staty = icon_staty;
    }

    public void setclick_action(String url){
        this.click_action = url;
    }


    private  void initView(Context context){

        mContext = context;
        viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.hzsdk_pop, null);
        gifimg = (ImageView) viewGroup.findViewById(R.id.hzsdk_img);

        DisplayMetrics metric = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metric);
        ScreenWidth = metric.widthPixels;
        ScreenHeight = metric.heightPixels;
    }

    public  void show(Bitmap bitmap){
        mScreenY = -icon_top * (ScreenHeight / 100) ;
        mScreenX = (100 - icon_right) * ScreenWidth / 100 - 100 ;
        initGif(bitmap);
    }

    public void show(int resoureid){
        mScreenY = -icon_top * (ScreenHeight / 100) ;
        mScreenX = (100 - icon_right) * ScreenWidth / 100 - 100 ;
        createPopup(viewGroup);
        gifimg.setImageResource(resoureid);
    }


    private  void createPopup(View view)
    {
        if (popup != null) {
            popup.dismiss();
            popup = null;
        }
        view.findViewById(R.id.hzsdk_img).setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        first_eventx = mScreenX;
                        first_eventy = mScreenY;

                        break;
                    case MotionEvent.ACTION_UP:
                        mScreenX = dx;
                        mScreenY = dy;
                        second_eventx = dx;
                        second_eventy = dy;

                        if (second_eventx - first_eventx <= 5
                                && second_eventy - first_eventy <= 5
                                && second_eventy - first_eventy > -5
                                && second_eventx - first_eventx > -5) {
                            if (click_action != null) {
                                Intent it = new Intent(mContext, H5.class);
                                it.putExtra("url", click_action);
                                mContext.startActivity(it);
                            }
                            return false;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        dx = (int) event.getRawX() - lastX + mScreenX;
                        dy = lastY - (int) event.getRawY() + mScreenY;
                        popup.update(dx, -dy, -1, -1);
                        break;
                }
                return true;
            }
        });


        if (popup == null)
            popup = new PopupWindow(view);
        popup.setWidth(ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        popup.setHeight(ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        popup.setFocusable(false);
        popup.setTouchable(true);
        popup.setOutsideTouchable(true);
        popup.showAtLocation(view, Gravity.NO_GRAVITY, mScreenX, -mScreenY);
        timeCount = new TimeCount(icon_staty * 1000,1000);
        timeCount.start();
        popup.update();
    }

    public void stop(){
        if (popup != null){
            popup.dismiss();
            popup = null;
        }
        flag = false;
    }

    public void setimageNo(){
        if (popup == null){
            return;
        }
        if (popup != null) {
            popup.dismiss();
            popup = null;
        }else {
            Log.e("popup", "is null");
        }
    }


    private class TimeCount extends CountDownTimer {

        @Override
        public void onTick(long millisUntilFinished) {
            if (!flag){
                cancel();
            }
        }

        @Override
        public void onFinish() {
            if (popup.isShowing()){
                popup.dismiss();
            }
        }
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
    }

    public void initGif(Bitmap bitmap){
        createPopup(viewGroup);
        gifimg.setImageBitmap(bitmap);
    }

}
