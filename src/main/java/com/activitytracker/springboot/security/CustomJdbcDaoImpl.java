package com.activitytracker.springboot.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class CustomJdbcDaoImpl extends JdbcDaoImpl  {
	Long userId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		
		
		return getJdbcTemplate().query(this.getUsersByUsernameQuery(),
				new String[] { username }, new RowMapper<UserDetails>() {
					@Override
					public UserDetails mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						//use emailid as username
						String emailid = rs.getString(1);
						String password = rs.getString(2);
						boolean enabled = rs.getBoolean(3);
						Long id = rs.getLong(4);
						setUserId(id);
						return new User(emailid, password, enabled, true, true, true,
								AuthorityUtils.NO_AUTHORITIES);
					}

				});
	}
	@Override
	protected List<GrantedAuthority> loadUserAuthorities(String username) {
		// ignore username as currently not required as per database schema
		
		return getJdbcTemplate().query(this.getAuthoritiesByUsernameQuery(),
				new Long[] { getUserId() }, new RowMapper<GrantedAuthority>() {
					@Override
					public GrantedAuthority mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String roleName = CustomJdbcDaoImpl.this.getRolePrefix() + rs.getString(2);

						return new SimpleGrantedAuthority(roleName);
					}
				});
	}
	

}
