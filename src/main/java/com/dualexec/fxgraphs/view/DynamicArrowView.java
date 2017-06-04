package com.dualexec.fxgraphs.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class DynamicArrowView extends Group {

	private DoubleProperty sourceX = new SimpleDoubleProperty();
	private DoubleProperty sourceY = new SimpleDoubleProperty();
	private DoubleProperty targetX = new SimpleDoubleProperty();
	private DoubleProperty targetY = new SimpleDoubleProperty();

	private Translate trans1 = new Translate();
	private Translate trans2 = new Translate();
	private Rotate rot = new Rotate();

	public DynamicArrowView(Shape source, Shape target) {
		Polygon polygon = new Polygon(0, 0, 30, 0, 15, 15);
		transformArrow(source, target, polygon);

		source.translateXProperty().addListener(getChangeListener(source, target, polygon));
		target.translateXProperty().addListener(getChangeListener(source, target, polygon));
		source.translateYProperty().addListener(getChangeListener(source, target, polygon));
		target.translateYProperty().addListener(getChangeListener(source, target, polygon));

		polygon.getTransforms().addAll(trans1, rot, trans2);
		trans2.setX(-15);
		trans2.setY(-15);
		polygon.setFill(Color.GRAY);

		getChildren().add(polygon);
	}

	private ChangeListener<Number> getChangeListener(final Shape source, final Shape target, final Polygon polygon) {
		return new ChangeListener<Number>() {

			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				transformArrow(source, target, polygon);
			}
		};
	}

	private void transformArrow(Shape source, Shape target, Polygon polygon) {
		updateNodePosition(source, target);
		double disty = targetY.doubleValue() - sourceY.doubleValue();
		double distx = targetX.doubleValue() - sourceX.doubleValue();
		double teta = Math.atan2(disty, distx);
		rot.setAngle(Math.toDegrees(teta) - 90);
		Point2D a = new Point2D(sourceX.doubleValue(), sourceY.doubleValue());
		Point2D b = new Point2D(targetX.doubleValue(), targetY.doubleValue());
		Point2D i = findIntersectionPoint(a, b, target);
		i = i.add(3 * Math.cos(teta), 3 * Math.sin(teta));
		trans1.setX(i.getX());
		trans1.setY(i.getY());
	}

	private void updateNodePosition(Shape source, Shape target) {
		sourceX.set(source.getBoundsInParent().getMinX()
				+ ((source.getBoundsInParent().getMaxX() - source.getBoundsInParent().getMinX()) / 2));
		sourceY.set(source.getBoundsInParent().getMinY()
				+ ((source.getBoundsInParent().getMaxY() - source.getBoundsInParent().getMinY()) / 2));
		targetX.set(target.getBoundsInParent().getMinX()
				+ ((target.getBoundsInParent().getMaxX() - target.getBoundsInParent().getMinX()) / 2));
		targetY.set(target.getBoundsInParent().getMinY()
				+ ((target.getBoundsInParent().getMaxY() - target.getBoundsInParent().getMinY()) / 2));
		System.out.println(sourceX.doubleValue());
		System.out.println(sourceY.doubleValue());
		System.out.println(targetX.doubleValue());
		System.out.println(targetY.doubleValue());
	}

	private Point2D findIntersectionPoint(Point2D a, Point2D b, Shape target) {
		Point2D middle = new Point2D((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
		System.out.println(middle.getX() + " " + middle.getY());
		if (a.distance(b) < 1) {
			System.out.println("A: " + a.getX() + " " + a.getY());
			System.out.println("B: " + b.getX() + " " + b.getY());
			System.out.println("Middle: " + middle.getX() + " " + middle.getY());
			return middle;
		}
		Point2D local = target.parentToLocal(middle);
		System.out.println("contains2222 " + local.getX() + " " + local.getY());
		if (target.contains(local)) {
			System.out.println("contains " + local.getX() + " " + local.getY());
			return findIntersectionPoint(a, middle, target);
		} else {
			return findIntersectionPoint(middle, b, target);
		}
	}

}
