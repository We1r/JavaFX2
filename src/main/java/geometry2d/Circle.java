package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements DrawableShape {
    private double x;
    private double y;
    private final double radius;
    private double offsetX;
    private double offsetY;
    private Color color;

    public Circle(double x, double y, double radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public boolean contains(double px, double py) {
        return Math.pow(px - x, 2) + Math.pow(py - y, 2) <= Math.pow(radius, 2);
    }

    @Override
    public void move(double px, double py) {
        x = px - offsetX;
        y = py - offsetY;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setOffset(double px, double py) {
        offsetX = px - x;
        offsetY = py - y;
    }
}
