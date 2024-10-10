# Mikado Graph

## Refactoring Goal

Prepare the code for an easy addition of new types of discount, such as "Two for one", "Fifty percent discount" and "Four for amount"

## Refactoring Graph

`List below is only the starting point. Please complete along the way...`

- [ ] 👍Prepare the code for an easy addition of "X for Y" discount type family
  - [ ] 👍Isolate the "Three for two" discount computation
    - [ ] Extract the discount computation of "Three for two" type in a method
      - [ ] Separate the computation part from the addition of the discount to the receipt
        - [ ] Clean-up each branch code leveraging on your IDE recommendations
          - [ ] Cut the existing code and paste it inside both the if and the else blocks
            - [ ] Create a high level if(offer.offerType == SpecialOfferType.THREE_FOR_TWO)/else at the start of the method
              - [ ] Move the discount code of "Three for two" in a single if, inside method "handleOffers"
  - [ ] 👍Make the "computeThreeForTwoDiscount" function generic
    - [ ] 👍Renaming
      - [ ] Rename "computeThreeForTwo" function
      - [ ] Extract 3 and 2 in dedicated variables x and y
    - [ ] 👍Parametrize "computeThreeForTwoDiscount" with XForY record
      - [ ] Replace x and y in the compute function with XForY record
          - [ ] Create XForY record that contains x and y (x= 3 and y= 2 for the THREE_FOR_TWO offer)
- [ ] 👍Add a new offer "TWO_FOR_ONE"
  - [ ] Enrich SpecialOfferType with a new type "TWO_FOR_ONE"
      - [ ] Add a new test covering this
- [ ] Parking-Lot
  - [ ] Optional: Remove quantityAsInt parameter from the compute function

## Note about Mikado Graph format

There are many ways to build a Mikado Graph, each of them coming with its pros & cons

- Using a **text file** as in here is a simple and cheap way to do it,
  with the advantage that it can easily be shared remotely and stored in source control along with the code being refactored
- Another solution is to use **post-it on a board** when all contributors are in the same location
- Another nice-looking solution is to use a **mind-mapping tool**, as long as everyone in the team is able to access it