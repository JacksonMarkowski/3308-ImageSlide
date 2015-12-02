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



        DBHandler db = new DBHandler(this);
        db.open();
        try{
        }catch(Exception e){}

        ArrayList<String> subList = new ArrayList<String>();

        subList = db.readAll();
        //Populate list code from http://stackoverflow.com/questions/5070830/populating-a-listview-using-arraylist
        ListView lv = (ListView) findViewById(R.id.subredditList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                subList
        );

        lv.setAdapter(arrayAdapter);

    }

    public void sendSub(View view){
        Intent intent = new Intent(this, DBHandler.class);
        EditText editText = (EditText) findViewById(R.id.subreddit);
        String message = editText.getText().toString();
        DBHandler db = new DBHandler(this);
        db.open();
        db.insertSub(message);
        Intent refresh = new Intent(this, SettingsActivity.class);
        startActivity(refresh);
    }

    /** Finishes/closes the settings activity and returns to the previous activity.
     *
     * @param view Reference to the backButton.
     */
    public void backButtonClick(View view) {
        finish();
    }

}
