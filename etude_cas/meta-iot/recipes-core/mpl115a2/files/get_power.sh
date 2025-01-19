#!/bin/sh
#
# Main script to get power from USB, leds, heat
#
#set -x

# Init
BASE_DIR=/home/root

N=0
FIRST_NAME="Pierre"
LAST_NAME="FICHEUX"
LOCATION="Arcachon"
CUSTOMER_ID=1

JSON_FRAME=$BASE_DIR/frame.json

MQTT_TOPIC=l0r4sm1l3-pr0t0
#MQTT_TOPIC=jdev2017
MQTT_SERVER=iot.eclipse.org

LOOP_DELAY=2
MQTT_DELAY=10

# temp simulation
T0=20
T=$T0

# Heat (max power + max temp)
P_MAX=700
T_MAX=30

A=$(expr $P_MAX / $T_MAX)

get_heat_power ()
{
    echo $(expr $(expr $1 \* -${A}) + $P_MAX)
}

rm -f $JSON_FRAME

while [ 1 ]
do
    # Get temp + increment
    T=$(mpl115a2 | cut -d " " -f5 | cut -d '.' -f1)
         # Temp simulation
    #    T=$(expr $T + 1)
    #    if [ $T -gt 30 ]; then
    #	T=$T0
    #    fi

    if [ $? -eq 0 ]; then
        if [ $T -ge $T_MAX ]; then
	    S=BATT
	    P_HEAT=0
        else
	    S=AC
	    P_HEAT=$(get_heat_power $T)
        fi
    else    
	# Default to T0 deg if sensor problem
	T=T0
	P_HEAT=$(get_heat_power $T)
    fi

    # Init frame
    echo "{ \"first_name\" : \"${FIRST_NAME}\", \"last_name\" : \"${LAST_NAME}\", \"location\" : \"${LOCATION}\", \"customer_id\" : \"${CUSTOMER_ID}\", \"usb\" : [ ], \"lights\" : [ { \"id\" : \"1\",    \"power\" : \"0\"  },  { \"id\" : \"2\", \"power\" : \"0\"  },  { \"id\" : \"3\",  \"power\" : \"0\" },  { \"id\" : \"4\", \"power\" : \"0\"  } ]," > $JSON_FRAME

    # Get total power
    PALL=${P_HEAT}

    # Update frame
    echo "\"heat\" : { \"power\" : \"${P_HEAT}\" }, \"all\" : { \"power\" : \"${PALL}\" }, \"source\" : { \"status\" : \"${S}\" } }" >> $JSON_FRAME

#    echo "$N $T deg"
    N=$(expr $N + $LOOP_DELAY)

    # MQTT
    if [ $N -ge $MQTT_DELAY ]; then
	N=0
        mosquitto_pub -h ${MQTT_SERVER} -t ${MQTT_TOPIC} -m "$(cat $JSON_FRAME)"
	echo
        echo -n "*** JSON $(date) = "
        cat $JSON_FRAME
    fi

    sleep $LOOP_DELAY
done
