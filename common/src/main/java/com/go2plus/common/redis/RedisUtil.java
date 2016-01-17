package com.go2plus.common.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Copyright (C) 2015 GO2.CN. All rights reserved. This computer program source code file is protected by copyright law and international
 * treaties. Unauthorized distribution of source code files, programs, or portion of the package, may result in severe civil and criminal
 * penalties, and will be prosecuted to the maximum extent under the law.
 * 
 * redis和连接池的封装 Jedis操作步骤如下： 获取Jedis实例需要从JedisPool中获取； 用完Jedis实例需要返还给JedisPool； 如果Jedis在使用过程中出错，则也需要还给JedisPool；
 * 
 * @author chengzl
 * @since 2015-11-11
 */

public class RedisUtil {
  private final static Logger log  = LoggerFactory.getLogger(RedisUtil.class);

  private static JedisPool    pool = null;

  /**
   * 构建redis连接池
   * 
   * @return JedisPool
   */
  public static JedisPool getPool() {
    if (pool == null) {
      JedisPoolConfig config = new JedisPoolConfig();
      config.setMaxTotal(500);
      config.setMaxIdle(5);
      config.setMinIdle(0);
      config.setMaxWaitMillis(100000);
      config.setTestOnBorrow(true);
      pool = new JedisPool(config, "121.40.214.228", 5268);
    }
    return pool;
  }

  /**
   * 构建redis连接池
   * 
   * @param ip
   * @param port
   * @return JedisPool
   */
  public static JedisPool getPool(String ip, int port) {
    if (pool == null) {
      JedisPoolConfig config = new JedisPoolConfig();
      config.setMaxTotal(500);
      config.setMaxIdle(5);
      config.setMinIdle(0);
      config.setMaxWaitMillis(100000);
      config.setTestOnBorrow(true);
      pool = new JedisPool(config, ip, port, 6379);
    }
    return pool;
  }

  /**
   * 返还到连接池
   * 
   * @param pool
   * @param redis
   */
  @SuppressWarnings("deprecation")
  public static void returnResource(JedisPool pool, Jedis redis) {
    if (redis != null && pool != null) {
      pool.returnResource(redis);
    }
  }

  /**
   * 从jedis连接池中获取获取jedis对象
   * 
   * @return
   */
  public static Jedis getJedis() {
    return pool.getResource();
  }

  /**
   * 回收jedis
   * 
   * @param jedis
   */
  public void returnJedis(Jedis jedis) {
    pool.returnResource(jedis);
  }

  /**
   * 获取数据
   * 
   * @param key
   * @return
   */
  public static String get(String key) {
    String value = null;
    Jedis jedis = null;
    try {
      if (pool == null)
        pool = getPool();
      jedis = pool.getResource();
      value = jedis.get(key);
    } catch (Exception e) {
      pool.returnBrokenResource(jedis);
      e.printStackTrace();
    } finally {
      returnResource(pool, jedis);
    }

    return value;
  }

  /**
   * 设置 String
   * 
   * @param key
   * @param value
   */
  public static void set(String key, String value) {
    Jedis jedis = null;
    try {
      if (pool == null)
        pool = getPool();
      jedis = pool.getResource();
      // log.error("First create JedisPool error : ");
      jedis.set(key, value);
    } catch (Exception e) {
      pool.returnBrokenResource(jedis);
      e.printStackTrace();
    } finally {
      returnResource(pool, jedis);
    }
  }

  /**
   * 设置 hashMap
   * 
   * @param key
   * @param map
   */
  public static void setMap(String key, HashMap<String, String> map) {
    Jedis jedis = null;
    try {
      if (pool == null)
        pool = getPool();
      jedis = pool.getResource();
      // log.error("First create JedisPool error : ");
      jedis.hmset(key, map);
    } catch (Exception e) {
      pool.returnBrokenResource(jedis);
      e.printStackTrace();
    } finally {
      returnResource(pool, jedis);
    }
  }

  /**
   * 判断redis是否连接异常
   * 
   * @return 是否有异常
   */
  public static boolean isConnectRedis() {
    Jedis jedis = null;
    try {
      if (pool == null)
        pool = getPool();
      jedis = pool.getResource();
    } catch (Exception e) {
      log.error("Reids u Exception");
      pool.returnBrokenResource(jedis);
      return false;
    } finally {
      returnResource(pool, jedis);
    }

    return true;
  }

  /**
   * 判断key是否存在
   * 
   * @param key
   * @return true OR false
   */
  public static Boolean exists(String key) {
    Jedis jedis = null;
    try {
      if (pool == null)
        pool = getPool();
      jedis = pool.getResource();
      return jedis.exists(key);
    } catch (Exception e) {
      pool.returnBrokenResource(jedis);
      e.printStackTrace();
      return false;
    } finally {
      returnResource(pool, jedis);
    }
  }

  /**
   * 删除指定的key,也可以传入一个包含key的数组
   * 
   * @param keys
   *          一个key 也可以使 string 数组
   * @return 返回删除成功的个数
   */
  public static Long del(String keys) {
    Jedis jedis = null;
    try {
      if (pool == null)
        pool = getPool();
      jedis = pool.getResource();
      return jedis.del(keys);
    } catch (Exception e) {
      pool.returnBrokenResource(jedis);
      e.printStackTrace();
      return 0L;
    } finally {
      returnResource(pool, jedis);
    }
  }

