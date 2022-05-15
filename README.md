# Webflux example

This project provide apis to create a Food's order. Both entities 'Food' and 'Customer' are pre created on db.\

The main apis of this repo is 'POST -> /orders' that is able to create an Order which requires pass customerId and foodId by request.\
If you look to OrderService and CustomerService classes you will find the some usages of Mono and Flux which is the propose of this repo.

[link to OrderService class](https://github.com/marlonklc/webflux-example/blob/master/src/main/java/com/webfluxexample/service/OrderService.java)
[link to CustomerService class](https://github.com/marlonklc/webflux-example/blob/master/src/main/java/com/webfluxexample/service/CustomerService.java)

PS: I know that some implementations doesn't make sense on real life, think on implementation that was used of mono and flux, ok ?!

## How to run ?

You just need configure the environment variable 'MONGO_URI' that needs to point any Mongo db.
```
MONGO_URI=mongodb+srv://<user>:<pass>@<cluster-host>/<db-name>?authSource=admin&replicaSet=<cluster-shard>&readPreference=primary&ssl=true
```
Tip: The Mongo Atlas plataform provides a free mongo db to use. [check it out](https://account.mongodb.com/account/login?nds=true)