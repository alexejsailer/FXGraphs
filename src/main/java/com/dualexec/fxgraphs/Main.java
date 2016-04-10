package com.dualexec.fxgraphs;


import com.dualexec.fxgraphs.editing.DiagramEditView;
import com.dualexec.fxgraphs.view.ArrowView;
import com.dualexec.fxgraphs.view.EdgeView;
import com.dualexec.fxgraphs.view.Place;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	static double x;
	static double y;

	@Override
	public void start(Stage primaryStage) {
		DiagramEditView editPart = new DiagramEditView();
		Place place0 = new Place(100, 100, 60);
		Place place1 = new Place(250, 150, 60);
		EdgeView connectionView = new EdgeView(place0, place1);

		editPart.getDiagramView().getChildren().add(connectionView);

		editPart.getDiagramView().getChildren().add(place0);
		makeDraggable(place0);

		editPart.getDiagramView().getChildren().add(place1);
		makeDraggable(place1);

		ArrowView arrowView = new ArrowView(place0, place1);
		editPart.getDiagramView().getChildren().add(arrowView);

		Pane root = new Pane(editPart.getDiagramView());
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void makeDraggable(final Group group) {

		group.setOnMousePressed(onMousePressedEventHandler);
		group.setOnMouseDragged(onMouseDraggedEventHandler);
		group.setOnMouseReleased(onMouseReleasedEventHandler);

	}


	public static void makeDraggable(final Node node) {

		node.setOnMousePressed(onMousePressedEventHandler);
		node.setOnMouseDragged(onMouseDraggedEventHandler);
		node.setOnMouseReleased(onMouseReleasedEventHandler);

	}

	static EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {
			Node node = (Node) event.getSource();
			x = node.getBoundsInParent().getMinX() - event.getScreenX();
			y = node.getBoundsInParent().getMinY() - event.getScreenY();
		}
	};

	static EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {

			Node node = (Node) event.getSource();

			double offsetX = event.getScreenX() + x;
			double offsetY = event.getScreenY() + y;

			double scale = 1f;

			offsetX /= scale;
			offsetY /= scale;

			node.relocate(offsetX, offsetY);
		}
	};

	static EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {

		}
	};


}