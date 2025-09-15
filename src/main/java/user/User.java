package user;

public class User {

    // 성명, 연락처, 주소지, 이메일
    private String name;
    private String phoneNumber;
    private String address;
    private String email;

    public User(String address, String phoneNumber, String name) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = "";
    }

    public User(String name, String phoneNumber, String address, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }


}
