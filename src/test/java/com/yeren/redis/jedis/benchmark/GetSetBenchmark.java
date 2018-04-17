package com.yeren.redis.jedis.benchmark;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;

import redis.clients.jedis.Jedis;
/**
 * 测试随机读写
 * @author ThinkPad
 *
 */
public class GetSetBenchmark {
  private static final int TOTAL_OPERATIONS = 300;

  public static void main(String[] args) throws UnknownHostException, IOException {
    Jedis jedis = new Jedis("139.199.124.81", 6370);
    jedis.connect();
    jedis.auth("foobared");
    jedis.flushAll();

    long begin = Calendar.getInstance().getTimeInMillis();

    for (int n = 0; n <= TOTAL_OPERATIONS; n++) {
      String key = "foo" + n;
      jedis.set(key, "bar" + n);
      jedis.get(key);
    }

    long elapsed = Calendar.getInstance().getTimeInMillis() - begin;

    jedis.disconnect();

    System.out.println(((1000 * 2 * TOTAL_OPERATIONS) / elapsed) + " ops");
  }
}