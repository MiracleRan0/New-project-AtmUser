package Modale;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private int id;

    /**
     * 卡号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户状态  0-正常 1-锁卡
     */
    private Integer userStatus;

    public BigDecimal getAtmAmount() {
        return atmAmount;
    }

    public void setAtmAmount(BigDecimal atmAmount) {
        this.atmAmount = atmAmount;
    }

    private BigDecimal atmAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
    public User(String userPassword) {
        super();
    }
    public User( BigDecimal balance) {
        super();
    }
    public User(int id, String userAccount, String userPassword, BigDecimal balance, String userName, Date createTime, Date updateTime, Integer userStatus) {
        super();
        this.id = id;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.balance = balance;
        this.userName = userName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userStatus = userStatus;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", balance=" + balance +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userStatus=" + userStatus +
                '}';
    }
}
