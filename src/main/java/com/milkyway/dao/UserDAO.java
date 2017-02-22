package com.milkyway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.milkyway.model.SubscribedItem;
import com.milkyway.model.Subscription;
import com.milkyway.model.User;

@Repository
public class UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public List<User> findAll() {
		return jdbcTemplate.query("select * from user", new UserRowMapper());
	}

	@Transactional(readOnly = true)
	public User findUserById(long userId) {
		return jdbcTemplate.queryForObject(
				"select * from user where user_id=?", new Object[] { userId },
				new UserRowMapper());
	}

	@Transactional(readOnly = true)
	public List<User> getUserSubscription(long userId) {
		final String getUserSubSql = "select user.first_name as first_name,user.last_name as last_name,"
				+ "user.mobile_no as mobile_no,user.email as email,subscription.subscribed_qty as subscribed_qty,"
				+ "subscription.subscription_id as subscription_id,subscription.subscribed_item_id as subscribed_item_id,"
				+ "(select subscribed_item_name from subscribed_item_m where subscribed_item_id = subscription.subscribed_item_id) as subscribed_item_name"
				+ " from user,subscription where user.user_id= " + userId + "";
		UserSubscriptionRowMapper userSubsMapper = new UserSubscriptionRowMapper();
		return jdbcTemplate.query(getUserSubSql, userSubsMapper);
		 
	}

	public Long addUser(final User userRequest) {
		KeyHolder holder = new GeneratedKeyHolder();
		final String insertSql = "insert into user(first_name,last_name,mobile_no,email,passcode) values(?,?,?,?,?)";
		/*
		 * // define query arguments Object[] params = new Object[] {
		 * userRequest.getFirstName(), userRequest.getLastName(),
		 * userRequest.getMobileNumber
		 * (),userRequest.getEmail(),userRequest.getPassCode()}; int row =
		 * jdbcTemplate.update(insertSql, params);
		 * System.out.println("RowNum-->" +row);
		 */
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, userRequest.getFirstName());
				ps.setString(2, userRequest.getLastName());
				ps.setString(3, userRequest.getMobileNumber());
				ps.setString(4, userRequest.getEmail());
				ps.setString(5, userRequest.getPassCode());
				return ps;
			}
		}, holder);

		Long newUserId = holder.getKey().longValue();
		return newUserId;
	}

	class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserId(rs.getLong("user_id"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setMobileNumber(rs.getString("mobile_no"));
			user.setEmail(rs.getString("email"));
			user.setPassCode(rs.getString("passcode"));
			return user;
		}
	}

	class UserSubscriptionRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setMobileNumber(rs.getString("mobile_no"));
			user.setEmail(rs.getString("email"));
			Subscription subscription = new Subscription();
			SubscribedItem subscribedItem = new SubscribedItem();
			subscription.setSubscriptionId(rs.getLong("subscription_id"));
			subscription.setSubscribedQty(rs.getLong("subscribed_qty"));
			subscribedItem.setSubscribedItemId(rs.getLong("subscribed_item_id"));
			subscribedItem.setSubscribedItemName(rs
					.getString("subscribed_item_name"));
			subscription.setSubscribedItem(subscribedItem);
			user.setSubscription(subscription);
			return user;
		}

	}

	public boolean findByMobileNo(final String mobileNumber) {
		String queryCheck = "SELECT * from user WHERE mobile_no = '"
				+ mobileNumber + "'";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryCheck);
		if (rows.size() > 0) {
			return false;
		}
		return true;
	}

	public void updateUserPasscode(final String passCode,
			final String mobileNumber) {
		final String updateSql = "UPDATE user SET passcode= '" + passCode
				+ "' WHERE mobile_no = '" + mobileNumber + "'";
		int row = jdbcTemplate.update(updateSql);
		System.out.println("RowNum-->" + row);
	}

	public boolean validateUserPasscode(long userId, String passCode) {
		User user = findUserById(userId);
		System.out.println("UserId is " + user.getUserId());
		System.out.println("UserName is " + user.getFirstName());
		if (user.getPassCode().equalsIgnoreCase(passCode)) {
			System.out.println("Passcode matches");
			return true;
		}
		return false;
	}

}
