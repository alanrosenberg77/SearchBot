package path.agent;

import java.awt.Point;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import path.agent.heuristic.Heuristic;
import path.level.Level;


/**
 * All search agents for this program will extend this class.  The agents will be aware (have 
 * a handle on the level) as well as the start state and goal state.  Here the states are way points
 * in the level geometry.   After fully configuring the agent, we can ask the agent to plan, which
 * results in a path and a search tree rooted at root.
 * <p>
 * After an agent plans, we will retain the tree rooted by the root handle and the path so we can
 * interrogate the agent about the results of search.
 * <p>
 * NOTE:  The programmer must clear/reset the agent to return to initial configuration/before planning.
 * 
 */
public abstract class PathAgent {

	protected Level level;   // just in case we need to ask the level for information

	protected Point start;   // starting point established by operator
	
	protected Point goal;	 // ending point established by operator

	protected List<Point> path = null;   // a path resulting from planning; null means path not available

	protected Node root;   // a handle on the resulting search tree after planning.  null means no plan yet.


	/**
	 * All agents are born with a knowledge of the current level...for convenience.  May not
	 * be needed by some agents.
	 * 
	 * @param lvl
	 */
	public PathAgent(Level lvl) {
		this.level = lvl;
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getGoal() {
		return goal;
	}

	public void setGoal(Point goal) {
		this.goal = goal;
	}

	public List<Point> getPath() {
		return path;
	}
	

	/**
	 * Implements the search, building the resulting search tree and
	 * establishing the path (see setter method) as well as returning 
	 * the path (as a programmer's convenience).
	 * 
	 * @return
	 */
	public abstract List<Point> findPath();
	
	
	/**
	 * Walks back up the search tree from the specified search node providing the list
	 * of states (ie, way points) that will be traveled along this path.  NOTE: this is not
	 * the list of actions to take.  This is the list of states the define the path.  
	 * 
	 * @param current the current node (presumably the goal node)
	 * @return a list of points to travel to get to the goal from the start of search
	 */
	public List<Point> pathFromNode(Node current) {

		List<Point> pth = new LinkedList<>();

		while (current != null) {
			pth.add(current.getState());
			current = current.getParent();
		}

		Collections.reverse(pth);

		return pth;
	}

	
	/**
	 * Return the states of the entire search tree. 
	 * 
	 * @return collection of states/points reached or null if no tree there
	 */
	public List<Point> searchTreeStates()  {
		
		//returning null if tree does not exist
		if(root == null) {
			return null;
		}
		
		//otherwise...
		Queue<Node> q = new LinkedList<>();			//making queue
		List<Point> points = new LinkedList<>();	//making list for points
		Node current = null;						//and making handle for current node
		
		//initializing search with root node
		q.add(root);
		
		//looping while queue is not empty
		while(!q.isEmpty()) {
			
			current = q.remove();				//getting handle on next item while removing from queue
			points.add(current.getState());		//adding current point to list of points
			q.addAll(current.getChildren());	//adding current node's children to queue
		}
		
		//returning list of points
		return points;
	}

	
	/**
	 * Returns the depth d of the search tree or -1 if the tree
	 * does not exist.
	 * 
	 * @return the 
	 */
	public int searchTreeDepth() {
		
		//returning -1 if tree does not exist
		if(root == null) {
			return -1;
		}
		
		//otherwise...
		Queue<Node> q = new LinkedList<>();			//making queue
		Node current = null;						//and making handle for current node
		int depth = 0;
		
		//initializing search with root node
		q.add(root);
		
		//looping while queue is not empty
		while(!q.isEmpty()) {
			
			current = q.remove();				//getting handle on next item while removing from queue
			q.addAll(current.getChildren());	//adding current node's children to queue
			
			if(depth < current.getDepth())
				depth = current.getDepth();
		}
		
		return depth;
	}

	/**
	 * Private helper method that generates valid children for a parent node.
	 * Creates points in each cardinal direction from the parent location,
	 * checks that the points are valid, then makes new Nodes
	 * 
	 * @param parent node
	 * @return List of child nodes
	 */
	protected List<Node> generateChildren(Node parent) {
		
		List<Node> children = new LinkedList<>();	//making list for child nodes
		
		//making north point
		Point north = new Point();
		north.setLocation(parent.getState().getX(), parent.getState().getY()+1);
		
		//checking north validity
		if(level.isValid(north)) {
			
			//appending to list of children
			Node n = new Node(north, parent, Action.N);
			children.add(n);
		}
		
		//making south point
		Point south = new Point();
		south.setLocation(parent.getState().getX(), parent.getState().getY()-1);
		
		//checking south validity
		if(level.isValid(south)) {
			
			//appending to list of children
			Node s = new Node(south, parent, Action.S);
			children.add(s);
		}
		
		//making east point
		Point east = new Point();
		east.setLocation(parent.getState().getX()+1, parent.getState().getY());
		
		//checking east validity
		if(level.isValid(east)) {
			
			//appending to list of children
			Node e = new Node(east, parent, Action.E);
			children.add(e);
		}
		
		//making west point ;)
		Point west = new Point();
		west.setLocation(parent.getState().getX()-1, parent.getState().getY());
		
		//checking west validity
		if(level.isValid(west)) {
			
			//appending to list of children
			Node w = new Node(west, parent, Action.W);
			children.add(w);
		}
		
		return children;
	}
	
	/**
	 * Returns to a pre-search state in which no path is known and
	 * no search tree exists.
	 */
	public void clearPath() {
		
		this.root = null;
		this.path = null;
		
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Node getRoot() {
		return root;
	}



}
