package DAO;

import Model.Message;
import UTIL.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public Message insertMessage(Message message){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "insert into message (posted_by, message_text, time_posted_epoch) values (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,message.getPosted_by());
            ps.setString(2,message.getMessage_text());
            ps.setLong(3,message.getTime_posted_epoch());
            ps.executeUpdate();

            ResultSet pkResultSet = ps.getGeneratedKeys();
            if (pkResultSet.next()){
                int generated_account_id = (int)pkResultSet.getLong(1);
                return new Message(generated_account_id,
                                    message.getPosted_by(),
                                    message.getMessage_text(),
                                    message.getTime_posted_epoch()
                                    );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    public List<Message> getAllMessages(){
        List<Message> messages = new ArrayList<>();
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select * from message";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return messages;
    }

    public Message getMessageById(int msgId){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select * from message where message_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,msgId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Message deleteMessageById(int msgId){
        Message deletedMsg = getMessageById(msgId);
        Connection conn = ConnectionUtil.getConnection();
        String sql = "delete from message where message_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,msgId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deletedMsg;
    }

    public Message updateMesageById(Message message){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "Update message set message_text = ? where message_id = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,message.getMessage_text());
            ps.setInt(2,message.getMessage_id());
            int rowsUpdated = ps.executeUpdate();

            if(rowsUpdated == 1){
                return getMessageById(message.getMessage_id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    public List<Message> getAllMessagesByUser(int postedBy){
        List<Message> messages = new ArrayList<>();
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select * from message where posted_by=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,postedBy);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return messages;
    }



}
