# TransportCompanyApp
Application for managing a transportation company.

### Classes

1. Person(abstract)  
Class to model persons(used for drivers and clients).  

2. Driver  
*Extends **Person***  
Class to model the drivers of the company.  
  
3. Client  
*Extends **Person***  
Class to model the clients.  
  
4. Car  
Class to model the cars.
A car has a certain *route* that it operates on.  
  
5. City  
Class that describes the cities in wich the company operates.  
The cities are also used as nodes for transportation and routing.  
  
6. Link  
Class that describes a link between two *cities*.  
  
7. Route  
Class that describes a route.    
A route is made out of multiple *links* and has two main *cities* as starting/ending points.  
  
8. Package  
Class that describes a package.  
A package has volume,weight and is owned by a *client*.  
 
### Interactions
1. DriverService
    - Add/remove drivers
    - Get driver by ID
    - Get drivers by car
    - Set a drivers car
    - Show all drivers  

1. ClientService
    - Add/remove clients
    - Show all clients

1. CarService
    - Add/remove cars
    - Get car by registration number
    - Show all cars (Ordered by RegNr)
    
1. CityService
    - Add/remove cities
    - Show all cities

1. LinkService  
    - Add/remove links  
    - Get shortest link between two cities(time or distance)
    - Show all links
    
1. PackageService
    - Add package
    - Get packages for client    
    
1. TransportService  
    - Add/remove transports
    - Get transports for a certain date
    - Show all transports
      
**Most interactions are available through a interactive console menu**