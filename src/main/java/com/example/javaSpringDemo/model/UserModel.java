package com.example.javaSpringDemo.model;

import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class UserModel {

		@Id
		private String id;
		private LocalTime dateOfJoining;
		private String name;
		private LocalTime lastUpdateTime;
		
		public UserModel(String id, String name, LocalTime dateOfJoining,LocalTime lastUpdateTime) {
			super();
			this.id = id;
			this.name = name;
			this.dateOfJoining = dateOfJoining;
			this.lastUpdateTime = lastUpdateTime;
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

		public LocalTime getDateOfJoining() {
			return dateOfJoining;
		}

		public void setDateOfJoining() {
			this.dateOfJoining = LocalTime.now(ZoneId.of("GMT+05:30"));
		}

		public LocalTime getLastUpdateTime() {
			return lastUpdateTime;
		}

		public void setLastUpdateTime(){
			this.lastUpdateTime = LocalTime.now(ZoneId.of("GMT+05:30"));
		}

}