# Car Renta - Spring Boot

## API calls - Examples

### Cars
http://server:port/car

http://server:port/car?size=3&sort=make

### Users

http://server:port/user

http://server:port/user?size=1&page=1&sort=username

## Integration tests

Use 'integration' profile to run integration tests as follows:

```mvn test -Dspring.profiles.active=integration```

## User Credentials

Basic authentication is required to make API calls.

Use the combinations below as per the SQL file provided:

| Username   | Password  |
| ---------- | ----------|
| user1      | password1 |
| user2      | password2 |
| user3      | password3 |

Note: passwords have been encrypted.

Bcrypt Generator: https://bcrypt-generator.com