package com.hku.projectapi.Mapper.email_verification;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.email_verification_code;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationMapper extends BaseMapper<email_verification_code>
{

}
