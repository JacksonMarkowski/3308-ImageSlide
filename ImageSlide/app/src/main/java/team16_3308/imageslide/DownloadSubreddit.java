package team16_3308.imageslide;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import android.util.Log;

/** Downloads the json from a reddit.com/r/. */
public class DownloadSubreddit extends AsyncTask<String, Void, String> {

    String url2;
    String rawData = "2";

    /** Constructor for DownloadSubreddit
     *
     * @param url Url that the subreddit will be appended to.
     */
    public DownloadSubreddit(String url) {
        this.url2 = url;
    }

    /** Background process of AsyncTask used for retrieving the json.
     *
     * @param subreddits Subreddit name that the json will be pulled from.
     * @return String of json retrieved from the subreddit page.
     */
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

    /** Opens a connection with a url
     *
     * @param url Url that the connection will be made to.
     * @return HTTPURLConnection that was made from the url or null if the connection failed.
     */
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