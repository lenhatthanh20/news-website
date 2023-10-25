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

This is a News website **real world large-scale** project which implemented using Spring Boot (version 3) framework, Domain Driven Design, Clean Architechture, CQRS pattern, Kafka, PostgreSQL, Redis, .... 
In the future, it will become a microservices project using Spring Cloud Microservices.

# 🧠 The flow
I will update the flow later

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
