package team16_3308.imageslide;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/** Accesses the database for all selected image sites and gets urls of images from those sites. */
public class RetrieveUrls {

    ArrayList<String> subredditList;
    ArrayList<String> finalUrls;

    /** Constructor that will execute methods in order to set finalURLs
     *
     * @param context Context of the activity that contains the database.
     */
    public RetrieveUrls(Context context) {
        finalUrls = new ArrayList<>();
        readDB(context);
        interpretSubreddits();
    }

    /** Opens and reads the database for all image sites that should be checked for images.
     *
     * @param context Context of the activity that contains the database.
     */
    public void readDB(Context context) {
        DBHandler db = new DBHandler(context);

        db.open();
        try{
        }catch(Exception e){}

        subredditList = db.readAll();
    }

    /** Iterates through subredditList and calls methods to retrieve and parseJSON on each subreddit. */
    public void interpretSubreddits() {
        for (int i=0; i < subredditList.size(); i++) {
            String rawJSON = retrieveJSON(subredditList.get(i));
            parseJSON(rawJSON);
        }
    }

    /** Parses the JSON of the json returned from a url, and gets all urls that are image format.
     *
     * @param rawJSON RawJSON from a subreddit.
     */
    public void parseJSON(String rawJSON) {
        try {

            JSONObject data = new JSONObject(rawJSON).getJSONObject("data");
            JSONArray children = data.getJSONArray("children");

            for (int i = 0; i < children.length(); i++)
            {
                String url = children.getJSONObject(i).getJSONObject("data").getString("url");
                String urlEnd = url.substring(url.length() - 4, url.length());
                if (urlEnd.equals(".jpg")) {
                    finalUrls.add(url);
                }

            }
        } catch(Exception e) {
            Log.e("Error parsing JSON", e.toString());
        }
    }

    /** Attempts to get the JSON from a subreddit url
     *
     * @param subreddit The name of the subreddit to get JSON from.
     * @return JSON retrieved or NODATA if no JSON was got.
     */
    public String retrieveJSON(String subreddit) {
        String rawJSON = "NODATA";
        try {
            rawJSON = new DownloadSubreddit("http://www.reddit.com/r/").execute(subreddit).get();
        } catch(Exception e) {
            Log.d("JSON Retrieve Fail", e.toString());
        }
        return rawJSON;
    }

    public ArrayList<String> getFinalUrls() {
        return finalUrls;
    }


}
