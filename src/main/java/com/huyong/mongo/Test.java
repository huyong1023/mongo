package com.huyong.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.util.Set;

/**
 * Created by huyong on 1/20/16.
 */
public class Test {

    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("192.168.11.22");
            // 连接到数据库
            DB db = mongoClient.getDB("test");
            System.out.println("Connect to database successfully");
            Boolean flag = db.collectionExists("tree");

            System.out.println("Collection mycol selected successfully:" + flag);
            System.out.println("Collection created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }
}
