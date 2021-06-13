package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.TodoList;
import util.DatabaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoListRepositoryImpl implements TodoListRepository{

    public TodoList[] data = new TodoList[10];
    private HikariDataSource dataSource;

    public TodoListRepositoryImpl(DataSource dataSource) {
        this.dataSource = DatabaseUtil.getDataSource();
    }

    @Override
    public TodoList[] getAll() {
        return data;
    }

    @Override
    public void add(TodoList todoList) {
        String sql = "INSERT INTO todolist(todo) VALUES(?)";

        try (Connection connection = DatabaseUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, todoList.getTodo());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private boolean isExists(Integer number) {
        String sql = "SELECT id FROM todolist WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, number);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return true;
                } else return false;
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean remove(Integer number) {
        if (isExists(number)) {
            String sql = "DELETE FROM todolist WHERE id = ?";

            try (Connection connection = DatabaseUtil.getDataSource().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1, number);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }
        } else return false;
    }
}
