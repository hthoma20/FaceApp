package harrison.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

/**
 * Class FaceSurfaceView
 *
 * class extends surface view so it can be displayed on the layout
 * contains a face and draws it
 *
 * @author Harry Thoma
 */

public class FaceSurfaceView extends SurfaceView {
    //the face for this surfaceview to draw
    private Face face;

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

    /**
     * do initialization things
     * called from every contructor
     */
    private void initialize(){
        setWillNotDraw(false);

        this.face= new Face(500,500);
    }

    public void onDraw(Canvas c){
        face.onDraw(c);
    }

    public Face getFace(){
        return face;
    }
}
