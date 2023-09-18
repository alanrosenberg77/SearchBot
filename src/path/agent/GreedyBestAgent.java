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
			
			//checking if current node is solution
			if(current.getState().equals(goal))
				return pathFromNode(current);	//returning path to goal
			
			//generating child nodes from current node
			List<Node> children = generateChildren(current);
			
			for(Node child : children) {
				
				/*
				 * If the fringe does not contain the next node, and we have not visited
				 * the current node before, then add the next node to the fringe
				 */
				
				if(!fringe.contains(child) && !visited.contains(child)) {
					fringe.add(child);
				}
			}
			
			//adding to visited list
			visited.add(current);
		}
		
		return null;
	}

}
