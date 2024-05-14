# Эндпоинты

==todo: разобраться как хранить теги==

## Профиль пользователя
### GET
#### GET user details
`GET /api/user/:user_id`

input: `user_id`
output:
```json
{
	"id":0,
	"username":"name",
	"avatar":"/avatars/users/0.jpg",
	"firsname":"firsname",
	"lastname":"lastname",
	"bio_info":"some info of biography",
	"email":"email@mail.com",
	"telegram":"@awat1ove",
	"link":"linkedin.com/awatlove",
	"teams":[
		{
			"id":1,
			"name":"teamName",
			"role":"roleNameInTeam"
		},
		{
			"id":2,
			"name":"teamName2",
			"role":"roleNameInTeam2"
		}
	]
}
```

#### GET get current user by token
`GET /api/user/cur`
**auth**

Получить userDetail авторизированного юзера

- getUserDetailsByAuthToken

```json
{
	"id":0,
	"username":"name",
	"avatar":"/avatars/users/0.jpg",
	"firsname":"firsname",
	"lastname":"lastname",
	"bio_info":"some info of biography",
	"email":"email@mail.com",
	"telegram":"@awat1ove",
	"link":"linkedin.com/awatlove",
	"teams":[
		{
			"id":1,
			"name":"teamName",
			"role":"roleNameInTeam"
		},
		{
			"id":2,
			"name":"teamName2",
			"role":"roleNameInTeam2"
		}
	]
}
```
#### GET all users
`GET /api/user`

- getAllUsers
input: -
output:
```json
{
	"users":[
		{
			"id":0,
			"username":"username",
			"avatar":"/avatars/users/0.jpg"
		},
		{
			"id":1,
			"username":"username1",
			"avatar":"/avatars/users/1.jpg"
		},
		{
			"id":2,
			"username":"username2",
			"avatar":"/avatars/users/2.jpg"
		}
	]
}
```

### PUT
#### PUT update user details
`PUT /api/user`
**auth**

input:
```json
{
	"username":"newUserName",
	"avatar":"/avatars/users/1.jpg",
	"firstname":"newFirstname",
	"bio_info":"new bio info",
	"lastname":"newLastname",
	"email":"newemail@mail.com",
	"telegram":"@newTG",
	"link":"new other contacts"
}
```
output:
```json
{
	"id":0,
	"username":"newUserName",
	"avatar":"/avatars/users/1.jpg",
	"firsname":"newFirstname",
	"lastname":"newLastname",
	"bio_info":"new bio info",
	"email":"newemail@mail.com",
	"telegram":"@newTG",
	"link":"new other contacts",
	"teams":[
		{
			"id":1,
			"name":"teamName",
			"role":"roleNameInTeam"
		},
		{
			"id":2,
			"name":"teamName2",
			"role":"roleNameInTeam2"
		}
	]
}
```

## Профиль команды
### GET
#### GET team details
`GET /api/team/:team_id`

- getTeamById
input: `team_id`
output:
```json
{
	"id":0,
	"name":"coolTeam0",
    "description":"sdgsdg",
	"folowers_count":4,
	"avatar":"/avatars/teams/0.jpg",
	"owner_id":0,
    "is_folow":true,
	"members":[
		{
			"id":0,
			"username":"user0",
			"avatar":"/avatars/users/0",
			"role":"owner"
		},
		{
			"id":1,
			"username":"user1",
			"avatar":"/avatars/users/1",
			"role":"developer"
		}
	],
	"projects":[
		{
			"id":0,
			"title":"someProjectsTitle0",
			"description":"someProjectsDescription0",
			"created_at":"2014-03-12T13:37:27+00:00",
			"likes_count":5,
			"folowers_count":2,
			"patch_count":2
		},
		{
			"id":1,
			"title":"someProjectsTitle1",
			"description":"someProjectsDescription1",
			"created_at":"2014-03-11T13:37:27+00:00",
			"likes_count":2,
			"folowers_count":0,
			"patch_count":2
		},
		{
			"id":2,
			"title":"someProjectsTitle2",
			"description":"someProjectsDescription2",
			"created_at":"2014-03-10T13:37:27+00:00",
			"likes_count":1,
			"folowers_count":1,
			"patch_count":2
		}
	]
}
```

