package com.dualexec.fxgraphs;

import org.jgrapht.EdgeFactory;

public class StringEdgeFactory implements EdgeFactory<String, String> {

	public String createEdge(String sourceVertex, String targetVertex) {
		return new String(sourceVertex + "-" + targetVertex);
	}

}
