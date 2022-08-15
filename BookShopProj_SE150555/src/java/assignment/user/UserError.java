package assignment.user;

public class UserError {

    private String userIDEror;
    private String fullNameError;
    private String passwordError;
    private String confirmError;
    private String phoneError;
    private String roleIDError;
    private String messageError;

    public UserError() {
        this.userIDEror = "";
        this.fullNameError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.phoneError = "";
        this.roleIDError = "";
        this.messageError = "";
    }

    public UserError(String userIDEror, String fullNameError, String passwordError, String confirmError, String phoneError, String roleIDError, String messageError) {
        this.userIDEror = userIDEror;
        this.fullNameError = fullNameError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.phoneError = phoneError;
        this.roleIDError = roleIDError;
        this.messageError = messageError;
    }



    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public String getUserIDEror() {
        return userIDEror;
    }

    public void setUserIDEror(String userIDEror) {
        this.userIDEror = userIDEror;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

}
