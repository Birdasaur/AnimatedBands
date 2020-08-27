package animatedbands;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.util.Duration;

/**
 *
 * @author phillsm1
 */
/**
 * Generates contour line bands on the screen whenever the
 * createQuadBand method is called. Bands grow and fade out over 3 seconds
 */
public class BandGenerator extends Group {

    private int polygonPoints;
    private double initialRadius;
    private double edgeVariation;
    private double generatorCenterX = 100.0;
    private double generatorCenterY = 100.0;
    
    private Timeline generate = new Timeline(
        new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createQuadBand();
            }
        })
    );

    public BandGenerator(int points, double initialRadius, double edgeVariation) {
        this.polygonPoints = points;
        this.initialRadius = initialRadius;
        this.edgeVariation = edgeVariation;
        generate.setCycleCount(Timeline.INDEFINITE);
    }

    public void createQuadBand() {
        //create a random polygon around the center point
        Random random = new Random();
        double [] doubles = new double[polygonPoints*2];
        double angleRadiansSlice  = 6.283185 / polygonPoints; 
        double randomLength, angleRadians = 0.0;

        //get a randomly perturbed length based on a set radius
        randomLength = initialRadius + (edgeVariation * random.nextGaussian()); 
        //setup first segment with a radian angle of 0.0
        doubles[0] = generatorCenterX + (Math.cos(angleRadians) * randomLength);
        doubles[1] = generatorCenterY + (Math.sin(angleRadians) * randomLength);
        //@DEBUG SMP create a circle showing the first point
        //getChildren().add(new Circle(doubles[0], doubles[1], 5.0, 
        //    Color.GREEN.deriveColor(1, 1, 1, 0.7)));
        //For each point, compute the coordinats    
        for(int i=1;i<polygonPoints;i++) {
            //jog the angle forward
            angleRadians += angleRadiansSlice;
            //get a new random length
            randomLength = initialRadius + (edgeVariation * random.nextGaussian()); 
            //create new end point
            doubles[2*i] = generatorCenterX + (Math.cos(angleRadians) * randomLength);
            doubles[2*i+1] = generatorCenterY + (Math.sin(angleRadians) * randomLength);
            //@DEBUG SMP Test circle to visualize points
            //getChildren().add(new Circle(doubles[2*i], doubles[2*i+1], 3.0, 
            //    Color.ALICEBLUE.deriveColor(1, 1, 1, 0.7)));
        }

        final Band band = new Band(generatorCenterX, generatorCenterY, doubles);
        getChildren().add(band);
        band.animation.play();

        Timeline remover = new Timeline(
            new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    getChildren().remove(band);
                    band.animation.stop();
                }
            })
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