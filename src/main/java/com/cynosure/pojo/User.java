package com.cynosure.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_user", schema="[cynosure-schema]")
public class User {
		@Id
		@GeneratedValue
		@Column(name="user_id")
		private Long userId;
		
		@Column(name="user_name")
		private String userName;
		
		@Column(name="user_email")
		private String userEmail;
		
		@Column(name="user_role")
		private Long userRole;

		public Long getUserRole() {
			return userRole;
		}

		public void setUserRole(Long userRole) {
			this.userRole = userRole;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		
	

}
