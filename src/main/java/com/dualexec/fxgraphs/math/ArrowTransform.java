package com.dualexec.fxgraphs.math;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;

public class ArrowTransform {

	private DoubleProperty arrow0X = new SimpleDoubleProperty();;
	private DoubleProperty arrow1X = new SimpleDoubleProperty();;
	private DoubleProperty arrow2X = new SimpleDoubleProperty();;

	private DoubleProperty arrow0Y = new SimpleDoubleProperty();;
	private DoubleProperty arrow1Y = new SimpleDoubleProperty();;
	private DoubleProperty arrow2Y = new SimpleDoubleProperty();;

	private LineFunction lineFunction;
	private LineFunction lineFunction1;
	private LineFunction lineFunction2;

	public ArrowTransform(DoubleProperty sourceX, DoubleProperty sourceY, DoubleProperty targetX,
			DoubleProperty targetY) {
		updateArrow(sourceX, sourceY, targetX, targetY);
	}

	public void updateArrow(DoubleProperty sourceX, DoubleProperty sourceY, DoubleProperty targetX,
			DoubleProperty targetY) {

		Point2D p0 = new Point2D(sourceX.get(), sourceY.get());
		Point2D p1 = new Point2D(targetX.get(), targetY.get());
		lineFunction = new LineFunction(p0, p1);
		double degree = Math.atan(lineFunction.getM());

		double bx = Math.sin(degree) * 15;
		double ay = Math.cos(degree) * 15;

		Point2D p01 = new Point2D(sourceX.get() + bx, sourceY.get() - ay);
		Point2D p02 = new Point2D(sourceX.get() - bx, sourceY.get() + ay);

		Point2D p11 = new Point2D(targetX.get() + bx, targetY.get() - ay);
		Point2D p12 = new Point2D(targetX.get() - bx, targetY.get() + ay);

		lineFunction1 = new LineFunction(p01, p11);
		lineFunction2 = new LineFunction(p02, p12);

		double a = Math.cos(degree) * 60;
		double a1 = Math.cos(degree) * 80;

		if (lineFunction.getM() >= 0 && (targetX.get() - sourceX.get()) > 0
				|| (lineFunction.getM() < 0 && (targetX.get() - sourceX.get()) > 0)) {
			arrow0X.set(p0.getX() + a);
			arrow1X.set(p01.getX() + a1);
			arrow2X.set(p02.getX() + a1);
		} else {
			arrow0X.set(p0.getX() - a);
			arrow1X.set(p01.getX() - a1);
			arrow2X.set(p02.getX() - a1);
		}
		arrow0Y.set(lineFunction.apply(arrow0X.get()));
		arrow1Y.set(lineFunction1.apply(arrow1X.get()));
		arrow2Y.set(lineFunction2.apply(arrow2X.get()));
	}

	public DoubleProperty getArrow0X() {
		return arrow0X;
	}

	public DoubleProperty getArrow1X() {
		return arrow1X;
	}

	public DoubleProperty getArrow2X() {
		return arrow2X;
	}

	public DoubleProperty getArrow0Y() {
		return arrow0Y;
	}

	public DoubleProperty getArrow1Y() {
		return arrow1Y;
	}

	public DoubleProperty getArrow2Y() {
		return arrow2Y;
	}

}
