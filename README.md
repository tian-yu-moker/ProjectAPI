# ProjectAPI
## 0.1 Overview
URL: http://120.77.98.16:8080  
## 0.2 Status Code
|   Code   |      Description      |      API      |
|----------|:-------------:|:-------------:|
| 99 | Update Internal server error. |All|
|98| No token, need to login first. |All|
|97| Token invalid, permission denied. |All|
| 00 |  Success. |All|
| 01 |    Incorrect password.   |Login|
| 02 | No such account, need to register one first. |Login|
| 03 | Account already exists. |Register|
| 04 | Something wrong, please check the email address. (send email fail) |Email Verification|
| 05 | No such knowledge question. |Delete knowledge question with ID|
| 06 | Update knowledge field error. |Update knowledge question with ID, field, value and type|
|07| Two email verification codes do not match. |Email Verification|
|08| No verification code generated. Please send one first. |Email Verification|
|09| Delete error| All delete functions|
|10| You are only allowed to delte your own assets. |All delete functions |
## 0.3 Token
Put into request header, name is token, value is the String obtained when login.  
Error response code.  
98: No token, need to login first.  
97: Token invalid, permission denied. (Incorrect token)  
```
{
    "code": "98",
    "description": "No valid token detected, please login first.",
    "token": "",
    "data": null
}
```


