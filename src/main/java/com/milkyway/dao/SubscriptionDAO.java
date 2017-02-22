package com.milkyway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.milkyway.model.Subscription;

@Repository
public class SubscriptionDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public long insertChangeRecord(final Subscription subscriptionChangeRequest) {
		
		KeyHolder holder = new GeneratedKeyHolder();
		final String insertSql = "insert into change_request(change_request_date,user_id,subscription_id,changed_qty) values(?,?,?,?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setTimestamp(1, getCurrentJavaSqlTimestamp());
				ps.setLong(2, subscriptionChangeRequest.getUserId());
				ps.setLong(3, subscriptionChangeRequest.getSubscriptionId());
				ps.setLong(4, subscriptionChangeRequest.getTempModifiedQty());
				return ps;
			}
		}, holder);
		
		Long newChangeRequestId = holder.getKey().longValue();
		return newChangeRequestId;
	}

	 public static java.sql.Timestamp getCurrentJavaSqlTimestamp() {
		    java.util.Date date = new java.util.Date();
		    return new java.sql.Timestamp(date.getTime());
		  }
}
