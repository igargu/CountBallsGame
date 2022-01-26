package es.ikergarciagutierrez.promul.countballsgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ReadyMessage extends View {

    private Paint paint;

    private int centroX;
    private int centroY;

    private int velocidadY = 50;

    public ReadyMessage(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        centroX = w / 2;
    }

    protected void onDraw(Canvas canvas) {

        int h = getHeight();
        int a = 0; // TODO Borra esto

        centroY += velocidadY;

        int limiteInferior = h - a;

        if (centroY > a) {
            velocidadY *= 1;
        }

        canvas.drawText(String.valueOf(R.string.readyMessage), centroX, centroY, paint);
        postInvalidateDelayed(100);
    }

}
