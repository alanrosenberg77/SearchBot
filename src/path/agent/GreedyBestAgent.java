package path.agent;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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
		
		//initializing fringe with start node
		fringe.add(new Node(start, null, null));
		
		//looping while the fringe is not empty
		while(!fringe.isEmpty()) {
			
			current = fringe.poll();	//getting handle on next node and removing from fringe
			
			if(current.getState().equals(goal)) {
				
			}
		}
		
		// TODO implement findPath() for Greedy Best Agents
		return null;
	}
	
	/**
	 * Helper method that determines the path to a specified node
	 * 
	 * @param n
	 * @return
	 */
	private List<Point> pathFrom(Node n) {
		
		List<Point> path = new LinkedList<>();	//making list of points to get to the node
		Node current = n;						//making handle on current node
		
		//looping while current node is not the root
		while(!current.equals(root)) {
			
			path.add(current.getState());	//adding current point to path
			current = current.getParent();	//walking up the tree
			
		}
		
		return path;
	}

}
