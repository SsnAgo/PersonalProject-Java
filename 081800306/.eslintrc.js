module.exports = {
  env: {
    browser: true,
    commonjs: true,
    es2021: true,
  },
  extends: [
    'airbnb-base',
  ],
  parserOptions: {
    ecmaVersion: 12,
  },
  rules: {
    'linebreak-style': ['off', 'windows'],
    'no-restricted-syntax': ['error', 'WithStatement'],
    'no-control-regex': 0,
    'no-useless-escape': 0,
    'max-classes-per-file': 0,
    'no-console': 0,
  },
};
