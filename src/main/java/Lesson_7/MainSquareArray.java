package Lesson_7;

public class MainSquareArray {

    public static void main(String[] args) {

        final int SIZE = 5;
        int nums = 1;
        int[][] arr = new int[SIZE][SIZE];
        int minRow = 0;
        int maxRow = SIZE - 1;
        int minCol = 0;
        int maxCol = SIZE - 1;

        while (nums <= SIZE * SIZE) {

            for (int i = minCol; i <= maxCol; i++) {
                arr[minRow][i] = nums;
                nums++;
            }

            for (int i = minRow + 1; i <= maxRow; i++) {
                arr[i][maxCol] = nums;
                nums++;
            }

            for (int i = maxCol - 1; i >= minCol; i--) {
                arr[maxRow][i] = nums;
                nums++;
            }

            for (int i = maxRow - 1; i >= minRow + 1; i--) {
                arr[i][minCol] = nums;
                nums++;
            }

            minRow++;
            minCol++;
            maxRow--;
            maxCol--;

        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }


    }

}
