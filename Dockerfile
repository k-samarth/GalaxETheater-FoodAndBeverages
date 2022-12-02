From openjdk:8
copy target/FoodAndBevarages-0.0.1-SNAPSHOT.jar FoodAndBevarages-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","FoodAndBevarages-0.0.1-SNAPSHOT.jar"]
EXPOSE 8085
