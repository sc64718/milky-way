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

import com.milkyway.model.Subscription;
import com.milkyway.model.UserDetails;
import com.milkyway.model.UserSubscription;
import com.milkyway.model.UserVacations;
import com.milkyway.utils.DateUtils;

@Repository
public class UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public List<UserDetails> findAll() {
		return jdbcTemplate.query("select * from user", new UserRowMapper());
	}

	@Transactional(readOnly = true)
	public UserDetails findUserById(long userId) {
		return jdbcTemplate.queryForObject(
				"select * from user where user_id=?", new Object[] { userId },
				new UserRowMapper());
	}

	@Transactional(readOnly = true)
	public List<UserSubscription> getUserSubscription(long userId) {
		final String getUserSubSql = "select user.first_name as first_name,user.last_name as last_name,"
				+ "user.mobile_no as mobile_no,user.email as email,subscription.subscribed_qty as subscribed_qty,"
				+ "subscription.subscription_id as subscription_id,subscription.subscribed_item_id as subscribed_item_id,"
				+ "(select subscribed_item_name from subscribed_item_m where subscribed_item_id = subscription.subscribed_item_id) as subscribed_item_name"
				+ " from user,subscription where subscription.user_id= user.user_id and user.user_id= " + userId + "";
		UserSubscriptionRowMapper userSubsMapper = new UserSubscriptionRowMapper();
		return jdbcTemplate.query(getUserSubSql, userSubsMapper);
		 
	}

	public Long addUser(final UserDetails userRequest) {
		KeyHolder holder = new GeneratedKeyHolder();
		final String insertSql = "insert into user(first_name,last_name,mobile_no,email,passcode) values(?,?,?,?,?)";
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
	
	public void addUserVacations(final UserVacations userVacations) {
		final String insertSql = "insert into user_vacations(user_id,from_date,to_date,request_date) values(?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSql);
				ps.setLong(1, userVacations.getUserId());
				ps.setDate(2, userVacations.getFromDate());
				ps.setDate(3, userVacations.getToDate());
				ps.setTimestamp(4, DateUtils.getCurrentJavaSqlTimestamp());

				return ps;
			}
		});
	}

	class UserRowMapper implements RowMapper<UserDetails> {
		@Override
		public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserDetails userDetails = new UserDetails();
			userDetails.setUserId(rs.getLong("user_id"));
			userDetails.setFirstName(rs.getString("first_name"));
			userDetails.setLastName(rs.getString("last_name"));
			userDetails.setMobileNumber(rs.getString("mobile_no"));
			userDetails.setEmail(rs.getString("email"));
			userDetails.setPassCode(rs.getString("passcode"));
			return userDetails;
		}
	}

	class UserSubscriptionRowMapper implements RowMapper<UserSubscription> {

		@Override
		public UserSubscription mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserSubscription userSubscription = new UserSubscription();
			
			UserDetails userDetails = new UserDetails();
			userDetails.setFirstName(rs.getString("first_name"));
			userDetails.setLastName(rs.getString("last_name"));
			userDetails.setMobileNumber(rs.getString("mobile_no"));
			userDetails.setEmail(rs.getString("email"));
			
			Subscription subscription = new Subscription();
			subscription.setSubscriptionId(rs.getLong("subscription_id"));
			subscription.setSubscribedQty(rs.getLong("subscribed_qty"));
			
			subscription.setSubscribedItemName(rs
					.getString("subscribed_item_name"));
					
			userSubscription.setSubscription(subscription);
			userSubscription.setUserDetails(userDetails);
			
			return userSubscription;
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
		UserDetails userDetails = findUserById(userId);
		System.out.println("UserId is " + userDetails.getUserId());
		System.out.println("UserName is " + userDetails.getFirstName());
		if (userDetails.getPassCode().equalsIgnoreCase(passCode)) {
			System.out.println("Passcode matches");
			return true;
		}
		return false;
	}

}
