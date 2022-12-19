package accountServer;

/**
 * @author ilYa
 */
public interface AccountServerControllerMBean {
    int getUsers();

    int getUsersLimit();

    void setUsersLimit(int usersLimit);
}