  /**
   * Hash
   */
  public long hdel(String key, String fieid) {
    Jedis jedis = getJedis();
    long s = jedis.hdel(key, fieid);
    returnJedis(jedis);
    return s;
  }

  public long hdel(String key) {
    Jedis jedis = getJedis();
    long s = jedis.del(key);
    returnJedis(jedis);
    return s;
  }

  /**
   * 测试hash中指定的存储是否存在
   * 
   * @param String
   *          key
   * @param String
   *          fieid 存储的名字
   * @return 1存在，0不存在
   * */
  public boolean hexists(String key, String fieid) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    boolean s = sjedis.hexists(key, fieid);
    returnJedis(sjedis);
    return s;
  }

  /**
   * 返回hash中指定存储位置的值
   * 
   * @param String
   *          key
   * @param String
   *          fieid 存储的名字
   * @return 存储对应的值
   * */
  public String hget(String key, String fieid) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    String s = sjedis.hget(key, fieid);
    returnJedis(sjedis);
    return s;
  }

  public byte[] hget(byte[] key, byte[] fieid) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    byte[] s = sjedis.hget(key, fieid);
    returnJedis(sjedis);
    return s;
  }

  /**
   * 以Map的形式返回hash中的存储和值
   * 
   * @param String
   *          key
   * @return Map<Strinig,String>
   * */
  public Map<String, String> hgetAll(String key) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    Map<String, String> map = sjedis.hgetAll(key);
    returnJedis(sjedis);
    return map;
  }

  /**
   * 添加一个对应关系
   * 
   * @param String
   *          key
   * @param String
   *          fieid
   * @param String
   *          value
   * @return 状态码 1成功，0失败，fieid已存在将更新，也返回0
   * **/
  public long hset(String key, String fieid, String value) {
    Jedis jedis = getJedis();
    long s = jedis.hset(key, fieid, value);
    returnJedis(jedis);
    return s;
  }

  public long hset(String key, String fieid, byte[] value) {
    Jedis jedis = getJedis();
    long s = jedis.hset(key.getBytes(), fieid.getBytes(), value);
    returnJedis(jedis);
    return s;
  }

  /**
   * 添加对应关系，只有在fieid不存在时才执行
   * 
   * @param String
   *          key
   * @param String
   *          fieid
   * @param String
   *          value
   * @return 状态码 1成功，0失败fieid已存
   * **/
  public long hsetnx(String key, String fieid, String value) {
    Jedis jedis = getJedis();
    long s = jedis.hsetnx(key, fieid, value);
    returnJedis(jedis);
    return s;
  }

  /**
   * 获取hash中value的集合
   * 
   * @param String
   *          key
   * @return List<String>
   * */
  public List<String> hvals(String key) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    List<String> list = sjedis.hvals(key);
    returnJedis(sjedis);
    return list;
  }

  /**
   * 在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型
   * 
   * @param String
   *          key
   * @param String
   *          fieid 存储位置
   * @param String
   *          long value 要增加的值,可以是负数
   * @return 增加指定数字后，存储位置的值
   * */
  public long hincrby(String key, String fieid, long value) {
    Jedis jedis = getJedis();
    long s = jedis.hincrBy(key, fieid, value);
    returnJedis(jedis);
    return s;
  }

  /**
   * 返回指定hash中的所有存储名字,类似Map中的keySet方法
   * 
   * @param String
   *          key
   * @return Set<String> 存储名称的集合
   * */
  public Set<String> hkeys(String key) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    Set<String> set = sjedis.hkeys(key);
    returnJedis(sjedis);
    return set;
  }

  /**
   * 获取hash中存储的个数，类似Map中size方法
   * 
   * @param String
   *          key
   * @return long 存储的个数
   * */
  public long hlen(String key) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    long len = sjedis.hlen(key);
    returnJedis(sjedis);
    return len;
  }

  /**
   * 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null
   * 
   * @param String
   *          key
   * @param String
   *          ... fieids 存储位置
   * @return List<String>
   * */
  public List<String> hmget(String key, String... fieids) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    List<String> list = sjedis.hmget(key, fieids);
    returnJedis(sjedis);
    return list;
  }

  public List<byte[]> hmget(byte[] key, byte[]... fieids) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    List<byte[]> list = sjedis.hmget(key, fieids);
    returnJedis(sjedis);
    return list;
  }

  /**
   * 添加对应关系，如果对应关系已存在，则覆盖
   * 
   * @param Strin
   *          key
   * @param Map
   *          <String,String> 对应关系
   * @return 状态，成功返回OK
   * */
  public String hmset(String key, Map<String, String> map) {
    Jedis jedis = getJedis();
    String s = jedis.hmset(key, map);
    returnJedis(jedis);
    return s;
  }

  /**
   * 添加对应关系，如果对应关系已存在，则覆盖
   * 
   * @param Strin
   *          key
   * @param Map
   *          <String,String> 对应关系
   * @return 状态，成功返回OK
   * */
  public String hmset(byte[] key, Map<byte[], byte[]> map) {
    Jedis jedis = getJedis();
    String s = jedis.hmset(key, map);
    returnJedis(jedis);
    return s;
  }
}
