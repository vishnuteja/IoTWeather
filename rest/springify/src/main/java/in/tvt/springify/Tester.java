package in.tvt.springify;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Tester {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		
		Hello hello = context.getBean(Hello.class);
		hello.sayHello();
		
		Hello hello1 = context.getBean(Hello.class);
		hello1.sayHello();
		
		System.out.println(hello == hello1);
		
		context.close();
	}

}
