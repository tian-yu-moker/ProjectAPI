# ProjectAPI
## 0.1 Overview
URL: http://ty19980611
Ty19980611
:8080  
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
|11| Cancel collection successfully. | Collection like.|
|12| No such record.| Collection like. (add) |
|13| No such interview, please check.|Interview query.|
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
    “interview_id”: "123",
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
### 2.2 Query knowledge by id
Type: GET  
URL: /knowledge_service?uuid=2f88b7c85ce24599baa75f2ba5f69c96  
Response Code:
00 Success  
05 No such knowledge question.  
```
{
    "code": "00",
    "description": "Success.",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NDY4MTA2LCJpbmZvIjp7ImFkbWluIjowLCJ1c2VybmFtZSI6IjEyMyJ9fQ.ZNpIvYGf8PHyJcS-vJUZtKOdYnWnIaWIwdn1uHziBis",
    "data": {
        "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
        "question_content": "What is deep learning?",
        "answer_list": "answerid1;answerid2",
        "userid": "123@qq.com",
        "interviewId": "",
        "userName": "Ace Coder",
        "comment_list": "null",
        "company": "Apple",
        "tag": "Operating System",
        "uploadTime": "2022-07-07T12:50:30.000+00:00",
        "isLiked": 1,
        "answers": {
            "queryInfo": {
                "currentPage": 0,
                "pageSize": 0,
                "totalRecord": 1
            },
            "entities": [
                {
                    "knowledgeAnswerId": "d44ba665798044698a61d9ccec40f688",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "tianyu98@connect.hku.hk",
                    "userName": "Tian Yu",
                    "content": "Deep learning is a subset of machine learning, which is essentially a neural network with three or more layers.",
                    "uploadTime": "2022-07-07T15:14:15.000+00:00",
                    "lastModifiedTime": "2022-07-07T15:14:15.000+00:00"
                }
            ]
        },
        "comments": {
            "queryInfo": {
                "currentPage": 0,
                "pageSize": 0,
                "totalRecord": 14
            },
            "entities": [
                {
                    "knowledgeCommentId": "0e24eaa3a2e24d8a9aca23a2e2090b81",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "this is so interesting",
                    "uploadTime": "2022-07-09T10:14:49.000+00:00",
                    "lastModifiedTime": "2022-07-09T10:14:49.000+00:00"
                },
                {
                    "knowledgeCommentId": "162b4df98c6f4beba3f2fb9863f1fd38",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "hello",
                    "uploadTime": "2022-07-09T10:08:20.000+00:00",
                    "lastModifiedTime": "2022-07-09T10:08:20.000+00:00"
                },
                {
                    "knowledgeCommentId": "3231873f7d684b8f8d2f2b190a4e53a1",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd",
                    "uploadTime": "2022-07-09T10:41:36.000+00:00",
                    "lastModifiedTime": "2022-07-09T10:41:36.000+00:00"
                },
                {
                    "knowledgeCommentId": "46bd71532ff04a2db1628078f5bffa84",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "interesting",
                    "uploadTime": "2022-07-09T10:16:00.000+00:00",
                    "lastModifiedTime": "2022-07-09T10:16:00.000+00:00"
                },
                {
                    "knowledgeCommentId": "491ef676a33646c3b54e7693f45f06bb",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "this is a interesting question but i don't know the answer",
                    "uploadTime": "2022-07-09T09:54:23.000+00:00",
                    "lastModifiedTime": "2022-07-09T09:54:23.000+00:00"
                },
                {
                    "knowledgeCommentId": "7d433d7fdf924c4bb2a712312e9f5688",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "hello",
                    "uploadTime": "2022-07-09T10:25:45.000+00:00",
                    "lastModifiedTime": "2022-07-09T10:25:45.000+00:00"
                },
                {
                    "knowledgeCommentId": "8216b38ad93343488cbdd6c1e9dde399",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "hello?",
                    "uploadTime": "2022-07-09T10:07:21.000+00:00",
                    "lastModifiedTime": "2022-07-09T10:07:21.000+00:00"
                },
                {
                    "knowledgeCommentId": "863dded62ac746c9831d6bd50da4cdb6",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "se",
                    "uploadTime": "2022-07-07T13:10:30.000+00:00",
                    "lastModifiedTime": "2022-07-07T13:10:30.000+00:00"
                },
                {
                    "knowledgeCommentId": "9827f13c34f44d689117f15f1477f3af",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "ads",
                    "uploadTime": "2022-07-07T13:10:33.000+00:00",
                    "lastModifiedTime": "2022-07-07T13:10:33.000+00:00"
                },
                {
                    "knowledgeCommentId": "a698c6a966db4057b35b86d4c38e8965",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "hello",
                    "uploadTime": "2022-07-09T10:29:43.000+00:00",
                    "lastModifiedTime": "2022-07-09T10:29:43.000+00:00"
                },
                {
                    "knowledgeCommentId": "bb1370a9520d403ea28442f1854e87cc",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "deep learning is try to make computer think like a real human",
                    "uploadTime": "2022-07-09T09:55:51.000+00:00",
                    "lastModifiedTime": "2022-07-09T09:55:51.000+00:00"
                },
                {
                    "knowledgeCommentId": "ca96d57c4e4148de8238a528f2a55d47",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "hhhhhh",
                    "uploadTime": "2022-07-09T10:24:26.000+00:00",
                    "lastModifiedTime": "2022-07-09T10:24:26.000+00:00"
                },
                {
                    "knowledgeCommentId": "f3e982712725403fb86164d7135a6d91",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "hello",
                    "uploadTime": "2022-07-09T10:26:24.000+00:00",
                    "lastModifiedTime": "2022-07-09T10:26:24.000+00:00"
                },
                {
                    "knowledgeCommentId": "f5a894edd2fb4fd19cbea18af69d0b17",
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "providerId": "123@qq.com",
                    "userName": "Ace Coder",
                    "content": "let us see",
                    "uploadTime": "2022-07-07T13:10:27.000+00:00",
                    "lastModifiedTime": "2022-07-07T13:10:27.000+00:00"
                }
            ]
        }
    }
}
```
### 2.3 Delete knowledge by id
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
    "type": 0,
    "tag1": 0,
    "tag2": 0    
}
type: 0, 1 (current only support 0)  
0: by time, the latest records  
1: by hot level
pageFirst: the page of knowledge questions
pageSecond: the page of answers of each question  
pageThird: the page of comments of each question  
tag1: company  
tag2: question type  
```
Response code:  
00: success  
99: internal server error  
isLike: 0-no liked by current user.  
1-liked by current user.  
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
                "interviewId": "1232131",
                "comment_list": "null",
                "company": "Google",
                "tag": "Database",
                isLike: 1,
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
### 3.2 Check whether user input and generated code are match
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
## 4. Dictionary
### 4.1 Company and Question Type
URL: /dictionary/getCompanyAndType  
Type: GET  
Response code: 
00: Success.  
98: Token invalid.  
```
{
    "code": "00",
    "description": "Success.",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NDY4MTA2LCJpbmZvIjp7ImFkbWluIjowLCJ1c2VybmFtZSI6IjEyMyJ9fQ.ZNpIvYGf8PHyJcS-vJUZtKOdYnWnIaWIwdn1uHziBis",
    "data": {
        "companies": [
        {
            "id": 1,
            "label": "Alibaba"
        },
        {
            "id": 2,
            "label": "Amzon"
        },
        {
            "id": 3,
            "label": "Tencent"
        },
        {
            "id": 4,
            "label": "Google"
        },
        {
            "id": 5,
            "label": "Facebook"
        },
        {
            "id": 6,
            "label": "Microsoft"
        }
    ],
    "questionTagBeans": [
        {
            "id": 1,
            "label": "Network"
        },
        {
            "id": 2,
            "label": "Operation System"
        },
        {
            "id": 3,
            "label": "Database"
        },
        {
            "id": 4,
            "label": "Data Structure"
        },
        {
            "id": 5,
            "label": "Design Pattern"
        },
        {
            "id": 6,
            "label": "Algorithm"
        },
        {
            "id": 7,
            "label": "Others"
        }
        ]
    }
}
```
## 5. Collection Service
### 5.1 Add / delete a collection (knowledge or interview)
URL: /users_like  
Type:  POST  
```
Request Body:
{
    "id": "4186f6f46bb94450ae1b3abe54517228",
    "type": 0
}
type: 0 knowledge
1: interview
```
Response Code:  
00 Success.  
11 Delete success.  
12 No such record.    
### 5.2 Query collections
URL: /users_like  
Type: GET
```
{
    "code": "00",
    "description": "Success.",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NDY4MTA2LCJpbmZvIjp7ImFkbWluIjowLCJ1c2VybmFtZSI6IjEyMyJ9fQ.ZNpIvYGf8PHyJcS-vJUZtKOdYnWnIaWIwdn1uHziBis",
    "data": {
        "queryInfo": null,
        "entities": {
            "interviews": [
                {
                    "interviewId": "6f96514800484508a147b9cc47e0d3c4",
                    "providerId": "123@qq.com",
                    "providerName": null,
                    "title": "2022-01-15 Interview in Tencent.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-06T09:09:49.000+00:00",
                    "questions": null
                }
            ],
            "knowledge": [
                {
                    "knowledgeId": "25505886ee0242d08d89ab3368c66b74",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "6f96514800484508a147b9cc47e0d3c4",
                    "userName": null,
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:09:49.000+00:00",
                    "isLiked": 0,
                    "answers": null,
                    "comments": null
                },
                {
                    "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                    "question_content": "What is deep learning?",
                    "answer_list": "answerid1;answerid2",
                    "userid": "123@qq.com",
                    "interviewId": "",
                    "userName": null,
                    "comment_list": "null",
                    "company": "Apple",
                    "tag": "Operating System",
                    "uploadTime": "2022-07-07T12:50:30.000+00:00",
                    "isLiked": 0,
                    "answers": null,
                    "comments": null
                },
                {
                    "knowledgeId": "4186f6f46bb94450ae1b3abe54517229",
                    "question_content": "What is operating system?",
                    "answer_list": "An operating system (OS) is system software that manages computer hardware, software resources, and provides common services for computer programs. Time-sharing operating systems schedule tasks for efficient use of the system and may also include accounting software for cost allocation of processor time, mass storage, printing, and other resources. For hardware functions such as input and output and memory allocation, the operating system acts as an intermediary between programs and the computer hardware,[1][2] although the application code is usually executed directly by the hardware and frequently makes system calls to an OS function or is interrupted by it. Operating systems are found on many devices that contain a computer – from cellular phones and video game consoles to web servers and supercomputers. The dominant general-purpose[3] personal computer operating system is Microsoft Windows with a market share of around 76.45%. macOS by Apple Inc. is in second place (17.72%),  varieties of Linux are collectively in third place (1.73%).[4] In the mobile sector (including smartphones and tablets), Android's share is up to 72% in the year 2020.[5] According to third quarter 2016 data, Android's share on smartphones is dominant with 87.5 percent with a growth rate of 10.3 percent per year, followed by Apple's iOS with 12.1 percent with per year decrease in market share of 5.2 percent, while other operating systems amount to just 0.3 percent.[6] Linux distributions are dominant in the server and supercomputing sectors. Other specialized classes of operating systems (special-purpose operating systems),[3][7] such as embedded and real-time systems, exist for many applications. Security-focused operating systems also exist. Some operating systems have low system requirements (e.g. light-weight Linux distribution). Others may have higher system requirements.",
                    "userid": "123@qq.com",
                    "interviewId": null,
                    "userName": null,
                    "comment_list": "null",
                    "company": "BoC",
                    "tag": "Operating System",
                    "uploadTime": "2022-06-10T08:25:38.000+00:00",
                    "isLiked": 0,
                    "answers": null,
                    "comments": null
                },
                {
                    "knowledgeId": "49e68bce08c545c785a72ac406846f73",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "0d2a02c1a527422b903b4a269b290cdb",
                    "userName": null,
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:06:45.000+00:00",
                    "isLiked": 0,
                    "answers": null,
                    "comments": null
                }
            ]
        }
    }
}
```
Response Code:  
00 Success.  
## 6. Interview service
### 6.1 Create an interview
URL: /interview_service/create  
TYPE: POST  
```
Request Body:
{
    "title": "2022-01-15 Interview in Apple.",
    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
    "company": "Tencent",
    "location": "Beijing",
    "position": "Software engineer.",
    "level": "Entry",
    "interview_time": 1657542542666,
    "questions": [
        {
            "question_content": "What is distributed system?",
            "company": "Apple",
            "tag": "Network"
        },
        {
            "question_content": "Tell me something about 3 hand shake?",
            "company": "Apple",
            "tag": "Network"
        },
        {
            "question_content": "Have you ever useed the token?",
            "company": "Apple",
            "tag": "Others"
        }
    ]
}
Note: the interview_time: should be the timestamp in "ms".
```
### 6.2 Get an interview by id
URL: /interview_service/query?interviewId=6f96514800484508a147b9cc47e0d3c4  
Type: GET  
```
Response Body:

