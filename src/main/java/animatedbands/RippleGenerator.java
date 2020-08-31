package animatedbands;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.util.Duration;

/**
 * Generates ripples on the screen every 0.5 seconds or whenever the
 * createRipple method is called. Ripples grow and fade out over 3 seconds
 */
public class RippleGenerator extends Group {

    private double generatorCenterX = 100.0;
    private double generatorCenterY = 100.0;

    private Timeline generate = new Timeline(
       new KeyFrame(Duration.seconds(0.5), event -> createRipple())
    );

    public RippleGenerator() {
        generate.setCycleCount(Timeline.INDEFINITE);
    }

    public void createRipple() {
        final Ripple ripple = new Ripple(generatorCenterX, generatorCenterY);
        getChildren().add(ripple);
        ripple.animation.play();

        Timeline remover = new Timeline(
            new KeyFrame(Duration.seconds(3), actionEvent -> {
                    getChildren().remove(ripple);
                    ripple.animation.stop();
                }
            )
        );
        remover.play();
    }

    public void startGenerating() {
        generate.play();
    }

    public void stopGenerating() {
        generate.stop();
    }

    public void setGeneratorCenterX(double generatorCenterX) {
        this.generatorCenterX = generatorCenterX;
    }

    public void setGeneratorCenterY(double generatorCenterY) {
        this.generatorCenterY = generatorCenterY;
    }
}