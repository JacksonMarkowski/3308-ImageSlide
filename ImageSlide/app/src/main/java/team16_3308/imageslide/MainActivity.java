package team16_3308.imageslide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private boolean menuOpen = false;

    public final static String EXTRA_MESSAGE = "team16_3308.imageslide.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets the menuLayout to not being visible(menu is not open on startup)
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.desert);
        ImagePanel p = new ImagePanel(this, bitmap, "Title", "http://link", "Description");
        p.createImageView();

        //LoadImageFromWebOperations("Google");
    }

    //Handles touchEvents for the activity
    public boolean onTouchEvent(MotionEvent e) {
        /*
        if (menuOpen) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
            float x = layout.getX();
            float y = layout.getY();
            int width = layout.getWidth();
            int height = layout.getHeight();

            float xTouch = e.getX();
            float yTouch = e.getY();
            if (xTouch < x || yTouch > (y+height)) {
                layout.setVisibility(View.GONE);
                Button b = (Button) findViewById(R.id.button_menu);
                b.setVisibility(View.VISIBLE);
            }

        }
        return true;
        */
        return true;
    }

    public void menuButtonClick(View view) {
        //TextView textView = new TextView(this);
        //textView.setTextSize(40);
        //textView.setText("Hello");
        //RelativeLayout layout = (RelativeLayout) findViewById(R.id.menuLayout);
        //layout.addView(textView);
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.VISIBLE);
        menuOpen = true;
        ImageButton b1 = (ImageButton) findViewById(R.id.menuButton);
        //b1.setVisibility(View.GONE);
    }

    public void imageSitesButtonClick(View view) {
        //closes the menuLayout
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);
        menuOpen = false;

        //Re-shows the menuButton
        ImageButton b = (ImageButton) findViewById(R.id.menuButton);
        //b.setVisibility(View.VISIBLE);

        //Opens the imageSitesActivity
        Intent intent = new Intent(this,ImageSitesActivity.class);
        startActivity(intent);
    }

    public void settingsButtonClick(View view) {
        //closes the menuLayout
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);
        menuOpen = false;

        //Re-shows the menuButton
        ImageButton b = (ImageButton) findViewById(R.id.menuButton);
        //b.setVisibility(View.VISIBLE);

        //Opens the settingsActivity
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
    /*
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    */

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
