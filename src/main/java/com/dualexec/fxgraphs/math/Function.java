package com.dualexec.fxgraphs.math;

import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Point2D;

public abstract class Function {

	private List<Point2D> points = new LinkedList<Point2D>();

	public abstract double apply(double point);

	public List<Point2D> getPoints() {
		return points;
	}

}
