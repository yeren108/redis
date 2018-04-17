package com.yeren.redis.jedis.cluster;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClusterTest {
	Set<HostAndPort> nodes;
	JedisCluster jc;

	@Before
	public void setUp() throws InterruptedException {
		nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.150.138", 6379));
		nodes.add(new HostAndPort("192.168.150.138", 6380));
		nodes.add(new HostAndPort("192.168.150.138", 6381));
		nodes.add(new HostAndPort("192.168.150.138", 6382));
		nodes.add(new HostAndPort("192.168.150.138", 6383));
		nodes.add(new HostAndPort("192.168.150.138", 6384));
		jc = new JedisCluster(nodes);

	}

	@AfterClass
	public static void cleanUp() {
		// node1.flushDB();
		// node2.flushDB();
		// node3.flushDB();
	}

	@After
	public void tearDown() throws InterruptedException {
		// cleanUp();
	}

	@Test
	public void testReadonly() throws Exception {
		// jc.set("blueCat", "haha");
		String string = jc.get("tiger");
		System.out.println(string);

	}

}
