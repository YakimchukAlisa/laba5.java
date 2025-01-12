import org.jsfml.graphics.*;

public class Blinky extends Ghost {

    private int x;
    private int y;
    private int score;
    private int direction;
    private int lastDirection;
    private Color color;

    public Blinky() {
    }

    public Color getColor() {
        return color;
    }

    public Blinky(int x, int y, int score, int direction, int lastDirection, Color color) {
        super(x, y, score, direction, lastDirection, color);
    }

    public void blinkyMove(Pacman pacman, Map map, GameSettings settings, RenderWindow window) {
        move(map, pacman.getX(), pacman.getY());
        ghostDraw(window, settings);
    }
}