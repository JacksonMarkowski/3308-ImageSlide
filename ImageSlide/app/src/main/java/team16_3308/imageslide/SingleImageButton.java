package team16_3308.imageslide;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class SingleImageButton extends ImageButton {

    public SingleImageButton(Context context) {
        super(context);
        setElements();
    }

    public SingleImageButton(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        setElements();
    }

    protected void setElements() {
        setBackgroundColor(0);
        setPadding(10, 10, 10, 10);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("1", "2");
            }
        });
    }
}
