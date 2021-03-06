package com.hku.projectapi.Controller.Knowledge;


import com.hku.projectapi.Beans.*;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionDTO;
import com.hku.projectapi.Beans.Knowledge.KnowledgeUpdateBean;
import com.hku.projectapi.Service.Knowledge.KnowledgeCommentService;
import com.hku.projectapi.Service.Knowledge.KnowledgeService;
import com.hku.projectapi.Tools.JwtUtil;
import com.hku.projectapi.Tools.UUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@CrossOrigin(origins = {"*","null"})
@RestController
public class KnowledgeController
{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private KnowledgeCommentService knowledgeCommentService;

    public KnowledgeController()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://120.77.98.16:3306/comp7705?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("0611");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // Create: post
//    @PostMapping("/knowledge_service")
//    @ResponseBody
//    public Object createQuestion(@RequestBody KnowledgeQuestionDTO question, @RequestHeader("token") String token)
//    {
//        String uuid = UUidGenerator.getUUID32();
//        String question_content = question.getQuestion_content();
//        String answer_list = question.getAnswer_list();
//        String interviewId = question.getInterview_id();
//        String userId = "";
//        try{
//            userId = JwtUtil.getUserId(token);
//        }catch (Exception e){
//            return new Result("98", "Invalid token", null);
//        }
//        String userid = userId;
//        String comment = question.getComment_list();
//        String company = question.getCompany();
//        String tag = question.getTag();
//        Date date = new Date();
//        Timestamp time = new Timestamp(date.getTime());
//        try
//        {
//            String sql = "INSERT INTO knowledge_questions(knowledge_id, question_content, answer_list, interview_id, userid, comment_list, company, tag, upload_time)" +
//                    "VALUES (\"" + uuid + "\", \"" + question_content + "\", \"" + answer_list +  "\", \"" + interviewId + "\", \"" + userid + "\", " +
//                    "\"" + comment +  "\", \"" + company + "\", + \"" + tag + "\", + \"" + time + "\")";
//            jdbcTemplate.execute(sql);
//            NormalResponse response = new NormalResponse();
//            response.setCode("00");
//            response.setDescription("Upload success.");
//            response.setToken(JwtUtil.updateToken(token));
//            return response;
//        }catch (Exception e)
//        {
//            NormalResponse response = new NormalResponse();
//            response.setCode("99");
//            response.setDescription("Internal service error.");
//            return response;
//        }
//    }

    @PostMapping("/knowledge_service")
    public Result create(@RequestBody KnowledgeQuestionDTO question, @RequestHeader("token") String token)
    {
        String userId = "";
        try{
            userId = JwtUtil.getUserId(token);
        } catch (Exception e){
            return new Result("98", "Invalid token", null);
        }
        try{
            Result res = knowledgeService.create(question, userId);
            res.setToken(JwtUtil.updateToken(token));
            return res;
        } catch (Exception e){
            Result res = new Result("99", "Internal server error.", null);
            res.setToken(JwtUtil.updateToken(token));
            return res;
        }
    }

    // Search: GET
    @GetMapping("/knowledge_service")
    public Result searchQuestionById(@RequestParam String uuid, @RequestHeader("token") String token)
    {
        String userId = "";
        try{
            userId = JwtUtil.getUserId(token);
        }catch (Exception e){
            return new Result("98", "Invalid token", null);
        }
        Result res = knowledgeService.getKnowledgeById(userId, uuid);

        try{
            token = JwtUtil.updateToken(token);
            res.setToken(token);
            return res;
        }catch (Exception e){
            return new Result("98", "Invalid token", null);
        }
//        String sql = "SELECT * FROM knowledge_questions WHERE knowledge_id=?";
//        try
//        {
//            QuestionBean data = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<QuestionBean>(QuestionBean.class), uuid);
//            ResponseWithData response = new ResponseWithData();
//            response.setCode("00");
//            response.setDescription("Success.");
//            response.setToken(JwtUtil.updateToken(token));
//            response.setData(data);
//            return response;
//
//        }catch(Exception e)
//        {
//            NormalResponse response = new NormalResponse("05", "No such knowledge question.",
//                    JwtUtil.updateToken(token));
//            return response;
//        }
    }

    // Update: PUT
    /**
     * Type: 0 OR 1
     * 0: append behind (only for comment, and answer update)
     * 1: replace (for question content update)
     */
    @PutMapping("/knowledge_service")
    public Object updateKnowledge(@RequestBody KnowledgeUpdateBean params, @RequestHeader("token") String token)
    {
        String sql = "UPDATE knowledge_questions SET " + params.getField() + " = ? WHERE knowledge_id = ?";
        try
        {
            if(params.getType() == 0)
            {
                String query = "SELECT " + params.getField() + " FROM knowledge_questions WHERE knowledge_id = ?";
                QuestionBean one = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<QuestionBean>(QuestionBean.class), params.getUuid());
                if(params.getField().equals("comment_list"))
                {
                    if(!one.getComment_list().equals(null))
                    {
                        params.setValue(one.getComment_list() + ";" + params.getValue());
                    }
                }
                else if(params.getField().equals("answer_list"))
                {
                    if(!one.getAnswer_list().equals(null))
                    {
                        params.setValue(one.getComment_list() + ";" + params.getValue());
                    }
                }
            }
            jdbcTemplate.update(sql, new Object[]{params.getValue(), params.getUuid()});
            NormalResponse response = new NormalResponse("05", "No such knowledge question.",
                    JwtUtil.updateToken(token));
            return response;
        }catch (Exception e)
        {
            NormalResponse response = new NormalResponse();
            response.setCode("06");
            response.setDescription("Update knowledge field error.");
            return response;
        }
    }

    @PostMapping("/knowledge_load")
    public Result load(@RequestHeader String token,
            @RequestBody PageRequestDTO pageRequestDTO)
    {
        Result res = knowledgeService.searchByPage(pageRequestDTO, token);
        if(res.getCode().equals("00"))
        {
            try{
                res.setToken(JwtUtil.updateToken(token));
                return res;
            } catch (Exception e){
                return new Result("98", "Invalid token, please login.", null);
            }
        }
        else
        {
            return res;
        }
    }


    // Delete
    @DeleteMapping("/knowledge_service")
    public Object deleteQuestion(String uuid, @RequestHeader("token") String token)
    {
        String sql = "DELETE FROM knowledge_questions WHERE knowledge_id=\"" + uuid + "\"";
        try
        {
            jdbcTemplate.execute(sql);
            NormalResponse response = new NormalResponse("05", "No such knowledge question.",
                    JwtUtil.updateToken(token));
            return response;
        }catch (Exception e)
        {
            NormalResponse response = new NormalResponse();
            response.setCode("05");
            response.setDescription("No such knowledge question.");
            return response;
        }
    }

}
