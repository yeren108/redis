package com.yeren.redis.jedis.commands;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

public abstract class JedisCommandTestBase {
  protected static HostAndPort hnp = new HostAndPort("139.199.124.81", 6370);

  protected Jedis jedis;

  public JedisCommandTestBase() {
    super();
  }

  @Before
  public void setUp() throws Exception {
    jedis = new Jedis(hnp.getHost(), hnp.getPort(), 500);
    jedis.connect();
    jedis.auth("foobared");
    jedis.flushAll();
  }

  @After
  public void tearDown() {
    jedis.disconnect();
  }

  protected Jedis createJedis() {
    Jedis j = new Jedis("139.199.124.81",6370);
    j.connect();
    j.auth("foobared");
    j.flushAll();
    return j;
  }

  protected boolean arrayContains(List<byte[]> array, byte[] expected) {
    for (byte[] a : array) {
      try {
        assertArrayEquals(a, expected);
        return true;
      } catch (AssertionError e) {

      }
    }
    return false;
  }

  protected boolean setContains(Set<byte[]> set, byte[] expected) {
    for (byte[] a : set) {
      try {
        assertArrayEquals(a, expected);
        return true;
      } catch (AssertionError e) {

      }
    }
    return false;
  }
}
