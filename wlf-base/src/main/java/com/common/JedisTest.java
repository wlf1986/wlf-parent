package com.common;

import redis.clients.jedis.Jedis;

import java.lang.reflect.InvocationTargetException;

public class JedisTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Jedis jedis = new Jedis("10.2.16.86");
        jedis.auth("wlf");
        String ping = jedis.ping();
        System.out.println(ping);
//        User user = new User();
//        user.setId(19L);
////        Method method = user.getClass().getMethod("setId", Long.class);
////        method.invoke(user,1L);
////        System.out.println(user.getId());
//        Object getId = user.getClass().getMethod("getId").invoke(user);
//        System.out.println(getId);
    }

}
