# Database Structure Issues and Improvement Proposals

## Description

This project involves a database to store information about films, actors, categories, and other entities related to cinematography.


## Issues

1. **film_text**:
    - Absence of a foreign key on the field film_id creates inconsistency in the One-to-One relationship between the tables film and film_text.
2. **Differences in film_id Column Data Types:**:
   - Tables film and film_text are created with differences in the data types of the film_id columns. In the film table, the film_id column has the type smallint unsigned auto_increment, whereas in the film_text table, it is defined as smallint. This may lead to the impossibility of creating a foreign key due to data type mismatch. To address this issue, it's recommended to change the data type of film_id in the film_text table to match the data type of the film_id column in the film table.

## Improvement Proposals

1. Add missing foreign keys to ensure data integrity.
2. It is recommended to change the data type of film_id in the film_text table to match the data type of the film_id column in the film table. If film_id in the film table is smallint unsigned, you can change the data type in the film_text table as follows:
```sql
ALTER TABLE film_text MODIFY COLUMN film_id smallint unsigned;
```

This is an overview of the database structure issues and improvement proposals. Please discuss and make necessary adjustments as needed.