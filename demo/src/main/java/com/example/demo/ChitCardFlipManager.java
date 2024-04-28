package com.example.demo;

import javafx.animation.RotateTransition;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class ChitCardFlipManager{
    private static ChitCardFlipManager chitCardFlipManager;
    private static Circle[] coveredChitCardShapes; // used to ensure that they do not listen for clicks
    private boolean ANIMATION_IN_PROGRESS = false;
    private final ArrayList<Circle> uncoveredChitCardsByPlayer = new ArrayList<>(); // specific uncovered chit cards in a player's turn

    private ChitCardFlipManager(){}
    public static ChitCardFlipManager getInstance(){
        if (chitCardFlipManager == null){
            chitCardFlipManager = new ChitCardFlipManager();
        } return chitCardFlipManager;
    }

    public static void setCoveredChitCardShapes(Circle[] coveredChitCardShapes) {
        ChitCardFlipManager.coveredChitCardShapes = coveredChitCardShapes;
    }

    public void setANIMATION_IN_PROGRESS(boolean COVERING_ANIMATION_IN_PROGRESS) {
        this.ANIMATION_IN_PROGRESS = COVERING_ANIMATION_IN_PROGRESS;
    }
    public boolean getANIMATION_IN_PROGRESS() {
        return ANIMATION_IN_PROGRESS;
    }

    public Circle[] getCoveredChitCardShapes() {
        return coveredChitCardShapes;
    }


    public void handleUncover(Circle chitCardChosen, ChitCard cardChosen){
        cardChosen.setCovered(false);
        getCoveredChitCardShapes()[cardChosen.getIndex()] = null; // this is so that it won't be listening for clicks on next turn
        uncoveredChitCardsByPlayer.add(cardChosen.getUncoveredForm());

        // do animation
        Circle hiddenCircle = cardChosen.getUncoveredForm();
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), chitCardChosen);
        rotateTransition.setAxis(Rotate.Y_AXIS); // Rotate around the Y axis
        rotateTransition.setByAngle(360); // Rotate 180 degrees
        setANIMATION_IN_PROGRESS(true);
        rotateTransition.setOnFinished(event -> {
            chitCardChosen.setVisible(false);
            hiddenCircle.setVisible(true);
            chitCardChosen.getTransforms().clear(); // Clear the transformation
            setANIMATION_IN_PROGRESS(false);

        });
        rotateTransition.play();
    }

    public void handleTurnEnd(){
        // do flip back animation
        for (Circle uncoveredChitCard : uncoveredChitCardsByPlayer) {
            ChitCard chitCard = ChitCardFactory.getViewControllerMapping().get(uncoveredChitCard);
            // animate
            Circle hiddenCircle = chitCard.getCoveredForm();
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), uncoveredChitCard);
            rotateTransition.setAxis(Rotate.Y_AXIS); // Rotate around the Y axis
            rotateTransition.setByAngle(360);
            rotateTransition.setOnFinished(event -> {
                uncoveredChitCard.setVisible(false);
                hiddenCircle.setVisible(true);
                uncoveredChitCard.getTransforms().clear(); // Clear the transformation
            });
            rotateTransition.play();

            chitCard.setCovered(true);
        }
        uncoveredChitCardsByPlayer.clear(); // all the chit cards that were uncovered have been covered, so they need to start listening for clicks again

    }

    public void resetOnClickListener(HashMap<Circle, ChitCard> mapping){
        // all the chit cards that were uncovered have been covered, so they need to start listening for clicks again
        for (Circle uncoveredChitCard : uncoveredChitCardsByPlayer) {
            ChitCard card = mapping.get(uncoveredChitCard);
            getCoveredChitCardShapes()[card.getIndex()] = card.getCoveredForm();
        }
    }
}