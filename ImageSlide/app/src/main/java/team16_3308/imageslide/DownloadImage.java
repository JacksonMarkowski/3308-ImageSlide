package team16_3308.imageslide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;

import java.io.InputStream;

public class DownloadImage extends AsyncTask <String, Void, Bitmap> {
    ImageButton panel;
    int displayWidth;

    public DownloadImage(ImageButton panel, int displayWidth) {
        this.panel = panel;
        this.displayWidth = displayWidth;
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

            if (width > displayWidth) {

                //final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfWidth / inSampleSize) > displayWidth) {
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

    //ToDO: Add colspan for imageButton params inorder to fix gaps between images in columns
    protected void onPostExecute(Bitmap image) {
        int originalImageWidth = image.getWidth();
        int originalImageHeight = image.getHeight();
        int newImageWidth = originalImageWidth;
        int newImageHeight = originalImageHeight;

        if (originalImageWidth > displayWidth) {
            newImageWidth = displayWidth;
            newImageHeight = (newImageWidth * originalImageHeight) / originalImageWidth;
        }

        Bitmap scaledImage = Bitmap.createScaledBitmap(image, newImageWidth, newImageHeight, true);

        panel.setImageBitmap(scaledImage);
    }
}
