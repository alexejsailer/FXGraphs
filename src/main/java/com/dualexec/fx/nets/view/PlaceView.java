package com.dualexec.fx.nets.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class PlaceView extends Group {

	private Node anchor;

	public PlaceView(double centerX, double centerY, double radius) {


		Circle circle0 = new Circle(radius, Color.WHITE);
		circle0.setLayoutX(centerX);
		circle0.setLayoutY(centerY);

		Circle circle = new Circle(radius, Color.GRAY);
		circle.setEffect(new Lighting());
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(2);
		circle.setStrokeType(StrokeType.OUTSIDE);
		circle.setOpacity(0.4);
		circle.setLayoutX(centerX);
		circle.setLayoutY(centerY);

		Circle circle1 = new Circle(radius * 0.9, Color.WHITE);
		circle1.setLayoutX(centerX);
		circle1.setLayoutY(centerY);

		Circle circle2 = new Circle(radius * 0.2, Color.BLACK);
		circle2.setEffect(new Lighting());
		circle2.setOpacity(0.8);
		circle2.setLayoutX(centerX);
		circle2.setLayoutY(centerY);

		Circle anchor = new Circle(radius * 0.2, Color.BLACK);
		anchor.setLayoutX(centerX);
		anchor.setLayoutY(centerY);

		setAnchor(anchor);
		getChildren().add(circle0);
		getChildren().add(circle);
		getChildren().add(circle1);
		getChildren().add(circle2);

	}

	public Node getAnchor() {
		return anchor;
	}

	public void setAnchor(Node anchor) {
		this.anchor = anchor;
	}

}
