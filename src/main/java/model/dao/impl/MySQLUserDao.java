package model.dao.impl;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.mapper.UserMapper;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQLUserDao implements UserDao {
    private Connection connection;
    private UserMapper userMapper;

    public MySQLUserDao(Connection connection) {
        this.connection = connection;
        userMapper = new UserMapper();
    }

    public static void main(String[] args) {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        User user = new User();
        user.setName("Igor");
        user.setSurname("Tarasov");
        user.setEmail("igor.new.new@gmail.com");
        user.setPassword("1234qwerty");
        user.setRole(2);
        userDao.create(user);
    }

    @Override
    public boolean create(User entity) {
        boolean result = false;
        String insertUser = "INSERT INTO user \n" +
                "(name, surname, email, password, role)\n" +
                "VALUES(?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(insertUser)) {
            setGeneralParamsToPreparedStatement(statement, entity);
            if (statement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public void close() throws Exception {

    }

    private void setGeneralParamsToPreparedStatement(PreparedStatement stm, User entity) throws SQLException {
        stm.setString(1, entity.getName());
        stm.setString(2, entity.getSurname());
        stm.setString(3, entity.getEmail());
        stm.setString(4, entity.getPassword());
        stm.setInt(5, entity.getRole());
    }
}
