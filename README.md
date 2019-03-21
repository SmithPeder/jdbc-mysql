# TDT4145 Databases and Database Management Systems

> Database project

## Setup

```sh
$ docker-compose up -d
```

## Getting started

- Access the docker container where the MySQL database is running.

```sh
# Get container id, and attatch STDOUT and STDIN to this container
$ docker ps
$ docker exec -it [id] sh

```

- Access the MySQL database running

```sh
# Enter the running MySQL server
$ mysql -u root -p
```

## Running java source code

> This project utilize the `JDBC api` to connect to the MySQL database

The connector can be download [here](https://dev.mysql.com/downloads/connector/j/8.0.html). After downloading the `.jar` you'll need to add it to your `CLASSPATH`.

```sh
# Note the `YOUR_PATH` section, as you can put this wherever you want.
export CLASSPATH=.:$CLASSPATH:~/[YOUR_PATH]/mysql-connector-java-8.0.15.jar

# Then compile and the `JDBC.java` file
$ cd src/
$ javac JDBC.java && java JDBC

```

## Shut down project

```sh
# Turn off container
$ docker-compose kill

# Remove container
$ docker-compose down
```

## Tasks

1. Register equipment, exercises and workout with associated data.

- Start program with empty database
- Press 1 to migrate database schema

> Register equipment

- Press 4 to access the Equipment menu
- Press 1 to fetch all rows (should be 0)
- Press 2 to add new Equipment (enter name and description)
- Press 1 to see that the Equipment is there

> Register exercise

- Press 5 to access the Exercise menu
- Press 1 to fetch all rows (should be 0)
- Press 2 to add new Exercise (select equipment id = 1 the one you just created)
- Press 1 to see that the Exercise is there

> Register workout

- Press 7 to access the Workout menu
- Press 1 to fetch all rows (should be 0)
- Press 2 to add new Workout (enter fields in correct format)
- Press 1 to see that the Workout is there

2. Get information about the n latest workouts with their notes, where n is chosen by the user.

- Press 3 to drop database if you entered rows in task 1
- Press 1 to migrate database schema
- Press 2 to load fixtures
- Press 7 to access the Workout menu
- Press 1 to see the current Workouts
- Press 3 to and enter the number 3 to see the 3 last Workouts with there notes

3. For every exercise, retrieve a result log for a given time interval where the endpoints of the interval is specified by the user.
4. Create new exercise groups and find similar exercises.

> Create new exercise groups

- Press 8 to access the Group menu
- Press 1 to see the the current Groups
- Press 2 to create a new Group
- Press 1 to see that the new Group is there

> Find similar exercises (Method 1)

- Press 8 to access the Group menu
- Press 1 to all Groups
- Press 3 to find exercises in Group
- Enter "Arms" to get all exercises under this group

> Find similar exercises (Method 2)

- Press 5 to access the Exercise Menu
- Press 1 to see all exercises
- Press 3 to find similar exercises
- Enter "Pull-up" to get all similar exercises

5. A self-chosen use case.

## Gif

> Preview

![](mov.gif)
