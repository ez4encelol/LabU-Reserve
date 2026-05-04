package model.database;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import model.UserFactory;
import model.enums.UserType;
import model.userhierarchy.UniversityAffiliated;
import model.userhierarchy.User;

public class UserTable extends Table{
	private Map<Integer, User> users;

	private String dbPath = getDBPath();

	private static UserTable dbInstance;

	//singleton of the user database
	private UserTable() {
		users=new HashMap<>();
		try {
			loadCSV();
		} catch (Exception e) {
			System.err.println("Error reading User CSV");
		}
	}
	
	//mainly used for testing, for utilizing test csv files 
  	public void setCustomDBPath(String path) {
  		dbPath=path;
  	}
	
	public void loadFromDB() {
		try {
			loadCSV();
		} catch (Exception e) {
			System.err.println("Error reading Equipment CSV");
		}
	}

	public static UserTable getInstance() {
		//use lazy initialization of the database
		if(dbInstance==null) {
			dbInstance=new UserTable();
		}
		return dbInstance;
	}

	public void addUser(User u) {
		users.put(u.getId(), u);
	}

	public User getUser(int id) {
		return users.get(id);
	}

	public List<User> getUsersAsList(){
		return new ArrayList<>(users.values());
	}

	@Override
	public final void loadCSV() {
		users = new HashMap<>();
		try {
			CsvReader reader = new CsvReader(dbPath);
			reader.readHeaders();
			int noRecords=0;
			while (reader.readRecord()) {
				int userId = Integer.parseInt(reader.get("userId"));
				String userName = reader.get("username");
				String passWord = reader.get("password");
				String email = reader.get("email");
				boolean affiliated = Boolean.parseBoolean(reader.get("universityAffiliated"));
				int verificationNum = Integer.parseInt(reader.get("verificationNum"));
				UserType userType = convertToEnum(reader.get("userType"));
				boolean isDepartmentApproved = Boolean.parseBoolean(reader.get("isDepartmentApproved"));

				UserFactory factory = new UserFactory();
				User user = factory.createUser(userId, email, userName, passWord, verificationNum, userType);
				user.setIsDepartmentApproved(isDepartmentApproved);

				users.put(userId, user);
				noRecords++;
			}
			idGen.setUserIdCount(noRecords+1);
			reader.close();
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error reading User CSV");
		}
	}

	@Override
	public final void update(){
		try {		
			CsvWriter csvOutput = new CsvWriter(new FileWriter(dbPath, false), ',');

			csvOutput.write("userId");
			csvOutput.write("username");
			csvOutput.write("password");
			csvOutput.write("email");
			csvOutput.write("universityAffiliated");
			csvOutput.write("verificationNum");
			csvOutput.write("userType");
			csvOutput.write("isDepartmentApproved");

			csvOutput.endRecord();

			for(User u: getUsersAsList()){
				csvOutput.write(String.valueOf(u.getId()));
				csvOutput.write(u.getUsername());
				csvOutput.write(u.getPassword());
				csvOutput.write(u.getEmail());
				csvOutput.write(String.valueOf(u.getUniversityAfilliated()));
				csvOutput.write(String.valueOf(u.getVerificationNum()));
				csvOutput.write(convertToString(u.getUserType()));
				csvOutput.write(String.valueOf(u.getIsDepartmentApproved()));
				csvOutput.endRecord();
			}
			csvOutput.close();

		}catch (IOException e) {
			System.err.println("Error writing to User CSV");
		}
	}

	//mainly for testing purposes
	public void setUsers(Map<Integer, User> users) {
		this.users=users;
	}

	//reset and set the instance to null for resetting for testing purposes
	public static void resetToNull() {
		dbInstance=null;
	}

	//Helper methods for Enums
	private static String convertToString(UserType type){
		return type.toString();
	}

	private static UserType convertToEnum(String status){
		return UserType.valueOf(status);
	}

	@Override
	public final String getDBPath(){
		return getDBFolder() + "User.csv";
	}
}
