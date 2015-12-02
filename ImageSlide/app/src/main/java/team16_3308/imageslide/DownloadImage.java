package team16_3308.imageslide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;

import java.io.InputStream;

public class DownloadImage extends AsyncTask <String, Void, Bitmap> {
    ImageButton panel;
    int displayWidthSample;
    int displayWidth;
    int style;

    public DownloadImage(ImageButton panel, int displayWidthSample, int displayWidth, int style) {
        this.panel = panel;
        this.displayWidthSample = displayWidthSample;
        this.displayWidth = displayWidth;
        this.style = style;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap image = null;
        try {
            InputStream input = new java.net.URL(url).openStream();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, null, options);

            final int width = options.outWidth;
            int inSampleSize = 1;

            if (width > displayWidthSample) {

                final int halfWidth = width / 2;

                while ((halfWidth / inSampleSize) > displayWidthSample) {
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
        int originalImageWidth = image.getWidth();
        int originalImageHeight = image.getHeight();
        int newImageWidth = originalImageWidth;
        int newImageHeight = originalImageHeight;

        if (originalImageWidth != displayWidth) {
            newImageWidth = displayWidth;
            newImageHeight = (newImageWidth * originalImageHeight) / originalImageWidth;
        }

        Bitmap scaledImage = Bitmap.createScaledBitmap(image, newImageWidth, newImageHeight, true);

        panel.setImageBitmap(scaledImage);
    }
}
