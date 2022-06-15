package com.hku.projectapi.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hku.projectapi.Beans.email_verification_code;
import com.hku.projectapi.Mapper.email_verification.EmailVerificationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class EmailService
{
    @Autowired
    private EmailVerificationMapper emailMapper;

    public String sendEmail(String email)
    {
        QueryWrapper<email_verification_code> oneQuery = new QueryWrapper<>();
        oneQuery.eq("email", email);
        List<email_verification_code> res = emailMapper.selectList(oneQuery);
        if(res.size() == 0)
        {
            email_verification_code newCode = new email_verification_code();
            newCode.setEmail(email);
            int code = (int) (Math.random() * (99999 - 10000) + 10000);
            newCode.setCode(String.valueOf(code));
            newCode.setGenerate(new Timestamp(new Date().getTime()));
            newCode.setExpiry(new Timestamp(new Date().getTime() + 180000));
            emailMapper.insert(newCode);
            return String.valueOf(code);
        }
        else
        {
            Date date = new Date();
            String last30s = new Timestamp(date.getTime() + 30000).toString();

            int compare = last30s.compareTo(res.get(0).getExpiry().toString());
            // If time is more than 30s to expiry time, use the old one.
            if(compare < 0)
            {
                String code = res.get(0).getCode();
                return code;
            }
            else
            {
                // Generate new one
                int code = (int) (Math.random() * (99999 - 10000) + 10000);
                UpdateWrapper<email_verification_code> updateWrapper = new UpdateWrapper();
                updateWrapper.eq("email", email);
                updateWrapper.set("code", String.valueOf(code));
                updateWrapper.set("generate", new Timestamp(date.getTime()));
                updateWrapper.set("expiry", new Timestamp(date.getTime() + 180000));
                emailMapper.update(null, updateWrapper);
                return String.valueOf(code);
            }
        }
    }

    public int doVerify(String email, String code)
    {
        QueryWrapper<email_verification_code> query = new QueryWrapper<>();
        query.eq("email", email);
        List<email_verification_code> res = emailMapper.selectList(query);
        // If no such code
        if(res.size() == 0)
        {
            return -1;
        }
        else
        {
            if(res.get(0).getCode().equals(code))
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }

    }
}
