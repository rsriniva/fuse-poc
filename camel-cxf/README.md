# System requirements

Before building and running this quick start you need:

* Maven 3.1.1 or higher
* JDK 1.7 or 1.8
* JBoss Fuse 6


# Build and Deploy


1. Change your working directory to `camel-cxf` directory.
* Run `mvn clean install` to build the quickstart.
* Start JBoss Fuse 6 by running bin/fuse (on Linux) or bin\fuse.bat (on Windows).
* In the JBoss Fuse console, enter the following command:

        osgi:install -s mvn:org.org.jboss.quickstarts.fuse/camel-cxf/1.0

* Fuse should give you an id when the bundle is deployed

* You can check that everything is ok by issuing  the command:

        osgi:list
   your bundle should be present at the end of the list


# bundle

To use the application be sure to have deployed the quickstart in Fuse as described above. 

1. cd to the 'camel-cxf-code-first' directory
* Run 'mvn -Ptest test

Or open 'http://localhost:8181/cxf/' in a browser to see 'OrderEndpoint' listed as a SOAP service.

# Update the bundle

    osgi:update <bundle_id> mvn:org.org.jboss.quickstarts.fuse/camel-cxf/1.0

# Undeploy the bundle


To stop and undeploy the bundle in Fuse:

1. Enter `osgi:list` command to retrieve your bundle id
2. To stop and uninstall the bundle enter

        osgi:uninstall <bundle_id>