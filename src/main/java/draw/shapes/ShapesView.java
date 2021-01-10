package draw.shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class ShapesView extends JComponent {
    private ArrayList<Point> drawing = new ArrayList<Point>();
    private BombManager bombManager;
    private int bombXval;
    private int bombYval;
    public static final int BOMB_DIAMETER = 130;
    public static int BOMB_BORDER_DIAMETER = 140;
    private final int BOMB_TOP_DIMENSION = 35;
    private final Color BOMB_COLOR = Color.BLACK;
    private final Color START_COLOR = new Color(10, 240, 120);
    private final Color SHINE_COLOR = Color.WHITE;

    public ShapesView(BombManager bombManager) {
        this.bombManager = bombManager;
    }

    public void clearDrawing() {
        drawing.clear();
    }

    public void addPoint(Point point) {
        drawing.add(point);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintStroke(g);
        paintBombs(g);
    }

    private void paintBombs(Graphics g) {
        List<Bomb> bombList = bombManager.getBombList();
        for (Bomb bomb : bombList) {
            bombXval = bomb.getLocation().getX();
            bombYval = bomb.getLocation().getY();
            paintShapes(g, bomb);
            paintBomb(g, bomb);

        }
    }

    private void paintBomb(Graphics g, Bomb bomb) {
        int bombOutlineX = bombXval - 70;
        int bombOutlineY = bombYval + 27;
        int bombX = bombXval - 65;
        int bombY = bombYval + 32;


        drawOutline(g, bombOutlineX, bombOutlineY, bomb.getAge());
        g.setColor(BOMB_COLOR);
        g.fillOval(bombX, bombY, BOMB_DIAMETER, BOMB_DIAMETER);

        int bombRadius = BOMB_BORDER_DIAMETER / 2;

        g.fillRect(bombOutlineX + (bombRadius - BOMB_TOP_DIMENSION / 2), bombYval + 20, BOMB_TOP_DIMENSION, BOMB_TOP_DIMENSION);
        g.fillOval(bombOutlineX + (bombRadius - BOMB_TOP_DIMENSION / 2), bombYval + 10, BOMB_TOP_DIMENSION, BOMB_TOP_DIMENSION / 2);

        g.setColor(SHINE_COLOR);
        g.fillArc(bombX + 25, bombY + 30, 10, 25, 50, 180);
    }

    private void drawOutline(Graphics g, int bombOutlineX, int bombOutlineY, float age) {
        g.setColor(getCurrentColor(age));
        g.fillOval(bombOutlineX, bombOutlineY, BOMB_BORDER_DIAMETER, BOMB_BORDER_DIAMETER);
    }

    private void paintShapes(Graphics g, Bomb bomb) {
        for (Shape shape : bomb.getShapeQueue()) {
            switch (shape) {
                case CARAT:
                    drawCarat(g);
                    break;
                case VEE:
                    drawVee(g);
                    break;
                case HORIZONTAL:
                    drawHorizontal(g);
                    break;
                case VERTICAL:
                    drawVertical(g);
                    break;
                default:
                    break;
            }
        }
    }

    private void drawVertical(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 30));
        g.setColor(Color.RED);
        bombXval += 10;
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform orig = g2d.getTransform();
        g2d.rotate(Math.toRadians(-90), bombXval++, bombYval);
        g2d.drawString("\u2796", bombXval++, bombYval);
        g2d.setTransform(orig);
        bombXval += 10;
    }

    private void drawVee(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 50));
        g.setColor(Color.ORANGE);
        g.drawString("\u02c5", bombXval++, bombYval);
        bombXval += 30;
    }

    private void drawHorizontal(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 30));
        g.setColor(Color.BLUE);
        g.drawString("\u2796", bombXval++, bombYval);
        bombXval += 50;
    }

    private void drawCarat(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 50));
        g.setColor(Color.GREEN);
        g.drawString("\u02c4", bombXval++, bombYval);
        bombXval += 30;
    }

    private void paintStroke(Graphics g) {
        if (!bombManager.isGameOver()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            for (int i = 0; i < drawing.size() - 2; i++) {
                g2.drawLine(drawing.get(i).getX(), drawing.get(i).getY(), drawing.get(i + 1).getX(), drawing.get(i + 1).getY());
            }
        }
    }

    //chnage to take ghost object?
    private Color getCurrentColor(float age) {
        age *= 20;
        return new Color(START_COLOR.getRed() + (int) age, START_COLOR.getGreen() - (int) age, START_COLOR.getBlue());
    }
}
