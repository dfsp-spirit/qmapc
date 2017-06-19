#!/bin/bash
mvn package && java -jar target/qmapc-0.1-SNAPSHOT.jar  -i src/test/resources/org/rcmd/qmapc/maps/spirit2dm9.map -o testout.map -a