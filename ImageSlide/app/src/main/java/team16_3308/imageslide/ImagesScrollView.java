package team16_3308.imageslide;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

/** ScrollView that will contain multiple images for the main layout. */
public class ImagesScrollView extends ScrollView {

    private int imageCount = 0;
    private int bottomLeftId;
    private int bottomRightId;
    private int style;

    /** Basic constructor
     *
     * @param context Context of the activity that the ImageScrollView is being added to.
     */
    public ImagesScrollView(Context context) {
        super(context);
    }

    /** Basic constructor with attributes.
     *
     * @param context Context of the activity that the ImageScrollView is being added to.
     * @param attributeSet Attribute parameters.
     */
    public ImagesScrollView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }

    /** Recognizes when the ScrollView has been moved up or down.  Checks to see if the ScrollView is at the bottom, if so the application adds more images.
     *
     * @param x mScrollX
     * @param y mScrollY
     * @param oldX oldX
     * @param oldY oldY
     */
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        View view = getChildAt(getChildCount() - 1);
        int diff = view.getBottom() - (getHeight() + getScrollY());
        if(diff == 0) {
            Log.v("5", "5");
        } else {
            super.onScrollChanged(x, y, oldX, oldY);
        }
    }

    /** Loads the first set of images into the ScrollView. */
    protected void loadInitialImages(ArrayList<String> urls) {
        style = 1;
        bottomLeftId = R.id.layoutLeftStart;
        bottomRightId = R.id.layoutRightStart;

        //ToDO: Url needs to come from api/json and for each url retrieved code inside second for loop needs to be executed
        //String[] urls = {"http://i.imgur.com/Tgs8g2o.jpg", "http://i.imgur.com/YTCuWJ9.jpg", "http://i.imgur.com/mxdD3nu.jpg", "http://i.imgur.com/I7jd1MQ.jpg?1", "https://i.imgur.com/iDNrz0i.jpg", "http://i.imgur.com/AhMrLqN.jpg", "http://i.imgur.com/z5fvyIe.jpg", "http://i.imgur.com/pRdipvw.jpg"};
        for (int i = 0; i < urls.size(); i++) {
            loadImage(urls.get(i));
        }
    }

    /** Loads an individual image into the ScrollView from a url.
     *
     * @param url Url that the image will be loaded from.
     */
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
        new DownloadImage(imageDisplay, imageDisplayWidth, imageDisplayWidth, style).execute(url);
        imageCount++;
    }
}
