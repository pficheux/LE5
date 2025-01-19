CFLAGS += -O2 $(INCLUDE_DIR) 

PROG= fahr

OBJS = $(PROG).o

all: $(PROG)

clean:
	rm -f $(OBJS) $(PROG) *~ 

install:
	mkdir -p $(DESTDIR)/usr/bin
	cp $(PROG) $(DESTDIR)/usr/bin

test: $(PROG)
	@./runtest.sh