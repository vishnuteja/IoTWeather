package in.tvt.springify.complex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import in.tvt.springify.complex.email.EmailService;

@Component
public class NotificationService {

	@Autowired
	@Qualifier("azure")
	private EmailService emailService;
	
//	@Autowired
//	public NotificationService(EmailService emailService) {
//		this.emailService = emailService;
//	}
	
	public void sendNotification(String to, String msg) {
		emailService.sendEmail(to, msg);
	}
}
