package team16_3308.imageslide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**Activity that is used to select where to get images from for the application. */
public class ImageSitesActivity extends AppCompatActivity {

    /** Basic initialization of ImageSitesActivity.
     *
     * @param savedInstanceState Saved state of previous application instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_sites);
    }

    /** Finishes/closes the activity and returns to the previous activity.
     *
     * @param view Reference to the backButton.
     */
    public void backButtonClick(View view) {
        finish();
    }
}
