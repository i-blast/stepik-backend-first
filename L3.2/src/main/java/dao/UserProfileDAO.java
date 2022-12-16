package dao;

import model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author ilYa
 */
public class UserProfileDAO {

    private Session session;

    public UserProfileDAO(Session session) {
        this.session = session;
    }

    public UserProfile getById(long id) throws HibernateException {
        return session.get(UserProfile.class, id);
    }

    public UserProfile getByLogin(String login) {
        Criteria criteria = session.createCriteria(UserProfile.class);
        return ((UserProfile) criteria.add(Restrictions.eq("login", login)).uniqueResult());
    }

    public long insertUser(UserProfile profile) throws HibernateException {
        return (Long) session.save(profile);
    }
}
