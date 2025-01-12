public class Food {
    private int count;
    private int point;
    private char type;
    static int totalFoodCount;

    static {
        totalFoodCount=0;
    }

    static int getTotalFoodCount() {
        return totalFoodCount;
    }

    static void setTotalFoodCount(int count) {
        totalFoodCount = count;
    }

    public Food(int count, int point, char type) {
        this.count = count;                    //использование оператора this
        this.point = point;
        this.type = type;
        totalFoodCount += count;
    }

    public void setAll(int count, int point, char type) {
        this.count = count;                   //использование оператора this
        this.point = point;
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public int getPoint() {
        return point;
    }

    public char getType() {
        return type;
    }

    public void decreaseCount() {
        count--;
        totalFoodCount--;
    }
}