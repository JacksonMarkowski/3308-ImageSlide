package team16_3308.imageslide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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

    /** On button hit, sub is saved into database and activity is refreshed
     *
     * @param view Reference to the backButton
     */
    public void sendSub(View view){
        EditText editText = (EditText) findViewById(R.id.subreddit);
        String message = editText.getText().toString();
        editText.setText("", TextView.BufferType.EDITABLE);
        DBHandler db = new DBHandler(this);
        db.open();
        db.insertSub(message);

        ArrayList<String> subList = new ArrayList<String>();
        subList = db.readAll();
        ListView lv = (ListView) findViewById(R.id.subredditList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                subList
        );
        lv.setAdapter(arrayAdapter);
    }

    /** Finishes/closes the image sites activity and returns to the previous activity.
     *
     * @param view Reference to the backButton.
     */
    public void backButtonClick(View view) {
        finish();
    }

}
