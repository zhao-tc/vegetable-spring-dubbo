package com.ztc.vegetable.manage.provider.comment.redis;


import redis.clients.jedis.Jedis;

public class RedisBO {
        public static void main(String[] args) {
            //连接本地的 Redis 服务
            Jedis jedis = new Jedis("47.103.113.151",6379);
            jedis.auth("root");
            System.out.println("连接成功");
            //查看服务是否运行
            System.out.println("服务正在运行: "+jedis.ping());
        }
}
