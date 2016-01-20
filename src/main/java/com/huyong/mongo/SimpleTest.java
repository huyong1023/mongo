package com.huyong.mongo;

import com.huyong.mongo.domain.Tree;
import com.huyong.mongo.repository.NatureRepositoryImpl;
import com.huyong.mongo.repository.Repository;
import com.mongodb.*;


import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;


import static java.util.Arrays.asList;
/**
 * Created by huyong on 1/15/16.
 */

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SimpleTest {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:/applicationContext.xml");

        Repository<Tree> repository = context.getBean(NatureRepositoryImpl.class);

        // cleanup collection before insertion
        repository.dropCollection();

        // create collection
        repository.createCollection();

        repository.saveObject(new Tree("1", "Apple Tree", 10));

        repository.saveObject(new Tree("2", "Orange Tree", 3));

        long start = System.currentTimeMillis();

        for (int i = 0; i < 50; i++) {
            repository.setObject("1", "name", "BananaTree");
            repository.setObject("1", "name", "KiwiFruitTree");
        }

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.format("1. 使用set修改100次，共用时%s毫秒n", duration);

        start = System.currentTimeMillis();

        for (int i = 0; i < 50; i++) {
            repository.incObject("1", "age", 1);
            repository.incObject("1", "age", 2);
        }

        end = System.currentTimeMillis();
        duration = end - start;
        System.out.format("2. 使用inc修改100次，共用时%s毫秒n", duration);

        start = System.currentTimeMillis();

        for (int i = 0; i < 50; i++) {
            repository.pushObject("1", "fruit", "Banana" + i);
            repository.pushObject("1", "fruit", "Kiwi" + i);
        }

        end = System.currentTimeMillis();
        duration = end - start;
        System.out.format("3. 使用push修改100次，共用时%s毫秒n", duration);
    }
}
