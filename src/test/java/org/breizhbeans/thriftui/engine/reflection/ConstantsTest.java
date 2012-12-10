package org.breizhbeans.thriftui.engine.reflection;

import junit.framework.TestCase;

/**
 * User: Pascal.Lombard
 * Date: 18/11/12
 * Time: 22:19
 */
public class ConstantsTest extends TestCase {
    public void testGetDefault() throws Exception {
        Class<?> classe = Class.forName("org.breizhbeans.thriftui.storage.Row");

        System.out.println(classe.getField("key").getType());
        Constants.defaultValue(classe.getField("key").getType());

        assertEquals(32, Constants.defaultValue(classe.getField("key").getType()));
        assertEquals("DefaultString", Constants.defaultValue(classe.getField("value").getType()));
    }
}