#### GET all teams
`GET /api/team`
optional: **auth**

input: -
output:
```json
{
	"teams":[
		{
			"id":0,
			"name":"coolTeamName0",
			"avatar":"/avatars/teams/0.jpg",
			"folowers_count":3,
             "is_folow":true
		},
		{
			"id":1,
			"name":"coolTeamName1",
			"avatar":"/avatars/teams/1.jpg",
			"folowers_count":1,
            "is_folow":true
		},
		{
			"id":2,
			"name":"coolTeamName2",
			"avatar":"/avatars/teams/2.jpg",
			"folowers_count":5,
            "is_folow":true
		}
	]
}
```

#### GET team folowers
`GET /api/team/:team_id/folowers`

- getFolowersByTeamId
input: `team_id`
ouput: 
```json
{
	"folowers":[
		{
			"id":0,
			"username":"user0",
			"avatar":"/avatars/users/0.jpg"
		},
		{
			"id":1,
			"username":"user1",
			"avatar":"/avatars/users/1.jpg"
		},
		{
			"id":2,
			"username":"user2",
			"avatar":"/avatars/users/2.jpg"
		},
		{
			"id":3,
			"username":"user3",
			"avatar":"/avatars/users/3.jpg"
		}
	]
}
```


### POST
#### POST create team
`POST /api/team`
**auth**

input:
```json
{
	"name":"teamName",
	"description":"some descrp",
	"avatar":"/avatars/teams/0.jpg"
}
```
output:
```json
{
	"id":0,
	"name":"teamName",
	"folowers_count":0,
	"avatar":"/avatars/teams/0.jpg",
	"owner_id":0,
	"description":"some descrp",
    "is_folow":true,
	"members":[
		{
			"id":0,
			"username":"user0",
			"avatar":"/avatars/users/0",
			"role":"owner"
		}
	],
	"projects":[]
}
```
### PUT
#### PUT update team
`PUT /api/team/:team_id`
**auth**

- updateTeamDetails
input: `team_id`
```json
{
	"name":"newTeamName0",
	"description":"some descrp",
	"avatar":"/avatars/teams/new0.jpg"
}
```
output: `teamDetails`
```json
{
	"id":0,
	"name":"newTeamName0",
	"folowers_count":4,
	"avatar":"/avatars/teams/new0.jpg",
	"owner_id":0,
	"description":"some descrp",
    "is_folow":true,  
	"members":[
		{
			"id":0,
			"username":"user0",
			"avatar":"/avatars/users/0",
			"role":"owner"
		},
		{
			"id":1,
			"username":"user1",
			"avatar":"/avatars/users/1",
			"role":"developer"
		}
	],
	"projects":[
		{
			"id":0,
			"title":"someProjectsTitle0",
			"description":"someProjectsDescription0",
			"created_at":"2014-03-12T13:37:27+00:00",
			"likes_count":5,
			"folowers_count":2
		},
		{
			"id":1,
			"title":"someProjectsTitle1",
			"description":"someProjectsDescription1",
			"created_at":"2014-03-11T13:37:27+00:00",
			"likes_count":2,
			"folowers_count":0
		},
		{
			"id":2,
			"title":"someProjectsTitle2",
			"description":"someProjectsDescription2",
			"created_at":"2014-03-10T13:37:27+00:00",
			"likes_count":1,
			"folowers_count":1
		}
	]
}

```

#### PUT change member role
`PUT /api/team/:team_id/member/:member_id`
**auth**

input: `team_id`, `member_id`
output:
```json
{
  "role": "newRole"
}
```

