# My approach to solution

1) Write a service which exposes a Rest API endpoint, Internally this service adds the request to the queue and returns a "200" response. (Acts as message producer to the queue).
2) Write a message consumer service which consumes the message and calls a third party API.
3) Consumers will have the rate limiter for the calls to third party API.
4) Take care of logging to console the status of system, security and performance etc.

# Lightbox Solution Details 
This gradle and springboot based multi module project. Java 13 version is used.

There is only one API post enpoint exposed at http://{ip}:8080/v0/accept

There is a seperate module for consumer of messages. Code for consumer module is in "Consumer" folder.

Other modules such as DTO, Consumer, Converter, Model and Services are shared modules.

#Steps To Run The Project 
# Rest (Producer module)

To run the project from command line go to parent directory i.e Lightbox and run the command

gradle Rest:bootRun

Post:/v0/accept
Body: 
{
"msg": "Testing"
}

To run consumer navigate to parent directory i.e "Lightbox" and run the below command.

gradle Rest:bootRun

If using Intellij, It has the click to run the options in Gradle navigation panel.

# Note 
A free version of Rabbit MQ is deployed in cloud and can be accessed for a few more days. 
So you can run the application with java and gradle installed in the system, without any blocker 