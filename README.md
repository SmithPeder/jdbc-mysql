# TDT4145 Databases and Database Management Systems

> Database project

## Setup

```sh
$ docker-compose up -d
```

## Gettings started

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

## Shut down project

```sh
# Turn off container
$ docker-compose kill

# Remove container
$ docker-compose down
```

## About project

The main database is called `wd` for workout-diary. When you gain access to the MySQL database you can list the current databases with `show databases;`. Without running `models.sql` script, there will be no database names `wd`.

In the __./fixtures__ folder there are `insert` scripts that will populate your tables with test data.
