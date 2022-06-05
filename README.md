# food-order-simulator
Generates fake food orders for building food delivery app

http://localhost:8080/orders/stream

![demo](media/order-stream.gif)


## Referred with thanks
### java-faker
- https://github.com/DiUS/java-faker
- https://www.baeldung.com/java-faker

### Food Order dataset
- https://www.kaggle.com/henslersoftware/19560-indian-takeaway-orders

### Others
- https://www.vertabelo.com/blog/a-restaurant-delivery-data-model/
- https://www.honeybadger.io/blog/uuids-and-ulids/
- https://mkyong.com/spring-boot/spring-boot-webflux-server-sent-events-example/

## Start the Fake Order Server 
`mvn clean spring-boot:run`

## Access the stream order endpoint

- http://localhost:8080/orders/stream  
- `curl -s --no-buffer http://localhost:8080/orders/stream`

### Sample Order
```json5
{
  "customer": {
    "id": "01EWCANBTSGF90ZC6W54GS42DF",
    "firstName": "Christoper",
    "lastName": "Corkery",
    "email": "nvfjh07@gmail.com",
    "phone": "(106) 292-3906"
  },
  "deliveryAddress": {
    "streetAddress": "00791 Dreama Trafficway Suite 271",
    "city": "Wallybury",
    "stateAbbr": "VA",
    "zip": "20148"
  },
  "order": {
    "orderId": "01EWCANBTSHX0NH4CDX76VPNMA",
    "confirmationCode": "b3407bd6",
    "orderTime": "2021-01-18T22:22:55.1937567",
    "orderItems": [
      { "name": "Saag - Lamb", "quantity": 1, "price": 8.95 },
      { "name": "Mushroom Bhajee", "quantity": 4, "price": 5.95 },
      { "name": "King Prawn Butterfly", "quantity": 3, "price": 5.95 },
      { "name": "Paneer Dansak", "quantity": 2, "price": 8.95 }
    ],
    "itemsCount": 4,
    "discountAmount": 0.0,
    "taxAmount": 1.72,
    "amount": 70.22
  }
}
```

### Maintenance
```shell
# check for maven dependencies updates  
mvn versions:display-dependency-updates
 
# check for maven plugin updates  
mvn versions:display-plugin-updates
```