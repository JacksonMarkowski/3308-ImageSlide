package team16_3308.imageslide;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import android.util.Log;

public class DownloadSubreddit extends AsyncTask<String, Void, String> {

    String url2;
    String rawData = "2";

    public DownloadSubreddit(String url) {
        this.url2 = url;
    }

    protected String doInBackground(String... subreddits) {
        String subreddit = subreddits[0];

        String url = "http://www.reddit.com/r/" +  subreddit + ".json";
        HttpURLConnection connect = getConnection(url);
        if (connect == null) {
            return null;
        }
        try {
            StringBuffer buff = new StringBuffer(8192);
            String temp;
            BufferedReader buffRead = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            while((temp = buffRead.readLine())!= null)
                buff.append(temp).append("\n");
            buffRead.close();
            return buff.toString();
        } catch(IOException e) {
            Log.d("READ FAILED", e.toString());
            return null;
        }
    }

    protected String returnData() {
        return rawData;
    }

    public static HttpURLConnection getConnection(String url)
    {
        //String url = "http://www.reddit.com/r/" + subreddit + ".json";
        //System.out.println("URL: "+url);
        HttpURLConnection connect = null;
        try
        {
            connect=(HttpURLConnection)new URL(url).openConnection();
            connect.setReadTimeout(20000); // 20 second time out.
            connect.setRequestProperty("User-Agent", "imageSlide");
        }
        catch (MalformedURLException e)
        {
            Log.e("getConnection()",
                    "Invalid URL: "+e.toString());
        }
        catch (IOException e)
        {
            Log.e("getConnection()",
                    "Could not connect: "+e.toString());
        }
        return connect;
    }
}