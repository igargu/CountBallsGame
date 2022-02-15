package es.ikergarciagutierrez.promul.countballsgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Clase que dibuja la bola
 */
public class Ball extends View {

    // Variables de la clase
    private Paint paint; // Objeto paint para dibujar la bola

    private static final int RADIO = 65; // Radio de la bola

    private int centroX; // Posición en el eje X de la bola
    private int centroY; // Posición en el eje Y de la bola

    private int velocidadX; // Velocidad en el eje X de la bola
    private int velocidadY; // Velocidad en el eje Y de la bola

    /**
     * Constructor para dibujar la bola
     *
     * @param context Contexto
     * @param attrs   Atributos de la bola
     */
    public Ball(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    /**
     * Método que ambia el tamaño de la bola
     *
     * @param w    Ancho
     * @param h    Altura
     * @param oldW Antiguo ancho
     * @param oldH Antiguo alto
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        centroX = w / 2;
        centroY = h / 2;
    }

    /**
     * Método que pinta la bola a partir de un color en hexadecimal
     *
     * @param color String con el color en hexadecimal
     */
    public void setColor(String color) {
        paint.setColor(Color.parseColor(color));
    }

    /**
     * Método que establece la velocidad de la bola
     *
     * @param ejeX Velocidad de la bola en el eje X
     * @param ejeY Velocidad de la bola en el eje Y
     */
    public void setVelocidad(int ejeX, int ejeY) {
        velocidadX = ejeX;
        velocidadY = ejeY;
    }

    /**
     * Método que dibuja la bola
     *
     * @param canvas Objeto canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        int w = getWidth();
        int h = getHeight();

        centroX += velocidadX;
        centroY += velocidadY;

        // Límite de la pantalla
        int limiteDerecha = w - RADIO;
        int limiteInferior = h - RADIO;

        // Comprobamos si invertimos la dirección al llegar al límite de la pantalla
        if (centroX >= limiteDerecha) {
            centroX = limiteDerecha;
            velocidadX *= -1;
        }
        if (centroX <= RADIO) {
            centroX = RADIO;
            velocidadX *= -1;
        }
        if (centroY >= limiteInferior) {
            centroY = limiteInferior;
            velocidadY *= -1;
        }
        if (centroY <= RADIO) {
            centroY = RADIO;
            velocidadY *= -1;
        }

        // Dibujamos la bola y le establecemos el delay
        canvas.drawCircle(centroX, centroY, RADIO, paint);
        postInvalidateDelayed(1);

    }

}