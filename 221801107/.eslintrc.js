module.exports = {
  env: {
    node: true,
  },
  extends: "airbnb",
  parserOptions: {
    ecmaVersion: 12,
    sourceType: "module",
  },
  rules: {
    quotes: 0,
    "import/prefer-default-export": 0,
    "linebreak-style": 0,
  },
};
