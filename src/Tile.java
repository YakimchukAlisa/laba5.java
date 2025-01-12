public class Tile {
    private char type; // 'X', 'o', ' ', 'P' и т.д.
    private boolean isPassable;   //можно ли пройти через клетку

    public Tile() {
        //this.type = ' ';
        this.isPassable = false;
    }

    public Tile(char type) {
        this.type = type;
        if (type=='X')
            this.isPassable = false;
        else this.isPassable = true;
    }

    public char getType() {
        return type;
    }

    public boolean getIsPassable() {
        return isPassable;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setIsPassable(boolean isPassable) {
        this.isPassable = isPassable;
    }
}