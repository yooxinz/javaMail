import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by star on 2017/6/2.
 */
public class MailUtil {

    public void sendEmail(String [] receivers, String message) {

        // 发件人电子邮箱
        String from = "发送者@qq.com";

        // 指定发送邮件的主机为 localhost
        String host = "smtp.qq.com";

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth","true");
        properties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.port", "465");


        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("发送者@qq.com", "发送者qq第三方发送授权码"); //发件人邮件用户名、密码
            }
        });

        try{
            //转换地址
            InternetAddress[] sendTo = new InternetAddress[receivers.length];
            for (int i = 0; i < receivers.length; i++) {
                System.out.println("发送到:" + receivers[i]);
                sendTo[i] = new InternetAddress(receivers[i]);
            }

            // 创建默认的 MimeMessage 对象
            MimeMessage messages = new MimeMessage(session);

            // Set From: 头部头字段
            messages.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            messages.setRecipients(Message.RecipientType.TO,sendTo);

            // Set Subject: 头部头字段
            messages.setSubject("This is the Subject Line!");

            // 设置消息体
            messages.setText(message);

            // 发送消息
            Transport.send(messages);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
