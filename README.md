# 招聘系统后端Spring
前端项目地址：[传送门](https://github.com/yanqing8/vue-recruit)

## 环境要求

- Java 17
- Maven 3.9.4 或更高版本

## 数据库配置

本项目使用 MySQL 数据库，需要配置以下信息：

- 数据库驱动：`com.mysql.cj.jdbc.Driver`
- 数据库 URL：`jdbc:mysql://localhost:3306/recruit_sys`
- 用户名：``
- 密码：``

请确保您的 MySQL 数据库已经启动，并且已经创建了名为 `recruit_sys` 的数据库。

## Redis 配置

本项目使用 Redis 作为缓存，需要配置以下信息：

- 主机：`localhost`
- 端口：`6379`

请确保您的 Redis 服务器已经启动。

## 七牛云配置

本项目使用七牛云进行文件上传，需要配置以下信息：

- Access Key：``
- Secret Key：``
- 存储空间名称：``
- 七牛云域名：``

请确保你已经在七牛云创建了相应的存储空间，并且已经将你的域名绑定到了这个存储空间。

## 依赖管理

本项目使用 Maven 进行依赖管理。进入idea使用maven导入项目，等待依赖下载完成。

## 启动项目

在完成所有配置后，您可以启动项目，项目启动后，您可以通过访问 `http://localhost:8080` 来查看项目的运行情况。

