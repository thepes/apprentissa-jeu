package org.copains.ludroid.account.objects;

public class Account {

    public static final String TBL_NAME = "users";

    private String firstName;
    private String birthDate;
    private Integer sex;
    private boolean canRead;

    public Account() {

    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate
     *            the birthDate to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return the canRead
     */
    public boolean isCanRead() {
        return canRead;
    }

    /**
     * @param canRead
     *            the canRead to set
     */
    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

}
