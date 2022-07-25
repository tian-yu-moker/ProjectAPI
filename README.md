# ProjectAPI
## 0.1 Overview
URL: http://120.77.98.16:8080  
## 0.2 Status Code
|   Code   |      Description      |      API      |
|----------|:-------------:|:-------------:|
|99| Update Internal server error. |All|
|98| No token, need to login first. |All|
|97| Token invalid, permission denied. |All|
|00|  Success. |All|
|01|    Incorrect password.   |Login|
|02| No such account, need to register one first. |Login|
|03| Account already exists. |Register|
|04| Something wrong, please check the email address. (send email fail) |Email Verification|
|05| No such knowledge question. |Delete knowledge question with ID|
|06| Update knowledge field error. |Update knowledge question with ID, field, value and type|
|07| Two email verification codes do not match. |Email Verification|
|08| No verification code generated. Please send one first. |Email Verification|
|09| Delete error| All delete functions|
|10| You are only allowed to delte your own assets. |All delete functions |
|11| Cancel collection successfully. | Collection like.|
|12| No such record.| Collection like. (add) |
|13| No such interview, please check.|Interview query.|
|14| No such record.|Online judgement. (Get history by id)|
|15| No such record. |Get job by id|
|16| Sorry, no alumni found.|Query alumni|
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
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NzU5ODc3LCJpbmZvIjp7ImFkbWluIjoxLCJ1c2VybmFtZSI6IkFjZSBDb2RlciJ9fQ.BNFvtuqPHNtqN0DLr6Y7ld8tVC1-uO8GCemQjgTp4pA",
    "data": {
        "interviews": {
            "queryInfo": {
                "currentPage": 0,
                "pageSize": 0,
                "totalRecord": 9
            },
            "entities": [
                {
                    "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T13:03:02.000+00:00",
                    "level": "Entry",
                    "interviewTime": "2022-01-01",
                    "position": "Software engineer.",
                    "location": "Beijing",
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:41:32.000+00:00",
                    "level": "Entry",
                    "interviewTime": "2022-07-11",
                    "position": "Software engineer.",
                    "location": "Beijing",
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "495af9edc5184446b4cb147ef9a92299",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:35:38.000+00:00",
                    "level": "Entry",
                    "interviewTime": "2022-07-11",
                    "position": "Software engineer.",
                    "location": "Beijing",
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:35:18.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:33:43.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:33:20.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:30:59.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "37b704bf25db41b1b1991118ab0fe7d8",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "Interview ay Apple",
                    "description": "",
                    "company": "Apple",
                    "uploadTime": "2022-07-11T04:46:40.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "6f96514800484508a147b9cc47e0d3c4",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Tencent.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-06T09:09:49.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                }
            ]
        },
        "questions": {
            "queryInfo": {
                "currentPage": 0,
                "pageSize": 0,
                "totalRecord": 51
            },
            "entities": [
                {
                    "knowledgeId": "23ba27f411544616ac4ed161879e8213",
                    "question_content": "**_hello_**\n\nabc\n\n<font color=#1976D2> Ada </font>\n\n\n\n\n\n\n\ndwasd\n\n\nwda\n\ndwasd\n",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "",
                    "tag": "",
                    "uploadTime": "2022-07-25T13:01:11.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "39357ab5cc314dd793571f7a2b277993",
                                "knowledgeId": "23ba27f411544616ac4ed161879e8213",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "dsadwa",
                                "uploadTime": "2022-07-25T13:01:33.000+00:00",
                                "lastModifiedTime": "2022-07-25T13:01:33.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "332d8027d1fd4087b5cb9c01899397cb",
                    "question_content": "**hello**\n",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "",
                    "tag": "",
                    "uploadTime": "2022-07-25T03:27:55.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "c5e1972b25f34f3abafba1f3799d6d30",
                                "knowledgeId": "332d8027d1fd4087b5cb9c01899397cb",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "hello guys",
                                "uploadTime": "2022-07-25T10:12:17.000+00:00",
                                "lastModifiedTime": "2022-07-25T10:12:17.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "d04b281c9dce4f6380ac9b4eb7fdbba0",
                                "knowledgeId": "332d8027d1fd4087b5cb9c01899397cb",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "hello\n",
                                "uploadTime": "2022-07-25T10:15:07.000+00:00",
                                "lastModifiedTime": "2022-07-25T10:15:07.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "a4310869be15443fbbb8e0bfffb60f9e",
                    "question_content": "\"\"hello\"\"\n\nada\n",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "ABC",
                    "tag": "test",
                    "uploadTime": "2022-07-25T03:15:00.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "272a414d9620449aab8c46a94a7e925b",
                                "knowledgeId": "a4310869be15443fbbb8e0bfffb60f9e",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "hello\n",
                                "uploadTime": "2022-07-25T10:16:11.000+00:00",
                                "lastModifiedTime": "2022-07-25T10:16:11.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "e21c8c8445434eb3916707bc40314e8b",
                    "question_content": "print(\"test\")",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": null,
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-12T04:49:27.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "23e7c2e77b9f48bbaf2b678344cd4850",
                    "question_content": "print(\"test\")",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": null,
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-12T03:23:03.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "9a0a03b08aa64663b2a3c62bc3c4cbb0",
                    "question_content": "How to ensure the aviliability of the system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": null,
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-12T03:22:28.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                    "question_content": "#### Try to tell me the output of the following code\n- if age = 1\n- if age = 9\n```\nfunction sayHi() {\n  console.log(name);\n  console.log(age);\n  var name = 'Ayush';\n  let age = 21;\n}\n\nsayHi();\n```",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Tencent",
                    "tag": "Data Structures",
                    "uploadTime": "2022-07-11T16:32:28.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 3
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "801c75343c84482ca2c3506351e3a702",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sadfsadf",
                                "uploadTime": "2022-07-22T14:12:38.000+00:00",
                                "lastModifiedTime": "2022-07-22T14:12:38.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "b1536b0e1a594f6f81beac94450ade87",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "It should be 21",
                                "uploadTime": "2022-07-11T16:38:10.000+00:00",
                                "lastModifiedTime": "2022-07-11T16:38:10.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "d0b6fe968ed34e3a8fecfb6cdf756cdc",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "23 instead",
                                "uploadTime": "2022-07-14T08:13:02.000+00:00",
                                "lastModifiedTime": "2022-07-14T08:13:02.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 4
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "18c2a9a29ef14c3c8a7dab4f7d3c2128",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "idk",
                                "uploadTime": "2022-07-11T16:38:17.000+00:00",
                                "lastModifiedTime": "2022-07-11T16:38:17.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "33d07a0d1fd844d6936129e340424d20",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "i hv no idea",
                                "uploadTime": "2022-07-12T02:35:38.000+00:00",
                                "lastModifiedTime": "2022-07-12T02:35:38.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "3527bf61f8db41db83e12eff10ec9db8",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "This is quite hard...",
                                "uploadTime": "2022-07-11T16:33:02.000+00:00",
                                "lastModifiedTime": "2022-07-11T16:33:02.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "74c0c195852644f4a85ca88b4f44b069",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdf",
                                "uploadTime": "2022-07-22T14:12:45.000+00:00",
                                "lastModifiedTime": "2022-07-22T14:12:45.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T13:03:02.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 5
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "24f5b2ab51174e6fa6804ac41fe3adfc",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdfsadf",
                                "uploadTime": "2022-07-11T13:24:33.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:24:33.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "29cd649cf16c4ca0aae8cc1fa230f89b",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "fsadf",
                                "uploadTime": "2022-07-11T13:25:02.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:25:02.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "3b5675f229814feca42c7b569c6639cf",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdfasf",
                                "uploadTime": "2022-07-11T13:24:38.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:24:38.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "4259b7161e8741b1b1b26a1a8ea53e2c",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "dasdaw",
                                "uploadTime": "2022-07-25T13:05:59.000+00:00",
                                "lastModifiedTime": "2022-07-25T13:05:59.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "fccf888358314d0786e4dfa26a113240",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdfsadf",
                                "uploadTime": "2022-07-11T13:24:36.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:24:36.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "c9ab6e83334c4663b67b216f44da3e98",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "asdfsad",
                                "uploadTime": "2022-07-11T13:24:30.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:24:30.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "2f93298c8efa48db910b5bdfeb050981",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T13:03:02.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "cfca2e867f4248a2bbc2e87bc5a2c869",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T13:03:02.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "73b33de0eea643ba9340aa8934913d6a",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:41:32.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "3c31b000cce24ae19804e82dfe10de5e",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:41:32.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "3c31b000cce24ae19804e82dfe10de5a",
                                "knowledgeId": "3c31b000cce24ae19804e82dfe10de5e",
                                "providerId": "tianyu98@connect.hku.hk",
                                "userName": "Tian Yu",
                                "content": "I don't kno...",
                                "uploadTime": "2022-07-27T13:47:58.000+00:00",
                                "lastModifiedTime": "2022-07-27T13:47:58.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "98c549e6613e4bf59f9568c7f94b3db8",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:41:32.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "6bcc196e37d14d8abee1dff3769763e8",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "495af9edc5184446b4cb147ef9a92299",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:35:38.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "254c69974b0842cba3f6f0e598d7a7ce",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "495af9edc5184446b4cb147ef9a92299",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:35:38.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "d845d80cd0ac4e38a2a2722e63663fa4",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "495af9edc5184446b4cb147ef9a92299",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:35:38.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "17f22be1b59c48b18a4f431fc15e9c4c",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:35:18.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "e150aac4da8945aea75669afed22e9ea",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:35:18.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "829716d72b0d4caab97f587a3dedeb0f",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:35:18.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "4fd046d3e5fd479f95b3446815a2e87e",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:33:43.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "bc6ad5daaeda43b78042139284738446",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:33:43.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "96b9281cd8274164874ec8d1d1959aec",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:33:43.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "8987e0280b4c496e90218bb24979ecd2",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:33:20.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "b8afea4395234179b4e8eb7bdaf0bdcd",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:33:20.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "ea5c32f98c3f4ee5be59e8c6b14a99da",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:33:20.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "1b1483917216431aac794163e56886df",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:30:59.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "745f6b93bcd0422ab854376adf415605",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:30:59.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "3c4ba346e84f47ec92a52f3b939c13eb",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:30:59.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "586b8443c8cb42a7b55783c1a729cf7f",
                    "question_content": "**type your question here**",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Alibaba",
                    "tag": "",
                    "uploadTime": "2022-07-11T09:15:39.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "a737198d5f2e4e48897b61d73376fb3a",
                    "question_content": "**see the code**\n```\na = [1, 2]\nprint(a)\n```",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Google",
                    "tag": "Data Structures",
                    "uploadTime": "2022-07-11T08:23:28.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "cae0151438fe4cc3a6e7979df2d87222",
                    "question_content": "```\n```\nvar obj1 = {\n   x:  43,\n   z: function(){\n      return this.x;\n   }\n}\n      \n```\n```",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Tencent",
                    "tag": "Data Structures",
                    "uploadTime": "2022-07-11T08:20:49.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "4ed9e54cf23f435a9fb33050b4d5b68d",
                    "question_content": "Let me test",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Google",
                    "tag": "Database",
                    "uploadTime": "2022-07-11T08:05:07.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                    "question_content": "Tell me something about the JVM, the structure and their function.",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "37b704bf25db41b1b1991118ab0fe7d8",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Apple",
                    "tag": "Programming",
                    "uploadTime": "2022-07-11T04:46:40.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 12
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "11c1a7814ce7454ca5d13d6460781ce7",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "comment",
                                "uploadTime": "2022-07-11T06:55:02.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:55:02.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "5ac0a2d6735b4d67b82e961f0fd85f22",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdfa",
                                "uploadTime": "2022-07-11T06:57:07.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:57:07.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "64b51cf41b274142893109bdb7497585",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "ss",
                                "uploadTime": "2022-07-11T06:23:49.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:23:49.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "64d5455ee5a84d1e8d4fa979ebee80e2",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "as",
                                "uploadTime": "2022-07-11T07:52:09.000+00:00",
                                "lastModifiedTime": "2022-07-11T07:52:09.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "79725626e72e4fd6af60aad5e433723d",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "",
                                "uploadTime": "2022-07-11T06:55:32.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:55:32.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "804fb23e9dbb4d288190366ecb344bea",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "nnn",
                                "uploadTime": "2022-07-11T06:32:01.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:32:01.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "a000bb9485554e83834f72c86a1ff68a",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "testing",
                                "uploadTime": "2022-07-11T06:17:44.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:17:44.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "c40bdc29db0a4364870a8081a4e27878",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "newest",
                                "uploadTime": "2022-07-11T06:31:45.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:31:45.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "c60acf79e6cb4c64801195c48e8e8a10",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "LongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLong",
                                "uploadTime": "2022-07-11T07:01:53.000+00:00",
                                "lastModifiedTime": "2022-07-11T07:01:53.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "ce01bc4547e149259451c24de8483d13",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "testing 2s",
                                "uploadTime": "2022-07-11T06:10:06.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:10:06.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "d3a6f4d16b824f5094014c3f6599f765",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdfs",
                                "uploadTime": "2022-07-11T06:24:23.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:24:23.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "f10622ceaeec414282a97abe188a344a",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "testing",
                                "uploadTime": "2022-07-11T06:09:34.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:09:34.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "1f20a168fb134585bd1013f1d4c8d851",
                    "question_content": "what is the day",
                    "answer_list": "",
                    "userid": "123@qq.com",
                    "interviewId": "",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "ali",
                    "tag": "time",
                    "uploadTime": "2022-07-10T08:23:33.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 2
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "271dde5a05494c189c38bcf114d18b3b",
                                "knowledgeId": "1f20a168fb134585bd1013f1d4c8d851",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "new",
                                "uploadTime": "2022-07-11T07:52:31.000+00:00",
                                "lastModifiedTime": "2022-07-11T07:52:31.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "f376bd8af5b64161a223e9ed29604b4a",
                                "knowledgeId": "1f20a168fb134585bd1013f1d4c8d851",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "LongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLong",
                                "uploadTime": "2022-07-11T07:02:16.000+00:00",
                                "lastModifiedTime": "2022-07-11T07:02:16.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "a495617682e14ff0bfba5c0d762c32be",
                    "question_content": "What is operating system?",
                    "answer_list": "answerid1;answerid2",
                    "userid": "123@qq.com",
                    "interviewId": "123",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Apple",
                    "tag": "OS",
                    "uploadTime": "2022-07-10T08:18:51.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "3437b14e2274479184e59bb2d069ce1a",
                                "knowledgeId": "a495617682e14ff0bfba5c0d762c32be",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "test",
                                "uploadTime": "2022-07-11T07:52:43.000+00:00",
                                "lastModifiedTime": "2022-07-11T07:52:43.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "b535daae4dde4dcf99cc507b242cf732",
                    "question_content": "I love Tian Yu",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Alibaba",
                    "tag": "Operating System",
                    "uploadTime": "2022-07-07T14:22:17.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
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
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 2
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "39a3f16cc5934b4495710427813d12f6",
                                "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "123",
                                "uploadTime": "2022-07-11T13:39:33.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:39:33.000+00:00"
                            },
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
                },
                {
                    "knowledgeId": "2f7000e264a844348db6bfc251cc7745",
                    "question_content": "What is deep learning?",
                    "answer_list": "answerid1;answerid2",
                    "userid": "123@qq.com",
                    "interviewId": "",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Apple",
                    "tag": "Operating System",
                    "uploadTime": "2022-07-07T12:47:18.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
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
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 3
                        },
                        "entities": [
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
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
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
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 2
                        },
                        "entities": [
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
                    }
                },
                {
                    "knowledgeId": "4b65331abbcc42a4856de8b443112fa7",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "4353167357fc4fa4b5b11d3ba3dfbbdf",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:08:41.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "3f58420e48b84d04a3f6bd954c4fea9a",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "4353167357fc4fa4b5b11d3ba3dfbbdf",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:08:41.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "fa58c22ecf924ecdb3ddb04327d79976",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "4353167357fc4fa4b5b11d3ba3dfbbdf",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-06T09:08:41.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "59f819f9de424364b8382fbc713bbf90",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "0d2a02c1a527422b903b4a269b290cdb",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-06T09:06:45.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "49e68bce08c545c785a72ac406846f73",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "0d2a02c1a527422b903b4a269b290cdb",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:06:45.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "71a8e4aa68a2433d8bc659a474c5d52b",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "0d2a02c1a527422b903b4a269b290cdb",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:06:45.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "18cfb2e4f14049f7bf81eda878b8d73b",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "608b6899635043f88100941fa547e96b",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-06T09:05:24.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "b098554c6c5c4279aeec8a6e3cee1e76",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "608b6899635043f88100941fa547e96b",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:05:24.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "03991dfb30ce440cbeacbca24494a15c",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "608b6899635043f88100941fa547e96b",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:05:24.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "4186f6f46bb94450ae1b3abe54517229",
                    "question_content": "What is operating system?",
                    "answer_list": "An operating system (OS) is system software that manages computer hardware, software resources, and provides common services for computer programs. Time-sharing operating systems schedule tasks for efficient use of the system and may also include accounting software for cost allocation of processor time, mass storage, printing, and other resources. For hardware functions such as input and output and memory allocation, the operating system acts as an intermediary between programs and the computer hardware,[1][2] although the application code is usually executed directly by the hardware and frequently makes system calls to an OS function or is interrupted by it. Operating systems are found on many devices that contain a computer – from cellular phones and video game consoles to web servers and supercomputers. The dominant general-purpose[3] personal computer operating system is Microsoft Windows with a market share of around 76.45%. macOS by Apple Inc. is in second place (17.72%),  varieties of Linux are collectively in third place (1.73%).[4] In the mobile sector (including smartphones and tablets), Android's share is up to 72% in the year 2020.[5] According to third quarter 2016 data, Android's share on smartphones is dominant with 87.5 percent with a growth rate of 10.3 percent per year, followed by Apple's iOS with 12.1 percent with per year decrease in market share of 5.2 percent, while other operating systems amount to just 0.3 percent.[6] Linux distributions are dominant in the server and supercomputing sectors. Other specialized classes of operating systems (special-purpose operating systems),[3][7] such as embedded and real-time systems, exist for many applications. Security-focused operating systems also exist. Some operating systems have low system requirements (e.g. light-weight Linux distribution). Others may have higher system requirements.",
                    "userid": "123@qq.com",
                    "interviewId": null,
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "BoC",
                    "tag": "Operating System",
                    "uploadTime": "2022-06-10T08:25:38.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
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
    "interview_time": "2022-01-01",
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
### 6.2 Load interviews by page
URL: interview_service/load  
Type: POST  
```
Response Body:
{
    "pageFirst": 1,
    "pageSizeFirst": 10,
    // Following can be ignored
    "pageSecond": 1,
    "pageSizeSecond": 2,
    "pageThird": 1,
    "pageSizeThird": 2,
    "type": 0,
    "tag1": 0,
    "tag2": 0
}
```
The interviews with **all** of their questions will be returned.  
```
{
    "code": "00",
    "description": "Success.",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NDY4MTA2LCJpbmZvIjp7ImFkbWluIjowLCJ1c2VybmFtZSI6IjEyMyJ9fQ.ZNpIvYGf8PHyJcS-vJUZtKOdYnWnIaWIwdn1uHziBis",
    "data": {
        "queryInfo": {
            "currentPage": 1,
            "pageSize": 10,
            "totalRecord": 9
        },
        "entities": [
            {
                "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                "providerId": "123@qq.com",
                "providerName": "Ace Coder",
                "title": "2022-01-15 Interview in Apple.",
                "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                "company": "Tencent",
                "uploadTime": "2022-07-11T13:03:02.000+00:00",
                "level": "Entry",
                "interviewTime": "2022-01-01",
                "position": "Software engineer.",
                "location": "Beijing",
                "isLiked": 10,
                "questions": {
                    "queryInfo": {
                        "currentPage": 0,
                        "pageSize": 0,
                        "totalRecord": 3
                    },
                    "entities": [
                        {
                            "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                            "question_content": "Tell me something about 3 hand shake?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T13:03:02.000+00:00",
                            "isLiked": 1,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "2f93298c8efa48db910b5bdfeb050981",
                            "question_content": "Have you ever useed the token?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Others",
                            "uploadTime": "2022-07-11T13:03:02.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "cfca2e867f4248a2bbc2e87bc5a2c869",
                            "question_content": "What is distributed system?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T13:03:02.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        }
                    ]
                }
            },
            {
                "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                "providerId": "123@qq.com",
                "providerName": "Ace Coder",
                "title": "2022-01-15 Interview in Apple.",
                "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                "company": "Tencent",
                "uploadTime": "2022-07-11T12:41:32.000+00:00",
                "level": "Entry",
                "interviewTime": "2022-07-11",
                "position": "Software engineer.",
                "location": "Beijing",
                "isLiked": 10,
                "questions": {
                    "queryInfo": {
                        "currentPage": 0,
                        "pageSize": 0,
                        "totalRecord": 3
                    },
                    "entities": [
                        {
                            "knowledgeId": "3c31b000cce24ae19804e82dfe10de5e",
                            "question_content": "Tell me something about 3 hand shake?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:41:32.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "73b33de0eea643ba9340aa8934913d6a",
                            "question_content": "What is distributed system?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:41:32.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "98c549e6613e4bf59f9568c7f94b3db8",
                            "question_content": "Have you ever useed the token?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Others",
                            "uploadTime": "2022-07-11T12:41:32.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        }
                    ]
                }
            },
            {
                "interviewId": "495af9edc5184446b4cb147ef9a92299",
                "providerId": "123@qq.com",
                "providerName": "Ace Coder",
                "title": "2022-01-15 Interview in Apple.",
                "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                "company": "Tencent",
                "uploadTime": "2022-07-11T12:35:38.000+00:00",
                "level": "Entry",
                "interviewTime": "2022-07-11",
                "position": "Software engineer.",
                "location": "Beijing",
                "isLiked": 10,
                "questions": {
                    "queryInfo": {
                        "currentPage": 0,
                        "pageSize": 0,
                        "totalRecord": 3
                    },
                    "entities": [
                        {
                            "knowledgeId": "254c69974b0842cba3f6f0e598d7a7ce",
                            "question_content": "Have you ever useed the token?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "495af9edc5184446b4cb147ef9a92299",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Others",
                            "uploadTime": "2022-07-11T12:35:38.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "6bcc196e37d14d8abee1dff3769763e8",
                            "question_content": "What is distributed system?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "495af9edc5184446b4cb147ef9a92299",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:35:38.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "d845d80cd0ac4e38a2a2722e63663fa4",
                            "question_content": "Tell me something about 3 hand shake?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "495af9edc5184446b4cb147ef9a92299",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:35:38.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        }
                    ]
                }
            },
            {
                "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                "providerId": "123@qq.com",
                "providerName": "Ace Coder",
                "title": "2022-01-15 Interview in Apple.",
                "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                "company": "Tencent",
                "uploadTime": "2022-07-11T12:35:18.000+00:00",
                "level": null,
                "interviewTime": null,
                "position": null,
                "location": null,
                "isLiked": 10,
                "questions": {
                    "queryInfo": {
                        "currentPage": 0,
                        "pageSize": 0,
                        "totalRecord": 3
                    },
                    "entities": [
                        {
                            "knowledgeId": "17f22be1b59c48b18a4f431fc15e9c4c",
                            "question_content": "Tell me something about 3 hand shake?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:35:18.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "829716d72b0d4caab97f587a3dedeb0f",
                            "question_content": "What is distributed system?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:35:18.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "e150aac4da8945aea75669afed22e9ea",
                            "question_content": "Have you ever useed the token?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Others",
                            "uploadTime": "2022-07-11T12:35:18.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        }
                    ]
                }
            },
            {
                "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                "providerId": "123@qq.com",
                "providerName": "Ace Coder",
                "title": "2022-01-15 Interview in Apple.",
                "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                "company": "Tencent",
                "uploadTime": "2022-07-11T12:33:43.000+00:00",
                "level": null,
                "interviewTime": null,
                "position": null,
                "location": null,
                "isLiked": 10,
                "questions": {
                    "queryInfo": {
                        "currentPage": 0,
                        "pageSize": 0,
                        "totalRecord": 3
                    },
                    "entities": [
                        {
                            "knowledgeId": "4fd046d3e5fd479f95b3446815a2e87e",
                            "question_content": "What is distributed system?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:33:43.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "96b9281cd8274164874ec8d1d1959aec",
                            "question_content": "Have you ever useed the token?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Others",
                            "uploadTime": "2022-07-11T12:33:43.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "bc6ad5daaeda43b78042139284738446",
                            "question_content": "Tell me something about 3 hand shake?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:33:43.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        }
                    ]
                }
            },
            {
                "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                "providerId": "123@qq.com",
                "providerName": "Ace Coder",
                "title": "2022-01-15 Interview in Apple.",
                "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                "company": "Tencent",
                "uploadTime": "2022-07-11T12:33:20.000+00:00",
                "level": null,
                "interviewTime": null,
                "position": null,
                "location": null,
                "isLiked": 10,
                "questions": {
                    "queryInfo": {
                        "currentPage": 0,
                        "pageSize": 0,
                        "totalRecord": 3
                    },
                    "entities": [
                        {
                            "knowledgeId": "8987e0280b4c496e90218bb24979ecd2",
                            "question_content": "What is distributed system?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:33:20.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "b8afea4395234179b4e8eb7bdaf0bdcd",
                            "question_content": "Have you ever useed the token?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Others",
                            "uploadTime": "2022-07-11T12:33:20.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "ea5c32f98c3f4ee5be59e8c6b14a99da",
                            "question_content": "Tell me something about 3 hand shake?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:33:20.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        }
                    ]
                }
            },
            {
                "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                "providerId": "123@qq.com",
                "providerName": "Ace Coder",
                "title": "2022-01-15 Interview in Apple.",
                "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                "company": "Tencent",
                "uploadTime": "2022-07-11T12:30:59.000+00:00",
                "level": null,
                "interviewTime": null,
                "position": null,
                "location": null,
                "isLiked": 10,
                "questions": {
                    "queryInfo": {
                        "currentPage": 0,
                        "pageSize": 0,
                        "totalRecord": 3
                    },
                    "entities": [
                        {
                            "knowledgeId": "1b1483917216431aac794163e56886df",
                            "question_content": "Have you ever useed the token?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Others",
                            "uploadTime": "2022-07-11T12:30:59.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "3c4ba346e84f47ec92a52f3b939c13eb",
                            "question_content": "Tell me something about 3 hand shake?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:30:59.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "745f6b93bcd0422ab854376adf415605",
                            "question_content": "What is distributed system?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-11T12:30:59.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        }
                    ]
                }
            },
            {
                "interviewId": "37b704bf25db41b1b1991118ab0fe7d8",
                "providerId": "123@qq.com",
                "providerName": "Ace Coder",
                "title": "Interview ay Apple",
                "description": "",
                "company": "Apple",
                "uploadTime": "2022-07-11T04:46:40.000+00:00",
                "level": null,
                "interviewTime": null,
                "position": null,
                "location": null,
                "isLiked": 10,
                "questions": {
                    "queryInfo": {
                        "currentPage": 0,
                        "pageSize": 0,
                        "totalRecord": 1
                    },
                    "entities": [
                        {
                            "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                            "question_content": "Tell me something about the JVM, the structure and their function.",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "37b704bf25db41b1b1991118ab0fe7d8",
                            "userName": null,
                            "comment_list": null,
                            "company": "Apple",
                            "tag": "Programming",
                            "uploadTime": "2022-07-11T04:46:40.000+00:00",
                            "isLiked": 1,
                            "answers": null,
                            "comments": null
                        }
                    ]
                }
            },
            {
                "interviewId": "6f96514800484508a147b9cc47e0d3c4",
                "providerId": "123@qq.com",
                "providerName": "Ace Coder",
                "title": "2022-01-15 Interview in Tencent.",
                "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                "company": "Tencent",
                "uploadTime": "2022-07-06T09:09:49.000+00:00",
                "level": null,
                "interviewTime": null,
                "position": null,
                "location": null,
                "isLiked": 1,
                "questions": {
                    "queryInfo": {
                        "currentPage": 0,
                        "pageSize": 0,
                        "totalRecord": 3
                    },
                    "entities": [
                        {
                            "knowledgeId": "25505886ee0242d08d89ab3368c66b74",
                            "question_content": "Tell me something about 3 hand shake?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "6f96514800484508a147b9cc47e0d3c4",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-06T09:09:49.000+00:00",
                            "isLiked": 1,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "a78e9b4bcca84082aa6e873cc1022b91",
                            "question_content": "Have you ever useed the token?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "6f96514800484508a147b9cc47e0d3c4",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Others",
                            "uploadTime": "2022-07-06T09:09:49.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        },
                        {
                            "knowledgeId": "c626494803684d6baf1155456d0d6d5f",
                            "question_content": "What is distributed system?",
                            "answer_list": null,
                            "userid": "Ace Coder",
                            "interviewId": "6f96514800484508a147b9cc47e0d3c4",
                            "userName": null,
                            "comment_list": null,
                            "company": "Tencent",
                            "tag": "Network",
                            "uploadTime": "2022-07-06T09:09:49.000+00:00",
                            "isLiked": 0,
                            "answers": null,
                            "comments": null
                        }
                    ]
                }
            }
        ]
    }
}
```
### 6.3 Get an interview by id
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
## 7. Py post
### 7.1 Load all my posts
URL: my_posts  
Type: GET  
```
{
    "code": "00",
    "description": "Success.",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NzU5ODc3LCJpbmZvIjp7ImFkbWluIjoxLCJ1c2VybmFtZSI6IkFjZSBDb2RlciJ9fQ.BNFvtuqPHNtqN0DLr6Y7ld8tVC1-uO8GCemQjgTp4pA",
    "data": {
        "interviews": {
            "queryInfo": {
                "currentPage": 0,
                "pageSize": 0,
                "totalRecord": 9
            },
            "entities": [
                {
                    "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T13:03:02.000+00:00",
                    "level": "Entry",
                    "interviewTime": "2022-01-01",
                    "position": "Software engineer.",
                    "location": "Beijing",
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:41:32.000+00:00",
                    "level": "Entry",
                    "interviewTime": "2022-07-11",
                    "position": "Software engineer.",
                    "location": "Beijing",
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "495af9edc5184446b4cb147ef9a92299",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:35:38.000+00:00",
                    "level": "Entry",
                    "interviewTime": "2022-07-11",
                    "position": "Software engineer.",
                    "location": "Beijing",
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:35:18.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:33:43.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:33:20.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Apple.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-11T12:30:59.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "37b704bf25db41b1b1991118ab0fe7d8",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "Interview ay Apple",
                    "description": "",
                    "company": "Apple",
                    "uploadTime": "2022-07-11T04:46:40.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                },
                {
                    "interviewId": "6f96514800484508a147b9cc47e0d3c4",
                    "userId": "123@qq.com",
                    "userName": "Ace Coder",
                    "title": "2022-01-15 Interview in Tencent.",
                    "description": "About 2 hours interview, which is quite long. The knowledeg asked includeds several aspects, suach as operation system, algorithm, network and database.",
                    "company": "Tencent",
                    "uploadTime": "2022-07-06T09:09:49.000+00:00",
                    "level": null,
                    "interviewTime": null,
                    "position": null,
                    "location": null,
                    "isLiked": 0,
                    "questions": null
                }
            ]
        },
        "questions": {
            "queryInfo": {
                "currentPage": 0,
                "pageSize": 0,
                "totalRecord": 51
            },
            "entities": [
                {
                    "knowledgeId": "23ba27f411544616ac4ed161879e8213",
                    "question_content": "**_hello_**\n\nabc\n\n<font color=#1976D2> Ada </font>\n\n\n\n\n\n\n\ndwasd\n\n\nwda\n\ndwasd\n",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "",
                    "tag": "",
                    "uploadTime": "2022-07-25T13:01:11.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "39357ab5cc314dd793571f7a2b277993",
                                "knowledgeId": "23ba27f411544616ac4ed161879e8213",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "dsadwa",
                                "uploadTime": "2022-07-25T13:01:33.000+00:00",
                                "lastModifiedTime": "2022-07-25T13:01:33.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "332d8027d1fd4087b5cb9c01899397cb",
                    "question_content": "**hello**\n",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "",
                    "tag": "",
                    "uploadTime": "2022-07-25T03:27:55.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "c5e1972b25f34f3abafba1f3799d6d30",
                                "knowledgeId": "332d8027d1fd4087b5cb9c01899397cb",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "hello guys",
                                "uploadTime": "2022-07-25T10:12:17.000+00:00",
                                "lastModifiedTime": "2022-07-25T10:12:17.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "d04b281c9dce4f6380ac9b4eb7fdbba0",
                                "knowledgeId": "332d8027d1fd4087b5cb9c01899397cb",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "hello\n",
                                "uploadTime": "2022-07-25T10:15:07.000+00:00",
                                "lastModifiedTime": "2022-07-25T10:15:07.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "a4310869be15443fbbb8e0bfffb60f9e",
                    "question_content": "\"\"hello\"\"\n\nada\n",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "ABC",
                    "tag": "test",
                    "uploadTime": "2022-07-25T03:15:00.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "272a414d9620449aab8c46a94a7e925b",
                                "knowledgeId": "a4310869be15443fbbb8e0bfffb60f9e",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "hello\n",
                                "uploadTime": "2022-07-25T10:16:11.000+00:00",
                                "lastModifiedTime": "2022-07-25T10:16:11.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "e21c8c8445434eb3916707bc40314e8b",
                    "question_content": "print(\"test\")",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": null,
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-12T04:49:27.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "23e7c2e77b9f48bbaf2b678344cd4850",
                    "question_content": "print(\"test\")",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": null,
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-12T03:23:03.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "9a0a03b08aa64663b2a3c62bc3c4cbb0",
                    "question_content": "How to ensure the aviliability of the system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": null,
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-12T03:22:28.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                    "question_content": "#### Try to tell me the output of the following code\n- if age = 1\n- if age = 9\n```\nfunction sayHi() {\n  console.log(name);\n  console.log(age);\n  var name = 'Ayush';\n  let age = 21;\n}\n\nsayHi();\n```",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Tencent",
                    "tag": "Data Structures",
                    "uploadTime": "2022-07-11T16:32:28.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 3
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "801c75343c84482ca2c3506351e3a702",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sadfsadf",
                                "uploadTime": "2022-07-22T14:12:38.000+00:00",
                                "lastModifiedTime": "2022-07-22T14:12:38.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "b1536b0e1a594f6f81beac94450ade87",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "It should be 21",
                                "uploadTime": "2022-07-11T16:38:10.000+00:00",
                                "lastModifiedTime": "2022-07-11T16:38:10.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "d0b6fe968ed34e3a8fecfb6cdf756cdc",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "23 instead",
                                "uploadTime": "2022-07-14T08:13:02.000+00:00",
                                "lastModifiedTime": "2022-07-14T08:13:02.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 4
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "18c2a9a29ef14c3c8a7dab4f7d3c2128",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "idk",
                                "uploadTime": "2022-07-11T16:38:17.000+00:00",
                                "lastModifiedTime": "2022-07-11T16:38:17.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "33d07a0d1fd844d6936129e340424d20",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "i hv no idea",
                                "uploadTime": "2022-07-12T02:35:38.000+00:00",
                                "lastModifiedTime": "2022-07-12T02:35:38.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "3527bf61f8db41db83e12eff10ec9db8",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "This is quite hard...",
                                "uploadTime": "2022-07-11T16:33:02.000+00:00",
                                "lastModifiedTime": "2022-07-11T16:33:02.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "74c0c195852644f4a85ca88b4f44b069",
                                "knowledgeId": "48e6ce005e5a44f7b001c9b139065371",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdf",
                                "uploadTime": "2022-07-22T14:12:45.000+00:00",
                                "lastModifiedTime": "2022-07-22T14:12:45.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T13:03:02.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 5
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "24f5b2ab51174e6fa6804ac41fe3adfc",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdfsadf",
                                "uploadTime": "2022-07-11T13:24:33.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:24:33.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "29cd649cf16c4ca0aae8cc1fa230f89b",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "fsadf",
                                "uploadTime": "2022-07-11T13:25:02.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:25:02.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "3b5675f229814feca42c7b569c6639cf",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdfasf",
                                "uploadTime": "2022-07-11T13:24:38.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:24:38.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "4259b7161e8741b1b1b26a1a8ea53e2c",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "dasdaw",
                                "uploadTime": "2022-07-25T13:05:59.000+00:00",
                                "lastModifiedTime": "2022-07-25T13:05:59.000+00:00"
                            },
                            {
                                "knowledgeAnswerId": "fccf888358314d0786e4dfa26a113240",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdfsadf",
                                "uploadTime": "2022-07-11T13:24:36.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:24:36.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "c9ab6e83334c4663b67b216f44da3e98",
                                "knowledgeId": "0e2fa8e6acc342d7ad0f6ba0c952882d",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "asdfsad",
                                "uploadTime": "2022-07-11T13:24:30.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:24:30.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "2f93298c8efa48db910b5bdfeb050981",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T13:03:02.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "cfca2e867f4248a2bbc2e87bc5a2c869",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "b6b985ca775442ed9c6f9539dd4739e6",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T13:03:02.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "73b33de0eea643ba9340aa8934913d6a",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:41:32.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "3c31b000cce24ae19804e82dfe10de5e",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:41:32.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "3c31b000cce24ae19804e82dfe10de5a",
                                "knowledgeId": "3c31b000cce24ae19804e82dfe10de5e",
                                "providerId": "tianyu98@connect.hku.hk",
                                "userName": "Tian Yu",
                                "content": "I don't kno...",
                                "uploadTime": "2022-07-27T13:47:58.000+00:00",
                                "lastModifiedTime": "2022-07-27T13:47:58.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "98c549e6613e4bf59f9568c7f94b3db8",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "1f98d10a816447a6ade096fd263af5fc",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:41:32.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "6bcc196e37d14d8abee1dff3769763e8",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "495af9edc5184446b4cb147ef9a92299",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:35:38.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "254c69974b0842cba3f6f0e598d7a7ce",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "495af9edc5184446b4cb147ef9a92299",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:35:38.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "d845d80cd0ac4e38a2a2722e63663fa4",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "495af9edc5184446b4cb147ef9a92299",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:35:38.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "17f22be1b59c48b18a4f431fc15e9c4c",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:35:18.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "e150aac4da8945aea75669afed22e9ea",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:35:18.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "829716d72b0d4caab97f587a3dedeb0f",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "db6a023e5efa4d13ae279835aef826b2",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:35:18.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "4fd046d3e5fd479f95b3446815a2e87e",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:33:43.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "bc6ad5daaeda43b78042139284738446",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:33:43.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "96b9281cd8274164874ec8d1d1959aec",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "fc5341d4fc3442bfa1c728a298da529d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:33:43.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "8987e0280b4c496e90218bb24979ecd2",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:33:20.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "b8afea4395234179b4e8eb7bdaf0bdcd",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:33:20.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "ea5c32f98c3f4ee5be59e8c6b14a99da",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "be8727bbdbef477a8b8ad8bb33e81c2d",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:33:20.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "1b1483917216431aac794163e56886df",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-11T12:30:59.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "745f6b93bcd0422ab854376adf415605",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:30:59.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "3c4ba346e84f47ec92a52f3b939c13eb",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "01271c8bc5d24c91a5b8695bef3a63d0",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-11T12:30:59.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "586b8443c8cb42a7b55783c1a729cf7f",
                    "question_content": "**type your question here**",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Alibaba",
                    "tag": "",
                    "uploadTime": "2022-07-11T09:15:39.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "a737198d5f2e4e48897b61d73376fb3a",
                    "question_content": "**see the code**\n```\na = [1, 2]\nprint(a)\n```",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Google",
                    "tag": "Data Structures",
                    "uploadTime": "2022-07-11T08:23:28.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "cae0151438fe4cc3a6e7979df2d87222",
                    "question_content": "```\n```\nvar obj1 = {\n   x:  43,\n   z: function(){\n      return this.x;\n   }\n}\n      \n```\n```",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Tencent",
                    "tag": "Data Structures",
                    "uploadTime": "2022-07-11T08:20:49.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "4ed9e54cf23f435a9fb33050b4d5b68d",
                    "question_content": "Let me test",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Google",
                    "tag": "Database",
                    "uploadTime": "2022-07-11T08:05:07.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                    "question_content": "Tell me something about the JVM, the structure and their function.",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "37b704bf25db41b1b1991118ab0fe7d8",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Apple",
                    "tag": "Programming",
                    "uploadTime": "2022-07-11T04:46:40.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 12
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "11c1a7814ce7454ca5d13d6460781ce7",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "comment",
                                "uploadTime": "2022-07-11T06:55:02.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:55:02.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "5ac0a2d6735b4d67b82e961f0fd85f22",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdfa",
                                "uploadTime": "2022-07-11T06:57:07.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:57:07.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "64b51cf41b274142893109bdb7497585",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "ss",
                                "uploadTime": "2022-07-11T06:23:49.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:23:49.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "64d5455ee5a84d1e8d4fa979ebee80e2",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "as",
                                "uploadTime": "2022-07-11T07:52:09.000+00:00",
                                "lastModifiedTime": "2022-07-11T07:52:09.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "79725626e72e4fd6af60aad5e433723d",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "",
                                "uploadTime": "2022-07-11T06:55:32.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:55:32.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "804fb23e9dbb4d288190366ecb344bea",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "nnn",
                                "uploadTime": "2022-07-11T06:32:01.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:32:01.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "a000bb9485554e83834f72c86a1ff68a",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "testing",
                                "uploadTime": "2022-07-11T06:17:44.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:17:44.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "c40bdc29db0a4364870a8081a4e27878",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "newest",
                                "uploadTime": "2022-07-11T06:31:45.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:31:45.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "c60acf79e6cb4c64801195c48e8e8a10",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "LongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLong",
                                "uploadTime": "2022-07-11T07:01:53.000+00:00",
                                "lastModifiedTime": "2022-07-11T07:01:53.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "ce01bc4547e149259451c24de8483d13",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "testing 2s",
                                "uploadTime": "2022-07-11T06:10:06.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:10:06.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "d3a6f4d16b824f5094014c3f6599f765",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "sdfs",
                                "uploadTime": "2022-07-11T06:24:23.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:24:23.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "f10622ceaeec414282a97abe188a344a",
                                "knowledgeId": "3743c65b31cc47109c3c5960e432a3db",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "testing",
                                "uploadTime": "2022-07-11T06:09:34.000+00:00",
                                "lastModifiedTime": "2022-07-11T06:09:34.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "1f20a168fb134585bd1013f1d4c8d851",
                    "question_content": "what is the day",
                    "answer_list": "",
                    "userid": "123@qq.com",
                    "interviewId": "",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "ali",
                    "tag": "time",
                    "uploadTime": "2022-07-10T08:23:33.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 2
                        },
                        "entities": [
                            {
                                "knowledgeCommentId": "271dde5a05494c189c38bcf114d18b3b",
                                "knowledgeId": "1f20a168fb134585bd1013f1d4c8d851",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "new",
                                "uploadTime": "2022-07-11T07:52:31.000+00:00",
                                "lastModifiedTime": "2022-07-11T07:52:31.000+00:00"
                            },
                            {
                                "knowledgeCommentId": "f376bd8af5b64161a223e9ed29604b4a",
                                "knowledgeId": "1f20a168fb134585bd1013f1d4c8d851",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "LongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLongLong",
                                "uploadTime": "2022-07-11T07:02:16.000+00:00",
                                "lastModifiedTime": "2022-07-11T07:02:16.000+00:00"
                            }
                        ]
                    }
                },
                {
                    "knowledgeId": "a495617682e14ff0bfba5c0d762c32be",
                    "question_content": "What is operating system?",
                    "answer_list": "answerid1;answerid2",
                    "userid": "123@qq.com",
                    "interviewId": "123",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Apple",
                    "tag": "OS",
                    "uploadTime": "2022-07-10T08:18:51.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 1
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "3437b14e2274479184e59bb2d069ce1a",
                                "knowledgeId": "a495617682e14ff0bfba5c0d762c32be",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "test",
                                "uploadTime": "2022-07-11T07:52:43.000+00:00",
                                "lastModifiedTime": "2022-07-11T07:52:43.000+00:00"
                            }
                        ]
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "b535daae4dde4dcf99cc507b242cf732",
                    "question_content": "I love Tian Yu",
                    "answer_list": "null",
                    "userid": "123@qq.com",
                    "interviewId": "null",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Alibaba",
                    "tag": "Operating System",
                    "uploadTime": "2022-07-07T14:22:17.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
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
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 2
                        },
                        "entities": [
                            {
                                "knowledgeAnswerId": "39a3f16cc5934b4495710427813d12f6",
                                "knowledgeId": "2f88b7c85ce24599baa75f2ba5f69c96",
                                "providerId": "123@qq.com",
                                "userName": "Ace Coder",
                                "content": "123",
                                "uploadTime": "2022-07-11T13:39:33.000+00:00",
                                "lastModifiedTime": "2022-07-11T13:39:33.000+00:00"
                            },
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
                },
                {
                    "knowledgeId": "2f7000e264a844348db6bfc251cc7745",
                    "question_content": "What is deep learning?",
                    "answer_list": "answerid1;answerid2",
                    "userid": "123@qq.com",
                    "interviewId": "",
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "Apple",
                    "tag": "Operating System",
                    "uploadTime": "2022-07-07T12:47:18.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
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
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 3
                        },
                        "entities": [
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
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
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
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 2
                        },
                        "entities": [
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
                    }
                },
                {
                    "knowledgeId": "4b65331abbcc42a4856de8b443112fa7",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "4353167357fc4fa4b5b11d3ba3dfbbdf",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:08:41.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "3f58420e48b84d04a3f6bd954c4fea9a",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "4353167357fc4fa4b5b11d3ba3dfbbdf",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:08:41.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "fa58c22ecf924ecdb3ddb04327d79976",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "4353167357fc4fa4b5b11d3ba3dfbbdf",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-06T09:08:41.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "59f819f9de424364b8382fbc713bbf90",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "0d2a02c1a527422b903b4a269b290cdb",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-06T09:06:45.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "49e68bce08c545c785a72ac406846f73",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "0d2a02c1a527422b903b4a269b290cdb",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:06:45.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "71a8e4aa68a2433d8bc659a474c5d52b",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "0d2a02c1a527422b903b4a269b290cdb",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:06:45.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "18cfb2e4f14049f7bf81eda878b8d73b",
                    "question_content": "Have you ever useed the token?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "608b6899635043f88100941fa547e96b",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Others",
                    "uploadTime": "2022-07-06T09:05:24.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "b098554c6c5c4279aeec8a6e3cee1e76",
                    "question_content": "Tell me something about 3 hand shake?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "608b6899635043f88100941fa547e96b",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:05:24.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "03991dfb30ce440cbeacbca24494a15c",
                    "question_content": "What is distributed system?",
                    "answer_list": null,
                    "userid": "123@qq.com",
                    "interviewId": "608b6899635043f88100941fa547e96b",
                    "userName": "Ace Coder",
                    "comment_list": null,
                    "company": "Tencent",
                    "tag": "Network",
                    "uploadTime": "2022-07-06T09:05:24.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                },
                {
                    "knowledgeId": "4186f6f46bb94450ae1b3abe54517229",
                    "question_content": "What is operating system?",
                    "answer_list": "An operating system (OS) is system software that manages computer hardware, software resources, and provides common services for computer programs. Time-sharing operating systems schedule tasks for efficient use of the system and may also include accounting software for cost allocation of processor time, mass storage, printing, and other resources. For hardware functions such as input and output and memory allocation, the operating system acts as an intermediary between programs and the computer hardware,[1][2] although the application code is usually executed directly by the hardware and frequently makes system calls to an OS function or is interrupted by it. Operating systems are found on many devices that contain a computer – from cellular phones and video game consoles to web servers and supercomputers. The dominant general-purpose[3] personal computer operating system is Microsoft Windows with a market share of around 76.45%. macOS by Apple Inc. is in second place (17.72%),  varieties of Linux are collectively in third place (1.73%).[4] In the mobile sector (including smartphones and tablets), Android's share is up to 72% in the year 2020.[5] According to third quarter 2016 data, Android's share on smartphones is dominant with 87.5 percent with a growth rate of 10.3 percent per year, followed by Apple's iOS with 12.1 percent with per year decrease in market share of 5.2 percent, while other operating systems amount to just 0.3 percent.[6] Linux distributions are dominant in the server and supercomputing sectors. Other specialized classes of operating systems (special-purpose operating systems),[3][7] such as embedded and real-time systems, exist for many applications. Security-focused operating systems also exist. Some operating systems have low system requirements (e.g. light-weight Linux distribution). Others may have higher system requirements.",
                    "userid": "123@qq.com",
                    "interviewId": null,
                    "userName": "Ace Coder",
                    "comment_list": "null",
                    "company": "BoC",
                    "tag": "Operating System",
                    "uploadTime": "2022-06-10T08:25:38.000+00:00",
                    "isLiked": 0,
                    "answers": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    },
                    "comments": {
                        "queryInfo": {
                            "currentPage": 0,
                            "pageSize": 0,
                            "totalRecord": 0
                        },
                        "entities": []
                    }
                }
            ]
        }
    }
}
```
## 8. Online Judgement
### 7.1 Do judgement
URL: /programming_service/judgement  
Type: POST  
```
Request Body:
{
    "questionId":1,
    "codes": "public class Solution{\npublic int[] twoSum(int[] nums, int target) {\nMap<Integer, Integer> hashtable = new HashMap<Integer, Integer>();\nfor (int i = 0; i < nums.length; ++i) {\nif (hashtable.containsKey(target - nums[i])) {\nreturn new int[]{hashtable.get(target - nums[i]), i};\n}hashtable.put(nums[i], i+1);}\nreturn new int[0];}\n}",
    "lang": "Java"
}
```
Response:  
```
{
    "code": "00",
    "description": "Success.",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NDY4MTA2LCJpbmZvIjp7ImFkbWluIjowLCJ1c2VybmFtZSI6IjEyMyJ9fQ.ZNpIvYGf8PHyJcS-vJUZtKOdYnWnIaWIwdn1uHziBis",
    "data": {
        "uuid": "faa9826167174ad69d115cfdc8270b0f",
        "waitMinutesToRequest": 2000
    }
}

uuid: the history id used for following result query.
waitMinutesToRequest: wait for how long, in ms.
```
### 7.2 Query history by id
URL: /programming_service/one_history?uuid=2e8ba9ad509345fe9c263479e0dc6488  
Type: GET  
```
{
    "code": "00",
    "description": "Success.",
    "token": null,
    "data": {
        "uuid": "2e8ba9ad509345fe9c263479e0dc6488",
        "userId": "123@qq.com",
        "questionId": 1,
        "uploadedCode": "public class Solution{\npublic int[] twoSum(int[] nums, int target) {\nMap<Integer, Integer> hashtable = new HashMap<Integer, Integer>();\nfor (int i = 0; i < nums.length; ++i) {\nif (hashtable.containsKey(target - nums[i])) {\nreturn new int[]{hashtable.get(target - nums[i]), i};\n}hashtable.put(nums[i], i+1);}\nreturn new int[0];}\n}",
        "uploadTime": "2022-07-16T10:09:29.000+00:00",
        "stdout": null,
        "stderr": null,
        "status": "Reject",
        "failedCases": [
            {
                "param1": [
                    2,
                    7,
                    11,
                    15
                ],
                "param2": 9,
                "param3": null,
                "param4": null,
                "param5": null
            }
        ],
        "message": "Test case not passed."
    }
}
```
Status code:  
"Accepted", "Rejected", "Error", "Time out", "Compile error"  
### 7.3 Get all the programming history of the user
URL: programming_service/all_history?questionId=1  
programming_service/all_history  
Question id is optional, if not added, return all the history of questions for the user.  
Type: GET  
```
{
    "code": "00",
    "description": "Success.",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NDY4MTA2LCJpbmZvIjp7ImFkbWluIjowLCJ1c2VybmFtZSI6IjEyMyJ9fQ.ZNpIvYGf8PHyJcS-vJUZtKOdYnWnIaWIwdn1uHziBis",
    "data": {
        "userId": "123@qq.com",
        "userName": "Ace Coder",
        "questionId": 1,
        "historyRecord": [
            {
                "uuid": "2e8ba9ad509345fe9c263479e0dc6488",
                "userId": "123@qq.com",
                "questionId": 1,
                "uploadedCode": "public class Solution{\npublic int[] twoSum(int[] nums, int target) {\nMap<Integer, Integer> hashtable = new HashMap<Integer, Integer>();\nfor (int i = 0; i < nums.length; ++i) {\nif (hashtable.containsKey(target - nums[i])) {\nreturn new int[]{hashtable.get(target - nums[i]), i};\n}hashtable.put(nums[i], i+1);}\nreturn new int[0];}\n}",
                "uploadTime": "2022-07-16T10:09:29.000+00:00",
                "stdout": null,
                "stderr": null,
                "status": "Reject",
                "failedCases": [
                    {
                        "param1": [
                            2,
                            7,
                            11,
                            15
                        ],
                        "param2": 9,
                        "param3": null,
                        "param4": null,
                        "param5": null
                    }
                ],
                "message": "Test case not passed."
            },
            {
                "uuid": "c91e81f6e5d64ca59bf044ed29b3635b",
                "userId": "123@qq.com",
                "questionId": 1,
                "uploadedCode": "public class Solution{\npublic int[] twoSum(int[] nums, int target) {\nMap<Integer, Integer> hashtable = new HashMap<Integer, Integer>();\nfor (int i = 0; i < nums.length; ++i) {\nif (hashtable.containsKey(target - nums[i])) {\nreturn new int[]{hashtable.get(target - nums[i]), i};\n}hashtable.put(nums[i], i+1);}\nreturn new int[0];}\n}",
                "uploadTime": "2022-07-16T10:02:37.000+00:00",
                "stdout": null,
                "stderr": null,
                "status": "Reject",
                "failedCases": [
                    {
                        "param1": [
                            2,
                            7,
                            11,
                            15
                        ],
                        "param2": 9,
                        "param3": null,
                        "param4": null,
                        "param5": null
                    }
                ],
                "message": "Test case not passed."
            },
            {
                "uuid": "faa9826167174ad69d115cfdc8270b0f",
                "userId": "123@qq.com",
                "questionId": 1,
                "uploadedCode": "public class Solution{\npublic int[] twoSum(int[] nums, int target) {\nMap<Integer, Integer> hashtable = new HashMap<Integer, Integer>();\nfor (int i = 0; i < nums.length; ++i) {\nif (hashtable.containsKey(target - nums[i])) {\nreturn new int[]{hashtable.get(target - nums[i]), i};\n}hashtable.put(nums[i], i+1);}\nreturn new int[0];}\n}",
                "uploadTime": "2022-07-16T10:26:57.000+00:00",
                "stdout": null,
                "stderr": null,
                "status": "Reject",
                "failedCases": [
                    {
                        "param1": [
                            2,
                            7,
                            11,
                            15
                        ],
                        "param2": 9,
                        "param3": null,
                        "param4": null,
                        "param5": null
                    }
                ],
                "message": "Test case not passed."
            }
        ]
    }
}
```
### 7.4 Load programming questions
URL: /programming_service/get_questions  
Type: POST  
Load by page, please feed the "preCode" tag into your front-end code editor.  
isPassed: 0: not started yet, 1: pass, 2: tried, but not passed.  
```
Request Body:
{
    "pageFirst":1,
    "pageSizeFirst": 50,
    "pageSecond": 1,
    "pageSizeSecond": 30,
    "pageThird": 1,
    "pageSizeThird": 10,
    "type": 0,
    "tag1": 0,
    "tag2": 0
}
```
```
Response Body:
{
    "code": "00",
    "description": "Success.",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NDY4MTA2LCJpbmZvIjp7ImFkbWluIjowLCJ1c2VybmFtZSI6IjEyMyJ9fQ.ZNpIvYGf8PHyJcS-vJUZtKOdYnWnIaWIwdn1uHziBis",
    "data": {
        "queryInfo": {
            "currentPage": 1,
            "pageSize": 50,
            "totalRecord": 1
        },
        "entities": [
            {
                "id": 1,
                "title": "Two sum",
                "description": "Given an integer array nums and an integer target value target, please find the two integers with and as the target value target in the array and return their array subscripts. \nYou can assume that each input will correspond to only one answer. However, the same element in the array cannot be repeated in the answer. \nYou can return the answers in any order.",
                "level": "Easy",
                "testCases": [
                    {
                        "param1": [
                            2,
                            7,
                            11,
                            15
                        ],
                        "param2": 9,
                        "param3": null,
                        "param4": null,
                        "param5": null
                    },
                    {
                        "param1": [
                            3,
                            2,
                            4
                        ],
                        "param2": 6,
                        "param3": null,
                        "param4": null,
                        "param5": null
                    }
                ],
                "prepareCode": "class Solution {\npublic int[] twoSum(int[] nums, int target) \n{\n}\n}",
                "defaultMethodName": "twoSum",
                "returnType": 1,
                "paramLen": 2,
                "paramTypes": "2;1",
                "testNum": 3,
                "isPassed": 1
            }
        ]
    }
}
```
### 7.5 Create a programming question
URL: /programming_service/create  
Type: POST  
```
Request Body:
{
    "description": "Given a string s, find the length of the longest substring without repeating characters. \nExample 1:\n Input: s = \"abcabcbb\"\nOutput: 3\nExplanation: The answer is \"abc\", with the length of 3.\nExample 2:\nInput: s = \"bbbbb\"\nOutput: 1\nExplanation: The answer is \"b\", with the length of 1.",
    "level": "Medium",
    "title": "Longest Substring Without Repeating Characters",
    "prepareCode": "public class Solution{public int lengthOfLongestSubstring(String s) {}}",
    "testCases": [
        {
            "param1": "abcabcbb"
        },
        {
            "param1": "bbbbb"
        },
        {
            "param1": "pwwkew"
        },
        {
            "param1": "abcde"
        },
        {
            "param1": "aaqqweas"
        }
    ]
}
param could be param1~param5
preCode: the prepared code that is shown on the front-end.
```
## 8. Job opportunities
### 8.1 Create job opportunity
URL: /job_service/create  
Type: POST  
### 8.2 Get all job information
URL: /job_service/load  
Type: POST  
By page  
```
{
    "pageFirst": 1,
    "pageSizeFirst": 22   
}
```
```
Response body:
{
    "code": "00",
    "description": "Success.",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NDY4MTA2LCJpbmZvIjp7ImFkbWluIjowLCJ1c2VybmFtZSI6IjEyMyJ9fQ.ZNpIvYGf8PHyJcS-vJUZtKOdYnWnIaWIwdn1uHziBis",
    "data": {
        "queryInfo": {
            "currentPage": 1,
            "pageSize": 22,
            "totalRecord": 1
        },
        "entities": [
            {
                "uuid": "80213b4c329444a3b5ed9c9cbf8d45d4",
                "company": "Tencent",
                "jobPosition": "Project Intern",
                "jobLocation": "Hong Kong",
                "descriptionShort": "Tencent AI lab job, mainly require candidates who are good at Machine learning experiences, especially those has paper released in publications or conferences.",
                "descriptionFull": "### AI Algorithm engineer in Tencent AI lab\r\n\r\n##### Company: \r\n\r\nTencent\r\n\r\n##### Job position: \r\n\r\nAlgorithm engineer\r\n\r\n##### Job location: \r\n\r\nShenzhen\r\n\r\n##### Job description:\r\n\r\n**Responsibilities**\r\n• Assist in handling client's enquiry in standard manners and liaise with internal departments to fulfill client's requirements\r\n• Prepare account opening document and follow client onboarding procedure\r\n• Assist in updating and maintaining client profiles\r\n• Perform ad hoc tasks assigned from time to time\r\n\r\n**Requirements**\r\nSkills and Abilities\r\n• Well organized and detail oriented\r\n• Good interpersonal and communication skills with positive attitude\r\n• Proficient in MS office, especially in Excel\r\n• Strong self-starter and able to work in a fast-paced environment\r\n• Good command of English and Chinese, fluency in Mandarin is preferred\r\n\r\n**Qualifications**\r\n• Finance and Economics related majors preferred\r\n\r\n**Salary**\r\n\r\n25~40k per month\r\n\r\n**Expiry Date**\r\n\r\n2022/08/30\r\n\r\n**Contact:**\r\n\r\ntianyu98@connect.hku.hk",
                "salary": "Discuss later",
                "hrContract": "tianyu@connect.hku.hk",
                "uploadTime": "2022-07-17T16:00:00.000+00:00",
                "expiryDate": "2022-09-14T16:00:00.000+00:00",
                "expired": false
            }
        ]
    }
}

expired: this job is closed and invalid, cannot be clicked.
```

### 8.3 Get job by id
URL: /job_service/query_by_id@uuid=

## 9. Alumni
### 9.1 Get Alumni
Type: GET  
URL: /get_alumni  
```
{
    "code": "00",
    "description": "Success.",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjNAcXEuY29tIiwiZXhwIjoxNjU4NDY4MTA2LCJpbmZvIjp7ImFkbWluIjowLCJ1c2VybmFtZSI6IjEyMyJ9fQ.ZNpIvYGf8PHyJcS-vJUZtKOdYnWnIaWIwdn1uHziBis",
    "data": [
        {
            "email": "123@qq.com",
            "name": "Ace Coder",
            "password": "",
            "isAdmin": null,
            "school": "The University of Hong Kong",
            "graduateDate": null,
            "type": null,
            "company": null,
            "yoe": null
        },
        {
            "email": "12345@qq.com",
            "name": "Wang Zhao",
            "password": "",
            "isAdmin": null,
            "school": "The University of Hong Kong",
            "graduateDate": null,
            "type": null,
            "company": null,
            "yoe": null
        },
        {
            "email": "123456@qq.com",
            "name": "123",
            "password": "",
            "isAdmin": null,
            "school": "The University of Hong Kong",
            "graduateDate": null,
            "type": null,
            "company": null,
            "yoe": null
        },
        {
            "email": "12345ty6@qq.com",
            "name": "123",
            "password": "",
            "isAdmin": null,
            "school": "The University of Hong Kong",
            "graduateDate": null,
            "type": null,
            "company": null,
            "yoe": null
        },
        {
            "email": "1258264684",
            "name": "gaoshiji",
            "password": "",
            "isAdmin": null,
            "school": "The University of Hong Kong",
            "graduateDate": null,
            "type": null,
            "company": null,
            "yoe": null
        },
        {
            "email": "gaoshiwei@qq.com",
            "name": "123",
            "password": "",
            "isAdmin": null,
            "school": "The University of Hong Kong",
            "graduateDate": null,
            "type": null,
            "company": null,
            "yoe": null
        },
        {
            "email": "tianyu98@connect.hku.hk",
            "name": "Tian Yu",
            "password": "",
            "isAdmin": null,
            "school": "The University of Hong Kong",
            "graduateDate": null,
            "type": null,
            "company": null,
            "yoe": null
        }
    ]
}
```