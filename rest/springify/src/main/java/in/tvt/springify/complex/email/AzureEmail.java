package in.tvt.springify.complex.email;

import org.springframework.stereotype.Component;

@Component("azure")
public class AzureEmail implements EmailService{

	@Override
	public void sendEmail(String to, String msg) {
		System.out.println("Azure");
		System.out.println("Email to: " + to);
		System.out.println("Email msg: " + msg);
	}

}