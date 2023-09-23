package Service;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import Model.Message;

import java.util.List;

public class MessageService {

    public MessageDAO messageDAO;
    public AccountDAO accountDAO;

    public MessageService(){
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }


    public Message addMessage(Message message){
        //check the message before add new message:
        if(checkMessage(message)){
         Message messageAdded = messageDAO.insertMessage(message);
         return messageAdded;
        }

        return null;
    }

    public boolean checkMessage(Message message){
        Account account = accountDAO.geAccountById(message.getPosted_by());
       if(account == null || message.getMessage_text().isEmpty() || message.getMessage_text().length() > 254) {
           return false;
       }

        return true;
    }

    public List<Message> getAllMessages(){
        List<Message> messages = messageDAO.getAllMessages();
        return messages;
    }


    public Message getMessageById(int message_id){
        Message retrievedMsg = messageDAO.getMessageById(message_id);
        return retrievedMsg;
    }


    public Message deleteMessageById(int message_id){
        Message deletedMSG = messageDAO.deleteMessageById(message_id);
        return deletedMSG;
    }


    public Message updateMessageById(Message message){
        if(checkUpdatedMessage(message)) {
            Message updatedMsg = messageDAO.updateMesageById(message);
            return updatedMsg;
        }
        return null;
    }

    public boolean checkUpdatedMessage(Message message){
        Message message1 = messageDAO.getMessageById(message.getMessage_id());
        if(message1 == null || message.getMessage_text().isEmpty() || message.getMessage_text().length() > 255) {
            return false;
        }

        return true;
    }


    public List<Message> getAllMessagesByUser(int postedBy){
        List<Message> messages = messageDAO.getAllMessagesByUser(postedBy);
        return messages;
    }


}
