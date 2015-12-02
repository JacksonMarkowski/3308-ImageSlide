package team16_3308.imageslide;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

/** ImageButton that will contain an image for the main application view. */
public class SingleImageButton extends ImageButton {

    protected String url;

    /** Basic constructor
     *
     * @param context Context of the activity that the SingleImageButton is being added to.
     */
    public SingleImageButton(Context context) {
        super(context);
        setElements();
    }

    /** Basic constructor with attributes.
     *
     * @param context Context of the activity that the SingleImageButton is being added to.
     * @param attributeSet Attribute parameters.
     */
    public SingleImageButton(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        setElements();
    }

    /** Adds and sets the basic elements to the ImageButton. Style and formatting are set.  ClickListener is added so the SingleImageButton is able to create a SingleImageActivity. */
    protected void setElements() {
        setBackgroundColor(0);
        setPadding(10, 10, 10, 10);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getContext();
                Intent intent = new Intent(context, SingleImageActivity.class);
                intent.putExtra("url", url);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
}
