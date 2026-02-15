# Bell4j
A simple, spring-enabled alerting built on top of your existing SLF4J logging.

## Configuring the host application

The application that imports this library needs to set up some configurations 
in order for certain features to work.

#### Key terms
> * `Threshold`: The number of occurrences allowed for an application log within a specified `time window`. If exceeded, an alert will be sent to the configured channels.
> * `Time window`: The amount of time within which the threshold matters.
> * `Time between alerts`: How long before an identical alert is re-sent to the configured channels. This is to avoid spamming the chats with the same alerts.

### Base config
> * `bell4j.base.time-between-alerts` : (Duration) Example: `PT5M` -- 5 minutes.
> * `bell4j.base.default-threshold` : (Integer) If not specified explicitly in usage, this will be the default threshold. 
> * `bell4j.base.default-time-window` : (Duration) If not specified explicitly in usage, this will be the default time window.
    
### Telegram notifier
> * `bell4j.telegram.enabled` : `true`/`false`
> * `bell4j.telegram.bot-id` : (String) The ID of the telegram bot to which the alerts should be sent.
> * `bell4j.telegram.chat-id` : (String) The ID of the telegram chat group to which the bot should redirect the alerts.

### Slack notifier
> * `bell4j.slack.enabled` : `true`/`false`
> * `bell4j.slack.alert-url` : (String) the URL of the slack app & chat combination to which the alert will be sent.