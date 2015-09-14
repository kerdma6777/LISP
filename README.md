# LISP
Program that performs string operations on a given arithmetic LISP expression that containers an operator followed by 1 or more sublists.

Created for the Senior Divison of Contest #2 for the 2014-2015 [American Computer Science League Competition](acsl.org).

Example of a valid expression: (ADD (EXP -3 2) (SQR 5) (SUB 6 2) (MULT 6 7 -2 3) (DIV 15 5))

The options the user can enter to manipulate the expression are:

COUNT - Counts the number of sublists 

REMOVE - Removes the sublists between an upper and lower bound i.e. REMOVE 1 3 would remove the 2nd through the 4th sublist (index starts at 0)

SORT - Sorts the sublists between and upper and lower bound i.e. SORT 1 3 would sort the 2nd through the 4th sublist by the function (MULT would have a higher priority than SUB)

REVERSE - Removes the sublists between an upper and lower bound i.e. REVERSE 1 3 would reverse the order of the 2nd through the 4th sublist (index starts at 0)

MAXIMUM - finds the longest sublist
