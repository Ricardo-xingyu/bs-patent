package com.bs.content.service.jobhandler;

import com.bs.messagesdk.model.po.MqMessage;
import com.bs.messagesdk.service.MessageProcessAbstract;
import com.bs.messagesdk.service.MqMessageService;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2022/9/22 10:16
 */
@Slf4j
@Component
public class CoursePublishTask extends MessageProcessAbstract {

    //任务调度入口
    @XxlJob("CoursePublishJobHandler")
    public void coursePublishJobHandler() throws Exception {
        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        log.debug("shardIndex="+shardIndex+",shardTotal="+shardTotal);
        //参数:分片序号、分片总数、消息类型、一次最多取到的任务数量、一次任务调度执行的超时时间
        process(shardIndex,shardTotal,"course_publish",30,60);
    }


    //专利发布任务处理
    @Override
    public boolean execute(MqMessage mqMessage) {
        //获取消息相关的业务信息
        String businessKey1 = mqMessage.getBusinessKey1();
        long courseId = Integer.parseInt(businessKey1);
        //专利静态化
        generateCourseHtml(mqMessage,courseId);
        //专利索引
        saveCourseIndex(mqMessage,courseId);
        //专利缓存
        saveCourseCache(mqMessage,courseId);
        return true;
    }


    //生成专利静态化页面并上传至文件系统
    public void generateCourseHtml(MqMessage mqMessage,long courseId){

        log.debug("开始进行专利静态化,专利id:{}",courseId);
        //消息id
        Long id = mqMessage.getId();
        //消息处理的service
        MqMessageService mqMessageService = this.getMqMessageService();
        //消息幂等性处理
        int stageOne = mqMessageService.getStageOne(id);
        if(stageOne >0){
            log.debug("专利静态化已处理直接返回，专利id:{}",courseId);
            return ;
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //保存第一阶段状态
        mqMessageService.completedStageOne(id);

    }

    //将专利信息缓存至redis
    public void saveCourseCache(MqMessage mqMessage,long courseId){
        log.debug("将专利信息缓存至redis,专利id:{}",courseId);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
    //保存专利索引信息
    public void saveCourseIndex(MqMessage mqMessage,long courseId){
        log.debug("保存专利索引信息,专利id:{}",courseId);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
