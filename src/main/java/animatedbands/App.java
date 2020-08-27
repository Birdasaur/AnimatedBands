package animatedbands;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class App extends Application {

    private static final Paint SCENE_FILL = new RadialGradient(
            0, 0, 300, 300, 500, false, CycleMethod.NO_CYCLE,
            FXCollections.observableArrayList(new Stop(0, Color.BLACK), new Stop(1, Color.BLUE))
    );

    @Override
    public void start(Stage stage) {
        BandGenerator bands = new BandGenerator(50, 100, 20.0);
        final Scene scene = new Scene(bands, 600, 600, SCENE_FILL);
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.isSecondaryButtonDown()) {
                    bands.getChildren().clear();
                } else if(event.isPrimaryButtonDown()) {
                    bands.setGeneratorCenterX(event.getSceneX());
                    bands.setGeneratorCenterY(event.getSceneY());
                    bands.createQuadBand();
                }
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bands.setGeneratorCenterX(event.getSceneX());
                bands.setGeneratorCenterY(event.getSceneY());
            }
        });

        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bands.stopGenerating();
            }
        });

        stage.setTitle("Click to create random band. Rightclick to clear.");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}