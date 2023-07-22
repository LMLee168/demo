package com.demo.common.cache;

import com.alibaba.fastjson.util.TypeUtils;
import com.demo.common.inter.CacheInvocation;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CacheTemplate {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void setValue(String key,String value, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(key,value, timeout, unit);
    }

    public void setValue(String key,String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Long increment(String key, Long size){
        return redisTemplate.opsForValue().increment(key,size);
    }

    public String getIncrValue(String key){
        return redisTemplate.boundValueOps(key).get(0,-1);
    }

    public String getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void expire(String key, long timeout, TimeUnit unit){
        redisTemplate.expire(key,timeout,unit);
    }

    public Long getExpire(String key, TimeUnit unit){
        return redisTemplate.getExpire(key, unit);
    }

    public String get(String key, long second, CacheInvocation cacheInvocation) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String json = operations.get(key);
        if (json != null) {
            log.info("get cache! key = " + key);
            return json;
        }
        synchronized (this) {
            json = operations.get(key);
            if (json != null) {
                log.info("get cache! key = " + key);
                return json;
            }
            json = cacheInvocation.invoke();
            if (json != null) {
                operations.set(key, json);
                redisTemplate.expire(key, second, TimeUnit.SECONDS);
            }
            return json;
        }
    }

    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /**
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    //======================set存储结构=====================
    /**
     * 新增一个  sadd
     *
     * @param key
     * @param value
     */
    public void sadd(String key, String... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public void smadd(String key, String... value ) {
        redisTemplate.opsForSet().add(key, value);
    }

    public void saddEx(String key, String value, Long expire) {
        redisTemplate.opsForSet().add(key, value);
        redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
    }

    /**
     * 删除集合中的值  srem
     *
     * @param key
     * @param value
     */
    public void srem(String key, String... value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    /**
     * 判断是否包含  sismember
     *
     * @param key
     * @param value
     */
    public boolean sismember(String key, String value) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
    }

    /**
     * 获取集合中所有的值 smembers
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 集合个数
     * @param key
     * @return
     */
    public Long sSize(String key){
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * Remove and return {@code count} random members from set at {@code key}.
     * @param key
     * @param count
     * @return
     */
    public List<String> pop(String key, Integer count) {
        return redisTemplate.opsForSet().pop(key, count);
    }

    //======================zset存储结构=====================

    /**
     * 新增一个  zadd
     *
     * @param key
     * @param value
     */
    public void zadd(String key, String value,Long score) {
        redisTemplate.opsForZSet().add(key, value,score);
    }

    /**
     * 批量存
     */
    public void zbatchAdd(String key, Map<String,String> data){

        Set<ZSetOperations.TypedTuple<String>> dataSet = new HashSet<>();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>(entry.getKey(),
                    TypeUtils.castToDouble(entry.getValue()));
            dataSet.add(typedTuple);
        }

        if (CollectionUtils.isNotEmpty(dataSet)) {
            redisTemplate.opsForZSet().add(key, dataSet);
        }
    }

    /**
     * 删除一个  zadd value
     *
     * @param key
     * @param value
     */
    public void zremove(String key, String value) {
        redisTemplate.opsForZSet().remove(key,value);
    }

    /**
     * 删除多个
     * @param key
     * @param values
     */
    public void zremoveMulti(String key, Object... values) {
        redisTemplate.opsForZSet().remove(key,values);
    }

    /**
     * 删除一个分数范围
     * @param key
     * @param start
     * @param end
     */
    public void zremoveRangeByScore(String key, long start,long end) {
        redisTemplate.opsForZSet().removeRangeByScore(key, start, end);
    }

    /**
     * 通过下标批量删除
     * @param key
     * @param start
     * @param end
     */
    public void zremoveRange(String key, long start, long end){
        redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * score区间取值 从小到大
     * @param key
     * @param
     * @return
     */
    public Set<String> zRangeByScore(String key, long min, long max){
        return redisTemplate.opsForZSet().rangeByScore(key,min,max);
    }

    public Double zScore(String key, String val) {
        return redisTemplate.opsForZSet().score(key, val);
    }

    /**
     * score区间取值 从大到小
     * @param key
     * @param
     * @return
     */
    public Set<String> zReverseRangeByScore(String key, long max, long min){
        return redisTemplate.opsForZSet().reverseRangeByScore(key,max,min);
    }

    public List<String> reverseRange(String key, long start, long end) {
        Set<String> set = redisTemplate.opsForZSet().reverseRange(key, start, end);
        if (CollectionUtils.isEmpty(set)) {
            return Lists.newArrayList();
        }
        List<String> childIds = new ArrayList<>(set);
        return childIds;
    }

    public Long zrank(String key, String value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }


    /**
     * 时间区间取值
     * @param key
     * @param timestampPartition
     * @return
     */
    public List<String>  zRangeByScore(String key, Long startTime, Long timestampPartition, int size){
        Set<String> set = redisTemplate.opsForZSet().rangeByScore(key, startTime, timestampPartition);
        if (CollectionUtils.isEmpty(set)) {
            return Lists.newArrayList();
        }

        List<String> childIds = new ArrayList<>(set);
        int firstIndex = 0;
        if ( size != 0 &&childIds.size() > size) {
            firstIndex = childIds.size() - size;
        }
        return childIds.subList(firstIndex, childIds.size());
    }

    //取所有
    public List<String> zrange(String key){
        Set<String> set = redisTemplate.opsForZSet().range(key,0,-1);
        if (CollectionUtils.isEmpty(set)) {
            return Lists.newArrayList();
        }
        List<String> values = new ArrayList<>(set);
        return values;
    }

    public List<String> zrange(String key, long start, long end){
        Set<String> set = redisTemplate.opsForZSet().range(key,start,end);
        if (CollectionUtils.isEmpty(set)) {
            return Lists.newArrayList();
        }
        List<String> values = new ArrayList<>(set);
        return values;
    }

    public Set<String> zrangeByScore(String key, double min, double max, long offset, long count){
        return redisTemplate.opsForZSet().rangeByScore(key,min,max,offset,count);
    }

    public Set<String> zReverseRangeByScore(String key, double min, double max, long offset, long count){
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, offset, count);
    }

    public Long zcount(String key, double start, double end){
        return redisTemplate.opsForZSet().count(key, start, end);
    }

    /**
     * 有序集 key 的元素成员的个数
     * @param key
     * @return
     */
    public Long zcard(String key){
        return redisTemplate.opsForZSet().zCard(key);
    }

    public Map<Long, Long> rangeByScoreWithScores(String key, long start,long end){
        Set<ZSetOperations.TypedTuple<String>> ids = redisTemplate.opsForZSet().rangeByScoreWithScores(key, start, end);
        Map<Long, Long> childScoreMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(ids)) {
            return childScoreMap;
        }
        for (ZSetOperations.TypedTuple<String> entry : ids){
            childScoreMap.put(Long.parseLong(entry.getValue()), entry.getScore().longValue());
        }
        return childScoreMap;
    }

    public Set<ZSetOperations.TypedTuple<String>> zrevrangeWithScores(String key){
        return redisTemplate.opsForZSet().reverseRangeWithScores(key,0, -1);
    }

    //======================Map存储结构=====================

    public void hdel(String key, Object... mapKey){
        redisTemplate.opsForHash().delete(key, mapKey);
    }

    public void hadd(String key, Object mapKey, Object mapValue){
        redisTemplate.opsForHash().put(key, mapKey, mapValue);
    }

    public void hexpire(String key, long time){
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 获取map 中的value
     * @param key
     * @param filed  map中的key
     * @return
     */
    public  Object hget(String key, Object filed){
        return redisTemplate.opsForHash().get(key, filed);
    }

    public Boolean hashPutIfAbsent(String key, Object hashKey, Object value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /**
     * 某个mapKey 的value是否存在
     * @param key
     * @param filed
     * @return
     */
    public boolean hexists(String key, Object filed){
        return redisTemplate.opsForHash().hasKey(key, filed);
    }

    public void hincrBy(String key, Object filed, Long mapValue){
        redisTemplate.opsForHash().increment(key,filed,mapValue);
    }

    /**
     * 多个存储
     */
    public void hmadd(String key, Map map){
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     *   获取所有键值
     */
    public Map hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    //============================list存储结构=================

    public void lmadd(String key, List list){
        redisTemplate.opsForList().rightPushAll(key, list);
    }

    public Long rpush(String key, String value){
        return  redisTemplate.opsForList().rightPush(key, value);
    }

    public String lpop(String key){
        return redisTemplate.opsForList().leftPop(key);
    }

    public void rpushAll(String key, List<String> values){
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    public void ldel(String key){
        redisTemplate.delete(key);
    }

    public void lremove(String key, long count, Object value){
        redisTemplate.opsForList().remove(key,count,value);
    }

    public List lmget(String key){
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public List lrange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public String lbpop(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    public Long llen(String key){
        return redisTemplate.opsForList().size(key);
    }



    /**
     * 加锁
     * @param key - 商品的唯一标志
     * @param expiredTimeMillis  当前时间+超时时间 也就是时间戳
     * @return
     */
    public boolean tryLock(String key,long expiredTimeMillis){
        String value = expiredTimeMillis+"";
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){//对应setnx命令
            return true;
        }
        //判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        String currentValue = redisTemplate.opsForValue().get(key);
        //如果锁过期
        if(StringUtils.isNotBlank(currentValue) && NumberUtils.toLong(currentValue) < System.currentTimeMillis()){//currentValue不为空且小于当前时间
            //获取上一个锁的时间value
            String oldValue =redisTemplate.opsForValue().getAndSet(key,value);//对应getset，如果key存在
            //假设两个线程同时进来这里，因为key被占用了，而且锁过期了。获取的值currentValue=A(get取的旧的值肯定是一样的),两个线程的value都是B,key都是K.锁时间已经过期了。
            //而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的value已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            if(StringUtils.isNotBlank(oldValue) && oldValue.equals(currentValue) ){
                //oldValue不为空且oldValue等于currentValue，也就是校验是不是上个锁的时间戳，也是防止并发
                return true;
            }
        }
        return false;
    }


    /**
     * 解锁
     * @param key
     * @param expiredTimeMillis
     */
    public void unLock(String key,long expiredTimeMillis){
        try {
            String value = expiredTimeMillis+"";
            String currentValue = redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value) ){
                redisTemplate.opsForValue().getOperations().delete(key);//删除key
            }
        } catch (Exception e) {
            log.error("[Redis分布式锁] 解锁出现异常了，{}",e);
        }
    }

    /**
     * 重命名key，如果newKey已经存在，则newKey的原值被覆盖
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

}