#### PUT folow to team
`PUT /api/team/:team_id/folow`
**auth**
работает как переключатель, один раз кинешь, подпишется, второй раз - отпишется
input: `team_id`, user_id from token
output: ==todo: надо ли здесь чтото возвращать кроме `200 ок`?==
### DELETE
#### DELETE member
`DELETE /api/team/:team_id/member/:user_id`
**auth**

- deleteTeamMemberByTeamIdAndUserId
input: `team_id`, `user_id`
output: `teamDetail`
```json
	{
	"id":0,
	"name":"coolTeam0",
	"folowers_count":4,
	"avatar":"/avatars/teams/0.jpg",
	"owner_id":0,
	"members":[
		{
			"id":0,
			"username":"user0",
			"avatar":"/avatars/users/0",
			"role":"owner"
		},
		{
			"id":1,
			"username":"user1",
			"avatar":"/avatars/users/1",
			"role":"developer"
		}
	],
	"projects":[
		{
			"id":0,
			"title":"someProjectsTitle0",
			"description":"someProjectsDescription0",
			"created_at":"2014-03-12T13:37:27+00:00",
			"likes_count":5,
			"folowers_count":2
		},
		{
			"id":1,
			"title":"someProjectsTitle1",
			"description":"someProjectsDescription1",
			"created_at":"2014-03-11T13:37:27+00:00",
			"likes_count":2,
			"folowers_count":0
		}
	]
}
```

#### DELETE delete team
==надо ли оно?==

#### DELETE quit from team
TODO:

## Профиль проекта
### GET
#### GET project details
`GET /api/project/:project_id`

- getProjectById
input: `project_id`
output:
```json
{
	"id":0,
	"team":{
		"id":0,
		"name":"teamName",
		"avatar":"/avatars/teams/0.jpg"
	},
	"title":"someProjectTitle",
	"description":"someProjectDescription",
	"html_info":"<h1>some header1</h1>",
	"stack":"Java, Postgres, Docker, Git",
	"likes_count":4,
	"folowers_count":2,
	"created_at":"2014-03-12T13:37:27+00:00",
	"patch_count":4,
	"updated_at":"2015-03-12T13:37:27+00:00",
	"is_folow":true,
	"is_liked":false,
	"tags":[
		{
			"id":0,
			"name":"Java"
		},
		{
			"id":1,
			"name":"PostgreSQL"
		},
		{
			"id":3,
			"name":"Docker"
		},
		{
			"id":5,
			"name":"ФИНТЕХ"
		}
	]
}
```

#### GET get all project
`GET /api/project`
optional: **auth** - если нужно получить инфу о подписанных или лайкнутых проектах

- getAllProjects
input: ?tags -> [0,5] для фильтрации, как query параметр
output:
```json
{
	"projects":[
		{
			"id":0,
			"team":{
				"id":0,
				"name":"teamName",
				"avatar":"/avatars/teams/0.jpg"
			},
			"title":"projectName",
			"description":"some project description",
			"likes_count":2,
			"folowers_count":1,
			"created_at":"2017-03-12T13:37:27+00:00",
			"updated_at":"2017-03-12T13:37:27+00:00",
			"patch_count":3,
			"is_folow":true,
			"is_liked":false,
			"tags":[
				{
					"id":0,
					"name":"Java"
				},
				{
					"id":1,
					"name":"PostgreSQL"
				},
				{
					"id":3,
					"name":"Docker"
				},
				{
					"id":5,
					"name":"ФИНТЕХ"
				}
			]
		},
		{
			"id":1,
			"team":{
				"id":1,
				"name":"teamName",
				"avatar":"/avatars/teams/0.jpg"
			},
			"title":"projectName",
			"description":"some project description",
			"likes_count":2,
			"folowers_count":1,
			"created_at":"2017-03-12T13:37:27+00:00",
			"updated_at":"2017-03-12T13:37:27+00:00",
			"patch_count":3,
			"is_folow":true,
			"is_liked":false,
			"tags":[
				{
					"id":0,
					"name":"Java"
				},
				{
					"id":5,
					"name":"ФИНТЕХ"
				}
			]
		}
	]
}
```


