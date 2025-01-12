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
        GameSettings settings = settingsArray[random.nextInt(1)+1];
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

        Font font = new Font();
        String fontPath = "Unformital Medium.ttf"; // Замените на актуальный путь
        InputStream fontStream = Main.class.getResourceAsStream(fontPath);

        try {
            if (fontStream == null) {
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
                    game.resetGame(map, smallFood, bigFood, pacman, ghostArray, settings, resultText);
                }
            }

            window.clear(Color.BLACK);
            map.mazePaint(settings, window, smallFood,
                    bigFood);

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
                pacman.move(map, smallFood, bigFood);
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
                        map.setTile(pacman.getY(), pacman.getX(), ' ');
                        map.setTile(settings.getPacmanStartY(), settings.getPacmanStartX(), 'P');
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