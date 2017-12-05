package in.tvt.springify.complex;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Tester {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		
		NotificationService service = context.getBean(NotificationService.class);
		service.sendNotification("vishnutejasun@gmail.com", "Good Luck !");
		
		context.close();
		
	}

}
