# Spring Store Challenge

REST Service solution with Spring for the Problem explained in the PDF.

## Running the Project with Maven

To run the project using Maven, you can use the following command:

`mvn spring-boot:run`

## Making Get Request to Prices

Here's how you can make requests to the specified endpoint with the provided query parameters:

```
GET http://localhost:8080/check-price?product_id={productId}&brand_id={brandId}&date={date}
```

**Replace:**

- {productId} with the integer product ID;
- {brandId} with the integer brand ID;
- {date} with the ISO 8601 formatted date.

**For example:**

```
GET http://localhost:8080/check-price?product_id=35455&brand_id=1&date=2024-04-14T10:00:00
```

## Calculated Discounts

The tax to be applied (whether negative or positive) is calculated between the product with the **highest priority and lowest price** and the product with the **lowest price** within the given time interval.

**The discount percentage is calculated as follows:**

1. Retrieve the prices of the specified product and brand within the given time interval.
2. Sort the retrieved prices by priority in descending order and then by price in ascending order.
3. Calculate the discount percentage by dividing the price of the product with the highest priority and lowest price with the price of the product with the lowest price. Multiply by 100 and the total is subtracted from 100 to get the final discount percentage. If the product with the highest priority is also the price with the lowest price we calculate the discount tax with the second lowest price.
4. The calculated discount percentage represents the tax value to be applied.

By following this approach, the tax (discount percentage) accurately reflects the difference in prices between the product with the highest priority and lowest price and the product with the lowest price within the specified time interval.
