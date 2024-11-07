package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle implements DrawableShape {
    private double x;
    private double y;
    private final double width;
    private final double height;
    private double offsetX;
    private double offsetY;
    private Color color;

    public Rectangle(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x, y, width, height);
    }

    @Override
    public boolean contains(double px, double py) {
        return px >= x && px <= x + width && py >= y && py <= y + height;
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
