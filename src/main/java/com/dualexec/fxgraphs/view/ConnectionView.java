package com.dualexec.fxgraphs.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.shape.Line;

public class ConnectionView extends Line {

	public ConnectionView(Node source, Node target) {
		setStartX(100);
		setStartY(100);
		setEndX(250);
		setEndY(150);
		SimpleDoubleProperty prop = new SimpleDoubleProperty(100);
		SimpleDoubleProperty prop1 = new SimpleDoubleProperty(250);
		SimpleDoubleProperty prop2 = new SimpleDoubleProperty(150);
		startXProperty().bind(source.layoutXProperty().add(prop));
		startYProperty().bind(source.layoutYProperty().add(prop));
		endXProperty().bind(target.layoutXProperty().add(prop1));
		endYProperty().bind(target.layoutYProperty().add(prop2));
		setStrokeWidth(2);
	}

}
