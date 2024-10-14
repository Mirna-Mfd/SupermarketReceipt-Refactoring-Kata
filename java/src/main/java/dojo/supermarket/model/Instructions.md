The main objective for this kata is to practice baby step refactoring while using the technique of the Mikado graph.

What is the Mikado method?
https://www.methodsandtools.com/archive/mikado.php

To sum up, the Mikado method helps you build a graph of your refactoring dependencies and execute them without ever breaking your codebase.

Now, let's see how this method can help us refactor our code.
The goal is to prepare the code for the addition of a new discount type: "Two for one".
First, refactor the code of "Three for two", which is similar to "Two for one", using a Mikado graph.
Then, add the new discount type.

Your Mikado graph will look like:

[ ] 👍Prepare the code for an easy addition of "X for Y" discount type family
- [ ] 👍Isolate the "Three for two" discount computation
    - [ ] ...
        - [ ] ...         
- [ ] 👍Make the "computeThreeForTwoDiscount" function generic
    - [ ] ...
    - [ ] ...
- [ ] 👍Add a new offer "TWO_FOR_ONE"
    - [ ] ...
- [ ] Parking-Lot
    - [ ] ...



