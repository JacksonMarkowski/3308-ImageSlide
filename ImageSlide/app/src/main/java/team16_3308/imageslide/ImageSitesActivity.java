package team16_3308.imageslide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ImageSitesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_sites);
    }

    public void backButtonClick(View view) {
        finish();
    }
}
