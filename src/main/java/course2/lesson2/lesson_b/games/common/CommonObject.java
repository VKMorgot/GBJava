package course2.lesson2.lesson_b.games.common;

import java.awt.*;

public interface CommonObject {
    void update(GameCanvas canvas, float deltaTime);
    void render(GameCanvas canvas, Graphics g);
}
