package com.myhd.util;

import cn.hutool.core.util.StrUtil;
import com.myhd.dto.ToEmail;
import com.myhd.exception.BusinessException;
import com.myhd.exception.SystemException;
import io.netty.util.internal.StringUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

/**
 * @Classname EmailService
 * @Description TODO
 * @Date 2023/10/25 下午9:44
 * @Created by joneelmo
 */
@Component
public class EmailService {
    @Resource
    JavaMailSender javaMailSender;
    @Resource
    ITemplateEngine iTemplateEngine;

    @Value("1660855825@qq.com")
    private String sendEmail;

    public Boolean sendEmail(ToEmail toEmail){
        if (StrUtil.isBlank(toEmail.getToUser())){
            throw new BusinessException(Code.BUSINESS_ERR,"邮箱地址不能为空");
        }
        if (StrUtil.isBlank(toEmail.getContent())){
            throw new BusinessException(Code.BUSINESS_ERR,"邮箱验证码不能为空");
        }
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());
            /*发件人*/
            mimeMessageHelper.setFrom(sendEmail,"人才招聘平台");
            /*收件人*/
            mimeMessageHelper.setTo(toEmail.getToUser());
            /*设置邮箱验证码*/
            Context context = new Context();
            context.setVariable("verifyCode", Arrays.asList(toEmail.getContent().split("")));
            val text = iTemplateEngine.process("EmailVerificationCode.html", context);
            mimeMessageHelper.setText(text,true);
            mimeMessageHelper.setSentDate(new Date());
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new SystemException(Code.SYSTEM_ERR,"发送邮件失败");
        }
    }
}
