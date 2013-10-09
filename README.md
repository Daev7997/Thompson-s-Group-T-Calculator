Thompson-s-Group-T-Calculator
=============================

A software package written for finding the length of elements of Thompson's Group T (or F) given a certain generating set.
It was developed by me for use by myself during a research intership, so it doesn't really have a lot in the way of a user interface.
To use it, simply open it up in Eclipse and modify the main method in the Driver class to your liking.  PLHom objects represent
elements of the group, which are stored as the "break points" in their piecewise-linear homeomorphisms.  There are methods written for
getting some of the more commonly used generators, and you can use compose() to make an element out of an array of other elements.
You can then use the findGeo() methods to find a geodesic to your element or between two elements.

A little warning: since the Cayley Graph of Thompson's Group T grows more or less exponentially, the farther out you go the more memory and time
the code will take.  This is because (as of my writing this) there is no known formula for length in Thompson's Group T, so the code works off a
brute force algorithm.  I don't remember the exact point at which my laptop started having issues, but I believe it was somewhere around a length
of 13.
