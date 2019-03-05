# TDT4145 Databases and Database Management Systems

> Database project

## Start project

```sh
$ docker-compose up -d
```

## Enter container

```sh
# Get the container id
$ docker ps

# Attach input and output to the container
$ docker exec -it [id] sh

```

## Access the MySQL database

> Must be attached to the container to run these

```sh
# Enter the running mysql server
$ mysql -u root -p

# Show a list of databases
> show databases;

# Select the 'wd' database from the list
> use wd
```


