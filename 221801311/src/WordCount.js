// get command parameter
let inUrl = process.argv[2];
let outUrl = process.argv[3];
if( (inUrl == undefined) || (outUrl == undefined) ) {
    console.log("missing parameter");
    return;
}
console.log("input file is " + inUrl);
console.log("output file is " + outUrl);