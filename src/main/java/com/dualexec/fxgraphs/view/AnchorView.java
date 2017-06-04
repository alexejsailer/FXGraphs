package com.dualexec.fxgraphs.view;

import javafx.scene.Node;

public class AnchorView {

	private Node anchor;

	public AnchorView(Node anchor) {

		this.anchor = anchor;

	}

	public Node getAnchor() {
		return anchor;
	}

	public void setAnchor(Node anchor) {
		this.anchor = anchor;
	}

}
