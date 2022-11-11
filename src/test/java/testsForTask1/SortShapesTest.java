package testsForTask1;

import org.junit.Test;
import task1.assignment3.Cube;
import task1.assignment3.Cylinder;
import task1.assignment3.Shape;
import task1.assignment3.SortShapes;
import task1.assignment3.Sphere;

import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SortShapesTest {
    @Test
    public void testSortNormalShapesList() {
        Shape cube = new Cube(7);
        Shape sphere = new Sphere(5);
        Shape cylinder = new Cylinder(8, 4);
        Shape cube2 = new Cube(4.5);
        Shape sphere2 = new Sphere(7.5);
        Shape cylinder2 = new Cylinder(5, 2.3);
        Shape cube3 = new Cube(8.9);
        Shape sphere3 = new Sphere(6);

        List<Shape> shapesList = List.of(cube, sphere, cylinder,
                cube2, sphere2, cylinder2, cube3, sphere3);

        Collection<Shape> resultShapes = SortShapes.sortShapesList(shapesList);

        Collection<Shape> expectedRes = List.of(cylinder2, cube2, cube,
                sphere, cylinder, sphere3, cube3, sphere2);

        assertEquals(expectedRes, resultShapes);

    }

    @Test
    public void testSorNullShapeList() {
        Collection<Shape> nullShapeList = null;

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> {
                    SortShapes.sortShapesList(nullShapeList);
                }, "Allowed null");

        assertEquals("Shape List is null!", ex.getMessage());
    }
}
