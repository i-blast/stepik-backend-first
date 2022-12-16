package service.account;

import model.UserProfile;
import service.db.DBException;
import service.db.DBService;

import java.util.Map;

/**
 * @author ilYa
 */
public class AccountService {

    private final DBService dbService;

    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService(DBService dbService, Map<String, UserProfile> sessionIdToProfile) {
        this.dbService = dbService;
        this.sessionIdToProfile = sessionIdToProfile;
    }

    // TODO: return Optional<Long>?
    public Long createUser(UserProfile userProfile) {
        try {
            return dbService.addUserProfile(userProfile);
        } catch (DBException exc) {
            System.out.println("Error during user profile persisting: ".concat(exc.getMessage()));
        }
        return null;
    }

    // TODO: return Optional<Long>?
    public UserProfile getUserByLogin(String login) {
        try {
            return dbService.getUserProfileByLogin(login);
        } catch (DBException exc) {
            System.out.println("Error during user profile persisting: ".concat(exc.getMessage()));
        }
        return null;
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
