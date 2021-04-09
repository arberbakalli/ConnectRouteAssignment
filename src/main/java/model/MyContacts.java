package model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyContacts {
	String firstName;
	String lastName;
	String country;
	String mobile;
	List<String> groups;
	String id;
	List<Object> blacklistedServices;
	boolean vip;
	String email;
	List<Object> labels;
}