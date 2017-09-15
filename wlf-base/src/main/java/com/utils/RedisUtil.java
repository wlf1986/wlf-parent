package com.utils;

import com.common.PageUtil;
import com.common.RedisKey;
import com.user.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author 王岚枫
 * @Datetime 2017年08月19日 14:41
 */
public final class RedisUtil {

    /**
     * Redis 获取主键值
     *
     * @param redisTemplate
     *
     * @return
     */
    private static Long getId(RedisTemplate redisTemplate) {
        return (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.incr(redisTemplate.getStringSerializer().serialize(User.class.getName()));
            }
        });
    }

    /**
     * Redis 添加
     *
     * @param t
     * @param redisTemplate
     * @param primaryKey
     * @param <T>
     *
     * @return
     */
    public static <T> T add(T t, RedisTemplate redisTemplate, String primaryKey) {
        Long id = getId(redisTemplate);
        try {
            if ((Long) t.getClass().getMethod("get" + primaryKey.substring(0,
                    1).toUpperCase() + primaryKey.substring(1)).invoke(t) == null)
                t.getClass().getMethod("set" + primaryKey.substring(0, 1).toUpperCase() + primaryKey.substring(1),
                        Long.class).invoke(t, id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        byte[] key = redisTemplate.getStringSerializer().serialize(String.format(RedisKey.MODEL_KEY,
                t.getClass().getName(),
                id));
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, redisTemplate.getValueSerializer().serialize(t));
                connection.zAdd(redisTemplate.getStringSerializer().serialize(String.format(RedisKey.MODEL_ALL,
                        t.getClass().getName())), id, key);
                return null;
            }
        });
        return t;
    }

    /**
     * Redis 删除
     *
     * @param redisTemplate
     * @param id
     * @param zlass
     */
    public static void delete(RedisTemplate redisTemplate, Long id, Class zlass) {
        byte[] key = redisTemplate.getStringSerializer().serialize(String.format(RedisKey.MODEL_KEY,
                zlass.getName(),
                id));
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(key);
                connection.zRem(redisTemplate.getStringSerializer().serialize(String.format(RedisKey.MODEL_ALL,
                        zlass.getName())), id.toString().getBytes(), key);
                return null;
            }
        });
    }


    /**
     * Redis 更新
     *
     * @param t
     * @param redisTemplate
     * @param primaryKey
     * @param <T>
     *
     * @return
     */
    public static <T> T update(T t, RedisTemplate redisTemplate, String primaryKey) {
        try {
            Long id = (Long) t.getClass().getMethod("get" + primaryKey.substring(0,
                    1).toUpperCase() + primaryKey.substring(1)).invoke(t);
            byte[] key = redisTemplate.getStringSerializer().serialize(String.format(RedisKey.MODEL_KEY,
                    t.getClass().getName(),
                    id));
            redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    T t1 = (T) redisTemplate.getValueSerializer().deserialize(connection.get(key));
                    BeanUtil.copyProperty(t, t1);
                    connection.set(key, redisTemplate.getValueSerializer().serialize(t1));
//                connection.zAdd(redisTemplate.getStringSerializer().serialize(String.format(RedisKey.MODEL_ALL,t.getClass().getName())),id, key);
                    return null;
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * Redis 获取单个对象
     *
     * @param id
     * @param redisTemplate
     * @param zlass
     * @param <T>
     *
     * @return
     */
    public static <T> T getOne(Long id, RedisTemplate redisTemplate, Class zlass) {
        byte[] key = redisTemplate.getStringSerializer().serialize(String.format(RedisKey.MODEL_KEY,
                zlass.getName(),
                id));
        return (T) redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                T t = (T) redisTemplate.getValueSerializer().deserialize(connection.get(key));
                return t;
            }
        });
    }

    /**
     * Redis 获取列表
     *
     * @param redisTemplate
     * @param zlass
     * @param <T>
     *
     * @return
     */
    public static <T> List<T> getList(RedisTemplate redisTemplate, Class zlass) {
        List<T> list = new ArrayList<T>();
        Page page = PageUtil.getPage();
        redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                page.setPageTotal(connection.zCard(redisTemplate.getStringSerializer().serialize(String.format(RedisKey.MODEL_ALL,
                        zlass.getName()))));
                Set<byte[]> bytes = connection.zRange(redisTemplate.getStringSerializer().serialize(
                        String.format(RedisKey.MODEL_ALL, zlass.getName())),
                        page.getPageStar(),
                        page.getPageStar() + page.getPageSize() - 1);
                for (byte[] b : bytes) {
                    list.add((T) redisTemplate.getValueSerializer().deserialize(connection.get(b)));
                }
                return null;
            }
        });
        return list;
    }
}
