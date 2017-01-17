package com.milkyway.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.milkyway.model.User;

@Repository
public class UserRegistrationDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public List<User> findAll() {
		return jdbcTemplate.query("select * from user", new UserRowMapper());
	}

	@Transactional(readOnly = true)
	public User findUserById(int id) {
		return jdbcTemplate.queryForObject("select * from user where id=?",
				new Object[] { id }, new UserRowMapper());
	}

	public void addUser(final User userRequest) {
		final String insertSql = "insert into user(first_name,last_name,mobile_no,email,user_type) values(?,?,?,?,?)";
		// define query arguments
		        Object[] params = new Object[] { userRequest.getFirstName(), userRequest.getLastName(),
		        		userRequest.getMobileNumber(),userRequest.getEmail(), userRequest.getUserType() };
                int row = jdbcTemplate.update(insertSql, params);
                System.out.println("RowNum-->" +row);
	}

	class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			/*user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));*/
			return user;
		}
	}

}
