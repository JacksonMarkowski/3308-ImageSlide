package team16_3308.imageslide;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImagePanel {

    private ImageView display;
    private Bitmap image;
    private String title;
    private String link;
    private String description;
    private AppCompatActivity activity;

    public ImagePanel() {

    }

    //ToDo: At some point the image from the site need to be converted to a bitmap
    //Change Bitmap to an Image if the conversion from image to bitmap is done in this class
    public ImagePanel(AppCompatActivity activity, Bitmap image, String title, String link, String description) {
        this.activity = activity;
        this.image = image;
        this.title = title;
        this.link = link;
        this.description = description;
    }

    //Creates the basic ImageView
    public void createImageView() {
        Bitmap imageScaled;
        GridLayout gLayout = (GridLayout) activity.findViewById(R.id.gridLayout);

        //ToDo: layoutWidth gets screen size
        int layoutWidth = 1000;
        int originalImageWidth = image.getWidth();
        int originalImageHeight = image.getHeight();
        int newImageWidth = originalImageWidth;
        int newImageHeight = originalImageHeight;

        if (originalImageWidth > (layoutWidth/2)) {
            newImageWidth = layoutWidth/2;
            newImageHeight = (newImageWidth * originalImageHeight) / originalImageWidth;
        }

        //creation of imageView
        display = new ImageView(activity);
        imageScaled = Bitmap.createScaledBitmap(image, newImageWidth, newImageHeight, false);
        display.setImageBitmap(imageScaled);

        //adds the ImageView to the grid layout
        gLayout.addView(display);


        //ToDo: Check to see if a buffer view needs to be added to the Gridlayout, image view is wider than  half the screen
    }


    public ImageView getImageView() {
        return display;
    }

    public Bitmap getBitmap() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }
}
