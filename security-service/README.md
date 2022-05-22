# Overview

For now these are my notes on the updates to take the default output of Spring Initializr
and create a working example.

# Features

* RFC-7519 JWT authentication
* Role and permission model for authorization
* QueryDSL
* HATEOAS
* Pagination

# Use Spring Initializr
TODO: dependencies
* 

# Database

* Create schema, users, and grant permissions to schema in db-setup.sql
* Update application.properties with the example-ddl user credentials
* Run this prior to starting the app

NOTE: spring using DDL account exclusively for now

# Maven pom.xml

* Add javax.inject to use JSR-330 annotations
* Add QueryDSL
* Add Google Guava

# Update application.properties 

* Add spring.security.user.*
* Update spring.datasource.* with a user that has DDL privileges (example-ddl)
* Validate spring.liquibase.change-log points to top level liquibase changeset

# Pending
