all: run

clean:
	rm -f out/Main.jar out/Integration.jar

out/Main.jar: out/parcs.jar src/Main.java src/MonteCarloIntegral.java
	@javac -cp out/parcs.jar src/Main.java src/MonteCarloIntegral.java
	@jar cf out/Main.jar -C src Main.class -C src MonteCarloIntegral.class
	@rm -f src/Main.class src/MonteCarloIntegral.class

out/Integration.jar: out/parcs.jar src/Integration.java src/MonteCarloIntegral.java
	@javac -cp out/parcs.jar src/Integration.java src/MonteCarloIntegral.java
	@jar cf out/Integration.jar -C src Integration.class -C src MonteCarloIntegral.class
	@rm -f src/Integration.class src/MonteCarloIntegral.class

build: out/Main.jar out/Integration.jar

run: out/Main.jar out/Integration.jar
	@cd out && java -cp 'parcs.jar:Main.jar' Main
