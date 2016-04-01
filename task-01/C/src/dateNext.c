#ifndef __DATENEXT_C__
#define __DATENEXT_C__

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>
#include <memory.h>
#include <math.h>

typedef struct _Date {
  int year;
  int month;
  int day;
} Date;

#ifndef NULL
#define NULL 0
#endif

int isLeapYear(int year)
{
  if (year < 0) {
    return -1;
  }
  else if ( (year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0)) ) {
    return 1;
  }
  else {
    return 0;
  }
}

int isLastDayOfMonth(Date d)
{
  if ((d.year <= 0) || (d.month <= 0) || (d.day <= 0)) {
    return -1;
  }
  if ((d.month == 1) || (d.month == 3) || (d.month == 5) || (d.month == 7)
    || (d.month == 8) || (d.month == 10) || (d.month == 12)) {
    if (d.day == 31) {
      return 1;
    }
    else {
      return 0;
    }
  }
  else if ((d.month == 4) || (d.month == 6) || (d.month == 9) || (d.month == 11)) {
    if (d.day == 30) {
      return 1;
    }
    else {
      return 0;
    }
  }
  else if (d.month == 2) {
    if ( isLeapYear(d.year) && (d.day == 29) ) {
      return 1;
    }
    if ( (!isLeapYear(d.year)) && (d.day == 28) ) {
      return 1;
    }
    else {
      return 0;
    }
  }
  return -1;
}

Date * nextDay(Date d)
{
  Date * ret = NULL;
  /* Handle less than 0 exceptions */
  if ((d.year <= 0) || (d.month <= 0) || (d.day <= 0)) {
    return NULL;
  }
  /* Handle month and day exceptions */
  if (d.month > 12) {
    return NULL;
  }
  /* Day number can't larger than 31 */
  if ((d.month == 1) || (d.month == 3) || (d.month == 5) || (d.month == 7)
    || (d.month == 8) || (d.month == 10) || (d.month == 12)) {
    if (d.day > 31) {
      return NULL;
    } 
  }
  /* Day number can't larger than 30 */
  if ((d.month == 4) || (d.month == 6) || (d.month == 9) || (d.month == 11)) {
    if (d.day > 30) {
      return NULL;
    }
  }
  /* Check February */
  if (d.month == 2) {
    /* Case leap year */
    if (isLeapYear(d.year)) {
      if (d.day > 29) {
        return NULL;
      }
    }
    /* Normal years */
    else {
      if (d.day > 28) {
        return NULL;
      }
    }
  }

  /* When not handling exceptions */
  /* On the final day of the year */
  if ((d.month == 12) && (d.day == 31)) {
    ret = (Date *) calloc(1, sizeof(Date));
    ret->year = d.year + 1;
    ret->month = 1;
    ret->day = 1;
    return ret;
  }
  /* On the final day of the month */
  else if (isLastDayOfMonth(d)) {
    ret = (Date *) calloc(1, sizeof(Date));
    ret->year = d.year;
    ret->month = d.month + 1;
    ret->day = 1;
  }
  /* Just next day of the month */
  else {
    ret = (Date *) calloc(1, sizeof(Date));
    ret->year = d.year;
    ret->month = d.month;
    ret->day = d.day + 1;
  }

  return ret;
}

#endif
