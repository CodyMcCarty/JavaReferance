# Demo of Relationships 
I mostly just use this as a reference.  
Includes one to one, one to many, and many to one.  Also includes an example of an enum in "X".
Includes example test

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
[] add test  
[] address abstract/interface/impliments/extends or something  
[] add demo data