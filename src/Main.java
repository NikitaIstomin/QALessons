public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog();
        dog1.run(300);
        dog1.swim(5);

        Cat cat1 = new Cat();
        cat1.run(150);
        cat1.swim(10);

        FoodBowl bowl = new FoodBowl(10);

        Cat[] cats = {new Cat(), new Cat(), new Cat()};

        cat1.eat(bowl);
        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        for (int i = 0; i < cats.length; i++) {
            System.out.println("Кот " + (i + 1) + " сыт: " + cats[i].isFull());
        }

        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Всего собак: " + Dog.getDogCount());
        System.out.println("Всего котов: " + Cat.getCatCount());

        bowl.addFood(5);

        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        for (int i = 0; i < cats.length; i++) {
            System.out.println("Кот " + (i + 1) + " сыт: " + cats[i].isFull());
        }
        //Второе задание:
        Shape circle = new Circle(5, "Красный", "Черный");
        Shape rectangle = new Rectangle(4, 6, "Синий", "Зеленый");
        Shape triangle = new Triangle(3, 4, 5, "Желтый", "Фиолетовый");

        System.out.println("Круг: " + circle.getDetails() + ", Цвет заливки: " + circle.getFillColor() + ", Цвет границы: " + circle.getBorderColor());
        System.out.println("Прямоугольник: " + rectangle.getDetails() + ", Цвет заливки: " + rectangle.getFillColor() + ", Цвет границы: " + rectangle.getBorderColor());
        System.out.println("Треугольник: " + triangle.getDetails() + ", Цвет заливки: " + triangle.getFillColor() + ", Цвет границы: " + triangle.getBorderColor());
    }
}
