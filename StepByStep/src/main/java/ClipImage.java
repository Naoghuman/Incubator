import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ClipImage extends Application {

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();

        // background image
        ImageView imageView = new ImageView( "http://i.stack.imgur.com/EuOrB.jpg");

        // some text
        Label label = new Label( "This is a Tiger. Tigers are awesome!");
        label.relocate(20, 400);
        label.setTextFill(Color.RED);
        label.setFont(new Font("Tahoma", 48));

        root.getChildren().addAll( imageView, label);

        Scene scene = new Scene( root, 1024, 768);

        primaryStage.setScene( scene);
        primaryStage.show();

        // pane with clipped area
        CirclePane circlePane = new CirclePane();
        makeDraggable( circlePane);

        root.getChildren().addAll( circlePane);

    }

    public static void main(String[] args) {
        launch(args);
    }


    private class CirclePane extends Pane {

        public CirclePane() {

            // load image
//          ImageView imageView = new ImageView( getClass().getResource("tiger.jpg").toExternalForm());
            ImageView imageView = new ImageView( "http://i.stack.imgur.com/EuOrB.jpg");

            // create circle
            Circle circle = new Circle( 200);
            circle.relocate(200, 100);

            // clip image by circle
            imageView.setClip(circle);

            // non-clip area should be transparent
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT); 

            // new image from clipped image
            WritableImage wim = null;
            wim = imageView.snapshot(parameters, wim);

            // new imageview
            ImageView clippedView = new ImageView( wim);

            // some shadow
            clippedView.setEffect(new DropShadow(15, Color.BLACK));

            clippedView.relocate( 200, 100);

            getChildren().addAll( clippedView);

        }

    }

    // make node draggable
    class DragContext { 
        double x;
        double y; 
    } 

    public void makeDraggable( Node node) {

        final DragContext dragDelta = new DragContext();

        node.setOnMousePressed(mouseEvent -> {

            dragDelta.x = node.getBoundsInParent().getMinX() - mouseEvent.getScreenX();
            dragDelta.y = node.getBoundsInParent().getMinY() - mouseEvent.getScreenY();

        });

        node.setOnMouseDragged(mouseEvent -> node.relocate( mouseEvent.getScreenX() + dragDelta.x, mouseEvent.getScreenY() + dragDelta.y));

    }


}
