package team16_3308.imageslide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private int imageCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets the menuLayout to not being visible(menu is not open on startup)
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);
        View outsideMenu = findViewById(R.id.outsideMenu);
        outsideMenu.setVisibility(View.GONE);

        RelativeLayout imagesLayout = (RelativeLayout) findViewById(R.id.imagesLayout);
        int imageDisplayWidth = (getResources().getDisplayMetrics().widthPixels - 40) / 2;

        int bottomLeftId = R.id.layoutLeftStart;
        int bottomRightId = R.id.layoutRightStart;

        //ToDO: Url needs to come from api/json and for each url retrieved code inside second for loop needs to be executed
        String[] urls = {"http://i.imgur.com/Tgs8g2o.jpg", "http://i.imgur.com/YTCuWJ9.jpg", "http://i.imgur.com/mxdD3nu.jpg", "http://i.imgur.com/I7jd1MQ.jpg?1", "https://i.imgur.com/iDNrz0i.jpg", "http://i.imgur.com/AhMrLqN.jpg", "http://i.imgur.com/z5fvyIe.jpg", "http://i.imgur.com/pRdipvw.jpg"};
        //i needs to be keep track of total number of images
        for (int i = 0; i < urls.length; i++) {
            ImageButton imageDisplay = new ImageButton(this.getApplicationContext());
            imageDisplay.setBackgroundColor(0);
            imageDisplay.setPadding(10, 10, 10, 10);
            //ToDo: move clickListener to own class/method, when clicked creates individual image activity
            imageDisplay.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.v("1", "2");
                }
            });
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
            new DownloadImage(imageDisplay, imageDisplayWidth).execute(urls[i]);
            imageCount++;
        }
    }

    /*public void createImageButton() {
        display = new ImageButton(activity);
        addClickListener();
        display.setBackgroundColor(0);
        display.setPadding(10, 10, 10, 10);
    }*/

    public void menuButtonClick(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.VISIBLE);

        View outsideMenu = findViewById(R.id.outsideMenu);
        outsideMenu.setVisibility(View.VISIBLE);
    }

    public void imageSitesButtonClick(View view) {
        //closes the menuLayout
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);
        View outsideMenu = findViewById(R.id.outsideMenu);
        outsideMenu.setVisibility(View.GONE);

        //Opens the imageSitesActivity
        Intent intent = new Intent(this,ImageSitesActivity.class);
        startActivity(intent);
    }

    public void settingsButtonClick(View view) {
        //closes the menuLayout
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);
        View outsideMenu = findViewById(R.id.outsideMenu);
        outsideMenu.setVisibility(View.GONE);

        //Opens the settingsActivity
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    public void outsideMenuClick(View view) {
        //closes the menuLayout
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);
        View outsideMenu = findViewById(R.id.outsideMenu);
        outsideMenu.setVisibility(View.GONE);
    }
}
