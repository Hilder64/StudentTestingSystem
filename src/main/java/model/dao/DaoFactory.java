package model.dao;

import model.dao.impl.MySQLDaoFactory;

public abstract class DaoFactory {
    private static volatile DaoFactory daoFactory;

    public abstract UserDao getUserDao();

    public static DaoFactory getInstance() {
        DaoFactory localInstance = daoFactory;
        if (localInstance == null) {
            synchronized (DaoFactory.class) {
                localInstance = daoFactory;
                if (localInstance == null) {
                    daoFactory = new MySQLDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
