# Demo

This is a demo project using Java/Spring and JPA/Hibernate knowledge.

It uses JPA/Hibernate as a persistence layer

## Getting Started

The project is a Spring Boot Gradle project. You can start up the server by running

    gradlew bootRun

Don't worry that it only gets to 80%, this is just the percentage through the Gradle build script. You're looking out for a line that says

    Started CandidatedemoApplication in x.xxx seconds
    
You are probably used to using an IDE for development. Run the task `gradlew eclipse` to generate a project file and import it as a new project in Eclipse.

Alternatively the [Buildship](https://gradle.org/eclipse/) plugin can help. You can find it in the Eclipse Marketplace.

There is an equivalent task for IntelliJ IDEA `gradlew idea`

## Tasks

### 1. Create product list page
* Create a page with a controller and view to render the list of products. Jstl will work in a jsp template.

### 2. Add prices to the product list
* Show the price of each product next to its name in the list.
* The current currency to display should be stored in a session variable.

### 3. Create a product edit page
* Create a controller, form bean and jsp to edit a product and its attributes
* Link to the product list
* Implement form validation for
    * name: not blank, max length 50 characters
    * prices: valid currency, amount is a number, amount is greater than 0
* If the form fails validation then reshow the form with error messages by the relevant field

### 4. JSON output
* Create a controller method to return all products as json.

