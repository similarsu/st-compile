package cn.st.compile;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    private static String classPath = System.getProperty("user.dir") + File.separator + "myFolder";

    public static void main(String[] args) {
        String fullClassName = "MyObj";

        String code =
                "public class MyObj implements cn.st.compile.MyInterface{public void sayHello(){System.out.println(666);}}";
        String code_2 =
                "public class MyObj implements cn.st.compile.MyInterface{public void sayHello(){System.out.println(777);}}";
        String code_3 =
                "public class MyObj implements cn.st.compile.MyInterface{public void sayHello(){System.out.println(888);}}";
        load(code, fullClassName);
        load(code_2, fullClassName);
        load(code_3, fullClassName);
    }

    private static void load(String code, String fullClassName) {
        new MyClassCompiler(fullClassName, code).compile();

        try {
            MyInterface myObj =
                    (MyInterface) new MyClassLoader().loadClass(fullClassName).newInstance();
            myObj.sayHello();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void load2(String code, String fullClassName) {
        new MyClassCompiler(fullClassName, code).compile();

        try {
            URLClassLoader urlClassLoader =
                    new URLClassLoader(new URL[] {new File(classPath).toURI().toURL()});
            MyInterface myObj = (MyInterface) urlClassLoader.loadClass(fullClassName).newInstance();
            myObj.sayHello();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
