public class Main {
    public static void main(String[] args) {
        String[][] validArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] invalidArraySize = {
                {"1", "2"},
                {"3", "4"}
        };

        String[][] invalidArrayData = {
                {"1", "2", "3", "4"},
                {"5", "six", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = ArraySum.arraySum(validArray);
            System.out.println("Сумма элементов массива: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            ArraySum.arraySum(invalidArraySize);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            ArraySum.arraySum(invalidArrayData);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());

        }
    }
}
