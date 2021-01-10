package draw.shapes;

public class Main {
    public static void main(String[] args) {
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        GhostManager ghostManager = new GhostManager();
        ShapesView shapesView = new ShapesView(ghostManager);
        StrokeListener listener = new StrokeListener(ghostManager, shapesView, analyzer);

        DrawShapesFrame drawShapesFrame = new DrawShapesFrame(listener, shapesView);
        drawShapesFrame.setVisible(true);
        PaintTask paintTask = new PaintTask(shapesView,ghostManager);
        GhostThread thread = new GhostThread(ghostManager, paintTask);

        thread.start();
    }
}
