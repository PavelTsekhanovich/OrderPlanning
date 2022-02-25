<h1 align="center">
  Order Planning
</h1>

<h4 align="center">Back-end</h4>

<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v11-orange.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v2.6.3-brightgreen.svg" />
    </a>
</p>

## Problem Overview ##

A Manufacturing company has various warehouses in multiple cities. When an order for a product is placed by their
customers, the company wants to make sure the order is picked up from the warehouse that is closest to the customers
location and containing the product.

**Ask**

Build a microservice with a REST API based interface to solve the above problem. There should be at least two key APIs
one for adding of a new customer and the other to place of an order. The order placement API in the response should
indicate from which warehouse the product would be delivered and the time/distance for arrival. The expectation is that
the response time of order placement is fairly quick (< 500ms).

**Assumptions**

Assume that the distance/time between the customers city/location and warehouse is provided when customer is setup. The
system would compute the using the data that is provided during setup. The format of the data can be anything as per the
solution.

Assume a certain number of Warehouses & products to be present. Assume any structure for the data. Preferably represent
that in JSON format as static data (or stored in db) that can be used for computation.

**Technology requirements**

- Build the solution using Java
- Preferably build the service using Sprint Boot
- Usage of database is not necessary
- Submit a brief write up of any key decisions being made
- Bonus points for writing unit test cases (key scenarios)

## General solution ##

Since we need to carry out the order in a short time, the optimal solution was to organize a service that calculates all the distances to the Warehouses at the time of the creation of the Customer. This operation is performed asynchronously and does not block the main flow. When creating an order, we can quickly find the nearest warehouse, thereby ensuring the speed of order placement.

## Technology ##

Following libraries were used during the development of this starter kit :

- **Spring Boot** - Server side framework
- **H2 Databse** - SQL database
- **Spring Doc** - API documentation
- **MapStruct** - Data transfer object converting
- **Lucene Spatial** - Calculation distance between two coordinates
- **Flyway** - Database migration

## Running the server locally ##

To be able to run this Spring Boot app you can use the below command. You will need to run it from the root project
folder.

```
./gradlew bootRun
```

If you use IDE, you can run app from **OrderPlanningApplication.java**

## API ##

After running app, you can find API definition by follows link:

- http://localhost:8080/swagger-ui/index.html

Some important API endpoints are as follows:

- http://localhost:8080/api/v1/product/all (HTTP:GET)
- http://localhost:8080/api/v1/customer (HTTP:POST)
- http://localhost:8080/api/v1/order (HTTP:POST)

## Example of usage ##

To get all products cURL:

```curl
curl --location --request GET 'http://localhost:8080/api/product'
```

Create customer cURL:

```sh
curl --location --request POST 'http://localhost:8080/api/customer' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName": "Tom",
"lastName": "Jerry",
"latitude": 55.54,
"longitude": 36.54
}'
```

Create order cURL:

```sh
curl --location --request POST 'http://localhost:8080/api/order' \
--header 'Content-Type: application/json' \
--data-raw '{
"customerId": "3",
"productId": "2"
}'
```

## Possible approach to improve functionality ##

- Creating a batch processing distance calculation
- Introduction of additional statuses, such as whether all distances are calculated for the user, as this operation on large data can take some time
- Introduction of the number of available products
- Additional REST API
- Recalculate distances