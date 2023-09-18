package path.test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.HashSet;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import path.agent.AStarAgent;
import path.agent.Action;
import path.agent.GreedyBestAgent;
import path.agent.Node;
import path.level.Level;

class Tests {
	
	AStarAgent asa;
	GreedyBestAgent bga;
	Node testStart;
	Node testGoal;
	
	@Before
	void setUp() {
		testGoal = new Node(new Point(), null, Action.S);
		testStart = new Node(new Point(), null, null);
		
		// TODO finish setup
	}

	@Test
	void test_HashSet_with_Points() {
		
		HashSet<Point> set = new HashSet<Point>();
		
		set.add(new Point(100,50));
		set.add(new Point(200,100));
		set.add(new Point(300,150));
		
		assertTrue (set.contains(new Point(200,100)));
		
	}
	
	@Test
	void test_PriorityQueue_with_TreeNode() {
		fail("not implemented yet");
	}
	
	@Test
	void test_searchTreeDepthNormal() {
		// TODO test searchTreeDepth()
	}
	
	@Test
	void test_generateChildrenNormal() {
		
		
	}

}
