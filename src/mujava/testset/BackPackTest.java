

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class BackPackTest {
	private BackPack b;
	
	int m = 10;
    int n = 3;
    int w[] = {3, 4, 5};
    int p[] = {4, 5, 6};
    int arrTest[][]= {
    		{0,0,0,0,0,0,0,0,0,0,0},
    		{0,0,0,4,4,4,4,4,4,4,4},	
    		{0,0,0,4,5,5,5,9,9,9,9},	
    		{0,0,0,4,5,6,6,9,10,11,11}};
    
	@Before
	public void setUp() {
		b = new BackPack();
	}
	
	@After
	public void tearDown() {
		b = null;
	}
	
	@Test
	public void test1() {
		assertArrayEquals(arrTest,new BackPack().BackPack_Solution(m,n,w,p));
	}
	
}
