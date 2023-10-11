import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ManufactureDAO {
    private final SessionFactory factory;

    public ManufactureDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public boolean add(Manufacture manufacture) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(manufacture);
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

    public Manufacture get(int id) {
        Session session = factory.openSession();
        Manufacture manufacture = session.get(Manufacture.class, id);
        session.close();
        return manufacture;
    }

    public List<Manufacture> getAll() {
        Session session = factory.openSession();
        List<Manufacture> manufactures = session.createQuery("FROM Manufacture", Manufacture.class).list();
        session.close();
        return manufactures;
    }

    public boolean remove(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id);
            if (manufacture != null) {
                session.delete(manufacture);
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

    public boolean remove(Manufacture manufacture) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(manufacture);
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

    public boolean update(Manufacture manufacture) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(manufacture);
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
