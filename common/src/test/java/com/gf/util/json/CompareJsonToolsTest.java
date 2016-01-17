package com.gf.util.json;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.junit.Before;

/**
 * Copyright (C) 2015 GO2.CN. All rights reserved. This computer program source code file is protected by copyright law and international
 * treaties. Unauthorized distribution of source code files, programs, or portion of the package, may result in severe civil and criminal
 * penalties, and will be prosecuted to the maximum extent under the law.
 * 
 * 用于测试 Jackson fastjson 与Gson 的性能, 模拟2层次对象 进行序列化和反序列化
 * 
 * @author gaofeng
 * @since 2015-10-30
 */
public class CompareJsonToolsTest {
  @Before
  public void setUp() {

  }

  /*
   * @Test public void test3JsonTools() { Group group = new Group(); group.setUpdateTime(new Date()); int amountOfUsers=1000; for (int i =
   * 0; i < amountOfUsers; i++) { User user = new User(); user.setId(1L); user.setName("test" + i); user.setVendor("class" + i);
   * user.setImageUrl("img/class.png" + i); user.setCreator("niles" + i); user.setInfo("Test user in group" + i); group.addUser(user); } try
   * { System.err.println("Start compare with a sample witch contains "+amountOfUsers+" User instances"); Gson gson = new Gson(); Gson gp =
   * new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
   * 
   * ObjectMapper mapper = new ObjectMapper();
   * 
   * long t0 = 0; long t1 = System.currentTimeMillis(); String str = null;
   * 
   * t0 = t1; str = gp.toJson(group); t1 = System.currentTimeMillis(); System.err.println("Gson(Pretty) serialize object:" + (t1 - t0));
   * 
   * t0 = t1; Group group3 = gp.fromJson(str, Group.class); t1 = System.currentTimeMillis(); System.err.println("Gson(Pretty) parse object:"
   * + (t1 - t0));
   * 
   * System.err.println("--------------");
   * 
   * t0 = t1; str = gson.toJson(group); t1 = System.currentTimeMillis(); System.err.println("Gson serialize object:" + (t1 - t0));
   * 
   * t0 = t1; Group group4 = gson.fromJson(str, Group.class); t1 = System.currentTimeMillis(); System.err.println("Gson parse object:" + (t1
   * - t0));
   * 
   * System.err.println("--------------");
   * 
   * t0 = t1; str = gp.toJson(group); t1 = System.currentTimeMillis(); System.err.println("Gson(Pretty) serialize object:" + (t1 - t0));
   * 
   * t0 = t1; Group group5 = gp.fromJson(str, Group.class); t1 = System.currentTimeMillis(); System.err.println("Gson(Pretty) parse object:"
   * + (t1 - t0));
   * 
   * System.err.println("--------------");
   * 
   * t0 = t1; str = mapper.writeValueAsString(group); t1 = System.currentTimeMillis(); System.err.println("jackson serialize object:" + (t1
   * - t0));
   * 
   * t0 = t1; Group group1 = mapper.readValue(str, Group.class); t1 = System.currentTimeMillis(); System.err.println("jackson parse object:"
   * + (t1 - t0));
   * 
   * System.err.println("--------------");
   * 
   * t0 = t1; str = JSON.toJSONString(group); t1 = System.currentTimeMillis(); System.err.println("fastjson serialize object:" + (t1 - t0));
   * 
   * t0 = t1; Group group2 = JSON.parseObject(str, Group.class); t1 = System.currentTimeMillis();
   * System.err.println("fastjson parse object:" + (t1 - t0)); } catch (Exception ex) { ex.printStackTrace(); }
   * 
   * }
   */
}

class User {

  private Long   id;
  private String name;
  private String vendor;
  private String imageUrl;
  private String creator;
  private String info;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public String getVendor() {
    return this.vendor;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getImageUrl() {
    return this.imageUrl;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

}

class Group {
  private Map<String, User> users      = new Hashtable<String, User>();

  private Date              updateTime = null;

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public Map<String, User> getUsers() {
    return users;
  }

  public void setUsers(Map<String, User> users) {
    this.users = users;
  }

  public void addUser(User user) {
    users.put(user.getName(), user);
  }

  public User getUser(String name) {
    return users.get(name);
  }
}