public class Employee {
    String fio;
    String position;
    String email;
    String tel;
    int salary;
    int age;

    public Employee() {
        fio = "Иванов Иван Иванович";
        position = "работник";
        email = "sampletext@mail.com";
        tel = "88005553535";
        salary = 120000;
        age = 16;
    }

    public Employee(String fio, String position, String email, String tel, int salary, int age) {
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.tel = tel;
        this.salary = salary;
        this.age = age;
    }

    public void task1(){
        System.out.println("FIO: "+ fio +", position: " + position +", email: "+ email+", tel: "+ tel+", salary: " + salary+", age: "+ age+".");
    }

    public void task2(){
        Employee[] empArray = new Employee[5];
        empArray[0] = new Employee("ivanov ivan", "engineer", "ivivan@mailbox.com", "88005553536", 30000, 16);
        empArray[1] = new Employee("Petrov Petr", "manager", "petrn@mailbox.com", "88005553537", 40000, 17);
        empArray[2] = new Employee("Bagryanorodny Konstanin", "king", "Kostya905@mailbox.com", "88005553538", 50000, 18);
        empArray[3] = new Employee("Jmyshenko Valery", "artist", "valrejmyh@mailbox.com", "88005553539", 60000, 19);
        empArray[4] = new Employee("Gamaz Ivan", "dancer", "ivgamaz@mailbox.com", "88005553540", 70000, 20);
        for (Employee emp : empArray) {
            emp.task1();
        }
    }
}
