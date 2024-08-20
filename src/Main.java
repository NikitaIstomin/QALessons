public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee();
        System.out.println("вывод конструктора:");
        employee.task1();
        System.out.println("Вывод Массива:");
        employee.task2();
        Park park = new Park();
        park.addAttraction("Американские горки", "10:00 - 22:00", 300);
        park.addAttraction("Колесо обозрения", "10:00 - 21:00", 250);
        park.addAttraction("Карусель", "10:00 - 20:00", 150);
        park.showAttractions();
    }

}