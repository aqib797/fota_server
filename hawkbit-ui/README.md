# hawkBit User Interface

The hawkBit user interface is based on the Vaadin and Vaadin-Spring framework and allows to manage software updates and large scale roll-outs via a user interface.

## Debugging client-side code 
### Debug using SuperDevMode
The SuperDevMode can be used to debug client side code without any browser plugin.

#### Using SuperDevMode with chrome :

- Add required maven dependencies
	- Add vaadin-client-compiler dependency
	- Add jetty dependencies (version : 8.1x)
- Set redirect property in the AppWidgetSet.gwt.xml module descriptor as follows
	- < set-configuration-property name="devModeRedirectEnabled" value="true" />
- Create launch configuration for the SuperDevMode 
	- The main class to execute should be com.google.gwt.dev.codeserver.CodeServer.
	- Add fully-qualified class name of widgetset (org.eclipse.hawkbit.ui.AppWidgetSet) as parameter
- Enable debug in chrome
	- Chrome inspector window ▸ Click on settings icon ▸ Scripts ▸ Enable source maps option
- Run the SuperDevMode Code Server with the launch configuration created above
<<<<<<< HEAD
- Open http://localhost:8085/UI/?debug  .Click on "SuperDev" button in debug console (Alternatively can directly add ?superdevmode parameter to URL)
=======
- Open http://localhost:8080/UI/?debug  .Click on "SuperDev" button in debug console (Alternatively can directly add ?superdevmode parameter to URL)
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
- Widgetset is compiled and you can see the java code files loaded in 'Chrome inspector window ▸ Source tab'


#### Using SuperDevMode with Eclipse :

- Install the plugin from http://sdbg.github.io/p2
- Start the server and Super Dev Mode as mentioned above
- Create a new launch configuration in Eclipse
	- Type is "Launch Chrome"
<<<<<<< HEAD
	- http://localhost:8085/UI/?superdevmode
=======
	- http://localhost:8080/UI/?superdevmode
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
- Launch the new configuration in debug mode
- Now breakpoints in eclipse can be set
