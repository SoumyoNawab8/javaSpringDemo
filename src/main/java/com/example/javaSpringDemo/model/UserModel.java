package com.example.javaSpringDemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class UserModel {

		@Id
		private String id;

		private String name;
		private String dateOfJoining;
		
		public UserModel(String id, String name, String dateOfJoining) {
			super();
			this.id = id;
			this.name = name;
			this.dateOfJoining = dateOfJoining;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

				public String getDateOfJoining() {
			return dateOfJoining;
		}

		public void setDateOfJoining(String date) {
			this.dateOfJoining = dateOfJoining;
		}

}