package org.breizhbeans.thriftui.engine.reflection;

/**
 * Created with IntelliJ IDEA.
 * User: Pascal.Lombard
 * Date: 18/11/12
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"WeakerAccess", "UnusedDeclaration", "SameReturnValue"})
public class Constants {

    public static final boolean DEFAULT_BOOL = true;
    public static final byte DEFAULT_BYTE = 8;
    public static final short DEFAULT_I16 = 16;
    public static final int DEFAULT_I32 = 32;
    public static final long DEFAULT_I64 = 64;
    public static final double DEFAULT_DOUBLE = 64.64;
    public static final String DEFAULT_STRING = "DefaultString";


    // Those "if" conditions are pretty ugly,
    // but I didn't manage to get a more elegant method to work.
    public static Object getDefault(Class<?> param) {
        if (param.getName().equalsIgnoreCase("boolean")) {
            return DEFAULT_BOOL;
        }
        if (param.getName().equalsIgnoreCase("byte")) {
            return DEFAULT_BYTE;
        }
        if (param.getName().equalsIgnoreCase("short")) {
            return DEFAULT_I16;
        }
        if (param.getName().equalsIgnoreCase("int")) {
            return DEFAULT_I32;
        }
        if (param.getName().equalsIgnoreCase("long")) {
            return DEFAULT_I64;
        }
        if (param.getName().equalsIgnoreCase("double")) {
            return DEFAULT_DOUBLE;
        }
        if (param.getName().equalsIgnoreCase("java.lang.String")) {
            return DEFAULT_STRING;
        }
        return DEFAULT_STRING;
    }

    public static Object getDefault(String param) {
        return DEFAULT_STRING;
    }

    public static Object getDefault(int _param) {
        return DEFAULT_I32;
    }

    public static Object getDefault(boolean _param) {
        return DEFAULT_BOOL;
    }

    public static Object getDefault(byte _param) {
        return DEFAULT_BYTE;
    }

    public static Object getDefault(short _param) {
        return DEFAULT_I16;
    }

    public static Object getDefault(long _param) {
        return DEFAULT_I64;
    }

    public static Object getDefault(double _param) {
        return DEFAULT_DOUBLE;
    }
}
