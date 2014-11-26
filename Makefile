.PHONY: all clean

all: clean less html cljs

clean:
	lein clean
	lein cljsbuild clean
	rm -rf dist/*

dist:
	mkdir dist

less: dist
	lein less once

html: dist
	lein run -m cirque.dev.html

cljs: dist
	PRODUCTION=true lein cljsbuild once production

