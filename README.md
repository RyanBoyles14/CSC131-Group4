# Group 4 Metrics Project
![](https://img.shields.io/github/contributors/CSC131Fall2018/Group4.svg)
![](https://img.shields.io/github/last-commit/CSC131Fall2018/Group4.svg)
![](https://img.shields.io/github/downloads/CSC131Fall2018/Group4/total.svg)

A work in progress...

## Building and Installing
```
git clone https://github.com/CSC131Fall2018/Group4.git
cd Group4
mvn clean install
```

## Usage
```
java -jar target/g4m-1.0.jar [-cChHIrt] [--output-to-stdout] [--print-all-metrics]
                                    [--output-format=<outputFormat>]
                                    [--output-to-file=<outputFilename>] <Git url>
```

### Options

    --output-format=<outputFormat>      Format output in JSON or XML. JSON enabled by default.
    --output-to-file=<outputFilename>   Send output to specified file.
    --output-to-stdout                  Send output to stdout. Enabled by default.
    --print-all-metrics                 print all metrics
    -c, --contributor                   print contributor metrics
    -C, --coupling                      print coupling metric
    -h, --help                          print help information
    -H, --halstead                      print halstead metric
    -I, --inheritanceDepth              print depth of inheritance metric
    -r, --overall-repository            print overall repository metrics
    -t, --timeComplexity                print time complexity metric


## Authors
- [Johnny Au](https://github.com/johnny-au)
- [Nathan Whitfield](https://github.com/natewhitfield)
- [Ryan Boyles](https://github.com/RyanBoyles14)
- [Tommy Tran](https://github.com/hangrytommy)
- [Kyle Yang](https://github.com/kyleYzn)