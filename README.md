# Flat offers aggregator

### Example of Hexagonal architecture with high cohesion modularization, CQRS and fast BDD tests in Java

This project is my learn of Behaviour Driven Development, Domain Driven Design, Command Query Responsibility Segregation. 

It is based on https://github.com/jakubnabrdalik/hentai Below is part of its description:

This repo is an example of Hexagonal architecture with sensible modularization on a package level, that provides high cohesion, low coupling, and allows for Behaviour Driven Development that has

- allows for most tests to be run in milliseconds (unit tests without IO)
- while at the same time not falling into the trap of testing INTERNALS of a module (no test-per-class mistake)
- tests that focus on behaviour of each module (refactoring does not require changing test)
- just enough intergration/acceptance tests with focus on performance (minimum waiting for tests to pass)
- tests that describe requirements (living documentation)
- modules that have high cohesion (everything hidden except for APIs) and low coupling (modules connected via their APIs
- easy to explain, understand and follow


---

# The problem

Real problem, with a set of requirements.

## Project – Apartment rental offers aggregator

For people, who want rent an appartment we want to create aggregator of offers from other services.
We want three primary functions.
- Update set of offers from others services
- Have an set of custom criteria
- Getting all available offers
- Getting offers with my criteria
 
## External services
External services have set of offers in a form of html or json. Parameters:
 - url
 - flat's parameters recognize method

## Offers
Offers has own parameters: 
 - title of offer
 - source with source code
 - address
 - flat area
 - level in building
 - information about garden, balcony, terrace, storage room, basement
 - price (with all charges, if we have that information)

## My criteria
To parameters user can criteria own criteria:
 - what should be in a title
 - localization area
 - min. and max. of flat area
 - min. and max. of level in building
 - requirement garden or others 
 - min. and max. price (including charges from offer or given by user)
 - travel time from home to work and/or other places

## Examples of price calculation

Basic price: 2000 zł
Administrative charge: 400 zł
Other charges without electricity: 300 zł
Electricity charge: 100 zł
Total price: 2800 zł

When lack of some charges
Basic price: 2000 zł
Administrative charge: 400 zł
Other charges without electricity: not given in offer
Electricity charge: not given in offer
User's Other charges: 500 zł
User's Other charges: not given
Total price: 2900 zł (with addnotation: lack of electricity charge)


---

# Acceptance specifications

At beginning, we can create one main happy path specification. If this specification is implemented, our project will have value. 

## Happy path scenario:

As a person, who want to rent a flat, I want to:

given: 'external service has in offer a flat 50m2 on Kosciuszki 2 Street on 2nd floor and 80m2 flat on Strzegomska 50 Street on 5th floor'

when I go to /flats
then I see no flats

when I go to /update
then I see that two flats are added

when I go to /flats
then I see both flats

when I go to /mycriteria
then I see I have no criteria

when I go to /flatsWithMyCriteria
then I see both flats

when I post to /mycriteria with flatFieldMin equal 60m2
then I see I have criterium flatFieldMin equal 60m2

when I go to /flatsWithMyCriteria
then I see 80m2 flat on Strzegomska 50 Street on 5th floor

when I post to /mycriteria with street equal Kosciuszki
then I see I have criterium flatFieldMin equal 60m2 and street equal Kosciuszki

when I go to /flatsWithMyCriteria
then I see no flats

when I post to /mycriteria with street equal Strzegomska
then I see I have criterium flatFieldMin equal 60m2 and street equal [Kosciuszki, Strzegomska]

when I go to /flatsWithMyCriteria
then I see 80m2 flat on Strzegomska 50 Street on 5th floor

---

# Modules

The list of our modules with their responsibilities 

flats
- list
- show
- update

flatsWithMyCriteria
- list
- show

mycriteria
- list
- show
- add
- remove
- update

user
- getLoggedUser

