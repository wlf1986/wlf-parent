package com.utils;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author 王岚枫
 * @Datetime 2017年08月24日 16:47
 */
public final class MongoUtil {

    private Long seq;
    private String name;


    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MongoUtil(Long seq, String name) {
        this.seq = seq;
        this.name = name;
    }

    public static Long getId(MongoTemplate mongoTemplate, Class zlass) {
        MongoUtil mu = mongoTemplate.findAndModify(Query.query(Criteria.where("name").is(zlass.getName())),
                new Update().inc("seq", 1),
                MongoUtil.class);
        if (mu == null) {
            mongoTemplate.insert(new MongoUtil(1L, zlass.getName()));
            return 1L;
        }
        return mu.getSeq() + 1L;
    }

    public static <T> Boolean insert(MongoTemplate mongoTemplate, T t) {
        try {
            Long id = MongoUtil.getId(mongoTemplate, t.getClass());
            Method setId = t.getClass().getMethod("setId",Long.class);
            setId.invoke(t,id);
            mongoTemplate.insert(t);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static <T> Boolean delete(MongoTemplate mongoTemplate,T t) {
        try {
            mongoTemplate.remove(t, t.getClass().getSimpleName().toLowerCase());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Object getOne(MongoTemplate mongoTemplate, Long id, Class z) {
        return mongoTemplate.find(new Query(Criteria.where("id").is(id)), z).get(0);
    }

    public static<T> List<T> list(MongoTemplate mongoTemplate, Class<T> z) {
        return mongoTemplate.find(null, z);
    }

    public static <T> Boolean update(MongoTemplate mongoTemplate, T t) {
        try {
            Update update = new Update();
            Method[] methods = t.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().substring(0, 3).equals("get")) {
                    Object o = method.invoke(t);
                    String key = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
                    if (o != null&&!key.equals("class"))
                        update.set(key, o);
                }
            }
            mongoTemplate.findAndModify(new Query(Criteria.where("id").is(t.getClass().getMethod("getId").invoke(t))),
                    update, t.getClass());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
