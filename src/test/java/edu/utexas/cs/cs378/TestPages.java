package edu.utexas.cs.cs378;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestPages {
	
	public List<DataItem> myData;

	@Before
	public void setUp() throws Exception {
		
		myData=	Utils.generateExampleData(4000000);
		
		
	}

	@Test
	public void test() {
		
		
		List<byte[]> pages = Utils.packageToPages(myData);
		
		
		
	}

}
