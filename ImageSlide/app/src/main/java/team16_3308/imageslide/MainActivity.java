package team16_3308.imageslide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "team16_3308.imageslide.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets the menuLayout to not being visible(menu is not open on startup)
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);


        //Tests for ImagePanel
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.desert);
        ImagePanel p = new ImagePanel(this, bitmap, "Title", "http://link", "Description");
        p.scaleImageView();
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.penguins);
        ImagePanel p2 = new ImagePanel(this, bitmap2, "Title", "http://link", "Description");
        p2.scaleImageView();

        //LoadImageFromWebOperations("Google");
    }

    //Handles touchEvents for the activity
    public boolean onTouchEvent(MotionEvent e) {

        Log.v("Working", "kinda");
        return false;
    }

    public void menuButtonClick(View view) {
        //TextView textView = new TextView(this);
        //textView.setTextSize(40);
        //textView.setText("Hello");
        //RelativeLayout layout = (RelativeLayout) findViewById(R.id.menuLayout);
        //layout.addView(textView);
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


    public static Drawable LoadImageFromWebOperations(String url)
    {
        try{
            InputStream is = (InputStream) new URL("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png").getContent();
            Drawable d = Drawable.createFromStream(is, "Google");
            return d;}
        catch(Exception e)
        {
            return null;
        }

    }
}
