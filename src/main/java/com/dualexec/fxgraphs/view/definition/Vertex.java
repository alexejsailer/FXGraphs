package com.dualexec.fxgraphs.view.definition;

import java.util.List;

import javafx.geometry.Point2D;

public interface Vertex {

	List<Anchor> getAnchors();

	Point2D getPosition();

}
