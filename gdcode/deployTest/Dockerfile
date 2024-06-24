# 基础镜像
FROM openjdk:11.0-jre-buster
# 设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 指定工作目录（命令将在该目录下运行）
WORKDIR /app
# 拷贝jar包
COPY app.jar /app/app.jar
# 曝露端口
EXPOSE 8080
# 入口
ENTRYPOINT ["java", "-jar", "app.jar"]