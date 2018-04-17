package com.yeren.redis.jedis.standalone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisStandalone {

	Jedis node1;

	@Before
	public void setUp() throws InterruptedException {
		node1 = new Jedis("139.199.124.81", 6370);
	}

	@AfterClass
	public static void cleanUp() {
		// node1.flushDB();
	}

	@After
	public void tearDown() throws InterruptedException {
		// cleanUp();
	}

	@Test
	public void testReadonly() throws Exception {
		node1.set("blueCat", "haha");
		String string = node1.get("bb");
		System.out.println(string);
		System.out.println(node1.get("blueCat"));

	}

}
