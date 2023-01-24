# hackronym

### **Overview**

Hackronym is a service for a slack app to talk to it. It analyses a block opf text, and deciphers any acronyms in the text. It then returns these to the slack app.

### **Architecture Running Locally**

![Hackronym Architecture](/resources/HackronymSlackArchitecture.jpg)

### **Useful Links**

Slack API App Console - https://api.slack.com/apps

### To Run

- Ngrok -  `ngrok http 3000`

Grab the ngrok url (eg https://a70c-84-13-121-114.ngrok.io) and update this for the slash and event (under Event
Subscriptions and Slash Commands )

- MySQL DB

`Start MySQL from Mac Preferences > MySQL (I find i have to stop and restart the instance to allow me to connect)`

- And the signing secret and bot token - you cna get these from the apps console

`export SLACK_SIGNING_SECRET=<slack app signing secret>`

`export SLACK_BOT_TOKEN=<slack bot token>`

`echo $SLACK_OAUTH_TOKEN` (check its set properly)

- Hackronym Springboot Application - (in the same terminal where you set the slack oauth token env variable)

`./gradlew bootRun`

### Testing

You can run a couple of tests now to check this is working.

Add the app to your slack instance and click onto the Home tab - you should get a description of the App.

Go to the 'hackronymtest' slack channel and type -

### Next Steps 24/01/2023

- Add update slash
- Bot can't react to its own posts
- Multiple reactions on one post result in a link back to the original bots post






