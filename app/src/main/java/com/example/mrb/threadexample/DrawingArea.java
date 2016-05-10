
package com.example.mrb.threadexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mrb on 16-04-26.
 */
public class DrawingArea extends View // You need to extend the View class
{

    Paint pntBlackPaint;    // Painting object to handle the paint jobs for graphics

    boolean blnShowText;
    int intTextPosition;

    private float fltLeft;
    private float fltTop;
    private float fltRight;
    private float fltBottom;

    public DrawingArea(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DrawingArea,
                0, 0);

        try {
            blnShowText = a.getBoolean(R.styleable.DrawingArea_displayText, false);
            intTextPosition = a.getInteger(R.styleable.DrawingArea_labelPosition, 0);
        } finally {
            a.recycle();
        }

        fltLeft = 0;
        fltTop = 0;
        fltRight = 50;
        fltBottom = 50;

        init(); // Calling the init() method happens once, when we set up our Paint objects
    }

    private void init()
    {
        // This method is called once when the DrawingArea object is made.
        // It is best practice to handle the set-up of our Paint objects here rather than in onDraw()

        pntBlackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        pntBlackPaint.setColor(Color.BLACK);
        pntBlackPaint.setStrokeWidth(3);

    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        // This method exists to handle re-drawing of the DrawingArea object in the event of a
        //   resize of our App. For example, tilting the device may require us to redraw our content.
        // More instructions are meant to be added at the bottom if required.

        super.onSizeChanged(w, h, oldw, oldh);

    }

    public void moveLeft(float fltStep)
    {
        fltLeft = fltLeft - fltStep;
        fltRight = fltRight - fltStep;

        this.invalidate();
    }

    public void moveRight(float fltStep)
    {
        fltLeft = fltLeft + fltStep;
        fltRight = fltRight + fltStep;

        this.invalidate();
    }

    protected void onDraw(Canvas canvas)
    {
        // This method is where we issue our actual drawing commands.
        // The Canvas parameter is what we draw ON; the Paint objects defined above are what we draw WITH.

        super.onDraw(canvas);

        canvas.drawRect(fltLeft, fltTop, fltRight, fltBottom, pntBlackPaint);
    }

    public float getFltRight()
    {
        return fltRight;
    }

}
