FROM adoptopenjdk:11-jre-hotspot
COPY build/libs/queues-telegram-bot.jar ./queues-telegram-bot.jar
CMD java -jar queues-telegram-bot.jar
