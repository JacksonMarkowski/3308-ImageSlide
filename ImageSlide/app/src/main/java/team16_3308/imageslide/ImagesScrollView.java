package team16_3308.imageslide;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class ImagesScrollView extends ScrollView {

    private int imageCount = 0;
    private int bottomLeftId;
    private int bottomRightId;

    public ImagesScrollView(Context context) {
        super(context);
    }

    public ImagesScrollView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = (View)getChildAt(getChildCount()-1);
        int d = view.getBottom();
        d -= (getHeight()+getScrollY());
        if(d==0)
        {
            Log.v("5", "5");
        }
        else
            super.onScrollChanged(l,t,oldl,oldt);
    }

    protected void loadInitialImages() {
        int bottomLeftId = R.id.layoutLeftStart;
        int bottomRightId = R.id.layoutRightStart;

        //ToDO: Url needs to come from api/json and for each url retrieved code inside second for loop needs to be executed
        String[] urls = {"http://i.imgur.com/Tgs8g2o.jpg", "http://i.imgur.com/YTCuWJ9.jpg", "http://i.imgur.com/mxdD3nu.jpg", "http://i.imgur.com/I7jd1MQ.jpg?1", "https://i.imgur.com/iDNrz0i.jpg", "http://i.imgur.com/AhMrLqN.jpg", "http://i.imgur.com/z5fvyIe.jpg", "http://i.imgur.com/pRdipvw.jpg"};
        for (int i = 0; i < urls.length; i++) {
            loadImage(urls[i]);
        }
    }

    protected void loadImage(String url) {
        RelativeLayout imagesLayout = (RelativeLayout) findViewById(R.id.imagesLayout);
        int imageDisplayWidth = (getResources().getDisplayMetrics().widthPixels - 40) / 2;

        SingleImageButton imageDisplay = new SingleImageButton(getContext().getApplicationContext());
        imageDisplay.url = url;
        int newId = View.generateViewId();
        imageDisplay.setId(newId);

        if (imageCount % 2 == 0) {
            RelativeLayout.LayoutParams paramsLeft = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            paramsLeft.addRule(RelativeLayout.ALIGN_PARENT_START);
            paramsLeft.addRule(RelativeLayout.ALIGN_END, R.id.layoutCenter);
            paramsLeft.addRule(RelativeLayout.BELOW, bottomLeftId);
            imageDisplay.setLayoutParams(paramsLeft);
            bottomLeftId = newId;
        } else {
            RelativeLayout.LayoutParams paramsRight = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            paramsRight.addRule(RelativeLayout.ALIGN_PARENT_END);
            paramsRight.addRule(RelativeLayout.ALIGN_START, R.id.layoutCenter);
            paramsRight.addRule(RelativeLayout.BELOW, bottomRightId);
            imageDisplay.setLayoutParams(paramsRight);
            bottomRightId = newId;
        }

        imagesLayout.addView(imageDisplay);
        new DownloadImage(imageDisplay, imageDisplayWidth, imageDisplayWidth, 1).execute(url);
        imageCount++;
    }
}
