package com.dualexec.fxgraphs.view;

import javafx.scene.Node;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class Place extends VertexView {

	private Node anchor;

	public Place(double centerX, double centerY, double radius) {
//		getAnchors().add(createAnchor(centerX, centerY, radius));
		getChildren().add(createBaseBackroundCircle(centerX, centerY, radius));
		getChildren().add(createOuterCircle(centerX, centerY, radius));
		getChildren().add(getInnerFillCircle(centerX, centerY, radius));
		getChildren().add(createCenterDot(centerX, centerY, radius));
	}

	private Circle getInnerFillCircle(double centerX, double centerY, double radius) {
		Circle innerFillCircle = new Circle(radius * 0.9, Color.WHITE);
		innerFillCircle.setLayoutX(centerX);
		innerFillCircle.setLayoutY(centerY);
		return innerFillCircle;
	}

	private Circle createAnchor(double centerX, double centerY, double radius) {
		Circle anchor = new Circle(radius * 0.2, Color.BLACK);
		anchor.setLayoutX(centerX);
		anchor.setLayoutY(centerY);
		return anchor;
	}

	private Circle createCenterDot(double centerX, double centerY, double radius) {
		Circle centerDot = new Circle(radius * 0.2, Color.BLACK);
		centerDot.setEffect(new Lighting());
		centerDot.setOpacity(0.8);
		centerDot.setLayoutX(centerX);
		centerDot.setLayoutY(centerY);
		return centerDot;
	}

	private Circle createBaseBackroundCircle(double centerX, double centerY, double radius) {
		Circle outerCircle = new Circle(radius, Color.WHITE);
		outerCircle.setLayoutX(centerX);
		outerCircle.setLayoutY(centerY);
		return outerCircle;
	}

	private Circle createOuterCircle(double centerX, double centerY, double radius) {
		Circle outerCircle = new Circle(radius, Color.GRAY);
		outerCircle.setEffect(new Lighting());
		outerCircle.setStroke(Color.BLACK);
		outerCircle.setStrokeWidth(2);
		outerCircle.setStrokeType(StrokeType.OUTSIDE);
		outerCircle.setOpacity(0.4);
		outerCircle.setLayoutX(centerX);
		outerCircle.setLayoutY(centerY);
		return outerCircle;
	}

}
