package uz.akbar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ContactRepository
 */
public class ContactRepository {

	public boolean saveContact(Contact contact) {

		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon",
				"postgres", "root123")) {

			String sql = "insert into contact(name, surname, phone) values(?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, contact.getName());
			preparedStatement.setString(2, contact.getSurname());
			preparedStatement.setString(3, contact.getPhone());
			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Contact getByPhone(String phone) {
		Contact contact = null;

		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon",
				"postgres", "root123")) {

			String sql = "select id, name, surname, phone from contact where phone = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				contact = new Contact();
				contact.setName(resultSet.getString("name"));
				contact.setSurname(resultSet.getString("surname"));
				contact.setPhone(resultSet.getString("phone"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contact;
	}
}
