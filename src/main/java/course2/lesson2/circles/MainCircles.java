package course2.lesson2.circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainCircles extends JFrame implements MouseListener {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private Sprite[] sprites = new Sprite[1];
    private int spritesCount;

    private int initBalls = 10;

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].render(canvas, g);
        }
    }

    void addSprite(Sprite s) {
        // увеличиваем размер массива сразу на много, чтобы не тратить ресурсы на пересоздание массивов
        if (spritesCount == sprites.length) {
            Sprite[] newSprites = new Sprite[sprites.length * 2];
            System.arraycopy(sprites, 0, newSprites, 0, sprites.length);
            sprites = newSprites;
        }
        sprites[spritesCount++] = s;
    }

    void removeSprite() {
        // не уменьшаем размер массива, чтобы не тратить на это ресурсы
        if (spritesCount > 1) {
            spritesCount--;
        }
    }

    void onDrawCanvas(GameCanvas c, Graphics g, float deltaTime) {
        update(c, deltaTime);
        render(c, g);
    }

    private void initApplication() {
        addSprite(new Background());
        for (int i = 0; i < initBalls; i++) {
            addSprite(new Ball());
        }
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        GameCanvas canvas = new GameCanvas(this);

        // добавление MouseListener на канву одной строкой:
        // для этого в MainCircles должен имплементироватся интерфейс MouseListener
//        canvas.addMouseListener(this);

        // добавление MouseAdapter на канву
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    addSprite(new Ball(e.getX(), e.getY()));
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    removeSprite();
                }
            }
        });
        add(canvas);
        initApplication();
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainCircles();
    }

    // реализация интерфейса MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            addSprite(new Ball(e.getX(), e.getY()));
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            removeSprite();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
