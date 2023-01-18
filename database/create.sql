CREATE TABLE `acronyms`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `acronym`     varchar(45) NOT NULL,
    `meaning`     varchar(150)  DEFAULT NULL,
    `description` varchar(2000) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

use
hackronym;

insert into acronyms (acronym, meaning, description)
values ("FWIW", "For what it's worth.", "An opinion"),
       ("SPGW", "Sportsbook Gateway.", "A Fanduel integration layer"),
       ("PAS", "Player Activity Stetement.", "what is says on the tin"),
       ("BYOB", "Bring Your Own Beer", "Standing rule for parties at my house…"),
       ("FD", "Fanduel", "The Client."),
       ("FTW", "For The Win", "Use this to indicate that something is the best"),
       ("HN", "Hacker News", "A.K.A. The Orange Site"),
       ("LMGTFY", "Let Me Google That For You",
        "It might be worth googling before asking the question (in a nice way :-) )"),
       ("NSFW", "Not Suitable For Work",
        "Unsuitable for consumption in a work environment. May contain swearing / politically incorrect language / adult content."),
       ("RTFM", "Read The Friendly Manual",
        "You/me/we/they can/could/should have found the requested information in the documentation"),
       ("SFW", "Suitable For Work",
        "Suitable for consumption in a work environment, devoid of swearing, politically-incorrect language etc."),
       ("TLDR", "Too Long, Didn’t Read", "Its all a bit long - this would be a summary"),
       ("XD", "XDesign", "Where we work!"),
       ("YMMV", "Your Mileage May Vary", "You may see different results. As in It works on my machine"),
       ("BDD", "Behaviour Driven Development", "Like TDD, but using the words “Given”, “When” and “Then” a lot."),
       ("DDD", "Domain Driven Design",
        "A software design methodology that puts the business domain at the forefront of the design process"),
       ("DRY", "Don’t Repeat Yourself",
        "By which I mean, a thing (usually some code) is repetitive and IMO should be refactored."),
       ("NIH", "Not Invented Here",
        "Remember that project you worked on where they decided not to use Spring, and instead ended up writing a half-baked, barely-functional reimplementation of most of Spring? Well, the decision not to use Spring was likely because it was not invented here."),
       ("IMO", "In My Opinion", "This is what i think"),
       ("IMHO", "In My Humble Opinion", "Same as IMO, but not quite as passive aggresive"),
       ("NPM", "Node Package Manager",
        "Javascript’s (well, node’s really) hellish package manager. If you encounter a project that’s still using this, run."),
       ("COK", "Cat On Keyboard",
        "A situation where something going wring can only be explained by a cat walking across your keyboard."),
       ("PICNIC", "Problem In Chair, Not In Computer",
        "Indicates that I/we/you/they caused the problem, and the software is fine. "),
       ("SOLID", "SOLID Programming Principles",
        "If I say this, I’m referring to the SOLID Principles for Object-Oriented design"),
       ("SQ", "SonarQube", "A great build-time code-quality tool that is almost universally hated."),
       ("TDD", "Test Driven Development",
        "Expectation: the practice of writing software starting from tiny incremental tests, and only writing enough software to make the tests pass before refactoring or writing another test. Red/Green/Refactor! Reality: The practice of writing your tests in a carefully-planned iterative style so you can pretend you wrote them first."),
       ("YAGNI", "You Ain’t Gonna Need It",
        "If I say this it usually means that I don’t think we should do something."),
       ("YANG", "You Are Not Google",
        "When I say this, I’m probably suggesting that the highly-performant massively-scalable fault-tolerant Kafka-and-reactive-streams-based micro-services architecture that is being suggested would be great, at Google scale.");




