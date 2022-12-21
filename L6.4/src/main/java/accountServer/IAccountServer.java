package accountServer;

/**
 * @author ilYa
 */
public interface IAccountServer {

    void addNewUser();

    void removeUser();

    int getUsersLimit();

    void setUsersLimit(int usersLimit);

    int getUsersCount();
}
