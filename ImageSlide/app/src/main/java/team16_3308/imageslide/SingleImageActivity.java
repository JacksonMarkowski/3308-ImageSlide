package team16_3308.imageslide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class SingleImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_image);

        //sets the menuLayout to not being visible(menu is not open on startup)
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);
        View outsideMenu = findViewById(R.id.outsideMenu);
        outsideMenu.setVisibility(View.GONE);
    }

    public void backButtonClick(View view) {
        finish();
    }

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

}
