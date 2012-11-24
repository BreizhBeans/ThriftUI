package test.org.breizhbeans.thriftui.engine.reflection;

import junit.framework.TestCase;
import org.breizhbeans.thriftui.engine.reflection.ThriftAnalyzer;
import org.breizhbeans.thriftui.engine.reflection.bean.ParsedThrift;

import java.util.jar.JarFile;

/**
 * Created with IntelliJ IDEA.
 * User: Pascal.Lombard
 * Date: 18/11/12
 * Time: 16:25
 */
public class ThriftAnalyzerTest extends TestCase {
    public void testFindClassesInJar() throws Exception {
        ParsedThrift parsedThrift = ThriftAnalyzer.findClassesInJar(new JarFile("lib/thriftui-storage.jar"));
        System.out.println(parsedThrift.toString());
        assertNotNull(parsedThrift);
    }
}
