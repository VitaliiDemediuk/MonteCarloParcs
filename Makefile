all: run

clean:
	rm -f out/Main.jar out/Integration.jar

out/Main.jar: out/parcs.jar src/Main.java src/MonteCarloIntegralInfo.java
	@javac -cp out/parcs.jar src/Main.java src/MonteCarloIntegralInfo.java
	@jar cf out/Main.jar -C src Main.class -C src MonteCarloIntegralInfo.class
	@rm -f src/Main.class src/MonteCarloIntegralInfo.class

out/Integration.jar: out/parcs.jar src/Integration.java src/MonteCarloIntegralInfo.java
	@javac -cp out/parcs.jar src/Integration.java src/MonteCarloIntegralInfo.java
	@jar cf out/Integration.jar -C src Integration.class -C src MonteCarloIntegralInfo.class
	@rm -f src/Integration.class src/MonteCarloIntegralInfo.class

build: out/Main.jar out/Integration.jar

run: out/Main.jar out/Integration.jar
	@cd out && java -cp 'parcs.jar:Main.jar' Main
