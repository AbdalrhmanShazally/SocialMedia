package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    public AccountDAO accountDAO;


    public AccountService(){
        accountDAO = new AccountDAO();
    }


    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }


    public Account addAccount(Account account){

        //check the account before add new account:

        if (checkAccount(account)){
            Account addedAccount = accountDAO.insertAccount(account);
            return addedAccount;
        }
        return null;
    }

    public Account processUserLogin(Account account){
        Account existAccount = accountDAO.geAccountByUserNamePassword(account.getUsername(),account.getPassword());
        if(existAccount != null){
            return existAccount;
        }
        return null;
    }

    public boolean checkAccount(Account account){
        if(accountDAO.geAccountByUserName(account.getUsername()) != null || account.getUsername().isEmpty() || account.getPassword().length() < 4 ) {
            return false;
        }
        return true;
    }


}
