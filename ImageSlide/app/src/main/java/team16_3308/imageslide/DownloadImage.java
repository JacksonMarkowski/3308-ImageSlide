package team16_3308.imageslide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;

import java.io.InputStream;

/** Downloads an image from a url and adds the image to a given ImageButton. */
public class DownloadImage extends AsyncTask <String, Void, Bitmap> {

    ImageButton panel;
    int displayWidthSample;
    int displayWidth;
    int style;

    /** Constructor for DownloadImage.
     *
     * @param panel ImageButton panel that the image will be added to.
     * @param displayWidthSample Width that will be used to calculate the sample size of the image.
     * @param displayWidth Actual width that the image needs to be scaled to.
     * @param style How the image needs to be scales. 1(Gridlayout, not squares) 2(Gridlayout, squares) 3(single image)
     */
    public DownloadImage(ImageButton panel, int displayWidthSample, int displayWidth, int style) {
        this.panel = panel;
        this.displayWidthSample = displayWidthSample;
        this.displayWidth = displayWidth;
        this.style = style;
    }

    /** Background process of AsyncTask used for loading an image.
     *
     * @param urls Url that contains the image to be loaded.
     * @return The Bitmap of the image that was downloaded from the url.
     */
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

    /** PostExecute process of AsyncTask.
     *
     * @param image Bitmap that was created from the image url.
     */
    protected void onPostExecute(Bitmap image) {
        if (style == 1 || style == 3) {
            scaleImageOriginalRatio(image);
        } else if (style == 2) {
            scaleImageSquare(image);
        }
    }

    /** Scales a bitmap so the image width is the inputted displayWidth.  Keeps the length to width ratio the same as the original image.
     *
     * @param image Bitmap that will be scaled and added to the ImageButton.
     */
    private void scaleImageOriginalRatio(Bitmap image) {
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

    /** Scales a bitmap so the image is a square, length and width will be the same. A portion of the image may be cropped off.
     *
     * @param image Bitmap that will be scaled and added to the ImageButton.
     */
    private void scaleImageSquare(Bitmap image) {
        int originalImageWidth = image.getWidth();
        int originalImageHeight = image.getHeight();
        int newImageWidth;
        int newImageHeight;
        Bitmap square;

        if (originalImageWidth > originalImageHeight) {
            newImageHeight = displayWidth;
            newImageWidth = (newImageHeight * originalImageWidth) / originalImageHeight;
            Bitmap scaledImage = Bitmap.createScaledBitmap(image, newImageWidth, newImageHeight, true);

            square = Bitmap.createBitmap(scaledImage, scaledImage.getWidth()/2 - scaledImage.getHeight()/ 2, 0, scaledImage.getHeight(), scaledImage.getHeight());
        } else {
            newImageWidth = displayWidth;
            newImageHeight = (newImageWidth * originalImageHeight) / originalImageWidth;
            Bitmap scaledImage = Bitmap.createScaledBitmap(image, newImageWidth, newImageHeight, true);

            square = Bitmap.createBitmap(scaledImage, 0, scaledImage.getHeight()/2 - scaledImage.getWidth()/2, scaledImage.getWidth(), scaledImage.getWidth());
        }

        panel.setImageBitmap(square);
    }
}
