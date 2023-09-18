package path.test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import path.agent.AStarAgent;
import path.agent.Action;
import path.agent.GreedyBestAgent;
import path.agent.Node;
import path.level.Level;

class Tests {
	
	AStarAgent asa;
	GreedyBestAgent gba;
	Point testStart;
	Point testGoal;
	Level level;
	
	@BeforeEach
	void setUp() {
		testStart = new Point(10, 10);
		testGoal = new Point(90, 10);
		
		level = Level.builder().size(100, 100)
				
				.addZone(new Rectangle(50, 50, 10, 50))
				.build();
		
		gba = new GreedyBestAgent(level);
		asa = new AStarAgent(level);
		
		gba.setStart(testStart);
		gba.setGoal(testGoal);
		
		asa.setStart(testStart);
		asa.setGoal(testGoal);
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
		
		Node start = new Node(testStart, null, null);
		Node goal = new Node(testGoal, null, null);
		start.setFval(testStart.distance(testGoal));
		goal.setFval(0);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(start);
		pq.add(goal);
		
		assertTrue(pq.contains(start));
		assertTrue(pq.contains(goal));
		
		assertTrue(goal.equals(pq.peek()));
	}
	
	@Test
	void test_searchTreeDepthNormal() {
		
		gba.findPath();
		asa.findPath();
		
		int gbDepth = gba.searchTreeDepth();
		int asDepth = asa.searchTreeDepth();
		
		System.err.println("Greedy Best Depth: " + gbDepth);
		System.err.println("A* Depth: " + asDepth);
		
		assertNotEquals(0, gbDepth);
		assertNotEquals(0, asDepth);
	}
	
	@Test
	void test_searchTreeStatesNormal() {
		
		gba.findPath();
		asa.findPath();
		
		List<Point> gbTree = gba.searchTreeStates();
		List<Point> asTree = asa.searchTreeStates();
		
		System.err.println("Greedy Best Tree: " + gbTree.size());
		System.err.println("A* Tree: " + asTree.size());
		
		assertNotNull(gbTree);
		assertNotNull(asTree);
	}

}
