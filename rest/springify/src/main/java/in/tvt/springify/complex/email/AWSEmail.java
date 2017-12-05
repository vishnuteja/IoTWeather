package in.tvt.springify.complex.email;

import org.springframework.stereotype.Component;

@Component("aws")
public class AWSEmail implements EmailService{

	@Override
	public void sendEmail(String to, String msg) {
		System.out.println("AWS");
		System.out.println("Email to: " + to);
		System.out.println("Email msg: " + msg);
	}

}
