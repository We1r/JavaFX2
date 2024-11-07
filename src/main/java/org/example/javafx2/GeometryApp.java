package org.example.javafx2;

import geometry2d.DrawableShape;
import geometry2d.Circle;
import geometry2d.Rectangle;
import geometry2d.Triangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeometryApp  extends Application {
    private Canvas canvas;
    private final List<DrawableShape> shapes = new ArrayList<>();
    private DrawableShape selectedShape;
    private final Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);

        canvas = new Canvas(800, 600);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        VBox buttonBox = new VBox(10);

        Button drawCircleButton = new Button("Нарисовать круг");
        Button drawRectangleButton = new Button("Нарисовать прямоугольник");
        Button drawTriangleButton = new Button("Нарисовать треугольник");

        styleButton(drawCircleButton);
        styleButton(drawRectangleButton);
        styleButton(drawTriangleButton);

        drawCircleButton.setOnAction(e -> drawRandomCircle());
        drawRectangleButton.setOnAction(e -> drawRandomRectangle());
        drawTriangleButton.setOnAction(e -> drawRandomTriangle());

        buttonBox.getChildren().addAll(drawCircleButton, drawRectangleButton, drawTriangleButton);

        Pane root = new Pane(canvas, buttonBox);

        buttonBox.setLayoutX(820);
        buttonBox.setLayoutY(20);
        buttonBox.setPrefHeight(canvas.getHeight());
        root.setStyle("-fx-background-color: #e8b274;");

        Scene scene = new Scene(root, 1050, 600);
        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseDragged(this::onMouseDragged);
        canvas.setOnMouseClicked(this::onMouseClicked);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Geometry2D App");
        primaryStage.show();

        redraw();
    }

    private void styleButton(Button button) {
        button.setPrefWidth(200);
        button.setPrefHeight(50);
        button.setFont(Font.font("Arial", 14));
        button.setStyle("-fx-background-color: #8b4513; -fx-text-fill: white;");
    }

    private void drawRandomCircle() {
        double x = random.nextDouble() * (canvas.getWidth() - 100);
        double y = random.nextDouble() * (canvas.getHeight() - 100);
        double radius = 20 + random.nextDouble() * 50;
        Color color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
        Circle circle = new Circle(x, y, radius, color);
        shapes.add(circle);
        redraw();
    }

    private void drawRandomRectangle() {
        double x = random.nextDouble() * (canvas.getWidth() - 100);
        double y = random.nextDouble() * (canvas.getHeight() - 100);
        double width = 30 + random.nextDouble() * 70;
        double height = 30 + random.nextDouble() * 70;
        Color color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
        Rectangle rectangle = new Rectangle(x, y, width, height, color);
        shapes.add(rectangle);
        redraw();
    }

    private void drawRandomTriangle() {
        double x = random.nextDouble() * (canvas.getWidth() - 100);
        double y = random.nextDouble() * (canvas.getHeight() - 100);
        double size = 30 + random.nextDouble() * 50;
        Color color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
        Triangle triangle = new Triangle(x, y, size, color);
        shapes.add(triangle);
        redraw();
    }

    private void redraw() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphicsContext.setStroke(Color.web("#8b4513"));
        graphicsContext.setLineWidth(8);
        graphicsContext.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (DrawableShape shape : shapes) {
            shape.draw(graphicsContext);
        }
    }

    private void onMousePressed(javafx.scene.input.MouseEvent event) {
        selectedShape = getShapeAt(event.getX(), event.getY());
        if (selectedShape != null) {
            shapes.remove(selectedShape);
            shapes.add(selectedShape);
            selectedShape.setOffset(event.getX(), event.getY());
            redraw();
        }
    }

    private void onMouseDragged(javafx.scene.input.MouseEvent event) {
        if (selectedShape != null) {
            selectedShape.move(event.getX(), event.getY());
            redraw();
        }
    }

    private void onMouseClicked(javafx.scene.input.MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            DrawableShape shape = getShapeAt(event.getX(),event.getY());
            if (shape != null) {
                shape.setColor(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
                redraw();
            }
        }
    }

    private DrawableShape getShapeAt(double x, double y) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).contains(x, y)) {
                return shapes.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
