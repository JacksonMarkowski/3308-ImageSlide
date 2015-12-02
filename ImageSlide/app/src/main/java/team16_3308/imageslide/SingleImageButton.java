package team16_3308.imageslide;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

public class SingleImageButton extends ImageButton {

    protected String url;

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
                Context context = getContext();
                Intent intent = new Intent(context, SingleImageActivity.class);
                intent.putExtra("url", url);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
}
