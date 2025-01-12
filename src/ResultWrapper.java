public class ResultWrapper {
    private int result;

    public ResultWrapper() {
        this.result = 0; // Инициализируем значением по умолчанию
    }

    public ResultWrapper(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}