{
    "code": "00",
    "description": "Success.",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NDY4MTA2LCJpbmZvIjp7ImFkbWluIjowLCJ1c2VybmFtZSI6IjEyMyJ9fQ.ZNpIvYGf8PHyJcS-vJUZtKOdYnWnIaWIwdn1uHziBis",
    "data": {
        "interview": {
            "interviewId": "6f96514800484508a147b9cc47e0d3c4",
            "providerId": "123@qq.com",
            "providerName": "Ace Coder",
            "title": "2022-01-15 Interview in Tencent.",
            "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
            "company": "Tencent",
            "uploadTime": "2022-07-06T09:09:49.000+00:00",
            "isLiked": 1,
            "questions": null
        },
        "questions": [
            {
                "knowledgeId": "25505886ee0242d08d89ab3368c66b74",
                "question_content": "Tell me something about 3 hand shake?",
                "answer_list": null,
                "userid": "123@qq.com",
                "interviewId": "6f96514800484508a147b9cc47e0d3c4",
                "userName": "Ace Coder",
                "comment_list": null,
                "company": "Tencent",
                "tag": "Network",
                "uploadTime": "2022-07-06T09:09:49.000+00:00",
                "isLiked": 1,
                "answers": [],
                "comments": [
                    {
                        "knowledgeCommentId": "74bcd8a31caa41269d38055595188dfc",
                        "knowledgeId": "25505886ee0242d08d89ab3368c66b74",
                        "providerId": "123@qq.com",
                        "userName": "Ace Coder",
                        "content": "it is about connection ",
                        "uploadTime": "2022-07-07T14:11:37.000+00:00",
                        "lastModifiedTime": "2022-07-07T14:11:37.000+00:00"
                    },
                    {
                        "knowledgeCommentId": "e555e4543e574748b0764e1c582fe534",
                        "knowledgeId": "25505886ee0242d08d89ab3368c66b74",
                        "providerId": "12345@qq.com",
                        "userName": "Wang Zhao",
                        "content": "I dont know",
                        "uploadTime": "2022-07-07T12:20:16.000+00:00",
                        "lastModifiedTime": "2022-07-07T12:20:16.000+00:00"
                    }
                ]
            },
            {
                "knowledgeId": "a78e9b4bcca84082aa6e873cc1022b91",
                "question_content": "Have you ever useed the token?",
                "answer_list": null,
                "userid": "123@qq.com",
                "interviewId": "6f96514800484508a147b9cc47e0d3c4",
                "userName": "Ace Coder",
                "comment_list": null,
                "company": "Tencent",
                "tag": "Others",
                "uploadTime": "2022-07-06T09:09:49.000+00:00",
                "isLiked": 0,
                "answers": [],
                "comments": []
            },
            {
                "knowledgeId": "c626494803684d6baf1155456d0d6d5f",
                "question_content": "What is distributed system?",
                "answer_list": null,
                "userid": "123@qq.com",
                "interviewId": "6f96514800484508a147b9cc47e0d3c4",
                "userName": "Ace Coder",
                "comment_list": null,
                "company": "Tencent",
                "tag": "Network",
                "uploadTime": "2022-07-06T09:09:49.000+00:00",
                "isLiked": 0,
                "answers": [],
                "comments": [
                    {
                        "knowledgeCommentId": "6c52b8768cdb437ab4414baa6377b16a",
                        "knowledgeId": "c626494803684d6baf1155456d0d6d5f",
                        "providerId": "123@qq.com",
                        "userName": "Ace Coder",
                        "content": "idk",
                        "uploadTime": "2022-07-07T12:46:30.000+00:00",
                        "lastModifiedTime": "2022-07-07T12:46:30.000+00:00"
                    },
                    {
                        "knowledgeCommentId": "a6015d96d8ad4e4893644767467eb317",
                        "knowledgeId": "c626494803684d6baf1155456d0d6d5f",
                        "providerId": "123@qq.com",
                        "userName": "Ace Coder",
                        "content": "sadfasd",
                        "uploadTime": "2022-07-07T13:05:39.000+00:00",
                        "lastModifiedTime": "2022-07-07T13:05:39.000+00:00"
                    },
                    {
                        "knowledgeCommentId": "f3eadff08391453591897c7e50c8264c",
                        "knowledgeId": "c626494803684d6baf1155456d0d6d5f",
                        "providerId": "123@qq.com",
                        "userName": "Ace Coder",
                        "content": "anyone tell me?",
                        "uploadTime": "2022-07-07T12:54:07.000+00:00",
                        "lastModifiedTime": "2022-07-07T12:54:07.000+00:00"
                    }
                ]
            }
        ]
    }
}
```