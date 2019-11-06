package junit.test;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.List;

public class AppTest {
    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ProjectMapper bean = context.getBean(ProjectMapper.class);
        List<Project> projects = bean.selectByExample(new ProjectExample());
        for (Project project : projects) {
            System.out.println(project.getEmployee().getEname());
        }
    }

    @Test
    public  void test02() throws  Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("email-beans.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);

        //邮件对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);


        helper.setFrom("lqf_deyouxiang@163.com");
        helper.setTo("zqf1481656983@163.com");
        helper.setSubject("这是0708班的邮件测试");
        helper.setText("嘿嘿嘿嘿嘿嘿嘿，阿哦哦阿哦好奥");

        //发送邮件
        bean.send(mimeMessage);

        System.out.println("EMAIL PASS");
    }
}
