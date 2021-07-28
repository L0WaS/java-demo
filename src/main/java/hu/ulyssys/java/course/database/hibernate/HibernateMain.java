package hu.ulyssys.java.course.database.hibernate;
import hu.ulyssys.java.course.database.hibernate.entity.Tank;
import org.hibernate.Session;
import java.util.Date;
public class HibernateMain {

    public static void main(String[] args) {
        //Save
        Tank tank = new Tank();
        tank.setName("példa tigris tank");
        tank.setType("tigris tank");
        tank.setCreatedDate(new Date());
        tank.setLastModifiedDate(new Date());
        Long id = insertTank(tank);
        // find By id
        Tank persisTank = findById(id);
        // lekérdezzük a tank
        persisTank.setName("test");
        persisTank.setCreatedDate(new Date());
        update(persisTank);
        DatabaseSessionProvider.getInstance().getSessionObj().close();
    }

    private static void update(Tank tank) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.update(tank);
        session.getTransaction().commit();
    }

    private static Tank findById(Long id) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        return session.find(Tank.class, id);
    }

    private static Long insertTank(Tank tank) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.save(tank);
        session.getTransaction().commit();
        return tank.getId();
    }
}