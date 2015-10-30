package team16_3308.imageslide;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImagePanel {

    private ImageButton display;
    private Bitmap image;
    private String title;
    private String link;
    private String description;
    private AppCompatActivity activity;
    private int activityWidth;

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
        this.activityWidth = (activity.getResources().getDisplayMetrics().widthPixels) - 40;
        createImageButton();
    }

    public void createImageButton() {
        display = new ImageButton(activity);
        addClickListener();
        display.setBackgroundColor(0);
        display.setPadding(10,10,10,10);
    }

    //ToDo:Click listener needs to be apart of the scroll view not the image
    public void addClickListener() {
        display.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("1", "2");
            }
        });
    }

    //Creates the basic ImageView
    public void scaleImageView() {
        Bitmap imageScaled;

        //generates values for in order to scale the bitmap
        int originalImageWidth = image.getWidth();
        int originalImageHeight = image.getHeight();
        int newImageWidth = originalImageWidth;
        int newImageHeight = originalImageHeight;

        if (originalImageWidth > (activityWidth / 2)) {
            newImageWidth = activityWidth / 2;
            newImageHeight = (newImageWidth * originalImageHeight) / originalImageWidth;
        }

        //ToDo: Check if image resolution needs to be adjusted
        imageScaled = Bitmap.createScaledBitmap(image, newImageWidth, newImageHeight, false) ;
        display.setImageBitmap(imageScaled);

        //adds the ImageView to the grid layout
        GridLayout gLayout = (GridLayout) activity.findViewById(R.id.gridLayout);
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
