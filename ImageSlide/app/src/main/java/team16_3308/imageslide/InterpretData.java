package team16_3308.imageslide;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import java.io.FileOutputStream;

public class InterpretData
{
    String urls;
    String beginurls;
    String endurls;
    List<String> subList = new ArrayList<>();
    List<String> picURL = new ArrayList<>();

    //Pass in subreddit name from the imageSource.java file, that's where everything else is handled.
    public InterpretData(/*Context ctx*/)
    {
        /*
        DBHandler db = new DBHandler(ctx);

        db.open();
        try{
        }catch(Exception e){}

        subList = db.readAll();
        */
        subList.add("superbowl");
        for (String temp : subList)
        {
            getPicURL(temp);
        }

        //returnPicURLS();
    }
    //After initializing the interpretData just call the getPicURL function to get the file you need. Alternative: Store the urls somewhere as a file.
    private void getPicURL(String subreddit)
    {
        FetchData fetch = new FetchData(subreddit);
        fetch.execute();
        String raw = fetch.returnJSON();
        //String raw = fetch.readContents(subreddit);
        Log.v(raw, raw);

        if(raw != "invalid") {
            try {
                JSONObject data = new JSONObject(raw).getJSONObject("data");
                JSONArray children = data.getJSONArray("children");
                //Have to get url from this.
                for (int i = 0; i < children.length(); i++) {
                    JSONObject url = children.getJSONObject(i).getJSONObject("url");
                    urls = url.toString();
                    beginurls = urls.substring(0, 16);
                    endurls = urls.substring(urls.length() - 4, urls.length());
                    if (beginurls.equals("http://imgur.com/") && (endurls.equals(".png") || endurls.equals(".jpg"))) {
                        picURL.add(urls);
                        Log.v("Adding url", "AAAAAAAAAAAAAAAA");
                    }
                }
            } catch (Exception e) {
                Log.e("fetchPosts()", e.toString());
            }
        }
    }
    public List<String> returnPicURLS()
    {
        for( String temp: picURL)
        {
            Log.v("test", temp);
        }
        return picURL;
    }
}
