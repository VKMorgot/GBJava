package course2.lesson1;

import java.awt.*;
import java.util.Random;

/**
 * Задание
 * <p>
 * написать КЛАСС Background, изменяющий цвет канвы в зависимости от времени в приложении
 * <p>
 * * реализовать добавление новых кружков по клику, используя ТОЛЬКО массивы
 * <p>
 * * реализовать по клику другой кнопки удаление кружков
 */
public class Background {

    private final int STEP = 1;
    private final GameCanvas canvas;
    private boolean isColorChanged;
    private Color newColor;

    Background(GameCanvas canvas) {
        this.canvas = canvas;
        isColorChanged = true;
    }

    void changeColor() {
        if (isColorChanged) {
            newColor = new Color(new Random().nextInt());
            isColorChanged = false;
        } else {
            Color currentColor = canvas.getBackground();
            if (currentColor.equals(newColor)) {
                isColorChanged = true;
            } else {
                canvas.setBackground(generateColor(newColor, currentColor));
            }
        }
    }

    private Color generateColor (Color newColor, Color currentColor) {
        return new Color(getNewColorRInt(newColor, currentColor),
                getNewColorGInt(newColor, currentColor),
                getNewColorBInt(newColor, currentColor),
                getNewColorAInt(newColor, currentColor));
    }

    private int getNewColorRInt (Color newColor, Color currentColor) {
        if (newColor.getRed() > currentColor.getRed()) {
            return currentColor.getRed() + STEP;
        } else if (newColor.getRed() < currentColor.getRed()) {
            return currentColor.getRed() - STEP;
        } else return currentColor.getRed();
    }

    private int getNewColorGInt (Color newColor, Color currentColor) {
        if (newColor.getGreen() > currentColor.getGreen()) {
            return currentColor.getGreen() + STEP;
        } else if (newColor.getGreen() < currentColor.getGreen()) {
            return currentColor.getGreen() - STEP;
        } else return currentColor.getGreen();
    }

    private int getNewColorBInt (Color newColor, Color currentColor) {
        if (newColor.getBlue() > currentColor.getBlue()) {
            return currentColor.getBlue() + STEP;
        } else if (newColor.getBlue() < currentColor.getBlue()) {
            return currentColor.getBlue() - STEP;
        } else return currentColor.getBlue();
    }

    private int getNewColorAInt (Color newColor, Color currentColor) {
        if (newColor.getAlpha() > currentColor.getAlpha()) {
            return currentColor.getAlpha() + STEP;
        } else if (newColor.getAlpha() < currentColor.getAlpha()) {
            return currentColor.getAlpha() - STEP;
        } else return currentColor.getAlpha();
    }

}
