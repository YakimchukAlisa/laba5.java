import java.util.ArrayList;
import java.util.List;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import static java.awt.SystemColor.window;

public class Map {
    private int H;
    private int W;
    private List<List<Tile>> maze;   //двумерный массив объектов класса Tile

    public Map(int H, int W) {
        this.H = H;                     //использование оператора this
        this.W = W;
        this.maze = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            maze.add(new ArrayList<>(W));
            for (int j = 0; j < W; j++) {
                maze.get(i).add(null);
            }
        }
    }

    public int getH() {
        return H;
    }

    public int getW() {
        return W;
    }

    public Tile getTile(int y, int x) {
        return maze.get(y).get(x);
    }

    public void setTile(int y, int x, Tile tile) {
        maze.get(y).set(x, tile);
    }


    public void createMap() {
        String[] tempMaze = {
                "                              ",
                "                              ",
                "                              ",
                " XXXXXXXXXXXXXXXXXXXXXXXXXXXX ",
                " XooooooooooooXXooooooooooooX ",
                " XoXXXXoXXXXXoXXoXXXXXoXXXXoX ",
                " XOXXXXoXXXXXoXXoXXXXXoXXXXOX ",
                " XoXXXXoXXXXXoXXoXXXXXoXXXXoX ",
                " XooooooooooooooooooooooooooX ",
                " XoXXXXoXXoXXXXXXXXoXXoXXXXoX ",
                " XoXXXXoXXoXXXXXXXXoXXoXXXXoX ",
                " XooooooXXooooXXooooXXooooooX ",
                " XXXXXXoXXXXX XX XXXXXoXXXXXX ",
                " nnnnnXoXXXXX XX XXXXXoXnnnnn ",
                " nnnnnXoXX          XXoXnnnnn ",
                " nnnnnXoXX XXXXXXXX XXoXnnnnn ",
                " XXXXXXoXX XnnnnnnX XXoXXXXXX ",
                "       o   XnnnnnnX   o       ",
                " XXXXXXoXX XnnnnnnX XXoXXXXXX ",
                " nnnnnXoXX XXXXXXXX XXoXnnnnn ",
                " nnnnnXoXX          XXoXnnnnn ",
                " nnnnnXoXX XXXXXXXX XXoXnnnnn ",
                " XXXXXXoXX XXXXXXXX XXoXXXXXX ",
                " XooooooooooooXXooooooooooooX ",
                " XoXXXXoXXXXXoXXoXXXXXoXXXXoX ",
                " XoXXXXoXXXXXoXXoXXXXXoXXXXoX ",
                " XOooXXooooooooooooooooXXooOX ",
                " XXXoXXoXXoXXXXXXXXoXXoXXoXXX ",
                " XXXoXXoXXoXXXXXXXXoXXoXXoXXX ",
                " XooooooXXooooXXooooXXooooooX ",
                " XoXXXXXXXXXXoXXoXXXXXXXXXXoX ",
                " XoXXXXXXXXXXoXXoXXXXXXXXXXoX ",
                " XooooooooooooooooooooooooooX ",
                " XXXXXXXXXXXXXXXXXXXXXXXXXXXX ",
                "                              ",
        };

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                char tileChar = tempMaze[i].charAt(j);
                maze.get(i).set(j, new Tile(tileChar));
            }

    }
        }

        public void mazePaint(GameSettings settings, RenderWindow window, Food smallFood, Food bigFood, Sprite fruitShape) {
            RectangleShape square = new RectangleShape(new Vector2f(settings.getGridSize(), settings.getGridSize()));
            square.setFillColor(settings.getSquareColor());

            CircleShape smallCircle = new CircleShape(3);
            smallCircle.setFillColor(settings.getSmallCircleColor());

            CircleShape bigCircle = new CircleShape(6);
            bigCircle.setFillColor(settings.getBigCircleColor());

            RectangleShape pacman = new RectangleShape(new Vector2f(settings.getGridSize(), settings.getGridSize()));
            pacman.setFillColor(settings.getPacmanColor());

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    char tile = maze.get(i).get(j).getType();

                    if (tile == 'X') {
                        square.setPosition(j * settings.getGridSize(), i * settings.getGridSize());
                        window.draw(square);
                    } else if (tile == smallFood.getType()) {
                        smallCircle.setPosition(j * settings.getGridSize() + 8.5f, i * settings.getGridSize() + 8.5f);
                        window.draw(smallCircle);
                    } else if (tile == bigFood.getType()) {
                        bigCircle.setPosition(j * settings.getGridSize() + 5.5f, i * settings.getGridSize() + 5.5f);
                        window.draw(bigCircle);
                    } else if (tile == 'P') {
                        pacman.setPosition(j * settings.getGridSize(), i * settings.getGridSize());
                        window.draw(pacman);
                    }
                    else if (tile == 'F')
                    {
                        window.draw(fruitShape);
                    }
                }
            }
        }
    }