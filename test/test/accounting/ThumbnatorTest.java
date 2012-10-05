package test.accounting;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.catalina.ha.backend.CollectedInfo;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

public class ThumbnatorTest {

	@Test
	public void test() throws Exception {
		File file = new File("C:/Users/jinukey/Desktop/제주도/P7310429.JPG");
		Thumbnails.of(file).size(1200, 1200).toFile("D:/workspace/openAccounting/web/upload/P7310430.jpg");
		
	}
	
	@Test
	public void test1() {
		List<String> list = new ArrayList<String>(); 
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		System.out.println(list.toString());
		Collections.shuffle(list);
		System.out.print(list.toString());
	}
}
