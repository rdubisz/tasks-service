Tasks exercise

# About
Simple Spring Boot service allowing REST access to tasks

# Usage

## Requirements
- JDK >= 17
- Docker

## Building

```
./mvnw clean install
docker-compose -f stack.yml build
```

Maven wrapper was added on Windows, so the Unix executable permission may not have been properly saved in the repository.

## Running
Running from Docker
`docker-compose -f stack.yml up`

## Client usage
Requires to have `curl` tool installed.

### About
`curl -v --user "webuser:websecret" localhost:8080`

### Get all tasks
`curl --user "webuser:websecret" localhost:8080/api/v1/task`

### Get single task
`curl --user "webuser:websecret" localhost:8080/api/v1/task/1`

### Getting non-existing task
`curl --user "webuser:websecret" localhost:8080/api/v1/task/90000`

### Create task
`curl --user "webuser:websecret" -X POST localhost:8080/api/v1/task -H 'Content-type:application/json' -d '{"name": "Task200", "code": "t200"}'`

### Update task
`curl --user "webuser:websecret" -X PUT localhost:8080/api/v1/task/1 -H 'Content-type:application/json' -d '{"id": "1", "name": "Cleaning-updated", "code": "cl-upd"}'`

### Add operation
`curl --user "webuser:websecret" -X POST localhost:8080/api/v1/task/operation -H 'Content-type:application/json' -d '{"taskDefinitionId": "1", "duration": "1", "startTime": "2023-01-01T16:01:01"}'`

### Delete operation
`curl --user "webuser:websecret" -X DELETE localhost:8080/api/v1/task/operation/2`

### Query
`curl --user "webuser:websecret" localhost:8080/api/v1/task/operation/query?startTime=2023-01-01T00:00:05\&endTime=2023-01-02T10:00:00\&taskDefinitionId=1`


# Data store
Data is stored in MariaDB, container named "db" by default or at localhost in spring profile "localhost".

Data during integration tests is stored in H2 memory database.

All timestamps are considered to be in UTC. Time conversion to user's TZ should be done at the client side.
