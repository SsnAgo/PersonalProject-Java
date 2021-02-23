const path = require("path");
const fs = require("fs");
const { ROOT_PATH } = require("./config/paths");
const {
  calCharacterCount,
  calWordCount,
  calNoEmptyRowsCount,
  calSortedWordsFrequency,
} = require("./lib/wordCount");

const main = () => {
  try {
    console.log("--------------");
    console.log("Run program...");
    const argvs = process.argv;
    if (argvs.length < 4) {
      console.error("Error: please input two files");
      return;
    }

    const input = path.join(ROOT_PATH, argvs[2]);
    const output = path.join(ROOT_PATH, argvs[3]);

    if (!fs.existsSync(input)) {
      console.error("Error: readFile not exist");
      return;
    }

    const content = fs.readFileSync(input).toString();

    const writeContent = `${calCharacterCount(content)}
${calWordCount(content)}
${calNoEmptyRowsCount(content)}
${calSortedWordsFrequency(content, 10).map(
    (item) => `${item.word}: ${item.count}\n`,
  ).join("")}`;

    fs.writeFileSync(output, writeContent);
    console.log("Finish Program...");
  } catch (ex) {
    console.error(ex.message);
    console.error("sorry, it must be some error in program");
  } finally {
    console.log("--------------");
  }
};

main();
