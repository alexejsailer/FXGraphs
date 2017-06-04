package com.dualexec.fxgraphs.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Rectangle;

public class Transition extends VertexView {

	public Transition(double x, double y, double height, double width) {
		Rectangle r = new Rectangle();
		r.setX(x);
		r.setY(y);
		r.setWidth(width);
		r.setHeight(height);
		// r.setArcWidth(20);
		// r.setArcHeight(20);

		// this.rotateProperty().set(90);

		this.layoutXProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println(Transition.this.layoutXProperty().doubleValue());
			}

		});

		getChildren().add(r);
	}

}
