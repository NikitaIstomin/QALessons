import java.util.ArrayList;
import java.util.List;

public class Park {
    private List<Attraction> attractions;

    public Park() {
        this.attractions = new ArrayList<>();
    }

    public void addAttraction(String name, String workingHours, int price) {
        attractions.add(new Attraction(name, workingHours, price));
    }

    public void showAttractions() {
        System.out.println("Аттракционы в парке:");
        for (Attraction attraction : attractions) {
            attraction.showInfo();
        }
    }

    // Внутренний класс Attraction
    private class Attraction {
        String name;
        String workingHours;
        int price;

        public Attraction(String name, String workingHours, int price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void showInfo() {
            System.out.println("Название: " + name + ", Время работы: " + workingHours + ", Стоимость: " + price + " руб.");
        }
    }
}