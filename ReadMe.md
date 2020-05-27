## Requirement:
Java 8, Junit


## Notes:

- A simple java project is created. No frameworks are used (e.g. Spring). Assumption is it is not required.
- No persistence layer added , hence the data is hardcoded inside the repositories. In real world it can be
replaced by existing frameworks/tools like spring jpa/repositories.
- Items are hardcoded in productRepository.java .
- Discounts are hardcoded in  discountRepository.java .
- Code comments are kept to the minimum following the best practice that code should speak by itself.
- Tests are provided for the main functionality.
- It is assumed that that the application will work only in GBP

### Extra Bonus :
 - Discount validity period is added.
 - Discount details are output in addition to the required pricing details.



## How to Execute :
   ### Step 1: 
   mvn clean compile
   ### Step 2:
   mvn exec:java -Dexec.mainClass=com.shopping.calculator.app.ShoppingCalculatorMain -Dexec.args="<args>"
 
   #### Example:
     mvn exec:java -Dexec.mainClass=com.shopping.calculator.app.ShoppingCalculatorMain -Dexec.args="APPLE SOUP BREAD BREAD"

     Output:
     Items: APPLE SOUP BREAD BREAD 
     Price: £50.00
     Discount: £6.00
     Total: £44.00

     Discount Details: 
     Apple 10% off: -£1.00
     Soup 5% off: -50p
     Bread 15% off: -£2.25
     Bread 15% off: -£2.25
