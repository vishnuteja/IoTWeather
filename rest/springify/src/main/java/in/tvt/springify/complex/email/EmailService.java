package in.tvt.springify.complex.email;

import org.springframework.stereotype.Component;

@Component
public interface EmailService {

	public void sendEmail(String to, String msg);
	
}
