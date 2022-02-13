package game;

public class GameLoop implements Runnable {
    public static final int UPDATES_PER_SECOND = 60;
    private static final long NANO_SECONDS = 1000000000;
    private static final int FPS = 60;

    private Game game;
    private boolean running;
    private final double updateRate = NANO_SECONDS / FPS;

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        running = true;

        long last = System.nanoTime();
        double upsDelta = 0;
        long timer = 0;
        int upsCounter = 0;
        int fpsCounter = 0;

        while (running) {
            long now = System.nanoTime();
            boolean isUpdate = false;
            upsDelta += ((now - last) / updateRate) * game.getSettings().getGameSpeedMultiplier();
            timer += now - last;
            last = now;

            if (upsDelta >= 1) {
                update();
                upsCounter++;
                upsDelta--;
                isUpdate = true;
            }

            if (isUpdate) {
                render();
                fpsCounter++;
            }

            if (timer >= NANO_SECONDS) {
                System.out.printf("FPS: %d, UPS: %d\n", fpsCounter, upsCounter);
                fpsCounter = upsCounter = 0;
                timer = 0;
            }
        }
    }

    private void update() {
        game.update();
    }

    private void render() {
        game.render();
    }
}
