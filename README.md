# compojure-api-with-spec

## Pre-requisites
* Java 8
* [Leiningen](https://leiningen.org)

## Usage

```bash
lein ring server
```

## Deploy 

```bash
lein uberjar
java -jar target/server.jar
```

## Test

Run all tests
```bash
lein test
```

`test-refresh` lein plugin is handy if you want to make changes as it re-executes tests affected by the updated code.
```bash

lein test-refresh
```

See how it works from command line with [HTTPie[(https:httpie.org).  
```bash
./http_tests
```

## Presentation
[![GitPitch](https://gitpitch.com/assets/badge.svg)](https://gitpitch.com/k2n/compojure-api-with-spec/master?grs=github&t=white)

## Discussion
[![Gitter chat](https://badges.gitter.im/gitterHQ/gitter.png)](https://gitter.im/d3_clojure/Lobby)

Copyright © 2017 Signifier, Inc.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
