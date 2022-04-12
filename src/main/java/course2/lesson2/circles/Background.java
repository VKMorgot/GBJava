package course2.lesson2.circles;

import java.awt.*;

/**
 * Задание
 * <p>
 * написать КЛАСС Background, изменяющий цвет канвы в зависимости от времени в приложении
 * <p>
 * * реализовать добавление новых кружков по клику, используя ТОЛЬКО массивы
 * <p>
 * * реализовать по клику другой кнопки удаление кружков
 */
// добавляем на канву бэкграунд как наследник спрайта
public class Background extends Sprite {

    private Color color;
    private float time;
    private static final float AMPLITUDE = 255f / 2f;

    @Override
    void update(GameCanvas canvas, float deltaTime) {
        time += deltaTime * 0.5;
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 4f));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 2f));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time));
        color = new Color(red, green, blue);
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
        canvas.setBackground(color);
    }
}
