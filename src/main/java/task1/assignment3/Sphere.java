package task1.assignment3;

public class Sphere implements Shape {
    private double radius;

    public Sphere(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("Negative radius");
        this.radius = radius;
    }

    @Override
    public double countVolume() {
        return  4 / 3 * Math.PI * Math.pow(this.radius, 3);
    }
}
