import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;
import static java.lang.Double.POSITIVE_INFINITY;

public class Ghost {
    private int x;
    private int y;
    private int score;
    private int direction;
    private int lastDirection;
    private Color color;

    public Ghost() {
    }


    public Color getColor() {
        return color;
    }

    public Ghost(int x, int y, int score, int direction, int lastDirection, Color color) {
        this.x = x;
        this.y = y;
        this.score = score;
        this.direction = direction;
        this.lastDirection = lastDirection;
        this.color = color; // Инициализируем цвет
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScore() {
        return score;
    }

    public int getDirection() {
        return direction;
    }

    public int getLastDirection() {
        return lastDirection;
    }

    public void setAll(int a, int b, int c, int d, int e, Color f) {
        x = a;
        y = b;
        score = c;
        direction = d;
        lastDirection = e;
        color = f;
    }

    public float distance(int x1, int y1, int x2, int y2) {
        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public void ghostDraw(RenderWindow window, GameSettings settings) {
        RectangleShape ghostShape = new RectangleShape(new Vector2f(settings.getGridSize(), settings.getGridSize()));
        ghostShape.setFillColor(color);
        ghostShape.setPosition(getX() * settings.getGridSize(), getY() * settings.getGridSize());
        window.draw(ghostShape);
    }

    public void move(Map map, int goalX, int goalY) {
        float distanceUp, distanceDown, distanceLeft, distanceRight;
        double minDistance = POSITIVE_INFINITY;
        int change = 0;


        distanceUp = distance(goalX, goalY, x, y - 1);
        distanceDown = distance(goalX, goalY, x, y + 1);
        if (y == 17 && x == 1)
            distanceLeft = distance(goalX, goalY, map.getW() - 1, y);
        else distanceLeft = distance(goalX, goalY, x - 1, y);
        if (y == 17 && x == map.getW() - 1)
            distanceRight = distance(goalX, goalY, 0, y);
        else distanceRight = distance(goalX, goalY, x + 1, y);


        if (distanceRight <= minDistance && map.getTile(y, x + 1).getIsPassable() && lastDirection != 2) {
            minDistance = distanceRight;
            direction = 3;
        }
        if (distanceUp <= minDistance && map.getTile(y - 1, x).getIsPassable() && lastDirection != 1 && !(y == 17 && x == 0 || y == 17 && x == map.getW() - 1)) {
            minDistance = distanceUp;
            direction = 0;
        }
        if (distanceLeft <= minDistance && map.getTile(y, x - 1).getIsPassable() && lastDirection != 3) {
            minDistance = distanceLeft;
            direction = 2;
        }
        if (distanceDown <= minDistance && map.getTile(y + 1, x).getIsPassable() && lastDirection != 0 && !(y == 17 && x == 0 || y == 17 && x == map.getW() - 1)) {
            minDistance = distanceDown;
            direction = 1;
        }



        score++;
        if (score >= 150) {
            change = 1;
            // Двигаемся в выбранном направлении
            switch (direction) {
                case 0: // Движение вверх
                    y--;
                    break;
                case 1: // Движение вниз
                    y++;
                    break;
                case 2: // Движение влево
                    if (y == 17 && x == 1)
                        x = map.getW() - 2;
                    else
                        x--;
                    break;
                case 3: // Движение вправо
                    if (y == 17 && x == map.getW() - 2)
                        x = 1;
                    else
                        x++;
                    break;
                default:
                    break;
            }
            score = 0;
        }

        if (lastDirection != direction && change == 1)
            lastDirection = direction;
    }
}