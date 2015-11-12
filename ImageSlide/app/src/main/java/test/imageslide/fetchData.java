package test.imageslide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.util.Log;

/**
 * This class shall serve as a utility class that handles network
 * connections.
 *
 * @author Hathy THANK YOU HATHY.
 */
public class fetchData
{
    /**
     * This methods returns a Connection to the specified URL,
     * with necessary properties like timeout and user-agent
     * set to your requirements.
     *
     * @param subreddit
     * @return
     */
    public static HttpURLConnection getConnection(String subreddit)
    {
        String url = "http://www.reddit.com/r/" + subreddit + ".json";
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


    /**
     * A very handy utility method that reads the contents of a URL
     * and returns them as a String.
     *
     * @param subreddit
     * @return
     */
    public static String readContents(String subreddit)
    {
        String url = "http://www.reddit.com/r/" + subreddit + ".json";
        HttpURLConnection connect = getConnection(url);
        if(connect == null) return null;
        try
        {
            StringBuffer buff = new StringBuffer(8192);
            String temp ="";
            BufferedReader buffRead = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            while((temp = buffRead.readLine())!= null)
                buff.append(temp).append("\n");
            buffRead.close();
            return buff.toString();
        }
        catch(IOException e)
        {
            Log.d("READ FAILED", e.toString());
            return null;
        }
    }
}