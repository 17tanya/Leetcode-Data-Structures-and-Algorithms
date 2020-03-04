/*You can verify that this satisfies our condition that the element we’re looking for always be present in the interval (lo, hi). However, there is another problem. Consider what happens when you run this code on some search space for which the predicate gives:

          | no	| yes | 

The code will get stuck in a loop.
It will always select the first element as mid, but then will not move the lower bound because it wants to keep the no in its search space.
The solution is to change mid = lo + (hi-lo)/2 to mid = lo + (hi-lo+1)/2, i.e. so that it rounds up instead of down.
Just remember to always test your code on a two-element set where the predicate is false for the first element and true for the second.

You may also wonder as to why mid is calculated using mid = lo + (hi-lo)/2 instead of the usual mid = (lo+hi)/2.
This is to avoid another potential rounding bug: in the first case, we want the division to always round down, towards the lower bound.
But division truncates, so when lo+hi would be negative, it would start rounding towards the higher bound.
Coding the calculation this way ensures that the number divided is always positive and hence always rounds as we want it to.
Although the bug doesn’t surface when the search space consists only of positive integers or real numbers
*/