#### GET get project by tags
input: ?optional -> user folows projects(от подписок пользователя смотреть) & ?tags -> [0,5] & ?pagenum

соединил с общим запросом
==todo: утвердить со Стасом==

output:
```json
{
	"projects":[
		{
			"id":0,
			"team":{
				"id":0,
				"name":"teamName",
				"avatar":"/avatars/teams/0.jpg"
			},
			"title":"projectName",
			"description":"some project description",
			"likes_count":2,
			"folowers_count":1,
			"created_at":"2017-03-12T13:37:27+00:00",
			"updated_at":"2017-03-12T13:37:27+00:00",
			"patch_count":3,
			"is_folow":true,
			"is_liked":false,
			"tags":[
				{
					"id":0,
					"name":"Java"
				},
				{
					"id":1,
					"name":"PostgreSQL"
				},
				{
					"id":3,
					"name":"Docker"
				},
				{
					"id":5,
					"name":"ФИНТЕХ"
				}
			]
		},
		{
			"id":1,
			"team":{
				"id":1,
				"name":"teamName",
				"avatar":"/avatars/teams/0.jpg"
			},
			"title":"projectName",
			"description":"some project description",
			"likes_count":2,
			"folowers_count":1,
			"created_at":"2017-03-12T13:37:27+00:00",
			"updated_at":"2017-03-12T13:37:27+00:00",
			"patch_count":3,
			"is_folow":true,
			"is_liked":false,
			"tags":[
				{
					"id":0,
					"name":"Java"
				},
				{
					"id":5,
					"name":"ФИНТЕХ"
				}
			]
		}
	]
}
```



#### GET project likes
`GET /api/project/:project_id/likes`

- getProjectLikes
input: `project_id`
output:
```json
{
	"likes":[
		{
			"id":0,
			"username":"user0",
			"avatar":"/avatars/users/0.jpg"
		},
		{
			"id":1,
			"username":"user1",
			"avatar":"/avatars/users/1.jpg"
		},
		{
			"id":2,
			"username":"user0",
			"avatar":"/avatars/users/3.jpg"
		}
	]
}
```


#### GET project folowers
`GET /api/project/:project_id/folowers`

- getProjectFolowers
input: `project_id`
output:
```json
{
	"folowers":[
		{
			"id":0,
			"username":"user0",
			"avatar":"/avatars/users/0.jpg"
		},
		{
			"id":1,
			"username":"user1",
			"avatar":"/avatars/users/1.jpg"
		},
		{
			"id":2,
			"username":"user0",
			"avatar":"/avatars/users/3.jpg"
		}
	]
}
```

#### GET patch likes
`GET /api/project/:project_id/patch/:patch_id/likes`

- getPatchLikes
input: - пока опционально ->`project_id`;  обязательно -> `patch_id`
output:
```json
{
	"likes":[
		{
			"id":0,
			"username":"user0",
			"avatar":"/avatars/users/0.jpg"
		},
		{
			"id":1,
			"username":"user1",
			"avatar":"/avatars/users/1.jpg"
		},
		{
			"id":2,
			"username":"user0",
			"avatar":"/avatars/users/3.jpg"
		}
	]
}
```


#### GET get all patch by project id
`GET /api/project/:project_id/patch`

input: `project_id`
output:
```json
{
	"patches":[
		{
			"id":0,
			"project_id":0,
			"title":"patchtitle",
			"description":"patch description",
            "isLiked": false,
			"likes_count":2,
			"created_at":"2018-03-12T13:37:27+00:00",
            "is_liked":false
		},
		{
			"id":1,
			"project_id":0,
			"title":"patchtitle",
			"description":"patch description",
            "isLiked": false,
			"likes_count":2,
			"created_at":"2018-03-12T13:37:27+00:00",
            "is_liked":false
		},
		{
			"id":2,
			"project_id":0,
			"title":"patchtitle",
			"description":"patch description",
            "isLiked": false,
			"likes_count":2,
			"created_at":"2018-03-12T13:37:27+00:00",
            "is_liked":false
		}
	]
}
```

