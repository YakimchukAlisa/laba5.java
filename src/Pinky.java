import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;


public class Pinky extends Ghost {

    private int x;
    private int y;
    private int score;
    private int direction;
    private int lastDirection;
    private Color color;

    public Pinky() {
    }

    public Color getColor() {
        return color;
    }

    public Pinky(int x, int y, int score, int direction, int lastDirection, Color color) {
        super(x, y, score, direction, lastDirection, color);
    }

    public void pinkyMove(Pacman pacman, Map map, GameSettings settings, RenderWindow window) {
        int a = pacman.getX();
        int b = pacman.getY();
        switch (pacman.getNextDirection()) {
            case 0:
                b = b - 4;
                break;
            case 1:
                b = b + 4;
                break;
            case 2:
                a = a - 4;
                break;
            case 3:
                a = a + 4;
                break;
        }
        move(map, a, b);
        ghostDraw(window, settings);
    }
}