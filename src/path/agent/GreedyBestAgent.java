package path.agent;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import path.agent.heuristic.SearchHeuristic;
import path.level.Level;

public class GreedyBestAgent extends PathAgent {

	public GreedyBestAgent(Level lvl) {
		super(lvl);
	}

	@Override
	public List<Point> findPath() {
		
		PriorityQueue<Node> fringe = new PriorityQueue<>();	//making priority queue fringe
		List<Node> visited = new LinkedList<>();			//making list of visited nodes
		Node current = null;								//making handle for current node
		heuristic = new SearchHeuristic(goal);				//initializing heuristic
		
		//initializing fringe with start node
		fringe.add(new Node(start, null, null));
		
		//looping while the fringe is not empty
		while(!fringe.isEmpty()) {
			
			current = fringe.poll();	//getting handle on next node and removing from fringe
			
			//checking if current node is solution
			if(current.getState().equals(goal)) {
				path = pathFromNode(current);	//returning path to goal
				return path;
			}
			
			//generating child nodes from current node
			List<Node> children = generateChildren(current);
			
			for(Node child : children) {
				
				/*
				 * If the fringe does not contain the next node, and we have not visited
				 * the current node before, then add the next node to the fringe
				 */
				
				if(!fringe.contains(child) && !visited.contains(child))
					fringe.add(child);
			}
			
			//adding to visited list
			visited.add(current);
		}
		
		return null;
	}
	
	public String toString() {
		return "Greedy Best First Agent";
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
			n.setFval(heuristic.h(north));
			children.add(n);
		}
		
		//making south point
		Point south = new Point();
		south.setLocation(parent.getState().getX(), parent.getState().getY()-10);
		
		//checking south validity
		if(level.isValid(south)) {
			
			//appending to list of children
			Node s = new Node(south, parent, Action.S);
			s.setFval(heuristic.h(south));
			children.add(s);
		}
		
		//making east point
		Point east = new Point();
		east.setLocation(parent.getState().getX()+10, parent.getState().getY());
		
		//checking east validity
		if(level.isValid(east)) {
			
			//appending to list of children
			Node e = new Node(east, parent, Action.E);
			e.setFval(heuristic.h(east));
			children.add(e);
		}
		
		//making west point ;)
		Point west = new Point();
		west.setLocation(parent.getState().getX()-10, parent.getState().getY());
		
		//checking west validity
		if(level.isValid(west)) {
			
			//appending to list of children
			Node w = new Node(west, parent, Action.W);
			w.setFval(heuristic.h(west));
			children.add(w);
		}
		
		return children;
	}

}
