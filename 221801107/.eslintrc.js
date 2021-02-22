module.exports = {
  env: {
    browser: true,
    es2021: true,
  },
  extends: "airbnb",
  parserOptions: {
    ecmaVersion: 12,
    sourceType: "module",
  },
  rules: {
    quotes: [0, "double"],
    "import/prefer-default-export": 0,
    "linebreak-style": 0,
  },
};
