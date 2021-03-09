export GPG_TTY=$(tty)
#mvn clean package deploy
#mvn clean javadoc:jar deploy -P release
#mvn clean deploy -P release -Dgpg.passphrase=
#mvn clean deploy -P release
mvn clean deploy