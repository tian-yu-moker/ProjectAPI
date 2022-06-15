package com.hku.projectapi.Controller;

import com.hku.projectapi.Beans.email_verification_code;
import com.hku.projectapi.Beans.NormalResponse;
import com.hku.projectapi.Service.EmailService;
import com.hku.projectapi.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*","null"})
@RestController
public class EmailVerificationController
{
    @Autowired
    private EmailService emailService;

    @PostMapping("/email_verification")
    public Object sendEmail(@RequestBody email_verification_code request)
    {
        String targetEmail = request.getEmail();
        try {
            // Read and get the time, see whether the last code is expiry
            String code = emailService.sendEmail(targetEmail);
            RegisterService.senEmail(targetEmail, code);
            // Set response
            NormalResponse response = new NormalResponse();
            response.setCode("00");
            response.setDescription("Success.");
            return response;
        }catch (Exception e)
        {
            NormalResponse response = new NormalResponse();
            response.setCode("04");
            response.setDescription("Something wrong, please check the email address.");
            return response;
        }
    }

    @GetMapping("/email_verification")
    public Object doVerify(@RequestParam String email, @RequestParam String code)
    {
        int res = emailService.doVerify(email, code);
        if(res == 0)
        {
            return new NormalResponse("00", "Pass verification.");
        }
        else if(res == 1)
        {
            return new NormalResponse("07", "Two codes do not match.");
        }
        else
        {
            return new NormalResponse("08", "No code generated.");
        }
    }
}
