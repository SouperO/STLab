

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class BubbleSortTest {
	private BubbleSort b;
	int arrTest[] = new int[]{1,6,2,2,5};
	int arrAfterTest[] = new int[]{1,2,2,5,6};
	
	@Before
	public void setUp() {
		b = new BubbleSort();
	}
	
	@After
	public void tearDown() {
		b = null;
	}
	
	@Test
	public void test1() {
		assertArrayEquals(arrAfterTest, b.BubbleSort(arrTest));
	}

}
