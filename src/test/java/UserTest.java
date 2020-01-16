/*
Rest RestAssured

1. Create a new maven project and add RestAssured dependency. // DONE
2. Add any test framework (TestNG or JUnit). // DONE
3. Go to the link https://www.mockapi.io/clone/5d2224742ba8cf0014c44d8c to clone the mockapi project.
4. Create a test class and add 5-10 test methods. Each method should represent
separate request to the mock service.
5. Create a request to the mock service to get all users.
Endpoint: /api/v1/users.
Extract all users from the response body and add them to ArrayList(Type of array list you can pick by yourself).
Print all user's first names, last names and ages to the console.  // DONE
6. Create a separate class to represent a user. This class should have all the fields according to the mock service.  // DONE
7. Create an object of a user and send it to mock service in post request.  // DONE
8. Get any user by id using GET request and create an object using the request body. ?
Endpoint: api/v1/users/id
9. Delete any user by ID using DELETE request.  // DONE
10. Update any user by ID using PUT request.  // DONE
11. All requests should verify that response status code is correct.  // DONE
 */

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class UserTest {

    @BeforeClass
    public static void setUp() {
        baseURI = "http://5e160a2222b5c600140cf814.mockapi.io/api/v1";
    }

    @Test
    public void getAllUsers() {
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Method name: " + name);
        final List<Object> allUsers = given().
                when().
                get("/users").
                then().
                assertThat().statusCode(200).
                extract().body().jsonPath().getList("");
        List<String> employeeSalariesDouble = new ArrayList<>();
        allUsers.forEach(System.out::println);
        allUsers.forEach(s -> employeeSalariesDouble.add((s.toString())));
    }

    @Test
    public void getUser() {
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Method name: " + name);
        given().
                when().
                get("/users/3").
                then().
                assertThat().statusCode(200).extract().body().jsonPath().prettyPrint();
    }

    @Test
    public void deleteUser() {
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Method name: " + name + "\n This user is deleted");
        given().
                when().
                delete("/users/11").
                then().
                assertThat().statusCode(200).extract().body().jsonPath().prettyPrint();
    }

    @Test
    public void createUser() {
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Method name: " + name);
        final String body = "{\"firstName\":\"firstName 9\",\"lastName\":\"lastName 9\",\"age\":91}";
        given().
                header("x-api-key", "DEMO-API-KEY").
                header("content-type", "application/json").
                body(body).
                when().
                post("/users").
                then().
                assertThat().statusCode(201).extract().body().jsonPath().prettyPrint();
    }

    @Test
    public void updateUser() {
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Method name: " + name);
        System.out.println("User before updating:");
        getUser();

        System.out.println("User after updating");
        final String body = "{\"firstName\":\"firstName 9\",\"lastName\":\"lastName 9\",\"age\":91}";
        given().
                header("x-api-key", "DEMO-API-KEY").
                header("content-type", "application/json").
                body(body).
                when().
                put("/users/3").
                then().
                assertThat().statusCode(200).extract().body().jsonPath().prettyPrint();
    }
}
