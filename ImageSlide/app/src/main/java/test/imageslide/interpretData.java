package test.imageslide;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class interpretData
{
    String subreddit;
    String urls;
    int count = 0;

    //Pass in subreddit name from the imageSource.java file, that's where everything else is handled.
    public interpretData(String subr)
    {
        subreddit = subr;
    }
    //After initializign the interpretData just call the getPicURL function to get the file you need. Alternative: Store the urls somewhere as a file.
    public List<String> getPicURL()
    {
        String raw = fetchData.readContents(subreddit);
        List<String> picURL = new ArrayList<>();
        try
        {
            JSONObject data = new JSONObject(raw).getJSONObject("data");
            JSONArray children = data.getJSONArray("children");
            //Have to get url from this.
            for (int i = 0; i < children.length(); i++)
            {
                JSONObject url = children.getJSONObject(i).getJSONObject("url");
                urls = url.toString();
                urls = urls.substring(0, 16);
                if (urls.equals("http://imgur.com/"))
                {
                    picURL.add(urls);
                    count++;
                }
            }
        }
        catch(Exception e)
        {
            Log.e("fetchPosts()", e.toString());
        }
        return picURL;
    }
}
