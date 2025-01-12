import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.RenderWindow;

import java.util.Random;

public class Fruit {
    private int x;       //координата X на карте
    private int y;       //координата Y на карте
    private int points;  //количество очков за съедение
    private Sprite sprite; //графическое представление
    static boolean isActive;  //активен ли фрукт

    public Fruit() {};
    public  Fruit(int points, Sprite sprite) {
        this.points = points;
        this.sprite = sprite;
    }
    public int getX()  { return x; }
    public int getY() { return y; }
    public int getPoints() { return points; }
    public Sprite getSprite() { return sprite; }
    public  void setIsActive(boolean active) { isActive = active; }
    public boolean getIsActive() { return isActive; }
    static
    {
        isActive = false;
    }
    public void createFruit(GameSettings settings, Map map, RenderWindow window, Food food) {
        if ((food.getTotalFoodCount() == 176 || food.getTotalFoodCount() == 76) && !isActive)  //когда Пакман съел первые 70 или 170 точек
        {
            int randY, randX;
            do {
                Random random = new Random();//выбор случайных координат
                randY = random.nextInt(30) + 4;
                randX = random.nextInt(23) + 4;
            } while (map.getTile(randY, randX).getType() != ' ');
            x = randX;
            y = randY;
            sprite.setPosition(randX * settings.getGridSize(), randY * settings.getGridSize());
            isActive = true;
        }
        if (isActive)                                 //если фрукт активен, добавляем его на карту
        {
            Tile newTile = new Tile('F');

            map.setTile(y, x, newTile);
        }
    }
};

