package accountServer;

/**
 * @author ilYa
 */
public class AccountServerController implements AccountServerControllerMBean {

    private final IAccountServer accountServer;

    public AccountServerController(IAccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public int getUsers() {
        return accountServer.getUsersCount();
    }

    @Override
    public int getUsersLimit() {
        return accountServer.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int userLimit) {
        accountServer.setUsersLimit(userLimit);
    }
}
