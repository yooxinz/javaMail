import org.junit.Test;

/**
 * Created by star on 2017/6/2.
 */
public class MailUtilTest {
    private MailUtil mailUtil=new MailUtil();

    @Test
    public void testSendEmail(){
        String [] receivers=new String[2];
        receivers[0]="接受者1@qq.com";
        receivers[1]="接受者2@qq.com";
        mailUtil.sendEmail(receivers,"测试");
    }
}
