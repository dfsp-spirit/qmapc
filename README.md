# qmapc
Map file parser and converter for the Quake FPS games


## License and author

   qmapc was written by Tim Schaefer, http://rcmd.org/ts/
   
   For license info, see the LICENSE file.

## Usage

   Not ready yet.
   

## Building

   This project uses Maven. To build:
   
   `mvn package`
   

## Tests

   Unit tests using junit are included. They should be run automatically during the build. To run them manually:

   `mvn test`
  
## Detailed info on how to build, run and use qmapc

   These instructions are targeted at end users who want to build the qmapc JAR file from source. Thanks to the Maven wrapper script, this is very easy under Unix-like systems (Linux, OSX) and Windows. Basically all you need to know is some shell basics.
  
1. Download a copy of the qmapc repository as a zip archive and unzip it (or just clone the repository using git in case you have that installed). See https://github.com/dfsp-spirit/qmapc
2. Start a terminal and change into the directory that contains your copy of the repository.
`cd path-to-your-download-dir`
`cd qmapc`
3. Build the software using the supplied Maven wrapper script (this will download and install the correct Maven version in case you don't have it yet):
    * Linux, OSX, ... : `./mvnw clean package`
    * Windows: `./mvnw clean package`
4. You can now run the jar file that has been produced and stored in the `target` directory:
`cd target`
`java -jar qmapc-<VERSION>.jar -h`
5. Optional: copy the jar file to some convenient location, maybe add that location to your PATH environment variable.
   

## Contributing

The repo is at https://github.com/dfsp-spirit/qmapc

Take care to maintain the existing coding style. Add unit tests for any new or changed functionality. Test your code, also make sure you agree with the license. Then send pull request.
