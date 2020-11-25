package com.mica.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {


    public static void main(String[] args) {
        Entity entity = new Entity(1, "dx", true);
        System.out.println(entity.getAge());
        System.out.println(entity.getName());
        LearnDemo demo = new LearnDemo();
        Class<?> clazz = demo.getClass();
        try {
            Method change = clazz.getDeclaredMethod("change", Entity.class);
            change.setAccessible(true);
            // LearnDemo learnDemo = (LearnDemo) clazz.newInstance();
            // change.invoke(learnDemo, entity);

            Field nice = clazz.getDeclaredField("nice");
            nice.setAccessible(true);
            System.out.println(nice.getInt(clazz.newInstance()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println(entity.getAge());
        System.out.println(entity.getName());

    }
}
