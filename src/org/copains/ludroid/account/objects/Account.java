package org.copains.ludroid.account.objects;

public class Account {

    public static final String TBL_NAME = "users";
    public static final String FLD_ID = "usr_id";
    public static final String FLD_FIRSTNAME = "usr_firstname";
    public static final String FLD_BIRTHDATE = "usr_birthdate";
    public static final String FLD_SEX = "usr_sex";
    public static final String FLD_CANREAD = "usr_canread";

    private Integer id;
    private String firstName;
    private String birthDate;
    private Integer sex;
    private boolean canRead = false;

    public Account() {

    }

    public Account(String firstName, String birthDate, int sex, int canRead) {
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.sex = sex;
        if (canRead == 1)
            this.canRead = true;
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

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

}

