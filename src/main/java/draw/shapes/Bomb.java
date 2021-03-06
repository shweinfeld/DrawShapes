package draw.shapes;

import java.util.Queue;

public class Bomb {

    private Point location;
    private Queue<Shape> shapeQueue;
    public static final int RADIUS = ShapesView.BOMB_BORDER_DIAMETER + 60;
    private long birthday;

    public Bomb(Queue<Shape> shapeQueue, Point location) {
        this.shapeQueue = shapeQueue;
        this.location = location;
        this.birthday = System.currentTimeMillis() / 1000;
    }

    public Queue<Shape> getShapeQueue() {
        return shapeQueue;
    }

    public Point getLocation() {
        return location;
    }


    public boolean intersects(Bomb other) {
        return this.getLocation().getDistance(other.getLocation()) < RADIUS;
    }

    public void removeShape() {
        shapeQueue.remove();
    }

    /**
     * return the number of seconds since the bomb was created
     * @return
     */
    public long getAge() {
        return System.currentTimeMillis() / 1000 - birthday;
    }
}
