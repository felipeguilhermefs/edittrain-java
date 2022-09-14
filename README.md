# README #

This README would normally document whatever steps are necessary to get your application up and running.

Todo:
v unique name + exception handling
- update course
- send message to other service?
  - subscription service
  - stub: extra main program
- scripts for using service

## Calling the service

Add a course:
```shell
curl -i -X POST localhost:8080/courses --data '{"name":"OO Essentials","description":"Entry level","teacher":"rob@editrain.eu"}' -H "Content-Type: application/json"
```

Get all courses:
```shell
curl localhost:8080/courses
```

Update a course (name and description only):
```shell
curl -i -X PUT localhost:8080/courses --data '{"id":"<id>","name":"OO Essentials part I","description":"Entry level","teacher":"rob@editrain.eu"}' -H "Content-Type: application/json"
```

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact