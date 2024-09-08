public class Main {
    public static void main(String[] args) {

        System.out.println("Task 1:");
        UniqueWordsCounter.ShowTask1();

        System.out.println("Task 2:");
                PhoneBook myPhoneBook = new PhoneBook();

        myPhoneBook.add("Петров", "88005553535");
        myPhoneBook.add("Иванов", "+79253595322");
        myPhoneBook.add("Иванов", "+380947115144");
        myPhoneBook.add("Сидоров", "223-322-223-322");

        System.out.println("Телефоны Иванова: " + myPhoneBook.get("Иванов"));
        System.out.println("Телефоны Петрова: " + myPhoneBook.get("Петров"));
        System.out.println("Телефоны Сидорова: " + myPhoneBook.get("Сидоров"));

    }
}
