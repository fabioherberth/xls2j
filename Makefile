.PHONY: build

build:
	rm -Rf target
	@echo "Starting compilation..."
	mvn clean package
	@echo "End of compilation"
