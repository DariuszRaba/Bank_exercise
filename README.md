# Bank_exercise - restfull app uses external api of Bank NBP to get latest USD currency rate.

there are endpoints:

http://localhost:8080/user/create/{name}/{surname}/{pesel}
Ex. http://localhost:8080/user/create/Dariusz/Raba/03271944459
We can creeate bank user Account with main Account in PLN and two subaccounts one in PLN and one in USD. 
User must have at least 18 years old and app checks it with provided pesel number.

http://localhost:8080/user/info/{pesel}
Ex. http://localhost:8080/user/info/90060804123
We can get user info by providing pesel of user that we are interested in.

http://localhost:8080/user/convert/{pesel}/{amount}/{from}/{convertTo}
Ex. http://localhost:8080/user/convert/90060804123/100//pln/usd
We can convert cash from PLN to USD and and backwards.
