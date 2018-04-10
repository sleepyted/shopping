## 依赖环境
* maven 3
* mysql 5.7
* tomcat 8
## 如何运行：
* 将项目导入IDEA
* 运行mysql数据库，执行项目根目录下shopping.sql创建数据库和表
* 修改 src/main/resources/db.properties 文件，填写本机正确的用户名、密码等
* 在根目录下执行 （已安装maven并配置好环境变量等）
```
mvn clean install
```
* idea中配置好tomcat，（可参考：https://www.cnblogs.com/Miracle-Maker/articles/6476687.html），即可运行本项目