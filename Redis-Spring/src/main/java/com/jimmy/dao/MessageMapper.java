package com.jimmy.dao;

import com.jimmy.domain.Message;

/**
 * @author xiongyang
 * @date 2020/7/29 10:40
 * @Description:
 */
public interface MessageMapper {

    Message queryMessage(Message message);

    int saveMessage(Message message);
}
