package com.hku.projectapi.Beans;

public class UserBean
{
    private String email;
    private String name;
    private String password;
    private int is_admin;
    private String school;
    private String graduate_date;
    private String type;
    private String company;
    private String YoE;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGraduate_date() {
        return graduate_date;
    }

    public void setGraduate_date(String graduate_date) {
        this.graduate_date = graduate_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getYoE() {
        return YoE;
    }

    public void setYoE(String yoE) {
        YoE = yoE;
    }
}
