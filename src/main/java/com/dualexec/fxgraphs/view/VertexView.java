package com.dualexec.fxgraphs.view;

import java.util.LinkedList;
import java.util.List;

import com.dualexec.fxgraphs.view.definition.Anchor;
import com.dualexec.fxgraphs.view.definition.Vertex;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public abstract class VertexView extends Group implements Vertex {

	private List<Anchor> anchors = new LinkedList<>();

	public List<Anchor> getAnchors() {
		return anchors;
	}

	public void setAnchors(List<Anchor> anchors) {
		this.anchors = anchors;
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return null;
	}


}
