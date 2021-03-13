package com.gad.epidemicmanage.common.utils;

import com.gad.epidemicmanage.pojo.entity.UserBaseInfo;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

import static com.gad.epidemicmanage.common.GlobalConstant.*;

/**
 * 邮件发送工具
 */
public class MailUtil {

    /**
     * 发送邮件
     */
    public static void sendMail(UserBaseInfo userBaseInfo) throws GeneralSecurityException, MessagingException {

        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host","smtp.qq.com");
        properties.setProperty("mail.transport.protocol","smtp");
        properties.setProperty("mail.smtp.auth","true");

        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_MAIL_ADDRESS,MAIL_KEY);
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect("smtp.qq.com",SENDER_MAIL_ADDRESS,MAIL_KEY);

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(SENDER_MAIL_ADDRESS));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(RECEIVER_MAIL_ADDRESS));

        //邮件标题
        mimeMessage.setSubject("疫情管理系统");

        //邮件内容
        String gender = userBaseInfo.getGender()==0 ? "女":"男";
        String content = "有新的体温异常用户！请尽快与之取得联系!" +
                "姓名:" + userBaseInfo.getName() +
                " 性别:" + gender +
                " 电话:" + userBaseInfo.getPhone() +
                " 住址:" + userBaseInfo.getCommunity()+ " " +userBaseInfo.getEstate()+ " " +userBaseInfo.getHouseNumber();
        mimeMessage.setContent(content,"text/html;charset=UTF-8");

        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();
    }
}