### POST
#### POST create project
`POST /api/project`
**auth**

создание проекта
user из токена
input:
```json
{
	"team_id":0,
	"title":"some project title",
	"description":"some description",
	"html_info":"<html>",
	"stack":"stack",
	"tags":[0,2,5]
}
```
output:
```json
{
	"id":0,
	"team":{
		"id":0,
		"name":"teamName",
		"avatar":"/avatars/teams/0.jpg"
	},
	"title":"someProjectTitle",
	"description":"someProjectDescription",
	"html_info":"<h1>some header1</h1>",
	"stack":"Java, Postgres, Docker, Git",
	"likes_count":4,
	"folowers_count":2,
	"created_at":"2014-03-12T13:37:27+00:00",
	"patch_count":4,
	"updated_at":"2015-03-12T13:37:27+00:00",
	"is_folow":true,
	"is_liked":false,
	"tags":[
		{
			"id":0,
			"name":"Java"
		},
		{
			"id":1,
			"name":"PostgreSQL"
		},
		{
			"id":3,
			"name":"Docker"
		},
		{
			"id":5,
			"name":"ФИНТЕХ"
		}
	]
}
```

#### POST create patch
`POST /api/project/:project_id/patch`
**auth**

создать патч
input: `project_id`
```json
{
	"title":"some title",
	"description":"some description"
}
```
output: 
```json
{
			"id":0,
			"project_id":0,
			"title":"patchtitle",
			"description":"patch description",
			"likes_count":2,
			"created_at":"2018-03-12T13:37:27+00:00"
}
```
### PUT
#### PUT update project details
`PUT /api/project/:project_id`
**auth**

input: `project_id` 
```json
{
	"title":"newTitle",
	"description":"new decription",
	"html_info":"some html",
	"stack":"new stack",
	"tags":[0,6]
}
```
output: 
```json
{
	"id":0,
	"team":{
		"id":0,
		"name":"teamName",
		"avatar":"/avatars/teams/0.jpg"
	},
	"title":"someProjectTitle",
	"description":"someProjectDescription",
	"html_info":"<h1>some header1</h1>",
	"stack":"Java, Postgres, Docker, Git",
	"likes_count":4,
	"folowers_count":2,
	"created_at":"2014-03-12T13:37:27+00:00",
	"patch_count":4,
	"updated_at":"2015-03-12T13:37:27+00:00",
	"tags":[
		{
			"id":0,
			"name":"Java"
		},
		{
			"id":1,
			"name":"PostgreSQL"
		}
	]
}
```

#### PUT folow to project
`PUT /api/project/:project_id/folow`
**auth**

работает как переключатель, один раз кинешь, подпишется, второй раз - отпишется
input: `project_id`, user_id from token
output: ==todo: надо ли здесь чтото возвращать кроме `200 ок`?==

#### PUT like project
`PUT /api/project/:project_id/like`
**auth**

работает как переключатель, один раз кинешь, лайкнет, второй раз - уберет лайк
input: `project_id`, user_id from token
output: ==todo: надо ли здесь чтото возвращать кроме `200 ок`?==

#### PUT like patch
`PUT /api/project/:project_id/patch/:patch_id/like`
**auth**


работает как переключатель, один раз кинешь, лайкнет, второй раз - уберет лайк
input: `project_id`, `patch_id`, user_id from token
output: ==todo: надо ли здесь чтото возвращать кроме `200 ок`?==
### DELETE
#### DELETE delete project
`DELETE /api/project/:project_id`
**auth**

- deleteProjectById
input: `project_id`
output: `200 ok`

