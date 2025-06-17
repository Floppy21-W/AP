public class PlayingField {
    private static final int SIZE = 3;
    private final char[][] field;

    public PlayingField() {
        field = new char[SIZE][SIZE];
        initializeField();
    }

    public void initializeField() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = ' ';
            }
        }
    }

    public void displayField() {
        System.out.println("---------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean makeMove(int row, int col, char player) {
        if (row >= 0 && row < SIZE && col >=0 && col < SIZE) {
            if (field[row][col] == ' ') {
                field[row][col] = player;
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++){
                if (field[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] getField() {
        return field;
    }
}
