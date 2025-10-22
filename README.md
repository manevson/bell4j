# Bell4j
A simple, spring-enabled alerting built on top of your existing SLF4J logging.

## Configuring the host application

The application that imports this library needs to set up some configurations 
in order for certain features to work.

### Telegram notifier

> * `bell4j.telegram.bot-id` : (String) The ID of the telegram bot to which the alerts should be sent.
> * `bell4j.telegram.chat-id` : (String) The ID of the telegram chat group to which the bot should redirect the alerts. Example 
> * `bell4j.telegram.time-between-alerts` : (String - ISO Duration) The amount of time Bell4j will wait before sending the same alert.