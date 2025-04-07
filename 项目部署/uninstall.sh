#!/bin/bash
docker compose down
docker rmi root-sms
rm -rf mysql nginx sms.sql sms-0.0.1-SNAPSHOT.jar Dockerfile docker-compose.yml
echo "卸载完成"