# Forked code base 
This repository is forked from https://github.com/RaiderRobotix/blue-alliance-api-java-library maintained by RaiderRobotix. The RaiderRobotix crew did a lot of very good work in creating and maintaining the original code base. This code base is forked for the express purpose of making Metal Mustang (2410) adaptations as required for our scouting application.

# The Blue Alliance API Java Library [![Build Status](https://travis-ci.org/RaiderRobotix/blue-alliance-api-java-library.svg?branch=master)](https://travis-ci.org/RaiderRobotix/blue-alliance-api-java-library)

Java client library to retrieve data from The Blue Alliance using TBA API v3

Full Javadoc documentation can be found [here](http://raiderrobotix.github.io/blue-alliance-api-java-library/)

## Usage

Begin by creating a TBA object with your Read TBA API Key. This can be found or generated on your [account dashboard](https://www.thebluealliance.com/account).

    String authKey = // your TBA API read key
    TBA tba = new TBA(authKey);

### Regular Usage

The library allows access to almost all of the calls in [The Blue Alliance API v3 documentation](https://www.thebluealliance.com/apidocs/v3). 

They are grouped into requests with team, event, district, or match parameters, and you will need to use the `teamRequest`, `eventRequest`, or `matchRequest` instance variables found in the [`TBA` class](http://spencerng.github.io/blue-alliance-api-java-library/com/thebluealliance/api/v3/TBA.html).

Here is an example of retrieving an array of teams in the FIRST Mid-Atlantic district in 2017:

    Team[] midAtlanticTeams = tba.districtRequest.getTeams("2017mar");

A list of request methods for each request object can be found [here](http://raiderrobotix.github.io/blue-alliance-api-java-library/com/thebluealliance/api/v3/requests/package-summary.html).

### Advanced Usage

If you want to utilize the `If-Modified-Since` and `Last-Modified` headers, you will need to make a direct URL request with the [`getDataTBA(String urlDirectory, String ifModifiedSince)` method](http://spencerng.github.io/blue-alliance-api-java-library/com/thebluealliance/api/v3/requests/DataRequest.html#getDataTBA-java.lang.String-java.lang.String-) in the [`DataRequest` class](http://spencerng.github.io/blue-alliance-api-java-library/com/thebluealliance/api/v3/requests/DataRequest.html). This will return an [`APIResponse`](http://spencerng.github.io/blue-alliance-api-java-library/com/thebluealliance/api/v3/requests/APIResponse.html) object with JSON data, the HTTP response code, and the `Last-Modified` header. 

The JSON data will need to be deserialized into an object model with a method in the [`Deserializer` class](http://spencerng.github.io/blue-alliance-api-java-library/com/thebluealliance/api/v3/Deserializer.html) before being used.

Here is an example of fetching the `Match` objects for the 2017 Mount Olive District Event, if they have been updated.

	APIResponse resp = tba.dataRequest.getDataTBA("/event/2017njfla/matches");
	String lastModified = resp.getLastModified();
	Match[] matchList = Deserializer.toMatchArray(resp.getJson());

	// Execute the following code block after waiting or in a separate method
	
	resp = tba.dataRequest.getDataTBA("/event/2017njfla/matches", lastModified);

	if(resp.getResponseCode()!=304){ // HTTP code 304 indicates no change
		teamList = Deserializer.jsonToTeamArray(resp.getJson());
		lastModified = resp.getLastModified();
		}
	}

## Models

A list of object model classes and their getter methods for instance variables can be found [here](http://raiderrobotix.github.io/blue-alliance-api-java-library/com/thebluealliance/api/v3/models/package-summary.html). Please note that the `master` branch of this repository contains updated object models for the current season's code, and object models for past seasons can be found in other branches.

## Dependencies

You will need [Gson](https://github.com/google/gson) to use the released compiled TBA API JAR file in your project. Gson can be installed with [Maven](https://maven-badges.herokuapp.com/maven-central/com.google.code.gson/gson), via a [JAR file](http://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.1/), or with Gradle if you include the following in your `build.gradle`

    dependencies {
    	compile 'com.google.code.gson:gson:2.2.4'
    }

Note that you will need Gradle to compile this repository's source code if you do not get Gson.


## Contact

Feel free to contact Spencer Ng at sng1488 (at) gmail (dot) com or create a pull request if you have any questions, fixes, or suggestions. 

## Build notes
(FRC 2410 Mentor)

Following a successful project build or rebuild, perform a Gradle build.
  1. Either select Gradle from the screen panel or using View | Tools Window | Gradle
  2. Reload Gradle using the double arrow in the upper right section
  3. Expend Task | Build
  4. Double click 'build'
  5. Double click 'jar'
  Additional steps if the jason jar file is missing from the build/libs directory
  6. Copy the gson-x.x.x.jar file to the build/libs directory
  7. Reload Gradle
  8. Repeat steps 3 - 5.
  
Check that TBA Java APIv3.jar is created with the current date and time stamps.
Check that the following files are generated
 * out/artifacts/release/TBA-APIv3-Java-Library-2.0.jar
 * out/artifacts/TBA_APIv3_Java_Library_X_X.jar/TBA_Java_APIv3_main.jar
 