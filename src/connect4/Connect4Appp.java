package connect4;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Created by Gabriel on 24.01.2017.
 */
public class Connect4Appp extends Application {
    private static final int TILESIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;

    private boolean redTurn = true;
    private Disc[][] grid = new Disc[COLUMNS][ROWS];

    private Pane discPane = new Pane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private Parent createContent() {
        Pane root = new Pane();
        root.getChildren().add(discPane);
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
        int row = ROWS - 1;
        do {
            if (!getDisc(column, row).isPresent()) {
                break;
            }
            row--;
        } while (row >= 0);
        if (row < 0) {
            return;
        }
        grid[column][row] = disc;
        discPane.getChildren().add(disc);
        disc.setTranslateX(column * (TILESIZE + 5) + TILESIZE / 4);
        final int localRow = row;
        TranslateTransition animation = new TranslateTransition(Duration.seconds(1), disc);
        animation.setToY(row * (TILESIZE + 5) + TILESIZE / 4);
        animation.setOnFinished(e -> {
            if (gameEnd(column, localRow)) {
                gameFinish();
            }
            redTurn = !redTurn;
        });
        animation.play();
    }

    private boolean check4(List<Point2D> point2DList) {
        int combination = 0;
        for (Point2D p : point2DList) {
            int col = (int) p.getX();
            int row = (int) p.getY();
            Disc disc = getDisc(col, row).orElse(new Disc(!redTurn));
            if (disc.red == redTurn) {
                combination++;
                if (combination == 4) {
                    return true;
                }

            } else {
                combination = 0;
            }
        }
        return false;
    }

    private boolean gameEnd(int col, int row) {

        List<Point2D> vertical = IntStream.rangeClosed(row - 3, row + 3).mapToObj(r -> new Point2D(col, r)).collect(Collectors.toList());
        List<Point2D> horizontal = IntStream.rangeClosed(col - 3, col + 3).mapToObj(c -> new Point2D(c, row)).collect(Collectors.toList());

        Point2D topL = new Point2D(col - 3, row - 3);
        List<Point2D> diagonalLeft = IntStream.rangeClosed(0, 6).mapToObj(i -> topL.add(i, i)).collect(Collectors.toList());

        Point2D bottL = new Point2D(col - 3, row + 3);
        List<Point2D> diagonalRight = IntStream.rangeClosed(0, 6).mapToObj(i -> bottL.add(i, -i)).collect(Collectors.toList());

        return check4(vertical) || check4(horizontal) || check4(diagonalLeft) || check4(diagonalRight);
    }


    private void gameFinish() {
        System.out.println("Winner: " + (redTurn ? "RED" : "YELLOW"));
        System.exit(0);
    }

    private Optional<Disc> getDisc(int col, int row) {
        if (col < 0 || col >= COLUMNS || row < 0 || row >= ROWS) {
            return Optional.empty();
        }
        return Optional.ofNullable(grid[col][row]);

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
