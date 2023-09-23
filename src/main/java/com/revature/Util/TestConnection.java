package com.revature.Util;

import Model.Message;
import Service.MessageService;
import UTIL.ConnectionUtil;

import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {
        try {
            System.out.println(ConnectionUtil.getConnection().isValid(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Message message = new Message(5,"tst",176434);
        MessageService messageService = new MessageService();

        Message addedMessage = messageService.addMessage(message);
        System.out.println(addedMessage.toString());
    }




}
