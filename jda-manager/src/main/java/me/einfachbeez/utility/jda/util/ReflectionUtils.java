package me.einfachbeez.utility.jda.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * @author EinfachBeez | https://github.com/EinfachBeez
 */
public class ReflectionUtils {

    /**
     * Creates instance of given Class
     *
     * @param clazz Command Class
     * @return Instance of generic object
     */
    public static Object createInstance(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException,
                                                               InstantiationException, IllegalAccessException {
        if (Modifier.isAbstract(clazz.getModifiers())) return null;
        List<Class<?>> params;

        for (Constructor<?> constructor : clazz.getConstructors()) {
            params = Arrays.asList(constructor.getParameterTypes());
            if (params.isEmpty()) return clazz.getConstructor().newInstance();
            else throw new IllegalArgumentException("Cannot access constructor. Types: " + params);
        }
        return null;
    }

    /**
     * Checks if the root class implements a certain class
     *
     * @param root Base Class
     * @param impClass Implementation Class
     * @return Boolean value by checking the implementation class implemented in root class
     */
    public static boolean isImplemented(Class<?> root, Class<?> impClass) {
        return impClass.isAssignableFrom(root);
    }
}
