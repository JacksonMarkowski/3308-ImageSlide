package team16_3308.imageslide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SingleImageActivity extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        Intent intent = getIntent();
        url = intent.getExtras().getString("url");

        ImageButton image = (ImageButton) findViewById(R.id.singleImage);
        int imageDisplayWidth = (getResources().getDisplayMetrics().widthPixels - 40);
        int imageDisplayWidthSample = imageDisplayWidth / 2;
        new DownloadImage(image, imageDisplayWidthSample, imageDisplayWidth, 3).execute(url);
    }

    public void backButtonClick(View view) {
        finish();
    }


}
