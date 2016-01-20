package com.huyong.mongo.repository;

/**
 * Created by huyong on 1/20/16.
 */


import java.util.List;

import com.huyong.mongo.domain.Tree;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;


public class NatureRepositoryImpl implements Repository<Tree> {

    MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Get all trees.
     */
    public List<Tree> getAllObjects() {
        return mongoTemplate.findAll(Tree.class);
    }

    /**
     * Saves a {<span class="referer">@link</span>  Tree}.
     */
    public void saveObject(Tree tree) {
        mongoTemplate.insert(tree);
    }

    /**
     * Gets a {<span class="referer">@link</span>  Tree} for a particular id.
     */
    public Tree getObject(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
                Tree.class);
    }

    /**
     * Updates a {<span class="referer">@link</span>  Tree} name for a particular id.
     */
    public WriteResult setObject(String id, String objName, String objValue) {
        return mongoTemplate.updateFirst(
                new Query(Criteria.where("id").is(id)),
                new Update().set(objName, objValue), Tree.class);
    }

    public WriteResult incObject(String id, String objName, Number incValue) {
        return mongoTemplate.updateFirst(
                new Query(Criteria.where("id").is(id)),
                new Update().inc(objName, incValue), Tree.class);
    }

    public WriteResult pushObject(String id, String objName, String pushValue) {
        return mongoTemplate.updateFirst(
                new Query(Criteria.where("id").is(id)),
                new Update().push(objName, pushValue), Tree.class);
    }

    /**
     * Delete a {<span class="referer">@link</span>  Tree} for a particular id.
     */
    public void deleteObject(String id) {
        mongoTemplate
                .remove(new Query(Criteria.where("id").is(id)), Tree.class);
    }

    /**
     * Create a {<span class="referer">@link</span>  Tree} collection if the collection does not already
     * exists
     */
    public void createCollection() {
        if (!mongoTemplate.collectionExists(Tree.class)) {
            mongoTemplate.createCollection(Tree.class);
        }
    }

    /**
     * Drops the {<span class="referer">@link</span>  Tree} collection if the collection does already exists
     */
    public void dropCollection() {
        if (mongoTemplate.collectionExists(Tree.class)) {
            mongoTemplate.dropCollection(Tree.class);
        }
    }
}
