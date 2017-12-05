package in.tvt.springify;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Hello {
	
	private String desc;
	
	public Hello(String ver)
	{
		desc = ver;
	}
	
	public void sayHello()
	{
		System.out.println("Hello there Spring " + desc);
	}

}
