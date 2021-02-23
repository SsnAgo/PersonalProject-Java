module.exports = {
  env: {
    node: true,
    jest: true,
  },
  extends: "airbnb",
  parserOptions: {
    ecmaVersion: 12,
    sourceType: "module",
  },
  rules: {
    quotes: 0,
    "linebreak-style": 0,
    "no-console": 0,
    "global-require": 0,
  },
};
