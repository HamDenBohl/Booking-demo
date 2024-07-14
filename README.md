Ensure your IDE is configured to handle Lombok properly. If you are using IntelliJ IDEA, make sure you have the Lombok plugin installed and annotation processing enabled (File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable annotation processing)

Ensure Lombok Plugin: Verify that the Lombok plugin is installed in your IDE.

Enable Annotation Processing: Make sure annotation processing is enabled in your IDE settings.


If database is not present in MySQL make sure to change 'spring.jpa.hibernate.ddl-auto=' in application.properties to "create"
when running 'restartDockerContainers'

To kill VM engine if consuming too much memory: 

CMD (Administrator)

Run: Tasklist /fo table

Locate task

execute 

TASKKILL /IM "process name" /F

or

TASKKILL /F /PID pid_number
