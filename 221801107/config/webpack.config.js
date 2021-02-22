const { DIST_PATH, INDEX_APP_PATH } = require("./paths");

module.exports = {
  mode: "development",
  entry: INDEX_APP_PATH,
  output: {
    path: DIST_PATH,
    filename: 'index.js',
  },
};
