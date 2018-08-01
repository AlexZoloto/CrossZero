import java.util.Random;
import java.util.Scanner;
public class Application {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    private static final int SIZE_X = 5;
    private static final int SIZE_Y = 5;
    private static final int WINNING_STREAK = 3;

    private static char[][] field = new char[SIZE_Y][SIZE_X];

    private static final char PLAYER_DOT = 'X';
    private static final char AI_DOT = 'O';
    private static final char EMPTY_DOT = '.';


    private static void initField(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }
    private static void printField(){
        System.out.println("-------");
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print("|");
                System.out.print(field[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-------");
    }

    private static void setSym(int y, int x, char sym){
        field[y][x] = sym;
    }

    private static void playerStep(){
        int x;
        int y;
        do {
            System.out.println("Введите координаты: X Y (1-3)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }while (!isCellValid(y, x));
        setSym(y,x,PLAYER_DOT);
    }
    private static void aiStep(){
        int x;
        int y;
        do {
//            if (checkForBLockStep(PLAYER_DOT, SIZE_Y, SIZE_X)) {
//                for (int i = 0; i < LAST_PLAYER_STEP_Y; i++) {
//                    for (int j = 0; j < LAST_PLAYER_STEP_X; j++) {
//                        if (field[i][j] == EMPTY_DOT){
//                            y = i;
//                            x = j;
//                            setSym(y,x,AI_DOT);
//                        }
//                    }
//                }
//            }
            x = random.nextInt(SIZE_X);
            y = random.nextInt(SIZE_Y);
        } while (!isCellValid(y, x));
        setSym(y,x,AI_DOT);
    }

    private static boolean isCellValid(int y, int x){
        if (x < 0 || y < 0 || x > SIZE_X - 1 || y > SIZE_Y - 1){
            return false;
        }
        return (field[y][x] == EMPTY_DOT);
    }

    private static boolean checkWin(char sym, int y, int x) {
        int counter = 0;
        //проверка горизонтали
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == sym) {
                    counter++;
                    if (counter == WINNING_STREAK) {
                        return true;
                    }
                }
                if (field[i][j] != sym) {
                    counter = 0;
                }
            }
        }
        counter = 0;
        //проверка вертикали
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (field[j][i] == sym) {
                    counter++;
                    if (counter == WINNING_STREAK) {
                        return true;
                    }
                }
                if (field[j][i] != sym) {
                    counter = 0;
                }
            }
        }
        counter = 0;
        //проверка диагонали справа налево
        for (int i = 0; i < SIZE_X; i++) {
            if (field[i][i] == sym) {
                counter++;
                if (counter == WINNING_STREAK) {
                    return true;
                }
            }
            if (field[i][i] != sym) {
                counter = 0;
            }
        }
        counter = 0;
        //проверка диагонали слева направо
        for (int i = 0, j = SIZE_Y - 1; i < SIZE_X; i++, j--) {
            if (field[j][i] == sym) {
                counter++;
                if (counter == WINNING_STREAK) {
                    return true;
                }
            }
            if (field[j][i] != sym) {
                counter = 0;
            }
        }
        return false;
    }

//    private static boolean checkForBLockStep(char sym, int y, int x) {
//        int counter = 0;
//        //проверка горизонтали
//        for (int i = 0; i < SIZE_Y; i++) {
//            for (int j = 0; j < SIZE_X; j++) {
//                if (field[i][j] == sym) {
//                    counter++;
//                    if (counter == WINNING_STREAK - 1) {
//                        return true;
//                    }
//                }
//                if (field[i][j] != sym) {
//                    counter = 0;
//                }
//            }
//        }
//        counter = 0;
//        //проверка вертикали
//        for (int i = 0; i < SIZE_X; i++) {
//            for (int j = 0; j < SIZE_Y; j++) {
//                if (field[j][i] == sym) {
//                    counter++;
//                    if (counter == WINNING_STREAK - 1) {
//                        return true;
//                    }
//                }
//                if (field[j][i] != sym) {
//                    counter = 0;
//                }
//            }
//        }
//        counter = 0;
//        //проверка диагонали справа налево
//        for (int i = 0; i < SIZE_X; i++) {
//            if (field[i][i] == sym) {
//                counter++;
//                if (counter == WINNING_STREAK - 1) {
//                    return true;
//                }
//            }
//            if (field[i][i] != sym) {
//                counter = 0;
//            }
//        }
//        counter = 0;
//        //проверка диагонали слева направо
//        for (int i = 0, j = SIZE_Y - 1; i < SIZE_X; i++, j--) {
//            if (field[j][i] == sym) {
//                counter++;
//                if (counter == WINNING_STREAK - 1) {
//                    return true;
//                }
//            }
//            if (field[j][i] != sym) {
//                counter = 0;
//            }
//        }
//        return false;
//    }

    private static boolean isFiledFull(){
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if(field[i][j]==EMPTY_DOT){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        initField();
        printField();

        while (true){
            playerStep();
            printField();
            if (checkWin(PLAYER_DOT, SIZE_Y, SIZE_X)){
                System.out.println("Player WIN!");
                break;
            }
            if (isFiledFull()){
                System.out.println("DRAW");
                break;
            }
            aiStep();
            printField();
            if (checkWin(AI_DOT, SIZE_Y, SIZE_X)){
                System.out.println("Win SkyNet!");
                break;
            }
            if (isFiledFull()){
                System.out.println("DRAW");
                break;
            }
        }
    }
}
