class Triangle implements Shape {
    private double a, b, c; // Стороны треугольника
    private String fillColor;
    private String borderColor;

    public Triangle(double a, double b, double c, String fillColor, String borderColor) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double area() {
        double s = (a + b + c) / 2; // Полупериметр
        return Math.sqrt(s * (s - a) * (s - b) * (s - c)); // Формула Герона
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

