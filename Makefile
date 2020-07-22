.PHONY: all

all:
	./gradlew clean build test
test:
	./gradlew test