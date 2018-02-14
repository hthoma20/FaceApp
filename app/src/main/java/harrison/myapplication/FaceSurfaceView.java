package harrison.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by Harrison on 2/7/2018.
 */

public class FaceSurfaceView extends SurfaceView {
    Face face;

    public FaceSurfaceView(Context context) {
        super(context);
        initialize();
    }

    public FaceSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public FaceSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    /*
     * do initialization things
     * called from every contructor
     */
    private void initialize(){
        setWillNotDraw(false);

        //the center of the surface view
        int centerX= getWidth()/2;
        int centerY= getHeight()/2;
        this.face= new Face(centerX,centerY);
    }

    public void onDraw(Canvas c){
        face.onDraw(c);
    }

    public Face getFace(){
        return face;
    }
}
