package com.jedis.test;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.jedis.pojo.Person;

import redis.clients.jedis.Jedis;



public class Test {
	private Jedis jedis;
	
	@Before
	public void initJedis(){
		jedis = new Jedis("127.0.0.1",6379);
		
	}
	
	/**
	 * redis设置字符串
	 */
	@org.junit.Test 
	public void test1(){
		jedis.set("name", "tom");
		System.out.println("set:"+jedis.get("name"));
		jedis.del("name");
		System.out.println("del:"+jedis.get("name"));
		jedis.append("name", ",jack");
		System.out.println("append:"+jedis.get("name"));
		jedis.mset("code","123","name","jose","pass","qwe","age","11");
		System.out.println("code:" + jedis.get("code") + ",name:" 
				+ jedis.get("name") + ",pass:" + jedis.get("pass"));
		jedis.incr("age");
		System.out.println("incr:"+jedis.get("age"));
		
	}
	
	/**
	 * redis设置map对象
	 */
	
	@org.junit.Test
	public void test2(){
		Map<String, String> user = new HashMap<String, String>();
		user.put("code", "1245");
		user.put("name", "lose");
		user.put("pass", "pass");
		jedis.hmset("user",user);
		Iterator<String> it = jedis.hkeys("user").iterator();
		while(it.hasNext()){
			System.out.println("hmget:"+jedis.hmget("user",it.next()));
		}
		jedis.hdel("user", "pass");
		System.out.println("hdel:"+jedis.hmget("user", "pass"));
		
	}
	
	/**
	 * 指定类型不确定参数
	 */
	@org.junit.Test
	public void test3(){
		Person person = new Person();
		System.out.println(person.getStrings("3333333","44444444"));
		System.out.println(person.getStrings("1111","22222","3333333","44444444"));
	}
}
