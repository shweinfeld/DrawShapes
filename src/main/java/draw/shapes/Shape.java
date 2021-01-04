package draw.shapes;

import java.util.List;
import java.util.Random;

public enum Shape {
    HORIZONTAL, VERTICAL, UP_SLOPE, DOWN_SLOPE;

    //dont need these
    private static final List<Shape> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    //need this
    private static final Random RANDOM = new Random();

    //put this in a factory
    public static Shape randomShape() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}