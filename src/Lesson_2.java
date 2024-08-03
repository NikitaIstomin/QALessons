public class Lesson_2 {
    //task1
    public static void printTreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }
    //task 2
    public static void checkSumSign() {
        int a = 1;
        int b = -4;
        if (a+b>=0){
            System.out.println("Сумма положительная");
        }
        else {
            System.out.println("Сумма отрицательная");
        }
    }
    //task 3
    public static void printColor() {
        int value = 100;
        if (value <= 0) {
            System.out.println("Красный");
        }
        if (value > 0 && value <=100) {
            System.out.println("Желтый");
        }
        if (value > 100) {
            System.out.println("Зеленый");
        }
    }
    //task 4
    public static void compareNumbers() {
        int a = 1;
        int b = 2;
        if (a>=b){
            System.out.println("a >= b");
        }
        else{
            System.out.println("a < b");
        }
    }
    //task 5
    public static boolean task5(int a, int b) {
        if (a+b>=10 && a+b<=20){
            return true;
        }
        else{
            return false;
        }
    }
    //task 6
    public static void task6(int a) {
        if (a<0) {
            System.out.println("число отрицательное");
        }
        else {
            System.out.println("число положительное");
        }
    }
    //task 7
    public static boolean task7(int a) {
        if (a<0) {
            return true;
        }
        else {
            return false;
        }
    }
    //task 8
    public static void task8(String word, int count) {
        for (int i=0; i<count; i++){
            System.out.println(word);
        }
    }
    //task 9
    public static boolean task9(int year) {
        if (year%4==0)
        {
            if (year%100 ==0){
                return year%400==0;
            }
            return true;
        }
        else {
            return false;
        }
    }
    //task 10
    public static void task10() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        int size = array.length;
        System.out.print("Было:  [");
        for (int i = 0; i<size; i++){
            if (i+1!=size){
                System.out.print(array[i]+", ");
            }
            else {
                System.out.print(array[i]+"]");
            }
        }
        System.out.println(" ");
        System.out.print("Стало: [");
        for (int i = 0; i<size; i++) {
            if (array[i] ==1) {
                array[i] = 0;
            }
            else {
                array[i]=1;
            }
            if (i+1!=size){
                System.out.print(array[i]+", ");
            }
            else {
                System.out.println(array[i]+"]");
            }
        }
    }
    //task 11
    public static void task11() {
        int size = 100;
        int[] array = new int[size];
        System.out.print("[");
        for (int i = 0; i<size; i++){
            array[i]=i+1;
            if (i+1<size){
                System.out.print(array[i]+", ");
            }
            else {
                System.out.print(array[i]+"]");
            }
            if (array[i]%10==0) {
                System.out.println("");
            }
        }
    }
    //task 12
    public static void task12() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int size = array.length;
        System.out.print("Было:  [");
        for (int i = 0; i<size; i++){
            if (i+1!=size){
                System.out.print(array[i]+", ");
            }
            else {
                System.out.print(array[i]+"]");
            }
        }
        System.out.println(" ");
        System.out.print("Стало: [");
        for (int i = 0; i<size; i++) {
            if (array[i] < 6) {
                array[i] = array[i]*2;
            }
            if (i+1!=size){
                System.out.print(array[i]+", ");
            }
            else {
                System.out.println(array[i]+"]");
            }
        }
    }
    //task 13
    public static void task13(){
        int size = 5;
        int[][] array = new int[size][size];
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                if ((i+j==size-1)||(i==j)){
                    array[i][j]=1;
                }
                else {
                    array[i][j]=0;
                }
                System.out.print(array[i][j]+", ");
            }
            System.out.println("");
        }
    }
    //task 14
    public static int[] task14(int len, int initialValue){
        int[] array=new int[len];
        for (int i = 0; i<len; i++){
            array[i]=initialValue;
        }
        return array;
    }
}
