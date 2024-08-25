class FoodBowl {
    private int foodAmount;

    public FoodBowl(int initialFood) {
        this.foodAmount = initialFood;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void eat(int amount) {
        if (foodAmount - amount >= 0) {
            foodAmount -= amount;
        } else {
            System.out.println("Недостаточно еды в миске.");
        }
    }

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("Добавлено " + amount + " единиц еды в миску. Теперь в миске " + foodAmount + " единиц еды.");
        } else {
            System.out.println("Нельзя добавить отрицательное количество еды.");
        }
    }
}
