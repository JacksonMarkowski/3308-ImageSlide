package team16_3308.imageslide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

/** Startup activity for application.*/
public class MainActivity extends AppCompatActivity {

    /** Basic initialization of MainActivity.
     *
     * @param savedInstanceState Saved state of previous application instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //sets the menuLayout to not being visible(menu is not open on startup)
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);
        View outsideMenu = findViewById(R.id.outsideMenu);
        outsideMenu.setVisibility(View.GONE);

        ImagesScrollView scrollView = (ImagesScrollView) findViewById(R.id.scrollView);
        scrollView.loadInitialImages();
    }

    /** Displays the side menu on the screen.
     *
     * @param view Reference to the menuButton.
     */
    public void menuButtonClick(View view) {
        //Opens the menu
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.VISIBLE);

        View outsideMenu = findViewById(R.id.outsideMenu);
        outsideMenu.setVisibility(View.VISIBLE);
    }

    /** Starts an instance of the imageSites Activity.
     *
     * @param view Reference to the imageSitesButton.
     */
    public void imageSitesButtonClick(View view) {
        closeMenu(view);

        //Opens the imageSitesActivity
        Intent intent = new Intent(this,ImageSitesActivity.class);
        startActivity(intent);
    }

    /** Starts an instance of the settings Activity.
     *
     * @param view Reference to the settingsButton.
     */
    public void settingsButtonClick(View view) {
        closeMenu(view);
        InterpretData test = new InterpretData();
        test.returnPicURLS();

        //Opens the settingsActivity
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    /** Recognizes when a click outside the menu has occurred.
     *
     * @param view Reference to the outsideMenu view.
     */
    public void outsideMenuClick(View view) {
        closeMenu(view);
    }

    /** Closes the menu so it is no longer being displayed.
     *
     * @param view Reference to the menu layout.
     */
    public void closeMenu(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.menuLayout);
        layout.setVisibility(View.GONE);
        View outsideMenu = findViewById(R.id.outsideMenu);
        outsideMenu.setVisibility(View.GONE);
    }
}
