import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PhoneDAO {
    private final SessionFactory factory;

    public PhoneDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public boolean add(Phone phone) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(phone);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public Phone get(int id) {
        Session session = factory.openSession();
        Phone phone = session.get(Phone.class, id);
        session.close();
        return phone;
    }

    public List<Phone> getAll() {
        Session session = factory.openSession();
        List<Phone> phones = session.createQuery("FROM Phone", Phone.class).list();
        session.close();
        return phones;
    }

    public boolean remove(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Phone phone = session.get(Phone.class, id);
            if (phone != null) {
                session.delete(phone);
                tx.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean remove(Phone phone) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(phone);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean update(Phone phone) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(phone);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
