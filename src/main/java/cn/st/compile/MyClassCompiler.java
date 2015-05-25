package cn.st.compile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class MyClassCompiler {
	private String simpleClassName;
	private String code;
	private String classPath = System.getProperty("user.dir") + File.separator
			+ "myFolder";

	public MyClassCompiler(String simpleClassName, String code) {
		this.simpleClassName = simpleClassName;
		this.code = code;
	}

	public boolean compile() {
		try {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			JavaFileObject javaFile = new SimpleJavaFileObject(new URI(
					simpleClassName + ".java"), Kind.SOURCE) {
				@Override
				public CharSequence getCharContent(boolean arg)
						throws IOException {
					return code;
				}
			};
			CompilationTask task = compiler.getTask(null, null, null,
					Arrays.asList("-d", classPath), null,
					Arrays.asList(javaFile));
			return task.call();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getSimpleClassName() {
		return simpleClassName;
	}

	public void setSimpleClassName(String simpleClassName) {
		this.simpleClassName = simpleClassName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
}
