package team16_3308.imageslide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/** Settings activity for application, contains various settings that can be adjusted.*/
public class SettingsActivity extends AppCompatActivity {

    /** Basic initialization of SettingsActivity.
     *
     * @param savedInstanceState Saved state of previous application instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


    }

    /** Finishes/closes the settings activity and returns to the previous activity.
     *
     * @param view Reference to the backButton.
     */
    public void backButtonClick(View view) {
        finish();
    }

}
