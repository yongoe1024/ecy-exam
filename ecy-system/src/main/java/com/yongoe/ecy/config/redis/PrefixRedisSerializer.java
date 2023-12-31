package com.yongoe.ecy.config.redis;

import org.springframework.data.redis.serializer.StringRedisSerializer;

public class PrefixRedisSerializer extends StringRedisSerializer {

    public static final String PREFIX_KEY = "ecy-exam:";

    /**
     * 序列化
     *
     * @param s key
     * @return 结果
     */
    @Override
    public byte[] serialize(String s) {

        if (s == null) {
            return new byte[0];
        }
        String realKey = PREFIX_KEY + s;
        return super.serialize(realKey);
    }

    /**
     * 反序列化
     *
     * @param bytes 数据
     * @return 结果
     */
    @Override
    public String deserialize(byte[] bytes) {
        String cacheKey = new String(bytes);
        int indexOf = cacheKey.indexOf(PREFIX_KEY);
        if (indexOf == -1) {
            cacheKey = PREFIX_KEY + cacheKey;
        }
        return cacheKey;
    }
}