package test.org.breizhbeans.thriftui.engine.reflection;

import junit.framework.TestCase;
import org.breizhbeans.thriftui.engine.reflection.org.breizhbeans.thriftui.engine.reflection.bean.ParsedThrift;
import org.breizhbeans.thriftui.engine.reflection.ThriftAnalyzer;

import java.util.jar.JarFile;

/**
 * Created with IntelliJ IDEA.
 * User: Pascal.Lombard
 * Date: 18/11/12
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
public class ThriftAnalyzerTest extends TestCase {
    public void testFindClassesInJar() throws Exception {
        ParsedThrift parsedThrift = ThriftAnalyzer.findClassesInJar(new JarFile("lib/thriftui-storage.jar"));
        System.out.println(parsedThrift.toString());
        assertNotNull(parsedThrift);
    }
}
