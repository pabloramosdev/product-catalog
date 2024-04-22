# Spring Boot application of prices rest api

## Overview
The following repo contains an example of an Spring Boot application that exposes a rest api
to retrieve the better price of a product from a brand.

## Guidelines
Run the example of connecting to OpenFin and creating applications

1. Clone this repository

2. Go to the root directory and compile with the command

```shell
	mvn clean install
```

3. Start the app with the next command:

```shell
	mvn spring-boot:run
```

4. Once the app starts open a browser and write:

```text
	http://localhost:8080/swagger-ui.html
```

5. This is the dashboard to test the app

![Open Api Dashboard](/images/price-controller-swagger.PNG)

6. To test the app clock on "Try it out" button and fill the required fields with these values
   1. fare_date = 2020-06-14 10:00:00 brand_id = 1 product_id = 35455
   2. fare_date = 2020-06-14 16:00:00 brand_id = 1 product_id = 35455
   3. fare_date = 2020-06-14 21:00:00 brand_id = 1 product_id = 35455
   4. fare_date = 2020-06-15 10:00:00 brand_id = 1 product_id = 35455
   5. fare_date = 2020-06-16 21:00:00 brand_id = 1 product_id = 35455

7. In above tests the result is like this

```json
{
  "productId": 35455,
  "brandId": 1,
  "fare": 4,
  "startDate": "2020-06-15T16:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 38.95
}
```

## Source Code Review

The app is developed in a three layers architecture described below:

1. Controller Layer: In this layer there is a PriceController with one method that receives three parameters,
the fare_date (a.k.a application_date), product_id and brand_id.

```java
@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<PriceDto> getPrices(@RequestParam("fare_date") LocalDateTime fareDate,
                                          @RequestParam("product_id") Integer productId,
                                          @RequestParam("brand_id") Integer brandId);
```

In this layer the methods of service layer are invoked

```java
priceService.productPriceWithHighestPriority(fareDate, productId, brandId);
```

2. Service Layer: This is the business layer where repository is invoked and maps the result to Dto. It has
only one method:

```java
public PriceDto productPriceWithHighestPriority(LocalDateTime fareDate, Integer productId, Integer brandId);
```
This method invokes PricesRepository to retrieve data and then maps to PriceDto:

```java
priceRepository.findProductPriceByBrandAndDateRange(fareDate, productId, brandId)
                .map(priceMapper::entityToDto)
                .orElseGet(() -> PriceDto.builder().build());
```

3. Finally, the data layer access to database directly using PricesRepository:

```java
@Query("select p from Price p where p.productId = :productId and p.brandId = :brandId and p.startDate <= :date and p.endDate >= :date order by p.priority desc limit 1")
Optional<Price> findProductPriceByBrandAndDateRange(@Param("date") LocalDateTime applicationDate,
                                                    @Param("productId") Integer productId,
                                                    @Param("brandId") Integer brandId);
```

This method executes a query to a H2 in memory database, filter by brand_id, product_id, between start_date and end_date and order by priority desc.
Finally, limit the result to the first row that is what we are looking for.


## Technologies used 

1. Java 17
2. Spring Boot 3.2.5
3. Open API 2.5.0
4. JUnit 5.10.2
5. H2 database 2.2.224
6. Maven 3.9.5

## Disclaimers
* This is an app for training purposes only.
* Its possible that the repo is not actively maintained.

## Support
Please enter an issue in the repo for any questions or problems.
<br> Alternatively, please contact us at pabloramosdev@gmail.com