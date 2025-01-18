
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectionTest {
	public static void main(String[] args) throws Exception{
		Class<?> keywordclass = Class.forName("TargetClass");
		System.out.println(keywordclass.getName());
		
		Method[] methods = keywordclass.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		
		Constructor<?> cont = keywordclass.getConstructor();
		Object keywordlib = cont.newInstance();
		
		Method navigate = keywordclass.getMethod("navigate", String.class);
		
		String url = "https://google.com";
		navigate.invoke(keywordlib, url);
		
		Method quit = keywordclass.getMethod("quit");
		quit.invoke(keywordlib);
	}
}
