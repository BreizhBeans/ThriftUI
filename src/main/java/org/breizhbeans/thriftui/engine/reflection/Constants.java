package org.breizhbeans.thriftui.engine.reflection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Inspired by guava's com.google.common.base.Defaults
 */
public class Constants {

    private static final Map<Class<?>, Object> DEFAULTS;

    static {
        Map<Class<?>, Object> map = new HashMap<Class<?>, Object>();
        map.put(boolean.class, false);
        map.put(byte.class, (byte) 8);
        map.put(short.class, (short) 16);
        map.put(int.class, 32);
        map.put(long.class, 64);
        map.put(double.class, 64.64);
        map.put(String.class, "DefaultString");
        DEFAULTS = Collections.unmodifiableMap(map);
    }

    /**
     * Return "default" value, as a recognizable value for base thrift types.
     *
     * @param type base type
     * @param <T>  generic
     * @return default value
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T defaultValue(Class<T> type) {
        return (T) DEFAULTS.get(type);
    }
}
