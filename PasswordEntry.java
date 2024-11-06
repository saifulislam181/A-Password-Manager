public class PasswordEntry {
    private String title;
    private String username;
    private String password;

    public PasswordEntry(String title, String username, String password) {
        this.title = title;
        this.username = username;
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void encryptPassword() {
        this.password = EncryptionUtil.encrypt(this.password);
    }

    public void decryptPassword() {
        this.password = EncryptionUtil.decrypt(this.password);
    }
}
