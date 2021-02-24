// get command parameter
let inUrl = process.argv[2];
let outUrl = process.argv[3];
if(inUrl == undefined || outUrl == undefined) {
    console.log("missing parameter");
    return;
}
// console.log("input file is " + inUrl);
// console.log("output file is " + outUrl);

let tool = require('./Lib');

// read file
let fileData = tool.readFile(inUrl);
console.log(fileData);

// write file
let outData = "test output"
let writeSuccess = tool.writeFile(outUrl, outData);
console.log(writeSuccess);
if(!writeSuccess) {
    return;
}