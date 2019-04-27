## Bobby's Chocolates

_Chocolates_ prints a list of orders that include any bonus chocolates awarded based on the following promotional rules:

* receive 1 bonus milk chocolate for every `X` milk chocolates purchased
* receive 2 bonus dark chocolates for every `X` dark chocolates purchased
* receive 1 bonus milk chocolate and white chocolate for every `X` white chocolates purchased

Original orders are read from the file `input/orders.csv` with the following example CSV format:

```
type,cash,price,bonus_ratio
"milk",12,2,5
"dark",13,4,1
"white",6,2,2
```

#### Build and Run

These steps assume a Linux environment with JDK 1.8 already installed.

1. Unzip
    ```
    unzip B-E-Stride-code-test.zip
    ```
    
2. Build
    ```
    mvn package
    ```

3. Test
    ```
    mvn test
    ```
    
4. Run
    ```
    mvn exec:java
    ```

#### Design Notes

###### Object-oriented design

The problem is simple enough that it doesn't require a full object-oriented solution, and so we only need a single class with a `main()` method along with a few other support methods.

###### Don't give away chocolate for free!

    price = (price < 1) ? 1 : price;

To avoid a division by zero when calculating the number of chocolates that can be purchased for an order, we require a minimum chocolate price of $1. This requirement also avoids the alternative situation where an infinite amount of free chocolates could be purchased.

###### No bonuses if there's no bonus ratio

    int bonus = (bonusRatio < 1) ? 0 : (cash / price) / bonusRatio;
    
To avoid another division by zero, this time with the bonus ratio, we default to zero bonus chocolates if the ratio isn't 1 or greater.

###### 'Switch' over 'If'

    switch (chocolateType)
    
Using an `if` statement for the main decision logic was less code overall, but harder to read with the all the null-safe `equals()` checks needed.

###### Expected exceptions

    (expected = IllegalStateException.class)
    
Invalid or malformed chocolate types are handled by simply throwing an uncaught exception. There are a handful of unit tests that expect this exception.
    
###### A proactive client suggestion

Consider offering ruby chocolate: [Ruby Chocolate Wants a Place at the Table With Dark, Milk and White](https://www.nytimes.com/2017/09/07/business/ruby-chocolate-pink-flavor.html)!