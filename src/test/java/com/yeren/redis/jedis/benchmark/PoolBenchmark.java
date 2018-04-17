package com.yeren.redis.jedis.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * 测试多个线程同时读写
 * @author ThinkPad
 *
 */
public class PoolBenchmark {
  private static final int TOTAL_OPERATIONS = 1000;

  public static void main(String[] args) throws Exception {
    Jedis j = new Jedis("139.199.124.81", 6370);
    j.connect();
    j.auth("foobared");
    j.flushAll();
    j.quit();
    j.disconnect();
    long t = System.currentTimeMillis();
    withPool();
    long elapsed = System.currentTimeMillis() - t;
    System.out.println(((1000 * 2 * TOTAL_OPERATIONS) / elapsed) + " ops");
  }

  private static void withPool() throws Exception {
    final JedisPool pool = new JedisPool(new GenericObjectPoolConfig(), "139.199.124.81",6370, 2000, "foobared");
    List<Thread> tds = new ArrayList<Thread>();

    final AtomicInteger ind = new AtomicInteger();
    for (int i = 0; i < 50; i++) {
      Thread hj = new Thread(new Runnable() {
        public void run() {
          for (int i = 0; (i = ind.getAndIncrement()) < TOTAL_OPERATIONS;) {
            try {
              Jedis j = pool.getResource();
              final String key = "foo" + i;
              j.set(key, key);
              j.get(key);
              j.close();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      });
      tds.add(hj);
      hj.start();
    }

    for (Thread t : tds)
      t.join();

    pool.destroy();

  }
}