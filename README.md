# README #

EdiTrain - Hexagonification Exercise

(c) copyright 2022 QWAN - Quality Without a Name - www.qwan.eu

## Todo:
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
