import org.jsfml.graphics.Color;

public class GameSettings {
    private String windowTitle;
    private Color pacmanColor;
    private Color squareColor;
    private Color smallCircleColor;
    private Color bigCircleColor;
    private Color blinkyColor;
    private Color pinkyColor;
    private Color inkyColor;
    private Color clydeColor;
    private int gridSize;
    private int pacmanStartX;
    private int pacmanStartY;

    public GameSettings() {
    }


    public GameSettings(String windowTitle, int gridSize,
                        int pacmanStartX, int pacmanStartY,
                        Color pacmanColor, Color squareColor, Color smallCircleColor,
                        Color bigCircleColor, Color blinkyColor, Color pinkyColor,
                        Color inkyColor, Color clydeColor) {
        this.windowTitle = windowTitle;           //использование оператора this
        this.gridSize = gridSize;
        this.pacmanStartX = pacmanStartX;
        this.pacmanStartY = pacmanStartY;
        this.pacmanColor = pacmanColor;
        this.squareColor = squareColor;
        this.smallCircleColor = smallCircleColor;
        this.bigCircleColor = bigCircleColor;
        this.blinkyColor = blinkyColor;
        this.pinkyColor = pinkyColor;
        this.inkyColor = inkyColor;
        this.clydeColor = clydeColor;


        try {
            if (gridSize <= 0) {
                throw new IllegalArgumentException("Grid size must be positive.");
            }
            this.gridSize = gridSize;
        } catch (IllegalArgumentException e) {
            System.err.println("Error during GameSettings initialization: " + e.getMessage());
            // Если gridsize неподходящий, то устанавливаем значение по умолчанию
            this.gridSize = 25;
        }

        try {
            if (pacmanStartX < 0 || pacmanStartY < 0) {
                throw new IllegalArgumentException("Pacman start coordinates must be positive.");
            }
            this.pacmanStartX = pacmanStartX;
            this.pacmanStartY = pacmanStartY;
        } catch (IllegalArgumentException e) {
            System.err.println("Error during GameSettings initialization: " + e.getMessage());
            // Если координаты неподходящие, то устанавливаем значения по умолчанию
            this.pacmanStartX = 14;
            this.pacmanStartY = 26;
        }
    }


public String getWindowTitle() {
        return windowTitle;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getPacmanStartX() {
        return pacmanStartX;
    }

    public int getPacmanStartY() {
        return pacmanStartY;
    }

    public Color getPacmanColor() {
        return pacmanColor;
    }

    public Color getSquareColor() {
        return squareColor;
    }

    public Color getSmallCircleColor() {
        return smallCircleColor;
    }

    public Color getBigCircleColor() {
        return bigCircleColor;
    }

    public Color getBlinkyColor() {
        return blinkyColor;
    }

    public Color getPinkyColor() {
        return pinkyColor;
    }

    public Color getInkyColor() {
        return inkyColor;
    }

    public Color getClydeColor() {
        return clydeColor;
    }
}