package course2.lesson1;

import javax.swing.*;
import java.awt.*;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private Ball[] sprites = new Ball[10];

    private void update(GameCanvas canvas, float deltaTime) {
        for (Ball sprite : sprites) {
            sprite.update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (Ball sprite : sprites) {
            sprite.render(canvas, g);
        }
    }

    private void changeBackground(GameCanvas canvas) {
        canvas.getBack().changeColor();
    }

    void addSprite() {
        Ball[] newSprites = new Ball[sprites.length + 1];
        System.arraycopy(sprites, 0, newSprites, 0, sprites.length);
        newSprites[newSprites.length - 1] = new Ball();
        sprites = newSprites;
    }

    void removeSprite() {
        if (sprites.length - 1 >= 0) {
            Ball[] newSprites = new Ball[sprites.length - 1];
            System.arraycopy(sprites, 0, newSprites, 0, sprites.length - 1);
            sprites = newSprites;
        }
    }

    void onDrawCanvas(GameCanvas c, Graphics g, float deltaTime) {
        update(c, deltaTime);
        render(c, g);
        changeBackground(c);
    }

    private void initApplication() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        GameCanvas canvas = new GameCanvas(this);
        add(canvas);
        initApplication();
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainCircles();
    }

}
