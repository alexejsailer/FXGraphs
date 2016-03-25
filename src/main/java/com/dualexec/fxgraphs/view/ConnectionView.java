package com.dualexec.fxgraphs.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.shape.Line;

public class ConnectionView extends Line {

	private DoubleProperty sourceX = new SimpleDoubleProperty();
	private DoubleProperty sourceY = new SimpleDoubleProperty();
	private DoubleProperty targetX = new SimpleDoubleProperty();
	private DoubleProperty targetY = new SimpleDoubleProperty();

	public ConnectionView(Node source, Node target) {
		updateNodePosition(source, target);
		setStartX(sourceX.get());
		setStartY(sourceY.get());
		setEndX(targetX.get());
		setEndY(targetY.get());
		startXProperty().bind(source.layoutXProperty().add(sourceX));
		startYProperty().bind(source.layoutYProperty().add(sourceY));
		endXProperty().bind(target.layoutXProperty().add(targetX));
		endYProperty().bind(target.layoutYProperty().add(targetY));
		setStrokeWidth(2);
	}


	private void updateNodePosition(Node source, Node target) {
		sourceX.set(source.getBoundsInParent().getMinX()
				+ ((source.getBoundsInParent().getMaxX() - source.getBoundsInParent().getMinX()) / 2));
		sourceY.set(source.getBoundsInParent().getMinY()
				+ ((source.getBoundsInParent().getMaxY() - source.getBoundsInParent().getMinY()) / 2));
		targetX.set(target.getBoundsInParent().getMinX()
				+ ((target.getBoundsInParent().getMaxX() - target.getBoundsInParent().getMinX()) / 2));
		targetY.set(target.getBoundsInParent().getMinY()
				+ ((target.getBoundsInParent().getMaxY() - target.getBoundsInParent().getMinY()) / 2));
	}

}
