package display;

import game.state.State;
import input.Input;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {
    private Canvas canvas;
    private Renderer renderer;
    private DebugRenderer debugRenderer;

    public Display(int width, int height, Input input) {
        setTitle("My Awesome 2D game.Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        this.renderer = new Renderer();
        this.debugRenderer = new DebugRenderer();

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(width, height));
        this.canvas.setFocusable(false);
        add(canvas);
        addKeyListener(input);
        pack();

        canvas.createBufferStrategy(2);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void render(State state, boolean debugMode) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        renderer.render(state, graphics);
        if (debugMode) {
            debugRenderer.render(state, graphics);
        }

        graphics.dispose();
        bufferStrategy.show();
    }
}
