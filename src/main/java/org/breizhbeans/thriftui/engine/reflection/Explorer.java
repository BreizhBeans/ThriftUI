package org.breizhbeans.thriftui.engine.reflection; /**
 * Created with IntelliJ IDEA.
 * User: Pascal.Lombard
 * Date: 11/11/12
 * Time: 21:40
 */

import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.breizhbeans.thriftui.engine.reflection.bean.ParsedThrift;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.jar.JarFile;

public class Explorer {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, TTransportException {
        // Open the .jar
        // Extract the thrift structures+exceptions+services
        ParsedThrift parsedThrift = ThriftAnalyzer.findClassesInJar(new JarFile(args[0]));

        // Create an dummy instance of every thrift structure found
        // and store it in a big fat map
        HashMap<String, Object> structures = getStructures(parsedThrift);

        // For each service found, create a dummy client
        // and invoke their methods
        for (String key : parsedThrift.services.keySet()) {

            // Preparing the protocol for invocation
            TTransport transport;
            transport = new TSocket("localhost", 9090);
            try {
                transport.open();

                TProtocol protocol = new TBinaryProtocol(transport);

                Class<?> serviceClass = parsedThrift.services.get(key);
                TServiceClient client = getClient(serviceClass, protocol);
                runClient(client, structures);

            } catch (TTransportException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                transport.close();
            }
        }

    }

    /**
     * For each method of the client which *looks like* a service invocation,
     * get the necessary instances in the structures map and fire the invoke.
     *
     * @param client     the client to explore
     * @param structures the ParsedThrift structures to use
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void runClient(TServiceClient client, HashMap<String, Object> structures) throws IllegalAccessException, InvocationTargetException {
        for (Method method : client.getClass().getDeclaredMethods()) {
            // for each method, retrieve its parameters
            // and invoke with default values.
            // Note that send_, recv_ and Client methods are
            // of no interest here (thrift internal plumbering).
            if (method.getName().startsWith("send_")
                    || method.getName().startsWith("recv_")
                    || method.getName().equalsIgnoreCase("Client")) {
                continue;
            }

            Class<?>[] parameters = method.getParameterTypes();
            int arraySize = parameters.length;
            Object[] defaults = new Object[arraySize];
            int i = 0;
            for (Class<?> parameter : parameters) {
                // if the parameter is a thrift structure,
                // we're likely to have it in our Map
                if (structures.containsKey(parameter.getSimpleName())) {
                    defaults[i] = structures.get(parameter.getSimpleName());
                } else {
                    // or it is a base type
                    defaults[i] = Constants.getDefault(parameter);
                }
                // or... we're screwed with the current org.breizhbeans.thriftui.engine.reflection.Constants.getDefault().
                // TODO : unscrew ourselves.
                i++;
            }

            method.invoke(client, defaults);
        }
    }

    /**
     * Mince, il n'y aurait pas un moyen plus simple et élégant de faire ça ?
     *
     * @param serviceClass a Thrift Service Class
     * @param protocol     an instance of a TProtocol
     * @return An instance of the Client class to the Service, using the protocol
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static TServiceClient getClient(Class<?> serviceClass, TProtocol protocol)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class[] classes = serviceClass.getClasses();
        TServiceClient client = null;

        for (Class classe : classes) {
            if ("Client".equalsIgnoreCase(classe.getSimpleName())) {
                @SuppressWarnings("unchecked")
                Constructor<TServiceClient> constructor = classe.getConstructor(protocol.getClass().getSuperclass());
                client = constructor.newInstance(protocol);
                break;
            }
        }

        return client;
    }

    /**
     * Create a default instance of every structure discovered in the thrift classes
     *
     * @param parsedThrift the container of evrything discovered in the thrift generated classes.
     * @return an HashMap with key = structure name and value = structure default instance.
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    private static HashMap<String, Object> getStructures(ParsedThrift parsedThrift)
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HashMap<String, Object> result = new HashMap<String, Object>();

        for (String key : parsedThrift.structures.keySet()) {
            Class<?> structure = parsedThrift.structures.get(key);
            // First, find the structure's fields
            int arraySize = structure.getFields().length - 1; // expected number of fields, minus metadataMap
            Object[] defaults = new Object[arraySize];
            Class<?>[] constructorParameters = new Class<?>[arraySize];
            int i = 0; // let's party like it's 1994 !
            for (Field field : structure.getFields()) {
                if (!"metaDataMap".equalsIgnoreCase(field.getName())) {
                    defaults[i] = Constants.getDefault(field.getType());
                    constructorParameters[i] = field.getType();
                    i++;
                }
            }

            // Then, instantiate the structure
            Constructor constructor = structure.getConstructor(constructorParameters);
            Object instance = constructor.newInstance(defaults);
            result.put(key, instance);
        }

        return result;
    }
}
