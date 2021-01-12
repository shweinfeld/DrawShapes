package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * build ghosts
 */

public class BombFactory {
    private final int OFFSET = 200;
    private final ShapeFactory shapeFactory;
    private final Random RANDOM = new Random();

    public BombFactory(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public Bomb newInstance(int numShapes) {
        return new Bomb(getNewShapeQueue(numShapes), getRandomLocation());
    }

    private Queue<Shape> getNewShapeQueue(int numShapes) {
        Queue<Shape> shapeQueue = new LinkedList<>();
        for (int i = 0; i < numShapes; i++) {
            shapeQueue.add(shapeFactory.newInstance());
        }
        return shapeQueue;
    }

    private Point getRandomLocation() {
        return new Point(RANDOM.nextInt(DrawShapesFrame.WIDTH - OFFSET), RANDOM.nextInt(DrawShapesFrame.HEIGHT - OFFSET) );
    }
}
