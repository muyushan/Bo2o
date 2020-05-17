package com.sane.so2o.service;


import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import java.util.Map;

public interface MailSenderService {
    public void sendSimpleTextMail(String to, String subject, String content);
    public void sendHtmlMail(String to, String subject, String content)throws MessagingException, javax.mail.MessagingException;
    public void sendAttachmentMail(String to, String subject, String content, String... fileArr)
            throws MessagingException, javax.mail.MessagingException;
    public void sendImgMail(String to, String subject, String content, Map<String, String> imgMap)
            throws MessagingException, javax.mail.MessagingException;
    public void sendTemplateMail(String to, String subject, Map<String, Object> paramMap, String template)
            throws MessagingException, javax.mail.MessagingException;
}
