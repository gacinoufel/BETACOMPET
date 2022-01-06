SRC = \
	src/competition/*.java \
	src/competition/strategy/*.java \
	src/util/*.java \
	src/competitor/*.java \
	src/match/*.java \
	src/match/observer/*.java\
	src/game/*.java


all: cls doc tests tournament.jar league.jar master.jar

clean:
	rm -fr docs
	rm -fr classes

doc:
	javadoc $(SRC) -d ./docs

cls:
	javac $(SRC) -d classes

tests:
	java -jar junit-platform-console-standalone-1.8.1.jar -cp test --scan-class-path --disable-banner

tournament.jar: classes/game
	cd classes; jar cvfm ../tournament.jar ../manifest-tournament game competitor competition match util


league.jar: classes/game
	cd classes; jar cvfm ../league.jar ../manifest-league game competitor competition match util


master.jar : classes/game
	cd classes; jar cvfm ../master.jar ../manifest-master game competitor competition competition/strategy match util