## 1. Login & Register
### 1.1 Login
URL: /login_register/login  
Type: POST  
```
Request Body:
{
  "email": "123@qq.com",
  "password": "123456789"
}
```
Response Code:  
00 success  
01 incorrect password  
02 no such account, need to register one first  
```
{
    "code": "00",
    "description": "Login success.",
    "data": {
        "email": "123@qq.com",
        "name": "123",
        "school": "HKU",
        "date": "2022-12-01",
        "type": "Student",
        "company": "Huawei",
        "isAdmin": 1
    }
}
```
### 1.2 Register
URL: /login_register/register  
Type: POST  
```
Request Body:
{
    "email": "123456@qq.com",
    "password": "123",
    "user_name": "123"
}
```
Response Code:  
00 success  
03 account already exists 
```
{
    "code": "00",
    "description": "Success."
}
```
### 1.3 School declaration
URL:/login_register/school
Ttpe:POST
```
Request Body:
{
    "email": "1948976547@qq.com",
    "school":"HKU"
}
```
Response Code:  
00 success  
02 no such account, need to register one first  
### 1.4 Company declaration
URL:/login_register/company
Ttpe:POST
```
Request Body:
{
    "email": "1948976547@qq.com",
    "company":"facebook"
}
```
Response Code:  
00 success  
02 no such account, need to register one first  
### 1.5 Type declaration
URL:/login_register/type
Ttpe:POST
```
Request Body:
{
    "email": "1948976547@qq.com",
    "type":"student"
}

```
Response Code:  
00 success  
02 no such account, need to register one first  
### 1.6 graduate time declaration
URL:/login_register/graduate_time
Type:POST
```
Request Body:
{
    "email": "1948976547@qq.com",
    "year":2022,
    "month":7
}
```
Response Code:  
00 success  
02 no such account, need to register one first  
## 2. Knowledge Service
### URL /knowledge_service
### 2.1 Create a knowledge question
Type: POST  
```
Request Body:
{
    "question_content": "What is operating system?",
    "answer_list": "answerid1;answerid2", (can be empty)
    "userid": "12345@qq.com",
    "comment": "commentid1;commentid2", (can be empty)
    "company": "Apple",
    "tag": "OS"
}
```
Response Code:  
00 Success  
99 Internal server error.  
```
{
    "code": "00",
    "description": "Upload success."
}
```
### 2.2 Query a knwoledge by id
Type: GET  
URL: /knowledge_service?uuid=4186f6f46bb94450ae1b3abe54517229  
Response Code:
00 Success  
05 No such knowledge question.  
```
{
    "code": "00",
    "description": "Success.",
    "data": {
        "knowledge_id": "4186f6f46bb94450ae1b3abe54517229",
        "question_content": "What is operating system?",
        "answer_list": "An operating system (OS) is system software that manages computer hardware, software resources, and provides common services for computer programs. ",
        "userid": "123@qq.com",
        "comment_list": "null",
        "company": "Apple",
        "tag": "Os",
        "upload_time": "2022-06-10 16:25:38"
    }
}
```
### 2.3 Delete a knwoledge by id
Type: DELETE  
URL: /knowledge_service?uuid=4186f6f46bb94450ae1b3abe54517229  
Response Code:
00 Success  
05 No such knowledge question.  
```
{
    "code": "00",
    "description": "Success."
}
```
### 2.4 Update a knowledge question
Type: PUT  
URL: /knowledge_service  
```
Request Body
{
    "uuid": "4186f6f46bb94450ae1b3abe54517229",
    "field": "company",
    "value": "BoC",
    "type": 0
}

where field is the column you want to update, value is the update content of that column
For type:
     * Type: 0 OR 1
     * 0: append behind (only for comment, and answer update)
     * 1: replace (for question content update)
```
### 2.5 Query knowledges by page
Type: POST  
URL: /knowledge_load
```
Request Body
{
    "pageFirst": 1,
    "pageSizeFirst": 22,
    "pageSecond": 1,
    "pageSizeSecond": 2,
    "pageThird": 1,
    "pageSizeThird": 2,
    "type": 0
}
type: 0, 1 (current only support 0)  
0: by time, the latest records  
1: by hot level
pageFirst: the page of knowledge questions
pageSecond: the page of answers of each question  
pageThird: the page of comments of each question  
```
Response code:  
00: success  
99: internal server error  
```
Response Body:  
{
    "code": "00",
    "description": "Success.",
    "token": null,
    "data": {
        "queryInfo": {
            "currentPage": 1,
            "pageSize": 1,
            "totalRecord": 22
        },
        "entities": [
            {
                "knowledgeId": "9e0239a4b4d542a582420a62eb9992a0",
                "question_content": "**type your question here**",
                "answer_list": "null",
                "userid": "12345@qq.com",
                "comment_list": "null",
                "company": "Google",
                "tag": "Database",
                "uploadTime": "2022-06-28T13:32:36.000+00:00",
                "answers": {
                    "queryInfo": {
                        "currentPage": 1,
                        "pageSize": 2,
                        "totalRecord": 4
                    },
                    "entities": [
                        {
                            "knowledgeAnswerId": "4b8bd4752a9e4207a19eee6e84f37d94",
                            "knowledgeId": "9e0239a4b4d542a582420a62eb9992a0",
                            "providerId": "12345@qq.com",
                            "content": "Tian Yu is handsome.",
                            "uploadTime": "2022-06-30T08:24:10.000+00:00",
                            "lastModifiedTime": "2022-06-30T08:24:10.000+00:00"
                        },
                        {
                            "knowledgeAnswerId": "6c6c6143af654042b963344247d0681d",
                            "knowledgeId": "9e0239a4b4d542a582420a62eb9992a0",
                            "providerId": "12345@qq.com",
                            "content": "Tian Yu is handsome.",
                            "uploadTime": "2022-06-30T08:24:11.000+00:00",
                            "lastModifiedTime": "2022-06-30T08:24:11.000+00:00"
                        }
                    ]
                },
                "comments": {
                    "queryInfo": {
                        "currentPage": 1,
                        "pageSize": 2,
                        "totalRecord": 5
                    },
                    "entities": [
                        {
                            "knowledgeCommentId": "1647e8bd900a4c6c832dbfaa40eea8ae",
                            "knowledgeId": "9e0239a4b4d542a582420a62eb9992a0",
                            "providerId": "12345@qq.com",
                            "content": "T.W. Chim is handsome.",
                            "uploadTime": "2022-06-30T09:02:19.000+00:00",
                            "lastModifiedTime": "2022-06-30T09:02:19.000+00:00"
                        },
                        {
                            "knowledgeCommentId": "30458469901b4c40ab7bc476da75b0cd",
                            "knowledgeId": "9e0239a4b4d542a582420a62eb9992a0",
                            "providerId": "12345@qq.com",
                            "content": "T.W. Chim is handsome.",
                            "uploadTime": "2022-06-30T09:02:18.000+00:00",
                            "lastModifiedTime": "2022-06-30T09:02:18.000+00:00"
                        }
                    ]
                }
            }
        ]
    }
}
```
### 2.6 Post knowledge answers
URL: /knowledge_service/answer  
Type: POST
```
Request Body: 
{
    "knowledgeId": "01fca8088ae6426b80dc252e32730fab",
    "provider": "123@qq.com",
    "content": "WANG Zhao is handsome."
}
```
Response Code:  
00 Success.  
99 Internal server error. 
### 2.7 Post knowledge comments
URL: /knowledge_service/comment  
Type: POST  
```
Request Body:
{
    "knowledgeId": "9e0239a4b4d542a582420a62eb9992a0",
    "provider": "12345@qq.com",
    "content": "T.W. Chim is handsome."
}
```
Response Code:  
00 Success.  
99 Internal server error. 
## 3. Email Verification Code
### URL: /email_verification  
### Note
The verification code can be expiried in 3 minutes.  
After 3 minutes, a new code will be generated and sent to the target email.  
Within 2 mins 30sec, the code will not be a new one. After 2 mins 30 sec, a new code will be generated.  
### 3.1 Send verification code
Type: POST  
```
Request Body:
{
    "email": "1948976547@qq.com"
}
```
Response Code:  
00 success  
04 Something wrong, please check the email address.  
```
{
    "code": "00",
    "description": "Success."
}
```
### 3.2 Check whether user input and generated code are matech
Type: GET  
URL: /email_verification?email=1948976547@qq.com&code=28423  
Response Code:  
00 success 
07 Two codes do not match.  
08  No code generated, need to send one first.
```
{
    "code": "00",
    "description": "Pass verification."
}
```
## 4.
