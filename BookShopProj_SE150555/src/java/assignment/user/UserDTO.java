package assignment.user;

public class UserDTO {

    private String userID;
    private String fullName;
    private String password;
    private String phone;
    private String address;
    private String createDate;
    private String roleID;
    private String statusID;

    public UserDTO() {
    }

    public UserDTO(String userID, String fullName, String password, String phone, String address, String createDate, String roleID, String statusID) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.roleID = roleID;
        this.statusID = statusID;
    }

    public UserDTO(String userID, String fullName, String password, String phone, String address, String createDate, String roleID) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.roleID = roleID;
    }
    

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    
}
