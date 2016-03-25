package com.dualexec.fxgraphs.math;

import javafx.geometry.Point2D;

public class LineFunction extends Function {

	private double m;
	private double b;

	public LineFunction(double m, double b) {
		this.m = m;
		this.b = b;
	}

	public LineFunction(Point2D p0, Point2D p1) {
		m = (p1.getY() - p0.getY()) / (p1.getX() - p0.getX());
		b = p0.getY() - (m * p0.getX());
	}

	public void update(Point2D p0, Point2D p1) {
		m = (p1.getY() - p0.getY()) / (p1.getX() - p0.getX());
		b = p0.getY() - (m * p0.getX());
	}

	@Override
	public double apply(double x) {
		return m * x + b;
	}

	public LineFunction getOrthogonalFunction(Point2D p1) {

		double mo = 1;
		double bo = 0;

		if (m != 0) {
			mo = -1 / m;
		}

		if (mo != 1) {
			bo = p1.getY() - (p1.getX() * mo);
		}

		LineFunction f = new LineFunction(mo, bo) {

			@Override
			public double apply(double x) {
				return m * x + b;
			}
		};

		return f;
	}

	public LineFunction getParallelFunction(final double y) {

		LineFunction f = new LineFunction(m, y) {
			@Override
			public double apply(double x) {
				return m * x + y;
			}
		};
		return f;
	}

	public Point2D getIntersection(LineFunction lf) {
		double x, y;
		x = (getB() - lf.getB()) / (lf.getM() - getM());
		y = lf.getM() * x + lf.getB();
		return new Point2D(x, y);
	}

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

}
