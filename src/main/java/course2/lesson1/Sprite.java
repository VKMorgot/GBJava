package course2.lesson1;

import java.awt.*;

/**
 * двумерный объект, который будет рисоваться
 */
public class Sprite {
    // координаты: центр объекта
    protected float x;
    protected float y;
    // половины ширины и высоты объекта
    protected float halfWidth;
    protected float halfHeight;

    protected float getLeft() {
        return x - halfWidth;
    }

    protected void setLeft(float left) {
        x = left + halfWidth;
    }

    protected float getRight() {
        return x + halfWidth;
    }

    protected void setRight(float right) {
        x = right - halfWidth;
    }

    protected float getTop() {
        return y - halfHeight;
    }

    protected void setTop(float top) {
        y = top + halfHeight;
    }

    protected float getBottom() {
        return y + halfHeight;
    }

    protected void setBottom(float bottom) {
        y = bottom - halfHeight;
    }

    protected float getWidth() {
        return 2f * halfWidth;
    }

    protected float getHeight() {
        return 2f * halfHeight;
    }

    /**
     * Как sprite должен обновиться с течением времени
     */
    void update(GameCanvas canvas, float deltaTime) {
    }

    /**
     * Как sprite должен рисоваться
     */
    void render(GameCanvas canvas, Graphics g) {
    }
}
