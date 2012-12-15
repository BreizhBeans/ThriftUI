package org.breizhbeans.thriftui.engine.reflection.bean;

import com.google.common.base.Objects;
import org.apache.thrift.TServiceClient;

import java.util.HashMap;

/**
 * Container class for the parsed thrift package/jar.
 */
public class ParsedThrift {

    public final HashMap<String, Class<?>> structures;
    public final HashMap<String, Class<?>> enums;
    public final HashMap<String, Class<?>> services;
    public final HashMap<String, Class<TServiceClient>> clients;
    public final HashMap<String, Class<?>> exceptions;
    public String namespace;

    public ParsedThrift() {
        this.structures = new HashMap<String, Class<?>>();
        this.enums = new HashMap<String, Class<?>>();
        this.services = new HashMap<String, Class<?>>();
        this.clients = new HashMap<String, Class<TServiceClient>>();
        this.exceptions = new HashMap<String, Class<?>>();
        this.namespace = null;
    }


    @Override
    public String toString() {
        // Let's try this cool guava helper
        return Objects.toStringHelper(this)
                .omitNullValues()
                .add("namespace", namespace)
                .add("structures", structures)
                .add("enums", enums)
                .add("services", services)
                .add("clients", clients)
                .add("exceptions", exceptions)
                .toString();
    }
}
