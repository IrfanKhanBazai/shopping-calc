Requirement:
Java 8
Junit


Notes:

- A simple java project is created. No frameworks are used (e.g. Spring). Assumption is it is not required.
- No persistence layer added , hence the data is hardcoded inside the repositories. In real world it can be
replaced by existing frameworks/tools like spring jpa/repositories.
- Code comments are kept to the minimum following the best practice that code should speak by itself.
- Tests are provided for the main functionality.
- It is assumed that that the application will work only in GBP

Extra Bonus :
 - Discounts validity period is added.
 - Discount details are output in addition to the required details.



To run :
run mvn command
mvn exec:java -Dexec.mainClass=com.shopping.calculator.app.ShoppingCalculatorMain -Dexec.args="<args>"


Example:
mvn exec:java -Dexec.mainClass=com.shopping.calculator.app.ShoppingCalculatorMain -Dexec.args="APPLE APPLE SOUP SOUP"

