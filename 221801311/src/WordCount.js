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
// console.log(fileData);

// count charactor
let charactorSize = tool.countChar(fileData);
// console.log(tool.countChar(fileData));

// count word
let wordSize = tool.countWord(fileData);
// console.log(tool.countWord(fileData));

// count line
let lineSize = tool.countValidLine(fileData);
// console.log(tool.countValidLine(fileData));

// count top occur
let topOccur = tool.countTopOccur(fileData);
// console.log(tool.countTopOccur(fileData));

// generate output
let outData = "characters: " + charactorSize;
outData += "\nwords: " + wordSize;
outData += "\nlines: " + lineSize;
for(let i=0; i<topOccur.length; i++) {
    outData += "\n" + topOccur[i].words + ": " + topOccur[i].frequency;
}
console.log(outData);

// write file
let writeSuccess = tool.writeFile(outUrl, outData);
if(!writeSuccess) {
    return;
}