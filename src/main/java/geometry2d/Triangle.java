package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle implements DrawableShape {
    private final double[] xPoints;
    private final double[] yPoints;
    private double baseX, baseY;
    private double offsetX, offsetY;
    private Color color;

    public Triangle(double x, double y, double size, Color color) {
        this.xPoints = new double[] { x, x + size, x - size };
        this.yPoints = new double[] { y, y + size, y + size };
        this.baseX = x;
        this.baseY = y;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color);
        graphicsContext.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public boolean contains(double px, double py) {
        double x0 = xPoints[0], y0 = yPoints[0];
        double x1 = xPoints[1], y1 = yPoints[1];
        double x2 = xPoints[2], y2 = yPoints[2];
        double denominator = ((y1 - y2) * (x0 - x2) + (x2 - x1) * (y0 - y2));
        double a = ((y1 - y2) * (px - x2) + (x2 - x1) * (py - y2)) / denominator;
        double b = ((y2 - y0) * (px - x2) + (x0 - x2) * (py - y2)) / denominator;
        double c = 1 - a - b;
        return a >= 0 && b >= 0 && c >= 0;
    }

    @Override
    public void move(double px, double py) {
        double dx = px - offsetX;
        double dy = py - offsetY;

        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] += dx;
            yPoints[i] += dy;
        }
        offsetX = px;
        offsetY = py;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setOffset(double px, double py) {
        offsetX = px;
        offsetY = py;
    }
}
