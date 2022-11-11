package task1.assignment3;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SortShapes {
    public static Collection<Shape> sortShapesList(Collection<Shape> shapeList) {
        if (shapeList == null)
            throw new IllegalArgumentException("Shape List is null!");

        return shapeList
                .stream()
                .sorted(Comparator.comparing(Shape::countVolume))
                .collect(Collectors.toList());
    }
}
