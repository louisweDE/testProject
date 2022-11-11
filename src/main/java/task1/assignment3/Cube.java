package task1.assignment3;

public class Cube implements Shape {
    private double side;

    public Cube(double side) {
        if (side <= 0)
            throw new IllegalArgumentException("Negative side");
        this.side = side;
    }

    @Override
    public double countVolume() {
        double volume = Math.pow(this.side, 3);
        return volume;
    }
}
