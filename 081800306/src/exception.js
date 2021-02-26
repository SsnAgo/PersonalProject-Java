class Exception extends Error {
  constructor(msg = '出现了异常', status = 500) {
    super();
    this.msg = msg;
    this.status = status;
  }
}

class NotFindException extends Exception {
  constructor(msg = '资源未找到', status = 404) {
    super();
    this.msg = msg;
    this.status = status;
  }
}

class ParamException extends Exception {
  constructor(msg = '参数错误', status = 400) {
    super();
    this.msg = msg;
    this.status = status;
  }
}

module.exports = { Exception, NotFindException, ParamException };
