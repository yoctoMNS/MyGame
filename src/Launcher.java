// TODO
// Part04 3:28

public class Launcher {
    public static void main(String[] args) {
        new Thread(new GameLoop(new Game(800, 600))).start();
    }
}
