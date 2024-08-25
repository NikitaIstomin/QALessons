interface Shape {
    double area();
    double perimeter();

    default String getDetails() {
        return "Площадь: " + area() + ", Периметр: " + perimeter();
    }

    String getFillColor();
    String getBorderColor();
}