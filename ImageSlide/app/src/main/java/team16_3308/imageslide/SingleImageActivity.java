package team16_3308.imageslide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/** Activity for displaying a single image on the screen.*/
public class SingleImageActivity extends AppCompatActivity {

    private String url;

    /** Basic initialization of SingleImageActivity.
     *
     * @param savedInstanceState Saved state of previous application instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        Intent intent = getIntent();
        url = intent.getExtras().getString("url");

        addImage(url);
    }

    /** Adds an image to the image button of the activity.
     *
     * @param url Url that the image will be loaded from.
     */
    public void addImage(String url) {
        ImageButton image = (ImageButton) findViewById(R.id.singleImage);
        int imageDisplayWidth = (getResources().getDisplayMetrics().widthPixels - 40);
        int imageDisplayWidthSample = imageDisplayWidth / 2;
        new DownloadImage(image, imageDisplayWidthSample, imageDisplayWidth, 3).execute(url);
    }

    /** Finishes/closes the activity and returns to the previous activity.
     *
     * @param view Reference to the backButton.
     */
    public void backButtonClick(View view) {
        finish();
    }


}
