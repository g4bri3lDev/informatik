package connect4;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gabriel on 24.01.2017.
 */
public class Connect4Appp extends Application {
    private static final int TILESIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;

    private boolean redTurn = true;
    private Disc[][] grid = new Disc[COLUMNS][ROWS];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    private Parent createContent() {
        Pane root = new Pane();
        Shape gridShape = genGrid();
        root.getChildren().add(gridShape);
        root.getChildren().addAll(columnList());
        return root;
    }

    private Shape genGrid() {
        Shape shape = new Rectangle((COLUMNS + 1) * TILESIZE, (ROWS + 1) * TILESIZE);
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                Circle circle = new Circle(TILESIZE / 2);
                circle.setCenterX(TILESIZE / 2);
                circle.setCenterY(TILESIZE / 2);
                circle.setTranslateX(x * (TILESIZE + 5) + TILESIZE / 4);
                circle.setTranslateY(y * (TILESIZE + 5) + TILESIZE / 4);
                shape = Shape.subtract(shape, circle);
            }
        }
        shape.setFill(Color.GRAY);
        return shape;
    }

    private List<Rectangle> columnList() {
        List<Rectangle> rectList = new ArrayList<>();
        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rectangle = new Rectangle(TILESIZE, (ROWS + 1) * TILESIZE);
            rectangle.setTranslateX(x * (TILESIZE + 5) + TILESIZE / 4);
            rectangle.setFill(Color.TRANSPARENT);
            final int column = x;
            rectangle.setOnMouseClicked(e -> placeDisc(new Disc(redTurn), column));
            rectangle.setOnMouseEntered(e -> rectangle.setFill(Color.rgb(0, 200, 50, 0.3)));
            rectangle.setOnMouseExited(e -> rectangle.setFill(Color.TRANSPARENT));
            rectList.add(rectangle);
        }
        return rectList;
    }

    private void placeDisc(Disc disc, int column) {

    }

    private static class Disc extends Circle {

        private final boolean red;

        public Disc(boolean red) {
            super(TILESIZE / 2, red ? Color.RED : Color.YELLOW);
            this.red = red;
            setCenterX(TILESIZE / 2);
            setCenterY(TILESIZE / 2);
        }
    }
}
