#include <stdio.h>
#include <stdlib.h>

double celsius_to_fahr (double tc)
{
  return (1.8 * tc + 32);
}

void main (int ac, char **av)
{
  if (ac < 2) {
    fprintf (stderr, "Usage: %s [temp]\n", av[0]);
    exit (1);
  }

  printf ("%g\n", celsius_to_fahr (atof(av[1])));
}
