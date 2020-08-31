package animatedbands;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.stage.Stage;

public class App extends Application {

    private static final Paint SCENE_FILL = new RadialGradient(
            0, 0, 300, 300, 500, false, CycleMethod.NO_CYCLE,
            FXCollections.observableArrayList(new Stop(0, Color.BLACK), new Stop(1, Color.BLUE))
    );

    @Override
    public void start(Stage stage) {
        BandGenerator bands = new BandGenerator(50, 100, 20.0);
        final Scene scene = new Scene(bands, 600, 600, SCENE_FILL);
        scene.setOnMousePressed( mouseEvent -> {
            if(mouseEvent.isSecondaryButtonDown()) {
                bands.getChildren().clear();
            } else if(mouseEvent.isPrimaryButtonDown()) {
                bands.setGeneratorCenterX(mouseEvent.getSceneX());
                bands.setGeneratorCenterY(mouseEvent.getSceneY());
                bands.createQuadBand();
            }
        });

        scene.setOnMouseDragged(mouseEvent -> {
            bands.setGeneratorCenterX(mouseEvent.getSceneX());
            bands.setGeneratorCenterY(mouseEvent.getSceneY());
        });

        scene.setOnMouseReleased(mouseEvent -> bands.stopGenerating());

        stage.setTitle("Click to create random band. Rightclick to clear.");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}