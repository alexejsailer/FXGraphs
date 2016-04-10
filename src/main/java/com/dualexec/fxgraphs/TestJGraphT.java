package com.dualexec.fxgraphs;

import org.jgrapht.Graph;

public class TestJGraphT {


	public static void main(String[] args) {

		Graph<String, String> test = new SimpleGraph(new StringEdgeFactory(), false, false);
		test.addVertex("V1");
		test.addVertex("V2");
		String edge = test.addEdge("V1", "V2");
		System.out.println(edge);
		
	}

}
