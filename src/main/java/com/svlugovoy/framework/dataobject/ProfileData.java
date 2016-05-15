package com.svlugovoy.framework.dataobject;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 09.05.2016.
 */
public class ProfileData {

    private String firstName;
    private String lastName;
    private String companyName;
    private String website;
    private String title;
    private String phone;
    private String timeZone;
    private int itemsPerPage;
    private int maxFileAge;

    public ProfileData(String firstName, String lastName, String companyName, String website, String title, String phone, String timeZone, int itemsPerPage, int maxFileAge) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.website = website;
        this.title = title;
        this.phone = phone;
        this.timeZone = timeZone;
        this.itemsPerPage = itemsPerPage;
        this.maxFileAge = maxFileAge;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getMaxFileAge() {
        return maxFileAge;
    }

    public void setMaxFileAge(int maxFileAge) {
        this.maxFileAge = maxFileAge;
    }

    @Override
    public String toString() {
        return "ProfileData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", website='" + website + '\'' +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", itemsPerPage=" + itemsPerPage +
                ", maxFileAge=" + maxFileAge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileData that = (ProfileData) o;

        if (itemsPerPage != that.itemsPerPage) return false;
        if (maxFileAge != that.maxFileAge) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (website != null ? !website.equals(that.website) : that.website != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        return timeZone != null ? timeZone.equals(that.timeZone) : that.timeZone == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
        result = 31 * result + itemsPerPage;
        result = 31 * result + maxFileAge;
        return result;
    }
}
