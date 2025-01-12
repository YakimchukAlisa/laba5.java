import org.jsfml.graphics.*;
import org.jsfml.system.*;
import java.io.IOException;
import org.jsfml.window.event.Event;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.graphics.RenderWindow;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Food smallFood = new Food(242, 5, 'o');
        Food bigFood = new Food(4, 10, 'O');
        Map map = new Map(35, 30);

        GameSettings[] settingsArray = new GameSettings[2];
        settingsArray[0] = new GameSettings("Pac-Man 1", 25, -14, 26, Color.YELLOW, Color.BLUE, Color.WHITE, Color.WHITE, Color.RED, new Color(255, 185, 193),
                Color.CYAN, new Color(255, 165, 0));
        settingsArray[1] = new GameSettings("Pac-Man 2", 25, -14, 8, new Color(255, 255, 153), new Color(100, 149, 247), new Color(255, 245, 238),
                new Color(255, 228, 225), new Color(220, 20, 60), new Color(255, 105, 180), new Color(176, 234, 240), new Color(255, 140, 0));
        Random random = new Random();
        GameSettings settings = settingsArray[random.nextInt(1)];
        map.createMap();
        Pacman pacman = new Pacman(settings.getPacmanStartX(), settings.getPacmanStartY(), settings.getPacmanStartX(), settings.getPacmanStartY(), 0, 3, 3, 0);

        List<Ghost> ghostArray = new ArrayList<>();
        Blinky blinky = new Blinky(11, 14, 0, 3, 3, settings.getBlinkyColor());
        Pinky pinky = new Pinky(13, 14, 0, 3, 3, settings.getPinkyColor());
        Inky inky = new Inky(15, 14, 0, 3, 3, settings.getInkyColor());
        Clyde clyde = new Clyde(17, 14, 0, 3, 3, settings.getClydeColor());
        ghostArray.add(blinky);
        ghostArray.add(pinky);
        ghostArray.add(inky);
        ghostArray.add(clyde);

        InputStream imageStream = Main.class.getResourceAsStream("images/cherry.jpeg");
        Texture cherryTexture = new Texture();
        try {
            //проверяем, удалось ли загрузить InputStream
            if (imageStream == null) {
                //выбрасываем исключение IOException с сообщением об ошибке
                throw new IOException("Image file not found.");
            }
            cherryTexture.loadFromStream(imageStream);
        }
    catch (IOException e) {
        System.err.println("Error: " + e.getMessage());
        return; // Завершаем программу, если изображение не загружено
    }
        Sprite cherryShape = new Sprite();
        cherryShape.setTexture(cherryTexture);
        cherryShape.setScale(0.1f, 0.1f);

        imageStream = Main.class.getResourceAsStream("images/apple.jpeg");
        Texture appleTexture = new Texture();
        try {
            if (imageStream == null) {
                 throw new IOException("Image file not found.");
            }
            appleTexture.loadFromStream(imageStream);
        }
        catch (IOException e) {
             System.err.println("Error: " + e.getMessage());
            return; // Завершаем программу, если изображение не загружено
        }
        Sprite appleShape = new Sprite();
        appleShape.setTexture(appleTexture);
        appleShape.setScale(0.02f, 0.02f);

        imageStream = Main.class.getResourceAsStream("images/pear.jpeg");
        Texture pearTexture = new Texture();
        try {
            if (imageStream == null) {
                throw new IOException("Image file not found.");
            }
            pearTexture.loadFromStream(imageStream);
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return; // Завершаем программу, если изображение не загружено
        }
        Sprite pearShape = new Sprite();
        pearShape.setTexture(pearTexture);
        pearShape.setScale(0.1f, 0.1f);

        imageStream = Main.class.getResourceAsStream("images/orange.jpeg");
        Texture orangeTexture = new Texture();
        try {
            if (imageStream == null) {
                throw new IOException("Image file not found.");
            }
            orangeTexture.loadFromStream(imageStream);
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return; // Завершаем программу, если изображение не загружено
        }
        Sprite orangeShape = new Sprite();
        orangeShape.setTexture(orangeTexture);
        orangeShape.setScale(0.1f, 0.1f);

        imageStream = Main.class.getResourceAsStream("images/watermelon.jpeg");
        Texture watermelonTexture = new Texture();
        try {
            if (imageStream == null) {
                throw new IOException("Image file not found.");
            }
            watermelonTexture.loadFromStream(imageStream);
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return; // Завершаем программу, если изображение не загружено
        }
        Sprite watermelonShape = new Sprite();
        watermelonShape.setTexture(watermelonTexture);
        watermelonShape.setScale(0.03f, 0.03f);

        //массив фруктов
        Fruit[] fruitArray = new Fruit[5];
        fruitArray[0] = new Fruit(20, cherryShape);
        fruitArray[1] = new Fruit(30, appleShape);
        fruitArray[2] = new Fruit(40, pearShape);
        fruitArray[3] =  new Fruit(50, orangeShape);
        fruitArray[4] = new Fruit(60,  watermelonShape);
        int randFruit = 0;

        Font font = new Font();
        String fontPath = "Unformital Medium.ttf";
        InputStream fontStream = Main.class.getResourceAsStream(fontPath);

        try {
            //проверяем, удалось ли загрузить fontStream
            if (fontStream == null) {
                //выбрасываем исключение IOException с сообщением об ошибке
                throw new IOException("Font file not found.");
            }
            font.loadFromStream(fontStream);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return; // Завершаем программу, если шрифт не загружен.
        }

        Text pointsText = new Text();
        pointsText.setFont(font);
        pointsText.setCharacterSize(40);
        pointsText.setColor(Color.WHITE);
        pointsText.setPosition(2 * settings.getGridSize(), settings.getGridSize());

        Text livesText = new Text();
        livesText.setFont(font);
        livesText.setCharacterSize(40);
        livesText.setColor(Color.WHITE);
        livesText.setPosition(22 * settings.getGridSize(), settings.getGridSize());

        Text resultText = new Text();
        resultText.setFont(font);
        resultText.setCharacterSize(80);
        resultText.setColor(Color.WHITE);
        resultText.setPosition(5 * settings.getGridSize(), 10 * settings.getGridSize());

        Text record = new Text();
        record.setFont(font);
        record.setCharacterSize(40);
        record.setColor(Color.WHITE);
        record.setPosition(11 * settings.getGridSize(),  settings.getGridSize());

        RenderWindow window = new RenderWindow(new VideoMode(settings.getGridSize() * map.getW(), settings.getGridSize() * map.getH()), settings.getWindowTitle());

        while (window.isOpen()) {
            Event event;
            while ((event = window.pollEvent()) != null) { // Получаем событие (если есть)
                if (event.type == Event.Type.CLOSED) {   // Проверяем тип события
                    window.close();
                }
                if (event.type == Event.Type.KEY_PRESSED && event.asKeyEvent().key == Keyboard.Key.RETURN) {
                    game.resetGame(map, smallFood, bigFood, pacman, ghostArray, settings, resultText, fruitArray[0]);
                }
            }

            if (!fruitArray[0].getIsActive())
            {
                randFruit = random.nextInt(1)+1;
            }

            window.clear(Color.BLACK);
            fruitArray[randFruit].createFruit(settings, map, window, smallFood);
            map.mazePaint(settings, window, smallFood, bigFood,  fruitArray[randFruit].getSprite());

            ResultWrapper wonOrLostResult = pacman.wonOrLost(smallFood, bigFood, resultText);
            if (wonOrLostResult.getResult() == 1) {

                for(Ghost ghost : ghostArray){                //работа с массивом объектов
                    ghost.ghostDraw(window,settings);
                }

                FloatRect textBounds = resultText.getLocalBounds();
                Vector2i windowSize = window.getSize();
                resultText.setPosition((windowSize.x - textBounds.width) / 2f, (windowSize.y - textBounds.height) / 2f - 50);
                window.draw(resultText);
                pacman.updateMaxPoints(pacman.getPoints());

            } else {
                pacman.move(map, smallFood, bigFood, fruitArray[randFruit]);
                blinky.blinkyMove(pacman, map, settings, window);
                pinky.pinkyMove(pacman, map, settings, window);
                inky.inkyMove(pacman, map, blinky, settings, window);
                clyde.clydeMove(pacman, map, settings, window);

                if (clyde.lose(pacman, blinky, pinky, inky) == 1) {
                    if(pacman.getLives() > 0) {
                        blinky.setAll(11, 14, 0, 3, 3, settings.getBlinkyColor());
                        pinky.setAll(13, 14, 0, 3, 3, settings.getPinkyColor());
                        inky.setAll(15, 14, 0, 3, 3, settings.getInkyColor());
                        clyde.setAll(17, 14, 0, 3, 3, settings.getClydeColor());
                        Tile newTile = new Tile(' ');
                        map.setTile(pacman.getY(), pacman.getX(), newTile);
                        newTile = new Tile('P');
                        map.setTile(settings.getPacmanStartY(), settings.getPacmanStartX(), newTile);
                        pacman.setX(settings.getPacmanStartX());
                        pacman.setY(settings.getPacmanStartY());
                        pacman.setNextX(settings.getPacmanStartX());
                        pacman.setNextY(settings.getPacmanStartY());
                        pacman.setScore(0);
                        pacman.setNextDirection(3);

                    }
                }
            }

            //обработка строк
            String pointString = String.format("Score %d", pacman.getPoints());
            pointsText.setString(pointString);
            String livesString = String.format("Lives %d", pacman.getLives());
            livesText.setString(livesString);
            record.setString("Record " + pacman.getMaxPoints());
            window.draw(pointsText);
            window.draw(livesText);
            window.draw(record);
            window.display();
        }
    }
}