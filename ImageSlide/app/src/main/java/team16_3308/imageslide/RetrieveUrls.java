package team16_3308.imageslide;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RetrieveUrls {

    ArrayList<String> subredditList;
    ArrayList<String> finalUrls;

    public RetrieveUrls(Context context) {
        finalUrls = new ArrayList<>();
        readDB(context);
        interpretSubreddits();
    }

    public void readDB(Context context) {
        DBHandler db = new DBHandler(context);

        db.open();
        try{
        }catch(Exception e){}

        subredditList = db.readAll();
    }

    public void interpretSubreddits() {
        for (int i=0; i < subredditList.size(); i++) {
            String rawJSON = retrieveJSON(subredditList.get(i));
            parseJSON(rawJSON);
        }
    }

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
