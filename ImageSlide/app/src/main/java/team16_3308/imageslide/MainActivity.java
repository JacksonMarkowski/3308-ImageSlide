package team16_3308.imageslide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets the menuLayout to not being visible(menu is not open on startup)
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);

        RelativeLayout imagesLayout = (RelativeLayout) findViewById(R.id.imagesLayout);
        int imageDisplayWidth = (getResources().getDisplayMetrics().widthPixels - 40) / 2;

        int bottomLeftId = R.id.layoutLeftStart;
        int bottomRightId = R.id.layoutRightStart;

        //ToDO: Url needs to come from api/json and for each url retrieved code inside for loop needs to be executed
        String[] urls = {"http://i.imgur.com/Tgs8g2o.jpg", "http://i.imgur.com/YTCuWJ9.jpg", "http://i.imgur.com/mxdD3nu.jpg", "http://i.imgur.com/I7jd1MQ.jpg?1", "https://i.imgur.com/iDNrz0i.jpg", "http://i.imgur.com/AhMrLqN.jpg"};
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < urls.length; i++) {
                ImageButton imageDisplay = new ImageButton(this.getApplicationContext());
                imageDisplay.setBackgroundColor(0);
                imageDisplay.setPadding(10, 10, 10, 10);
                int newId = View.generateViewId();
                imageDisplay.setId(newId);

                if (i % 2 == 0) {
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
            }
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
    }

    public void imageSitesButtonClick(View view) {
        //closes the menuLayout
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);

        //Opens the imageSitesActivity
        Intent intent = new Intent(this,ImageSitesActivity.class);
        startActivity(intent);
    }

    public void settingsButtonClick(View view) {
        //closes the menuLayout
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);

        //Opens the settingsActivity
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
}
