<p align="center">
    <a href="https://github.com/lenhatthanh20/news-website/actions/workflows/unit-test.yaml" alt="Unit tests">
        <img src="https://github.com/lenhatthanh20/news-website/actions/workflows/unit-test.yaml/badge.svg?branch=main" /></a>
    <a href="https://github.com/lenhatthanh20/news-website/graphs/contributors" alt="Contributors">
        <img src="https://img.shields.io/github/contributors/lenhatthanh20/news-website" /></a>
    <a href="https://github.com/lenhatthanh20/news-website/pulse" alt="Activity">
        <img src="https://img.shields.io/github/commit-activity/m/lenhatthanh20/news-website" /></a>
    <a href="https://circleci.com/gh/lenhatthanh20/news-website/tree/master">
        <img src="https://img.shields.io/circleci/project/github/lenhatthanh20/news-website/master" alt="build status"></a>
    <a href="https://coveralls.io/github/lenhatthanh20/news-website">
        <img src="https://img.shields.io/coveralls/github/lenhatthanh20/news-website"
            alt="coverage"></a>
</p>

# 👩‍💻 Introduction

This is a **large-scale, real-world** project for a news website that was developed with the help of the Spring Boot (version 3) framework, Domain Driven Design, Clean Architecture, CQRS pattern, Kafka, PostgreSQL, Redis, and other technologies. It will eventually be transformed into a microservices project with Spring Cloud Microservices.

# 🧠 Appllication Design
The below image describe for the design of this application.

![The design](https://lenhatthanh.com/wp-content/uploads/2023/10/cqrs-ddd-clean-architecture.png)

## CQRS
Because this is a News website, so the `read requests` (`GET` method) ammount has majority compared to `write requests` (`POST`, `PUT`, `DELETE`, ...). 
So we need to apply the CQRS pattern to seperate the Write side (Command side) and Read side (Query side) for scaling in both codebase and server.
I will write a article to dive deep into CQRS later (Vietnamese in https://lenhatthanh.com)

## DDD and Clean Architecture in Write Side
In the Write side, we apply DDD to isolate the business logics (in `domain` layer), and apply some useful concepts of DDD to handle business logics.
Also, we apply the Clean architecture for easily to mantain and scale the codebase.

# 🚀 Local Development
Please following these step for local development:

## **Step 1**: Clone the project
```bash
$ git clone https://github.com/lenhatthanh20/news-website.git
```

## **Step 2**: Run all docker containers
```bash
$ docker compose up
```

After docker compose up
- Can access the Kafka UI via: `http://localhost:8085`
- The PosgreSQL database will started at the port number `5555`
- The Redis database will started at the port number `6379`

More detail please view in `docker-compose.yaml` file

## **Step 3**: Run the command side and query side services (Spring Boot application)
> [!IMPORTANT]\
> Please use the Java SDK version `<= 17` (recommend version `17`) because some of dependencies don't work with higher Java SDK.

## **Step 4**: Enjoy your coding

# 💬 Contact
You can contact me via:

`<My blog>` ：<https://lenhatthanh.com>

`<Facebook>` ：<https://facebook.com/coderdocs>

`<Github>` ：<https://github.com/lenhatthanh20>

`<Gmail>` ：lenhatthanh20@gmail.com

# ⚡️ License
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://github.com/lenhatthanh20/news-website/blob/43cee8e88d4ab45948016725360c00666d428303/LICENSE)
