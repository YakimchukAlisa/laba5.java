import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;

public class Clyde extends Ghost {

    private int x;
    private int y;
    private int score;
    private int direction;
    private int lastDirection;
    private Color color;

    public Clyde() {
    }

    public Color getColor() {
        return color;
    }

    public Clyde(int x, int y, int score, int direction, int lastDirection, Color color) {
        super(x, y, score, direction, lastDirection, color);
    }

    public void clydeMove(Pacman pacman, Map map, GameSettings settings, RenderWindow window) {
        int a, b;
        float mainDistance = distance(pacman.getX(), pacman.getY(), getX(), getY());
        if (mainDistance > 8) {
            a = pacman.getX();
            b = pacman.getY();
        } else {
            a = 0;
            b = map.getH();
        }
        move(map, a, b);
        ghostDraw(window, settings);
    }


    public int lose(Pacman pacman, Blinky blinky, Pinky pinky, Inky inky) {
        int result = 0;
        if (pacman.getX() == blinky.getX() && pacman.getY() == blinky.getY()) {
            pacman.loseLife();
            result = 1;
        } else if (pacman.getX() == pinky.getX() && pacman.getY() == pinky.getY()) {
            pacman.loseLife();
            result = 1;
        } else if (pacman.getX() == inky.getX() && pacman.getY() == inky.getY()) {
            pacman.loseLife();
            result = 1;
        } else if (pacman.getX() == getX() && pacman.getY() == getY()) {
            pacman.loseLife();
            result = 1;
        }

        switch (pacman.getNextDirection()) {
            case 0:
                if (pacman.getX() == blinky.getX() && pacman.getY() - 1 == blinky.getY()) {
                    pacman.loseLife();
                    result = 1;
                } else if (pacman.getX() == pinky.getX() && pacman.getY() - 1 == pinky.getY()) {
                    pacman.loseLife();
                    result = 1;
                } else if (pacman.getX() == inky.getX() && pacman.getY() - 1 == inky.getY()) {
                    pacman.loseLife();
                    result = 1;
                } else if (pacman.getX() == getX() && pacman.getY() - 1 == getY()) {
                    pacman.loseLife();
                    result = 1;
                }
                break;
            case 1:
                if (pacman.getX() == blinky.getX() && pacman.getY() + 1 == blinky.getY()) {
                    pacman.loseLife();
                    result = 1;
                } else if (pacman.getX() == pinky.getX() && pacman.getY() + 1 == pinky.getY()) {
                    pacman.loseLife();
                    result = 1;
                } else if (pacman.getX() == inky.getX() && pacman.getY() + 1 == inky.getY()) {
                    pacman.loseLife();
                    result = 1;
                } else if (pacman.getX() == getX() && pacman.getY() + 1 == getY()) {
                    pacman.loseLife();
                    result = 1;
                }
                break;
            case 2:
                if (pacman.getX() -1 == blinky.getX() && pacman.getY() == blinky.getY()) {
                    pacman.loseLife();
                    result = 1;
                }
                else if (pacman.getX() -1 == pinky.getX() && pacman.getY() == pinky.getY()) {
                    pacman.loseLife();
                    result = 1;
                }
                else if (pacman.getX()-1 == inky.getX() && pacman.getY() == inky.getY()) {
                    pacman.loseLife();
                    result = 1;
                }
                else if (pacman.getX() - 1 == getX() && pacman.getY() == getY()) {
                    pacman.loseLife();
                    result = 1;
                }
                break;
            case 3:
                if (pacman.getX()+1 == blinky.getX() && pacman.getY() == blinky.getY()) {
                    pacman.loseLife();
                    result = 1;
                }
                else if (pacman.getX()+1 == pinky.getX() && pacman.getY() == pinky.getY()) {
                    pacman.loseLife();
                    result = 1;
                }
                else if (pacman.getX()+1 == inky.getX() && pacman.getY() == inky.getY()) {
                    pacman.loseLife();
                    result = 1;
                }
                else if (pacman.getX()+1 == getX() && pacman.getY() == getY()) {
                    pacman.loseLife();
                    result = 1;
                }
                break;
        }
        return result;
    }
}