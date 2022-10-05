package com.atguigu.adin;

import com.atguigu.bean.Emp2;
import com.atguigu.mapper.EmpMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootTest
class Boot05WebAdminApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    EmpMapper empMapper;

    @Test
    void contextLoads() {
        Integer ans = jdbcTemplate.queryForObject("select count(*) from book", Integer.class);
        log.info("BOOKSHOP TABLE裡共有 {} 筆資料", ans);
    }

    @Test
    void testEmpMapper(){
        Emp2 emp = empMapper.selectById(7001);
        log.info("員工7001 詳細情報: {}", emp);

    }

    @Test
    void testRedis(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();

        //add一筆資料
//        stringStringValueOperations.set("hello", "world, springboot");

        log.info("從redis拿到的值: {}", stringStringValueOperations.get("pens"));
        log.info("使用的Connection Factory是 {}", redisConnectionFactory.getClass());
    }

}
