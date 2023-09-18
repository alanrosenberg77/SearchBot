package path.agent;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import path.agent.heuristic.SearchHeuristic;
import path.level.Level;

public class AStarAgent extends PathAgent {
	
	SearchHeuristic h;

	public AStarAgent(Level lvl) {
		super(lvl);
		h = new SearchHeuristic(goal);
	}

	@Override
	public List<Point> findPath() {
		// TODO implement findPath() for A* Agent
		return null;
	}

	@Override
	protected List<Node> generateChildren(Node parent) {
		
		List<Node> children = new LinkedList<>();	//making list for child nodes
		
		//making north point
		Point north = new Point();
		north.setLocation(parent.getState().getX(), parent.getState().getY()+10);
		
		//checking north validity
		if(level.isValid(north)) {
			
			//appending to list of children
			Node n = new Node(north, parent, Action.N);
			n.setFval(heuristic.h(north) + parent.getCost());
			children.add(n);
		}
		
		//making south point
		Point south = new Point();
		south.setLocation(parent.getState().getX(), parent.getState().getY()-10);
		
		//checking south validity
		if(level.isValid(south)) {
			
			//appending to list of children
			Node s = new Node(south, parent, Action.S);
			s.setFval(heuristic.h(south) + parent.getCost());
			children.add(s);
		}
		
		//making east point
		Point east = new Point();
		east.setLocation(parent.getState().getX()+10, parent.getState().getY());
		
		//checking east validity
		if(level.isValid(east)) {
			
			//appending to list of children
			Node e = new Node(east, parent, Action.E);
			e.setFval(heuristic.h(east) + parent.getCost());
			children.add(e);
		}
		
		//making west point ;)
		Point west = new Point();
		west.setLocation(parent.getState().getX()-10, parent.getState().getY());
		
		//checking west validity
		if(level.isValid(west)) {
			
			//appending to list of children
			Node w = new Node(west, parent, Action.W);
			w.setFval(heuristic.h(west) + parent.getCost());
			children.add(w);
		}
		
		return children;
	}

}
