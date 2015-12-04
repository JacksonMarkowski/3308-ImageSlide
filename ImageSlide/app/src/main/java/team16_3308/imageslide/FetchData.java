package team16_3308.imageslide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

import android.os.AsyncTask;
import android.util.Log;

/**
 * This class shall serve as a utility class that handles network
 * connections.
 *
 * THANK YOU HATHY.
 */
public class FetchData extends AsyncTask <String, Void, Void>
{
    private String url;
    private String JSONText = "invalid";
    /**
     * This methods establishes a connection with a url, then parses that url for json.
     *
     * Without the AsyncTask extension, it gets mad, can't run network activities on the main activiity.
     */
    public FetchData(String subreddit)
    {
        url = "http://www.reddit.com/r/" + subreddit + ".json";
        Log.v("In the constructor", "In the constructor");
    }
    protected Void doInBackground(String... test) {
        HttpURLConnection connect;
        BufferedReader buffRead = null;
        try {
            connect = (HttpURLConnection) new URL(url).openConnection();
            connect.setReadTimeout(20000); // 20 second time out.
            connect.setRequestProperty("User-Agent", "imageSlide");
            buffRead = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        } catch (MalformedURLException e) {
            Log.e("getConnection()",
                    "Invalid URL: " + e.toString());
        } catch (IOException e) {
            Log.e("getConnection()",
                    "Could not connect: " + e.toString());
        }

        if (buffRead == null) return null;
        else {
            try {
                StringBuffer buff = new StringBuffer(8192);
                String temp;
                while ((temp = buffRead.readLine()) != null) {
                    buff.append(temp).append("\n");
                    Log.v(" In try to buff read", " help");
                }

                buffRead.close();
                if (temp != null) {
                    Log.v("SETTING EQUAL HERE", "SETTING");
                    JSONText = buff.toString();
                }
            } catch (IOException e) {
                Log.d("READ FAILED", e.toString());
                return null;
            }
            //return buffRead;
            return null;
        }
    }
    /**
     *Returns a string containing JSON
     *
     * @return
     */
    protected String returnJSON()
    {
        return JSONText;
    }

}