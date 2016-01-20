package com.huyong.mongo.repository;

/**
 * Created by huyong on 1/20/16.
 */


import java.util.List;

import com.mongodb.WriteResult;

public interface Repository<T> {

    public List<T> getAllObjects();

    public void saveObject(T object);

    public T getObject(String id);

    public WriteResult setObject(String id, String objName, String objValue);

    public WriteResult incObject(String id, String objName, Number incValue);

    public WriteResult pushObject(String id, String objName, String string);

    public void deleteObject(String id);

    public void createCollection();

    public void dropCollection();
}
