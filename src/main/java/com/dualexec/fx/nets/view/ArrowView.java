package com.dualexec.fx.nets.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;

public class ArrowView extends Group {

	private DoubleProperty sourceX = new SimpleDoubleProperty();
	private DoubleProperty sourceY = new SimpleDoubleProperty();
	private DoubleProperty targetX = new SimpleDoubleProperty();
	private DoubleProperty targetY = new SimpleDoubleProperty();
	private ArrowTransform transform;

	public ArrowView(Node source, Node target) {

		Polygon polygon = new Polygon();
		transformArrow(source, target, polygon);

		source.layoutXProperty().addListener(getChangeListener(source, target, polygon));
		target.layoutXProperty().addListener(getChangeListener(source, target, polygon));
		source.layoutYProperty().addListener(getChangeListener(source, target, polygon));
		target.layoutYProperty().addListener(getChangeListener(source, target, polygon));

		getChildren().add(polygon);
	}

	private ChangeListener<Number> getChangeListener(final Node source, final Node target, final Polygon polygon) {
		return new ChangeListener<Number>() {

			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				transformArrow(source, target, polygon);
			}
		};
	}

	private void transformArrow(Node source, Node target, Polygon polygon) {
		updateNodePosition(source, target);
		transform = new ArrowTransform(sourceX, sourceY, targetX, targetY);
		if (polygon.getPoints().size() == 0) {
			polygon.getPoints()
					.addAll(new Double[] { transform.getArrow0X().get(), transform.getArrow0Y().get(),
							transform.getArrow1X().get(), transform.getArrow1Y().get(), transform.getArrow2X().get(),
							transform.getArrow2Y().get() });
		}
		polygon.getPoints().set(0, transform.getArrow0X().get());
		polygon.getPoints().set(1, transform.getArrow0Y().get());
		polygon.getPoints().set(2, transform.getArrow1X().get());
		polygon.getPoints().set(3, transform.getArrow1Y().get());
		polygon.getPoints().set(4, transform.getArrow2X().get());
		polygon.getPoints().set(5, transform.getArrow2Y().get());
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
