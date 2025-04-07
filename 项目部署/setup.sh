#!/bin/bash

# 解压 project.zip 到当前目录
unzip project.zip -d .

# 创建 mysql 文件夹，并在其中创建 conf、data、init 三个子文件夹
mkdir -p mysql/conf mysql/data mysql/init

# 将 sms.sql 文件移动到 mysql/init 文件夹
mv sms.sql mysql/init/

# 返回到 .sh 文件所在目录
cd $(dirname "$0")

# 创建 nginx 文件夹
mkdir -p nginx

# 将 html 文件夹和 nginx.conf 文件移动到 nginx 下
mv html nginx/
mv nginx.conf nginx/

# 运行 docker compose up -d
docker compose up -d

echo "安装完成"