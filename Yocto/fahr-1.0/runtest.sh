#!/bin/sh

cat test.dat | while read line
do
    set $line
    echo -n "Testing $1"
    if [ "$(fahr $1)" != "$2" ]; then
	echo " KO"
	break
    fi
    echo " OK"
done
