public class Main {
    public static void main(String[] args) {
        System.out.println("task1:");
        Lesson_2.printTreeWords();
        System.out.println("task2:");
        Lesson_2.checkSumSign();
        System.out.println("task3:");
        Lesson_2.printColor();
        System.out.println("task4:");
        Lesson_2.compareNumbers();
        System.out.println("task5:");
        System.out.println(Lesson_2.task5(5, 10));
        System.out.println("task6:");
        Lesson_2.task6(-5);
        System.out.println("task7:");
        System.out.println(Lesson_2.task7(5));
        System.out.println("task8:");
        Lesson_2.task8("sample text", 3);
        System.out.println("task9:");
        System.out.println(Lesson_2.task9(500));
        System.out.println("task10:");
        Lesson_2.task10();
        System.out.println("task11:");
        Lesson_2.task11();
        System.out.println("task12:");
        Lesson_2.task12();
        System.out.println("task13:");
        Lesson_2.task13();
        System.out.println("task14:");
        int[] array= Lesson_2.task14(8,15);
        for(int i = 0; i<8; i++){
            System.out.print(array[i]+" ");
        }
    }
}