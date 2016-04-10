package com.dualexec.fxgraphs.view;

import com.dualexec.fxgraphs.view.definition.Edge;
import com.dualexec.fxgraphs.view.definition.Vertex;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Line;

public class EdgeView extends Line implements Edge {

	private DoubleProperty sourceX = new SimpleDoubleProperty();
	private DoubleProperty sourceY = new SimpleDoubleProperty();
	private DoubleProperty targetX = new SimpleDoubleProperty();
	private DoubleProperty targetY = new SimpleDoubleProperty();

	private Vertex source;
	private Vertex target;

	public EdgeView(VertexView source, VertexView target) {
		this.source = source;
		this.target = target;
		updateNodePosition(source, target);
//		setStartX(sourceX.get());
//		setStartY(sourceY.get());
//		setEndX(targetX.get());
//		setEndY(targetY.get());
		startXProperty().bind(source.layoutXProperty().add(sourceX));
		startYProperty().bind(source.layoutYProperty().add(sourceY));
		endXProperty().bind(target.layoutXProperty().add(targetX));
		endYProperty().bind(target.layoutYProperty().add(targetY));
		setStrokeWidth(2);
	}

	private void updateNodePosition(VertexView source, VertexView target) {
		sourceX.set(source.getBoundsInParent().getMinX()
				+ ((source.getBoundsInParent().getMaxX() - source.getBoundsInParent().getMinX()) / 2));
		sourceY.set(source.getBoundsInParent().getMinY()
				+ ((source.getBoundsInParent().getMaxY() - source.getBoundsInParent().getMinY()) / 2));
		targetX.set(target.getBoundsInParent().getMinX()
				+ ((target.getBoundsInParent().getMaxX() - target.getBoundsInParent().getMinX()) / 2));
		targetY.set(target.getBoundsInParent().getMinY()
				+ ((target.getBoundsInParent().getMaxY() - target.getBoundsInParent().getMinY()) / 2));
	}

	@Override
	public Vertex getSource() {
		return source;
	}

	@Override
	public Vertex getTarget() {
		return target;
	}


}
