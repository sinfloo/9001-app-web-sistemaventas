package sendemail;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modeloDTO.Cliente;

public class SendEmailUsingGMailSMTP {
    
    private static final String HOST="smtp.gmail.com";
    private static final String PORT="587";
    private static final String FROM="";
    private static final String PASSWORD="";

    public static int sendEmail(String asunto, String msg,List<Cliente>clientes) {
        int r=0;
        //Get properties object    
        Properties props = new Properties();
        
        props.put("mail.smtp.auth", "true");        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

   
        //get Session   
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        });
        //compose message    
        try {
            MimeMessage message = new MimeMessage(session);
            String correos = "";
            String separator = "";
            for (Cliente cliente : clientes) {
                if(clientes.size()>1){
                    if(!correos.equals("")){
                        separator=",";
                    }
                    correos=correos.concat(separator+cliente.getDir());
                }else{
                    correos=cliente.getDir();
                }
                
            }
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(correos));

            message.setSubject(asunto);
            message.setText(msg);

            //send message  
            Transport.send(message);
            r=1;
            System.out.println("message sent successfully");
            
        } catch (MessagingException e) {
            r=0;
            throw new RuntimeException(e);
        }
        
        return r;

    }

}
