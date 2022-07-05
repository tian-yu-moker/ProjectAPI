package com.hku.projectapi.Controller.Dictionary;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hku.projectapi.Beans.Dictionary.CompanyBean;
import com.hku.projectapi.Beans.Dictionary.DictionaryDTO;
import com.hku.projectapi.Beans.Dictionary.QuestionTagBean;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Mapper.Dictionary.CompanyMapper;
import com.hku.projectapi.Mapper.Dictionary.QuestionTagMapper;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*","null"})
@RestController
@RequestMapping("/dictionary")
public class DictionaryController
{
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private QuestionTagMapper questionTagMapper;


    @GetMapping("getCompanyAndType")
    public Result getDictionary(@RequestHeader String token)
    {
        try{
            QueryWrapper<CompanyBean> companyQueryWrapper = new QueryWrapper<>();
            QueryWrapper<QuestionTagBean> questionTagQueryWrapper = new QueryWrapper<>();
            List<CompanyBean> companies = companyMapper.selectList(companyQueryWrapper);
            List<QuestionTagBean> tags = questionTagMapper.selectList(questionTagQueryWrapper);
            DictionaryDTO dictionary = new DictionaryDTO(companies, tags);
            token = JwtUtil.updateToken(token);
            Result res = new Result("00", "Success.", token, dictionary);
            return res;
        } catch (Exception e){
            return new Result("98", "Invalid token, please login.", null);
        }
    }
}
