class Dog extends Animal {
    private static int dogCount = 0;

    public Dog() {
        super();
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            System.out.println("Собака пробежала " + distance + " м.");
        } else {
            System.out.println("Собака не может пробежать больше 500 м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            System.out.println("Собака проплыла " + distance + " м.");
        } else {
            System.out.println("Собака не может проплыть больше 10 м.");
        }
    }

    public static int getDogCount() {
        return dogCount;
    }
}

