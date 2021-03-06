package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Java Object for DbUser Table with DbUser id, user id, role ID, contact email, sfu email, phoneNumber, photoURL
 */

@Entity
public class DbUser extends Model {
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_CONTACT_EMAIL = "contact_email";
    public static final String COLUMN_SFU_EMAIL = "sfu_email";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_PHOTO_URL = "photo_url";

    @Id
    private Integer id;

    @Column(columnDefinition = "integer default -1")
    public Integer roleId = -1;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String contactEmail;

    @Column(nullable = false, unique = true)
    private String sfuEmail;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String photoURL;

    @Column(columnDefinition = "integer default 1")
    private Boolean enabled = true;

    public DbUser() {
        // Required empty constructor for FormFactory
    }

    public DbUser(String firstName,
                  String lastName,
                  String sfuEmail,
                  String contactEmail,
                  String phoneNumber,
                  String photoURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sfuEmail = sfuEmail;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
        this.photoURL = photoURL;
    }

    public Integer getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getSfuEmail() {
        return sfuEmail;
    }

    public void setSfuEmail(String sfuEmail) {
        this.sfuEmail = sfuEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public static Finder<Integer, DbUser> find = new Finder<>(DbUser.class);


    @Override
    public String toString() {
        return "DbUser{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", sfuEmail='" + sfuEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", photoURL='" + photoURL + '\'' +
                '}';
    }
}
