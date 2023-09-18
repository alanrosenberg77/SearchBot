package path.agent.heuristic;

import java.awt.Point;

public class SearchHeuristic extends Heuristic {

	public SearchHeuristic() {
		
	}

	public SearchHeuristic(Point goal) {
		super(goal);
	}

	@Override
	public double h(Point nodeState) {
		return nodeState.distance(goalState);
	}

}