#### DELETE delete patch
`DELETE /api/project/:project_id/patch/:patch_id`
**auth**

- deletePatchById
input: `project_id, patch_id`
output: `200 ok`


## Профиль резюме

### POST
#### POST create resume
`POST /api/resume`
**auth**

input:
```json
{
	"title":"resume name",
	"html_info":"<html>"
}
```
output:
```json
{
	"id":0,
	"title":"resume title",
	"html_info":"<html>",
	"email":"email from user",
	"telegram":"telegram from user",
	"link":"link from user"
}
```
### GET
#### GET get all user's resumes
`GET /api/resume`
**auth**

input: - userId из токена
output:
```json
{
	"resumes":[
		{
			"id":0,
			"user_id":0,
			"title":"resume title",
			"html_info":"<html>",
			"email":"email from user",
			"telegram":"telegram from user",
			"link":"link from user"
		},
		{
			"id":1,
			"user_id":0,
			"title":"resume title1",
			"html_info":"<html>",
			"email":"email from user",
			"telegram":"telegram from user",
			"link":"link from user"
		}
	]
}
```

#### GET get user resume by id
`GET /api/resume/:resume_id`
**auth**

input: `resume_id`
output:
```json
{
	"id":0,
	"user_id":0,
	"title":"resume title",
	"html_info":"<html>",
	"email":"email from user",
	"telegram":"telegram from user",
	"link":"link from user"
}
```
### PUT
#### PUT update
`PUT /api/resume/:resume_id`
**auth**

==если пользователь обновляет контактные данные, в резюме тоже обновляются==
input: `resume_id`
```json
{
  "title":"new resume name",
  "html_info":"new <html>",
  "email":"email from user",
  "telegram":"telegram from user",
  "link":"link from user"
}
```
output:
```json
{
	"id":0,
	"user_id":0,
	"title":"resume title",
	"html_info":"<html>",
	"email":"email from user",
	"telegram":"telegram from user",
	"link":"link from user"
}
```
### DELETE
#### DELETE resume by id
`DELETE /api/resume/:resume_id`
**auth**

input: `resume_id`
output: `200 ok`

## Профиль заявок
### POST
#### POST create claim
`POST /api/claim`
**auth**

input:
```json
{
	"team_id":0,
	"resume_id":0
}
```
output:
```json
{
	"id":0,
	"team":{
		"id":0,
		"name":"teamName",
		"avatar":"/avatars/teams/0.jpg"
	},
	"resume":{
		"id":0,
		"title":"resume header"
	},
	"created_at":"2017-03-12T13:37:27+00:00",
	"updated_at":"2017-03-12T13:37:27+00:00",
	"accepted":null
}
```

### GET
#### GET get claims by userId
`GET /api/claim`
**auth**

input: - из токена userId
output:
```json
{
	"claims":[
		{
			"id":0,
			"team":{
				"id":0,
				"name":"teamName",
				"avatar":"/avatars/teams/0.jpg"
			},
			"resume":{
				"id":0,
				"title":"resume header"
			},
			"created_at":"2017-03-12T13:37:27+00:00",
			"updated_at":"2017-03-12T13:37:27+00:00",
			"accepted":null
		},
		{
			"id":1,
			"team":{
				"id":1,
				"name":"teamName1",
				"avatar":"/avatars/teams/1.jpg"
			},
			"resume":{
				"id":0,
				"title":"resume header"
			},
			"created_at":"2017-03-12T13:37:27+00:00",
			"updated_at":"2017-03-12T13:37:27+00:00",
			"accepted":false
		}
	]
}
```


#### GET get claims by team id
`GET /api/claim/:team_id`
**auth**

