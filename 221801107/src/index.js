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
  const argvs = process.argv;
  if (argvs.length < 4) {
    console.error("please input two files");
    return;
  }

  const input = path.join(ROOT_PATH, argvs[2]);
  const output = path.join(ROOT_PATH, argvs[3]);

  if (!fs.existsSync(input)) {
    console.error("readFile not exist");
  }

  const content = fs.readFileSync(input).toString();

  const writeContent = `${calCharacterCount(content)}
  ${calWordCount(content)}
  ${calNoEmptyRowsCount(content)}
  ${calSortedWordsFrequency(content, 10).map(
    (item) => `${item.word}${item.count}`,
  )}
  `;

  fs.writeFileSync(output, writeContent);
};

main();

module.exports = {
  main,
};
