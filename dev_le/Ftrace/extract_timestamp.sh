#!/bin/sh

TRACE_CMD=$HOME/bin/trace-cmd

if [ $# -lt 2 ]; then
    echo "Usage: $0 filename command"
    exit 1
fi

F=$1
P=$2

$TRACE_CMD report -i $F | grep "comm=$P" | while read LINE
do
    set $LINE
    if [ "$V1" = "" ]; then
	V1=$(echo $3 | tr -d ':')
	V2=$V1
    else
	V1=$V2
	V2=$(echo $3 | tr -d ':')
    fi
    echo "$V2-$V1" | bc -l
done
