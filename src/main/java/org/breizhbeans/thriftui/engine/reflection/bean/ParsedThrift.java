package org.breizhbeans.thriftui.engine.reflection.bean;

import com.google.common.base.Objects;

import java.util.HashMap;

/**
 * Container class for the parsed thrift package/jar.
 * <p/>
 * User: Pascal.Lombard
 * Date: 18/11/12
 * Time: 00:15
 */
public class ParsedThrift {

    public final HashMap<String, Class<?>> structures;
    public final HashMap<String, Class<?>> services;
    public final HashMap<String, Class<?>> exceptions;
    public String namespace;

    public ParsedThrift() {
        this.structures = new HashMap<String, Class<?>>();
        this.services = new HashMap<String, Class<?>>();
        this.exceptions = new HashMap<String, Class<?>>();
        this.namespace = null;
    }


    @Override
    public String toString() {
        // Let's try this cool new guava helper
        return Objects.toStringHelper(this)
                .omitNullValues()
                .add("namespace", namespace)
                .add("structures", structures)
                .add("services", services)
                .add("exceptions", exceptions)
                .toString();
        // Wait, is that it ? :D
    }
}
