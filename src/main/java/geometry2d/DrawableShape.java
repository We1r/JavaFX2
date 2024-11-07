package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface DrawableShape {
    void draw(GraphicsContext graphicsContext);
    boolean contains(double x, double y);
    void move(double x, double y);
    void setColor(Color color);
    void setOffset(double x, double y);
}
