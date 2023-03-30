package utils;

import java.io.*;

public class User {
    private String username;
    private String email;
    private String password;
    private String gender;
    private int admin;

    final File resourceDir = new File("src/main/resources");;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String email, String username, String password, String gender, int admin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.admin = admin;
    }

    public boolean validatePassword () {
        if (password.length() < 9 || password.length() > 20) return false;
        return true;
    }
    public boolean validateUsername() {
        return username.matches("^[A-Za-z]\\w{8,29}$");
    }
    public boolean validateEmail() {
        return email.matches("^(.+)@(.+)$");
    }
    public boolean userExists() throws IOException {
        if (!new File(resourceDir, "databases/users.txt").isFile())
            return false;
        BufferedReader reader = new BufferedReader(new FileReader(new File(resourceDir, "databases/users.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] userData = line.split(",");
            if (userData[1].equals(username)) { // To check by email too - userData[0].equals(email)
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }
    public boolean writeToFile() throws IOException, IOException {
        if (userExists()) return false;
        boolean notCreate = true;
        if (!new File(resourceDir, "databases/users.txt").isFile())
            notCreate = false;
        FileWriter writer = new FileWriter(new File(resourceDir, "databases/users.txt"), notCreate);
        writer.write(email + "," + username + "," + gender+ "," + admin + "," + password + "\n");
        writer.close();
        return true;
    }
    public boolean userCheck() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(resourceDir, "databases/users.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] userData = line.split(",", 5);
            if (userData[1].equals(username) && userData[4].equals(password)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }
    public void remember() throws IOException {
        FileWriter writer = new FileWriter(new File(resourceDir, "databases/logged_in.txt"), false);
        writer.write(username);
        writer.close();
    }
    public void logOut()  {
        File logged_in = new File(resourceDir, "databases/logged_in.txt");
        if (logged_in.isFile())
            logged_in.delete();
    }
    public boolean isLoggedIn() {
        if (!new File(resourceDir, "databases/logged_in.txt").isFile())
            return false;
        return true;
    }
    public void changePassword() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(resourceDir+"/databases/users.txt"));
            String line = reader.readLine();
            StringBuilder updatedFileContent = new StringBuilder();

            // Update the password for username, and save
            while (line != null) {
                String[] userFields = line.split(",");
                String currentUsername = userFields[1];
                if (currentUsername.equals(username)) {
                    userFields[4] = password;
                    line = String.join(",", userFields);
                }
                updatedFileContent.append(line).append("\n");
                line = reader.readLine();
            }
            reader.close();

            // Write the updated file
            FileWriter writer = new FileWriter(resourceDir+"/databases/users.txt");
            writer.write(updatedFileContent.toString());
            writer.close();
        } catch (IOException e) {

        }
    }
}
