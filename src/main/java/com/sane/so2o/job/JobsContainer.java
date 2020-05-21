package com.sane.so2o.job;

import com.sane.so2o.entity.Verifycodeprocess;
import com.sane.so2o.service.IVerifycodeprocessService;
import com.sane.so2o.service.MailSenderService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class JobsContainer {
    @Autowired
    private IVerifycodeprocessService verifycodeprocessService;
    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("o2o-SendVerifyCode")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        XxlJobLogger.log(">>>>>>>>>o2o-SendVerifyCode>>>>>>>>run");
        verifycodeprocessService.processSendRegistCode();
        XxlJobLogger.log(">>>>>>>>>o2o-SendVerifyCode>>>>>>>>stop");
        return ReturnT.SUCCESS;
    }

}
