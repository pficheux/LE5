CC= $(CROSS_COMPILE)gcc
CFLAGS= -O2 -Wall

PROG= jtag_enabler

OBJS= $(PROG).o

all: $(PROG)

$(PROG): $(OBJS)
	$(CC) $(CFLAGS) -o $(PROG) $(OBJS)

clean:
	rm -f *~ $(OBJS)  $(PROG)

install: $(PROG)
	cp $(PROG) $(DESTDIR)/usr/bin
