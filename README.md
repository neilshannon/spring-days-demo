# Spring Days Demo - Rock Solid Cloud Native Apps with Spring

## Tech Stack

- Platform: Spring Boot
- Persistence: Spring Data MongoDB
- REST: Spring Data REST
- Testing: JUnit, Spring Test
- Build: Gradle
- Cloud Environment: Spring Cloud Connectors

## Getting Started

Developed and tested with the following software:

- a bash-like shell (macOS Sierra 10.12.3)
- cloud foundry command line interface installed

```sh
$ brew tap cloudfoundry/tap
$ brew install cf-cli
$ cf -v
cf version 6.23.0+c7866be18-2016-12-22
```
via homebrew or download and install from: https://github.com/cloudfoundry/cli#downloads 

- mongodb 3.4.0 running locally

```sh 
$ brew install mongodb
$ mongod
```

via homebrew or download and install from: https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/

- git

```sh 
$ git --version
git version 2.10.1

```
via homebrew or download and install from: https://git-scm.org

- jdk 1.8+

```sh
$ java -version
java version "1.8.0_112"
Java(TM) SE Runtime Environment (build 1.8.0_112-b16)
Java HotSpot(TM) 64-Bit Server VM (build 25.112-b16, mixed mode)
```

## Java ##
This project runs on Spring Boot and uses Spring Data MongoDB and Spring REST.  
Cloud configuration is provided by Spring Cloud Connectors.  

To run the app locally, you must have a running instance of MongoDB at localhost.

For testing, we use an in-memory database.


**To Run**
`./gradlew bootRun`

**To Test**
`./gradlew test`

**To Package for Deployment**
`./gradlew build`
