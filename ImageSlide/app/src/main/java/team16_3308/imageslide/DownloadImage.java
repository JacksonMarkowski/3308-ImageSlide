package team16_3308.imageslide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;

import java.io.InputStream;

public class DownloadImage extends AsyncTask <String, Void, Bitmap> {
    ImageButton panel;

    public DownloadImage(ImageButton panel) {
        this.panel = panel;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap image = null;
        try {
            InputStream input = new java.net.URL(url).openStream();
            //image = BitmapFactory.decodeStream(input);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, null, options);

            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > 500 || width > 500) {

                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfHeight / inSampleSize) > 500
                        && (halfWidth / inSampleSize) > 500) {
                    inSampleSize *= 2;
                }
            }
            options.inSampleSize = inSampleSize;
            options.inJustDecodeBounds = false;

            input.close();
            input = new java.net.URL(url).openStream();
            image = BitmapFactory.decodeStream(input, null, options);
        } catch (Exception e) {
            Log.e("DownloadImageError", e.getMessage());
        }
        return image;
    }

    protected void onPostExecute(Bitmap image) {
        panel.setImageBitmap(image);
    }
}
