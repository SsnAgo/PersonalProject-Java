const path = require('path');

const SRC_PATH = path.join(__dirname, '..', 'src');
const DIST_PATH = path.join(__dirname, '..', 'dist');
const INDEX_APP_PATH = path.join(SRC_PATH, 'index.js');

module.exports = {
  SRC_PATH,
  DIST_PATH,
  INDEX_APP_PATH,
};
