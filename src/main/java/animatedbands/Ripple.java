package animatedbands;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author phillsm1
 */
    public class Ripple extends Circle {

        public Ripple(double centerX, double centerY) {
            super(centerX, centerY, 0, null);
            setStroke(Color.rgb(200, 200, 255));
        }
        Timeline animation = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(radiusProperty(), 0)),
            new KeyFrame(Duration.seconds(1), new KeyValue(opacityProperty(), 1)),
            new KeyFrame(Duration.seconds(3), new KeyValue(radiusProperty(), 100)),
            new KeyFrame(Duration.seconds(3), new KeyValue(opacityProperty(), 0))
        );
    }