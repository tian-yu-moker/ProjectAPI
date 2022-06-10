package com.hku.projectapi.Beans;

public class KnowledgeQuestion
{
    private String question_content;
    private String answer_list;
    private String userid;
    private String comment_list;
    private String company;
    private String tag;


    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getAnswer_list() {
        return answer_list;
    }

    public void setAnswer_list(String answer_list) {
        this.answer_list = answer_list;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getComment_list() {
        return comment_list;
    }

    public void setComment_list(String comment_list) {
        this.comment_list = comment_list;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
