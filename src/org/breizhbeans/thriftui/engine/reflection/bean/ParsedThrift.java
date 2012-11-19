package org.breizhbeans.thriftui.engine.reflection.bean;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Pascal.Lombard
 * Date: 18/11/12
 * Time: 00:15
 * To change this template use File | Settings | File Templates.
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
        StringBuilder result = new StringBuilder();

        result.append("{namespace:");
        if (this.namespace != null) {
            result.append(namespace);
        } else {
            result.append("\'\'");
        }

        if (this.structures != null && !this.structures.isEmpty()) {
            result.append(", structures {");
            for (String key : this.structures.keySet()) {
                result.append(key);
                result.append(":");
                result.append(this.structures.get(key).getCanonicalName());
                result.append(",");
            }
            result.append("}");
        }

        if (this.services != null && !this.services.isEmpty()) {
            result.append(", services {");
            for (String key : this.services.keySet()) {
                result.append(key);
                result.append(":");
                result.append(this.services.get(key).getCanonicalName());
                result.append(",");
            }
            result.append("}");
        }

        if (this.exceptions != null && !this.exceptions.isEmpty()) {
            result.append(", exceptions {");
            for (String key : this.exceptions.keySet()) {
                result.append(key);
                result.append(":");
                result.append(this.exceptions.get(key).getCanonicalName());
                result.append(",");
            }
            result.append("}");
        }

        result.append("}");
        return result.toString();
    }
}
