# Demo of Spring
I mostly just use this as a reference.  
Includes one to one, one to many, and many to one.  Also includes an example of an enum in "X".
Includes example test.  I'll eventually include other examples.  This will stay on an in memory DB

## Swagger
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

## Relations:  
- many-to-one from Hub to X
- one-to-many from Y to Hub
- many-to-many from Hub to B
- one-to-one from X to Z
- many-to-one from Address to NeedsAdd
- one-to-many from NeedsBillingAddress to BillingAddress

// TODOs: list
- [x] make a few relationships
- [x] add test for every file
- [x] add test < 90 % class/method/line coverage
- [] for the address model: abstract/interface/implements/extends or something (REST is not very dry, why does no one use an interface with default methods?)  
- [] add demo data  
- [] update put to use transactional  
- [] update put test w/o demo data  for transactional  
- [] update jpa with a few queries 
- [] update test w/o demo data for jpa queries
- [] get with url query, example of
- [] get with url query, do that thing like in dotNet
- [] update get tests w/o demo data
- [] customize swagger
