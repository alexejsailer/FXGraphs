package com.dualexec.fxgraphs;

import com.dualexec.fxgraphs.editing.DiagramEditView;
import com.dualexec.fxgraphs.view.CircleArrowView;
import com.dualexec.fxgraphs.view.EdgeView;
import com.dualexec.fxgraphs.view.Place;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	private static final double MAX_SCALE = 10.0d;
	private static final double MIN_SCALE = .1d;

	double mouseAnchorX;
	double mouseAnchorY;

	double translateAnchorX;
	double translateAnchorY;

	double paperHight = 20000;
	double paperWidth = 20000;

	DoubleProperty myScale = new SimpleDoubleProperty(1.0);

	final ScrollBar sc = new ScrollBar();
	final ScrollBar sc1 = new ScrollBar();

	private DiagramEditView diagramView;

	@Override
	public void start(Stage primaryStage) {

		Group root = new Group();

		paper = new Pane();
		paper.setPrefSize(paperWidth, paperHight);
		paper.setStyle("-fx-background-color: green;");

		Scene scene = new Scene(root, 1024, 768);
		diagramView = new DiagramEditView();
		Place place0 = new Place(100, 100, 60);
		place0.addEventFilter(MouseEvent.MOUSE_PRESSED, getOnMousePressedEventHandler());
		place0.addEventFilter(MouseEvent.MOUSE_DRAGGED, getOnMouseDraggedEventHandler());
		Place place1 = new Place(250, 150, 60);
		place1.addEventFilter(MouseEvent.MOUSE_PRESSED, getOnMousePressedEventHandler());
		place1.addEventFilter(MouseEvent.MOUSE_DRAGGED, getOnMouseDraggedEventHandler());

		EdgeView connectionView = new EdgeView(place0, place1);
		diagramView.getDiagramView().getChildren().add(connectionView);

		diagramView.getDiagramView().getChildren().add(place0);
		diagramView.getDiagramView().getChildren().add(place1);

		CircleArrowView arrowView = new CircleArrowView(place0, place1);
		diagramView.getDiagramView().getChildren().add(arrowView);

		sc.setLayoutX(scene.getWidth() - sc.getWidth());
		sc.setMin(0);
		sc.setMax(10000);
		sc.setOrientation(Orientation.VERTICAL);

		sc1.setLayoutY(scene.getHeight() - sc1.getWidth() + 3);
		sc1.setMin(0);
		sc1.setMax(10000);
		sc1.setOrientation(Orientation.HORIZONTAL);

		paper.getChildren().addAll(diagramView.getDiagramView());
		root.getChildren().addAll(paper, sc, sc1);

		scene.addEventFilter(ScrollEvent.ANY, getOnMouseScrollEventHandler());

		primaryStage.setScene(scene);

		sc.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				paper.setLayoutY(-new_val.doubleValue());
			}
		});

		sc1.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				paper.setLayoutX(-new_val.doubleValue());
			}
		});

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	private EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<ScrollEvent>() {

		@Override
		public void handle(ScrollEvent event) {

			System.out.println("Scroll DelatY " + event.getDeltaY());

			double delta = 1.2;
			double scale = getMyScale().doubleValue();
			double oldScale = scale;
			if (event.getDeltaY() < 0) {
				scale /= delta;
			} else {
				scale *= delta;
			}

			scale = clamp(scale, MIN_SCALE, MAX_SCALE);

			double f = (scale / oldScale) - 1;

			getMyScale().set(scale);

			System.out.println("DV-B in Parent Width " + diagramView.getDiagramView().getBoundsInParent().getWidth());
			System.out.println("DV-B in Parent Height " + diagramView.getDiagramView().getBoundsInParent().getHeight());
			System.out.println("DV-B in Parent MinX " + diagramView.getDiagramView().getBoundsInParent().getMinX());
			System.out.println("DV-B in Parent MinY " + diagramView.getDiagramView().getBoundsInParent().getMinY());

			System.out.println("DV-B in Local Width " + diagramView.getDiagramView().getBoundsInLocal().getWidth());
			System.out.println("DV-B in Local Height " + diagramView.getDiagramView().getBoundsInLocal().getHeight());
			System.out.println("DV-B in Local MinX " + diagramView.getDiagramView().getBoundsInLocal().getMinX());
			System.out.println("DV-B in Local MinY " + diagramView.getDiagramView().getBoundsInLocal().getMinY());

			System.out.println("SceneX " + event.getSceneX());
			System.out.println("SceneY " + event.getSceneY());

			double dx = (event.getSceneX()
					- (paper.getBoundsInParent().getWidth() / 2 + paper.getBoundsInParent().getMinX()));

			System.out.println(diagramView.getDiagramView()
					.localToScene(diagramView.getDiagramView().getBoundsInLocal()).getMinY());

			double dy = (event.getSceneY()
					- (paper.getBoundsInParent().getHeight() / 2 + paper.getBoundsInParent().getMinY()));

			paper.setScaleX(scale);
			paper.setScaleY(scale);

			paper.setTranslateX(paper.getTranslateX() - f * dx);
			paper.setTranslateY(paper.getTranslateY() - f * (dy));

			event.consume();
		}

	};

	public static double clamp(double value, double min, double max) {

		if (Double.compare(value, min) < 0)
			return min;

		if (Double.compare(value, max) > 0)
			return max;

		return value;
	}

	public EventHandler<ScrollEvent> getOnMouseScrollEventHandler() {
		return onScrollEventHandler;
	}

	public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
		return onMousePressedEventHandler;
	}

	public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
		return onMouseDraggedEventHandler;
	}

	private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {

			if (!event.isPrimaryButtonDown())
				return;

			mouseAnchorX = event.getSceneX();
			mouseAnchorY = event.getSceneY();

			Node node = (Node) event.getSource();

			translateAnchorX = node.getTranslateX();
			translateAnchorY = node.getTranslateY();

			System.out.println("Node translateX " + node.getTranslateX());
			System.out.println("Node translateY " + node.getTranslateY());

		}

	};

	private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			if (!event.isPrimaryButtonDown())
				return;

			double scale = getMyScale().doubleValue();

			Node node = (Node) event.getSource();

			node.setTranslateX(translateAnchorX + ((event.getSceneX() - mouseAnchorX) / scale));
			node.setTranslateY(translateAnchorY + ((event.getSceneY() - mouseAnchorY) / scale));

			event.consume();

		}
	};

	private Pane paper;

	public DoubleProperty getMyScale() {
		return myScale;
	}

	public void setMyScale(DoubleProperty myScale) {
		this.myScale = myScale;
	}

}