input: `team_id`
output:
```json
{
	"claims":[
		{
			"id":0,
			"team":{
				"id":0,
				"name":"teamName",
				"avatar":"/avatars/teams/0.jpg"
			},
			"resume":{
				"id":0,
				"title":"resume header"
			},
			"created_at":"2017-03-12T13:37:27+00:00",
			"updated_at":"2017-03-12T13:37:27+00:00",
			"accepted":null
		},
		{
			"id":1,
			"team":{
				"id":1,
				"name":"teamName1",
				"avatar":"/avatars/teams/1.jpg"
			},
			"resume":{
				"id":0,
				"title":"resume header"
			},
			"created_at":"2017-03-12T13:37:27+00:00",
			"updated_at":"2017-03-12T13:37:27+00:00",
			"accepted":false
		}
	]
}
```

### PUT
#### PUT confirm claim
`PUT /api/claim/:claim_id/accept`
**auth**


==todo backend: тут мы должны добавить его в команду==
Проверить является ли пользователь, который принимает заявку Owner команды
Заполнить user_team и User_folows ну и естественно изменить claims

input: `claim_id` ?accept={true/false} ?role=(роль в команде)
output: `200 ok`

### DELETE
#### DELETE delete claim by id
`DELETE /api/claim/:claim_id`
**auth**


input: `claim_id`
output: `200 ok`

## авторизация
#### POST sign up
`POST /api/auth/signup`

input:
```json
{
	"username":"username",
	"firstname":"firstname",
	"lastname":"lastname",
	"password":"password12345"
}
```
output:
```json
{
	"id":0
}
```

#### POST sign in
`POST /api/auth/signin`


input:
```json
{
	"username":"username",
	"password":"password12345"
}
```
output:
```json
{
	"token":"aslksgasjdgnaug834hgni8bew7goie4gh843g4edg"
}
```


## Коментарии
### POST
#### POST create comment
`POST /api/project/:project_id/comment`
**auth**

input: `project_id`
```json
{
	"text":"some comment text"
}
```
output:
```json
{
	"id":0,
	"user":{
		"id":0,
		"username":"user0",
		"avatar":"/avatars/users/0.jpg"
	},
	"text":"some comment text",
	"created_at":"2017-03-12T13:37:27+00:00"
}
```

### GET
#### GET get all comments by project
`GET /api/project/:project_id/comment`


input: `project_id`
output:
```json
{
	"comments":[
		{
			"id":0,
			"user":{
				"id":0,
				"username":"user0",
				"avatar":"/avatars/users/0.jpg"
			},
			"text":"some comment text",
			"created_at":"2017-03-12T13:37:27+00:00"
		},
		{
			"id":1,
			"user":{
				"id":1,
				"username":"user1",
				"avatar":"/avatars/users/0.jpg"
			},
			"text":"some comment text else else",
			"created_at":"2017-03-12T13:37:27+00:00"
		},
		{
			"id":2,
			"user":{
				"id":0,
				"username":"user0",
				"avatar":"/avatars/users/0.jpg"
			},
			"text":"and else some text in comment",
			"created_at":"2018-03-12T13:37:27+00:00"
		}
	]
}
```

### DELETE
#### DELETE delete by id
`DELETE /api/project/:project_id/comment/:comment_id`


input: `project_id`, `comment_id`
output: `200 ok`

## ТЕГИ
#### GET all tags
`GET /api/tag`

input: -
output:
```json
{
	"tags":[
		{
			"id":0,
			"tagname":"Java"
		},
		{
			"id":1,
			"tagname":"PostgreSQL"
		},
		{
			"id":3,
			"tagname":"Docker"
		},
		{
			"id":5,
			"tagname":"ФИНТЕХ"
		}
	]
}
```

#### POST create tag
`POST /api/tag`

input: 
```json
{
	"tagname":"Golang"
}
```
output:
```json
{
	"id":6,
	"tagname":"Golang"
}
```

#### PUT update tag
`PUT /api/tag`

input: `tag_id`
```json
{
	"tagname":"go"
}
```
output:
```json
{
	"id":6,
	"tagname":"Golang"
}
```
#### DELETE tag
`DELETE /api/tag/:tag_id`

input: `tag_id`
output: `200 ok`