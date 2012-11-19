package test.org.breizhbeans.thriftui.engine.reflection;

import junit.framework.TestCase;
import org.breizhbeans.thriftui.engine.reflection.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Pascal.Lombard
 * Date: 18/11/12
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */
public class ConstantsTest extends TestCase {
    public void testGetDefault() throws Exception {
        Class<?> classe = Class.forName("org.breizhbeans.thriftui.storage.Row");

        System.out.println(classe.getField("key").getType());

        assertEquals(Constants.DEFAULT_I32, Constants.getDefault(classe.getField("key").getType()));
        assertEquals(Constants.DEFAULT_STRING,Constants.getDefault(classe.getField("value").getType()));
    }
}
