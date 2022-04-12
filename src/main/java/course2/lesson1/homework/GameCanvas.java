package course2.lesson1.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// холст для рисования
public class GameCanvas extends JPanel {
    private long lastFrameTime;
    private final MainCircles controller;
    private final Background back;

    GameCanvas(MainCircles controller) {
        lastFrameTime = System.nanoTime();
        this.controller = controller;
        back = new Background(this);
        addMouseListener(createMouseListener());
    }

    @Override
    protected void paintComponent(Graphics g) { // do
        super.paintComponent(g); // {
        // no payload = 250fps
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f; // умножаем, чтобы получить секунды
        lastFrameTime = currentTime;
        controller.onDrawCanvas(this, g, deltaTime);
        try {
            Thread.sleep(16); //  1 sec / 60 frames = 16.(6) fps
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint(); // } while (true);
    }

    private MouseListener createMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    controller.addSprite();
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    controller.removeSprite();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

    public int getLeft() {
        return 0;
    }

    public int getRight() {
        return getWidth() - 1;
    }

    public int getTop() {
        return 0;
    }

    public int getBottom() {
        return getHeight() - 1;
    }

    public Background getBack() {
        return back;
    }
}
