#include <stdio.h>
#include <memory.h>
#include "dateNext.c"


int main(int argc, char * argv[])
{
  FILE * fp = stdin;
  char * line = NULL;
  size_t len = 0;
  ssize_t read;
  int year;
  int month;
  int day;
  Date currentDate;

  if (fp == NULL) {
    exit(-1);
  }

  while ((read = getline(&line, &len, fp)) != -1) {
    // printf("Retrieved line of length %zu :\n", read);
    // printf("%s", line);
    sscanf(line, "%d %d %d", &(currentDate.year), &(currentDate.month), &(currentDate.day));
    Date * dayNext = nextDay(currentDate);

    if (dayNext == NULL) {
      fprintf(stdout, "error\n");
    }
    else {
      if (dayNext->month < 10) {
        fprintf(stdout, "%d-0%d-", dayNext->year, dayNext->month);
      }
      else {
        fprintf(stdout, "%d-%d-", dayNext->year, dayNext->month);
      }

      if (dayNext->day < 10) {
        fprintf(stdout, "0%d\n", dayNext->day);
      }
      else {
        fprintf(stdout, "%d\n", dayNext->day);
      }
    }

  }

  // fclose(fp);

  if (line) {
    free(line);
  }

  exit(0);
}


/*
int main(int argc, char * argv[])
{
  Date currentDate;
  currentDate.year  = 0;
  currentDate.month = 0;
  currentDate.day   = 0;

  while (!feof(stdin)) {
    fprintf(stderr, "Input year: ");
    fscanf(stdin, "%d", &(currentDate.year));
    fprintf(stderr, "Input month: ");
    fscanf(stdin, "%d", &(currentDate.month));
    fprintf(stderr, "Input day: ");
    fscanf(stdin, "%d", &(currentDate.day));

    Date * dayNext = nextDay(currentDate);
    if (dayNext == NULL) {
      fprintf(stdout, "error\n");
    }
    else {
      if (dayNext->month < 10) {
        fprintf(stdout, "%d-0%d-", dayNext->year, dayNext->month);
      }
      else {
        fprintf(stdout, "%d-%d-", dayNext->year, dayNext->month);
      }

      if (dayNext->day < 10) {
        fprintf(stdout, "0%d\n", dayNext->day);
      }
      else {
        fprintf(stdout, "%d\n", dayNext->day);
      }
    }
  }

  return 0;
}*/
