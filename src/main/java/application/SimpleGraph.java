package application;

import org.jgrapht.DirectedGraph;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.AbstractBaseGraph;

public class SimpleGraph extends AbstractBaseGraph<String, String> implements DirectedGraph<String, String> {

	protected SimpleGraph(EdgeFactory<String, String> ef, boolean allowMultipleEdges, boolean allowLoops) {
		super(ef, allowMultipleEdges, allowLoops);
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


}
