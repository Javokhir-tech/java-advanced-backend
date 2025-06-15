
## Task 1 - ETL process (40 Points)

Create data model with following fields:

    "id": int, // id of sport
    "name": string // sport name


### Create Reactive Repository
Create Setup class with following functionality:
3.1. Requests items from https://webservice.rakuten.co.jp/explorer/api/IchibaItem/Search
3.2. Parse Json Response using reactive pipelines and save objects using reactive repository


## Task 2 - API for Sports (40 Points)

Implement the following API methods using Router Functions:


    - POST /api/v1/sport/{sportname]} // create sport with name. make sure that you correctly cach exceptions if sport with provided name already exist
    - GET /api/v1/sport?q=... //search sports by name



Implement methods in reactive repository with methods to support API above