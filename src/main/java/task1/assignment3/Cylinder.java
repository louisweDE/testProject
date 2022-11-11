package task1.assignment3;

public class Cylinder implements Shape {
    private double height;
    private double radius;

    public Cylinder(double height, double radius) {
        if (height <= 0 || radius <= 0)
            throw new IllegalArgumentException("Negative height or radius");
        this.height = height;
        this.radius = radius;
    }

    @Override
    public double countVolume() {
        double volume = Math.PI * Math.pow(this.radius, 2) * this.height;
        return volume;
    }
}
