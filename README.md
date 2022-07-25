# Getting Started

# aira2c 下载地址
* https://github.com/aria2/aria2/releases


# 配置文件  application.properties  
 * 和jar包同目录即可
````
#mirai-api-http host
bot.host=localhost
#mirai-api-http port
bot.port=8080
#mirai-api-http  verifyKey
bot.verifyKey=qq1234567890
#绑定机器人
bot.qq=123465
#0为非 R18，1为 R18，2为混合（在库中的分类，不等同于作品本身的 R18 标识）
bot.r18=0
#发送群黑名单 多个用逗号隔开
bot.noSend=123467,
bot.size=original
#配置aira2c目录  eg D:\aria2\aria2c.exe
bot.aira2c=D:\\aria2\\aria2c.exe
#配置aira2c 图片下载目录
bot.outDir=D:\\aria2\\pic
#配置色图前缀
bot.ins=loli
#开启调试日志
bot.debug=false

````
# 启动方式
 java -jar loli-0.0.1.jar
# unix 后台启动
 nohup java -jar loli-0.0.1.jar >/dev/null &
# windows 
start /min java -server  -jar  loli-0.0.1.jar
